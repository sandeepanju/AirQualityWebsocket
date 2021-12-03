package com.example.airqualitywebsocket.repository

interface CallBack {
    fun onMessageRecieved(text: String)
    fun onFailure(message: String?)

}
