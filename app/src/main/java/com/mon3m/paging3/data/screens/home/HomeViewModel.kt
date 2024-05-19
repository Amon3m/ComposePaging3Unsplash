package com.mon3m.paging3.data.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.mon3m.paging3.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel  @Inject constructor(
    repository: Repository
): ViewModel() {
    val getAllImages = repository.getAllImages()



    init {
        Log.d("HomeViewModel", "ViewModel initialized")
        viewModelScope.launch {
            getAllImages.map { pagingData ->
                pagingData.map { data ->
                    Log.d("HomeViewModel", "Loaded data: $data")
                    data
                }
            }.collect {
                Log.d("HomeViewModel", "$it")
            }
        }
    }

}