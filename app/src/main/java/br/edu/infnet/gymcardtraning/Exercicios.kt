package br.edu.infnet.gymcardtraning

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.gymcardtraning.Model.Exercicio

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
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



    }




    private inner class ExercicioItem(internal  val adExercicios: Exercicio): Item<ViewHolder>() {

        override fun getLayout(): Int {
            return R.layout.lista_exercicios


        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.tv_exercico.text = adExercicios.nomeExercicio
            viewHolder.itemView.tv_quantidade.text = adExercicios.tempoExercicio
            viewHolder.itemView.tv_descanso.text = adExercicios.descansoExercicio
            Picasso.get().load(adExercicios.uid).into(viewHolder.itemView.fotoExercicio)

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

//    private var exercicioAdapter: ExercicioAdapter? = null
//     private var exercicios  = mutableListOf<Exercicio>()
//    private lateinit var layoutManager: LinearLayoutManager
//    private var firestoreDB: FirebaseFirestore? = null
//    private var firestoreListener: ListenerRegistration? = null
//
//
//
//    override fun onCreateView(
//          inflater: LayoutInflater,container: ViewGroup?,
//          saveInstances: Bundle?
//      ): View?{
//          return inflater.inflate(R.layout.fragment_exercios, container, false)
//      }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        firestoreDB = FirebaseFirestore.getInstance()
//        BuscarExercicios()
//        exercicioAdapter = ExercicioAdapter(
//            exercicios,
//         requireContext(),
//        FirebaseFirestore.getInstance()
//         )
//        layoutManager = LinearLayoutManager(context)
//        recycler_exercicios.layoutManager = layoutManager
//        recycler_exercicios.adapter = exercicioAdapter
//
//
//
//
//
//    }
//    override fun onDestroy() {
//        super.onDestroy()
//
//        firestoreListener!!.remove()
//    }
//
//    private fun partItemClicked(exercicio: Exercicio) {
//        val intent = Intent(requireContext(), UpdateExercicioActivity::class.java)
//        intent.putExtra("UpdateExercicioId", exercicio.uid)
//        intent.putExtra("UpdateNomeExercicio", exercicio.nomeExercicio)
//        intent.putExtra("UpdateTempoExercicio", exercicio.tempoExercicio)
//        intent.putExtra("UpdateDescansoExercicio", exercicio.descansoExercicio)
//        intent.putExtra("UpdateFotoExercicio", exercicio.url)
//
//        startActivity(intent)
//    }
//
//
//
//
//        private fun updateExercicios(uid: String, nomeExercicio: String?, tempoExercicio: String?,
//                                     descansoExercicio: String,  url: String?) {
//            val exercicio = Exercicio(uid, nomeExercicio, tempoExercicio, descansoExercicio, url)
//
//            FirebaseFirestore.getInstance().collection("Exercicios")
//                .document(exercicio.uid)
//                .set(exercicio.toMap())
//                .addOnSuccessListener {
//                    Log.e(TAG, "Exercicio document update successful!")
//                    Toast.makeText(context, "Exercicio has been updated!", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener { e ->
//                    Log.e(TAG, "Erro ao adicionar documento de exercício", e)
//                    Toast.makeText(context, "O exercício não pôde ser atualizado!", Toast.LENGTH_SHORT).show()
//                }
//        }
//    private fun BuscarExercicios() {
////        FirebaseFirestore.getInstance().collection("Exercicios").addSnapshotListener{
////                snapshot, exception -> exception?.let {
////            return@addSnapshotListener
////        }
////
//////            exercicios = snapshot?.toObjects(Exercicio::class.java) as MutableList<Exercicio>
////            snapshot?.let {
////                for(doc in snapshot){
////                    val exercicio = doc.toObject(Exercicio::class.java)
////                    exercicios.add(exercicio)
////
////                }
////            }
////
////
////        }
//
//        firestoreDB!!.collection("Exercicios")
//            .get()
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    val exerciciosList = mutableListOf<Exercicio>()
//
//                    for (doc in task.result!!) {
//                        val exercicio = doc.toObject(Exercicio::class.java)
//                        updateExercicios(doc.id, exercicio.nomeExercicio, exercicio.tempoExercicio)
//                        exerciciosList.add(exercicio)
//                    }
//
//                    exercicioAdapter = ExercicioAdapter(exerciciosList, requireContext(), firestoreDB!!) {
//                            partItem: Exercicio -> partItemClicked(partItem) }
//                    val mLayoutManager = LinearLayoutManager(requireContext())
//                    recycler_exercicios.layoutManager = mLayoutManager
//                    recycler_exercicios.itemAnimator = DefaultItemAnimator()
//                    recycler_exercicios.adapter = exercicioAdapter
//                    recycler_exercicios.addItemDecoration(
//                        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
//                    )
//                } else {
//                    Log.d(TAG, "Error getting documents: ", task.exception)
//                }
//            }
//    }


}



