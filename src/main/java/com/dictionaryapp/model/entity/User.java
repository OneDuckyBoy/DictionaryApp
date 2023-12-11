package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity{
    //•	Has a Username (unique, not null)
    //o	Username length must be between 3 and 20 characters (inclusive of 3 and 20).
    //•	Has a Password (not null)
    //o	Password length must be between 3 and 20 characters (inclusive of 3 and 20).
    //•	Has an Email (unique, not null)
    //o	Must contain '@'.
    //•	Has addedWords
    //o	The addedWords is a collection that contains all words that the user added. One user may have many word and one word can be added by only one user.

    @Column(unique = true,nullable = false)
    @Length(min =3,max = 20)
    private String username;
    @Column(nullable = false,columnDefinition = "text")
    @Length(max =60)
    private String password;
    @Email
    @Column(unique = true,nullable = false)
    private String email;
  @OneToMany(mappedBy = "addedBy",fetch = FetchType.EAGER)
    private Set<Word>addedWords;

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
