package pl.sikora.katarzyna.ShoppingList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.sikora.katarzyna.ShoppingList.model.ConfirmationToken;
import pl.sikora.katarzyna.ShoppingList.model.ShoppingUser;
import pl.sikora.katarzyna.ShoppingList.repository.ConfirmationTokenRepository;

@Service("emailSenderService")
public class EmailSenderService {

    private final JavaMailSender javaMailSender;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender,
                              ConfirmationTokenRepository confirmationTokenRepository) {
        this.javaMailSender = javaMailSender;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public void setEmail(ShoppingUser user){
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Potwierdzenie rejestracji konta");
        mailMessage.setFrom("contact.office.foodstuff@gmail.com");
        mailMessage.setText("Dziękujemy za rejestrację na naszym portalu! \nTwoja rejestracja na stronie foodstuff.katarzynasikora.pl przebiega pomyślnie! \n" +
                "\nMożesz już zarządzać własnymi przepisami szybciej i wygodniej. \n" +
                "\nAby potwierdzić rejestrację, proszę kliknąć w ten link : "
                +"http://foodstuff.sikorakatarzyna.pl/activate;token="+confirmationToken.getConfirmationToken() +
                "\n Pozdrawiamy serdecznie. \n " +
                "Zespół foodstuff :) ");

        sendEmail(mailMessage);
    }
}
