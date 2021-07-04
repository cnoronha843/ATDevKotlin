package br.edu.infnet.gymcardtraning.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.HashMap



data class Exercicio(
    val uid: String = "",
    val nomeExercicio: String? = "",
    val tempoExercicio: String? = "",
    val descansoExercicio: String? = "",
    val url: String? = ""




)
{

    fun toMap(): Map<String,String> {

        val result = HashMap<String,String>()
        result["uid"] = uid
        result["nomeExercicio"] = nomeExercicio.toString()
        result["tempoExercicio"] = tempoExercicio.toString()
        result["descansoExercicio"] = descansoExercicio.toString()
        result["url"] = url.toString()


        return result
    }

}