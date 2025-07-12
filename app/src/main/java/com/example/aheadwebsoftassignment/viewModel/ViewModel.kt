package com.example.aheadwebsoftassignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aheadwebsoftassignment.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: ItemRepository) : ViewModel() {

    val itemLiveData get() = repository.itemLiveData

    fun getItems() {
        viewModelScope.launch {
            repository.getItems()
        }
    }
}