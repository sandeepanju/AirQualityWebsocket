package com.example.airqualitywebsocket

import android.util.Log
import com.example.airqualitywebsocket.repository.CallBack
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class SocketListener(private val callBack: CallBack) : WebSocketListener() {

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        Log.e("SocketListner--", "Receive Message: $text")
        callBack.onMessageRecieved(text)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        Log.e("SocketListner--", "Closing Socket : $code / $reason")

    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        callBack.onFailure(t.message)
        Log.e("SocketListner--", "Error : " + t.message)
    }

}
