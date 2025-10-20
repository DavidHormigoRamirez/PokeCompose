package com.turingalan.pokemon.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel



@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit = {},
    viewModel: PokemonListViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ){
        items(
            items=state.pokemons,
            key = {
                item -> item.id
            }
        ) {
            pokemon ->
            PokemonCard(
                modifier = Modifier.fillMaxSize()
                    .clickable(
                        enabled = true,
                        onClick = {
                            onClick(pokemon.id)
                        }
                    ),
                name = pokemon.name,
                spriteId = pokemon.spriteId,
            )

        }
    }

}

@Composable
fun PokemonCard(
    modifier:Modifier = Modifier,
    name:String,
    spriteId:Int,
)
{
    Card(modifier = modifier) {
        Column {
            Image(painterResource(spriteId),name)
            Text(
                text=name,
                style = MaterialTheme.typography.headlineSmall
                )
        }

    }

}