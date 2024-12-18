package org.example.troyanbackend.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.troyanbackend.template.IdNotes;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "notes")
public class NotesTable extends IdNotes {

    private String email;
    private String password;
}
