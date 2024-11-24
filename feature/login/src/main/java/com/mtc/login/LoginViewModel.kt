package com.mtc.login

import androidx.lifecycle.viewModelScope
import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {

    val existAccount = false

    fun getAccountExist() {
        postSideEffect(
            LoginSideEffect.Loading,
        )
        viewModelScope.launch {
            delay(1550L)
            runCatching { existAccount }
                .onSuccess {
                    intent {
                        copy(existAccount = it)
                    }
                    postSideEffect(LoginSideEffect.Success)
                }.onFailure {
                    postSideEffect(LoginSideEffect.Failure)
                }

        }
    }
}
