package com.tgiraldo.retociclo4.room_database

import androidx.room.*
import javax.annotation.Nonnull;

@Dao
interface VentasDAO {
   @Query("SELECT * FROM Ventas")
    suspend fun getAllTasks(): List<Ventas>

    @Query("SELECT * FROM Ventas WHERE id = :id")
    suspend fun findById(id: Int): Ventas

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarVenta(task: Ventas): Long

    @Update
    suspend fun updateVenta(task: Ventas)

    @Delete
    suspend fun deleteVenta(task: Ventas)
}