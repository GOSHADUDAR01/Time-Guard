package com.example.time_guard.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.Flow

class FirebaseRemoteDataSource {

    private val firestore = FirebaseFirestore.getInstance()

    fun listenBlockedApps(deviceId: String): Flow<List<String>> = callbackFlow {
        val listener = firestore.collection("parent_controls")
            .document(deviceId)
            .addSnapshotListener { snapshot, _ ->
                val list = snapshot?.get("blockedPackages") as? List<*>
                val blocked = list?.filterIsInstance<String>() ?: emptyList()
                trySend(blocked)
            }

        awaitClose { listener.remove() }
    }

    suspend fun sendDeviceInfo(deviceId: String, data: Map<String, Any>) {
        firestore.collection("child_devices")
            .document(deviceId)
            .set(data)
    }
}
