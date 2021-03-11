package com.bakiyem.surucu.proje.model.denemeSinavi

data class AnswerModel(

    var questionNumber: Int,
    var questionAnswe: String,
    var isCorrectAnswe: Boolean? = null

)