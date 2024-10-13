package com.example.data.utils

interface DataMapper<out T> {
    fun toModel(): T
}