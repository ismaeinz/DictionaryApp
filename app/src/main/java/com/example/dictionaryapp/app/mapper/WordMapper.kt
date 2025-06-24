package com.example.dictionaryapp.app.mapper

import com.example.dictionaryapp.app.data.dto.DefinitionDto
import com.example.dictionaryapp.app.data.dto.MeaningDto
import com.example.dictionaryapp.app.data.dto.WordItemDto
import com.example.dictionaryapp.app.domain.model.Definition
import com.example.dictionaryapp.app.domain.model.Meaning
import com.example.dictionaryapp.app.domain.model.WordItem


fun WordItemDto.toWordItem() = WordItem(
    word = word ?: "",
    meanings = meanings?.map {
        it.toMeaning()
    } ?: emptyList(),
    phonetic = phonetic ?: ""
)

fun MeaningDto.toMeaning() = Meaning(
    definition = definitionDtoToDefinition(
        definitions?.get(0)
    ),
    partOfSpeach = partOfSpeech ?: "",
)


fun definitionDtoToDefinition(
    definitionDto: DefinitionDto?,
) = Definition(
    definition = definitionDto?.definition ?: "",
    example = definitionDto?.example ?: ""
)