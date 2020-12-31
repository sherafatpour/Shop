package net.sherafatpour.shop.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import net.sherafatpour.shop.R
import net.sherafatpour.shop.databinding.PostItemBinding
import net.sherafatpour.shop.model.PostModel

class PostAdapter(private val list:PostModel) :RecyclerView.Adapter<PostAdapter.ViewHolderI> (){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderI {
        return ViewHolderI(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.post_item,parent,false))
    }


    override fun getItemCount(): Int {
      return list.size

    }

    class ViewHolderI(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onBindViewHolder(holder: ViewHolderI, position: Int) {

        val data = list[position]

        holder.binding.item = data
    }


}


