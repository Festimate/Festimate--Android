package com.mtc.datastore.datastore

import kotlinx.coroutines.flow.Flow
import tech.thdev.useful.encrypted.data.store.preferences.ksp.annotations.UsefulPreferences
import tech.thdev.useful.encrypted.data.store.preferences.ksp.annotations.value.GetValue
import tech.thdev.useful.encrypted.data.store.preferences.ksp.annotations.value.SetValue

@UsefulPreferences
interface SecurityDataStore {
    @GetValue(KEY_EXIST_ACCOUNT)
    fun flowExistAccount(): Flow<Boolean>

    @SetValue(KEY_EXIST_ACCOUNT)
    suspend fun setExistAccount(boolean: Boolean)

    companion object {
        const val KEY_EXIST_ACCOUNT = "key-exist-account"
    }
}
