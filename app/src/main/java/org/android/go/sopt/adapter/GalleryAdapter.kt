package org.android.go.sopt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemGalleryBinding

class GalleryAdapter(
    _itemList:List<Int> = listOf()
):RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){
    private var itemList:List<Int> = _itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GalleryViewHolder(binding)
    }

    class GalleryViewHolder(
        private val binding:ItemGalleryBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun bind(src:Int){
                binding.ivImg.setImageResource(src)
            }
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun setItemList(itemList: List<Int>){
        this.itemList = itemList
        notifyDataSetChanged()
    }
}