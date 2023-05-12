package org.android.go.sopt.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import org.android.go.sopt.adapter.RepoRVAdapter
import org.android.go.sopt.data.ApiFactory
import org.android.go.sopt.data.ApiFactory.retrofitUserList
import org.android.go.sopt.data.ResponseUserListDto.Data
import org.android.go.sopt.data.ResponseUserListDto
import org.android.go.sopt.data.RetrofitService
import org.android.go.sopt.data.UserListService
import org.android.go.sopt.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private var userList:List<Data> = arrayListOf()
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
        setUserList()
    }

    private fun setUserList() {
        val retrofitService = retrofitUserList.create(UserListService::class.java)
        retrofitService.getUserList(1).enqueue(object : Callback<ResponseUserListDto> {
            override fun onResponse(
                call: Call<ResponseUserListDto>,
                response: Response<ResponseUserListDto>
            ) {
                if (response.isSuccessful) {
                    userList = response.body()?.data!!
                    Log.e("hyeon",userList.toString())
                    initAdapter()
                }

                else {
                    Log.e("hyeon",response.body().toString())
                }
            }

            override fun onFailure(call: Call<ResponseUserListDto>, t: Throwable) {
                t.message?.let {
                    Log.e("hyeon", "onFailure $it")
                }
            }
        })
    }

    private fun initAdapter() {
        val concatAdapter = ConcatAdapter(RepoRVAdapter(requireContext(),userList))
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