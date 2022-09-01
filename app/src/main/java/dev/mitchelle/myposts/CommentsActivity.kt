package dev.mitchelle.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.mitchelle.myposts.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    var postId=0
    var comments=0
    lateinit var binding: ActivityCommentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        fetchPostId()
        setupToolbar()
        fetchComments()
    }
    fun obtainPostId(){
        postId=intent.extras?.getInt("POST_ID")?:0  //elvis operator(if expretion is null it assigns the variable on the left else the right
    }

    fun fetchPostId(){
        val apiClient=ApiClient.buildApiCLient(ApiInterface::class.java)
        val request=apiClient.getPostById(postId)
        request.enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    var post=response.body()
                    binding.tvPostTitle.text=post?.title
                    binding.tvPostBody.text=post?.body
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun setupToolbar(){
        setSupportActionBar(binding.toolbarCom)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    fun fetchComments(){
        var retro=ApiClient.buildApiCLient((ApiInterface::class.java))
        var request=retro.getComments(postId)
        request.enqueue(object :Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if(response.isSuccessful){
                    var commentss=response.body()?: emptyList()
                        displayComments(commentss)

                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun displayComments(commentList:List<Comment>){
        val commentsAdapter=CommentsRVAdapter(commentList)
        binding.rvComments.layoutManager=LinearLayoutManager(this)
        binding.rvComments.adapter=commentsAdapter
    }

}