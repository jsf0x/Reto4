package com.tgiraldo.retociclo4

data class Venta (val id: Int, val venta: String, val descripcion: String, val precio: String){
    override fun toString(): String {
        return venta
    }
}