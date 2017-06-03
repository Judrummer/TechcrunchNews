package com.github.judrummer.techcrunchnews.data.entity

import java.util.*

/**
 * Created by judrummer on 3/6/2560.
 */

data class NewsEntity(
        val author:String,
        val title:String,
        val description:String,
        val url:String,
        val urlToImage:String,
        val publishedAt:Date
)