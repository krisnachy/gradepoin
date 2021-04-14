package com.krisnachy.presensi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtAbsen : TextInputEditText
    private lateinit var edtTugas : TextInputEditText
    private lateinit var edtUts : TextInputEditText
    private lateinit var edtUas : TextInputEditText
    private lateinit var btnSubmit : Button
    private lateinit var tvNilai : TextView
    private lateinit var tvGrade : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtAbsen = findViewById(R.id.edtAbsen)
        edtTugas = findViewById(R.id.edtTugas)
        edtUts = findViewById(R.id.edtUts)
        edtUas = findViewById(R.id.edtUas)
        btnSubmit = findViewById(R.id.button)
        tvNilai = findViewById(R.id.tv_total)
        tvGrade = findViewById(R.id.tv_grade)

        btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button -> {
                var isEmptyField = false
                val nilaiAbsen = edtAbsen.text.toString().trim()
                val nilaiTugas = edtTugas.text.toString().trim()
                val nilaiUts = edtUts.text.toString().trim()
                val nilaiUas = edtUas.text.toString().trim()

                if (nilaiAbsen.isEmpty()){
                    isEmptyField = true
                    edtAbsen.error = "Masukkan Nilai Absen"
                }else if (nilaiAbsen.toInt() > 100 || nilaiAbsen.toInt() < 0){
                    isEmptyField = true
                    edtAbsen.error = "Range harus diatas 0 hingga 100"
                }

                if (nilaiTugas.isEmpty()){
                    isEmptyField = true
                    edtTugas.error = "Masukkan Nilai Tugas"
                }else if (nilaiTugas.toInt() > 100 || nilaiTugas.toInt() < 0){
                    isEmptyField = true
                    edtAbsen.error = "Range harus diatas 0 hingga 100"
                }

                if (nilaiUts.isEmpty()){
                    isEmptyField = true
                    edtUts.error = "Masukkan Nilai UTS"
                }else if (nilaiUts.toInt() > 100 || nilaiUts.toInt() < 0){
                    isEmptyField = true
                    edtAbsen.error = "Range harus diatas 0 hingga 100"
                }

                if (nilaiUas.isEmpty()){
                    isEmptyField = true
                    edtUas.error = "Masukkan Nilai UAS"
                }else if (nilaiUas.toInt() > 100 || nilaiUas.toInt() < 0){
                    isEmptyField = true
                    edtAbsen.error = "Range harus diatas 0 hingga 100"
                }

                if (!isEmptyField) {
                    val hitung = HitungNilai(
                            nilaiAbsen.toInt(),
                            nilaiTugas.toInt(),
                            nilaiUts.toInt(),
                            nilaiUas.toInt()
                    )

                    tvNilai.text = "Total Nilai : ${hitung.nilai()}"
                    tvGrade.text = "Grade       : ${hitung.grade()}"
                }

            }
        }
    }
}

class HitungNilai(var absen : Int, var tugas : Int, var uts : Int, var uas : Int) {
    fun nilai() : Int {
        var hasil = (10*absen + 20*tugas + 30*uts + 40*uas)/100
        return hasil.toInt()
    }

    fun grade() : String {
        var hasil = (10*absen + 20*tugas + 30*uts + 40*uas)/100
        when(hasil) {
            in 0..50 -> return "E"
            in 50..60 -> return "D"
            in 60..70 -> return "C"
            in 70..80 -> return "B"
            in 80..100 -> return "A"
            else -> return "F"
        }
    }
}