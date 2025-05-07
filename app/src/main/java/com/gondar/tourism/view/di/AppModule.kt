package com.gondar.tourism.view.di

import android.content.Context
import androidx.room.Room
import com.gondar.tourism.model.AppDatabase
import com.gondar.tourism.model.api.VehicleApi
import com.gondar.tourism.model.dao.FuelLogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://retoolapi.dev/wnqA5Y/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit): VehicleApi = retrofit.create(VehicleApi::class.java)

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "tourfleet_db").build()

    @Provides
    fun provideFuelLogDao(db: AppDatabase): FuelLogDao = db.fuelLogDao()
}