package uz.gita.eventsapplicationgb.navigation

import androidx.navigation.NavDirections

interface Navigator {

    suspend fun navigateTo(direction: NavDirections)

    suspend fun navigationUp()
}