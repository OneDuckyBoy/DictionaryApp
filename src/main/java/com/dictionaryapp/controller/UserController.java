package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public UserController(PasswordEncoder passwordEncoder, LoggedUser loggedUser, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    @GetMapping("login")
    public ModelAndView Login(Model model){
        if (loggedUser.isLogged()){
            return new ModelAndView("redirect:/");
        }
        if(!model.containsAttribute("UserLoginDTO")){
            model.addAttribute("UserLoginDTO",
                    new UserLoginDTO());
        }


        return new ModelAndView("login");

    }

    @PostMapping("/login")
    public ModelAndView Login(@Valid @ModelAttribute("UserLoginDTO")  UserLoginDTO loginDTO,
                              BindingResult bindingResult, RedirectAttributes rAtt){
        if (loggedUser.isLogged()){
            return new ModelAndView("redirect:/");
        }
        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("UserLoginDTO", loginDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.SomeModel", bindingResult);
            return new ModelAndView("/login");
        }
        ModelAndView mv = login(loginDTO);


        return login(loginDTO);
    }

    @GetMapping("/register")
    private ModelAndView Register(Model model){
        if (loggedUser.isLogged()){
            return new ModelAndView("redirect:/");
        }

        if(!model.containsAttribute("UserRegisterDTO")){
            model.addAttribute("UserRegisterDTO",
                    new UserRegisterDTO());
        }

        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView Register(@Valid @ModelAttribute("UserRegisterDTO")  UserRegisterDTO registerDTO,
                                 BindingResult bindingResult, RedirectAttributes rAtt){
        if (loggedUser.isLogged()){
            System.out.println("user logged");
            return new ModelAndView("redirect:/");
        }
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()){
            System.out.println("username present");
            return new ModelAndView("redirect:/");
        }
        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("UserRegisterDTO", registerDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.SomeModel", bindingResult);
            return new ModelAndView("/register");
        }
        System.out.println("Hii");
        String password = registerDTO.getPassword();
        registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        User user = new User(registerDTO.getUsername(), registerDTO.getPassword(),registerDTO.getEmail());
        UserLoginDTO loginDTO = new UserLoginDTO(registerDTO.getUsername(),password);
        userRepository.save(user);

        return new ModelAndView("redirect:/");
    }

    public ModelAndView login(UserLoginDTO loginDTO){
        if (loggedUser.isLogged()){
            return new ModelAndView("redirect:/");
        }


        Optional<User> optionalUser = userRepository.findByUsername(loginDTO.getUsername());
        if (optionalUser.isEmpty()) {
            return new ModelAndView("redirect:/");
        }
        User user = optionalUser.get();
        boolean matches = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());

        if (!matches){
            return new ModelAndView("redirect:/");
        }
        loggedUser.LogIn(loginDTO.getUsername());
        return new ModelAndView("redirect:/");

    }



    @GetMapping("/logout")
    public ModelAndView Logout(){
        loggedUser.Logout();
        return new ModelAndView("redirect:/");
    }
}
