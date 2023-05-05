package duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.controllers.User;

import duo.cmr.deuxKolos.boundedContexts.GoodDeals.domain.models.chatmessage.ChatMessage;
import duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.services.subservices.ChatMessageService;
import duo.cmr.deuxKolos.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.deuxKolos.boundedContexts.dasandere.web.services.ServiceSupreme;
import duo.cmr.deuxKolos.boundedContexts.dasandere.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class ChatController {

    private AppUserService appUserService;
    private ChatMessageService chatMessageService;
    private ServiceSupreme serviceSupreme;

    @GetMapping("/contact/{receiver}")
    public String chat(@PathVariable("receiver") String receiver, Model model, @ModelAttribute("sender") String sender,
                       @ModelAttribute("receiverName") String receiverName) {
        return "redirect:/discussions/" + serviceSupreme.getChatDiscussionHashFor(sender, receiver);
    }

    @PostMapping("/chatpage/send")
    public String send(Model model, @ModelAttribute("sender") String sender, @RequestParam String receiver, @RequestParam String message) {
        ChatMessage msg = new ChatMessage(sender, receiver, message, serviceSupreme.getChatDiscussionHashFor(sender, receiver));
        chatMessageService.save(msg);
         return "redirect:/discussions/" + msg.getDiscussionHash();
    }

    @ModelAttribute("sender")
    String sender(Principal user) {
        return user.getName();
    }

    @ModelAttribute("receiverName")
    String receiverName(@ModelAttribute("receiver") String receiver) {
        AppUser appUser = appUserService.findByEmail(receiver);
        return appUser.getFirstName() + " " + appUser.getLastName();
    }

     /*@GetMapping("/messages/{sender}/{receiver}")
    @ResponseBody
    public List<ChatMessage> messages(@PathVariable("receiver") String sender, @PathVariable("sender") String receiver) {
        return chatMessageService.findBySenderAndReceiver(sender, receiver);
    }*/

    /*@GetMapping("/chatpage")
    public String chatPage(@ModelAttribute("receiver") String receiver, Model model, @ModelAttribute("sender") String sender) {
        model.addAttribute("sender", sender);
        model.addAttribute("receiver", receiver);
        model.addAttribute("messages", chatMessageService.findBySenderAndReceiver(sender, receiver));
        return "chatpage";
    }*/
}
