package br.edu.infnet.gymcardtraning.data.repositories

import br.edu.infnet.gymcardtraning.data.local.WeatherDatabase
import br.edu.infnet.gymcardtraning.data.model.WeatherDataResponse
import br.edu.infnet.gymcardtraning.data.model.WeatherDetail
import br.edu.infnet.gymcardtraning.data.network.ApiInterface
import br.edu.infnet.gymcardtraning.data.network.SafeApiRequest

class WeatherRepository(
    private val api: ApiInterface,
    private val db: WeatherDatabase
) : SafeApiRequest() {

    suspend fun findCityWeather(cityName: String): WeatherDataResponse = apiRequest {
        api.findCityWeatherData(cityName)
    }

    suspend fun addWeather(weatherDetail: WeatherDetail) {
        db.getWeatherDao().addWeather(weatherDetail)
    }

    suspend fun fetchWeatherDetail(cityName: String): WeatherDetail? =
        db.getWeatherDao().fetchWeatherByCity(cityName)

    suspend fun fetchAllWeatherDetails(): List<WeatherDetail> =
        db.getWeatherDao().fetchAllWeatherDetails()
}
