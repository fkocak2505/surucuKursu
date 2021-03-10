package com.bakiyem.surucu.proje.activity.denemeSinavi

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Html
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.denemeSinavi.AnswerModel
import com.bakiyem.surucu.proje.model.denemeSinavi.QuestionsResultModel
import com.bakiyem.surucu.proje.model.denemeSinavi.Response4DenemeSinavi
import com.bakiyem.surucu.proje.model.login.Response4Login
import com.bakiyem.surucu.proje.utils.ext.renderHtml
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_deneme_sinavi.*
import kotlinx.android.synthetic.main.activity_deneme_sinavi.iv_back

class DenemeSinaviActivity : BaseActivity() {

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


    override fun getLayoutID(): Int = R.layout.activity_deneme_sinavi

    override fun initVM() {
        denemeSinaviVM = ViewModelProviders.of(this).get(DenemeSinaviVM::class.java)
    }

    override fun initChangeFont() {

    }

    override fun initReq() {
        denemeSinaviVM.getDenemeSinavi()
    }

    override fun initVMListener() {
        denemeSinaviVM.denemeSinaviLD.observe(this, {
            it?.let {
                prepareDenemeSinavi(it)
                cl_root.visibility = View.VISIBLE
                generateAnswerGridFirstData(it.size)
            } ?: run {
                toast("Error Deneme Sinavi")
                onBackPressed()
            }
        })

        denemeSinaviVM.cevapNumberLD.observe(this, {
            it?.let {
                resultStrData = it
            }?: run{
                resultStrData = "0&0&0"
            }
        })

        denemeSinaviVM.sinavSonucLD.observe(this, {
            it?.let {
                toast(it.detay!!)
            } ?: run {
                toast("Error Sinav Sonuc Post")
            }

            changeVisibility()
            changeConstraint()
            calculateSinavSonuc()

        })
    }

    override fun onCreateMethod() {

        fillUserInfo()

        startCountDown()

        seekbarConfig()

        handleSecenekClickListener()

        goBack()


    }

    private fun prepareDenemeSinavi(listOfQuestions: MutableList<Response4DenemeSinavi>) {
        questionLength = listOfQuestions.size
        questions = listOfQuestions

        resultQuestions = mutableListOf()
        questions.forEach { itemQuestion ->
            resultQuestions.add(
                QuestionsResultModel(
                    itemQuestion.kategori!!,
                    0
                )
            )
        }


        NextQuestion(currentQuizIndex)
    }

    private fun NextQuestion(num: Int) {
        sb_questionsLength.max = 0
        sb_questionsLength.max = questions.size
        sb_questionsLength.progress = num + 1
        tv_seekbarValue.text = (num + 1).toString()

        val value =
            num * (sb_questionsLength!!.width - 2 * sb_questionsLength.thumbOffset) / sb_questionsLength.max
        tv_seekbarValue.x = sb_questionsLength.x + value + sb_questionsLength.thumbOffset / 2

        tv_qKategoriName.text = questions[num].kategori
        tv_qSoru.text = questions[num].soru

        val htmlData = Html.fromHtml(questions[num].soruAciklama!!).toString()
        tv_qSoruAciklama renderHtml htmlData

        tv_secenekA.text = questions[num].secenekler!![0]?.cevap
        tv_secenekB.text = questions[num].secenekler!![1]?.cevap
        tv_secenekC.text = questions[num].secenekler!![2]?.cevap
        tv_secenekD.text = questions[num].secenekler!![3]?.cevap

        questions[num].secenekler?.forEach {
            if (it?.dogru == "1")
                answer = it.cevap
        }
    }

    private fun prepareAllAnswersGrid() {
        rv_answerQuiz.layoutManager = GridLayoutManager(this, 6)
        mAdapter = DenemeSinaviQuizAnswerAdapter(applicationContext, listOfAnswers.toList())
        rv_answerQuiz.adapter = mAdapter
    }

    private fun handleSecenekClickListener() {
        cv_seceneklerA.setOnClickListener {
            checkIfCorrectAnswer(tv_secenekA, "A")
        }

        cv_seceneklerB.setOnClickListener {
            checkIfCorrectAnswer(tv_secenekB, "B")
        }

        cv_seceneklerC.setOnClickListener {
            checkIfCorrectAnswer(tv_secenekC, "C")
        }

        cv_seceneklerD.setOnClickListener {
            checkIfCorrectAnswer(tv_secenekD, "D")
        }

        cv_oncekiSoru.setOnClickListener {
            if (currentQuizIndex == 0) {
                Toast.makeText(applicationContext, "Başa geldi", Toast.LENGTH_SHORT).show()
            } else {
                currentQuizIndex--
                NextQuestion(currentQuizIndex)
            }
        }

        cv_sonrakiSoru.setOnClickListener {
            if (currentQuizIndex + 1 == questionLength) {
                Toast.makeText(applicationContext, "Sona geldi", Toast.LENGTH_SHORT).show()
            } else {
                notifyAnswerAdapter("-")
                currentQuizIndex++
                NextQuestion(currentQuizIndex)
            }
        }
    }

    private fun checkIfCorrectAnswer(view: TextView, secenek: String) {
        if (currentQuizIndex + 1 == questionLength) {
            isQuizFinished = true
        }

        if (view.text.toString() == answer) {
            resultQuestions[currentQuizIndex].answer = 1
        } else {
            resultQuestions[currentQuizIndex].answer = -1
        }

        notifyAnswerAdapter(secenek)


        if (!isQuizFinished) {
            currentQuizIndex++
            NextQuestion(currentQuizIndex)
        } else {
            finishQuiz()
        }

    }

    private fun notifyAnswerAdapter(secenek: String) {
        listOfAnswers[currentQuizIndex].questionAnswe = secenek
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
                    ""
                )
            )
        }

        prepareAllAnswersGrid()
    }

    @SuppressLint("SetTextI18n")
    private fun calculateSinavSonuc(){
        val dogruCevap = resultStrData.split("&")[0]
        val yanlisCevap = resultStrData.split("&")[1]
        val bosCevap = resultStrData.split("&")[2]

        tv_correctNumber.text = dogruCevap
        tv_wrongNumber.text = yanlisCevap
        tv_emptyNumber.text = bosCevap

        tv_score.text = "${dogruCevap.toInt() * 2}"
        tv_sinavAdi.text = "Sınav Sonuçları"
    }

    private fun changeVisibility(){
        tv_seekbarValue.visibility = View.GONE
        sb_questionsLength.visibility = View.GONE
        tv_qKategoriName.visibility = View.GONE
        tv_qSoruAciklama.visibility = View.GONE
        tv_qSoru.visibility = View.GONE
        cv_seceneklerA.visibility = View.GONE
        cv_seceneklerB.visibility = View.GONE
        cv_seceneklerC.visibility = View.GONE
        cv_seceneklerD.visibility = View.GONE
        cv_oncekiSoru.visibility = View.GONE
        cv_sonrakiSoru.visibility = View.GONE

        cv_sinavSonuc.visibility = View.VISIBLE
        cv_sinavSonucPuan.visibility = View.VISIBLE
    }

    private fun changeConstraint(){
        val constraintSet =  ConstraintSet()
        constraintSet.clone(cl_midRoot)
        constraintSet.connect(R.id.rv_answerQuiz, ConstraintSet.TOP, R.id.cv_sinavSonucPuan, ConstraintSet.BOTTOM)
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
}