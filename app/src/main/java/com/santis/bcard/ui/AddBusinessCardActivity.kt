package com.santis.bcard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.github.dhaval2404.colorpicker.util.ColorUtil
import com.santis.bcard.App
import com.santis.bcard.R
import com.santis.bcard.data.BusinessCard
import com.santis.bcard.databinding.ActivityAddBusinessCardBinding
import com.santis.bcard.util.getColorFromAttr
import com.santis.bcard.util.isValidEmail
import me.ibrahimsn.lib.PhoneNumberKit

class AddBusinessCardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    // implantar o ViewModel na View
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val phoneNumberKit = PhoneNumberKit(this)
    private var defaultColor = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_BusinessCardApp)
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
            var msgToast = R.string.label_show_fail
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

            if (!haserr) {
                msgToast = R.string.label_show_success
                val businessCard = BusinessCard(
                    nome = binding.tilName.editText?.text.toString(),
                    empresa = binding.tilEmpresa.editText?.text.toString(),
                    tel = binding.tilPhone.editText?.text.toString(),
                    email = binding.tilEmail.editText?.text.toString(),
                    cor = ColorUtil.formatColor(defaultColor)
                )
                mainViewModel.insert(businessCard)
                Toast.makeText(this, msgToast, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, msgToast, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
