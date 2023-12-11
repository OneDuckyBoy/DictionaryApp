package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enums.LanguageName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Language extends BaseEntity {

    @Column(unique = true,nullable = true,name = "name")
    @Enumerated(EnumType.STRING)
    private LanguageName languageName;

    private String description;

    @OneToMany(mappedBy = "language")
    private List<Word> words;

    public Language(LanguageName languageName) {
        setLanguageName(languageName);
    }


    public void setLanguageName(LanguageName languageName) {
        this.languageName = languageName;
        setDescription(languageName);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void setDescription(LanguageName name){
        switch (name){

            case GERMAN:
                setDescription("A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.");
                break;
            case ITALIAN:
                setDescription("A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.");
                break;
            case SPANISH:
                setDescription("A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.");
                break;
            case FRENCH:
                setDescription("A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.");
                break;
        }
    }


}
