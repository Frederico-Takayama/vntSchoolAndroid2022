package com.example.aula15

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isFirstTime()) {
            showWelcomeToast()
            updateFirstTime()
        }
    }

    private fun loadTextFieldValue() {
        val etField = findViewById<EditText>(R.id.et_field)
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        etField.setText(sharedPref.getString(TEXT_FIELD, ""))
    }

    private fun updateFirstTime() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean(FIRST_TIME_KEY, false)
            apply()
        }
    }

    private fun showWelcomeToast() {
        Toast.makeText(this, "first time", Toast.LENGTH_SHORT).show()
    }

    private fun isFirstTime(): Boolean {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getBoolean(FIRST_TIME_KEY, true)
    }

    companion object {
        const val FIRST_TIME_KEY = "FIRST_TIME"
        const val TEXT_FIELD = "TEXT_FIELD"
    }

    override fun onPause() {
        super.onPause()
        saveTextField()
    }

    override fun onResume() {
        super.onResume()
        loadTextFieldValue()
    }

    private fun saveTextField() {
        val etField = findViewById<EditText>(R.id.et_field)
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(TEXT_FIELD, etField.text.toString())
            apply()
        }
    }
}