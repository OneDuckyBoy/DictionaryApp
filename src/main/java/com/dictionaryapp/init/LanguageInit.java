package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageName;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LanguageInit
        implements CommandLineRunner
{

    private final LanguageRepository languageRepository;

    public LanguageInit(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (languageRepository.count()!=0){
            return;
        }
        List<Language> moods = new ArrayList<>();
        for (LanguageName name : LanguageName.values()) {
            moods.add(new Language(name));
        }

        languageRepository.saveAll(moods);

    }
}
