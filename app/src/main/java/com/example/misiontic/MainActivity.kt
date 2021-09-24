package com.example.misiontic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val trans = supportFragmentManager.beginTransaction()
        val frag = POIDetailFragment()
        trans.replace(R.id.frag, frag)
        trans.commit()
    }
}
