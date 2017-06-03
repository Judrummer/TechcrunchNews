package com.github.judrummer.techcrunchnews.extension

import retrofit2.Retrofit

/**
 * Created by judrummer on 3/6/2560.
 */

inline fun <reified T> Retrofit.kreate() = create(T::class.java)