package com.bonobostudios.amigosenmarcha

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class NormalLauncherActivity : AppCompatActivity(), NotFragment.OnFragmentInteractionListener,
    MainFragment.OnFragmentInteractionListener, StatsFragment.OnFragmentInteractionListener {
    var fragment1: MainFragment = MainFragment()
    var fragment2: StatsFragment = StatsFragment()
    var fragment3: NotFragment = NotFragment()
    lateinit var navigationDrawer: ImageView
    //private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_launcher)

        fun cargarPreferencias(): String {
            var preferences: SharedPreferences =
                getSharedPreferences("usuario", Context.MODE_PRIVATE)

            val usuario = preferences.getString("user", "no hay nada allí")

            return usuario
        }
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit()

        navigationDrawer = findViewById(R.id.NavigationDrawer)

        navigationDrawer.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.nav_main_test -> {
                        var intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.left_in, R.anim.left_off)
                        true
                    }
                    R.id.nav_tools -> {
                        Toast.makeText(this, "SETTINGS", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_cerrar -> {
                        FirebaseFirestore.getInstance().collection("usuarios")
                            .document(cargarPreferencias())
                            .update("online", false)
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
                        finish()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.activity_testing_drawer)

            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.e("ERROR ICON:", "No se muestran los íconos de  los menú", e)
            } finally {
                popupMenu.show()
            }
        }


    }


    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun OnClick(view: View) {
        var transition: FragmentTransaction = getSupportFragmentManager().beginTransaction()
        var home = findViewById<Button>(R.id.homeButton)
        var stats = findViewById<Button>(R.id.statsBton)
        var notify = findViewById<Button>(R.id.notifiBtn)

        when (view.getId()) {
            R.id.homeButton -> getLindo(transition, home, stats, notify, 1)

            R.id.statsBton -> getLindo(transition, home, stats, notify, 2)

            R.id.notifiBtn -> getLindo(transition, home, stats, notify, 3)
        }
    }

    fun getLindo(
        transition: FragmentTransaction,
        iv1: Button,
        iv2: Button,
        iv3: Button,
        name: Int
    ): Int {
        transition.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_off_left)
        transition.addToBackStack(null)
        transition.replace(R.id.container, fragment1)
        if (name == 1) {
            transition.replace(R.id.container, fragment1)
            iv1.setBackgroundResource(R.drawable.aem_home_pressed)
            iv2.setBackgroundResource(R.drawable.aem_stats_non_pressed)
            iv3.setBackgroundResource(R.drawable.aem_notify_non_pressed)
        }
        if (name == 2) {
            transition.replace(R.id.container, fragment2)
            iv1.setBackgroundResource(R.drawable.aem_home_non_pressed)
            iv2.setBackgroundResource(R.drawable.aem_stats_pressed)
            iv3.setBackgroundResource(R.drawable.aem_notify_non_pressed)
        }
        if (name == 3) {
            transition.replace(R.id.container, fragment3)
            iv1.setBackgroundResource(R.drawable.aem_home_non_pressed)
            iv2.setBackgroundResource(R.drawable.aem_stats_non_pressed)
            iv3.setBackgroundResource(R.drawable.aem_notify_pressed)
        }
        return transition.commit()
    }


}
