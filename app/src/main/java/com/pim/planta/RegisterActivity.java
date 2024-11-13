package com.pim.planta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, userEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEditText = findViewById(R.id.editTextUser);
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.buttonRegister);

        registerButton.setOnClickListener(v -> {
            String user = userEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || user.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return; // Salir del método si hay campos vacíos
            }

            if (!isValidEmail(email)) {
                Toast.makeText(RegisterActivity.this, "Formato de correo electrónico incorrecto", Toast.LENGTH_SHORT).show();
                return; // Salir del método si el email es inválido
            }

            // Aquí iría la lógica para registrar al usuario

            Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, JardinActivity.class);
            startActivity(intent);
        });
    }

    private boolean isValidEmail(String email) {
        // Puedes usar una expresión regular más compleja para validar el email
        return email.contains("@") && email.contains(".");
    }
}