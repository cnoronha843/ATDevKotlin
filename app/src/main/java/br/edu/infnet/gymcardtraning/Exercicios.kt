package br.edu.infnet.gymcardtraning

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.infnet.gymcardtraning.Model.Exercicio
import br.edu.infnet.gymcardtraning.Onclick.OnItemClickListener
import br.edu.infnet.gymcardtraning.Onclick.addOnItemClickListener
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_exercios.*
import kotlinx.android.synthetic.main.lista_exercicios.view.*


class Exercicios : Fragment() {


    private lateinit var Adapter: GroupAdapter<ViewHolder>

      override fun onCreateView(
          inflater: LayoutInflater,container: ViewGroup?,
          saveInstances: Bundle?
      ): View?{
          return inflater.inflate(R.layout.fragment_exercios, container, false)
      }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Adapter = GroupAdapter()
        recycler_exercicios.adapter = Adapter

        BuscarExercicios()

        recycler_exercicios.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemCliecked(position: Int, view: View) {
                when{
                    position == 0 ->  DetalhesExercicio()
                }
            }
        })

    }

    private fun DetalhesExercicio(){
        val intent = Intent(requireActivity(), DetalhesExercicios::class.java)
        startActivity(intent)
    }


    private inner class ExercicioItem(internal  val adExercicios: Exercicio): Item<ViewHolder>() {

        override fun getLayout(): Int {
            return R.layout.lista_exercicios


        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.nomeExercicio.text = adExercicios.nomeExercicio
            viewHolder.itemView.qtdExercicio.text = adExercicios.tempoExercicio
            viewHolder.itemView.tempoDescansoExercicio.text = adExercicios.descansoExercicio
            //Picasso.get().load(adExercicios.uid).into(viewHolder.itemView.fotoExercicio)

        }
    }

    private fun BuscarExercicios() {
        FirebaseFirestore.getInstance().collection("Exercicios").addSnapshotListener{
            snapshot, exception -> exception?.let {
                return@addSnapshotListener
        }
            snapshot?.let {
                for(doc in snapshot){
                    val exercicios = doc.toObject(Exercicio::class.java)
                    Adapter.add(ExercicioItem(exercicios))
                }
            }
        }
    }
}