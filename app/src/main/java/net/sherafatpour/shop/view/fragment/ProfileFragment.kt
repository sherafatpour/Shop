package net.sherafatpour.shop.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.sherafatpour.shop.R
import net.sherafatpour.shop.databinding.FragmentProfileBinding
import net.sherafatpour.shop.view.adapter.OrderAdapter

import net.sherafatpour.shop.viewModel.ProfileViewModel

class ProfileFragment : Fragment(), OrderAdapter.ItemsClick {

    private lateinit var profileViewModel: ProfileViewModel
    lateinit var binding: FragmentProfileBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val userId = requireArguments().getString("userId")
        profileViewModel.getInfo(userId!!)

        profileViewModel.mutableUserInfo.observe(requireActivity(), Observer {

            binding.data = it[0]

        })

        profileViewModel.mutableUserOrder.observe(requireActivity(), Observer {

            Toast.makeText(requireActivity(), it.size.toString(), Toast.LENGTH_LONG).show()
            val adapter = OrderAdapter(it, this)
            val recyclerView = binding.recyclerview
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        })


        return binding.root
    }

    override fun itemId(id: String) {

    }


}