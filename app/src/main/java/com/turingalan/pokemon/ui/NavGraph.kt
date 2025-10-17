package com.turingalan.pokemon.ui

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.turingalan.pokemon.R
import com.turingalan.pokemon.ui.detail.PokemonDetailScreen
import com.turingalan.pokemon.ui.list.PokemonListScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val startDestination = Route.List
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                }
            )
        }
    ) {
        innerPadding ->

            val contentModifier = Modifier.consumeWindowInsets(innerPadding).padding(innerPadding)
            NavHost(
                navController = navController,
                startDestination = startDestination
            )
        {
            composable<Route.List> {
                PokemonListScreen(modifier = contentModifier)

            }

            composable<Route.Detail> {
                // TODO coger los valoresxº
                PokemonDetailScreen(
                    modifier = contentModifier,
                    name = "NAME",
                    artworkId = 1
                )

            }
        }
    }
}

