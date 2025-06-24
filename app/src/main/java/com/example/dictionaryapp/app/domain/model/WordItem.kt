package com.example.dictionaryapp.app.domain.model

data class WordItem(
    var word: String,
    var meanings: List<Meaning>,
    var phonetic: String,
)