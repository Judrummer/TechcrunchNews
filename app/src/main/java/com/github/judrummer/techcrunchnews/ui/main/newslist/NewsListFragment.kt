package com.github.judrummer.techcrunchnews.ui.main.newslist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.judrummer.jxadapter.JxAdapter
import com.github.judrummer.jxadapter.JxViewHolder
import com.github.judrummer.techcrunchnews.Injection
import com.github.judrummer.techcrunchnews.R
import com.github.judrummer.techcrunchnews.extension.addTo
import com.github.judrummer.techcrunchnews.extension.setImageUrl
import com.github.judrummer.techcrunchnews.ui.base.BaseFragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.item_news.view.*

class NewsListFragment : BaseFragment(), NewsListViewIntent {

    companion object {
        fun instance() = NewsListFragment()
    }


    override val contentLayoutResourceId: Int = R.layout.fragment_news_list

    val refreshSubject = PublishSubject.create<Unit>()!!
    val viewModel by lazy { NewsListViewModel(this, Injection.provideNewsRepository()) }

    val newsListAdapter = JxAdapter(JxViewHolder<NewsItem>(R.layout.item_news) { position, item ->
        itemView.apply {
            tvItemNewsTitle.text = item.title
            tvItemNewsDescription.text = item.description
            ivItemNewsImage.setImageUrl(item.urlToImage)
        }
    })

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.attachView()
        rvNewsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsListAdapter
        }
        srlNewsList.setOnRefreshListener { refreshSubject.onNext(Unit) }

        viewModel.state.subscribe { (loading, newsList, error) ->
            newsListAdapter.items = newsList
            srlNewsList.isRefreshing = loading
            tvErrorMessage.visibility = if (error != null) View.VISIBLE else View.GONE
        }.addTo(disposables)


        if (savedInstanceState == null) {
            refreshSubject.onNext(Unit)
        } else {
            val state: NewsListState = savedInstanceState.getParcelable(NewsListFragment::class.java.simpleName)
            viewModel.restoreState(state)
            if (state.loading) refreshSubject.onNext(Unit)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(NewsListFragment::class.java.simpleName, viewModel.saveState())
    }

    override fun onDestroyView() {
        viewModel.detachView()
        super.onDestroyView()
    }

    override fun refreshIntent(): Observable<Unit> = refreshSubject
}
