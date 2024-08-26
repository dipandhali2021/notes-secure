package com.secure.notes.services;

import com.secure.notes.models.Note;

import java.util.List;

public interface NoteService {
    Note createNoteForUser(String username, String content);
    Note updateNoteForUser(String username, Long noteId, String content);
    void deleteNoteForUser(String username, Long noteId);
    List<Note> getNotesForUser(String username);
}
