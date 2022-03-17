package com.digicell.mycash.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import com.digicell.mycash.databinding.ActivityMainBinding
import com.digicell.mycash.presentation.ui.BaseActivity
import com.digicell.mycash.presentation.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logout.setOnClickListener {
            sessionManager.logout()
        }

        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.cachedToken.observe(this) { authToken ->
            if (authToken?.token == null) {
                Timber.d("NAVIGATING TO AUTH ACTIVITY")
                navAuthActivity()
            }
        }
    }

    private fun navAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun displayProgressBar(loading: Boolean) {
        //ignore
    }

}