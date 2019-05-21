package com.vitovalov.taxilocator.internal

interface MvpPresenter<V : MvpView> {
    fun onViewReady()
    fun onStop()
}