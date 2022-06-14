package com.albertomier.klikinwaylettest.ui.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.albertomier.klikinwaylettest.R
import com.albertomier.klikinwaylettest.databinding.CustomInfoLayoutBinding

class CustomInfoLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: CustomInfoLayoutBinding =
        CustomInfoLayoutBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)

        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.CustomInfoLayout, defStyleAttr, 0)

        binding.title.text = attributes.getString(R.styleable.CustomInfoLayout_title)
        binding.subtitle.text =
            attributes.getString(R.styleable.CustomInfoLayout_subtitle)

        binding.card.setCardBackgroundColor(
            attributes.getInteger(
                R.styleable.CustomInfoLayout_bgColor,
                0
            )
        )
        binding.title.setTextColor(
            attributes.getInteger(
                R.styleable.CustomInfoLayout_titleColor,
                0
            )
        )
        binding.subtitle.setTextColor(
            attributes.getInteger(
                R.styleable.CustomInfoLayout_subtitleColor,
                0
            )
        )

        customizeFont(binding)
    }

    fun setTitle(text: String) {
        binding.title.text = text
    }

    private fun customizeFont(binding: CustomInfoLayoutBinding) {
        binding.title.typeface = Typeface.createFromAsset(context.assets, "fonts/Taviraj-Bold.ttf")
        binding.subtitle.typeface =
            Typeface.createFromAsset(context.assets, "fonts/Taviraj-SemiBold.ttf")
    }
}