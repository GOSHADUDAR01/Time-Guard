package com.example.time_guard.ui.child

import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.time_guard.data.remote.FirebaseRemoteDataSource
import com.example.time_guard.data.repository.ChildRepositoryImpl
import com.example.time_guard.domain.model.AppInfo
import com.example.time_guard.domain.model.DeviceInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ChildViewModel(
    private val context: Context
) : ViewModel() {

    private val repo = ChildRepositoryImpl(FirebaseRemoteDataSource())

    val blockedApps = MutableStateFlow<List<String>>(emptyList())

    private val deviceId: String =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    init {
        sendDeviceInfo()
        observeBlocked()
    }

    private fun observeBlocked() {
        viewModelScope.launch {
            repo.listenBlockedApps(deviceId).collect {
                blockedApps.value = it
            }
        }
    }

    private fun sendDeviceInfo() {
        viewModelScope.launch {
            val apps = getInstalledApps()
            val device = DeviceInfo(
                deviceId = deviceId,
                installedApps = apps
            )
            repo.sendDeviceInfo(device)
        }
    }

    private fun getInstalledApps(): List<AppInfo> {
        val pm: PackageManager = context.packageManager
        return pm.getInstalledApplications(PackageManager.GET_META_DATA).map {
            AppInfo(
                packageName = it.packageName,
                name = pm.getApplicationLabel(it).toString()
            )
        }
    }
}
