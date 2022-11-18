package com.askat.rickandmorty.presentation.favorite

import com.askat.rickandmorty.domain.model.CharactersDomain

data class FavoriteState(
    val characterList: List<CharactersDomain> = emptyList(),
    val isError: Boolean = false
)