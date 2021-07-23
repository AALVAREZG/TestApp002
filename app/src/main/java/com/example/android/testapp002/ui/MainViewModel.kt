package com.example.android.testapp002.ui

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel: ViewModel() {


    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word


    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    init {
        _score.value = 0
        _word.value = "H·ll0"
        Timber.i("LOG -  MainViewModel initialized")
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)

    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("LOG - MainViewModel destroyed")
    }

}

