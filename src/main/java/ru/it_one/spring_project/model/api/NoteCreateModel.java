package ru.it_one.spring_project.model.api;

import lombok.Data;

@Data
public class NoteCreateModel {

    private String header;

    private String text;
}
