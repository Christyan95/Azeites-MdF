package com.example.azeites.classes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pedido (var id_pedido : String, var data : String, var cpf_cliente : String): Parcelable