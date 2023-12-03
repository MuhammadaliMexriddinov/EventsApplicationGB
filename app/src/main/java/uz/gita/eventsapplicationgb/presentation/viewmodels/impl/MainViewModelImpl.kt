package uz.gita.eventsapplicationgb.presentation.viewmodels.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.eventsapplicationgb.data.room.entity.EventEntity
import uz.gita.eventsapplicationgb.domain.AppUseCase
import uz.gita.eventsapplicationgb.navigation.Navigator
import uz.gita.eventsapplicationgb.presentation.viewmodels.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val appUseCase: AppUseCase,
    private val navigator: Navigator
) : MainViewModel, ViewModel() {

    override val allEventData = MutableSharedFlow<List<EventEntity>>()


    override fun itemClick(eventEntity: EventEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            appUseCase.updateEventData(eventEntity)
        }
    }

    override fun getAllEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            appUseCase.getAllEvents().collectLatest {
                allEventData.emit(it)
            }
        }
    }

    override fun settingsClicked() {
        //TODO navigate settings
    }
}