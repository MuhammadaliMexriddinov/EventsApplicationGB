package uz.gita.eventsapplicationgb.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.eventsapplicationgb.repository.AppRepository
import uz.gita.eventsapplicationgb.repository.impl.AppRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository

}