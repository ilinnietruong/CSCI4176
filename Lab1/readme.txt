CSCI4176
Lab Activity - Note Taking App
Linh Truong



All the modification is in the file , "CreateNoteFragment.kt" in the Fragment folder.
There is isEmpty(x:String) method created, where to return a boolean.

Modified the code where before a new note is created, needs to go under validation. Validation means the title and the content are not empty. If either title, title or both is/are empty, there will be an error pop stating it is empty and try again.

Also, modified the code where the maximum characters in the content is 40. It will display the first 40 characters on the main activity.

There are two .png files of my screenshots in the folder: 
EmptyBodyAndTitle.png and FortyCharacters.png
