package com.dapascript.jakmalltest.data.repository

import com.dapascript.jakmalltest.data.repository.model.ChildItem
import com.dapascript.jakmalltest.data.repository.model.ParentItem
import com.dapascript.jakmalltest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getParentItem(): Flow<Resource<List<ParentItem>>>
    fun getChildItem(): Flow<List<ChildItem>>
}