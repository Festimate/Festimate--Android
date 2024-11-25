package com.mtc.login

import androidx.lifecycle.viewModelScope
import com.mtc.datastore.datastore.SecurityDataStore
import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore: SecurityDataStore,
) : BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {

    // 자동로그인 설정
//    init {
//        setAccountExist()
//    }
//    fun setAccountExist() {
//        viewModelScope.launch {
//            dataStore.setExistAccount(false)
//        }
//    }

    fun getAccountExist() {
        postSideEffect(
            LoginSideEffect.Loading,
        )
        viewModelScope.launch {
            delay(1550L)
            runCatching { dataStore.flowExistAccount().first() }
                .onSuccess {
                    if (it) {
                        intent {
                            copy(existAccount = it)
                        }
                        postSideEffect(LoginSideEffect.Success)
                    } else {
                        postSideEffect(LoginSideEffect.Failure)
                    }
                }.onFailure {
                    Timber.tag("ExistAccount").d(it)
                }
        }
    }
}
