package ian.meda.ligaitesosoccer

import android.content.Context
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.get
import ian.meda.ligaitesosoccer.utils.SESSION_USERNAME
import ian.meda.ligaitesosoccer.utils.SESSION_USERTYPE
import ian.meda.ligaitesosoccer.utils.SHARED_PREFERENCES
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_calendario, R.id.nav_tablageneral, R.id.nav_goleadores,
                R.id.nav_foro, R.id.nav_equipo, R.id.nav_codigo
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val sharedPreferences = this!!.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val sessionId = sharedPreferences.getString(SESSION_USERTYPE, "")
        if (sessionId=="ADMIN" || sessionId=="CAPITAN"){
            navView.menu[3].isVisible = true
        }
        if (sessionId=="MIEMBRO" || sessionId=="CAPITAN"){
            navView.menu[4].isVisible = true
        }


        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
