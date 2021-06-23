package br.edu.infnet.gymcardtraning

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import br.edu.infnet.gymcardtraning.databinding.ActivityDetalhesExerciciosBinding
import br.edu.infnet.gymcardtraning.databinding.ActivityVideoBinding
import java.net.URI
import java.util.logging.Level.parse

class Video : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val videoUrl = Uri.parse("https://firebasestorage.googleapis.com/v0/b/gymcardtraning.appspot.com/o/imagens%2F7305392e-190c-493f-8970-e7bbd4ed4f39?alt=media&token=a486dc07-f945-4bbb-b77c-4aec81a4cd3c")
        val video = binding.video
        video.setMediaController(MediaController(this))
        video.setVideoURI(videoUrl)
        video.requestFocus()
        video.start()


    }
}