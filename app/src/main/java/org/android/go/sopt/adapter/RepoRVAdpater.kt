package org.android.go.sopt.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.data.model.ResponseUserListDto.Data
import org.android.go.sopt.databinding.ItemRepoBinding

class RepoRVAdapter(private val context: Context,_userList:List<Data>) : ListAdapter<Data, RepoRVAdapter.RepoViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }
    private val userList:List<Data> = _userList

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(userList[position])
        Log.e("hyeon",userList.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(inflater,parent,false)
        return RepoViewHolder(binding,context)
    }
    override fun getItemCount(): Int {
        return userList.size
    }

    companion object{
        val diffUtil = object:DiffUtil.ItemCallback<Data>(){
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem:Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }

   class RepoViewHolder(private val binding:ItemRepoBinding, private val context:Context) : RecyclerView.ViewHolder(binding.root){
       fun bind(item:Data){
           with(binding) {
               tvRepoTitle.text = "${item.first_name}  ${item.last_name}"
               tvRepoName.text = item.email
               Glide.with(context).load(item.avatar).into(ivProfile);
           }
       }
    }
}