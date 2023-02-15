package com.isllam.texttsap.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.isllam.texttsap.R
import com.isllam.texttsap.databinding.FragmentHomeBinding
import com.isllam.texttsap.ui.MainActivity
import com.isllam.texttsap.utils.Constants


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
            parent.navController.navigate(
                R.id.action_homeFragment_to_selectAppSheetFragment,
                bundleOf(
                    Constants.PHONE to "${binding.ccp.selectedCountryCode}+${binding.phone.text}",
                    Constants.MESSAGE to binding.message.text.toString()
                )
            )

            /*getLaunchIntent(
                "${binding.ccp.selectedCountryCode}+${binding.phone.text}",
                binding.message.text.toString(), true
            ).launchIfResolved(requireContext())*/
        }
    }



}