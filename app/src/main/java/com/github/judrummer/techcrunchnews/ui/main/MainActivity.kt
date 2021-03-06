package com.github.judrummer.techcrunchnews.ui.main

import android.os.Bundle
import com.github.judrummer.techcrunchnews.R
import com.github.judrummer.techcrunchnews.extension.transaction
import com.github.judrummer.techcrunchnews.ui.base.BaseActivity
import com.github.judrummer.techcrunchnews.ui.main.newslist.NewsListFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by judrummer on 3/6/2560.
 */

class MainActivity : BaseActivity() {

    override val contentLayoutResourceId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.contentContainer, NewsListFragment.instance())
            }
        }
    }

}
