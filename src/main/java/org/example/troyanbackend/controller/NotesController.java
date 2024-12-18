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
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotesController {

    private final NotesService notesService;

    // Ma'lumotlar yuborish qismini shakllantirish
    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendMessage(@RequestBody NotesDto notesDto) {
        ApiResponse response = notesService.sendMail(notesDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }
}