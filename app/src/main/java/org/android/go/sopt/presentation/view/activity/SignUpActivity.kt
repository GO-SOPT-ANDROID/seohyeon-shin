package org.android.go.sopt.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import org.android.go.sopt.R
import org.android.go.sopt.util.base.BaseActivity
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.presentation.viewmodel.SignUpViewModel
import org.android.go.sopt.util.verifyId
import org.android.go.sopt.util.verifyPw

class SignUpActivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        watchText()
        checkEnableBtn()
        clickBtn()
        viewModel.signUpResult.observe(this) { signUpResult ->
            startActivity(
                Intent(
                    this@SignUpActivity,
                    MainActivity::class.java
                )
            )
        }
    }

    private fun checkEnableBtn() {
        viewModel.name.value = binding.etName.text.toString()
        viewModel.specialty.value = binding.etSpecialty.text.toString()

        viewModel.enableBtn.observe(this) {enable ->
            with(binding){
                btnSignup.isEnabled = enable
            }
        }
    }

    private fun watchText() {
        with(binding) {
            etId.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s != null && !verifyId(s.toString())) {
                        layoutEtId.error = "아이디 영문, 숫자가 포함 6~10글자 이내"
                    } else {
                        layoutEtId.error = null
                    }
                    viewModel.id.value =s.toString()
                }
            })
            etPw.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s != null && !verifyPw(s.toString())) {
                        layoutEtPw.error = "비밀번호 영문, 숫자, 특수문자가 포함되어야 하고 6~12글자 이내"
                    } else {
                        layoutEtPw.error = null
                    }
                    viewModel.pw.value =s.toString()
                }
            })
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