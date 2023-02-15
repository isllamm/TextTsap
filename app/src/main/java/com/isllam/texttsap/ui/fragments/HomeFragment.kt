package com.isllam.texttsap.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.isllam.texttsap.R
import com.isllam.texttsap.databinding.FragmentHomeBinding
import com.isllam.texttsap.ui.MainActivity


class HomeFragment : Fragment(R.layout.fragment_home) {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var parent: MainActivity


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(requireView())
        parent = requireActivity() as MainActivity

        onClick()
    }

    private fun onClick() {
        binding.btnSend.setOnClickListener {
            getLaunchIntent(
                binding.message.text.toString(),
                "${binding.ccp.selectedCountryCode}+${binding.phone.text}", true
            ).launchIfResolved(requireContext())
        }
    }




    //generate launch intent with the given parameters
    private fun getLaunchIntent(phoneNumber: String, message: String, business: Boolean): Intent {

        val total = "https://api.whatsapp.com/send?phone=" +
                phoneNumber.replace("+", "") + "&text=${message}"

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(total)
            `package` = if (business) "com.whatsapp.w4b" else "com.whatsapp"
        }
        return intent
    }

    //Extension function on Intent that launches if the required app is installed or shows
//a toast to inform that the desired application is not installed.
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