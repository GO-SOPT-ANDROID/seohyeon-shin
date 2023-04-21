package org.android.go.sopt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.fragment.GalleryFragment
import org.android.go.sopt.fragment.HomeFragment
import org.android.go.sopt.fragment.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNav()
    }

    private fun setBottomNav() {
        binding.bnvMain.run {
            setOnItemSelectedListener {
                changeFragment(
                    when (it.itemId) {
                        R.id.menu_home -> HomeFragment()
                        R.id.menu_gallery -> GalleryFragment()
                        else -> SearchFragment()
                    }
                )
                true
            }
            selectedItemId = R.id.menu_home
        }
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main,fragment)
            .commit()
    }
}