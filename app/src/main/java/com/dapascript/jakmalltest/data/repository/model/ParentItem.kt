package com.dapascript.jakmalltest.data.repository.model

data class ParentItem(
    val parentTitle: String? = null,
    val childList: MutableList<ChildItem> = ArrayList(),
)
