package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.enums.LanguageName;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter

public class WordDTO {
    @Size(min = 2,max = 40,message = "The term length must be between 2 and 40 characters!")
    private String term;
    @Size(min = 2,max = 80,message = "The translation length must be between 2 and 80 characters!")
    private String translation;
    @Size(min = 2,max = 200,message = "The example length must be between 2 and 200 characters!")
    private String example;

    @PastOrPresent(message = "The input date must be in the past or the present!")
    @NotNull(message = "The input date must be in the past or the present!")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;


    @NotNull(message = "You must select a language!")

    private LanguageName language;


    private User addedBy;

    private long id;
}
