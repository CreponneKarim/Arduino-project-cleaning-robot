package com.example.test_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.test_app.components.SplashScreen
import com.example.test_app.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this,R.color.blue2)
        setContent {
            AppTheme {

                rememberSystemUiController().setSystemBarsColor(
                    MaterialTheme.colors.primaryVariant
                )
                val systemUiController = rememberSystemUiController()
                Surface(
                    modifier = Modifier.fillMaxSize()
                ){
                    systemUiController.setSystemBarsColor(
                        MaterialTheme.colors.primaryVariant
                    )
                    SplashScreen()
                }
            }
        }
        lifecycleScope.launch {
            delay(timeMillis = 3000)
            startActivity(
                Intent(this@SplashScreenActivity,MainActivity::class.java)
            )
            finish()
        }
    }
}