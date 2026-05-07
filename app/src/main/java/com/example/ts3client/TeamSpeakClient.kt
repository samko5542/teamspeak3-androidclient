package com.example.ts3client

import android.util.Log

interface TeamSpeakClient {
    fun connect(server: String, nickname: String): Boolean
    fun setPushToTalk(enabled: Boolean)
    fun disconnect()
}

class NativeTeamSpeakClient : TeamSpeakClient {
    override fun connect(server: String, nickname: String): Boolean {
        Log.i("TS3", "Mock connect to $server as $nickname")
        return server.isNotBlank() && nickname.isNotBlank()
    }

    override fun setPushToTalk(enabled: Boolean) {
        Log.i("TS3", "Mock PTT: $enabled")
    }

    override fun disconnect() {
        Log.i("TS3", "Mock disconnect")
    }
}
