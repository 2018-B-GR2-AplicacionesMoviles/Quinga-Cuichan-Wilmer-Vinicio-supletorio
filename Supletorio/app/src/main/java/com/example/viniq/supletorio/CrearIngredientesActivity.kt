package com.example.viniq.supletorio

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.tapadoo.alerter.Alerter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_crear_ingredientes.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CrearIngredientesActivity : AppCompatActivity() {


    var tipo = false
    var idC = 0
    var opcion11: String = ""
    var opcion22: String = ""

    lateinit var comboInstalado: Spinner;
    var opcion: String = ""
    lateinit var comboInstalado1: Spinner;
    var opcion1: String = ""


    var pathActualFoto = ""
    var respuestaBarcode = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_ingredientes)


///spinner opcional
        comboInstalado = findViewById(R.id.spinner_opcional)
        val adapter: ArrayAdapter<CharSequence>
        adapter = ArrayAdapter.createFromResource(this, R.array.combo_intalado, android.R.layout.simple_spinner_item)

        comboInstalado.setAdapter(adapter);
        comboInstalado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                opcion = parent!!.getItemAtPosition(position).toString()
                Log.i("opcion", opcion)
                Toast.makeText(
                    parent!!.context,
                    "Seleccionado:" + parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("adaptador", "${parent}")
            }
        }

             ///spinner necesitaRefrigeracion

           comboInstalado1 = findViewById(R.id.spinner2_Refrigeracion)
           val adapter1: ArrayAdapter<CharSequence>
           adapter1 = ArrayAdapter.createFromResource(this, R.array.combo_intalado, android.R.layout.simple_spinner_item)

           comboInstalado1.setAdapter(adapter1);
           comboInstalado1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
               override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                   opcion1 = parent!!.getItemAtPosition(position).toString()
                   Log.i("opcion", opcion1)
                   Toast.makeText(
                       parent!!.context,
                       "Seleccionado:" + parent.getItemAtPosition(position).toString(),
                       Toast.LENGTH_LONG
                   ).show()
               }

               override fun onNothingSelected(parent: AdapterView<*>?) {
                   Log.i("adaptador", "${parent}")
               }
           }

       /*  val type = intent.getStringExtra("tipo")

            val ingredientesRecivida = intent.getParcelableExtra<Ingredientes>("Ingredientes")
            idC = ingredientesRecivida.idComida
            Log.i("csdcsdc","cccccccc")


            if (type == "Edit") {
                txt_id_ingredienteCrear.setText(ingredientesRecivida.idIngredientes.toString())
                txt_nombres_ingredienteCrear.setText(ingredientesRecivida.nombreIngrediente.toString())
                txt_cantidadCrear.setText(ingredientesRecivida.cantidad.toString())
                txt_descripcionPreparacionCrear.setText(ingredientesRecivida.descripcionPreparacion.toString())
                txt_tipoIngrediente.setText(ingredientesRecivida.tipoIngrediente.toString())
                tipo = true
            }

*/


        btn_foto.setOnClickListener {
            this.tomarFoto()
        }
        btn_guardar_ingredientes.setOnClickListener {

            this.guardarDatos()


        }

    }

    fun guardarDatos() {
        if (txt_id_ingredienteCrear.text.toString().isEmpty() ||
            txt_nombres_ingredienteCrear.text.toString().isEmpty() ||
            txt_cantidadCrear.text.toString().isEmpty() ||
            txt_descripcionPreparacionCrear.text.toString().isEmpty() ||
            txt_tipoIngrediente.text.toString().isEmpty()) {


            Alerter.create(this).setTitle("Campos Vacios")
                .setText("Completa la informacion de todos los campos")
                .setBackgroundColorRes(R.color.error_color_material_dark)
                .enableSwipeToDismiss().show()
        }
        else {
            Log.i("ddddddddddddddddd", "ffffffffffffffffff")


            var idIngredientes = txt_id_ingredienteCrear.text.toString().toInt()

            Log.i("ddddddddddddddddd", idIngredientes.toString())

            var nombreIngrediente = txt_nombres_ingredienteCrear.text.toString()
            var cantidad = txt_cantidadCrear.text.toString().toInt()
            var descripcionPreparacion = txt_descripcionPreparacionCrear.text.toString()

             var tipoIngrediente = txt_tipoIngrediente.text.toString()



            var idComida = idC
            var ingredidentesIngredientes = Ingredientes(
                idIngredientes,
                nombreIngrediente,
                cantidad,
                descripcionPreparacion,
                true,
                tipoIngrediente,
                true,
                idComida)


            Log.i("ddddddddddddddddd", ingredidentesIngredientes.toString())

            BaseDatosIngredientes.insertarIngrediente(ingredidentesIngredientes)

          /*  if (tipo == true) {

                BaseDatosIngredientes.editarIngrediente(ingredientes)
            } else {

                BaseDatosIngredientes.insertarIngrediente(ingredientes)
                Log.i("mensaje", ingredientes.toString())
            }*/

            Toasty.success(this, "Datos registrados", Toast.LENGTH_LONG, true).show()
            val intent = Intent(this, ListaIngredientesActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    fun tomarFoto() {
        val archivoImgen = crearArchivo("JPEG", Environment.DIRECTORY_PICTURES, ".jpg")
        pathActualFoto = archivoImgen.absolutePath
        enviarIntentFoto(archivoImgen)
    }

    private fun crearArchivo(prefijo: String, directorio: String, extension: String): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = prefijo + timeStamp + "_"
        val storageDir = getExternalFilesDir(directorio)

        return File.createTempFile(imageFileName, extension, storageDir)
    }

    private fun enviarIntentFoto(archivoFile: File) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoURI: Uri = FileProvider.getUriForFile(this, "com.example.viniq.supletorio", archivoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, TOMAR_FOTO_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            TOMAR_FOTO_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {

                    obtenerInfoCodigoBarras(obtenerBitmapDeArchivo(pathActualFoto))
                }
            }
        }
    }

    fun obtenerBitmapDeArchivo(path: String): Bitmap {
        val archivoImagen = File(path)
        return BitmapFactory.decodeFile(archivoImagen.getAbsolutePath())
    }


    fun obtenerInfoCodigoBarras(bitmap: Bitmap) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val detector = FirebaseVision.getInstance()
            .visionBarcodeDetector
        Log.i("mensaje", "------- Entro a detectar")
        val result = detector.detectInImage(image)
            .addOnSuccessListener { barCodes ->
                Log.i("mensaje", "------- tamano del barcode ${barCodes.size}")
                respuestaBarcode.add("Ejemplo")
                for (barcode in barCodes) {
                    val bounds = barcode.getBoundingBox()
                    val corners = barcode.getCornerPoints()

                    val rawValue = barcode.getRawValue()

                    Log.i("mensaje", "------- $bounds")
                    Log.i("mensaje", "------- $corners")
                    Log.i("mensaje", "------- $rawValue")

                    respuestaBarcode.add(rawValue.toString())
                }
                txt_id_ingredienteCrear.setText(respuestaBarcode[1])
            }
            .addOnFailureListener {
                Log.i("mensaje", "------- No reconocio nada")
            }
    }

    companion object {
        val TOMAR_FOTO_REQUEST = 1
    }

}
