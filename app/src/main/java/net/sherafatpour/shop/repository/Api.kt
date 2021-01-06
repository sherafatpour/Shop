package net.sherafatpour.shop.repository

import net.sherafatpour.shop.model.Status
import net.sherafatpour.shop.model.detail.PostDetail
import net.sherafatpour.shop.model.orders.OrderModel
import net.sherafatpour.shop.model.post.PostModel
import net.sherafatpour.shop.model.userInfo.UserInfoModel
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

    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(@Field("mobile")mobile:String,@Field("pass")pass:String) : Response<Status>

    @FormUrlEncoded
    @POST("Reg.php")
    suspend fun register(@Field("name")name:String,@Field("mobile")mobile:String,@Field("email")email:String,@Field("pass")pass:String) : Response<Status>

    @FormUrlEncoded
    @POST("User_info.php")
    suspend fun userInfo(@Field("user_id")userid:String) : Response<UserInfoModel>

    @FormUrlEncoded
    @POST("list_order.php")
    suspend fun orderList(@Field("user")userid:String) : Response<OrderModel>

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