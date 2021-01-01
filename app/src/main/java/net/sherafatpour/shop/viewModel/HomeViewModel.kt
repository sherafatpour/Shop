package net.sherafatpour.shop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import net.sherafatpour.shop.model.post.PostModel
import net.sherafatpour.shop.repository.Api
import net.sherafatpour.shop.repository.HandleRequest
import net.sherafatpour.shop.repository.ThreadMain

class HomeViewModel : ViewModel() {


    lateinit var job: Job

    val postLiveData = MutableLiveData<PostModel>()

    fun getPosts() {
        job = ThreadMain.coroutineHandle(
            {
                HandleRequest.request(Api = Api.invoke().listPost())
            }, {

                postLiveData.value = it
            }

        )

    }

    override fun onCleared() {
        super.onCleared()
        if (this::job.isInitialized)
            job.cancel()
    }
}