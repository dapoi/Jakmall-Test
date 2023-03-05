package com.dapascript.jakmalltest.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dapascript.jakmalltest.data.repository.ItemRepository
import com.dapascript.jakmalltest.data.repository.model.ChildItem
import com.dapascript.jakmalltest.data.repository.model.ParentItem
import com.dapascript.jakmalltest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {

    fun getParentItem(): LiveData<Resource<List<ParentItem>>> =
        itemRepository.getParentItem().asLiveData()

    fun getChildItem(): LiveData<List<ChildItem>> = itemRepository.getChildItem().asLiveData()
}