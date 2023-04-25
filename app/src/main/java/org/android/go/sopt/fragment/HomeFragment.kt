package org.android.go.sopt.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.adapter.RepoRVAdapter
import org.android.go.sopt.adapter.RepoTitleRVAdapter
import org.android.go.sopt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding? = null
    private val binding:FragmentHomeBinding
        get() = requireNotNull(_binding) {
            "binding null"
        } // null 이 아닐때만 호출.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter(){
        val concatAdapter = ConcatAdapter(RepoTitleRVAdapter(requireContext()),RepoRVAdapter(requireContext()))
        with(binding){
            rvHome.adapter = concatAdapter
            rvHome.layoutManager = LinearLayoutManager(context)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}