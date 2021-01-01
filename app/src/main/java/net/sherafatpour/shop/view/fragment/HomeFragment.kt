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
import net.sherafatpour.shop.R
import net.sherafatpour.shop.view.adapter.PostAdapter
import net.sherafatpour.shop.viewModel.HomeViewModel

class HomeFragment : Fragment(),PostAdapter.ItemsClick {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView= root.findViewById(R.id.recyclerview)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getPosts()

        homeViewModel.postLiveData.observe(viewLifecycleOwner, Observer {

            val postAdapter = PostAdapter(it,this)
            recyclerView.apply {

                layoutManager  =LinearLayoutManager(requireActivity())
                 adapter = postAdapter

            }
        })
    }

    override fun itemId(id: String) {

        val bundle = Bundle()
        bundle.putString("postId", id)

        Navigation.findNavController(recyclerView).navigate(R.id.action_navigation_home_to_detailFragment,bundle)

    }
}