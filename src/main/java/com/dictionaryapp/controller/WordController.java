package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.impl.WordServiceImpl;
import com.dictionaryapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/words")
public class WordController {
    private final LoggedUser loggedUser;
    private final WordServiceImpl wordService;


    public WordController(LoggedUser loggedUser, WordServiceImpl wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("/remove/{id}")
    public ModelAndView removeWord(@PathVariable("id") long id){
        if (!loggedUser.isLogged()){
            return new ModelAndView("redirect:/");
        }

        wordService.RemoveWordWithId(id);

        return new ModelAndView("redirect:/");


    }
 @GetMapping("/remove-all")
    public ModelAndView removeAllWords(){
        if (!loggedUser.isLogged()){
            return new ModelAndView("redirect:/");
        }

        wordService.RemoveAllWords();

        return new ModelAndView("redirect:/");


    }

    @GetMapping("add")
    public ModelAndView Add(Model model){
        if (!loggedUser.isLogged()){
            return new ModelAndView("redirect:/");
        }
        if(!model.containsAttribute("WordDTO")){
            model.addAttribute("WordDTO",
                    new WordDTO());
        }
        return new ModelAndView("word-add");
    }

    @PostMapping("/add")
    public ModelAndView Add(@Valid @ModelAttribute("WordDTO") WordDTO wordDTO,
                            BindingResult bindingResult, RedirectAttributes rAtt) {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("WordDTO", wordDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.SomeModel", bindingResult);
            return new ModelAndView("/word-add");
        }

        Word word = wordService.GetWordFromDTO(wordDTO);

        wordService.SaveWord(word);

        return new ModelAndView("redirect:/");

    }


}
