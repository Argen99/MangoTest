package com.example.core_ui.extensions

import android.content.Context
import android.telephony.TelephonyManager
import android.widget.Toast

fun Context.showToast(message: String, durations: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, durations).show()
}

fun Context.getDefaultLangCode(context: Context): String {
    val localeCode: TelephonyManager =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    val countryCode = localeCode.networkCountryIso
    val defaultLocale = androidx.compose.ui.text.intl.Locale.current.language
    return countryCode?.ifBlank { defaultLocale } ?: defaultLocale
}