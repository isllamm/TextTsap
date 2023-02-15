package com.isllam.texttsap.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.isllam.texttsap.R
import com.isllam.texttsap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment
        navController = navHostFragment.navController

        binding.navView.setupWithNavController(navController)

    }

    private fun getLaunchIntent(phoneNumber: String, message: String, business: Boolean): Intent {

        val total = "https://api.whatsapp.com/send?phone=" +
                phoneNumber.replace("+", "") + "&text=${message}"

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(total)
            `package` = if (business) "com.whatsapp.w4b" else "com.whatsapp"
        }
        return intent
    }


    @SuppressLint("QueryPermissionsNeeded")
    fun Intent.launchIfResolved(context: Context) {
        if (resolveActivity(context.packageManager) == null) Toast.makeText(
            context,
            context.getString(R.string.app_not_installed),
            Toast.LENGTH_SHORT
        ).show()
        else context.startActivity(this)
    }
}