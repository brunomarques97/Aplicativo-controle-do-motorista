package com.example.aplicativocontroledomotorista

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var editTextDate: EditText
    private lateinit var editTextKilometer: EditText
    private lateinit var editTextEarnings: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando os componentes da UI
        editTextDate = findViewById(R.id.editTextDate)
        editTextKilometer = findViewById(R.id.editTextKilometer)
        editTextEarnings = findViewById(R.id.editTextEarnings)
        buttonSave = findViewById(R.id.buttonSave)

        // Configura o DatePicker (calendário)
        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView(calendar)
        }

        editTextDate.setOnClickListener {
            DatePickerDialog(
                this@MainActivity,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Lógica para o botão de salvar
        buttonSave.setOnClickListener {
            val date = editTextDate.text.toString()
            val kilometer = editTextKilometer.text.toString()
            val earnings = editTextEarnings.text.toString()

            if (date.isEmpty() || kilometer.isEmpty() || earnings.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            } else {
                // Aqui você pode adicionar sua lógica para salvar os dados
                // Por exemplo, enviar para um banco de dados local (Room) ou remoto.
                val message = "Dados salvos:\nData: $date\nQuilometragem: $kilometer km\nGanho Bruto: R$ $earnings"
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()

                // Opcional: Limpar os campos após salvar
                editTextDate.text.clear()
                editTextKilometer.text.clear()
                editTextEarnings.text.clear()
            }
        }
    }

    private fun updateDateInView(calendar: Calendar) {
        val myFormat = "dd/MM/yyyy" // Formato desejado para a data
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        editTextDate.setText(sdf.format(calendar.time))
    }
}