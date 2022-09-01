package dev.mitchelle.myposts

data class Post(
    var userId:Int,
    var id:Int,
    var title:String,
    var body:String
)

data class Comment(
    var postId:Int,
    var id:Int,
    var name:String,
    var email:String,
    var body: String
)

//Generic functions - takes in any type without specificifying
//fun <T>comparePosts(item1:T, item2:T):T{
//    //Do something
//    return item1
//}
