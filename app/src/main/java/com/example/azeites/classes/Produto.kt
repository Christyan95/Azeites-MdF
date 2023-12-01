package com.example.azeites.classes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Produto (var id_produto : String, var descricao : String, var valor : Float): Parcelable