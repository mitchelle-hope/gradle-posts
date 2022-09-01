package dev.mitchelle.myposts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.mitchelle.myposts.databinding.PostListItemsBinding

class PostRvAdapter (var postList:List<Post>):RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var binding = PostListItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        binding.mcvPosts

        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currentPost=postList.get(position)
        with(holder.binding){
            tvUserId.text=currentPost.userId.toString()
            tvId.text=currentPost.id.toString()
            tvTitle.text=currentPost.title
            tvBody.text=currentPost.body
            var context=holder.itemView.context //using context to get a reference to where we are
            mcvPosts.setOnClickListener {   //adding an onclick listener to the cardview (since it is the root)
                val intent=Intent(context,CommentsActivity::class.java) //starting services
                intent.putExtra("POST_ID",currentPost.id)   //passing data in intents (contains a key and value)
                context.startActivity(intent)   //using context to check the activity this adapter is going to be used in
            }
        }

    }

    override fun getItemCount(): Int {
        return postList.size
    }
}
class PostViewHolder(var binding: PostListItemsBinding):RecyclerView.ViewHolder(binding.root){}