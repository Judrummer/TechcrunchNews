package com.github.judrummer.techcrunchnews

import com.github.judrummer.techcrunchnews.data.repository.NewsRepository
import com.github.judrummer.techcrunchnews.data.repository.NewsRepositoryImpl

object Injection {

    fun provideNewsRepository(): NewsRepository {
        return NewsRepositoryImpl()
    }

}
