package com.example.rickandmortyapp.data.local.room

import android.content.Context
import androidx.room.Room
import com.example.rickandmortyapp.data.local.entities.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HelperDataBase {
        return Room.databaseBuilder(
            context,
            HelperDataBase::class.java,
            HelperDataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(helperDataBase: HelperDataBase): CharacterDao {
        return helperDataBase.characterDao()
    }

}