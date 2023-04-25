package org.android.go.sopt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
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
        initView()
        setBottomNav()

    }

    private fun initView(){
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            changeFragment(HomeFragment())
        }
    }

    private fun setBottomNav() {
        binding.bnvMain.run {
            setOnItemReselectedListener {
                scrollTop()
            }
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
        }
    }

    private fun scrollTop(){
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment is HomeFragment) {
            val scrollView = currentFragment.view?.findViewById<RecyclerView>(R.id.rv_home)
            scrollView?.scrollToPosition(0)
        }
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main,fragment)
            .commit()
    }
}