package com.example.azeites.telaProduto

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.azeites.classes.Produto
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TelaProdutoInserir : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            minhaTelaProdutoInserir()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun minhaTelaProdutoInserir(modifier: Modifier = Modifier) {

    val referencia = Firebase.database.getReference("/produto")

    var text_id_produto by remember { mutableStateOf(TextFieldValue()) }
    var text_descricao by remember { mutableStateOf(TextFieldValue()) }
    var text_valor by remember { mutableStateOf(TextFieldValue()) }

    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    var carregamento by remember{ mutableStateOf(false)}

    if(carregamento){
        LoadingIndicator()
    } else{
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(android.graphics.Color.parseColor("#1C2000"))),
        ) {
            Text("  - Inserir Produtos | Azeites MdF - ",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 20.dp),
                color = Color.White
            )

            TextField(
                value = text_id_produto,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                onValueChange = {
                    text_id_produto = it
                },
                label = { Text("id do produto..")},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = (Color(android.graphics.Color.parseColor("#7E8D0C"))).copy(.5f),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = (Color(android.graphics.Color.parseColor("#1C2000"))),
                    focusedLabelColor = (Color(android.graphics.Color.parseColor("#E1FF00"))),
                    cursorColor = (Color(android.graphics.Color.parseColor("#E1FF00")))
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = text_descricao,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                onValueChange = {
                    text_descricao = it
                },
                label = { Text("descrição do produto..")},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = (Color(android.graphics.Color.parseColor("#7E8D0C"))).copy(.5f),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = (Color(android.graphics.Color.parseColor("#1C2000"))),
                    focusedLabelColor = (Color(android.graphics.Color.parseColor("#E1FF00"))),
                    cursorColor = (Color(android.graphics.Color.parseColor("#E1FF00")))
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = text_valor,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                onValueChange = {
                    text_valor = it
                },
                label = { Text("valor do produto")},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = (Color(android.graphics.Color.parseColor("#7E8D0C"))).copy(.5f),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = (Color(android.graphics.Color.parseColor("#1C2000"))),
                    focusedLabelColor = (Color(android.graphics.Color.parseColor("#E1FF00"))),
                    cursorColor = (Color(android.graphics.Color.parseColor("#E1FF00")))
                )
            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = {
                    carregamento = true
                    val produto = Produto(text_id_produto.text,text_descricao.text,text_valor.text.toFloat())

                    Log.i("produto",produto.toString())

                    text_id_produto = TextFieldValue("")
                    text_descricao = TextFieldValue("")
                    text_valor = TextFieldValue("")

                    referencia.child(produto.id_produto).setValue(produto)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Produto inserido com sucesso!", Toast.LENGTH_SHORT).show()
                            activity?.finish()
                        }
                        .addOnFailureListener{
                            Toast.makeText(context, "Falha ao inserir o produto!", Toast.LENGTH_SHORT).show()
                            activity?.finish()
                        }
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#E1FF00")))
            ) {
                Text(text = "Inserir Produto", color = Color(android.graphics.Color.parseColor("#1C2000")), fontWeight = FontWeight.Bold)
            }
        }
    }
}