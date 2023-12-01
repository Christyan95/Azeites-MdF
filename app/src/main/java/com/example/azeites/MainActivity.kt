package com.example.azeites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.azeites.telaCliente.TelaClientePrincipal
import com.example.azeites.telaPedido.TelaPedidoPrincipal
import com.example.azeites.telaProduto.TelaProdutoPrincipal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            chamandoOutraTela()
        }
    }
}

@Composable
fun chamandoOutraTela(){
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(android.graphics.Color.parseColor("#1C2000"))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Azeites Maria da Fé",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = Color.White
        )

        Spacer(modifier = Modifier.height(100.dp))

        Button(
            onClick = {
                //Isso é uma ação.
                chamarTelaCliente(contexto)
            },
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            //Estas são características gráficas.
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#E1FF00")))) {
            Text(text = "Clientes - MdF",color = Color(android.graphics.Color.parseColor("#1C2000")), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                //Isso é uma ação.
                chamarTelaProduto(contexto)
            },
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            //Estas são características gráficas.
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#E1FF00")))) {
            Text(text = "Produtos  - MdF",color = Color(android.graphics.Color.parseColor("#1C2000")), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                //Isso é uma ação.
                chamarTelaPedido(contexto)
            },
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(24.dp)),
            //Estas são características gráficas.
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#E1FF00"))),
        ) {
            Text(text = "Pedidos - MdF",color = Color(android.graphics.Color.parseColor("#1C2000")), fontWeight = FontWeight.Bold)
        }
    }
}

fun chamarTelaCliente(contexto : Context){
    contexto.startActivity(Intent(contexto, TelaClientePrincipal::class.java))
}

fun chamarTelaProduto(contexto : Context){
    contexto.startActivity(Intent(contexto, TelaProdutoPrincipal::class.java))
}

fun chamarTelaPedido(contexto : Context){
    contexto.startActivity(Intent(contexto, TelaPedidoPrincipal::class.java))
}