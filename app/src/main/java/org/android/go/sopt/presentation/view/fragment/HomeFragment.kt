package org.android.go.sopt.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import org.android.go.sopt.adapter.RepoRVAdapter
import org.android.go.sopt.data.model.response.ResponseUserListDto.Data
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.presentation.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val viewModel by viewModels <HomeViewModel>()
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) {
            "binding null"
        } // null 이 아닐때만 호출.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserList()
        setUserList()
    }

    private fun getUserList() {
        viewModel.getUserList()
    }

    private fun setUserList(){
        viewModel.userResult.observe(requireActivity()){
            Log.e("hyeon",it.toString())
            initAdapter(it)
        }
    }
    private fun initAdapter(userList : List<Data>) {
        val concatAdapter = ConcatAdapter(RepoRVAdapter(requireContext(), userList))
        with(binding) {
            rvHome.adapter = concatAdapter
            rvHome.layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}