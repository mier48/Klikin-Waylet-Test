package com.albertomier.klikinwaylettest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.klikinwaylettest.data.network.ApiResponseStatus
import com.albertomier.klikinwaylettest.domain.GetCategory
import com.albertomier.klikinwaylettest.domain.GetFilteredShopList
import com.albertomier.klikinwaylettest.domain.GetShop
import com.albertomier.klikinwaylettest.domain.model.Shop
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopListViewModel @Inject constructor(
    private val getShop: GetShop,
    private val getFilteredShopList: GetFilteredShopList,
    private val getCategory: GetCategory
) : ViewModel() {

    private val _shopList = MutableLiveData<List<Shop>>()
    val shopList: LiveData<List<Shop>> get() = _shopList

    private val _categoryList = MutableLiveData<List<String>>()
    val categoryList: LiveData<List<String>> get() = _categoryList

    private val _status = MutableLiveData<ApiResponseStatus<Any>>()
    val status: LiveData<ApiResponseStatus<Any>> get() = _status

//    init {
//        getShopList()
//    }

    fun getShopList() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(getShop())
            getCategoryList()
        }
    }

    private fun getCategoryList() {
        viewModelScope.launch {
            handleResponseStatusString(getCategory())
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatus(responseStatus: ApiResponseStatus<List<Shop>>) {
        if (responseStatus is ApiResponseStatus.Success) {
            _shopList.value = responseStatus.data
        }

        _status.value = responseStatus as ApiResponseStatus<Any>
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusString(responseStatus: ApiResponseStatus<List<String>>) {
        if (responseStatus is ApiResponseStatus.Success) {
            _categoryList.value = responseStatus.data
        }
    }

    fun filterList(category: String) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(getFilteredShopList(category))
            getCategoryList()
        }
    }
}