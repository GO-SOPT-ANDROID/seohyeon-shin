package org.android.go.sopt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setProfile()
    }

    private fun setProfile() {
        with(binding) {
            tvName.text = intent.getStringExtra("name")
            tvSpecialty.text = intent.getStringExtra("spe")
        }
    }
}