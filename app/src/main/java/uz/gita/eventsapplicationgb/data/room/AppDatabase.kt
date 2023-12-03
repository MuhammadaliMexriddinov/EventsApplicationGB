package uz.gita.eventsapplicationgb.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.eventsapplicationgb.data.room.dao.EventDao
import uz.gita.eventsapplicationgb.data.room.entity.EventEntity


/**
Mobile Developer
Creator:Mekhriddinov Muhammadali
 */
@Database(entities = [EventEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}