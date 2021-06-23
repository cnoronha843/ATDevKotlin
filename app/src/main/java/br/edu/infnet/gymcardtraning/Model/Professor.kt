package br.edu.infnet.gymcardtraning.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Professor(
        val uid: String = "",
        val nomeProfessor: String = "",
        val emailProfessor:String = "",
        val idadeProfessor:String = "" ,
        val senhaProfessor: String = "",
        val isProfessor: Boolean,
        val url: String = ""


): Parcelable