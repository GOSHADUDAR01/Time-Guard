package com.example.time_guard.domain.model

data class DeviceInfo(
    val deviceId: String = "",
    val status: String = "online",
    val lastSeen: Long = System.currentTimeMillis(),
    val installedApps: List<AppInfo> = emptyList()
)
