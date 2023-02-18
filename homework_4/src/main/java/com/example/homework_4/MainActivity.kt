package com.example.homework_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, CustomFragment())
            .commit()
    }
}

fun Fragment.addFragment(fragment: Fragment) {
    val activity = requireActivity() as? MainActivity ?: error ("")
    activity
        .supportFragmentManager
        .beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack(null)
        .commit()
}