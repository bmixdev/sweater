package ru.mbelin.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.mbelin.sweater.bean.BeanKeys;
import ru.mbelin.sweater.controller.dto.CaptchaResponseDto;
import ru.mbelin.sweater.domain.User;
import ru.mbelin.sweater.sevice.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    //private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService userService;

    @Autowired
    private BeanKeys beanKeys;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("g-recaptcha-response") String captchaResponce,
            @Valid User user,
            BindingResult bindingResult,
            Model model) {
        String url = String.format(beanKeys.getGoogleCaptchaURL(), beanKeys.getGoogleCaptchaSecret(), captchaResponce);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.EMPTY_LIST, CaptchaResponseDto.class);
        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Необходимо заполнить капчу!\n"+response.getErrorCode().toString());
        }
        if (user.getPassword2() == null || user.getPassword2().trim().isEmpty()) {
            bindingResult.addError(new ObjectError("password2Error", "!!Password confirmation cannot be empty!!"));
        }

        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("passwordError", "Пароли не равны!");
            bindingResult.addError(new ObjectError("passwordError", "Пароли не равны!"));
        }
        if (bindingResult.hasErrors() || !response.isSuccess()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activated code is not found!");
        }
        return "login";
    }
}
