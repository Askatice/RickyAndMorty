package com.askat.rickandmorty.presentation.character.viewmodel.states

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.askat.rickandmorty.domain.model.CharactersDomain
import com.askat.rickandmorty.util.GenderState
import com.askat.rickandmorty.util.StatusState

data class CharacterActivityState(
    val characters: PagingData<CharactersDomain>? = PagingData.empty(),
    val characterIdFromCharacterListFragment: Int = 1,
    val statusState: StatusState = StatusState.NONE,
    val genderState: GenderState = GenderState.NONE,
    val queryCharacterName: MutableLiveData<String> = MutableLiveData(""),
    val isFilter: Boolean = false,
    val favoriteCharacter: List<CharactersDomain> = emptyList(),
    val showToastMessageEvent: Boolean = false,
    val toastMessage: String = "",
    val listType: ListType = ListType.GridLayout

)

enum class ListType() {
    LinearLayout(),
    GridLayout()
}


