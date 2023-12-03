package uz.gita.eventsapplicationgb.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.eventsapplicationgb.domain.AppUseCase
import uz.gita.eventsapplicationgb.domain.impl.AppUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindAppUseCase(appUseCaseImpl: AppUseCaseImpl): AppUseCase

}