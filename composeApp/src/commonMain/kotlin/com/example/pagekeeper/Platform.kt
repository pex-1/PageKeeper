package com.example.pagekeeper

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
