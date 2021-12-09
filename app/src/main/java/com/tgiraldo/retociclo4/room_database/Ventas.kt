package com.tgiraldo.retociclo4.room_database

import androidx.room.*

@Entity
data class Ventas(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val venta: String,
    val descripcion: String,
    val precio: String,
    val fechaVenta: String,
    val cedulaVendedor: String,
    val nombreCliente: String,
    val direccion: String,
    val latitud: String,
    val longitud: String,
    val tipoCortina: String,
    val ancho: String,
    val alto: String,
    val cuotas: String,
//    val saldo: String,
)