package com.example.myintentmaftuh

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnMoveActivity = findViewById<Button>(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener{
            onClick()
        }

        val btnDialNumber = findViewById<Button>(R.id.btn_dial_number)
        btnDialNumber.setOnClickListener{
            onDial()
        }

        val btnSendSms = findViewById<Button>(R.id.btn_send_sms)
        btnSendSms.setOnClickListener {
            onSendSms()
        }
    }

    private fun onClick() {
        val intent = Intent(applicationContext, MoveActivity::class.java)
        startActivity(intent)
    }

    private fun onDial() {
        val dialNumber = "08124356789"
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + dialNumber))
        startActivity(intent)
    }

    private fun onSendSms() {
        val phoneNumber = "08123456789"
        val message = "Halo, ini pesan dari aplikasi!"

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:$phoneNumber") // Only SMS apps should handle this
            putExtra("sms_body", message)
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No SMS app available", Toast.LENGTH_SHORT).show()
        }
    }

}