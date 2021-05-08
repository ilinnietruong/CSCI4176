package com.example.mynotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mynotes.MainActivity
import com.example.mynotes.R
import com.example.mynotes.datamodels.NoteInfo
import com.example.mynotes.viewmodels.NotesViewModel
import kotlinx.android.synthetic.main.fragment_create_note.*
import org.w3c.dom.Text

class CreateNoteFragment : Fragment() {

    private val notesViewModel : NotesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolBar = view.findViewById<Toolbar>(R.id.topAppBarCreateNoteFragment)

        toolBar.setNavigationOnClickListener {
            saveNote(noteTitleInputField, noteBodyInputField)

        }

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                saveNote(noteTitleInputField, noteBodyInputField)

            }
        })
    }

    private fun saveNote(noteTitleInputField : TextView, noteBodyInputField : TextView) {
        val noteTitle : String = noteTitleInputField.text.toString()
        val noteBody : String = noteBodyInputField.text.toString()

        //if the note title is empty, an error will pop up
        if(isEmpty(noteTitle)) {
            noteTitleInputField.error = "Note title is empty. Please try again"
        }

        //if the note body/content is empty, an error will pop up
        if(isEmpty(noteBody)) {
            noteBodyInputField.error = "Note body is empty. Please try again."
        }

        //if both note title and note body are NOTE empty, create the note and only 40 characters are allowed
        // in the body/note
        if(!isEmpty(noteTitle) && !isEmpty(noteBody)) {
            //Maximum characters is 40 in the note body (the content)
            val outputFortyCharacters:String = noteBody.substring(0,40)
            val note = NoteInfo(title = noteTitle, body = outputFortyCharacters)
            notesViewModel.insertNote(note)
            findNavController().navigate(R.id.action_createNoteFragment_to_notesFragment)
        }
    }

    //Check to see whether a string is empty or not
    private fun isEmpty(text: String) : Boolean {
        return text.isNullOrBlank()
    }
}