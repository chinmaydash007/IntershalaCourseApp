package com.example.intershalacourseapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.example.intershalacourseapp.R
import com.example.intershalatrainingapp.ChatFragment
import com.example.intershalatrainingapp.MessageFragment
import com.example.intershalatrainingapp.Profile_Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = drawer_layout
        navigationView = nav_view
        toolbar = nav_toolbar


        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        var toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.nav_drawer_open,
            R.string.nav_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()




        openMessageFragment()

        navigationView.setNavigationItemSelectedListener(this)

    }

    private fun openMessageFragment() {
        var messageFragment = MessageFragment()
        var fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, messageFragment, "MessageFragment")
            .commit()
        navigationView.setCheckedItem(R.id.nav_message)

        supportActionBar?.setTitle("Message Fragment")
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (frag !is MessageFragment) {
            openMessageFragment()
        } else {
            super.onBackPressed()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater: MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_chat -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ChatFragment(), "Chat Fragment")
                    .commit()
                supportActionBar?.setTitle("Chat Fragment")

            }
            R.id.nav_message -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MessageFragment(), "Message Fragment")
                    .commit()
                supportActionBar?.setTitle("Message Fragment")

            }
            R.id.nav_profile -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, Profile_Fragment(), "Profile Fragment")
                    .commit()
                supportActionBar?.setTitle("Profile Fragment")

            }

        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}
