package uz.gita.eventsapplicationgb.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.eventsapplicationgb.data.room.entity.EventEntity
import uz.gita.eventsapplicationgb.domain.AppUseCase
import uz.gita.eventsapplicationgb.repository.AppRepository
import javax.inject.Inject

class AppUseCaseImpl @Inject constructor(private val repository: AppRepository) : AppUseCase {

    override suspend fun insertEventData(eventEntity: EventEntity) = repository.insertEventData(eventEntity)

    override suspend fun updateEventData(eventEntity: EventEntity) = repository.updateEventData(eventEntity)

    override fun  getAllEvents() : Flow<List<EventEntity>> = repository.getAllEvents()

}