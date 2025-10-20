package com.turingalan.pokemon.ui.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.turingalan.pokemon.data.model.Pokemon
import com.turingalan.pokemon.data.repository.PokemonRepository
import com.turingalan.pokemon.ui.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class ListUiState(
    val pokemons:List<ItemUiState> = listOf()
)
data class ItemUiState(
    val id:Long,
    val name:String,
    val spriteId:Int,
)

fun Pokemon.asItemUiState(): ItemUiState {
    return ItemUiState(
        id = this.id,
        name = this.name,
        spriteId = this.spriteId
    )
}
fun List<Pokemon>.asListUiState(): ListUiState {

    val items = this.map { it.asItemUiState() }
    return ListUiState(pokemons = items)
}

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PokemonRepository
): ViewModel() {


    private val _uiState: MutableStateFlow<ListUiState> = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState>
        get() = _uiState.asStateFlow()

    init {
        val pokemons = repository.readAll()
        _uiState.value = pokemons.asListUiState().copy()
    }


}