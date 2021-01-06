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
import net.sherafatpour.shop.databinding.FragmentLoginBinding
import net.sherafatpour.shop.repository.Repository
import net.sherafatpour.shop.viewModel.LoginViewModel

class LoginFragment : Fragment() {
    var binding : FragmentLoginBinding? = null
    lateinit var  navController : NavController
    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding=  FragmentLoginBinding.inflate(inflater,container,false)
        navController= Navigation.findNavController(requireActivity(),R.id.nav_host_fragment)
         viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding!!.data=viewModel
        viewModel.loginLivedata.observe(requireActivity(), Observer {
            if(it.status == "ok"){
                Repository.setLogin(requireContext(),it.user_id)
                val bundle= Bundle()
                bundle.putString("userId",it.user_id)
               navController.navigate(R.id.action_loginFragment_to_navigation_profile,bundle)
            }else
            {
                Toast.makeText(requireContext(), "خطا نام کاربری یا رمز عبور صحیح نیست!", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.registerClick.observe(requireActivity(), Observer {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        })

        return binding!!.root
    }



}