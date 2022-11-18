package com.askat.rickandmorty.data.remote.dto.character

import com.askat.rickandmorty.data.remote.dto.Info


data class CharactersDto(
    val info: Info,
    val results: List<CharacterData>
)

