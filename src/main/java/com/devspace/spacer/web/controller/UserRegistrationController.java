package com.devspace.spacer.web.controller;

import com.devspace.spacer.model.User;
import com.devspace.spacer.service.UserService;
import com.devspace.spacer.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    /**
     *
     * @param userRegistrationDto Data transfer object for user registration.
     * @param bindingResult
     * @return
     */
    @PostMapping
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto,
            BindingResult bindingResult) {
        User existing = userService.findByEmail(userRegistrationDto.getEmail());
        if(existing != null)
            bindingResult.rejectValue("email", null, "В системе есть зарегистрированный аккаунт с указанным email.");
        if(bindingResult.hasErrors())
            return "registration";
        userService.save(userRegistrationDto);

        return "redirect:/registration?success";
    }
}
