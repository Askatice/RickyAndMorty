package com.askat.rickandmorty.data.remote.dto.character

import com.askat.rickandmorty.domain.model.CharacterDomain

data class CharacterDto(
    val result: CharacterData
)
fun CharacterDto.toCharacter(): CharacterDomain {
    return CharacterDomain(
        result = result
    )
}