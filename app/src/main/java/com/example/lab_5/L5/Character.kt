package com.example.lab_5.L5

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val results: List<Character>
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: Location,
    val location: Location
)

@Serializable
data class Location(
    val name: String
)
