package net.sherafatpour.shop.repository

import retrofit2.Response
import java.lang.Exception

abstract class Repository {

    suspend fun <T:Any> customRequest(Api:()->Response<T>):T{

        val response = Api.invoke()
        if (response.isSuccessful)
            return response.body()!!
        throw Exception(response.message())

    }


}