package com.santis.bcard.util

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

fun isValidEmail(target: CharSequence?): Boolean {
    return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
}

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}
