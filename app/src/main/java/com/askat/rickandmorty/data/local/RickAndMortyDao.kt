package com.askat.rickandmorty.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.askat.rickandmorty.domain.model.CharactersDomain
import kotlinx.coroutines.flow.Flow

@Dao
interface RickAndMortyDao {

    @Insert
    suspend fun insertFavoriteCharacter(character: CharactersDomain)

    @Delete
    suspend fun deleteFavoriteCharacter(character: CharactersDomain)

    @Query("SELECT * FROM charactersdomain")
    fun getAllFavoriteCharacters(): Flow<List<CharactersDomain>>
}