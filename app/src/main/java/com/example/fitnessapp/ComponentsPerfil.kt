package com.example.fitnessapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Opciones que se muestran en los menús desplegables
val OBJECTIUS = listOf("Salut", "Guanyar massa muscular", "Perdre pes", "Mantenir el pes")
val ACTIVITATS = listOf("Baixa", "Moderada", "Alta")

// ---------------------------------------------------------------
// AVATAR: un círculo verde claro con un icono de persona dentro.
// (Más adelante se podría cambiar por una foto real.)
// ---------------------------------------------------------------
@Composable
fun Avatar() {
    Box(
        modifier = Modifier
            .size(96.dp)                  // tamaño del círculo
            .clip(CircleShape)            // lo recortamos en forma de círculo
            .background(Color(0xFFD9EFE3)), // color de fondo
        contentAlignment = Alignment.Center // icono centrado
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Foto de perfil",
            tint = Color(0xFF1B8A5A),
            modifier = Modifier.size(56.dp)
        )
    }
}

// ---------------------------------------------------------------
// TARGETA DE DADA: una tarjeta con un título pequeño y un valor.
// Ejemplo: titol = "Pes", valor = "60 kg"
// El "modifier" permite que quien la use decida su tamaño
// (por ejemplo, que ocupe la mitad de la fila).
// ---------------------------------------------------------------
@Composable
fun TargetaDada(titol: String, valor: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F4F2))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = titol, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Text(text = valor, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
    }
}

// ---------------------------------------------------------------
// SELECTOR DESPLEGABLE: un botón que, al pulsarlo, abre una lista
// de opciones para elegir una.
//  - etiqueta: texto pequeño encima (ej. "Objectiu")
//  - opcions: lista de opciones posibles
//  - seleccionada: la opción elegida ahora mismo
//  - onSeleccio: qué hacer cuando el usuario elige una opción
// ---------------------------------------------------------------
@Composable
fun SelectorDesplegable(
    etiqueta: String,
    opcions: List<String>,
    seleccionada: String,
    onSeleccio: (String) -> Unit
) {
    // Estado propio de este componente: ¿el menú está abierto o cerrado?
    var obert by remember { mutableStateOf(false) }

    Column {
        Text(text = etiqueta, style = MaterialTheme.typography.labelMedium, color = Color.Gray)

        // Box permite colocar el menú justo debajo del botón
        Box {
            OutlinedButton(
                onClick = { obert = true },          // al pulsar, abrimos el menú
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = seleccionada, modifier = Modifier.weight(1f))
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }

            DropdownMenu(
                expanded = obert,
                onDismissRequest = { obert = false } // al pulsar fuera, se cierra
            ) {
                // Creamos una fila del menú por cada opción de la lista
                opcions.forEach { opcio ->
                    DropdownMenuItem(
                        text = { Text(opcio) },
                        onClick = {
                            onSeleccio(opcio)        // avisamos de la opción elegida
                            obert = false            // y cerramos el menú
                        }
                    )
                }
            }
        }
    }
}
