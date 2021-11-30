package com.geekbrains.myfirsttests

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.myfirsttests.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val emailValidator = EmailValidator()
    private val numberChecker = NumberChecker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            emailInput.addTextChangedListener(emailValidator)
            numberInput.addTextChangedListener(numberChecker)

            saveButton.setOnClickListener {
                when {
                    emailInput.text.toString() != "" -> {
                        emailValidatorFun()
                    }
                    numberInput.text.toString() != "" -> {
                        Toast.makeText(
                            this@MainActivity,
                            numberChecker.value.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun emailValidatorFun() {
        if (emailValidator.isValid) {
            Toast.makeText(
                this@MainActivity,
                getString(R.string.valid_email),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val errorEmail = getString(R.string.invalid_email)
            binding.emailInput.error = errorEmail
            Toast.makeText(this@MainActivity, errorEmail, Toast.LENGTH_SHORT).show()
        }
    }
}
