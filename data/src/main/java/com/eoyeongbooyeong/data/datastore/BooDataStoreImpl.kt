package com.eoyeongbooyeong.data.datastore

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class BooDataStoreImpl @Inject constructor(
    private val dataStore: SharedPreferences,
) : BooDataStore {
    override var accessToken: String
        get() = dataStore.getString(ACCESS_TOKEN, "") ?: ""
        set(value) = dataStore.edit { putString(ACCESS_TOKEN, value) }

    override var refreshToken: String
        get() = dataStore.getString(REFRESH_TOKEN, "") ?: ""
        set(value) = dataStore.edit { putString(REFRESH_TOKEN, value) }

    override fun clearInfo() {
        dataStore.edit().clear().commit()
    }

    override fun setTokens(accessToken: String, refreshToken: String) {
        dataStore.edit {
            putString(ACCESS_TOKEN, accessToken)
            putString(REFRESH_TOKEN, refreshToken)
        }
    }

    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
    }
}