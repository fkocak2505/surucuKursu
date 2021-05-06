package com.bakiyem.surucu.project.model.denemeSinavi

data class AnswerModel(

    var questionNumber: Int,
    var questionAnswe: String,
    var isCorrectAnswe: Boolean? = null

)