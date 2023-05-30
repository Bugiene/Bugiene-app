package com.example.bugiene.ui.profile

import android.app.DownloadManager.Query
import android.net.UrlQuerySanitizer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.bugiene.R
import com.example.bugiene.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: GetUserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customToolbar.btLogout.setOnClickListener {
            val dialogFragment = LogoutDialogFragment()
            showLogoutDialog()
        }

        profileViewModel = ViewModelProvider(this)[GetUserViewModel::class.java]

        profileViewModel.user.observe(viewLifecycleOwner) {
            binding.tvName.text = it.username
            binding.tvEmail.text = it.email
        }


    }

    private fun showLogoutDialog() {
        val dialogFragment = LogoutDialogFragment()
        dialogFragment.show(parentFragmentManager, "LogoutDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}