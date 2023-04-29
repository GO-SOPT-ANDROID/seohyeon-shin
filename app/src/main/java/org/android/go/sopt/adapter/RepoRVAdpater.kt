package org.android.go.sopt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.Repository
import org.android.go.sopt.databinding.ItemRepoBinding

class RepoRVAdapter(context: Context) : ListAdapter<Repository, RepoRVAdapter.RepoViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }

    private val repoList:List<Repository> = listOf(
        Repository(1,"Repository1","name1"),
        Repository(2,"Repository2","name2"),
        Repository(3,"Repository3","name3"),
        Repository(4,"Repository4","name4"),
        Repository(5,"Repository5","name5"),
        Repository(6,"Repository6","name6"),
        Repository(7,"Repository7","name7")
    )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(inflater,parent,false)
        return RepoViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return repoList.size
    }

    companion object{
        val diffUtil = object:DiffUtil.ItemCallback<Repository>(){
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem == newItem
            }
        }
    }
   class RepoViewHolder(private val binding:ItemRepoBinding) : RecyclerView.ViewHolder(binding.root){
       fun bind(item:Repository){
           with(binding) {
               tvRepoTitle.text = item.title
               tvRepoName.text = item.name
           }
       }
    }
}