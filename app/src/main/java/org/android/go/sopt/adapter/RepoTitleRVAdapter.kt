package org.android.go.sopt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemTitleBinding

class RepoTitleRVAdapter(context: Context) : RecyclerView.Adapter<RepoTitleRVAdapter.RepoTitleViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoTitleViewHolder {
        return  RepoTitleViewHolder(ItemTitleBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: RepoTitleViewHolder, position: Int) {

    }

    override fun getItemCount(): Int =1

    class RepoTitleViewHolder(private val binding:ItemTitleBinding) : RecyclerView.ViewHolder(binding.root)
}