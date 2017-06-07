package com.github.judrummer.techcrunchnews.ui.main.newslist

import com.github.judrummer.techcrunchnews.data.repository.NewsRepository
import com.github.judrummer.techcrunchnews.extension.addTo
import com.github.judrummer.techcrunchnews.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by judrummer on 3/6/2560.
 */

interface NewsListViewIntent {
    fun refreshIntent(): Observable<Unit>
}

class NewsListViewModel(val viewIntent: NewsListViewIntent, private val newsRepository: NewsRepository) : BaseViewModel<NewsListState>() {

    override val state: BehaviorSubject<NewsListState> = BehaviorSubject.createDefault(NewsListState())

    override fun attachView() {
        viewIntent.refreshIntent()
                .switchMap {
                    newsRepository.getNewsList()
                            .map { Action.Loaded(it.map { NewsItem(it.author, it.title, it.description, it.url, it.urlToImage, it.publishedAt) }) as Action }
                            .onErrorReturn { Action.Error(it) }
                            .startWith(Action.Loading())
                }
                .map(this::reducer)
                .subscribe(state::onNext)
                .addTo(disposables)
    }

    private fun reducer(action: Action): NewsListState =
            when (action) {
                is Action.Loading -> state.value.copy(loading = true, error = null)
                is Action.Loaded -> state.value.copy(loading = false, newsList = action.newsList)
                is Action.Error -> state.value.copy(loading = false, error = action.error.message)
                else -> state.value
            }

    private sealed class Action {
        class Loading : Action()
        class Loaded(val newsList: List<NewsItem>) : Action()
        class Error(val error: Throwable) : Action()
    }

}