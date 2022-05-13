package com.example.connect.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.connect.R
import com.example.connect.common.fragments.ChatsFragment
import com.example.connect.common.fragments.ProfileFragment
import com.example.connect.common.fragments.SettingsFragment
import com.example.connect.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = setSupportActionBar(binding.toolbar)
        auth = Firebase.auth
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.chatsMenuItem -> {
                    navigationFragment(ChatsFragment())
                    binding.toolbar.title = "Chats"
                }
                R.id.profileMenuItem -> {
                    navigationFragment(ProfileFragment())
                    binding.toolbar.title = "Profile"
                }
                R.id.settingsMenuItem -> {
                    navigationFragment(SettingsFragment())
                    binding.toolbar.title = "Settings"
                }
            }
            true
        }
    }
    private fun navigationFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerView, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_navigate_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (R.id.itemMenuExit == item.itemId) {
            auth.signOut()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}