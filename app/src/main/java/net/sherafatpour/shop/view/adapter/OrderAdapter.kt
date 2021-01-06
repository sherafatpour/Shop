package net.sherafatpour.shop.view.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import net.sherafatpour.shop.R
import net.sherafatpour.shop.databinding.OrderItemBinding
import net.sherafatpour.shop.databinding.PostItemBinding
import net.sherafatpour.shop.model.orders.OrderModel
import net.sherafatpour.shop.model.post.PostModel

class OrderAdapter(private val list: OrderModel, private val itemClick: ItemsClick) :
    RecyclerView.Adapter<OrderAdapter.ViewHolderI>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderI {
        return ViewHolderI(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.order_item,
                parent,
                false
            )
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolderI, position: Int) {

        val data = list[position]

        holder.binding.data = data
     //   holder.binding.price.text = StringBuilder(data.price + " تومان")

        if (data.status == "1"){
            holder.binding.textSuccess.text = "پرداخت موفق"
            holder.binding.textSuccess.setBackgroundResource(R.color.green_600)
        }else{

            holder.binding.textSuccess.text = "پرداخت ناموفق"
            holder.binding.textSuccess.setBackgroundResource(R.color.red_A400)
        }

        holder.itemView.setOnClickListener {
          //  itemClick.itemId(data.id)

        }

    }


    override fun getItemCount(): Int {
        return list.size

    }

    interface ItemsClick {
        fun itemId(id: String)
    }

    class ViewHolderI(val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root)


}


