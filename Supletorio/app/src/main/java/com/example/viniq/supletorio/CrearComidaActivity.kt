package com.example.viniq.supletorio

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.tapadoo.alerter.Alerter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_crear_comida.*

class CrearComidaActivity : AppCompatActivity() {


    lateinit var comboInstalado: Spinner;
    var opcion: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_comida)


        comboInstalado = findViewById(R.id.spinner_picante)
        val adapter: ArrayAdapter<CharSequence>
        adapter = ArrayAdapter.createFromResource(this, R.array.combo_intalado, android.R.layout.simple_spinner_item)

        comboInstalado.setAdapter(adapter);
        comboInstalado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                opcion = parent!!.getItemAtPosition(position).toString()
                Log.i("opcion", opcion)
                Toast.makeText(parent!!.context, "Seleccionado:" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("adaptador", "${parent}")
            }
        }

        button2_CrearGuardar.setOnClickListener {
            this.guardarDatos()
        }

        button_CrearCancelar.setOnClickListener {
            this.cancelar()
        }

    }

    fun guardarDatos() {
        if (editText_nombrePlato.text.toString().isEmpty() || editText2_descripcionPlato.text.toString().isEmpty() || editText3_nacionalidad.text.toString().isEmpty() || editText4_numeroPersonas.text.toString().isEmpty()) {
            Alerter.create(this).setTitle("Campos Vacios").setText("Completa la informacion de todos los campos").setBackgroundColorRes(R.color.error_color_material_dark).enableSwipeToDismiss().show()
        } else {
            var nombrePlato = editText_nombrePlato.text.toString()
            var descripcionPlato = editText2_descripcionPlato.text.toString()
            var nacionalidad = editText3_nacionalidad.text.toString()
            var numeroPersonas = editText4_numeroPersonas.text.toString().toInt()
            var picante: Boolean
            if (opcion.equals("Si", true)) {
                picante = true
            } else {
                picante = false
            }

            var comidaComida = Comida(0, nombrePlato, descripcionPlato, nacionalidad, numeroPersonas, picante)

             BaseDatosComida.insertarComida(comidaComida)

            Toasty.success(this, "Datos registrados", Toast.LENGTH_LONG, true).show()
            val intent = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(intent)
        }
    }


    fun cancelar() {
        val cancelar = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(cancelar)
        this.finish()


    }


}
