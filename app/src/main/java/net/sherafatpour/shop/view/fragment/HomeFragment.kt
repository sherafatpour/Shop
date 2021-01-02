package net.sherafatpour.shop.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.sherafatpour.shop.DataBinderMapperImpl
import net.sherafatpour.shop.R
import net.sherafatpour.shop.databinding.FragmentHomeBinding
import net.sherafatpour.shop.view.adapter.PostAdapter
import net.sherafatpour.shop.viewModel.HomeViewModel

class HomeFragment : Fragment(),PostAdapter.ItemsClick {
    private  var binding: FragmentHomeBinding? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        //save recyclerview state -- binding ?: run {}
        binding ?: run {
            binding = FragmentHomeBinding.inflate(inflater, container, false)
            recyclerView = binding!!.recyclerview
            val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
            viewModel.getPosts()
            viewModel.postLiveData.observe(requireActivity(), Observer { itpost ->
                recyclerView.also {
                    it.layoutManager = LinearLayoutManager(requireActivity())
                    val adapter = PostAdapter(itpost, this)
                    it.adapter = adapter
                }
            })

        }

        return binding!!.root
    }

    override fun itemId(id: String) {

        val bundle = Bundle()
        bundle.putString("postId", id)

        Navigation.findNavController(recyclerView).navigate(R.id.action_navigation_home_to_detailFragment,bundle)

    }
}