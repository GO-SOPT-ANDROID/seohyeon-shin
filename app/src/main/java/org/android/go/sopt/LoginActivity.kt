package org.android.go.sopt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.android.go.sopt.base.BaseActivity
import org.android.go.sopt.data.*
import org.android.go.sopt.data.Login.Companion.getId
import org.android.go.sopt.data.Login.Companion.getPwd
import org.android.go.sopt.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Response

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val retrofitService = ServicePool.retrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickLoginBtn()
        clickSignUpBtn()
    }

    private fun clickSignUpBtn() {
        with(binding) {
            btnSignup.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            }
        }
    }

    private fun clickLoginBtn() {
        with(binding) {
            btnLogin.setOnClickListener {
                completeLogin()
            }
        }
    }

    private fun completeLogin() {
        retrofitService.login(
            with(binding) {
                RequestLoginDto(
                    etId.text.toString(),
                    etPw.text.toString()
                )
            }
        ).enqueue(object : retrofit2.Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.let {
                        if (response.body()?.status == 200) {
                            Log.e("hyeon", response.body()?.data?.id.toString())
                            Toast.makeText(
                                this@LoginActivity,
                                "로그인이 성공되었습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                            if (!isFinishing) finish()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        }
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "비밀번호 또는 아이디가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                t.message?.let {
                    Log.e("hyeon", "onFailure $it")
                    Toast.makeText(this@LoginActivity, "서버통신 실패 응답값이 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
