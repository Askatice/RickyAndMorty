package com.askat.rickandmorty.presentation.character.viewmodel.states

import com.askat.rickandmorty.data.remote.dto.character.CharacterData

data class CharacterDetailState(
    val character: CharacterData? = null,
    val characterIdFromCharacterListFragment: Int = 1,
)