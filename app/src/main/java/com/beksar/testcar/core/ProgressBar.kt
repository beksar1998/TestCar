package com.beksar.testcar.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beksar.testcar.R

/**
 * Progress для всех
 * для show использовать childFragmentManager
 */
class ProgressBar : BaseAlertDialog() {

    override fun isCancelableDialog() = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_progress_bar, container, false)
    }
}