package com.example.contextualcards.viewHolders

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.net.Uri
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

    private var cardClicked: Boolean = false

    fun bind(
        cardGroupsList: ArrayList<CardGroup>,
        position: Int,
        groupPosition: Int
    ) {
        if (!cardGroupsList[groupPosition].cards.isNullOrEmpty()) {
            if (!cardGroupsList[groupPosition].cards[position].bg_color.isNullOrEmpty())
                binding.layout.setBackgroundColor(Color.parseColor(cardGroupsList[groupPosition].cards[position].bg_color))

            if (!cardGroupsList[groupPosition].cards[position].bg_image?.image_url.isNullOrEmpty())
                Glide.with(binding.root)
                    .load(cardGroupsList[groupPosition].cards[position].bg_image?.image_url)
                    .into(binding.image)

            binding.title.text =
                cardGroupsList[groupPosition].cards[position].formatted_title?.text
            binding.desc.text =
                cardGroupsList[groupPosition].cards[position].formatted_description?.text

            binding.btn.text = cardGroupsList[groupPosition].cards[position].cta?.get(0)?.text
            binding.btn.setTextColor(
                Color.parseColor(
                    cardGroupsList[groupPosition].cards[position].cta?.get(
                        0
                    )?.text_color
                )
            )
            binding.btn.setBackgroundColor(
                Color.parseColor(
                    cardGroupsList[groupPosition].cards[position].cta?.get(
                        0
                    )?.bg_color
                )
            )

            if (!cardGroupsList[groupPosition].cards[position].cta?.get(0)?.url.isNullOrEmpty()) {
                binding.btn.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data =
                        Uri.parse(cardGroupsList[groupPosition].cards[position].cta?.get(0)?.url)
                    binding.btn.context.startActivity(intent)
                }
            }

            if (!cardGroupsList[groupPosition].cards[position].url.isNullOrEmpty()) {
                binding.layout.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(cardGroupsList[groupPosition].cards[position].url)
                    binding.root.context.startActivity(intent)
                }
            }

            binding.layout.setOnLongClickListener(this)
        }
    }

    override fun onLongClick(p0: View?): Boolean {
        return if (cardClicked) {
            cardClicked = !cardClicked
            val oa1 = (p0?.width)?.toFloat()
                ?.let { ObjectAnimator.ofFloat(p0, "translationX", it, 0f) }
            oa1?.interpolator = DecelerateInterpolator()
            oa1?.duration = 100
            oa1?.start()
            true
        } else {
            cardClicked = !cardClicked
            val oa1 = (p0?.width)?.times((0.4))?.toFloat()
                ?.let { ObjectAnimator.ofFloat(p0, "translationX", 0f, it) }
            oa1?.interpolator = DecelerateInterpolator()
            oa1?.duration = 100
            oa1?.start()
            true
        }
    }
}