package ru.mbelin.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mbelin.sweater.bean.MailGunSender;

@Controller
@RequestMapping("/debug")
public class DebugController {
    @Autowired
    private MailGunSender mailGunSender;

    @GetMapping
    public String debug(Model model)
    {
        //model.addAttribute("users", userRepo.findAll());
        return "debug";
    }

    @PostMapping("/sendMailGunMsg")
    public String sendMailGunMsg(
                        @RequestParam String subject,
                        @RequestParam String textMessage,
                        Model model)
    {
        String res = mailGunSender.sendMail("codxxx@gmail.com", subject, textMessage);
        model.addAttribute("messageSendMail", "Email is send: " + res);
        return "debug";
    }

}
