package com.xonicsports_sportnews

import android.app.Application
import com.xonicsports_sportnews.network.Api
import com.xonicsports_sportnews.network.NetworkConnectionInterceptor
import com.xonicsports_sportnews.repository.Repository
import com.xonicsports_sportnews.ui.home.HomeViewModelFactory
import com.onesignal.OneSignal
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class XonicsApp : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId("15acffc2-2b5b-4bcb-a1a2-e3181d62a0cb")
    }
    override val kodein = Kodein.lazy {
        import(androidXModule(this@XonicsApp))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { Api(instance()) }
        bind() from singleton { Repository(instance()) }
        bind() from singleton { HomeViewModelFactory(instance()) }
    }
}