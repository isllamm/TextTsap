package com.isllam.texttsap.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.isllam.texttsap.R
import com.isllam.texttsap.databinding.FragmentHomeBinding
import com.isllam.texttsap.ui.MainActivity
import java.net.URLEncoder


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
            sendMessage(
                binding.message.text.toString(),
                "${binding.ccp.selectedCountryCode}+${binding.phone.text}"
            )
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun sendMessage(message: String, number: String) {
        val packageManager: PackageManager = requireContext().packageManager
        val i = Intent(Intent.ACTION_VIEW)
        try {
            val url = "https://api.whatsapp.com/send?phone=$number&text=" + URLEncoder.encode(
                message,
                "UTF-8"
            )
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)
            if (i.resolveActivity(packageManager) != null) {
                requireContext().startActivity(i)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}