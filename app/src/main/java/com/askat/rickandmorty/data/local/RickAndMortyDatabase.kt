package com.askat.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.askat.rickandmorty.domain.model.CharactersDomain

@Database(entities = [CharactersDomain::class], version = 1)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract val rickMortyDao: RickAndMortyDao
}