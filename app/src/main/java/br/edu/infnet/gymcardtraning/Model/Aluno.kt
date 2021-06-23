package br.edu.infnet.gymcardtraning.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Aluno(
        val uid: String = "",
        val nomeAluno: String = "",
        val emailAluno:String = "",
        val idadeAluno:String = "" ,
        val senhaAluno: String = "",
        val isAluno: Boolean,
        val url: String = ""


): Parcelable