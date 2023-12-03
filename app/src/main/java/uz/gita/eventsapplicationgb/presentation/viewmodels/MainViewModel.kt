package uz.gita.eventsapplicationgb.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.eventsapplicationgb.data.room.entity.EventEntity

interface MainViewModel {

    val allEventData: SharedFlow<List<EventEntity>>

    fun itemClick(eventEntity: EventEntity)

    fun getAllEvents()

    fun settingsClicked()

}