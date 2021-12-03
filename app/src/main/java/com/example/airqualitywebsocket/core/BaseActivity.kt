package com.example.airqualitywebsocket.core

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<Vb : ViewDataBinding> : AppCompatActivity() {
    protected val binding by lazy {
        DataBindingUtil.setContentView<Vb>(this, layoutId())
    }

    @LayoutRes
    abstract fun layoutId(): Int
}