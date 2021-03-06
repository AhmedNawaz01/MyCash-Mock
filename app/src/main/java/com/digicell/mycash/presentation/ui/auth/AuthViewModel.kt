package com.digicell.mycash.presentation.ui.auth

import android.content.Context
import androidx.lifecycle.*
import com.digicell.mycash.R
import com.digicell.mycash.data.remote.requests.AuthLoginRequest
import com.digicell.mycash.data.remote.requests.AuthRegistrationRequest
import com.digicell.mycash.domain.models.*
import com.digicell.mycash.repository.AuthRepository
import com.digicell.mycash.util.Event
import com.digicell.mycash.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginCredentials = MutableLiveData<LoginCredentials>()

    val loginCredentials: LiveData<LoginCredentials>
        get() = _loginCredentials

    private val _registrationCredentials = MutableLiveData<RegistrationCredentials>()

    val registrationCredentials: LiveData<RegistrationCredentials>
        get() = _registrationCredentials

    private val _loginResult = MutableLiveData<Event<Resource<AuthToken>>>()

    val loginResult: LiveData<Event<Resource<AuthToken>>>
        get() = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.postValue(Event(Resource.loading(null)))
            authRepository.login(
                AuthLoginRequest(
                    email = email,
                    password = password
                )
            ).collect {
                _loginResult.postValue(Event(it))
            }
        }

    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.postValue(Event(Resource.loading(null)))
            authRepository.register(
                AuthRegistrationRequest(
                    email = email,
                    password = password
                )
            ).collect {
                _loginResult.postValue(Event(it))
            }
        }

    }

    fun checkPreviousAuthUser() {
        viewModelScope.launch {
            _loginResult.postValue(Event(Resource.loading(null)))
            authRepository.checkPreviousAuthUser().collect {
                _loginResult.postValue(Event(it))
            }
        }
    }

    fun setLoginFields(email: String?, password: String?) {
        _loginCredentials.value = LoginCredentials(email = email, password = password)
    }

    fun setRegistrationFields(email: String?, password: String?) {
        _registrationCredentials.value = RegistrationCredentials(email = email, password = password)
    }

    fun isValidForLogin(loginEmail: String?, loginPassword: String?): Boolean {
        if (loginEmail.isNullOrEmpty()
            || loginEmail.isNullOrBlank()
            || loginPassword.isNullOrEmpty()
            || loginPassword.isNullOrBlank()
        ) {
            postErrorValue(
                context.getString(R.string.empty_email_or_password)
            )
            return false
        }

        if(loginPassword.length < 5) {
            postErrorValue(
                context.getString(R.string.invalid_password)
            )
            return false
        }

        return true
    }

    fun isValidForRegistration(
        registrationEmail: String?,
        registrationPassword: String?,
        confirmRegistrationPassword: String?
    ): Boolean {
        if (registrationEmail.isNullOrEmpty()
            || registrationEmail.isNullOrBlank()
            || registrationPassword.isNullOrEmpty()
            || registrationPassword.isNullOrBlank()
            || confirmRegistrationPassword.isNullOrEmpty()
            || confirmRegistrationPassword.isNullOrBlank()
        ) {
            postErrorValue(
                context.getString(R.string.empty_email_or_password)
            )
            return false
        }

        if(registrationPassword.length < 5 || confirmRegistrationPassword.length < 5) {
            postErrorValue(
                context.getString(R.string.invalid_password)
            )
            return false
        }

        if(registrationPassword != confirmRegistrationPassword) {
            postErrorValue(
                context.getString(R.string.passwords_do_not_match)
            )
            return false
        }
        
        return true
    }

    private fun postErrorValue(errorMessage: String) {
        _loginResult.postValue(
            Event(
                Resource.error(
                    errorMessage,
                    AuthToken(
                        errorResponse = StateResponse(
                            message = errorMessage,
                            errorResponseType = ResponseType.Dialog
                        )
                    )
                )
            )
        )
    }

}