package org.example.troyanbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.troyanbackend.dto.NotesDto;
import org.example.troyanbackend.payload.ApiResponse;
import org.example.troyanbackend.service.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
//@CrossOrigin(origins = "https://birthday-frontend-d8395a6906eb.herokuapp.com")
@CrossOrigin(origins = "https://send-notes-to-email-front.vercel.app")
public class NotesController {

    private final NotesService notesService;

    @PostMapping("/send")
//    @CrossOrigin(origins = "https://send-notes-to-email-front.vercel.app")
    public ResponseEntity<ApiResponse> sendMessage(@RequestBody NotesDto notesDto) {
        ApiResponse response = notesService.sendMail(notesDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }
}
