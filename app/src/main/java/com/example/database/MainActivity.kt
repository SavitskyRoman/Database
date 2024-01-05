package com.example.database

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
//    override fun onBackPressed() {
//        AlertDialog.Builder(this).apply {
//            setTitle("Подтверждение")
//            setMessage("Вы уверены, что хотите выйти из программы?")
//
//            setPositiveButton("Таки да") { _, _ ->
//                super.onBackPressed()
//            }
//
//            setNegativeButton("Нет"){_, _ ->
//                // if user press no, then return the activity
//                Toast.makeText(this@MainActivity, "Thank you",
//                    Toast.LENGTH_LONG).show()
//            }
//            setCancelable(true)
//        }.create().show()
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }


}