package com.example.azeites.telaCliente

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.azeites.classes.Cliente
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TelaClienteAtualizar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle? = intent?.extras
        bundle?.apply {
            val cliente: Cliente? = getParcelable("cliente")
            setContent {
                minhaTelaClienteAtualizar(cliente = cliente)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun minhaTelaClienteAtualizar(modifier: Modifier = Modifier, cliente: Cliente?) {

    val referencia = Firebase.database.getReference("/cliente")

    var text_cpf by remember { mutableStateOf(TextFieldValue(cliente!!.cpf)) }
    var text_nome by remember { mutableStateOf(TextFieldValue(cliente!!.nome)) }
    var text_telefone by remember { mutableStateOf(TextFieldValue(cliente!!.telefone)) }
    var text_endereco by remember { mutableStateOf(TextFieldValue(cliente!!.endereco)) }

    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    var carregamento by remember { mutableStateOf(false) }

    if (carregamento) {
        LoadingIndicator()
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(android.graphics.Color.parseColor("#1C2000"))),
        ) {
            Text("  - Atualizar Clientes | Azeites MdF - ",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 20.dp),
                color = Color.White
            )

            TextField(
                value = text_cpf,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                onValueChange = {
                    text_cpf = it
                },
                label = { Text("cpf do cliente..")},
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
                value = text_nome,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                onValueChange = {
                    text_nome = it
                },
                label = { Text("nome do cliente..")},
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
                value = text_telefone,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                onValueChange = {
                    text_telefone = it
                },
                label = { Text("telefone do cliente")},
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
                value = text_endereco,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                onValueChange = {
                    text_endereco = it
                },
                label = { Text("endereço do cliente")},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = (Color(android.graphics.Color.parseColor("#7E8D0C"))).copy(.5f),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = (Color(android.graphics.Color.parseColor("#1C2000"))),
                    focusedLabelColor = (Color(android.graphics.Color.parseColor("#E1FF00"))),
                    cursorColor = (Color(android.graphics.Color.parseColor("#E1FF00")))
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    carregamento = true

                    cliente!!.cpf = text_cpf.text
                    cliente!!.nome = text_nome.text
                    cliente!!.telefone = text_telefone.text
                    cliente!!.endereco = text_endereco.text

                    Log.i("cliente", cliente!!.toString())

                    text_cpf = TextFieldValue("")
                    text_nome = TextFieldValue("")
                    text_telefone = TextFieldValue("")
                    text_endereco = TextFieldValue("")

                    referencia.child(cliente.cpf).setValue(cliente)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Cliente atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                            activity?.finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Falha ao atualizar o cliente!", Toast.LENGTH_SHORT).show()
                            activity?.finish()
                        }
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#E1FF00")))
            ) {
                Text(text = "Atualizar Cliente", color = Color(android.graphics.Color.parseColor("#1C2000")), fontWeight = FontWeight.Bold)
            }
        }
    }
}

