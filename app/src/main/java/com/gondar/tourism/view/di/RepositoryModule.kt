package com.gondar.tourism.view.di

import com.gondar.tourism.model.api.VehicleApi
import com.gondar.tourism.model.dao.FuelLogDao
import com.gondar.tourism.model.repository.VehicleRepository
import com.gondar.tourism.model.repository.VehicleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideVehicleRepository(api: VehicleApi, dao: FuelLogDao): VehicleRepository {
        return VehicleRepositoryImpl(api, dao)
    }
}