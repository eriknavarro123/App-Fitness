package com.example.fitnessapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ---------------------------------------------------------------
// PROFILESCREEN: solo MUESTRA los datos, no los modifica.
//  - perfil: los datos que hay que mostrar
//  - onEditar: función que se ejecuta al pulsar el botón "Editar"
// ---------------------------------------------------------------
@Composable
fun ProfileScreen(perfil: Perfil, onEditar: () -> Unit) {
    // Column coloca los elementos uno debajo de otro
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // todo centrado
    ) {
        // Foto de perfil (componente reutilizable)
        Avatar()

        Spacer(modifier = Modifier.height(12.dp)) // espacio vacío

        // Nombre y correo
        Text(
            text = perfil.nom,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(text = perfil.correu, color = Color.Gray)

        Spacer(modifier = Modifier.height(24.dp))

        // Row coloca los elementos uno al lado del otro.
        // Con weight(1f) cada tarjeta ocupa la mitad del ancho.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TargetaDada("Alçada", "${perfil.alcada} cm", Modifier.weight(1f))
            TargetaDada("Pes", "${perfil.pes} kg", Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TargetaDada("Objectiu", perfil.objectiu, Modifier.weight(1f))
            TargetaDada("Activitat", perfil.activitat, Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para ir a la pantalla de edición
        Button(onClick = onEditar) {
            Text("Editar")
        }
    }
}
