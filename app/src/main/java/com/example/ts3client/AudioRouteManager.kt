package com.example.ts3client

import android.content.Context
import android.media.AudioDeviceInfo
import android.media.AudioManager

class AudioRouteManager(context: Context) {
    private val audioManager = context.getSystemService(AudioManager::class.java)

    fun configureForVoice() {
        audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
        audioManager.isBluetoothScoOn = hasBluetoothMic()
        if (audioManager.isBluetoothScoOn) {
            audioManager.startBluetoothSco()
        }
    }

    private fun hasBluetoothMic(): Boolean =
        audioManager.getDevices(AudioManager.GET_DEVICES_INPUTS).any {
            it.type == AudioDeviceInfo.TYPE_BLUETOOTH_SCO || it.type == AudioDeviceInfo.TYPE_BLUETOOTH_A2DP
        }

    fun reset() {
        audioManager.stopBluetoothSco()
        audioManager.isBluetoothScoOn = false
        audioManager.mode = AudioManager.MODE_NORMAL
    }
}
