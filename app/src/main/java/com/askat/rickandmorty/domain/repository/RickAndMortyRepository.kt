package com.askat.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.askat.rickandmorty.data.remote.dto.character.CharacterData
import com.askat.rickandmorty.domain.model.CharactersDomain
import com.askat.rickandmorty.util.GenderState
import com.askat.rickandmorty.util.StatusState
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {

    suspend fun getAllCharacters(
        status: StatusState = StatusState.NONE,
        gender: GenderState = GenderState.NONE,
        name: String = ""
    ): Flow<PagingData<CharacterData>>


    suspend fun getCharacterDetailById(characterId: Int): CharacterData

    suspend fun getAllFavoriteCharacters(): Flow<List<CharactersDomain>>

    suspend fun insertMyFavoriteList(character: CharactersDomain)

    suspend fun deleteCharacterFromMyFavoriteList(character: CharactersDomain)
}