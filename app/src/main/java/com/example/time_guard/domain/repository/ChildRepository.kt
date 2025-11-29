package com.example.time_guard.domain.repository

import com.example.time_guard.domain.model.DeviceInfo
import kotlinx.coroutines.flow.Flow

interface ChildRepository {

    suspend fun sendDeviceInfo(deviceInfo: DeviceInfo)

    fun listenBlockedApps(deviceId: String): Flow<List<String>>
}
