package com.example.app2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavegacion()
        }
    }
}

@Composable
fun AppNavegacion() {
    // Controla la navegación entre pantallas
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "contador"
    ) {
        // Primera pantalla
        composable("contador") {
            PantallaContador(navController)
        }
        // Segunda pantalla con parámetro
        composable("resultado/{valor}") { backStackEntry ->
            val valor = backStackEntry.arguments?.getString("valor") ?:
            "0"
            PantallaResultado(valor)
        }
    }
}
@Composable
fun PantallaContador(navController: NavController) {
    // Estado que se actualiza en pantalla
    var contador by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Contador: $contador", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { contador++ }) {
            Text("Sumar")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            // Envía el valor a la otra pantalla
            navController.navigate("resultado/$contador")
        }) {
            Text("Ver Resultado")
        }
    }
}
@Composable
fun PantallaResultado(valor: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Valor final: $valor", fontSize = 28.sp)
    }
}

