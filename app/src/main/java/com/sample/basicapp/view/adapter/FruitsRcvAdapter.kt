package com.sample.basicapp.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.basicapp.R
import com.sample.basicapp.data.model.Fruit
import com.sample.basicapp.databinding.ItemFruitBinding

class FruitsRcvAdapter(
    private var ctx: Activity
) :
    RecyclerView.Adapter<FruitsRcvAdapter.ViewHolder>() {
    private val fruits: MutableList<Fruit> = ArrayList()

    inner class ViewHolder(private var binding: ItemFruitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fruit: Fruit) {
            binding.fruit = fruit


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_fruit,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return fruits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fruits[holder.adapterPosition])
    }

    fun swap(fruits: MutableList<Fruit>?) {

        val diffCallback =
            CrSingleConversationListDiffUtils(
                this.fruits,
                fruits ?: ArrayList()
            )
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.fruits.clear()
        this.fruits.addAll(fruits ?: ArrayList())
        diffResult.dispatchUpdatesTo(this)
    }


}

private class CrSingleConversationListDiffUtils(
    private val oldList: MutableList<Fruit>, private val newList: MutableList<Fruit>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].catId == newList[newItemPosition].catId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].catId == newList[newItemPosition].catId

    }
}

