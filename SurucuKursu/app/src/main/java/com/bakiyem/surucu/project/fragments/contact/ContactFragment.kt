package com.bakiyem.surucu.project.fragments.contact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.model.iletisim.Response4Iletisim
import com.bakiyem.surucu.project.utils.ext.regular
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_contact.view.*
import kotlinx.android.synthetic.main.fragment_course.view.tv_hugeTitle


class ContactFragment : Fragment() {

    lateinit var viewP: View

    lateinit var iletisimVM: IletisimVM

    lateinit var iletisimData: Response4Iletisim

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewP = inflater.inflate(R.layout.fragment_contact, container, false)

        iletisimData = Hawk.get("iletisim")

        changeFontType()

        initVM()

        handleClickListener()

        viewP.tv_hugeTitle.semibold()

        return viewP
    }

    private fun changeFontType() {
        viewP.tv_hugeTitle.semibold()
        viewP.tv_location.regular()
        viewP.tv_phone.regular()
        viewP.tv_mail.regular()
        viewP.tv_nameInfo.semibold()
        viewP.tv_epostaInfo.semibold()
        viewP.tv_telefonInfo.semibold()
        viewP.tv_mesajInfo.semibold()
        viewP.et_name.regular()
        viewP.et_eposta.regular()
        viewP.et_telefon.regular()
        viewP.et_mesaj.regular()
    }

    private fun initVM() {
        iletisimVM = ViewModelProviders.of(this).get(IletisimVM::class.java)


        iletisimVM.sendFeedbackLD.observe(this, {
            it?.let {
                Toast.makeText(activity?.applicationContext, it.detay, Toast.LENGTH_SHORT).show()
                viewP.et_name.setText("")
                viewP.et_eposta.setText("")
                viewP.et_telefon.setText("")
                viewP.et_mesaj.setText("")
            } ?: run {
                Toast.makeText(
                    activity?.applicationContext,
                    "Error send Feedback..",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        prepareIletisimData(iletisimData)
    }

    private fun handleClickListener() {
        viewP.btn_send.setOnClickListener {
            sendFeedback()
        }

        viewP.btn_yolTarifi.setOnClickListener {
            /*val uri = String.format(Locale.ENGLISH, "geo:%f,%f", iletisimData.haritaX?.toFloat(), iletisimData.haritaY?.toFloat())
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context!!.startActivity(intent)*/
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=${iletisimData.haritaX},${iletisimData.haritaY} (" + "deneme" + ")")
            )
            startActivity(intent)
        }

        viewP.iv_facebook.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            facebookIntent.data = Uri.parse(iletisimData.facebook)
            startActivity(facebookIntent)
        }

        viewP.iv_twitter.setOnClickListener {
            val twitterIntent = Intent(Intent.ACTION_VIEW)
            twitterIntent.data = Uri.parse(iletisimData.twiter)
            startActivity(twitterIntent)
        }

        viewP.iv_instagram.setOnClickListener {
            val instagramIntent = Intent(Intent.ACTION_VIEW)
            instagramIntent.data = Uri.parse(iletisimData.instagram)
            startActivity(instagramIntent)
        }

        viewP.iv_whatsapp.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=${iletisimData.whatsapp}"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private fun sendFeedback() {
        val adSoyad = viewP.et_name.text.toString()
        val mail = viewP.et_eposta.text.toString()
        val tel = viewP.et_telefon.text.toString()
        val mesaj = viewP.et_mesaj.text.toString()

        if (adSoyad.trim() == "") {
            Toast.makeText(
                activity?.applicationContext,
                "Ad Soyad Alanı Boş Bırakılamaz",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (mail.trim() == "") {
            Toast.makeText(
                activity?.applicationContext,
                "Mail Alanı Boş Bırakılamaz",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (tel.trim() == "") {
            Toast.makeText(
                activity?.applicationContext,
                "Telefon Alanı Boş Bırakılamaz",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (mesaj.trim() == "") {
            Toast.makeText(
                activity?.applicationContext,
                "Mesaj Alanı Boş Bırakılamaz",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        iletisimVM.sendFeedback(adSoyad, mail, tel, mesaj)

    }

    private fun prepareIletisimData(iletisimData: Response4Iletisim) {
        viewP.tv_location.text = iletisimData.adres
        viewP.tv_phone.text = iletisimData.telefon1
        viewP.tv_mail.text = iletisimData.mail

    }
}