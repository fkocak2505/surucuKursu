package com.bakiyem.surucu.project

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.bakiyem.surucu.project.activity.dersKategorileri.DerslerimActivity
import com.bakiyem.surucu.project.activity.faydaliBilgiler.FaydaliBilgilerActivity
import com.bakiyem.surucu.project.activity.hakkimizda.HakkimizdaActivity
import com.bakiyem.surucu.project.activity.login.LoginActivity
import com.bakiyem.surucu.project.activity.odemeBilgilerim.OdemeBilgilerimActivity
import com.bakiyem.surucu.project.activity.profil.ProfilimActivity
import com.bakiyem.surucu.project.activity.randevular.RandevularimActivity
import com.bakiyem.surucu.project.activity.sinavSonuclarim.SinavSonuclarimActivity
import com.bakiyem.surucu.project.activity.sinavlarim.SinavlarimActivity
import com.bakiyem.surucu.project.activity.video.VideolarimActivity
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.fragments.contact.ContactFragment
import com.bakiyem.surucu.project.fragments.course.view.CourseFragment
import com.bakiyem.surucu.project.fragments.main.view.MainFragment
import com.bakiyem.surucu.project.model.iletisim.Response4Iletisim
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.model.login.Response4Login
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.regular
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.left_menu_with_login.*
import kotlinx.android.synthetic.main.left_menu_without_login.*
import kotlinx.android.synthetic.main.navigation_header.*


class MainActivity : BaseActivity() {

    lateinit var iletisimData: Response4Iletisim

    override fun getLayoutID(): Int = R.layout.activity_main

    override fun initVM() {

    }

    override fun initChangeFont() {
        setBottomNavigationViewTypeface(bottomNavigationView)

        tv_userName.semibold()
        tv_login.semibold()
        tv_navLeftProfilim.regular()
        tv_navLeftDerslerim.regular()
        tv_navLeftSinavlar.regular()
        tv_navLeftVideolar.regular()
        tv_navLeftSinavSonuclarim.regular()
        tv_navLeftOdemeBilgilerim.regular()
        tv_navLeftRandevularim.regular()
        tv_navLeftFaydaliBilgiler.regular()
        tv_navLeftGuvenliCikis.regular()
        tv_hakkimizda.regular()
        tv_gizlilik.regular()
        tv_iletisim.regular()

        changeFontType(
            applicationContext, bottomNavigationView
        )

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
        iv_logo.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)

        val unwrappedDrawable = AppCompatResources.getDrawable(
            btn_login.context,
            R.drawable.bg_start_btn
        )
        val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
        DrawableCompat.setTint(
            wrappedDrawable,
            Color.parseColor("#${Hawk.get<Response4Kurs>("kursBilgisi").renk}")
        )
        btn_login.setBackgroundResource(R.drawable.bg_start_btn)
    }

    override fun initReq() {

    }

    override fun initVMListener() {

    }

    override fun onCreateMethod() {

        prepareLeftAndBottomMenu()

        handleClickListener()

        handleisLoginandDrawUI()

        iletisimData = Hawk.get("iletisim")

    }

    private fun prepareLeftAndBottomMenu() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.open,
            R.string.close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

    }

    @SuppressLint("ResourceType")
    private fun handleClickListener() {
        val firstFragment = MainFragment()
        val secondFragment = CourseFragment()
        val thirdFragment = ContactFragment()


        setCurrentFragment(firstFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(firstFragment)
                R.id.course -> setCurrentFragment(secondFragment)
                R.id.contact -> setCurrentFragment(thirdFragment)

            }
            true
        }

        val state = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_focused),
            intArrayOf(android.R.attr.state_pressed)
        )

        val color = intArrayOf(
            Color.parseColor("#${Hawk.get<Response4Kurs>("kursBilgisi").renk}"),
            Color.GRAY,
            Color.GRAY,
            Color.GRAY,
            Color.GRAY
        )

        val colorStateList1 = ColorStateList(state, color)
        val colorStateList2 = ColorStateList(state, color)

        bottomNavigationView.itemTextColor = colorStateList1
        bottomNavigationView.itemIconTintList = colorStateList2

        btn_login.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            startActivity(Intent(this, LoginActivity::class.java))
        }

        cl_profilim.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            startActivity(Intent(this, ProfilimActivity::class.java))
        }

        cl_derslerim.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            startActivity(Intent(this, DerslerimActivity::class.java))
        }

        cl_sinavlarim.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            startActivity(Intent(this, SinavlarimActivity::class.java))
        }

        cl_videolar.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            startActivity(Intent(this, VideolarimActivity::class.java))
        }

        cl_sinavSonuclarim.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            startActivity(Intent(this, SinavSonuclarimActivity::class.java))
        }

        cl_odemeBilgilerim.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            startActivity(Intent(this, OdemeBilgilerimActivity::class.java))
        }

        cl_Randevularim.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            startActivity(Intent(this, RandevularimActivity::class.java))
        }

        cl_faydaliBilgiler.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            startActivity(Intent(this, FaydaliBilgilerActivity::class.java))
        }

        cl_guvenliCikis.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            Hawk.delete("loginResponse")
            toast("Çıkış Yaptınız")
            startActivity(intent)
        }

        iv_facebook.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            facebookIntent.data = Uri.parse(iletisimData.facebook)
            startActivity(facebookIntent)
        }

        iv_twitter.setOnClickListener {
            val twitterIntent = Intent(Intent.ACTION_VIEW)
            twitterIntent.data = Uri.parse(iletisimData.twiter)
            startActivity(twitterIntent)
        }

        iv_instagram.setOnClickListener {
            val instagramIntent = Intent(Intent.ACTION_VIEW)
            instagramIntent.data = Uri.parse(iletisimData.instagram)
            startActivity(instagramIntent)
        }

        iv_whatsapp.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=${iletisimData.whatsapp}"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        iv_facebookLeftMenu.setOnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            facebookIntent.data = Uri.parse(iletisimData.facebook)
            startActivity(facebookIntent)
        }

        iv_twitterLeftMenu.setOnClickListener {
            val twitterIntent = Intent(Intent.ACTION_VIEW)
            twitterIntent.data = Uri.parse(iletisimData.twiter)
            startActivity(twitterIntent)
        }

        iv_instagramLeftMenu.setOnClickListener {
            val instagramIntent = Intent(Intent.ACTION_VIEW)
            instagramIntent.data = Uri.parse(iletisimData.instagram)
            startActivity(instagramIntent)
        }

        iv_whatsappLeftMenu.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=${iletisimData.whatsapp}"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        tv_gizlilik.setOnClickListener {
            val url = "https://${iletisimData.web}/gizlilik-sozlesmesi"
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        tv_hakkimizda.setOnClickListener {
            startActivity(Intent(this@MainActivity, HakkimizdaActivity::class.java))
        }

        tv_iletisim.setOnClickListener {
            bottomNavigationView.selectedItemId = bottomNavigationView.menu.getItem(2).itemId
            drawer_layout.closeDrawer(GravityCompat.START)
        }

    }

    private fun handleisLoginandDrawUI() {
        if (Hawk.contains("loginResponse")) {
            nav_without_login.visibility = View.GONE
            nav_with_login.visibility = View.VISIBLE

            prepareLoginData()

        } else {
            nav_without_login.visibility = View.VISIBLE
            nav_with_login.visibility = View.GONE
        }
    }

    private fun prepareLoginData() {
        val loginData = Hawk.get<Response4Login>("loginResponse")

        iv_userImage.loadImage(loginData.resim)
        tv_userName.text = loginData.adSoyad
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame, fragment)
            commit()
        }

    private fun setBottomNavigationViewTypeface(v: View) {
        try {
            if (v is ViewGroup) {
                for (i in 0 until v.childCount) {
                    val child: View = v.getChildAt(i)
                    setBottomNavigationViewTypeface(child)
                }
            } else if (v is TextView) {
                v.typeface = Typeface.createFromAsset(
                    context.assets,
                    "montserrat_regular.otf"
                )
            }
        } catch (e: Exception) {
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
            true
        } else super.onKeyDown(keyCode, event)
    }

    private fun changeFontType(context: Context, v: View) {
        try {
            if (v is ViewGroup) {
                for (i in 0 until v.childCount) {
                    val child: View = v.getChildAt(i)
                    changeFontType(context, child)
                }
            } else if (v is TextView) {
                (v as TextView).typeface = Typeface.createFromAsset(
                    context.assets,
                    "montserrat_regular.ttf"
                )
            }
        } catch (e: Exception) {
        }
    }

    /*override fun onNavigationItemSelected(item: MenuItem): Boolean {

        *//*when (item.itemId) {
            R.id.description -> {

            }
            R.id.transactions -> {

            }
            R.id.reports -> {

            }
        }*//*

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }*/

}