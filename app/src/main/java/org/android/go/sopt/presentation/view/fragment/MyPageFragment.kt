package org.android.go.sopt.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.android.go.sopt.data.model.response.ResponseUserDto
import org.android.go.sopt.data.model.User
import org.android.go.sopt.data.model.ServicePool
import org.android.go.sopt.databinding.FragmentMypageBinding
import retrofit2.Call
import retrofit2.Response

class MyPageFragment : Fragment() {
    private val retrofitService = ServicePool.retrofitService
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) {
            "binding null"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUserData() {
        Log.e("hyeon", "id : ${User.getId()}")
        retrofitService.getUserData(User.getId())
            .enqueue(object : retrofit2.Callback<ResponseUserDto> {
                override fun onResponse(
                    call: Call<ResponseUserDto>,
                    response: Response<ResponseUserDto>
                ) {
                    if (response.isSuccessful) {
                        with(binding) {
                            response.body()?.data.apply {
                                tvId.text = "ID : ${this@apply?.id}"
                                tvName.text = "이름 : ${this@apply?.name}"
                                tvSpecialty.text = "특기 : ${this@apply?.skill}"
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                    t.message?.let {
                        Toast.makeText(requireContext(), "서버통신 실패 응답값이 없습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }
}