package com.example.time_guard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.time_guard.ui.child.ChildScreen
import com.example.time_guard.ui.child.ChildViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ChildViewModel(this)

        setContent {
            ChildScreen(viewModel)
        }
    }
}
