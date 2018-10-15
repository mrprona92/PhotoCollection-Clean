package com.binhth.photocollection.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binhth.photocollection.R
import com.binhth.photocollection.ui.screen.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
                .replace(R.id.parent, MainFragment.newInstance(), MainFragment.TAG)
                .commit()
    }
}
