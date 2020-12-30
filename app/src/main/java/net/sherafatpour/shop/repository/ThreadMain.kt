package net.sherafatpour.shop.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


object ThreadMain {
    fun <T:Any>coroutineHandle(api:suspend(()->T),callBack:((T)->Unit))=
        CoroutineScope(Dispatchers.Main).launch {
          val data =   CoroutineScope(Dispatchers.IO).async rt@{

              return@rt api()
          }.await()
            callBack(data)
        }
}