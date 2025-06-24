package com.example.dictionaryapp.presention

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.app.domain.repository.DictionaryRepository
import com.example.dictionaryapp.app.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository,
) : ViewModel() {
    //createStateflow
    private val _mainState =
        MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()
    private val searchJob: Job? = null
    fun onEvent(mainUiEvents: MainUiEvents) {
        when (mainUiEvents) {
            MainUiEvents.OnSearchClick -> {
                loadWordResult()
            }

            is MainUiEvents.OnSearchWordChange -> {
                _mainState.update { word ->
                    word.copy(
                        searchWord = mainUiEvents.newWord.lowercase().toString()
                    )
                }
            }
        }
    }

    private fun loadWordResult() {
        viewModelScope.launch {
            dictionaryRepository.getWordResult(
                mainState.value.searchWord
            ).collect { result ->
                when (result) {
                    is Result.Error<*> -> Unit
                    is Result.Loading<*> -> {
                        _mainState.update {
                            it.copy(
                                isLoading = result.isLoading
                            )
                        }
                    }

                    is Result.Success<*> -> {
                        result.data?.let { wordItem ->
                            _mainState.update {
                                it.copy(
                                    wordItem = wordItem

                                )
                            }
                        }
                    }
                }
            }
        }
    }

}