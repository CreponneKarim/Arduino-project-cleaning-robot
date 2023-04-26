package com.example.test_app

import android.app.Activity
//import android.bluetooth.BluetoothAdapter
//import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test_app.components.DrawingCanvas
import com.example.test_app.components.OptionsMenu
import com.example.test_app.components.RobotStatusCard
import com.example.test_app.data.AllData
import com.example.test_app.data.DataSpace
import com.example.test_app.data.DistancePack
import com.example.test_app.logic.CallApi
import com.example.test_app.logic.Wifi
import com.example.test_app.ui.theme.AppTheme
import com.example.test_app.ui.theme.gray4
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.net.ServerSocket
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataSpace.fullPreviousData.vehicle.apply {
            badWaterLevel.value   = 0.1f
            cleanWaterLevel.value = 0.5f
            batteryLevel.value    = 0.3f
        }

        DataSpace.fullPreviousData.middleSensorData.updateDataBundle(
            mutableMapOf(
                "2023-12-12-00:01" to DistancePack()
            )
        )

        val requestBody = Gson().toJson(DataSpace.fullPreviousData).toString()

        Log.d("CREPONNEKARIM_CREPONNEKARIM",requestBody)

        Executors.newSingleThreadExecutor().execute{
            Looper.prepare()
            Wifi().start()
        }
        val result = GlobalScope.launch{
            val k="""{"name": "John Doe", "email": "johndoe@example.com"}""".trimIndent()
            val response = CallApi.makeHttpRequestPOST("https://cloudarduino.jaafarzaatar.repl.co",k)

            Log.i("http_response", response)

            val response2 = CallApi.makeHttpRequestGET("https://cloudarduino.jaafarzaatar.repl.co")

            Log.i("http_response", response2)
        }

        setContent {
            val speedState by remember {
                DataSpace.fullPreviousData.vehicle.speed
            }
            Log.d("speed", "new speed is  ${speedState}")
            AppTheme {
                var scrollState = rememberScrollState()

                rememberSystemUiController().setSystemBarsColor(
                    MaterialTheme.colors.background
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .background(MaterialTheme.colors.background)
                ) {
                    var padding = PaddingValues(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    )
                    DrawingCanvas(paddingValues = padding)
                    RobotStatusCard(paddingValues = padding)
                    OptionsMenu(paddingValues = padding)
                }

                DataSpace.fullPreviousData.vehicle.speed.value = 14.2f

            }
        }

    }
}

