package com.turingalan.pokemon.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.turingalan.pokemon.data.repository.PokemonRepository
import com.turingalan.pokemon.ui.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class DetailUiState(
    val name:String = "",
    val artwork:Int = -1,
)
@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val pokemonRepository: PokemonRepository

): ViewModel() {

    private val _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(
        DetailUiState()
    )
    val uiState: StateFlow<DetailUiState>
        get() = _uiState.asStateFlow()

    init {
        val route = savedStateHandle.toRoute<Route.Detail>()
        val pokemonId = route.id
        val pokemon = pokemonRepository.readOne(pokemonId)
        pokemon?.let {
            _uiState.value = DetailUiState(
                name = pokemon.name,
                artwork = pokemon.artworkId,
            )
        }
    }
}