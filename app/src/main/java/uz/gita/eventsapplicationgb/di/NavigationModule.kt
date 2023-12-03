package uz.gita.eventsapplicationgb.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.eventsapplicationgb.navigation.NavigationDispatcher
import uz.gita.eventsapplicationgb.navigation.NavigationHandler
import uz.gita.eventsapplicationgb.navigation.Navigator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @[Binds Singleton]
    fun bindNavigator(navigationDispatcher: NavigationDispatcher): Navigator

    @[Binds Singleton]
    fun bindNavigationHandler(navigationDispatcher: NavigationDispatcher): NavigationHandler


}