package com.example.airqualitywebsocket.utils

import android.text.format.DateUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.airqualitywebsocket.R
import com.google.gson.reflect.TypeToken
import java.util.*

inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

@BindingAdapter("setBackgroundColorAccToRange")
fun setBackgroundColorAccToRange(view: TextView, inputNumber: Double) =
    when (inputNumber.toInt()) {
        in 0..50 -> {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.color_good))
        }

        in 51..100 -> {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.color_satisfactory))
        }

        in 101..200 -> {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.color_moderate))
        }

        in 201..300 -> {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.color_poor))
        }

        in 301..400 -> {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.color_very_poor))
        }

        in 401..500 -> {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.color_severe))
        }

        else -> {
            view.setTextColor(
                ContextCompat.getColor(
                    view.context,
                    R.color.design_default_color_primary
                )
            )
        }
    }

@BindingAdapter("getLastUpdatedTime")
fun getLastUpdatedTime(view: TextView,inputNumber: Double) {
    val text = DateUtils.getRelativeTimeSpanString(
        Calendar.getInstance().timeInMillis,
        Calendar.getInstance().timeInMillis,
        DateUtils.MINUTE_IN_MILLIS
    );
    view.text = text
}

