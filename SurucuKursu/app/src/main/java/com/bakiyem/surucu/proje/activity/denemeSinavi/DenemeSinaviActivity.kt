package com.bakiyem.surucu.proje.activity.denemeSinavi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.denemeSinavi.AnswerModel
import com.bakiyem.surucu.proje.model.denemeSinavi.QuestionsResultModel
import com.bakiyem.surucu.proje.model.denemeSinavi.Response4DenemeSinavi
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.model.login.Response4Login
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.renderHtml
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_deneme_sinavi.*
import kotlinx.android.synthetic.main.activity_deneme_sinavi.iv_back
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class DenemeSinaviActivity : BaseActivity(), DenemeSinaviQuizAnswerAdapter.ItemClickListener {

    lateinit var denemeSinaviVM: DenemeSinaviVM

    private var answer: String? = null

    private var questionLength: Int = 0

    lateinit var questions: MutableList<Response4DenemeSinavi>

    private var currentQuizIndex = 0

    private lateinit var resultQuestions: MutableList<QuestionsResultModel>

    var isQuizFinished = false

    lateinit var mAdapter: DenemeSinaviQuizAnswerAdapter

    lateinit var listOfAnswers: MutableList<AnswerModel>

    var resultStrData = ""

    var isFinishExam = false

    var correctAnswerIndex = -1

    var selectedSecenek = ""


    override fun getLayoutID(): Int = R.layout.activity_deneme_sinavi

    override fun initVM() {
        denemeSinaviVM = ViewModelProviders.of(this).get(DenemeSinaviVM::class.java)
    }

    override fun initChangeFont() {

        tv_hugeTitle.text = sinavtitle

        tv_hugeTitle.semibold()
        tv_userName.semibold()
        tv_sinavAdi.regular()
        tv_remainingTimeInfo.regular()
        tv_seekbarValue.semibold()
        tv_qKategoriName.semibold()
        tv_qSoruAciklama.regular()
        tv_qSoru.regular()
        tv_secenekAInfo.semibold()
        tv_secenekA.regular()
        tv_secenekBInfo.semibold()
        tv_secenekB.regular()
        tv_secenekCInfo.semibold()
        tv_secenekC.regular()
        tv_secenekDInfo.semibold()
        tv_secenekD.regular()
        tv_oncekiSoru.semibold()
        tv_sonrakiSoru.semibold()
        tv_correctNumber.semibold()
        tv_correctNumberInfo.semibold()
        tv_wrongNumber.semibold()
        tv_wrongNumberInfo.semibold()
        tv_emptyNumber.semibold()
        tv_emptyNumberInfo.semibold()
        tv_score.semibold()
        tv_scoreInfo.semibold()
        tv_chooseAgain.regular()
        tv_finishExam.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)

        tv_seekbarValue.setTextColor(Color.parseColor("#${Hawk.get<Response4Kurs>("kursBilgisi").renk}"))
        sb_questionsLength.thumb.setColorFilter(
            Color.parseColor("#${Hawk.get<Response4Kurs>("kursBilgisi").renk}"),
            PorterDuff.Mode.SRC_ATOP
        )

        val unwrappedDrawable = AppCompatResources.getDrawable(
            cl_sinavSonucPuan.context,
            R.drawable.bg_sinav_sonuc_puan
        )
        val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
        DrawableCompat.setTint(
            wrappedDrawable,
            Color.parseColor("#${Hawk.get<Response4Kurs>("kursBilgisi").renk}")
        )
        cl_sinavSonucPuan.setBackgroundResource(R.drawable.bg_sinav_sonuc_puan)


    }

    override fun initReq() {
        when (sinavTur) {
            "4" -> {
                prepareDenemeSinaviFirst()
            }
            "3" -> {
                prepareAnotherSinav()
            }
            "2" -> {
                prepareDenemeSinaviFirst()
            }
            "1" -> {
                prepareAnotherSinav()
            }
        }

        /*if (isComingOzelSinav) {
            questions = ozelSinavData
            questionLength = ozelSinavData.size
            prepareDenemeSinavi()
            cl_root.visibility = View.VISIBLE
            btn_finisExam.visibility = View.VISIBLE
            generateAnswerGridFirstData(ozelSinavData.size)

        } else {
            questions = mutableListOf()
            denemeSinaviVM.getDenemeSinavi()
        }*/
    }

    private fun prepareAnotherSinav() {
        questions = sinavData
        questionLength = sinavData.size
        prepareDenemeSinavi()
        cl_root.visibility = View.VISIBLE
        btn_finisExam.visibility = View.VISIBLE
        generateAnswerGridFirstData(sinavData.size)
    }

    private fun prepareDenemeSinaviFirst() {
        questions = mutableListOf()
        denemeSinaviVM.getDenemeSinavi()
    }

    override fun initVMListener() {
        prepareWithBaseVM(denemeSinaviVM)

        denemeSinaviVM.denemeSinaviLD.observe(this, {
            it?.let {
                questions = it
                questionLength = it.size
                prepareDenemeSinavi()
                cl_root.visibility = View.VISIBLE
                btn_finisExam.visibility = View.VISIBLE
                generateAnswerGridFirstData(it.size)
            } ?: run {
                toast("Error Deneme Sinavi")
                onBackPressed()
            }
        })

        denemeSinaviVM.cevapNumberLD.observe(this, {
            it?.let {
                resultStrData = it
            } ?: run {
                resultStrData = "0&0&0"
            }
        })

        denemeSinaviVM.sinavSonucLD.observe(this, {
            it?.let {
                toast(it.detay!!)
            } ?: run {
                toast("Error Sinav Sonuc Post")
            }

            isFinishExam = true

            changeVisibility(View.GONE, View.VISIBLE)
            changeConstraint(R.id.tv_chooseAgain)
            calculateSinavSonuc()

            isComingHaziCevap = true
            prepareAllAnswersGrid(true)

        })
    }

    override fun onCreateMethod() {

        fillUserInfo()

        startCountDown()

        seekbarConfig()

        handleSecenekClickListener()

        goBack()


    }

    private fun prepareDenemeSinavi() {
        resultQuestions = mutableListOf()
        questions.forEach { itemQuestion ->
            resultQuestions.add(
                QuestionsResultModel(
                    itemQuestion.kategori!!,
                    0
                )
            )
        }

        /*isShowAnswer?.let {
            NextQuestion(currentQuizIndex, true)
        }?: run{
            NextQuestion(currentQuizIndex)
        }*/

        NextQuestion(currentQuizIndex)

    }

    private fun NextQuestion(
        num: Int,
        isShowBGColor: Boolean = false,
        questionsAnswer: String = ""
    ) {
        sb_questionsLength.max = 0
        sb_questionsLength.max = questions.size
        sb_questionsLength.progress = num + 1
        tv_seekbarValue.text = (num + 1).toString()

        val value =
            num * (sb_questionsLength!!.width - 2 * sb_questionsLength.thumbOffset) / sb_questionsLength.max
        tv_seekbarValue.x = sb_questionsLength.x + value + sb_questionsLength.thumbOffset / 2

        tv_qKategoriName.text = questions[num].kategori

        if (questions[num].soruResim != "") {
            iv_questionImage.visibility = View.VISIBLE
            iv_questionImage.loadImage(questions[num].soruResim)
        } else {
            iv_questionImage.visibility = View.GONE
        }

        if (questions[num].soruAciklama != "") {
            tv_qSoruAciklama.visibility = View.VISIBLE
            val htmlData = Html.fromHtml(questions[num].soruAciklama!!).toString()
            tv_qSoruAciklama renderHtml htmlData
        } else {
            tv_qSoruAciklama.visibility = View.GONE
        }

        tv_qSoru.text = questions[num].soru


        tv_secenekA.text = questions[num].secenekler!![0]?.cevap
        tv_secenekB.text = questions[num].secenekler!![1]?.cevap
        tv_secenekC.text = questions[num].secenekler!![2]?.cevap
        tv_secenekD.text = questions[num].secenekler!![3]?.cevap


        questions[num].secenekler?.forEachIndexed { index, seceneklerItem ->
            if (seceneklerItem?.dogru == "1") {
                answer = seceneklerItem.cevap
                correctAnswerIndex = index
            }

        }

        if (isShowBGColor) {
            if (sinavTur == "2")
                changeBackgroundColor4Secenek(questionsAnswer)
            else
                changeBackgroundColor4Selected(questionsAnswer)
        } else {
            goFirstColorBGForSecenek()
        }
    }

    private fun changeBackgroundColor4Selected(questionsAnswer: String) {
        when (questionsAnswer) {
            "A" -> {
                cv_seceneklerA.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.selectedAnswer
                    )
                )

                cv_seceneklerB.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
                cv_seceneklerC.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
                cv_seceneklerD.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
            }
            "B" -> {
                cv_seceneklerB.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.selectedAnswer
                    )
                )

                cv_seceneklerA.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
                cv_seceneklerC.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
                cv_seceneklerD.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
            }
            "C" -> {
                cv_seceneklerC.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.selectedAnswer
                    )
                )

                cv_seceneklerA.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
                cv_seceneklerB.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
                cv_seceneklerD.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
            }
            "D" -> {
                cv_seceneklerD.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.selectedAnswer
                    )
                )

                cv_seceneklerA.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
                cv_seceneklerB.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
                cv_seceneklerC.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.titleBackground
                    )
                )
            }
        }
    }

    private fun changeBackgroundColor4Secenek(questionsAnswer: String) {
        when (correctAnswerIndex) {
            0 -> {
                cv_seceneklerA.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.correct_answer
                    )
                )

                when (questionsAnswer) {
                    "B" -> {
                        cv_seceneklerB.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerC.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerD.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "C" -> {
                        cv_seceneklerC.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerB.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerD.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "D" -> {
                        cv_seceneklerD.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerB.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerC.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "-" -> {
                        goFirstColorBGForSecenek()
                    }
                }

            }
            1 -> {
                cv_seceneklerB.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.correct_answer
                    )
                )

                when (questionsAnswer) {
                    "A" -> {
                        cv_seceneklerA.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerC.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerD.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "C" -> {
                        cv_seceneklerC.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerA.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerD.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "D" -> {
                        cv_seceneklerD.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerA.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerC.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "-" -> {
                        goFirstColorBGForSecenek()
                    }
                }

            }
            2 -> {
                cv_seceneklerC.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.correct_answer
                    )
                )

                when (questionsAnswer) {
                    "A" -> {
                        cv_seceneklerA.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerB.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerD.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "B" -> {
                        cv_seceneklerB.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerA.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerD.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "D" -> {
                        cv_seceneklerD.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerA.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerB.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "-" -> {
                        goFirstColorBGForSecenek()
                    }
                }

            }
            3 -> {
                cv_seceneklerD.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.correct_answer
                    )
                )

                when (questionsAnswer) {
                    "A" -> {
                        cv_seceneklerA.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerB.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerC.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "B" -> {
                        cv_seceneklerB.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerA.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerC.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "C" -> {
                        cv_seceneklerC.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.wrong_answer
                            )
                        )

                        cv_seceneklerA.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                        cv_seceneklerB.setCardBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.titleBackground
                            )
                        )
                    }
                    "-" -> {
                        goFirstColorBGForSecenek()
                    }
                }
            }
        }
    }

    private fun goFirstColorBGForSecenek() {
        cv_seceneklerA.setCardBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.titleBackground
            )
        )
        cv_seceneklerB.setCardBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.titleBackground
            )
        )
        cv_seceneklerC.setCardBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.titleBackground
            )
        )
        cv_seceneklerD.setCardBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.titleBackground
            )
        )
    }

    private fun prepareAllAnswersGrid(isShowAnswer: Boolean) {
        rv_answerQuiz.layoutManager = GridLayoutManager(this, 6)

        mAdapter = if (isComingHaziCevap!!)
            DenemeSinaviQuizAnswerAdapter(applicationContext, listOfAnswers.toList(), true)
        else
            DenemeSinaviQuizAnswerAdapter(applicationContext, listOfAnswers.toList(), false)
        rv_answerQuiz.adapter = mAdapter

        mAdapter.setClickListener(this)


    }

    private fun handleSecenekClickListener() {
        cv_seceneklerA.setOnClickListener {
            selectedSecenek = "A"
            changeBackgroundColor4Selected("A")
            checkIfCorrectAnswer(tv_secenekA, "A")
        }

        cv_seceneklerB.setOnClickListener {
            selectedSecenek = "B"
            changeBackgroundColor4Selected("B")
            checkIfCorrectAnswer(tv_secenekB, "B")
        }

        cv_seceneklerC.setOnClickListener {
            selectedSecenek = "C"
            changeBackgroundColor4Selected("C")
            checkIfCorrectAnswer(tv_secenekC, "C")
        }

        cv_seceneklerD.setOnClickListener {
            selectedSecenek = "D"
            changeBackgroundColor4Selected("D")
            checkIfCorrectAnswer(tv_secenekD, "D")
        }

        cv_oncekiSoru.setOnClickListener {
            if (currentQuizIndex == 0) {
                Toast.makeText(applicationContext, "Başa geldi", Toast.LENGTH_SHORT).show()
            } else {
                currentQuizIndex--
                NextQuestion(
                    currentQuizIndex,
                    true,
                    mAdapter.getItem(currentQuizIndex).questionAnswe
                )
            }
        }

        cv_sonrakiSoru.setOnClickListener {
            if (currentQuizIndex + 1 == questionLength) {
                Toast.makeText(applicationContext, "Sona geldi", Toast.LENGTH_SHORT).show()
            } else {
                /*if (!isComingHaziCevap!!)
                    notifyAnswerAdapter("-")*/
                currentQuizIndex++

                if (mAdapter.getItem(currentQuizIndex).questionAnswe == "-")
                    NextQuestion(currentQuizIndex)
                else
                    NextQuestion(
                        currentQuizIndex,
                        true,
                        mAdapter.getItem(currentQuizIndex).questionAnswe
                    )
            }
        }

        btn_finisExam.setOnClickListener {
            finishQuiz()
        }


    }

    private fun checkIfCorrectAnswer(view: TextView, secenek: String) {
        if (currentQuizIndex + 1 == questionLength) {
            isQuizFinished = true
        }

        if (view.text.toString() == answer) {
            resultQuestions[currentQuizIndex].answer = 1
            notifyAnswerAdapter(secenek, true)
        } else {
            resultQuestions[currentQuizIndex].answer = -1
            notifyAnswerAdapter(secenek, false)
        }

        if (isComingHaziCevap!!)
            changeBackgroundColor4Secenek(secenek)
        else {
            if (!isQuizFinished) {
                /*currentQuizIndex++
                NextQuestion(currentQuizIndex)*/
            } else {
                finishQuiz()
            }
        }
    }

    private fun notifyAnswerAdapter(secenek: String, isCorrect: Boolean? = null) {
        listOfAnswers[currentQuizIndex].questionAnswe = secenek
        listOfAnswers[currentQuizIndex].isCorrectAnswe = isCorrect
        mAdapter.notifyItemChanged(currentQuizIndex)
    }

    private fun finishQuiz() {
        val pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Sınavın bitiyor"
        pDialog.setCancelable(false)
        pDialog.setConfirmButton(
            "Sınavı Bitir"
        ) {
            denemeSinaviVM.postSinavSonuc(
                "${cdv_remainingTime.minute}:${cdv_remainingTime.second}",
                sinavTur!!,
                sinavId,
                resultQuestions
            )
            pDialog.dismissWithAnimation()
        }
        pDialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun fillUserInfo() {
        val user: Response4Login = Hawk.get("loginResponse")

        tv_userName.text = "Sayın ${user.adSoyad}"
    }

    private fun startCountDown() {
        cdv_remainingTime.start(2700000)

        cdv_remainingTime.setOnCountdownEndListener {
            toast("Bitti")
        }
    }

    private fun generateAnswerGridFirstData(size: Int) {
        listOfAnswers = mutableListOf()
        for (i in 0 until size) {
            listOfAnswers.add(
                AnswerModel(
                    i + 1,
                    "-"
                )
            )
        }

        prepareAllAnswersGrid(false)
    }

    @SuppressLint("SetTextI18n")
    private fun calculateSinavSonuc() {
        val dogruCevap = resultStrData.split("&")[0]
        val yanlisCevap = resultStrData.split("&")[1]
        val bosCevap = resultStrData.split("&")[2]

        tv_correctNumber.text = dogruCevap
        tv_wrongNumber.text = yanlisCevap
        tv_emptyNumber.text = bosCevap

        tv_score.text = "${dogruCevap.toInt() * 2}"
        tv_sinavAdi.text = "Sınav Sonuçları"
    }

    private fun changeVisibility(isQuestionVisible: Int, isResultVisible: Int) {
        tv_seekbarValue.visibility = isQuestionVisible
        sb_questionsLength.visibility = isQuestionVisible
        tv_qKategoriName.visibility = isQuestionVisible
        tv_qSoruAciklama.visibility = isQuestionVisible
        tv_qSoru.visibility = isQuestionVisible
        cv_seceneklerA.visibility = isQuestionVisible
        cv_seceneklerB.visibility = isQuestionVisible
        cv_seceneklerC.visibility = isQuestionVisible
        cv_seceneklerD.visibility = isQuestionVisible
        cv_oncekiSoru.visibility = isQuestionVisible
        cv_sonrakiSoru.visibility = isQuestionVisible
        cv_remainingTime.visibility = isQuestionVisible
        btn_finisExam.visibility = isQuestionVisible

        cv_sinavSonuc.visibility = isResultVisible
        cv_sinavSonucPuan.visibility = isResultVisible
        tv_chooseAgain.visibility = isResultVisible

        cdv_remainingTime.stop()
    }

    private fun changeConstraint(topConstraintID: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(cl_midRoot)
        constraintSet.connect(
            R.id.rv_answerQuiz,
            ConstraintSet.TOP,
            topConstraintID,
            ConstraintSet.BOTTOM
        )
        constraintSet.applyTo(cl_midRoot)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun seekbarConfig() {
        sb_questionsLength.setOnTouchListener { _, _ -> true }
    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onItemClick(answerModel: AnswerModel, position: Int) {
        currentQuizIndex = position

        changeVisibility(View.VISIBLE, View.GONE)
        changeConstraint(R.id.cv_sonrakiSoru)
        goFirstColorBGForSecenek()
        NextQuestion(currentQuizIndex, true, answerModel.questionAnswe)
        /*if (isFinishExam) {
            currentQuizIndex = position

            changeVisibility(View.VISIBLE, View.GONE)
            changeConstraint(R.id.cv_sonrakiSoru)
            goFirstColorBGForSecenek()
            NextQuestion(currentQuizIndex, true, answerModel.questionAnswe)
        } else {
            toast("Önce sınavı bitiriniz..")
        }*/
    }

    companion object {

        var sinavTur: String? = ""
        var sinavId: String? = ""
        var sinavData: MutableList<Response4DenemeSinavi> = mutableListOf()
        var isComingHaziCevap: Boolean? = false
        var sinavtitle: String? = ""


        fun start(
            activity: AppCompatActivity?,
            sinavTur: String,
            sinavId: String?,
            isComingHaziCevap: Boolean,
            sinavtitle: String,
            sinavData: MutableList<Response4DenemeSinavi>
        ) {
            val starter = Intent(activity, DenemeSinaviActivity::class.java)
            this.sinavTur = sinavTur
            this.sinavId = sinavId
            this.isComingHaziCevap = isComingHaziCevap
            this.sinavData = sinavData
            this.sinavtitle = sinavtitle

            activity!!.startActivity(starter)
        }
    }
}