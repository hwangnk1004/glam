package com.example.cupist.allinterface

interface GetDataCallback<T> {
    fun onSuccess(data: T?)
    fun onFail()
}