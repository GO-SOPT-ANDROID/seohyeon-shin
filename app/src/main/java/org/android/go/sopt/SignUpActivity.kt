package org.android.go.sopt

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.android.go.sopt.base.BaseActivity
import org.android.go.sopt.data.Login.Companion.setId
import org.android.go.sopt.data.Login.Companion.setPwd
import org.android.go.sopt.data.RequestSignUpDto
import org.android.go.sopt.data.ResponseSignUpDto
import org.android.go.sopt.data.ServicePool
import org.android.go.sopt.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val retrofitService = ServicePool.retrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickBtn()
    }

    private fun clickBtn() {
        binding.btnSignup.setOnClickListener {
            completeSignUp()
        }
    }

    private fun completeSignUp() {
        retrofitService.signUp(
            with(binding) {
                RequestSignUpDto(
                    etId.text.toString(),
                    etPw.text.toString(),
                    etName.text.toString(),
                    etSpecialty.text.toString()
                )
            }
        ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.let {
                        Log.e("hyeon", it)
                        with(binding) {
                            setId(etId.text.toString())
                            setPwd(etPw.text.toString())
                        }
                        Toast.makeText(this@SignUpActivity, "회원가입에 성공했습니다", Toast.LENGTH_SHORT)
                            .show()
                        if (!isFinishing) finish()
                    }
                } else {
                    response.body()?.message?.let {
                        Toast.makeText(this@SignUpActivity, "서버 통신 실패", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                t.message?.let {
                    Toast.makeText(this@SignUpActivity, "서버통신 실패 응답값이 없습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}