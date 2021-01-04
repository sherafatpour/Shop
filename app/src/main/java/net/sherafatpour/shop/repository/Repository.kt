package net.sherafatpour.shop.repository

import android.content.Context
import retrofit2.Response
import java.lang.Exception

abstract class Repository {

    suspend fun <T:Any> customRequest(Api:()->Response<T>):T{

        val response = Api.invoke()
        if (response.isSuccessful)
            return response.body()!!
        throw Exception(response.message())

    }
    companion object{
        fun setLogin(content : Context, userid:String){
            val shard = content.getSharedPreferences("token",0)
            val editor = shard.edit()
            editor.putString("userid",userid)
            editor.apply()
        }

        fun isLogin(content : Context) : String{
            val shard = content.getSharedPreferences("token",0)
            val userid = shard.getString("userid",null)
            userid?.let {
                return userid
            }
            return "notFind"
        }
    }

}