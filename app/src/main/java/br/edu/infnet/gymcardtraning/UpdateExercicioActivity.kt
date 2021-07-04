package br.edu.infnet.gymcardtraning

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import br.edu.infnet.gymcardtraning.Model.Exercicio
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_cadastro_exercicios.*
import java.util.*

class UpdateExercicioActivity : AppCompatActivity() {
    private var SelecionarUri: Uri? = null
    var uid =""
    var nomeExercicio: String? = ""
    var tempoExercicio: String? = ""
    var descansoExercicio: String? = ""
    var url: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {




        uid = intent.extras?.getString("uid")!!
        nomeExercicio = intent.extras?.getString("nomeExercicio")!!
        tempoExercicio =intent.extras?.getString("tempoExercicio")!!
        descansoExercicio =intent.extras?.getString("descansoExercicio")!!
        url =intent.extras?.getString("url")!!

        edit_nome_exercicio.setText(nomeExercicio)
        edit_descanso_exercicio.setText(descansoExercicio)
        edit_qtd_exercicio.setText(tempoExercicio)
        bt_selecionar_foto.text = url.toString()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_exercicios)

        bt_selecionar_foto.setOnClickListener {
            SelecionarFotoDaGaleria()
        }
        bt_cadastrar_exercicio_firebase.setOnClickListener {
            SalvarDadosNoFirebase()

        }




    }
    private fun IrParaHome(){
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }

    private fun SalvarDadosNoFirebase() {
        val nomeArquivo = UUID.randomUUID().toString()
        val referencia = FirebaseStorage.getInstance().getReference(
            "/imagens/${nomeArquivo}" )

        SelecionarUri?.let {
            referencia.putFile(it)
                .addOnSuccessListener {
                    referencia.downloadUrl.addOnSuccessListener {
                        val url = it.toString()
                        val nomeExercicio = edit_nome_exercicio.text.toString()
                        val tempoExercicio = edit_qtd_exercicio.text.toString()
                        val descansoExercicio = edit_descanso_exercicio.text.toString()
                        val uid = FirebaseAuth.getInstance().uid

                        val Exercicios = Exercicio(url, nomeExercicio,tempoExercicio,descansoExercicio)

                        FirebaseFirestore.getInstance().collection("Exercicios").document(uid!!)
                            .set(Exercicios).addOnSuccessListener {

                                Toast.makeText(this, "Exercicio cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                                IrParaHome()

                            }.addOnFailureListener {
                                Toast.makeText(this, "Exercicio não cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                            }


                    }
                }
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0){
            SelecionarUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, SelecionarUri)
            foto_exercicio.setImageBitmap(bitmap)
            bt_selecionar_foto.alpha = 0f
        }
    }

    private fun SelecionarFotoDaGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent,0)
    }


}