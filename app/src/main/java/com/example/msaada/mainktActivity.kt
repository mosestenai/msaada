package com.example.msaada;

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.msaada.contributor.addcontributionActivity
import com.example.msaada.forms.feedbackActivity
import com.example.msaada.forms.loginActivity
import com.example.msaada.forms.registerActivity
import com.google.android.material.navigation.NavigationView

class mainktActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    lateinit var proceed: Button

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        proceed = findViewById(R.id.proceed)

        val toolbar: Toolbar= findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false);


        drawer= findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)



        //go to login page if login button is clicked
        proceed.setOnClickListener{
            val intent = Intent(this@mainktActivity, contributionsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_item_one->  {val intent = Intent(this@mainktActivity, loginActivity::class.java)
                startActivity(intent)}
            R.id.nav_item_two-> {val intent = Intent(this, registerActivity::class.java)
            startActivity(intent) }
            R.id.nav_item_four-> {val intent = Intent(this, feedbackActivity::class.java)
                startActivity(intent) }
            R.id.nav_item_six->{val intent = Intent(this,contributionsActivity::class.java)
            startActivity(intent)}
            R.id.nav_item_seven->{val intent = Intent(this, addcontributionActivity::class.java)
                startActivity(intent)}

        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }
}