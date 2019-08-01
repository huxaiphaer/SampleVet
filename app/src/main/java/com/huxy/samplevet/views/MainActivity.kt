package com.huxy.samplevet.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.huxy.samplevet.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.navHostFragment))

    }

    override fun onSupportNavigateUp(): Boolean {

        return findNavController(R.id.navHostFragment).navigateUp()
    }
}
