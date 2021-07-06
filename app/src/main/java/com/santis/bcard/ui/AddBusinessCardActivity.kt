package com.santis.bcard.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.github.dhaval2404.colorpicker.util.ColorUtil
import com.santis.bcard.R
import com.santis.bcard.databinding.ActivityAddBusinessCardBinding
import com.santis.bcard.util.getColorFromAttr
import com.santis.bcard.util.isValidEmail
import me.ibrahimsn.lib.PhoneNumberKit
import kotlin.math.absoluteValue

class AddBusinessCardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }
    private val phoneNumberKit = PhoneNumberKit(this)
    private var defaultColor = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme (R.style.Theme_BusinessCardApp)
        defaultColor = getColorFromAttr(R.attr.colorPrimary)
        setContentView(binding.root)
        insertListeners()
        phoneNumberKit.attachToInput(binding.tilPhone, 55)

        binding.btnColor.setBackgroundColor(defaultColor)
    }

    private fun insertListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.btnColor.setOnClickListener {
            // Abre e retorna ColorPicker
            MaterialColorPickerDialog
                .Builder(this) // Pass Activity Instance
                .setTitle("Cor da Tag") // Default "Choose Color"
                .setColorShape(ColorShape.SQAURE) // Default ColorShape.CIRCLE
                .setColorSwatch(ColorSwatch._500) // Default ColorSwatch._500
                .setDefaultColor(defaultColor) // Pass Default Color
                .setTickColorPerCard(true)
                .setColorListener { color, colorHex ->
                    // Handle Color Selection
                    binding.btnColor.setBackgroundColor(color)
                    defaultColor = color
                }
                .show()
        }

        binding.btnConfirm.setOnClickListener {
            var haserr = false

            // Checa Nome
            binding.tilName.error = null
            if (binding.tilName.editText?.text.isNullOrBlank()) {
                binding.tilName
                    .error = binding.tilName.helperText
                haserr = true
            }

            // Checa E-mail
            binding.tilEmail.error = null
            val strEmail = binding.tilEmail.editText?.text.toString()
            if (!isValidEmail(strEmail)) {
                binding.tilEmail
                    .error = binding.tilEmail.helperText
                haserr = true
            }

            // Checa Fone
            binding.tilPhone.error = null
            if (!(phoneNumberKit.isValid)) {
                binding.tilPhone
                    .error = binding.tilPhone.helperText
                haserr = true
            }
        }
    }
}
