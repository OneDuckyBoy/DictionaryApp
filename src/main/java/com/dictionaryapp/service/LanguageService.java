package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageName;
import org.springframework.stereotype.Service;

@Service
public interface LanguageService {

    public Language GetLanguageFromLanguageName(LanguageName name);
}
