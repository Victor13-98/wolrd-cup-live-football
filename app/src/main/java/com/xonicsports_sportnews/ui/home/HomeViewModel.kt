package com.xonicsports_sportnews.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xonicsports_sportnews.dataClasses.FlexTonics
import com.xonicsports_sportnews.repository.Repository
import com.xonicsports_sportnews.utils.Coroutines
import kotlinx.coroutines.Job

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private lateinit var job: Job
    private val _data = MutableLiveData<FlexTonics>()
    val data: LiveData<FlexTonics> get() = _data

    fun getCars(context: Context) {
        job = Coroutines.ioThenMain(
            { repository.getData() }, {
                _data.value = it
            }, context
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}