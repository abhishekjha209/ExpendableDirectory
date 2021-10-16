package com.abhishekdev.expendabledirectory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnlyOneOpenedAdapter(var context: Context, var itemList: ArrayList<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var currentOpenedParent: Parent? = null

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int): Int {
        return itemList[position].getItemType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CHILD -> ChildViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.children, parent, false)
            )
            else -> ParentViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.header, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            CHILD -> {
                val childViewHolder = (holder as ChildViewHolder)
                childViewHolder.childItem = itemList[position] as Child
                childViewHolder.bind()
            }
            else -> {
                val parentViewHolder = holder as ParentViewHolder
                parentViewHolder.parentItem = itemList[position] as Parent
                parentViewHolder.bind()
            }
        }
    }

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val startPosition = adapterPosition + 1
                val count = parentItem.childItems.size

                if (parentItem.isExpanded) {
                    itemList.removeAll(parentItem.childItems)
                    notifyItemRangeRemoved(startPosition, count)
                    parentItem.isExpanded = false
                    currentOpenedParent = null
                } else {
                    itemList.addAll(startPosition, parentItem.childItems)
                    notifyItemRangeInserted(startPosition, count)
                    parentItem.isExpanded = true

                    if (currentOpenedParent != null) {
                        itemList.removeAll(currentOpenedParent!!.childItems)
                        notifyItemRangeRemoved(
                            itemList.indexOf(currentOpenedParent!!) + 1,
                            currentOpenedParent!!.childItems.size
                        )
                        currentOpenedParent?.isExpanded = false
                        notifyItemChanged(itemList.indexOf(currentOpenedParent!!))
                    }

                    currentOpenedParent = parentItem
                }
                updateViewState()
            }
        }

        lateinit var parentItem: Parent

        private val parentTitle: TextView = itemView.findViewById(R.id.ageTitle)
        private val ivExpanded: ImageView = itemView.findViewById(R.id.isExpanded)

        fun bind() {
            updateViewState()
        }

        private fun updateViewState() {
            parentTitle.text = parentItem.ageGroup

            if (parentItem.isExpanded) {
                ivExpanded.setImageResource(R.drawable.ic_expanded)
            } else {
                ivExpanded.setImageResource(R.drawable.ic_not_expanded)
            }
        }
    }

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val parentPosition = itemList.indexOf(childItem.parent)
                val startPosition = parentPosition + 1
                val count = childItem.parent.childItems.size

                itemList.removeAll(childItem.parent.childItems)
                notifyItemRangeRemoved(startPosition, count)
                childItem.parent.isExpanded = false

                notifyItemChanged(parentPosition)
            }
        }

        lateinit var childItem: Child

        private val childTitle: TextView = itemView.findViewById(R.id.tvName)
        private val childImage: ImageView = itemView.findViewById(R.id.iv_avi)
        private val childStatement: TextView = itemView.findViewById(R.id.tvStatement)
        fun bind() {
            childTitle.text = childItem.name
            childImage.setImageResource(childItem.imageResource)
            childStatement.text = childItem.statement()
        }
    }

}
