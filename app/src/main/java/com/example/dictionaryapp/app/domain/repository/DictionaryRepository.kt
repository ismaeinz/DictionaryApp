package com.example.dictionaryapp.app.domain.repository

import com.example.dictionaryapp.app.domain.model.WordItem
import com.example.dictionaryapp.app.util.Result
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {
    suspend fun getWordResult(
        word: String,
    ): Flow<Result<WordItem>>
}