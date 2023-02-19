package com.isllam.texttsap.ui.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.isllam.texttsap.R
import com.isllam.texttsap.databinding.FragmentSelectAppSheetBinding
import com.isllam.texttsap.ui.MainActivity
import com.isllam.texttsap.utils.Constants
import kotlin.properties.Delegates


class SelectAppSheetFragment : BottomSheetDialogFragment() {


    private lateinit var binding: FragmentSelectAppSheetBinding
    private lateinit var parent: MainActivity
    private var message by Delegates.notNull<String>()
    private var number by Delegates.notNull<String>()
    private var isBusiness by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectAppSheetBinding.inflate(inflater)
        parent = requireActivity() as MainActivity
        message = requireContext().getString(Constants.MESSAGE)
        onClick()

        return binding.root
    }

    private fun onClick() {

    }

    @SuppressLint("CutPasteId")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            val bottomSheet =
                bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior: BottomSheetBehavior<*> =
                BottomSheetBehavior.from(bottomSheet!!)
            behavior.skipCollapsed = true
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return bottomSheetDialog
    }

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme
}