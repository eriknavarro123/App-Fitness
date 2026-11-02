# FitnessApp – Pàgina de perfil

App Android (Kotlin + Jetpack Compose) con dos pantallas:

- **ProfileScreen**: consulta los datos del usuario (Laia Soler).
- **EditProfileScreen**: modifica los datos (campos de texto y desplegables).

Los datos se guardan solo en memoria (sin Firebase).

## Cómo abrirlo
1. Descomprime el zip.
2. En Android Studio: **File > Open** y elige la carpeta `FitnessApp`.
3. Espera a que termine el *Gradle Sync* (la primera vez descarga cosas, necesita internet).
4. Pulsa **Run** (▶) con un emulador o un móvil conectado.

Si Android Studio sugiere actualizar Gradle o el plugin de Android (AGP), puedes aceptar.

## Estructura del código
app/src/main/java/com/example/fitnessapp/
- MainActivity.kt        -> datos, navegación entre pantallas
- ProfileScreen.kt       -> pantalla de consulta
- EditProfileScreen.kt   -> pantalla de edición
- ComponentsPerfil.kt    -> Avatar, TargetaDada, SelectorDesplegable

## Check list
1. Abrir la app y ver los datos de Laia Soler.
2. Pulsar Editar y cambiar nombre, peso y objetivo.
3. Pulsar Guardar canvis: los datos nuevos aparecen en el perfil.
4. Volver a editar, cambiar un campo y pulsar Cancel·lar: se mantiene el valor anterior.

## Ver la preview
Abre `Previews.kt` y pulsa **Split** (arriba a la derecha) para ver ProfileScreen y EditProfileScreen sin ejecutar la app.
Si aparece el aviso, pulsa **Build & Refresh**.
