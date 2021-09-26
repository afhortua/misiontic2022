package com.example.misiontic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.misiontic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val transaction = supportFragmentManager.beginTransaction()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemConfig -> {
                supportFragmentManager.beginTransaction().replace(R.id.frag, SettingsFragment())
                    .addToBackStack(null).commit()
                return true
            }
            R.id.itemAbout -> {
                supportFragmentManager.beginTransaction().replace(R.id.frag, AboutFragment())
                    .addToBackStack(null).commit()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

