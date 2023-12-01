package com.example.azeites.telaPedido

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class TelaPedidoPrincipal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            minhaTelaPedidoPrincipal()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun minhaTelaPedidoPrincipal(modifier: Modifier = Modifier) {

    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(android.graphics.Color.parseColor("#1C2000"))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("  - Pedidos | Azeites MdF - ",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = Color.White
        )

        Spacer(modifier = Modifier.height(100.dp))

        Button(
            onClick = {
//                contexto.startActivity(Intent(contexto,TelaPedidoInserir::class.java))
            },
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#E1FF00")))
        ) {
            Text(text = "Tela Inserir Pedidos", color = Color(android.graphics.Color.parseColor("#1C2000")), fontWeight = FontWeight.Bold)
        }

//        Spacer(modifier = Modifier.height(35.dp))
//
//        Button(
//            onClick = {
//                contexto.startActivity(Intent(contexto,TelaPedidoAtualizar::class.java))
//            },
//            modifier = Modifier
//                .padding(horizontal = 25.dp)
//                .fillMaxWidth(),
//            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
//            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#E1FF00")))
//        ) {
//            Text(text = "Tela Atualizar Pedidos", color = Color(android.graphics.Color.parseColor("#1C2000")), fontWeight = FontWeight.Bold)
//        }

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
//                contexto.startActivity(Intent(contexto,TelaPedidoMostrar::class.java))
            },
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#E1FF00")))
        ) {
            Text(text = "Tela Mostrar Pedidos", color = Color(android.graphics.Color.parseColor("#1C2000")), fontWeight = FontWeight.Bold)
        }
    }
}