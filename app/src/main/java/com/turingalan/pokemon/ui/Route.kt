package com.turingalan.pokemon.ui

import kotlinx.serialization.Serializable

@Serializable
sealed class Route(val route:String) {
    @Serializable
    data object List:Route("pokemon_list")
    @Serializable
    data class Detail(val id:Int):Route(route = "pokemon_detail[$id]")
}