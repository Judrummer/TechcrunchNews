package com.github.judrummer.techcrunchnews.ui.man.newslist

import com.github.judrummer.techcrunchnews.data.entity.NewsEntity
import com.github.judrummer.techcrunchnews.data.repository.NewsRepository
import com.github.judrummer.techcrunchnews.ui.main.newslist.NewsItem
import com.github.judrummer.techcrunchnews.ui.main.newslist.NewsListState
import com.github.judrummer.techcrunchnews.ui.main.newslist.NewsListViewIntent
import com.github.judrummer.techcrunchnews.ui.main.newslist.NewsListViewModel
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * Created by judrummer on 4/6/2560.
 */

class NewsListViewModelTest {

    lateinit var getNewsListSubject: PublishSubject<List<NewsEntity>>
    lateinit var refreshSubject: PublishSubject<Unit>

    lateinit var repository: NewsRepository
    lateinit var viewIntent: NewsListViewIntent
    lateinit var viewModel: NewsListViewModel

    val CURRENTDATE =  Date()

    val NEWS_ENTITY_LIST = (1..20).map {
        NewsEntity(
                author = "author$it",
                title = "title$it", description = "description$it",
                url = "url$it",
                urlToImage = "urlToImage$it",
                publishedAt = CURRENTDATE)
    }


    val NEWS_ITEM_LIST = (1..20).map {
        NewsItem(
                author = "author$it",
                title = "title$it", description = "description$it",
                url = "url$it",
                urlToImage = "urlToImage$it",
                publishedAt = CURRENTDATE)
    }

    @Before
    fun setup() {
        refreshSubject = PublishSubject.create<Unit>()
        getNewsListSubject = PublishSubject.create<List<NewsEntity>>()
        repository = mock()
        viewIntent = mock()
        Mockito.`when`(viewIntent.refreshIntent()).thenReturn(refreshSubject)
        Mockito.`when`(repository.getNewsList()).thenReturn(getNewsListSubject)
        viewModel = NewsListViewModel(viewIntent, repository)
    }

    @Test
    fun refreshNewsList_repositorySuccess_renderCorrectState() {
        viewModel.attachView()
        val actualStates = viewModel.state.test()
        refreshSubject.onNext(Unit)
        getNewsListSubject.onNext(NEWS_ENTITY_LIST)
        val expectedState1 = NewsListState()
        val expectedState2 = expectedState1.copy(loading = true)
        val expectedState3 = expectedState2.copy(loading = false, newsList = NEWS_ITEM_LIST)
        viewModel.detachView()
        actualStates.assertValues(expectedState1, expectedState2, expectedState3)
        actualStates.assertNoErrors()
    }

    @Test
    fun refreshNewsList_repositoryErrorSuccess_renderCorrectState() {
        viewModel.attachView()
        val actualStates = viewModel.state.test()
        refreshSubject.onNext(Unit)
        val error = Throwable("mockError")
        getNewsListSubject.onError(error)
        val expectedState1 = NewsListState()
        val expectedState2 = expectedState1.copy(loading = true)
        val expectedState3 = expectedState2.copy(loading = false, error = "mockError")
        viewModel.detachView()
        actualStates.assertValues(expectedState1, expectedState2, expectedState3)
        actualStates.assertNoErrors()
    }


}
