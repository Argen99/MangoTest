package com.example.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.data.local.JwtTokenDataStore
import com.example.data.local.JwtTokenManager
import com.example.data.remote.api_service.AuthApiService
import com.example.data.remote.api_service.MainApiService
import com.example.data.remote.interceptor.AccessTokenInterceptor
import com.example.data.remote.interceptor.AuthAuthenticator
import com.example.data.remote.repository.AuthRepositoryImpl
import com.example.data.remote.repository.MainRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.MainRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://plannerok.ru/"

val dataModule = module {
    single(named("Unauthenticated")) { provideUnauthenticatedOkHttpClient() }
    single(named("Authenticated")) { provideAuthenticatedOkHttpClient(get(), get()) }

    single { provideAuthApiService(get(named("Unauthenticated"))) }
    single { provideMainApiService(get(named("Authenticated"))) }
    singleOf(::AuthRepositoryImpl) {
        bind<AuthRepository>()
    }
    singleOf(::MainRepositoryImpl) {
        bind<MainRepository>()
    }
    singleOf(::provideDataStore)
    singleOf(::provideJwtTokenManager)
    singleOf(::AccessTokenInterceptor)
    singleOf(::AuthAuthenticator)
}

fun provideAuthApiService(okHttpClient: OkHttpClient): AuthApiService {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(AuthApiService::class.java)
}

fun provideMainApiService(okHttpClient: OkHttpClient): MainApiService {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(MainApiService::class.java)
}

fun provideUnauthenticatedOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun provideAuthenticatedOkHttpClient(
    accessTokenInterceptor: AccessTokenInterceptor,
    authAuthenticator: AuthAuthenticator
): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient().newBuilder()
        .authenticator(authAuthenticator)
        .addInterceptor(accessTokenInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun provideDataStore(context: Context): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ),
        produceFile = { context.preferencesDataStoreFile("auth_pref") }
    )
}

fun provideJwtTokenManager(dataStore: DataStore<Preferences>): JwtTokenManager {
    return JwtTokenDataStore(dataStore = dataStore)
}