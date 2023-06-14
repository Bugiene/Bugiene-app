package com.example.bugiene.ui.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bugiene.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: GetUserViewModel

    private var username: String? = null
    private var email: String? = null

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var historyViewModel: HistoryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customToolbar.btLogout.setOnClickListener {
            val dialogFragment = LogoutDialogFragment()
            showLogoutDialog()
        }
        binding.customToolbar.teks.text = "Profile"
        binding.batas.visibility = View.GONE
//        setUpProfile()
        saveData()
        setUpHistory()
        observeHistory()
        historyViewModel.getHistory()
    }

    private fun setUpHistory() {
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeHistory() {
        historyViewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        Log.d("VIEWMODEL", "HistoryViewModel initialized")
        historyViewModel.history.observe(viewLifecycleOwner) { historyResponse ->
            Log.d("msg", "hasil {$historyResponse}")
            if (historyResponse != null) {
                historyResponse.listHistory?.let {
                    historyAdapter = HistoryAdapter(it)
                    binding.rvHistory.adapter = historyAdapter
                }
                historyAdapter.notifyDataSetChanged()
                binding.ivEmptyState.visibility = View.GONE
                binding.tvMessage.visibility = View.GONE
                binding.batas.visibility  = View.GONE
            } else {
                binding.ivEmptyState.visibility = View.VISIBLE
                binding.tvMessage.visibility = View.VISIBLE
                binding.batas.visibility  = View.GONE
            }
        }
    }

    private fun showLogoutDialog() {
        val dialogFragment = LogoutDialogFragment()
        dialogFragment.show(parentFragmentManager, "LogoutDialog")
    }

    //Backup
    private fun saveData() {
        username = sharedPreferences.getString("PREF_USERNAME", username)
        email = sharedPreferences.getString("PREF_EMAIL", email)
        Log.d("pesan", "message : {${username} and {${email}")
        binding.tvName.text = username
        binding.tvEmail.text = email
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpProfile() {
        profileViewModel = ViewModelProvider(this)[GetUserViewModel::class.java]

        profileViewModel.user.observe(viewLifecycleOwner) { userResultResponse ->
            binding.tvName.text = userResultResponse.username
            binding.tvEmail.text = userResultResponse.email
        }

        profileViewModel.getUserData()
    }
}