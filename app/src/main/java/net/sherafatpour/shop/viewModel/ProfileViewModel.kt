package net.sherafatpour.shop.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import net.sherafatpour.shop.model.orders.OrderModel
import net.sherafatpour.shop.model.userInfo.UserInfoModel
import net.sherafatpour.shop.repository.Api
import net.sherafatpour.shop.repository.HandleRequest
import net.sherafatpour.shop.repository.ThreadMain

class ProfileViewModel : ViewModel() {

    lateinit var job: Job
    val mutableUserInfo = MutableLiveData<UserInfoModel>()
    val mutableUserOrder = MutableLiveData<OrderModel>()

    fun getInfo(id:String){
        job = ThreadMain.coroutineHandle({HandleRequest.request(Api.invoke().userInfo(userid = id))},{
            mutableUserInfo.value = it
        })
        job = ThreadMain.coroutineHandle({HandleRequest.request(Api.invoke().orderList(userid = id))},{
            mutableUserOrder.value = it
        })
    }

    override fun onCleared() {
        super.onCleared()
        if(this::job.isInitialized)
            job.cancel()
    }
}