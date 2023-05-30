package com.example.bugiene

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bugiene.databinding.ActivityMainBinding
import com.example.bugiene.ui.dashboard.DashboardFragment
import com.example.bugiene.ui.identify.IdentifyActivity
import com.example.bugiene.ui.profile.LogoutDialogFragment
import com.example.bugiene.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(2)
        replaceFragment(DashboardFragment())


        binding.fabCamera.setOnClickListener {
            val intent = Intent(this@MainActivity, IdentifyActivity::class.java)
            startActivity(intent)
        }

        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){

                R.id.miDashboard -> replaceFragment(DashboardFragment())
                R.id.miProfile -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}