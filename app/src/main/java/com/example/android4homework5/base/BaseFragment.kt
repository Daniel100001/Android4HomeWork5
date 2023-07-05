package com.example.android4homework5.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(@LayoutRes layoutId: Int) :
    Fragment(layoutId) {

    abstract val binding: VB
    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUpObserves()
        setUpRequests()
        setUpListeners()
        bottomNavigationSelected()
//        isNetworkAvailable()
    }

    protected open fun initialize() {}

    protected open fun setUpObserves() {}

    protected open fun setUpRequests() {}

    protected open fun setUpListeners() {}

    protected open fun bottomNavigationSelected() {}

//    protected open fun isNetworkAvailable() {}

}
