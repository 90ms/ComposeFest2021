package com.a90ms.codelab01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.a90ms.codelab01.ui.theme.CodeLab01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeLab01Theme {
                CodeLap01App()
            }
        }
    }
}

