package com.askat.rickandmorty.presentation.character.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.askat.rickandmorty.domain.repository.RickAndMortyRepository
import com.askat.rickandmorty.presentation.character.viewmodel.states.CharacterDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailState())
    val state: StateFlow<CharacterDetailState> get() = _state


    private fun getCharacter(characterID: Int) {

        viewModelScope.launch {
            val data = repository.getCharacterDetailById(characterID)

            _state.value = _state.value.copy(
                character = data
            )



        }

    }

    fun setCharacterId(id: Int) {
        _state.value = _state.value.copy(
            characterIdFromCharacterListFragment = id
        )
    }


    fun getCharacterInvoke() {
        getCharacter(getCharacterIDFromFragmentList())
    }

    fun getCharacterIDFromFragmentList(): Int {
        return _state.value.characterIdFromCharacterListFragment
    }


}

