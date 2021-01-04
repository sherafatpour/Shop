package net.sherafatpour.shop.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import net.sherafatpour.shop.R
import net.sherafatpour.shop.databinding.FragmentRegisterBinding
import net.sherafatpour.shop.repository.Repository
import net.sherafatpour.shop.viewModel.RegisterViewModel

class RegisterFragment : Fragment() {
    var binding: FragmentRegisterBinding? = null
    lateinit var navController: NavController

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding!!.data = viewModel
        viewModel.registerLivedata.observe(requireActivity(), Observer {
            if (it.status == "ok") {
                Repository.setLogin(requireContext(), it.user_id)
                navController.navigate(R.id.action_registerFragment_to_navigation_profile)
            } else {
                Toast.makeText(requireContext(), " خطا در ثبت نام اطلاعات...!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        viewModel.registerLivedata.observe(requireActivity(), Observer {
            navController.navigate(R.id.navigation_profile)
            Repository.setLogin(requireContext(), it.user_id)
        })

        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

}