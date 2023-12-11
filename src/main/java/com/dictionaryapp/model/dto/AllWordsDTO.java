package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.Word;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AllWordsDTO {
    private List<WordDTO> german;
    private List<WordDTO> french;
    private List<WordDTO> spanish;
    private List<WordDTO> italian;

    public void AddToGerman(WordDTO word){
        german.add(word);
    }
    public void AddToFrench(WordDTO word){
        french.add(word);
    }
    public void AddToSpanish(WordDTO word){
        spanish.add(word);
    }
    public void AddToItalian(WordDTO word){
        italian.add(word);
    }
    public int allWordsCount(){
        return german.size()+ french.size()+ italian.size()+ spanish.size();
    }

    public AllWordsDTO(List<WordDTO> german, List<WordDTO> french, List<WordDTO> spanish, List<WordDTO> italian) {
        this.german = german;
        this.french = french;
        this.spanish = spanish;
        this.italian = italian;
    }
    public AllWordsDTO() {
        this.german = new ArrayList<>();
        this.french = new ArrayList<>();
        this.spanish = new ArrayList<>();
        this.italian = new ArrayList<>();
    }
}
