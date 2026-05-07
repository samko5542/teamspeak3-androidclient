package com.example.ts3client

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.ts3client.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tsClient: TeamSpeakClient
    private lateinit var audioRouteManager: AudioRouteManager

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        val granted = it.values.all { ok -> ok }
        binding.statusText.text = if (granted) "Permissions granted" else "Missing permissions"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        audioRouteManager = AudioRouteManager(this)
        tsClient = NativeTeamSpeakClient()
        requestPermissionsIfNeeded()

        binding.connectButton.setOnClickListener {
            val ok = tsClient.connect(binding.serverInput.text.toString(), binding.nicknameInput.text.toString())
            if (ok) {
                audioRouteManager.configureForVoice()
            }
            binding.statusText.text = if (ok) "Connected" else "Connection failed"
        }

        binding.pttButton.setOnCheckedChangeListener { _, isChecked ->
            tsClient.setPushToTalk(isChecked)
            binding.statusText.text = if (isChecked) "PTT active" else "PTT muted"
        }
    }

    override fun onDestroy() {
        tsClient.disconnect()
        audioRouteManager.reset()
        super.onDestroy()
    }

    private fun requestPermissionsIfNeeded() {
        val permissions = arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.BLUETOOTH_CONNECT
        )
        val missing = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (missing.isNotEmpty()) {
            permissionLauncher.launch(missing.toTypedArray())
        }
    }
}
