package org.android.go.sopt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var mId:String?=null
    private var mPw:String?=null
    private var mName:String?=null
    private var mSpe:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                mId = result.data?.getStringExtra("ID")
                mPw = result.data?.getStringExtra("PW")
                mName=result.data?.getStringExtra("NAME")
                mSpe=result.data?.getStringExtra("SPE")
                Log.e("data 값", mId + mPw + mName+mSpe)
            }
        }

        setSignUp()
        clickLogin()
    }
    private fun clickLogin(){
        with(binding){
            btnLogin.setOnClickListener {
                if (mId.equals(etId.text.toString()) && mPw.equals(etPw.text.toString())){
                    Toast.makeText(this@LoginActivity, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    Intent(this@LoginActivity,ProfileActivity::class.java).apply{
                        putExtra("name",mName)
                        putExtra("spe",mSpe)
                        startActivity(this)
                    }
                }
            }
        }
    }
    private fun setSignUp(){
        with(binding){
            btnSignup.setOnClickListener {
                resultLauncher.launch(Intent(this@LoginActivity,SignUpActivity::class.java))
            }
        }
    }
}