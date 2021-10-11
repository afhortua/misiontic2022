package com.example.misiontic.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.misiontic.R
import com.example.misiontic.databinding.ActivityMainBinding
import com.example.misiontic.viewmodel.POIviewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var model:POIviewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(POIviewModel::class.java)
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
            R.id.action_refresh -> {
                model.getPois()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

