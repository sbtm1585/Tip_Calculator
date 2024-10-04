package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editView = findViewById<EditText>(R.id.edit_text)
        val billValue = findViewById<TextView>(R.id.bill_value_tv)
        val seekBar = findViewById<SeekBar>(R.id.seek_bar)
        val tipValueText = findViewById<TextView>(R.id.tip_percent_tv)
        val tipAmountText = findViewById<TextView>(R.id.tip_amount_tv)
        var tipAmount: BigDecimal
        var newVal = BigDecimal(0)
        var newValFormatted: String

        editView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                newVal = s.toString().toBigDecimalOrNull() ?: BigDecimal(0.0)
                newValFormatted = "Bill Value: $${String.format("%.2f", newVal)}"
                if (newVal.toDouble() > 0) {
                    tipAmount = newVal * 0.01.toBigDecimal() * seekBar.progress.toBigDecimal()
                    tipAmountText.text = "Tip Amount: $${String.format("%.2f", tipAmount)}"
                    billValue.text = newValFormatted
                    tipValueText.text = "Tip: ${seekBar.progress}%"

                } else {
                    tipAmountText.text = ""
                    billValue.text = ""
                    tipValueText.text = ""
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (billValue.text == "") {
                    tipAmountText.text = ""
                    tipValueText.text = ""
                } else {
                    tipAmount = newVal * 0.01.toBigDecimal() * progress.toBigDecimal()
                    tipAmountText.text = "Tip Amount: $${String.format("%.2f", tipAmount)}"
                    tipValueText.text = "Tip: $progress%"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (billValue.text == "") {
                    tipAmountText.text = ""
                    tipValueText.text = ""
                } else {
                    tipAmount = newVal * 0.01.toBigDecimal() * seekBar?.progress!!.toBigDecimal()
                    tipAmountText.text = "Tip Amount: $${String.format("%.2f", tipAmount)}"
                    tipValueText.text = "Tip: ${seekBar?.progress}%"
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (billValue.text == "") {
                    tipAmountText.text = ""
                    tipValueText.text = ""
                } else {
                    tipAmount = newVal * 0.01.toBigDecimal() * seekBar?.progress!!.toBigDecimal()
                    tipAmountText.text = "Tip Amount: $${String.format("%.2f", tipAmount)}"
                    tipValueText.text = "Tip: ${seekBar?.progress}%"
                }
            }

        })

    }
}