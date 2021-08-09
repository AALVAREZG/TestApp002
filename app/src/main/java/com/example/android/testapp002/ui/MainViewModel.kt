package com.example.android.testapp002.ui

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.android.testapp002.network.*
//import com.example.android.testapp002.repository.MainRepository
import kotlinx.coroutines.*
import retrofit2.http.Body
import timber.log.Timber
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class MainViewModel: ViewModel() {


    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _body = MutableLiveData<String>()
    val body: LiveData<String>
        get() = _body
    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private lateinit var _playlist: NetworkVideoContainer
    private var viewModelJob = Job()


    /*
    Dispatchers: Dispatchers help coroutines in deciding the thread on which the work has to be done.
    *There are majorly three types of Dispatchers which are as IO, Default, and Main.
    - IO dispatcher is used to do the network and disk-related work.
    - Default is used to do the CPU intensive work.
    - Main is the UI thread of Android.
    */

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO )

    init {
        _score.value = 0
        _word.value = "Hell0"
        _body.value = "VOID"
        Timber.i("LOG -  MainViewModel initialized")

    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(2)
        var formatedBody: String = "n/d"
        coroutineScope.launch{
            try {
                formatedBody = loadDataSuspend()!!
                Timber.i("LOG - RESPONSE:")
                Timber.i("${formatedBody}")
                _body.postValue(formatedBody)
                /**
                 * While using the Main thread to change the data, you should use the setValue method
                 * of the MutableLiveData class and while using the background thread to change the
                 * LiveData, you should use the postValue method of the MutableLiveData class.
                 * https://blog.mindorks.com/livedata-setvalue-vs-postvalue-in-android
                 */

            }catch (e: Exception) {
                Timber.i("LOG - error in API SERVICE:")
                Timber.i("$e")
            }

        }
         // I NEED TO RETURN FORMATED BODY RESULT, LAUNCH RETURN NOTHING
    }

    fun setOtherValues(data: String) {
        _body.value = data
    }

    suspend fun loadDataSuspend(): String? {
        return Network.apiService.getPlaylist().body()?.asUsableData()
    }


    override fun onCleared() {
        super.onCleared()
        Timber.i("LOG - MainViewModel destroyed")
    }

}

