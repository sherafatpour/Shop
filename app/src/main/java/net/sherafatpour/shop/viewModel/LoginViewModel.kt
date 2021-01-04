package net.sherafatpour.shop.viewModel

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import net.sherafatpour.shop.model.Status
import net.sherafatpour.shop.repository.Api
import net.sherafatpour.shop.repository.HandleRequest
import net.sherafatpour.shop.repository.ThreadMain

class LoginViewModel(val app : Application) : AndroidViewModel(app) {

    lateinit var job : Job
    val loginLivedata = MutableLiveData<Status>()
    val registerClick = MutableLiveData<Boolean>()
    var mobile : String? = null
    var pass : String? = null

    fun loginBtn(view : View){
        when {
            mobile.isNullOrEmpty() -> {
                Toast.makeText(app.applicationContext, "شماره موبایل را وارد کنید...", Toast.LENGTH_SHORT).show()
            }
            pass.isNullOrEmpty() -> {
                Toast.makeText(app.applicationContext, "رمز عبور را وارد کنید...", Toast.LENGTH_SHORT).show()
            }
            else -> {
                job= ThreadMain.coroutineHandle({
                    HandleRequest.request(Api.invoke().login(mobile!!,pass!!))
                },
                        {
                            loginLivedata.value=it
                        })
            }
        }
    }
    fun registerBtn(view : View){
        registerClick.value=true
    }

    override fun onCleared() {
        if(::job.isInitialized)job.cancel()
        super.onCleared()
    }

}