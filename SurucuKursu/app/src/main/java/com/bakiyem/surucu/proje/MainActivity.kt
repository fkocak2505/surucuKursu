package com.bakiyem.surucu.proje

import android.content.Intent
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.bakiyem.surucu.proje.activity.login.LoginActivity
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.contact.ContactFragment
import com.bakiyem.surucu.proje.fragments.course.CourseFragment
import com.bakiyem.surucu.proje.fragments.main.view.MainFragment
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.left_menu_without_login.*


class MainActivity : BaseActivity(){

    override fun getLayoutID(): Int = R.layout.activity_main

    override fun initVM() {

    }

    override fun initChangeFont() {

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
    }

    private fun handleisLoginandDrawUI() {
        if (Hawk.contains("loginResponse")) {
            nav_without_login.visibility = View.GONE
            nav_with_login.visibility = View.VISIBLE
        } else {
            nav_without_login.visibility = View.VISIBLE
            nav_with_login.visibility = View.GONE
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame, fragment)
            commit()
        }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
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