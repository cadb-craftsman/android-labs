package com.woowrale.searchpatterns.kapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener({
            val search = searchNifText.text.toString()
            if (validateNIF(search)) {
                Log.e("SEARCH", "Is a NIF")
            }
            if (validateNIE(search)) {
                Log.e("SEARCH", "Is a NIE")
            }
            if (validateFullName(search)) {
                Log.e("SEARCH", "Is a NAME")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun validateNIF(data: String): Boolean {
        var isOk = false
        val p = Pattern.compile("[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]")
        if (p.matcher(data).find()) {
            isOk = true
        }
        return isOk
    }

    private fun validateNIE(data: String): Boolean {
        var isOk = false
        val p = Pattern.compile("[XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]")
        if (p.matcher(data).find()) {
            isOk = true
        }
        return isOk
    }

    private fun validateFullName(data: String): Boolean {
        var isOk = false
        val p = Pattern.compile("^[a-zA-Z\\s]*$")
        if (p.matcher(data).find()) {
            isOk = true
        }
        return isOk
    }
}
