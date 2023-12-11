package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AllWordsDTO;
import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.model.entity.Word;
import org.springframework.stereotype.Service;

@Service
public interface WordService {

    public AllWordsDTO getAllWords();

    public Word GetWordFromDTO(WordDTO wordDTO);
    public void SaveWord(Word word);
    public void RemoveWordWithId(long id);
    public void RemoveAllWords();
}
