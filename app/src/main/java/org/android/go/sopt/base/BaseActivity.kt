package org.android.go.sopt.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity <T:ViewDataBinding>(
    @LayoutRes private val layoutRes : Int) :AppCompatActivity(){
    private lateinit var binding:T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,layoutRes)
        binding.lifecycleOwner = this // LiveData 사용을 위해 지정
    }
}