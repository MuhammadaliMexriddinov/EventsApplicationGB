package uz.gita.eventsapplicationgb.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.eventsapplicationgb.data.room.entity.EventEntity

interface AppRepository {

    suspend fun insertEventData(eventEntity: EventEntity)

    suspend fun updateEventData(eventEntity: EventEntity)

    fun getAllEvents(): Flow<List<EventEntity>>
}