package com.dictionaryapp.service.impl;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.dictionaryapp.model.dto.AllWordsDTO;
import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final LanguageServiceImpl languageService;
    private final UserServiceImpl userService;

    private final LoggedUser loggedUser;
    public WordServiceImpl(WordRepository wordRepository, LanguageServiceImpl languageService, UserServiceImpl userService, LoggedUser loggedUser) {
        this.wordRepository = wordRepository;
        this.languageService = languageService;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @Override
    public AllWordsDTO getAllWords() {
        AllWordsDTO allWordsDTO = new AllWordsDTO();

        List<Word> words = wordRepository.findAll();

        for (Word word : words) {

            WordDTO wordDTO = new WordDTO();
            wordDTO.setDate(word.getDate());
            wordDTO.setTerm(word.getTerm());
            wordDTO.setExample(word.getExample());
            wordDTO.setTranslation(word.getTranslation());
            wordDTO.setAddedBy(userService.GetUserFromUsername(loggedUser.getUsername()));
            wordDTO.setLanguage(word.getLanguage().getLanguageName());
            wordDTO.setId(word.getId());
            switch (word.getLanguage().getLanguageName()){
                case FRENCH:allWordsDTO.AddToFrench(wordDTO);
                break;
                case GERMAN:allWordsDTO.AddToGerman(wordDTO);
                break;
                case ITALIAN:allWordsDTO.AddToItalian(wordDTO);
                break;
                case SPANISH:allWordsDTO.AddToSpanish(wordDTO);
            }
        }


        return allWordsDTO;
    }

    @Override
    public Word GetWordFromDTO(WordDTO wordDTO) {
        Language language = languageService.GetLanguageFromLanguageName(wordDTO.getLanguage());
        User user = userService.GetUserFromUsername(loggedUser.getUsername());
        Word word = new Word();
        word.setAddedBy(user);
        word.setLanguage(language);
        word.setTerm(wordDTO.getTerm());
        word.setTranslation(wordDTO.getTranslation());
        word.setExample(wordDTO.getExample());

        LocalDate date = LocalDate.parse(wordDTO.getDate().toString());

        word.setDate(date);

        return word;
    }

    @Override
    public void SaveWord(Word word) {
        System.out.println();
        wordRepository.save(word);
    }

    @Override
    public void RemoveWordWithId(long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public void RemoveAllWords() {
        wordRepository.deleteAll();
    }
}
