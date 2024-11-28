package com.mtc.datastore.datastore

import kotlinx.coroutines.flow.Flow
import tech.thdev.useful.encrypted.data.store.preferences.ksp.annotations.UsefulPreferences
import tech.thdev.useful.encrypted.data.store.preferences.ksp.annotations.value.GetValue
import tech.thdev.useful.encrypted.data.store.preferences.ksp.annotations.value.SetValue

@UsefulPreferences
interface SecurityDataStore {

    @GetValue(KEY_USER_ID)
    fun flowUserId(): Flow<Long>

    @SetValue(KEY_USER_ID)
    suspend fun setUserId(long: Long)

    @GetValue(KEY_EXIST_ACCOUNT)
    fun flowExistAccount(): Flow<Boolean>

    @SetValue(KEY_EXIST_ACCOUNT)
    suspend fun setExistAccount(boolean: Boolean)

    companion object {
        const val KEY_USER_ID = "key-user-id"
        const val KEY_EXIST_ACCOUNT = "key-exist-account"
    }
}
