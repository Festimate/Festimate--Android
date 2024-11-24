package com.mtc.signup

import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : BaseViewModel<SignUpState, SignUpSideEffect>(SignUpState()) {

    fun signUp() {
        postSideEffect(
            SignUpSideEffect.Success,
        )
    }
}
