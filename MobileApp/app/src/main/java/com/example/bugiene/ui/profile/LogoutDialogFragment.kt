package com.example.bugiene.ui.profile

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.bugiene.databinding.FragmentLogoutDialogBinding
import com.example.bugiene.ui.login.LoginActivity

class LogoutDialogFragment : DialogFragment() {

    private lateinit var sharedPreferences: SharedPreferences

    private var _binding: FragmentLogoutDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogoutDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultButton()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    private fun resultButton() {
        binding.btnLogoutNo.setOnClickListener {
            dismiss()
        }

        binding.btnLogoutYes.setOnClickListener {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
            val token = sharedPreferences.getString("PREF_TOKEN", null)
            val userId = sharedPreferences.getString("PREF_ID", null)

            sharedPreferences.edit().remove("PREF_TOKEN").apply()
            sharedPreferences.edit().remove("PREF_ID").apply()

            Log.d("LogoutDialogFragment", "Token: $token")
            Log.d("LogoutHASILID", "User ID: $userId")

            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
            dismiss()
        }
    }

}