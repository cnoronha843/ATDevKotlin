package br.edu.infnet.gymcardtraning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.infnet.gymcardtraning.databinding.ActivityDetalhesExerciciosBinding

class DetalhesExercicios : AppCompatActivity() {
    lateinit var binding: ActivityDetalhesExerciciosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesExerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        binding.playVideo.setOnClickListener {
            val intent = Intent(this, Video::class.java)
            startActivity(intent)
        }


    }
    private fun Toolbar(){
        val toolbar_detalhes = binding.toolbarDetalhes
        toolbar_detalhes.setNavigationIcon(getDrawable(R.drawable.ic_voltar_foreground))
        toolbar_detalhes.setNavigationOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

}