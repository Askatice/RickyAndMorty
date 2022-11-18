package com.askat.rickandmorty.util

import com.askat.rickandmorty.domain.model.CharactersDomain

class ItemClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(id: Int) = clickListener(id)
}
class ItemLongClickListener(val longClickListener: (character: CharactersDomain) -> Unit) {
    fun onLongClick(character: CharactersDomain) = longClickListener(character)
}