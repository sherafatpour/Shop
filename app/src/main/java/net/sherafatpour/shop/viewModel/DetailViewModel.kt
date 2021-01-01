package net.sherafatpour.shop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import net.sherafatpour.shop.model.detail.PostDetail
import net.sherafatpour.shop.repository.Api
import net.sherafatpour.shop.repository.ThreadMain

class DetailViewModel : ViewModel() {
    lateinit var job:Job
     val  detailLiveData = MutableLiveData<PostDetail>()

    fun getDetail(id:String){
        job = ThreadMain.coroutineHandle({
            Api.invoke().postDetail(id)
        },{

            detailLiveData.value = it.body()
        })


    }

    override fun onCleared() {
        super.onCleared()
        if (this::job.isInitialized)
            job.cancel()
    }
}