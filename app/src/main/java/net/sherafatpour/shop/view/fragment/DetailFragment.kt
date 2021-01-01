package net.sherafatpour.shop.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import net.sherafatpour.shop.R
import net.sherafatpour.shop.databinding.FragmentDetailBinding
import net.sherafatpour.shop.viewModel.DetailViewModel

class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    lateinit var btnCard:Button

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.fragment_detail,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCard = view.findViewById(R.id.BtnCard)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getDetail(requireArguments().getString("postId")!!)
        viewModel.detailLiveData.observe(requireActivity(), Observer { item ->

            binding.post = item.post[0]
            btnCard.text ="افزودن به سبد خرید ${item.post[0].price} تومان"
        })

    }


}