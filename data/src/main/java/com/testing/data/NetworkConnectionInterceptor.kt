package com.testing.data

import android.content.Context
import android.net.ConnectivityManager
import com.testing.utils.NoInternetException
import com.testing.utils.isInternetAvailable
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        val connectivityManager = applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (!connectivityManager.isInternetAvailable(applicationContext))
            throw NoInternetException()

        return chain.proceed(chain.request())
    }
}