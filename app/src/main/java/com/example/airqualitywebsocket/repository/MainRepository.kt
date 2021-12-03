package com.example.airqualitywebsocket.repository

import android.util.Log
import com.example.airqualitywebsocket.SocketListener
import com.example.airqualitywebsocket.pojo.MData
import com.example.airqualitywebsocket.utils.genericType
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class MainRepository(private val request: Request, private val client: OkHttpClient) {
    lateinit var webSocket: WebSocket
    suspend fun getAirQuality(): List<MData> {
        val aiqList = ArrayList<MData>()
        withContext(Dispatchers.IO) {
            webSocket = client.newWebSocket(request, SocketListener(object : CallBack {
                override fun onMessageRecieved(text: String) {
                    aiqList.addAll(Gson().fromJson<List<MData>>(text, genericType<List<MData>>()))
                }

                override fun onFailure(message: String?) {

                }
            }))
        }
        Log.e("Receive Repository size", aiqList.size.toString())
        delay(2000)
        webSocket.close(1000, "closed by guest")
        return aiqList
    }
}
