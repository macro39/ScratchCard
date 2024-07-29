package com.macek.scratchcard.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @ApplicationScope
    @Provides
    fun providesCoroutineScope(
    ): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    @Provides
    @Singleton
    fun provideScratchCardRepository(
        @ApplicationScope coroutineScope: CoroutineScope
    ): ScratchCardRepository = ScratchCardRepositoryImpl(coroutineScope)
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope