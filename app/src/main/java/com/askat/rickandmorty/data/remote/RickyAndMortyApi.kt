package com.askat.rickandmorty.data.remote

import com.askat.rickandmorty.data.remote.dto.character.CharacterData
import com.askat.rickandmorty.data.remote.dto.character.CharactersDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickyAndMortyApi {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("status") status: String="",
        @Query("gender") gender: String="",
        @Query("name") name: String="",
        @Query("page") page: Int? = null
    ): CharactersDto


    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterData

}