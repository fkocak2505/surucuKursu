package com.bakiyem.surucu.proje

import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.contact.ContactFragment
import com.bakiyem.surucu.proje.fragments.course.CourseFragment
import com.bakiyem.surucu.proje.fragments.main.view.MainFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

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

    }

    private fun prepareLeftAndBottomMenu(){
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

        nav_view.setNavigationItemSelectedListener(this)

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.description) {

        } else if (id == R.id.transactions) {
        } else if (id == R.id.reports) {
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}