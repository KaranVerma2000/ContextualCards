package com.example.contextualcards.viewHolders

import android.animation.ObjectAnimator
import android.content.SharedPreferences
import android.graphics.Color
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contextualcards.databinding.ItemHc3LayoutBinding
import com.example.contextualcards.model.CardGroup
import timber.log.Timber


/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class ViewholderHC3(var binding: ItemHc3LayoutBinding) : RecyclerView.ViewHolder(binding.root),
    View.OnLongClickListener {
    fun bind(
        cardGroupsList: ArrayList<CardGroup>,
        position: Int,
        groupPosition: Int,
        dismissList: ArrayList<Int>
    ) {
        val cards = cardGroupsList[groupPosition].cards

        dismissList.forEach {
            cards.removeAt(it)
        }
        Timber.d("HC3 cards ${cards.toString()}")

        if (!cards.isNullOrEmpty()) {
            if (!cards[position].bg_color.isNullOrEmpty())
                binding.layout.setBackgroundColor(Color.parseColor(cards[position].bg_color))

            if (!cards[position].bg_image?.image_url.isNullOrEmpty())
                Glide.with(binding.root).load(cards[position].bg_image?.image_url)
                    .into(binding.image)

            binding.title.text = cards[position].formatted_title?.text
            binding.desc.text = cards[position].formatted_description?.text

            binding.btn.text = cards[position].cta?.get(0)?.text
            binding.btn.setTextColor(Color.parseColor(cards[position].cta?.get(0)?.text_color))
            binding.btn.setBackgroundColor(Color.parseColor(cards[position].cta?.get(0)?.bg_color))
            binding.btn.setOnClickListener {
            }
            binding.layout.setOnLongClickListener(this)
        }
    }

    override fun onLongClick(p0: View?): Boolean {
        Timber.d("Long hit")
        val oa1 = (p0?.width)?.times((0.4))?.toFloat()
            ?.let { ObjectAnimator.ofFloat(p0, "translationX", 0f, it) }
        oa1?.interpolator = DecelerateInterpolator()
        oa1?.duration = 100
        oa1?.start()
        return true
    }
}