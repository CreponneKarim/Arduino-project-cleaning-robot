package com.example.test_app.logic

import android.util.Log
import com.example.test_app.data.AllData
import com.example.test_app.data.DataSpace
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit


object CallApi{
    val CONNECT_TIMEOUT = 10L
    val READ_TIMEOUT = 10L

    val client:OkHttpClient = OkHttpClient.Builder().connectTimeout(CONNECT_TIMEOUT,TimeUnit.MINUTES).readTimeout(READ_TIMEOUT, TimeUnit.MINUTES).build()

    suspend fun makeHttpRequestGET(url:String):String{
        val request = Request.Builder()
            .url(url)
            .header("isarduino","0")
            .build()
        return withContext(Dispatchers.IO){
            val response = client.newCall(request).execute()
            response.body?.string() ?: ""
        }
    }

    suspend fun makeHttpRequestPOST(url:String,body:String):String{
        val mediaType = "application/json".toMediaType()
        val requestBody = body.toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        Log.i("http_post_request_body", request.toString())
        return withContext(Dispatchers.IO){
            val response = client.newCall(request).execute()
            response.body?.string() ?: ""
        }
    }
}