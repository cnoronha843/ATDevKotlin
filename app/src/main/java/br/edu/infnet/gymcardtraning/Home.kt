package br.edu.infnet.gymcardtraning

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import br.edu.infnet.gymcardtraning.databinding.ActivityHomeBinding
import br.edu.infnet.gymcardtraning.ui.activities.WeatherActivity
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.paypal.android.sdk.payments.*
import java.math.BigDecimal

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val exerciciosFragment = Exercicios()
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.frameContainer,exerciciosFragment)
        fragment.commit()


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener (this,)



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.nav_execicios){
            val exerciciosFragment = Exercicios()
            val fragment = supportFragmentManager.beginTransaction()
            fragment.replace(R.id.frameContainer,exerciciosFragment)
            fragment.commit()

        } else if (id == R.id.nav_add_exercicio){
            var intent = Intent(this, CadastroExercicio::class.java)
            startActivity(intent)

        }else if (id == R.id.nav_mensalidade) {
            var intent = Intent(this, Mensalidade::class.java)
            startActivity(intent)

        }else if (id == R.id.nav_tempo) {
            var intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)

        }else if (id == R.id.nav_contato){

            EnviarEmail()

        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun EnviarEmail() {
        val PACKAGEM_GOOGLEMAIL = "com.google.android.gm"
        val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf("")) //Enviar e-mail
        email.putExtra(Intent.EXTRA_SUBJECT,"")// Enviar um assunto de e-mail
        email.putExtra(Intent.EXTRA_TEXT,"") // Definir um texto para o nosso e-mail

        //Configurações de apps de envio de e-mail

        email.type = "message/rec822"
        email.setPackage(PACKAGEM_GOOGLEMAIL)
        startActivity(Intent.createChooser(email, "Escolha o APP de email"))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id==R.id.item_sair){
            FirebaseAuth.getInstance().signOut()
            LoginManager.getInstance().logOut()
            VoltarParaFormLogin()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun VoltarParaFormLogin() {
        var  intent = Intent(this, FormLogin::class.java)
        startActivity( intent )
        finish()
    }

}