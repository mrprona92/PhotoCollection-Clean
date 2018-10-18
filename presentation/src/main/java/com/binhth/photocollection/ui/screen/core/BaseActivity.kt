package com.binhth.photocollection.ui.screen.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() {
    lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initComponent(savedInstanceState)
    }

    abstract fun initComponent(savedInstanceState: Bundle?)
    abstract fun getLayout(): Int

    open fun addFragment(fragment: Fragment, container: Int, tag: String) {
        supportFragmentManager.beginTransaction().apply {
            add(container, fragment, tag)
            addToBackStack(tag)
            commit()
        }
        title = tag
    }


    open fun replaceFragment(fragment: Fragment, container: Int, tag: String, isAddToBackStack: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            replace(container, fragment, tag)
            if (isAddToBackStack) {
                addToBackStack(tag)
            }
            commit()
        }
        title = tag
    }
}
