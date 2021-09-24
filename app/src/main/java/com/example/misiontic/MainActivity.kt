package com.example.misiontic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.misiontic.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val transaction = supportFragmentManager.beginTransaction()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        transaction.replace(binding.frag.id, POIListFragment(loadData("poi_list.json")))
        transaction.commit()
    }

    private fun loadData(inFile: String): String {
        var content = ""
        try {
            val stream = assets.open(inFile)
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            content = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content
    }
}
