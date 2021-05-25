package com.scarecrow.ragdoll.topskin

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if(isNetworkConnected()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            showErrorDialog()
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }

    fun showErrorDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Ошибка")
            .setMessage("Отсутствует подключение к интернету")
            .setIcon(R.drawable.ic_logo)
            .setPositiveButton("ОК") {
                    dialog, id ->  dialog.cancel()
            }
        builder.create().show()
    }
}