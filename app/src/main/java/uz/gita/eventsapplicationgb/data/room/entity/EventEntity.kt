package uz.gita.eventsapplicationgb.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
Mobile Developer
Creator:Mekhriddinov Muhammadali
 */
@Entity(tableName = "event_data")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val status: Int = 0
)
