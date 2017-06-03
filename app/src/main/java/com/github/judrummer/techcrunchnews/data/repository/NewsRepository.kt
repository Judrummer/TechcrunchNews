package com.github.judrummer.techcrunchnews.data.repository

import com.github.judrummer.techcrunchnews.data.entity.NewsEntity
import io.reactivex.Observable

/**
 * Created by judrummer on 3/6/2560.
 */

interface NewsRepository {
    fun getNewsList(): Observable<List<NewsEntity>>
}
