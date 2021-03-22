package com.bakiyem.surucu.proje

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.bakiyem.surucu.proje.activity.dersKategorileri.DerslerimActivity
import com.bakiyem.surucu.proje.activity.login.LoginActivity
import com.bakiyem.surucu.proje.activity.odemeBilgilerim.OdemeBilgilerimActivity
import com.bakiyem.surucu.proje.activity.profil.ProfilimActivity
import com.bakiyem.surucu.proje.activity.randevular.RandevularimActivity
import com.bakiyem.surucu.proje.activity.sinavSonuclarim.SinavSonuclarimActivity
import com.bakiyem.surucu.proje.activity.sinavlarim.SinavlarimActivity
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.contact.ContactFragment
import com.bakiyem.surucu.proje.fragments.course.view.CourseFragment
import com.bakiyem.surucu.proje.fragments.main.view.MainFragment
import com.bakiyem.surucu.proje.model.login.Response4Login
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.left_menu_with_login.*
import kotlinx.android.synthetic.main.left_menu_without_login.*


class MainActivity : BaseActivity() {

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
        tv_navLeftSinavSonuclarim.regular()
        tv_navLeftOdemeBilgilerim.regular()
        tv_navLeftRandevularim.regular()
        tv_navLeftFaydaliBilgiler.regular()
        tv_navLeftGuvenliCikis.regular()

        changeFontType(
            applicationContext, bottomNavigationView
        )
    }

    override fun initReq() {

    }

    override fun initVMListener() {

    }

    override fun onCreateMethod() {

        prepareLeftAndBottomMenu()

        handleClickListener()

        handleisLoginandDrawUI()

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
            toast("Profilim")
        }

        cl_guvenliCikis.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            toast("Profilim")
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
            super.onBackPressed()
        }
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