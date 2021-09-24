package com.example.misiontic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val transaction = supportFragmentManager.beginTransaction()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        transaction.replace(R.id.frag, POIListFragment(loadData("poi_list.json")))
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
