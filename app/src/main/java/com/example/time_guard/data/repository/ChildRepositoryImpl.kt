package com.example.time_guard.data.repository

import com.example.time_guard.data.remote.FirebaseRemoteDataSource
import com.example.time_guard.domain.model.DeviceInfo
import com.example.time_guard.domain.repository.ChildRepository
import kotlinx.coroutines.flow.Flow

class ChildRepositoryImpl(
    private val remote: FirebaseRemoteDataSource
) : ChildRepository {

    override suspend fun sendDeviceInfo(deviceInfo: DeviceInfo) {
        val map = mapOf(
            "deviceId" to deviceInfo.deviceId,
            "status" to deviceInfo.status,
            "lastSeen" to deviceInfo.lastSeen,
            "installedApps" to deviceInfo.installedApps
        )
        remote.sendDeviceInfo(deviceInfo.deviceId, map)
    }

    override fun listenBlockedApps(deviceId: String): Flow<List<String>> {
        return remote.listenBlockedApps(deviceId)
    }
}
