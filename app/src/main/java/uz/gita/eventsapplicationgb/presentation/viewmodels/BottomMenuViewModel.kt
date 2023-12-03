package uz.gita.eventsapplicationgb.presentation.viewmodels

import androidx.lifecycle.LiveData

interface BottomMenuViewModel {

    val supportUsLiveData: LiveData<Unit>

    val shareAppLiveData: LiveData<Unit>

    val aboutUsLiveData: LiveData<Unit>

    fun supportClick()

    fun shareClick()

    fun aboutClick()

}