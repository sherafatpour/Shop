package net.sherafatpour.shop.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.sherafatpour.shop.R
import net.sherafatpour.shop.viewModel.NotificationsViewModel

class ProfileFragment : Fragment() {

    private lateinit var ProfileViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        ProfileViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)


        return  inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ProfileViewModel.text.observe(viewLifecycleOwner, Observer { item ->



        })
    }
}