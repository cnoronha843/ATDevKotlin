package br.edu.infnet.gymcardtraning.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.edu.infnet.gymcardtraning.data.model.WeatherDetail.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class WeatherDetail(

    @PrimaryKey
    var id: Int? = 0,
    var temp: Double? = null,
    var icon: String? = null,
    var cityName: String? = null,
    var countryName: String? = null,
    var dateTime: String? = null
) {
    companion object {
        const val TABLE_NAME = "weather_detail"
    }
}