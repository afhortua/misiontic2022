package com.example.misiontic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.misiontic.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val transaction = supportFragmentManager.beginTransaction()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        transaction.replace(binding.frag.id, POIListFragment())
        transaction.commit()
    }
}
