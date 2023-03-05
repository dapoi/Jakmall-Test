package com.dapascript.jakmalltest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.dapascript.jakmalltest.data.repository.model.ChildItem
import com.dapascript.jakmalltest.databinding.ItemListChildBinding

class ChildAdapter(
    private val childList: MutableList<ChildItem>,
    private val onClick: ParentAdapter.OnItemClickListener?
) :
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(
        binding: ItemListChildBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClick?.onItemClicked(childList[position])
                }
            }
        }

        var childTitle = binding.tvItem
        var divider = binding.view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(
            ItemListChildBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = childList.size

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val childItem = childList[position]
        holder.apply {
            childTitle.text = childItem.childTitle
            if (position == childList.size - 1) {
                divider.isVisible = false
            }
        }
    }
}
