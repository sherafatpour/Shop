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
import net.sherafatpour.shop.viewModel.CardViewModel

class CardFragment : Fragment() {

    private lateinit var cardViewModel: CardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        cardViewModel =
                ViewModelProvider(this).get(CardViewModel::class.java)
        val root = inflater.inflate(R.layout.card_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        cardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}