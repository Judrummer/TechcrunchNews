package com.github.judrummer.techcrunchnews.data.api

import com.github.judrummer.techcrunchnews.data.entity.NewsEntity
import com.github.judrummer.techcrunchnews.data.repository.NewsRepository

import io.reactivex.Observable
import java.util.*

class NewsRepositoryMock : NewsRepository {

    override fun getNewsList(): Observable<List<NewsEntity>> {
        val mockList = ArrayList<NewsEntity>()
        (1..20).mapTo(mockList) {
            NewsEntity(
                    author = "Judrummer$it",
                    title = "title$it", description = "description$it Developer & Drummer",
                    url = "https://github.com/Judrummer",
                    urlToImage = "https://avatars0.githubusercontent.com/u/12605075?v=3&s=460",
                    publishedAt = Date())
        }
        return Observable.just<List<NewsEntity>>(mockList)
    }

}
