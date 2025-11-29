package com.example.time_guard.ui.child

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChildScreen(viewModel: ChildViewModel) {

    val blockedApps by viewModel.blockedApps.collectAsState()

    LaunchedEffect(blockedApps) {
        Log.d("BLOCKED_APPS", blockedApps.toString())
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Child App is running")
        Text(text = "Blocked apps: ${blockedApps.size}")
    }
}
