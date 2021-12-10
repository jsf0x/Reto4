package com.tgiraldo.retociclo4

data class Cortina (val id: Int, val tipo: String, val color: String, val precio: Int){
    override fun toString(): String {
        return tipo
    }
}