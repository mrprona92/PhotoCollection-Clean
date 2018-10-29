package com.binhth.photocollection.ui.screen.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.binhth.photocollection.ui.screen.MainActivity
import com.binhth.photocollection.utils.DialogUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tbruyelle.rxpermissions2.RxPermissions

abstract class BaseBottomFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : BottomSheetDialogFragment() {
    abstract val bindingVariable: Int

    lateinit var binding: ViewBinding

    abstract val viewModel: ViewModel

    @get:LayoutRes
    abstract val layoutId: Int

    lateinit var mainActivity: MainActivity

    lateinit var rxPermissions: RxPermissions


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (activity is MainActivity) {
            mainActivity = activity as MainActivity
            activity?.let {
                rxPermissions = RxPermissions(it)
            }
        }
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        initContent(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setLifecycleOwner(this@BaseBottomFragment)
            setVariable(bindingVariable, viewModel)
            root.isClickable = true
            executePendingBindings()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            errorMessage.observe(this@BaseBottomFragment, Observer {
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

    open fun onBackPressed(): Boolean {
        return false
    }
}