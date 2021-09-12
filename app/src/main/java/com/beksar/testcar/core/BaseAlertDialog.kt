package com.beksar.testcar.core

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.beksar.testcar.R

abstract class BaseAlertDialog : DialogFragment() {

    /**
     * Transparent level for background dialog
     * */
    private var dimAmount: Float = 0.6F

    abstract fun isCancelableDialog(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val style = STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style, theme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.setCanceledOnTouchOutside(isCancelableDialog())
    }

    override fun onStart() {
        super.onStart()

        val window: Window? = dialog?.window
        val params: WindowManager.LayoutParams? = window?.attributes
        params?.dimAmount = dimAmount
        window?.attributes = params
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}