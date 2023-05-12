package duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class TestSendMail {

    public static void main(String [] args) {
        // Paramètres de configuration de l'envoi de messagerie
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "dyshashop0@gmail.com";
        String password = "Dysha@0612";

        // Définir les propriétés système
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Créer la session de messagerie
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Créer l'objet message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dyshashop0@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("silatsamdylan@gmail.com"));
            message.setSubject("Test email");
            message.setText("This is a test email");

            // Envoyer le message
            Transport.send(message);

            System.out.println("Le message a été envoyé avec succès");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
