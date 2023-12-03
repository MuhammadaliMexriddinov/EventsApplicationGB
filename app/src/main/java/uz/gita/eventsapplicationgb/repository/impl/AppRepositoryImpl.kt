package uz.gita.eventsapplicationgb.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.eventsapplicationgb.data.room.dao.EventDao
import uz.gita.eventsapplicationgb.data.room.entity.EventEntity
import uz.gita.eventsapplicationgb.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val eventDao: EventDao
): AppRepository {

    override suspend fun insertEventData(eventEntity: EventEntity) = eventDao.insertEvent(eventEntity)

    override suspend fun updateEventData(eventEntity: EventEntity) = eventDao.updateEvent(eventEntity)

    override fun getAllEvents(): Flow<List<EventEntity>> = eventDao.getAllEvents()
}