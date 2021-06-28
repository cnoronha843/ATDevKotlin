package br.edu.infnet.gymcardtraning

import android.app.Application
import br.edu.infnet.gymcardtraning.data.local.WeatherDatabase
import br.edu.infnet.gymcardtraning.data.network.ApiInterface
import br.edu.infnet.gymcardtraning.data.network.NetworkConnectionInterceptor
import br.edu.infnet.gymcardtraning.data.repositories.WeatherRepository
import br.edu.infnet.gymcardtraning.ui.viewmodelfactory.WeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class WeatherApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@WeatherApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiInterface(instance()) }
        bind() from singleton { WeatherRepository(instance(), instance()) }
        bind() from provider { WeatherViewModelFactory(instance()) }
        bind() from provider { WeatherDatabase(instance()) }
    }


}
