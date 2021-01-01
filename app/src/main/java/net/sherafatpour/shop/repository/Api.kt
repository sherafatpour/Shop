package net.sherafatpour.shop.repository

import net.sherafatpour.shop.model.detail.PostDetail
import net.sherafatpour.shop.model.post.PostModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("index.php")
    suspend fun listPost(): Response<PostModel>

    @POST("postdetails.php")
    @FormUrlEncoded
    suspend fun postDetail(@Field("id")id:String): Response<PostDetail>


    companion object {

        operator fun invoke(): Api {

            return Retrofit.Builder()
                .baseUrl("http://kongerehstudio.com/shop/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)

        }
    }
}