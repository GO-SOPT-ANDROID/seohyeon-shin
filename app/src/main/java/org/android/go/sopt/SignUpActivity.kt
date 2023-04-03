package org.android.go.sopt

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignupBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSignUp()
    }

    private fun setSignUp(){
        with(binding){
            btnSignup.setOnClickListener {
               Intent(this@SignUpActivity,LoginActivity::class.java).apply{
                   if (etId.length()>=6 && etPw.length()>=8){
                       putExtra("ID",etId.text.toString())
                       putExtra("PW",etPw.text.toString())
                       putExtra("NAME",etName.text.toString())
                       putExtra("SPE",etSpecialty.text.toString())
                       setResult(RESULT_OK,this)
                       if(!isFinishing) finish()
                       Snackbar.make(
                           binding.root,
                           "회원가입이 완료되었습니다.",
                           Snackbar.LENGTH_SHORT
                       ).show()
                   }
                }
            }
        }
    }
}