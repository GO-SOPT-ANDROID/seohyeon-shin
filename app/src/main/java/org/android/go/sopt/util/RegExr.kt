package org.android.go.sopt.util

fun verifyId(id:String) :Boolean{
    val regexId = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$".toRegex()
    return regexId.matches(id)
}

fun verifyPw(pw:String) :Boolean {
    val regexPW = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,12}$".toRegex()
    return regexPW.matches(pw)
}