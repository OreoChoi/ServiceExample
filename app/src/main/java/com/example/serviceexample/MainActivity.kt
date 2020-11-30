package com.example.serviceexample

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start_service.setOnClickListener {
            Intent(this, ForegroundService::class.java).let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(it)
                } else {
                    startService(it)
                }
            }
        }

        btn_intent_service.setOnClickListener{
            Intent(this,MyIntentService::class.java).let {
                it.putExtra("MSG","Do something")
                startService(it)
            }
        }
    }

    override fun onDestroy() {
        Intent(this, ForegroundService::class.java).let {
            stopService(it)
        }
        super.onDestroy()
    }
}