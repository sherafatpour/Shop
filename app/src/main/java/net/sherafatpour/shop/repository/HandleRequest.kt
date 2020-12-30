package net.sherafatpour.shop.repository

import retrofit2.Response

object HandleRequest : Repository() {

    suspend fun <T : Any> request(Api: Response<T>) = customRequest {
        Api
    }

}