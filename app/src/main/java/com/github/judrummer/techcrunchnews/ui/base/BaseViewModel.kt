package com.github.judrummer.techcrunchnews.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<T> {

    abstract val state: BehaviorSubject<T>

    protected var disposables = CompositeDisposable()

    abstract fun attachView()

    open fun detachView() {
        disposables.clear()
    }

    open fun saveState(): T {
        return state.value
    }

    open fun restoreState(state: T) {
        this.state.onNext(state)
    }

}

