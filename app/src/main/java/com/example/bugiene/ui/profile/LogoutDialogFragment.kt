package com.example.bugiene.ui.profile

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.bugiene.R
import com.example.bugiene.ui.login.LoginActivity

class LogoutDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_logout_dialog, container, false)

        // Find your views and set click listeners
        val btnLogoutNo = view.findViewById<Button>(R.id.btnLogoutNo)
        val btnLogoutYes = view.findViewById<Button>(R.id.btnLogoutYes)

        btnLogoutNo.setOnClickListener {
            // Handle "No" button click
            dismiss()
        }

        btnLogoutYes.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish() //opsional
            dismiss()
        }

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            // Customize the dialog appearance
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}