package com.github.judrummer.techcrunchnews.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity() {

    val disposables  = CompositeDisposable()

    abstract val contentLayoutResourceId: Int

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentLayoutResourceId)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

}
