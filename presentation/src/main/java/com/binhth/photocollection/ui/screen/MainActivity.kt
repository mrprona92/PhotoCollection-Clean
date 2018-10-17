package com.binhth.photocollection.ui.screen

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.binhth.photocollection.R
import com.binhth.photocollection.ui.screen.core.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity<MainActivityViewModel>() {
    override fun getLayout(): Int = R.layout.activity_main

    private var toggle: ActionBarDrawerToggle? = null

    override fun initComponent(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        val drawer = drawer_layout as DrawerLayout
        toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.app_name, R.string.app_name
        )
        drawer.addDrawerListener(toggle as DrawerLayout.DrawerListener)
        toggle?.syncState()

        navigation_view.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_collection_list -> {
                    //TODO
                }
                R.id.action_search -> {
                    //TODO
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(navigation_view)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> super.onBackPressed()
        }
        return true
    }

}
