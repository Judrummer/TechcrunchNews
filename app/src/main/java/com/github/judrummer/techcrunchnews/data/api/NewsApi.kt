package com.github.judrummer.techcrunchnews.data.api

import com.github.judrummer.techcrunchnews.data.entity.NewsEntity
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by judrummer on 3/6/2560.
 */

data class GetNewsListApiResponse(val status: String,
                                  val source: String,
                                  val sortBy: String,
                                  val articles: List<NewsEntity>)

interface NewsApi {
    @GET("articles?source=techcrunch&apiKey=$NEWSAPI_KEY")
    fun getNewsList(): Observable<GetNewsListApiResponse>
}
