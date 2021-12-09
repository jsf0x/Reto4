package com.tgiraldo.retociclo4.room_database

import androidx.room.*

@Database(
    entities = arrayOf(Ventas::class),
    version = 1
)

abstract class VentaDatabase: RoomDatabase() {
    abstract fun ventasDao(): VentasDAO
}