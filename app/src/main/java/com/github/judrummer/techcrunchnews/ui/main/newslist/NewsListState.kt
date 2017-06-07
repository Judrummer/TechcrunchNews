package com.github.judrummer.techcrunchnews.ui.main.newslist

import android.os.Parcel
import android.os.Parcelable
import com.github.judrummer.jxadapter.JxItem
import java.util.*

/**
 * Created by judrummer on 4/6/2560.
 */


data class NewsListState(val loading: Boolean = false, val newsList: List<NewsItem> = listOf(), val error: String? = null) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<NewsListState> = object : Parcelable.Creator<NewsListState> {
            override fun createFromParcel(source: Parcel): NewsListState = NewsListState(source)
            override fun newArray(size: Int): Array<NewsListState?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            1 == source.readInt(),
            ArrayList<NewsItem>().apply { source.readList(this, NewsItem::class.java.classLoader) },
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt((if (loading) 1 else 0))
        dest.writeList(newsList)
        dest.writeString(error)
    }
}


data class NewsItem(
        val author: String = "",
        val title: String = "",
        val description: String = "",
        val url: String = "",
        val urlToImage: String = "",
        val publishedAt: Date = Date()
) : JxItem, Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<NewsItem> = object : Parcelable.Creator<NewsItem> {
            override fun createFromParcel(source: Parcel): NewsItem = NewsItem(source)
            override fun newArray(size: Int): Array<NewsItem?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readSerializable() as Date
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(author)
        dest.writeString(title)
        dest.writeString(description)
        dest.writeString(url)
        dest.writeString(urlToImage)
        dest.writeSerializable(publishedAt)
    }
}

