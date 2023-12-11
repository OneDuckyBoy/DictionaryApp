package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AllWordsDTO;
import com.dictionaryapp.service.impl.WordServiceImpl;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final WordServiceImpl wordService;

    public HomeController(LoggedUser loggedUser,WordServiceImpl wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public ModelAndView Home(){
        if (!loggedUser.isLogged()){
            return new ModelAndView("index");
        }
        AllWordsDTO allWordsDTO= wordService.getAllWords();
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("AllWordsDTO",allWordsDTO);
        return mv;

    }
}
