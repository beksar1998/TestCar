package com.beksar.testcar.core

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.beksar.testcar.R

abstract class BaseActivity : AppCompatActivity() {

    private val progressbar = ProgressBar()

    protected val statusObserver = Observer<Status> {
        when (it) {
            Status.SHOW_LOADING -> {
                progressbar.show(supportFragmentManager)
            }
            Status.HIDE_LOADING -> {
                progressbar.hide()
            }
            Status.ERROR -> {
                progressbar.hide()
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}