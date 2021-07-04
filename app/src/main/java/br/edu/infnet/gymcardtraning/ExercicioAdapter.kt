//package br.edu.infnet.gymcardtraning
//
//import android.content.ContentValues
//import android.content.Context
//import android.content.Intent
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import androidx.recyclerview.widget.RecyclerView
//import br.edu.infnet.gymcardtraning.Model.Exercicio
//import com.google.firebase.firestore.FirebaseFirestore
//
//class ExercicioAdapter (
//    var exerciciosList: MutableList<Exercicio>,
//    private val context: Context,
//    private val firestoreDB: FirebaseFirestore
//
//    )
//    : RecyclerView.Adapter<ExercicioAdapter.ExerciciosViewholder>(){
//
//
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciciosViewholder {
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_exercicios, parent, false)
//
//            return ExerciciosViewholder(view)
//        }
//
//       inner class ExerciciosViewholder internal constructor(itemView: View):
//            RecyclerView.ViewHolder(itemView) {
//
//            var nomeExercicio: TextView = itemView.findViewById(R.id.tv_exercico)
//            var quantidade: TextView = itemView.findViewById(R.id.tv_quantidade)
//            var descanso: TextView = itemView.findViewById(R.id.tv_descanso)
//            var btnDelete: ImageView = itemView.findViewById(R.id.btn_delete)
//            var editExercicio: LinearLayout = itemView.findViewById(R.id.edit_exercicio)
//        }
//
//        override fun onBindViewHolder(holder: ExerciciosViewholder, position: Int) {
//            val exercicio = exerciciosList[position]
//
//            holder.nomeExercicio.text = exercicio.nomeExercicio.toString()
//            holder.descanso.text = exercicio.descansoExercicio
//            holder.quantidade.text = exercicio.tempoExercicio.toString()
//
//
//
//
//
//
//
//            holder.btnDelete.setOnClickListener {
//
//                deleteExercicio(exercicio, position)
//            }
//
//            holder.editExercicio.setOnClickListener{
//
//
//                    val intent = Intent(context, UpdateExercicioActivity::class.java)
//                    intent.putExtra("UID", exercicio.uid)
//                    intent.putExtra("EXERCICIO", exercicio.nomeExercicio)
//                    intent.putExtra("TEMPOEX", exercicio.tempoExercicio)
//                    intent.putExtra("DESCANSO", exercicio.descansoExercicio)
//                    intent.putExtra("URL", exercicio.url)
//                    context.startActivity(intent)
//
//
//            }
//        }
//
//        override fun getItemCount(): Int {
//            return exerciciosList.size
//        }
//
//        private fun deleteExercicio(exercicio: Exercicio, position: Int) {
//            exercicio.uid.let {
//                firestoreDB.collection("Exercicios")
//                    .document(it)
//                    .delete()
//                    .addOnCompleteListener {
//                        exerciciosList.removeAt(position)
//                        notifyItemRemoved(position)
//                        notifyItemRangeChanged(position, exerciciosList.size)
//                        Toast.makeText(context, "Exercise has been deleted!", Toast.LENGTH_SHORT).show()
//                    }
//            }
//        }
//
//        private fun updateExercicios(uid: String, nomeExercicio: String?, tempoExercicio: String?,
//                                     descansoExercicio: String,  url: String?) {
//            val exercicio = Exercicio(uid, nomeExercicio, tempoExercicio, descansoExercicio, url)
//
//            FirebaseFirestore.getInstance().collection("Exercicios")
//                .document(exercicio.uid)
//                .set(exercicio.toMap())
//                .addOnSuccessListener {
//                    Log.e(ContentValues.TAG, "Exercicio document update successful!")
//                    Toast.makeText(context, "Product has been updated!", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener { e ->
//                    Log.e(ContentValues.TAG, "Erro ao adicionar documento de exercício", e)
//                    Toast.makeText(context, "O exercício não pôde ser atualizado!", Toast.LENGTH_SHORT).show()
//                }
//        }
//
//
//}