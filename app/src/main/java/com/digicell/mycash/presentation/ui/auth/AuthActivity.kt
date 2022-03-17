package com.digicell.mycash.presentation.ui.auth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.digicell.mycash.R
import com.digicell.mycash.databinding.ActivityAuthBinding
import com.digicell.mycash.domain.models.ResponseType
import com.digicell.mycash.domain.models.StateResponse
import com.digicell.mycash.presentation.ui.BaseActivity
import com.digicell.mycash.presentation.ui.displayErrorDialog
import com.digicell.mycash.presentation.ui.displayToast
import com.digicell.mycash.presentation.ui.main.MainActivity
import com.digicell.mycash.util.Status
import com.digicell.mycash.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AuthActivity : BaseActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityAuthBinding

    private val authViewModel: AuthViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.auth_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        Utils.hasInternetConnection(this)
        subscribeObservers()
        checkPreviousAuthUser()
    }

    private fun subscribeObservers() {
        authViewModel.loginResult.observe(this) { event ->
            event.getContentIfNotHandled()?.let { result ->
                result.let {
                    when (result.status) {

                        Status.LOADING -> {
                            displayProgressBar(true)
                            Timber.d("loading...")
                        }

                        Status.SUCCESS -> {
                            displayProgressBar(false)
                            result.data?.let { authToken ->
                                sessionManager.login(authToken)
                            }
                            Timber.d("auth token: ${result.data!!.token}")
                        }

                        Status.ERROR -> {
                            displayProgressBar(false)
                            Timber.e("error...")
                            Timber.e(result.message)
                            result.data?.let {
                                it.errorResponse?.let { stateResponse ->
                                    handleErrorResponse(stateResponse)
                                }
                            }
                        }

                    }
                }
            }

        }

        sessionManager.cachedToken.observe(this) { authToken ->
            if (authToken?.token != null) {
                Timber.d("NAVIGATING TO MAIN ACTIVITY")
                navMainActivity()
            }
        }
    }

    private fun navMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkPreviousAuthUser() {
        authViewModel.checkPreviousAuthUser()
    }

    private fun handleErrorResponse(response: StateResponse) {
        when(response.errorResponseType) {

            is ResponseType.Toast -> {
                Timber.d("handleErrorResponse Toast type: ${response.message}")
                response.message?.let {
                    displayToast(it)
                }
            }

            is ResponseType.Dialog -> {
                Timber.d("handleErrorResponse Dialog type: ${response.message}")
                response.message?.let {
                    displayErrorDialog(it)
                }
            }

            is ResponseType.None -> {
                Timber.i("handleErrorResponse: ${response.message}")
            }

        }
    }

    override fun displayProgressBar(loading: Boolean) {
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

}