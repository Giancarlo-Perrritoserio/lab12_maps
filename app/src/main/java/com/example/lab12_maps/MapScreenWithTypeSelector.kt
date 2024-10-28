package com.example.lab12_maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreenWithBottomTypeSelector() {
    val ArequipaLocation = LatLng(-16.4040102, -71.559611) // Ubicación inicial en Arequipa, Perú
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(ArequipaLocation, 12f)
    }

    // Estado para el tipo de mapa y visibilidad del menú
    val (mapType, setMapType) = remember { mutableStateOf(MapType.NORMAL) }
    val isDropdownOpen = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(mapType = mapType) // Cambia el tipo de mapa aquí
        )

        // Botón para abrir el menú en la parte inferior
        Button(
            onClick = { isDropdownOpen.value = true },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("Cambiar tipo de mapa")
        }

        // Menú desplegable para seleccionar el tipo de mapa
        DropdownMenu(
            expanded = isDropdownOpen.value,
            onDismissRequest = { isDropdownOpen.value = false },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            DropdownMenuItem(onClick = {
                setMapType(MapType.NORMAL)
                isDropdownOpen.value = false
            }) {
                Text("Normal")
            }
            DropdownMenuItem(onClick = {
                setMapType(MapType.HYBRID)
                isDropdownOpen.value = false
            }) {
                Text("Hybrid")
            }
            DropdownMenuItem(onClick = {
                setMapType(MapType.SATELLITE)
                isDropdownOpen.value = false
            }) {
                Text("Satellite")
            }
            DropdownMenuItem(onClick = {
                setMapType(MapType.TERRAIN)
                isDropdownOpen.value = false
            }) {
                Text("Terrain")
            }
        }
    }
}
