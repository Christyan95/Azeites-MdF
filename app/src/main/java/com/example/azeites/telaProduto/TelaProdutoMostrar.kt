package com.example.azeites.telaProduto

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.azeites.classes.Produto
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class TelaProdutoMostrar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            minhaTelaProdutoMostrar()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun minhaTelaProdutoMostrar(modifier: Modifier = Modifier) {

    lateinit var referencia: DatabaseReference

    referencia = Firebase.database.getReference("/produto")

    var carregamento by remember{ mutableStateOf(true)}
    val produtosState = remember { mutableStateListOf<Produto>() }
    val listState = rememberLazyListState()

    if(carregamento){
        LoadingIndicator()
        LaunchedEffect(Unit) {
            val snapshot = withContext(Dispatchers.IO) {
                referencia.get().await() // Obtém uma única vez os dados do banco de dados
            }

            if (snapshot.exists()) {
                val gson = Gson()
                for (i in snapshot.children) {
                    val json = gson.toJson(i.value)
                    val produto = gson.fromJson(json, Produto::class.java)
                    produtosState.add(
                        Produto(
                            produto.id_produto,
                            produto.descricao,
                            produto.valor
                        )
                    )
                    carregamento = false
                }
                Log.i("listaBanco", produtosState.toString())
            }
        }
    }
    else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(android.graphics.Color.parseColor("#1C2000")))
        ) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {

                items(produtosState) { produto ->
                    Log.i("Produto Mostrar: ", produto.toString())
                    CardProduto(produto)
                }
            }
        }
    }
}

@Composable
fun CardProduto(produto: Produto) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(android.graphics.Color.parseColor("#7E8D0C"))),
        ){
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "ID: "+produto.id_produto, fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor("#1C2000")))
                Text(text =  "Descrição: "+produto.descricao, fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor("#1C2000")))
                Text(text =  "Valor: "+produto.valor, fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor("#1C2000")))
            }

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                MenuTresPontos(produto)
            }
        }
    }
}

@Composable
fun ConfimationScreen(produto: Produto,showDialog: MutableState<Boolean>) {

    val referencia = Firebase.database.getReference("/produto")
    val activity = (LocalContext.current as? Activity)

    AlertDialog(
        containerColor = Color((android.graphics.Color.parseColor("#1C2000"))),
        onDismissRequest = { },
        title = { Text("Confirmação", color = Color.White) },
        text = { Text("Você deseja confirmar esta ação?", color = Color.White) },
        confirmButton = {
            Button(
                onClick = {
                    referencia.child(produto.id_produto).removeValue()
                    activity?.finish()
                    Toast.makeText(activity, "Produto excluido com sucesso!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#E1FF00")))
            ) {
                Text("Confirmar", color = Color((android.graphics.Color.parseColor("#1C2000"))))
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    showDialog.value = false
                    return@Button
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#E1FF00")))
            ) {
                Text("Cancelar", color = Color((android.graphics.Color.parseColor("#1C2000"))))
            }
        }
    )
}

@Composable
fun MenuTresPontos(produto: Produto){

    val contexto = LocalContext.current
    var isOpened by remember{mutableStateOf(false)}
    val activity = (LocalContext.current as? Activity)
    val showDialog = remember{mutableStateOf(false)}

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    ){
        IconButton(onClick = { isOpened = !isOpened}) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More vert"
            )
        }
        DropdownMenu(
            expanded = isOpened,
            onDismissRequest = {isOpened = false},
            modifier = Modifier
                .background(Color(android.graphics.Color.parseColor("#1C2000")))
        ) {
            DropdownMenuItem(
                text = {Text("Editar", color = Color.White)},
                onClick = {
                    val bundle = Bundle().apply {
                        putParcelable("produto",produto)
                    }
                    contexto.startActivity(Intent(contexto,TelaProdutoAtualizar::class.java).putExtras(bundle))
                    activity?.finish()
                    isOpened = !isOpened
                }
            )

            DropdownMenuItem(
                onClick = {
                    showDialog.value = true
                    isOpened = !isOpened
                },
                text = {Text("Excluir", color = Color.White)}
            )

        }
    }

    if(showDialog.value){
        ConfimationScreen(produto,showDialog)
    }

}

@Composable
fun LoadingIndicator(){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
        )
    }
}