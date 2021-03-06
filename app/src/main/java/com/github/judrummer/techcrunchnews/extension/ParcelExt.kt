package com.github.judrummer.techcrunchnews.extension

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by judrummer on 3/6/2560.
 */

inline fun <reified T : Parcelable> createParcel(
        crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }