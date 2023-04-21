package org.android.go.sopt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.Repository
import org.android.go.sopt.databinding.ItemRepoBinding

class RepoRVAdapter(context: Context) : RecyclerView.Adapter<RepoRVAdapter.RepoViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context) }

    private val repoList:List<Repository> = listOf(
        Repository("Repository1","name1"),
        Repository("Repository2","name2"),
        Repository("Repository3","name3"),
        Repository("Repository4","name4"),
        Repository("Repository5","name5"),
        Repository("Repository6","name6"),
        Repository("Repository7","name7")
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

   class RepoViewHolder(private val binding:ItemRepoBinding) : RecyclerView.ViewHolder(binding.root){
       fun bind(item:Repository){
           with(binding) {
               tvRepoTitle.text = item.title
               tvRepoName.text = item.name
           }
       }
    }
}