package com.example.lab_5.L5

import android.app.Application
import androidx.lifecycle.*
import com.example.lab_5.L7.AppDatabase
import com.example.lab_5.L7.CharacterEntity
import com.example.lab_5.L7.CharacterLocalRepository
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val characterDao = AppDatabase.getInstance(application).characterDao()
    private val localRepository = CharacterLocalRepository(characterDao)
    private val remoteRepository = CharacterRepository(RetrofitInstance.api)

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    val currentPage = MutableLiveData<Int>(1)

    init {
        fetchCharactersFromDb()
    }

    private fun fetchCharactersFromDb() {
        viewModelScope.launch {
            localRepository.getAllCharacters().collect { entities ->
                if (entities.isEmpty()) {
                    fetchCharactersFromApi(1) // Автоматическая загрузка из API при пустой БД
                } else {
                    _characters.value = entities.map {
                        Character(
                            id = it.id,
                            name = it.name,
                            status = it.status,
                            species = it.species,
                            gender = it.gender,
                            image = it.image,
                            origin = Location(it.origin),
                            location = Location(it.location)
                        )
                    }
                }
            }
        }
    }

    fun fetchCharactersFromApi(page: Int) {
        viewModelScope.launch {
            try {
                val response = remoteRepository.getCharacters(page)
                val entities = response.results.map {
                    CharacterEntity(
                        id = it.id,
                        name = it.name,
                        status = it.status,
                        species = it.species,
                        gender = it.gender,
                        image = it.image,
                        origin = it.origin.name,
                        location = it.location.name
                    )
                }
                localRepository.saveCharacters(entities)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun refreshCharacters() {
        viewModelScope.launch {
            localRepository.clearDatabase()
            fetchCharactersFromApi(currentPage.value ?: 1)
        }
    }
}


