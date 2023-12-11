package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "words")
@Entity
public class Word extends BaseEntity{



    //•	Has a Term (not null)
    //o	Term length must be between 2 and 40 characters (inclusive of 2 and 40).
    //•	Has a Translation (not null)
    //o	Translation length must be between 2 and 80 characters (inclusive of 2 and 80).
    //•	Has an Example
    //o	Example length must be between 2 and 200 characters (inclusive of 2 and 200).
    //•	Has an inputDate – date (not null)
    //o	The input date must be a date in the past or present.
    //•	Has a Language (not null)
    //o	One word has one language and one language can have many words.
    //•	Has an addedBy
    //o	The user who added the word in the dictionary. One word has one user, but one user may have many words.
    @Column(name = "term")
    @NotNull
    @Size(min = 2,max = 40)
    private String term;
    @Column(name = "translation")
    @NotNull
    @Size(min = 2,max = 80)
    private String translation;
    @Column(name = "example")
    @Size(min = 2,max = 200)
    private String example;

    @PastOrPresent
    @Column(nullable = false,name = "input_date")
    private LocalDate date;


    @ManyToOne()
    @NotNull
    private Language language;

    @ManyToOne()
    private User addedBy;


}
