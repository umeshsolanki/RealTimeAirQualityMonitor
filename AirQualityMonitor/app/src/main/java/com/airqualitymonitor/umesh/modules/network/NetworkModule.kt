package com.airqualitymonitor.umesh.modules.network

import android.app.Application
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.retry.ExponentialWithJitterBackoffStrategy
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Network module provides all n/w relates stuffs eg: Scarlet and OkHTTP client
 *
 * @constructor Create empty Network module
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val URL = "ws://city-ws.herokuapp.com"

    /**
     * Provide  singleton instance of websocket client i.e Scarlet
     *
     * @param okHttpClient
     * @param application
     * @return
     */
    @Provides
    fun provideWSClient(okHttpClient: OkHttpClient, application: Application): Scarlet {
        return Scarlet.Builder()
            .webSocketFactory(okHttpClient.newWebSocketFactory(URL))
            .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
            .addMessageAdapterFactory(MoshiMessageAdapter.Factory())
            .lifecycle(AndroidLifecycle.ofApplicationForeground(application))
            .build()
    }


    /**
     * Provide okHttp client
     *
     * @return
     */
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

}