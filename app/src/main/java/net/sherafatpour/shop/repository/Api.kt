package net.sherafatpour.shop.repository

import net.sherafatpour.shop.model.PostModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {

    @GET("index.php")
    suspend fun listPost(): Response<PostModel>

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