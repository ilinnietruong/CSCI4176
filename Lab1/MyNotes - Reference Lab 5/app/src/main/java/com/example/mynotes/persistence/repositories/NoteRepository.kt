package com.example.mynotes.persistence.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.mynotes.datamodels.NoteInfo
import com.example.mynotes.persistence.dao.NoteDAO

class NoteRepository(private val noteDAO: NoteDAO) {

    // Get notes, after wrapping them in LiveData, by calling NoteDAO's getNotes method
    val notes : LiveData<List<NoteInfo>> = noteDAO.getNotes()

    // The insert function calls the NoteDAO's insertNote method in non-blocking way
    @WorkerThread
    suspend fun insert(note : NoteInfo) {
        noteDAO.insertNote(note)
    }

}