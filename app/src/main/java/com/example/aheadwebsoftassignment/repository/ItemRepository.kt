package com.example.aheadwebsoftassignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aheadwebsoftassignment.data.ItemModel
import com.example.aheadwebsoftassignment.network.ServerAPI
import org.json.JSONObject
import javax.inject.Inject

class ItemRepository @Inject constructor(private val serverAPI: ServerAPI) {
    private val _itemLiveData = MutableLiveData<ItemModel>()
    val itemLiveData : LiveData<ItemModel> get() = _itemLiveData

    suspend fun getItems(){
        try {
            val response = serverAPI.getItems()
            if (response.isSuccessful && response.body() != null){
                _itemLiveData.postValue(response.body())
            }
        }catch (e: Exception){
            Log.d("Error api",e.toString())
        }


    }
}