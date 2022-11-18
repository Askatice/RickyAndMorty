package com.askat.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.askat.rickandmorty.data.local.RickAndMortyDao
import com.askat.rickandmorty.data.remote.RickyAndMortyApi
import com.askat.rickandmorty.data.remote.dto.character.CharacterData
import com.askat.rickandmorty.paging.CharactersPagingDataSource
import com.askat.rickandmorty.domain.model.CharactersDomain
import com.askat.rickandmorty.domain.repository.RickAndMortyRepository
import com.askat.rickandmorty.util.GenderState
import com.askat.rickandmorty.util.StatusState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickAndMortyImpl @Inject constructor(
    val api: RickyAndMortyApi,
    private val dao: RickAndMortyDao
) : RickAndMortyRepository {


    override suspend fun getAllCharacters(
        status: StatusState,
        gender: GenderState,
        name: String
    ): Flow<PagingData<CharacterData>> {
        return Pager(
            config = PagingConfig(pageSize = 25),
            pagingSourceFactory = {
                CharactersPagingDataSource(
                    api,
                    statusState = status,
                    genderState = gender,
                    nameQuery = name
                )
            }
        ).flow
    }


    override suspend fun getCharacterDetailById(characterId: Int): CharacterData {

        return api.getCharacter(characterId)
    }

    override suspend fun getAllFavoriteCharacters(): Flow<List<CharactersDomain>> {
        return dao.getAllFavoriteCharacters()
    }

    override suspend fun insertMyFavoriteList(character: CharactersDomain) {
        dao.insertFavoriteCharacter(character = character)
    }

    override suspend fun deleteCharacterFromMyFavoriteList(character: CharactersDomain) {
        dao.deleteFavoriteCharacter(character)
    }


}