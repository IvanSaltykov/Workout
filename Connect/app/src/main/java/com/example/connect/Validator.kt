package com.example.connect

class Validator {

    fun checkConfirmPassword(confirmPassword: String, password: String) : Boolean {
        if (confirmPassword == password)
            return true
        return false
    }

    fun checkPassword(password: String) : Boolean {
        if (password.isNotEmpty())
            return true
        return false
    }

    fun checkEmail(email: String) : Boolean {
        if (email.isTitleCase()) {
            val domen = email.substringAfter("@").trim()
            if (!domen.substringAfter('.').isNumberCase()
                && domen.substringAfter('.').length <= 3)
                return true
        }
        return false
    }

    private fun String.isTitleCase() : Boolean {    //проверка на наличие верхнего регистра в строке
        val str: Pair<String, String> = this.partition {
            it.isUpperCase()
        }
        if (str.first == "")
            return true
        return false
    }

    private fun String.isNumberCase() = any { it.isDigit() }    //проверка на наличие цифр в строке
}