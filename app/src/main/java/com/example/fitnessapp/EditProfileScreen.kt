package com.example.fitnessapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

// ---------------------------------------------------------------
// EDITPROFILESCREEN: permite modificar los datos.
//  - perfil: los datos actuales (para rellenar los campos al empezar)
//  - onGuardar: se ejecuta al pulsar "Guardar canvis" y recibe el perfil nuevo
//  - onCancelar: se ejecuta al pulsar "Cancel·lar"
//
// IDEA CLAVE: cada campo trabaja con una COPIA temporal de los datos.
// Si guardamos, enviamos la copia. Si cancelamos, la copia se descarta
// y los datos originales siguen intactos.
// ---------------------------------------------------------------
@Composable
fun EditProfileScreen(
    perfil: Perfil,
    onGuardar: (Perfil) -> Unit,
    onCancelar: () -> Unit
) {
    // Estado temporal de cada campo, inicializado con el valor actual.
    // Los números los guardamos como texto porque un campo de texto trabaja con String.
    var nom by remember { mutableStateOf(perfil.nom) }
    var correu by remember { mutableStateOf(perfil.correu) }
    var alcada by remember { mutableStateOf(perfil.alcada.toString()) }
    var pes by remember { mutableStateOf(perfil.pes.toString()) }
    var objectiu by remember { mutableStateOf(perfil.objectiu) }
    var activitat by remember { mutableStateOf(perfil.activitat) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // permite hacer scroll si no cabe todo
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp) // espacio entre elementos
    ) {
        Text(
            text = "Editar perfil",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        // Campo de texto para el nombre.
        // value = lo que se muestra; onValueChange = qué hacer cuando el usuario escribe.
        OutlinedTextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = correu,
            onValueChange = { correu = it },
            label = { Text("Correu") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), // teclado de email
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = alcada,
            onValueChange = { alcada = it },
            label = { Text("Alçada (cm)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // teclado numérico
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = pes,
            onValueChange = { pes = it },
            label = { Text("Pes (kg)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Menús desplegables (componente reutilizable de ComponentsPerfil.kt)
        SelectorDesplegable(
            etiqueta = "Objectiu",
            opcions = OBJECTIUS,
            seleccionada = objectiu,
            onSeleccio = { objectiu = it }
        )

        SelectorDesplegable(
            etiqueta = "Nivell d'activitat",
            opcions = ACTIVITATS,
            seleccionada = activitat,
            onSeleccio = { activitat = it }
        )

        // GUARDAR: creamos un perfil nuevo con .copy() y se lo enviamos a quien nos llamó.
        Button(
            onClick = {
                onGuardar(
                    perfil.copy(
                        nom = nom,
                        correu = correu,
                        // toIntOrNull() convierte el texto a número.
                        // Si el campo está vacío o no es un número, usamos el valor anterior (?:)
                        alcada = alcada.toIntOrNull() ?: perfil.alcada,
                        pes = pes.toIntOrNull() ?: perfil.pes,
                        objectiu = objectiu,
                        activitat = activitat
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar canvis")
        }

        // CANCELAR: no enviamos nada, simplemente volvemos atrás.
        OutlinedButton(
            onClick = onCancelar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel·lar")
        }
    }
}
