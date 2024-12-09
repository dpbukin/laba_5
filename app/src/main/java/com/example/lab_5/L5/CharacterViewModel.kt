package com.example.lab_5.L5

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    private val repository = CharacterRepository(RetrofitInstance.api)

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

//    отсеживание текущей страницы
    private val _currentPage = MutableLiveData<Int>().apply { value = 1 }
    val currentPage: LiveData<Int> get() = _currentPage

    fun fetchCharacters(page: Int = 1) {
        viewModelScope.launch {
            try {
                val response = repository.getCharacters(page)
                _characters.postValue(response.results)
                _currentPage.postValue(page)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

    fun loadNextPage() {
        val nextPage = (_currentPage.value ?: 1) + 1
        fetchCharacters(nextPage)
    }

    fun loadPreviousPage() {
        val current = _currentPage.value ?: 1
        if (current > 1) {
            fetchCharacters(current - 1)
        }
    }
}

