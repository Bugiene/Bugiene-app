package com.example.bugiene.ui.profile

import android.os.Bundle
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

    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var historyViewModel: HistoryViewModel


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
        binding.customToolbar.teks.text = "Profile"

        setUpProfile()
        setUpHistory()
        observeHistory()
    }

    private fun setUpProfile(){
        profileViewModel = ViewModelProvider(this)[GetUserViewModel::class.java]

        profileViewModel.user.observe(viewLifecycleOwner) {
            binding.tvName.text = it.username
            binding.tvEmail.text = it.email
        }
    }
    private fun setUpHistory() {
        binding.rvHistory.layoutManager = LinearLayoutManager(context)
        historyAdapter = HistoryAdapter(null)
        binding.rvHistory.adapter = historyAdapter
    }
    private fun observeHistory() {
        historyViewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        historyViewModel.getHistory()
        historyViewModel.history.observe(viewLifecycleOwner) { historyResponse ->
            Log.d("msg", "hasil {$historyResponse}")
            if (historyResponse != null) {
                historyAdapter.listHistory = historyResponse.listHistory
                historyAdapter.notifyDataSetChanged()
                binding.tvMessage.visibility = View.GONE
            } else {
                binding.tvMessage.visibility = View.VISIBLE
            }
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