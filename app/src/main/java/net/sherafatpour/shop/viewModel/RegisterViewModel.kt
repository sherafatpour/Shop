package net.sherafatpour.shop.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import net.sherafatpour.shop.model.Status
import net.sherafatpour.shop.repository.Api
import net.sherafatpour.shop.repository.HandleRequest
import net.sherafatpour.shop.repository.ThreadMain

class RegisterViewModel : ViewModel() {
    lateinit var job : Job
    val registerLivedata = MutableLiveData<Status>()
    val loginLiveData = MutableLiveData<Boolean>()
    var name : String? = null
    var mobile : String? = null
    var email : String? = null
    var pass : String? = null

    fun registerBtn(view : View){
        job= ThreadMain.coroutineHandle({
            HandleRequest.request(Api.invoke().register(name!!,mobile!!,email!!,pass!!))
        },
                {
                    registerLivedata.value=it
                })
    }
    fun loginBtn(view : View){
        loginLiveData.value=true
    }

    override fun onCleared() {
        if(::job.isInitialized)job.cancel()
        super.onCleared()
    }
}