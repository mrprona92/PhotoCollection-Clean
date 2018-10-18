package com.binhth.photocollection.ui.screen.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.binhth.photocollection.utils.DialogUtils

abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {
    abstract val bindingVariable: Int

    lateinit var binding: ViewBinding

    abstract val viewModel: ViewModel

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        initContent(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setLifecycleOwner(this@BaseFragment)
            setVariable(bindingVariable, viewModel)
            root.isClickable = true
            executePendingBindings()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            errorMessage.observe(this@BaseFragment, Observer {
                if (!it.isNullOrEmpty()) {
                    showToast(it)
                    errorMessage.value = ""
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.errorMessage.removeObservers(this)
        viewModel.onActivityDestroyed()
    }

    abstract fun initContent(viewBinding: ViewBinding)

    open fun showToast(message: String?) {
        DialogUtils.showToast(activity, message)
    }
}