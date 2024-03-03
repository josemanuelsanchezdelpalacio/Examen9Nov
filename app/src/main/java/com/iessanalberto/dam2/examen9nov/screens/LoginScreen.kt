package com.iessanalberto.dam2.examen9nov.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iessanalberto.dam2.examen9nov.MainActivity
import com.iessanalberto.dam2.examen9nov.R
import com.iessanalberto.dam2.examen9nov.navigation.AppScreens
import kotlin.system.exitProcess

@Composable
fun LoginScreen(navController: NavController) {
    var usuario by rememberSaveable { mutableStateOf("") }
    var contraseña by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    var numIntentos by remember { mutableStateOf(3) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_ies),
            contentDescription = "Logo instituto",
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(30.dp)),
            contentScale = ContentScale.Crop
        )
        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text(text = "Introduce nombre usuario") }
        )
        OutlinedTextField(
            value = contraseña,
            onValueChange = { contraseña = it },
            label = { Text(text = "Introduce contraseña") }
        )

        Button(onClick = {
            // Validar usuario y contraseña
            if (usuario == "admin" && contraseña == "admin") {
                navController.navigate(AppScreens.SelectionScreen.route)
                // Reiniciar número de intentos si se logra el inicio de sesión
                numIntentos = 3
            } else {
                // Reducir número de intentos si la combinación es incorrecta
                numIntentos--
                if (numIntentos == 0) {
                    // Mostrar mensaje y cerrar la aplicación si se superan los intentos
                    Toast.makeText(context, "El número de intentos ha sido superado", Toast.LENGTH_SHORT).show()
                    // Cerrar la aplicación
                    navController.popBackStack()
                } else {
                    // Mostrar mensaje de error si la combinación es incorrecta pero quedan intentos
                    Toast.makeText(context, "Error en el usuario o contraseña", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text(text = "Entrar")
        }
    }
}