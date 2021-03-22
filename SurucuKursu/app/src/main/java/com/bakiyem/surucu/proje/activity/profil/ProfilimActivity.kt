package com.bakiyem.surucu.proje.activity.profil

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.profilim.Response4Profilim
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold
import kotlinx.android.synthetic.main.activity_profilim.*

class ProfilimActivity : BaseActivity() {

    lateinit var profilVM: ProfilimActivityVM

    override fun getLayoutID(): Int = R.layout.activity_profilim

    override fun initVM() {
        profilVM = ViewModelProviders.of(this).get(ProfilimActivityVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()
        tv_generalInfo.semibold()
        tv_userNameAndSurname.semibold()
        tv_userPhone.regular()

        tv_adayNoInfo.semibold()
        tv_adayNo.regular()
        tv_adiInfo.semibold()
        tv_adi.regular()
        tv_soyAdiInfo.semibold()
        tv_soyAdi.regular()
        tv_grupInfo.semibold()
        tv_grup.regular()
        tv_tcInfo.semibold()
        tv_tc.regular()
        tv_dogumInfo.semibold()
        tv_dogum.regular()
        tv_kayitTarihiInfo.semibold()
        tv_kayitTarihi.regular()
        tv_ehliyetSinifiInfo.semibold()
        tv_ehliyetSinifi.regular()
        tv_dilInfo.semibold()
        tv_dil.regular()
        tv_cinsiyetInfo.semibold()
        tv_cinsiyet.regular()
        tv_kanGrubuInfo.semibold()
        tv_kanGrubu.regular()
        tv_onlineDersInfo.semibold()
        tv_onlineDers.regular()
        tv_adresInfo.semibold()
        tv_adres.regular()
        tv_cepTelInfo.semibold()
        tv_cepTel.regular()
        tv_evTelInfo.semibold()
        tv_evTel.regular()
        tv_acilErisimTelInfo.semibold()
        tv_acilErisimTel.regular()
        tv_epostaInfo.semibold()
        tv_eposta.regular()
    }

    override fun initReq() {
        profilVM.getProfilim()
    }

    override fun initVMListener() {
        prepareWithBaseVM(profilVM)
        profilVM.profilLD.observe(this, {
            it?.let {
                prepareProfilimData(it)
            } ?: run {
                toast("Error Profilim")
                onBackPressed()
            }
        })
    }

    override fun onCreateMethod() {
        goBackListener()
    }

    @SuppressLint("SetTextI18n")
    private fun prepareProfilimData(profilim: Response4Profilim) {
        iv_profilImage.loadImage(profilim.resim)
        tv_userNameAndSurname.text = "${profilim.ad} ${profilim.soyad}"
        tv_userPhone.text = profilim.gsm

        tv_adayNo.text = profilim.adayNo
        tv_adi.text = profilim.ad
        tv_soyAdi.text = profilim.soyad
        tv_grup.text = profilim.grup
        tv_tc.text = profilim.tckn
        tv_dogum.text = profilim.dogumTarih
        tv_kayitTarihi.text = profilim.kayitTarihi
        tv_ehliyetSinifi.text = profilim.sertifikaSinif
        tv_dil.text = profilim.dili
        tv_cinsiyet.text = profilim.cinsiyeti
        tv_kanGrubu.text = profilim.kanGrup
        tv_onlineDers.text = profilim.onlineDers
        tv_adres.text = profilim.adres
        tv_cepTel.text = profilim.gsm
        tv_evTel.text = profilim.evTel
        tv_acilErisimTel.text = profilim.acilTel
        tv_eposta.text = profilim.mail


    }

    private fun goBackListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }
}