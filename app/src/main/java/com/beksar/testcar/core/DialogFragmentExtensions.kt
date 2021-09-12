package com.beksar.testcar.core

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.util.*

fun DialogFragment?.show(manager: FragmentManager) {
    if (this?.isAdded == false) {
        try{
            this?.show(
                manager,
                this::class.java.canonicalName
            )
        }catch (e:Exception){}

    } else {
        return
    }
}

fun DialogFragment?.hide() {
    if (this?.isAdded == true) {
        this?.dismiss()
    } else {
        return
    }
}