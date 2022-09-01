package dev.mitchelle.myposts

import                                                                                       androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.mitchelle.myposts.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPosts()
    }
    fun fetchPosts(){
        var apiClient = ApiClient.buildApiCLient(ApiInterface::class.java)
        var request = apiClient.getPosts()

        request.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if(response.isSuccessful){
                    var posts=response.body()?: emptyList() //elvis operator
                    Toast.makeText(baseContext,"fetched ${posts!!.size} posts",Toast.LENGTH_LONG).show()
                    binding.rvAdapt.layoutManager=LinearLayoutManager(baseContext)
                    binding.rvAdapt.adapter= PostRvAdapter(posts)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}