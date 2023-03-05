package com.dapascript.jakmalltest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dapascript.jakmalltest.data.repository.model.ChildItem
import com.dapascript.jakmalltest.data.repository.model.ParentItem
import com.dapascript.jakmalltest.databinding.ItemListParentBinding

class ParentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var parentItems = ArrayList<ParentItem>()
    private var childItems = ArrayList<ChildItem>()
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun parentList(list: List<ParentItem>) {
        parentItems.clear()
        parentItems.addAll(list)
        notifyDataSetChanged()
    }

    fun childList(list: List<ChildItem>) {
        childItems.clear()
        childItems.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ParentViewHolder(
            ItemListParentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = parentItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var isExpanded = false
        val parentItem = parentItems[position]
        val parentViewHolder = holder as ParentViewHolder
        parentViewHolder.apply {
            parentTitle.text = parentItem.parentTitle
            rvChild.apply {
                val onClick = onItemClickListener
                adapter = ChildAdapter(childItems, onClick)
                layoutManager = LinearLayoutManager(parentViewHolder.itemView.context)
            }

            btnSelectedItemToTop.setOnClickListener {
                val swapItem = parentItems.removeAt(position)
                parentItems.add(0, swapItem)
                notifyDataSetChanged()
            }

            btnAddItem.setOnClickListener {
                val newItem = ChildItem("New Item ${childItems.size + 1}")
                childItems.add(newItem)
                notifyItemChanged(position, newItem)
            }

            parentLayout.setOnClickListener {
                if (isExpanded) {
                    childLayout.isVisible = false
                    isExpanded = false
                } else {
                    childLayout.isVisible = true
                    isExpanded = true
                }
            }
        }
    }

    inner class ParentViewHolder(
        binding: ItemListParentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        var parentTitle = binding.tvParent
        var parentLayout = binding.parentLayout
        var childLayout = binding.childLayout
        var btnSelectedItemToTop = binding.btnToTop
        var btnAddItem = binding.btnAddItem
        var rvChild = binding.rvChild
    }

    interface OnItemClickListener {
        fun onItemClicked(data: ChildItem)
    }
}