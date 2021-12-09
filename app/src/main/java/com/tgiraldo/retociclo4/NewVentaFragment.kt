package com.tgiraldo.retociclo4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.room.Room
import com.tgiraldo.retociclo4.room_database.VentaDatabase
import com.tgiraldo.retociclo4.room_database.Ventas
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewVentaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewVentaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_venta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spiVenta: Spinner = view.findViewById(R.id.spiVenta)
        val edtTime: EditText = view.findViewById(R.id.edtTime)
        val edtPlace: EditText = view.findViewById(R.id.edtPlace)
        val edtFecha: EditText = view.findViewById(R.id.edtFechaVenta)
        val edtCedula: EditText = view.findViewById(R.id.edtCedulaVendedor)
        val edtNombre: EditText = view.findViewById(R.id.edtNombreCliente)
        val edtDirection: EditText = view.findViewById(R.id.edtDireccionCliente)
        val edtLatitud: EditText = view.findViewById(R.id.edtLatitud)
        val edtLongitud: EditText = view.findViewById(R.id.edtLongitud)
        val edtTipoCortina: EditText = view.findViewById(R.id.edtTipoCortina)
        val edtAncho: EditText = view.findViewById(R.id.edtAnchoCortina)
        val edtAlto: EditText = view.findViewById(R.id.edtAltoCortina)
        val edtCuotas: EditText = view.findViewById(R.id.edtCuotaVenta)
        val btnNewTask: Button = view.findViewById(R.id.btnNewTask)
        //val total = edtAncho * edtAlto;
//        var tareas = arrayOf("Hacer ejercicio", "Estudiar", "Mercar")
        var tareas: ArrayList<Venta> = ArrayList()
        tareas.add(Venta(1,"Venta en Línea", "Ventas ofertadas en Línea", "Plataformas"))
        tareas.add(Venta(2, "Venta en Punto", "Ventas de origen en tienda", "Tienda principal"))
        tareas.add(Venta(3, "Venta Indirecta", "Ventas por refereridos", "Referidos"))
        val taskAdapter = ArrayAdapter(context?.applicationContext!!, android.R.layout.simple_spinner_item, tareas)
        taskAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiVenta.adapter = taskAdapter
        btnNewTask.setOnClickListener {
            var ventaSeleccionada: Venta = spiVenta.selectedItem as Venta
            val room: VentaDatabase = Room.databaseBuilder(context?.applicationContext!!,
                VentaDatabase::class.java, "VentaDatabase").build()
            var todoDao = room.ventasDao()
            var task = Ventas(0, ventaSeleccionada.venta, edtTime.text.toString(), edtPlace.text.toString(),edtFecha.text.toString(),edtCedula.text.toString(),edtNombre.text.toString(),edtDirection.text.toString(),edtLatitud.text.toString(),edtLongitud.text.toString(),edtTipoCortina.text.toString(),edtAncho.text.toString(),edtAlto.text.toString(),edtCuotas.text.toString())
            //val dbFirebase = FirebaseFirestore.getInstance()
            runBlocking {
                launch {
                    var result = todoDao.insertarVenta(task)
  /*                  if(result != -1L){
                        dbFirebase.collection("Ventas")
                            .document(result.toString())
                            .set(
                                hashMapOf(
                                    "title" to ventaSeleccionada.venta,
                                    "time" to edtTime.text.toString(),
                                    "place" to edtPlace.text.toString()
                                )
                            )
                    }*/
//                   Toast.makeText(context?.applicationContext!!, "" + result, Toast.LENGTH_LONG).show()
                }
            }
            Navigation.findNavController(view).navigate(R.id.nav_ventas)

/*           Toast.makeText(context?.applicationContext!!, ventaSeleccionada.venta, Toast.LENGTH_LONG).show()*/
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewVentaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewVentaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}