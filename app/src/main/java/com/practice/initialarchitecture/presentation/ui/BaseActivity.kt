package com.practice.initialarchitecture.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import com.practice.initialarchitecture.session.SessionManager
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    abstract fun displayProgressBar(loading: Boolean)

}