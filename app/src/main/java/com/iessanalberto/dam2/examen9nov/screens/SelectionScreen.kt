package com.iessanalberto.dam2.examen9nov.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iessanalberto.dam2.examen9nov.R
import com.iessanalberto.dam2.examen9nov.data.Aula
import com.iessanalberto.dam2.examen9nov.data.listadoAulas
import com.iessanalberto.dam2.examen9nov.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreen(navController: NavController) {
    var filtroTexto by rememberSaveable { mutableStateOf("") }
    var filtroAulas by rememberSaveable { mutableStateOf(listadoAulas) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Reservas") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "atras"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            OutlinedTextField(
                value = filtroTexto,
                onValueChange = { filtroTexto = it },
                label = { Text("filtro nombre aula") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Button(
                onClick = { filtroAulas = filterAulas(filtroTexto) },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(16.dp)
            ) {
                Text("Filtrar")
            }

            SelectionScreenBodyContent(
                modifier = Modifier.padding(paddingValues),
                aulas = filtroAulas
            )
        }
    }
}

@Composable
fun SelectionScreenBodyContent(modifier: Modifier, aulas: List<Aula>) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(aulas) { aula ->
            CardRecurso(aula = aula)
        }
    }
}

fun filterAulas(filtroTexto: String): List<Aula> {
    return listadoAulas.filter { aula ->
        aula.name.contains(filtroTexto, ignoreCase = true)
    }
}

@Composable
fun CardRecurso(aula: Aula) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.reserva_aulas),
                contentDescription = "Aulas",
                modifier = Modifier.size(48.dp)
            )
            Column {
                Text(text = aula.name)
                Text(text = aula.description)
            }
        }
    }
}
