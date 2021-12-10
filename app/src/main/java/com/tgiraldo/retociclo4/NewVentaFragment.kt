package com.tgiraldo.retociclo4

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
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
        val spiCortina: Spinner = view.findViewById(R.id.spiCortina)
        val edtTime: EditText = view.findViewById(R.id.edtTime)
        //val edtPrecio: EditText = view.findViewById(R.id.edtPrecio)
        val edtPrecio: EditText = view.findViewById(R.id.edtPrecio)
        val edtFecha: EditText = view.findViewById(R.id.edtFechaVenta)
        val edtCedula: EditText = view.findViewById(R.id.edtCedulaVendedor)
        val edtNombre: EditText = view.findViewById(R.id.edtNombreCliente)
        val edtDirection: EditText = view.findViewById(R.id.edtDireccionCliente)
        val edtLatitud: EditText = view.findViewById(R.id.edtLatitud)
        val edtLongitud: EditText = view.findViewById(R.id.edtLongitud)
        //val edtTipoCortina: EditText = view.findViewById(R.id.edtTipoCortina)
        val spiVenta: Spinner = view.findViewById(R.id.spiVenta)
        val edtAncho: EditText = view.findViewById(R.id.edtAnchoCortina)
        val edtAlto: EditText = view.findViewById(R.id.edtAltoCortina)
        val edtCuotas: EditText = view.findViewById(R.id.edtCuotaVenta)
        val btnNewVenta: Button = view.findViewById(R.id.btnNewVenta)

        //val total = edtAncho * edtAlto;

//        var tareas = arrayOf("Hacer ejercicio", "Estudiar", "Mercar")
        var cortinas: ArrayList<Cortina> = ArrayList()
        cortinas.add(Cortina(1,"Cortina Alemana", "Negra", 500000))
        cortinas.add(Cortina(2, "Cortina Rusa", "Verde", 800000))
        cortinas.add(Cortina(3, "Cortina Arabe", "Blanca", 1500000))
        val CortinaAdapter = ArrayAdapter(context?.applicationContext!!, android.R.layout.simple_spinner_item, cortinas)
        CortinaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiCortina.adapter = CortinaAdapter

        var tareas: ArrayList<Venta> = ArrayList()
        tareas.add(Venta(1,"Venta en Línea", "Ventas ofertadas en Línea", "Plataformas"))
        tareas.add(Venta(2, "Venta en Punto", "Ventas de origen en tienda", "Tienda principal"))
        tareas.add(Venta(3, "Venta Indirecta", "Ventas por refereridos", "Referidos"))
        val taskAdapter = ArrayAdapter(context?.applicationContext!!, android.R.layout.simple_spinner_item, tareas)
        taskAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiVenta.adapter = taskAdapter

        btnNewVenta.setOnClickListener {
            var Areatotal = Integer.parseInt( edtAncho.getText().toString() ) * Integer.parseInt( edtAlto.getText().toString() );
            var ventaSeleccionada: Venta = spiVenta.selectedItem as Venta
            var cortinaSeleccionada: Cortina = spiCortina.selectedItem as Cortina
            var precioCortina=0
            if(cortinaSeleccionada.tipo.equals("Cortina Alemana")){
                precioCortina=55000
                //edtPrecio.setText("550000")
            }else if(cortinaSeleccionada.tipo.equals("Cortina Rusa")){
                precioCortina=80000
                //edtPrecio.setText("800000")
            }else if(cortinaSeleccionada.tipo.equals("Cortina Arabe")){
                precioCortina=120000
                //edtPrecio.setText("1200000")
                Toast.makeText(context,"CORTINA ARABE",Toast.LENGTH_LONG).show()
            }
            edtPrecio.setText(precioCortina.toString())
            val valorCuotaSemanal = ((precioCortina*Areatotal)/6).toString()

            val room: VentaDatabase = Room.databaseBuilder(context?.applicationContext!!,
                VentaDatabase::class.java, "VentaDatabase").build()
            var todoDao = room.ventasDao()
            var task = Ventas(0, ventaSeleccionada.venta, edtTime.text.toString(), edtPrecio.text.toString(),edtFecha.text.toString(),edtCedula.text.toString(),edtNombre.text.toString(),edtDirection.text.toString(),edtLatitud.text.toString(),edtLongitud.text.toString(),cortinaSeleccionada.tipo,edtAncho.text.toString(),edtAlto.text.toString(),edtCuotas.text.toString(),Areatotal.toString(), valorCuotaSemanal)
            val dbFirebase = FirebaseFirestore.getInstance()
            runBlocking {
                launch {
                    var result = todoDao.insertarVenta(task)

                    if(result != -1L){
                        dbFirebase.collection("Ventanal")
                            .document(result.toString())
                            .set(
                                hashMapOf(
                                    /*"title" to ventaSeleccionada.venta,
                                    "time" to edtTime.text.toString(),
                                    "place" to edtPlace.text.toString()*/
                                "venta" to ventaSeleccionada.venta,
                                "descripcion" to edtTime.text.toString(),
                                "precio" to edtPrecio.text.toString(),
                                    "fecha" to edtFecha.text.toString(),
                                    "cedula" to edtCedula.text.toString(),
                                    "nombre" to edtNombre.text.toString(),
                                    "direccion" to edtDirection.text.toString(),
                                    "latitud" to edtLatitud.text.toString(),
                                    "longitud" to edtLongitud.text.toString(),
                                    "tipoCortina" to cortinaSeleccionada.tipo,
                                    "ancho" to edtAncho.text.toString(),
                                    "alto" to edtAlto.text.toString(),
                                    "cuotas" to edtCuotas.text.toString(),
                                    "areaTotal" to Areatotal.toString(),
                                    "valorCuotaSemanal" to valorCuotaSemanal

                                )
                            )
                    }

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