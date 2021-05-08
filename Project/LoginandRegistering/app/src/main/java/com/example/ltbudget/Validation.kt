package com.example.ltbudget


class Validation {
    private var firstName: String
    private var lastName: String
    private var username: String
    private var email : String
    private var password:String

    constructor(fn: String, ln: String, usr: String, em: String, pw: String) {
        firstName = fn
        lastName = ln
        username = usr
        email= em
        password = pw
    }

        //empty String
     fun isEmpty(target: String): Boolean {
            return target.isNullOrBlank()
    }

    /*Reference: https://www.tutorialspoint.com/how-to-check-email-address-validation-in-android-on-edit-text
    Determine if the email is invalid by the length of the e-mail and the characters in it
     */
    open fun validEmail(): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex() //extended regular expression from the reference above
        return if (isEmpty(email)) {
         false
        }
        else
            email.matches(emailPattern)
    }//only allowed to have 10 characters, but need at least 5 characters

        /*At least one capital letter, one lowercase and one uppercase. Got the  regular expression from:
      *https://androidfreetutorial.wordpress.com/2018/01/04/regular-expression-for-password-field-in-android/
      */
    fun isValidPassword(): Boolean  {
        val match = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,10}$".toRegex()
            //only allowed to have 10 characters, but need at least 5 characters,at least one digit and at least one capital letter.
        return if (!passwordLengthCheck()) {
            false
        } else password.matches(match)
    }

    fun passwordLengthCheck(): Boolean {
        return password.length in 5..10
    }

        //this is used to confirm password
    fun samePassword(password2: String): Boolean {
       return password == password2
    }

        //check whether registration work
    fun successfulRegister(): Boolean {
        return validEmail() && isValidPassword() && !isEmpty(firstName) && !isEmpty(lastName)
    }

    fun getEmail(): String {
        return email
    }

    fun getFirstName(): String {
        return firstName
    }

    fun getLastName(): String {
        return lastName
    }

    fun getPassword(): String {
        return password
    }

    fun getUsername(): String{
        return username
    }
}
