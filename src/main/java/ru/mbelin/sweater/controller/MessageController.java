package ru.mbelin.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mbelin.sweater.domain.Message;
import ru.mbelin.sweater.repos.MessageRepo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/editMessage")
public class MessageController {
    @Autowired
    private MessageRepo messageRepo;

    @PostMapping()
    public String editMessage(  @RequestParam("messageId") Long messageId
                              , @RequestParam Map<String,String> form
                              )
    {
        Message message = messageRepo.findById(messageId).get();

        for (String key : form.keySet()) {
            String tmpValue = form.get(key);
            switch (key) {
                case "tagMsg" :
                    message.setTag(tmpValue);
                    break;
                case "textMsg" :
                    message.setText(tmpValue);
                    break;
            }
        }
        messageRepo.save(message);
        return "redirect:/main";
    }
}
