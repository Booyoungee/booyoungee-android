package com.eoyeongbooyeong.data.datastore

interface BooDataStore {
    var accessToken: String
    var refreshToken: String
    fun clearInfo()
    fun setTokens(accessToken: String, refreshToken: String)
    fun isAlreadyLogin(): Boolean
}