package com.example.test_app.logic

import android.content.Context
import android.content.ServiceConnection
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.test_app.data.AllData
import com.example.test_app.data.DataSpace
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.Scanner
import java.util.concurrent.Executors

class Wifi(val port:Int = 5555):Thread(){
    override fun run() {

        Log.i("paradise", "the myth")
        val serverSocket= ServerSocket(port)
        while (true){
            //  listen to the next connection
            val acceptedConnection = serverSocket.accept()

            ClientHandler(acceptedConnection = acceptedConnection).start()
        }
    }
}

class ClientHandler(var acceptedConnection: Socket):Thread(){
    override fun run() {
        val dataBuffer = Scanner(acceptedConnection.getInputStream())
        Log.d("ACCADDR","address is ->" + acceptedConnection.inetAddress.address.toString())
        //  read the received data
        while (acceptedConnection.isConnected){

            if(acceptedConnection.isClosed)
                break

            //  read data
            if(dataBuffer.hasNextLine()) {
                val data = dataBuffer.nextLine()
                //  parse data from json to actual class
                try {
                    }catch (e : java.lang.NumberFormatException){
                    //  abort operation
                    //  mybe display message
                }
                val parsedData = Gson().fromJson(data, AllData::class.java)
                DataSpace.updateData(parsedData)

            }
        }
    }
}

