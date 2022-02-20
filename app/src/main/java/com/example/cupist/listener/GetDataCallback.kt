package com.example.cupist.listener

interface GetDataCallback<T> {
    fun onSuccess(data: T?)
    fun onFail()
}