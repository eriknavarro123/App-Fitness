package com.example.fitnessapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// ---------------------------------------------------------------
// PREVIEWS: sirven para ver las pantallas en Android Studio
// sin tener que ejecutar la app en un emulador.
// Una función con @Preview + @Composable aparece en el panel "Design".
// Estas funciones NO forman parte de la app final, solo ayudan al desarrollo.
// ---------------------------------------------------------------

// Datos de ejemplo solo para la preview
private val perfilDeProva = Perfil(
    nom = "Laia Soler",
    correu = "laia@exemple.cat",
    alcada = 165,
    pes = 60,
    objectiu = "Salut",
    activitat = "Moderada"
)

@Preview(showBackground = true, showSystemUi = true, name = "ProfileScreen")
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        Surface {
            ProfileScreen(perfil = perfilDeProva, onEditar = {})
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "EditProfileScreen")
@Composable
fun EditProfileScreenPreview() {
    MaterialTheme {
        Surface {
            EditProfileScreen(perfil = perfilDeProva, onGuardar = {}, onCancelar = {})
        }
    }
}
