package com.github.judrummer.techcrunchnews

import com.github.judrummer.techcrunchnews.data.api.NewsRepositoryMock
import com.github.judrummer.techcrunchnews.data.repository.NewsRepository

object Injection {

    fun provideNewsRepository(): NewsRepository = NewsRepositoryMock()

}
