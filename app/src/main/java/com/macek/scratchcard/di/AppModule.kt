package com.macek.scratchcard.di

import com.macek.scratchcard.data.remote.CardApi
import com.macek.scratchcard.repository.ScratchCardRepository
import com.macek.scratchcard.repository.ScratchCardRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun provideCardApi(): CardApi {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl("https://api.o2.sk/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CardApi::class.java)
    }

    @Provides
    @Singleton
    fun provideScratchCardRepository(
        @ApplicationScope coroutineScope: CoroutineScope,
        cardApi: CardApi,
    ): ScratchCardRepository = ScratchCardRepositoryImpl(coroutineScope, cardApi)
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope