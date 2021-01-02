package net.sherafatpour.shop.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import net.sherafatpour.shop.databinding.ViewPagerItemBinding
import net.sherafatpour.shop.model.detail.Slider

class ViewPagerAdapter(val urls: List<Slider>) : PagerAdapter() {
    override fun getCount(): Int {
        return urls.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val binding = ViewPagerItemBinding.inflate(LayoutInflater.from(container.context), container, false)
        binding.data = urls[position].image
        container.addView(binding.root)
        return binding.root
    }
}