package com.pim.planta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Tu archivo XML del login

        // Obtener referencia a los elementos del layout
        EditText username = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);

        // Configurar la lógica del botón de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (validateCredentials(user, pass)) {
                    Intent intent = new Intent(LoginActivity.this, JardinActivity.class);
                    startActivity(intent);
                    finish(); // Finaliza la LoginActivity para que no vuelva a ella al pulsar back
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateCredentials(String user, String pass) {
        return user.equals("admin@gmail.com") && pass.equals("1234");
    }
}
