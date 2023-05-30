package org.android.go.sopt.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import org.android.go.sopt.R
import org.android.go.sopt.util.base.BaseActivity
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.presentation.viewmodel.SignUpViewModel

class SignUpActivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel by viewModels <SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickBtn()

        viewModel.signUpResult.observe(this){signUpResult->
            startActivity(
                Intent(this@SignUpActivity,
                MainActivity::class.java)
            )
        }
    }

    private fun clickBtn() {
        with(binding){
            btnSignup.setOnClickListener {
                viewModel.signUp(
                        etId.text.toString(),
                        etPw.text.toString(),
                        etName.text.toString(),
                        etSpecialty.text.toString()
                )
            }
        }
    }
}