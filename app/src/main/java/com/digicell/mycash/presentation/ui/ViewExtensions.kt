package com.digicell.mycash.presentation.ui

import android.content.Context
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.digicell.mycash.R

fun Context.displayToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

fun Context.displayErrorDialog(errorMessage: String?) {
    MaterialDialog(this)
        .show {
            title(R.string.text_error)
            message(text = errorMessage)
            positiveButton(R.string.text_ok)
        }
}