package com.example.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

// ---------------------------------------------------------------
// 1) MODELO DE DATOS
// Una "data class" sirve para agrupar todos los datos del usuario.
// Usamos "val" (no se puede cambiar): para modificar algo creamos
// una copia nueva con .copy(...)
// ---------------------------------------------------------------
data class Perfil(
    val nom: String,
    val correu: String,
    val alcada: Int,      // en cm
    val pes: Int,         // en kg
    val objectiu: String,
    val activitat: String
)

// ---------------------------------------------------------------
// 2) ACTIVIDAD: es el punto de entrada de la app Android.
// Aquí solo "enchufamos" nuestra app de Compose.
// ---------------------------------------------------------------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Si tu proyecto ya tiene un tema (p. ej. FitnessAppTheme), puedes usarlo aquí
            MaterialTheme {
                // safeDrawingPadding evita que el contenido quede debajo de la barra de estado
                Surface(modifier = Modifier.fillMaxSize().safeDrawingPadding()) {
                    FitnessApp()
                }
            }
        }
    }
}

// ---------------------------------------------------------------
// 3) FitnessApp: guarda los datos y decide qué pantalla se muestra.
// ---------------------------------------------------------------
@Composable
fun FitnessApp() {
    // "Remember" hace que Compose recuerde el valor aunque la pantalla se redibuje.
    // "mutableStateOf" hace que, cuando el valor cambia, la pantalla se actualice sola.
    // Los datos viven AQUÍ (y no en cada pantalla) para que las dos pantallas los compartan.
    var perfil by remember {
        mutableStateOf(
            Perfil(
                nom = "Laia Soler",
                correu = "laia@exemple.cat",
                alcada = 165,
                pes = 60,
                objectiu = "Salut",
                activitat = "Moderada"
            )
        )
    }

    // Navegación sencilla: una variable que dice en qué pantalla estamos.
    // "Perfil" = ProfileScreen, "editar" = EditProfileScreen
    var pantalla by remember { mutableStateOf("perfil") }

    // Según el valor de "pantalla", mostramos una u otra.
    when (pantalla) {
        "perfil" -> ProfileScreen(
            perfil = perfil,
            onEditar = { pantalla = "editar" }          // botón Editar -> vamos a editar
        )

        "editar" -> EditProfileScreen(
            perfil = perfil,
            onGuardar = { perfilNou ->
                perfil = perfilNou                      // guardamos los datos nuevos
                pantalla = "perfil"                     // y volvemos al perfil
            },
            onCancelar = { pantalla = "perfil" }        // volvemos SIN cambiar los datos
        )
    }
}
