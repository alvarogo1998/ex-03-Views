package com.agalobr.ex03views.app

sealed class ErrorApp {
    object UnKnownError : ErrorApp()
    object DatabaseErrorApp: ErrorApp()
    object InternetErrorApp: ErrorApp()
}