package duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.controllers.User;


import duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.models.chatmessage.ChatMessage;
import duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.services.subservices.ChatMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
public class ChatController {

    private ChatMessageService chatMessageService;

    @GetMapping("/contact/{receiver}")
    public String chat(@PathVariable("receiver") String receiver, Model model, @ModelAttribute("sender") String sender) {
        model.addAttribute("sender", sender);
        model.addAttribute("receiver", receiver);
        model.addAttribute("messages", chatMessageService.findBySenderAndReceiver(sender, receiver));
        return "/chatpage";
    }

    @GetMapping("/chatpage")
    public String chatPage(@ModelAttribute("receiver") String receiver, Model model, @ModelAttribute("sender") String sender) {
        model.addAttribute("sender", sender);
        model.addAttribute("receiver", receiver);
        model.addAttribute("messages", chatMessageService.findBySenderAndReceiver(sender, receiver));
        return "chatpage";
    }

    @PostMapping("/chatpage/send")
    public String send(Model model, @ModelAttribute("sender") String sender, @RequestParam String receiver, @RequestParam String message) {
        chatMessageService.save(new ChatMessage(sender, receiver, message));
        model.addAttribute("sender", sender);
        model.addAttribute("receiver", receiver);
        model.addAttribute("messages", chatMessageService.findBySenderAndReceiver(sender, receiver));
        return "redirect:/contact/" + receiver;
    }

   @GetMapping("/messages/{sender}/{receiver}")
    @ResponseBody
    public List<ChatMessage> messages(@PathVariable("receiver") String sender, @PathVariable("sender") String receiver) {
        return chatMessageService.findBySenderAndReceiver(sender, receiver);
    }

    @ModelAttribute("sender")
    String sender(Principal user) {
        return user.getName();
    }
}
