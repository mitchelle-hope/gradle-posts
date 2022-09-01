package dev.mitchelle.myposts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.mitchelle.myposts.databinding.CommentListItemBinding

class CommentsRVAdapter (var commentsList:List<Comment>):RecyclerView.Adapter<CommentsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var binding = CommentListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentComment=commentsList.get(position)
        with(holder.binding){
            tvPostIdComment.text=currentComment.postId.toString()
            tvIdComment.text=currentComment.id.toString()
            tvNameComment.text=currentComment.name
            tvEmailComment.text=currentComment.email
            tvBodyComment.text=currentComment.body
//            var context=holder.itemView.context
//            mvcComments.setOnClickListener {
//                val intent= Intent(context,CommentsActivity::class.java)
//                intent.putExtra("POST_ID",currentComment.id)
//                context.startActivity(intent)
//            }
        }
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }
}
class CommentsViewHolder(var binding: CommentListItemBinding):RecyclerView.ViewHolder(binding.root){
}