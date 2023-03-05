package com.dapascript.jakmalltest.data.repository

import com.dapascript.jakmalltest.data.repository.model.ChildItem
import com.dapascript.jakmalltest.data.repository.model.ParentItem
import com.dapascript.jakmalltest.data.source.service.ApiService
import com.dapascript.jakmalltest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ItemRepository {
    override fun getParentItem(): Flow<Resource<List<ParentItem>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getParentItem().categories
                val parentList = response.map {
                    ParentItem(
                        parentTitle = it
                    )
                }
                emit(Resource.Success(parentList))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    override fun getChildItem(): Flow<List<ChildItem>> {
        return flow {
            val response = apiService.getChildItem().jokes
            val childList = response.map {
                ChildItem(childTitle = it.joke)
            }
            emit(childList)
        }
    }
}