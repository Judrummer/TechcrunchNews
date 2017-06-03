package com.github.judrummer.techcrunchnews.data.repository

import com.github.judrummer.techcrunchnews.data.api.AppRetrofit
import com.github.judrummer.techcrunchnews.data.api.NewsApi
import com.github.judrummer.techcrunchnews.data.entity.NewsEntity
import com.github.judrummer.techcrunchnews.extension.kreate
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by judrummer on 3/6/2560.
 */

class NewsRepositoryImpl : NewsRepository {

    override fun getNewsList(): Observable<List<NewsEntity>> =
            AppRetrofit.instance.kreate<NewsApi>().getNewsList().map { it.articles }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

}

