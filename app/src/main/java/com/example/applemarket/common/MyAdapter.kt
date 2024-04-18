package com.example.applemarket.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.R
import com.example.applemarket.data.DummyData
import com.example.applemarket.databinding.ItemRecyclerviewBinding

class MyAdapter(private val items: MutableList<DummyData>): RecyclerView.Adapter<MyAdapter.Holder>() {

    interface ItemClick {
        fun onClick(viwe: View, position: Int)
    }
    var itemClick: ItemClick? = null

    interface LongClick {
        fun longClick(position: Int)
    }
    var longClick: LongClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnLongClickListener {
            longClick?.longClick(position)
            false
        }

        holder.heart.setOnClickListener {
            heartClick(position)
        }


        holder.image.setImageResource(items[position].image)
        holder.image.clipToOutline = true
        holder.product.setText(items[position].product)
        holder.price.text = DecimalFormat.decimalFormat(items[position].price)
        holder.address.text = items[position].address
        holder.chat.text = items[position].chat.toString()
        holder.like.text = items[position].like.toString()

        if (items[position].check) {
            holder.heart.setImageResource(R.drawable.ic_favorite)
        } else {
            holder.heart.setImageResource(R.drawable.ic_favorite_border)

        }
    }

    fun heartClick(position: Int) {
        items[position].check = !items[position].check

        if (items[position].check) {
            items[position].like ++
        } else {
            items[position].like --
        }
        notifyItemChanged(position)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, itemCount - position)
    }

    inner class Holder(private val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageView
        val product = binding.product
        val price = binding.price
        val address = binding.address
        var like = binding.like
        var chat = binding.chat
        var heart = binding.heartIcon
    }

}