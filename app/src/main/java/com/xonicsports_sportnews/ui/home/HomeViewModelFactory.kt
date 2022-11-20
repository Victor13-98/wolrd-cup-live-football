package com.xonicsports_sportnews.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xonicsports_sportnews.repository.Repository

class HomeViewModelFactory(private val dataRepository: Repository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(dataRepository) as T
    }
}