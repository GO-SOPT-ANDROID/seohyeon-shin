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
    private var id:String?=null
    private var pw:String?=null
    private var name:String?=null
    private var spe:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        setSignUp()
        clickLogin()
    }

    private fun setData(){
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                id = result.data?.getStringExtra("ID")
                pw = result.data?.getStringExtra("PW")
                name=result.data?.getStringExtra("NAME")
                spe=result.data?.getStringExtra("SPE")
                Log.e("data 값", id + pw + name+spe)
            }
        }
    }

    private fun clickLogin(){
        with(binding){
            btnLogin.setOnClickListener {
                if (id.equals(etId.text.toString()) && pw.equals(etPw.text.toString())){
                    Toast.makeText(this@LoginActivity, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    Intent(this@LoginActivity,ProfileActivity::class.java).apply{
                        putExtra("name",name)
                        putExtra("spe",spe)
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