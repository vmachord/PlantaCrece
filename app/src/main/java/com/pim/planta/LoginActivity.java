package com.pim.planta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pim.planta.db.DatabaseExecutor;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public User UserConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Tu archivo XML del login

        // Obtener referencia a los elementos del layout
        EditText elemail = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);
        TextView registerText = findViewById(R.id.textViewRegIni);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        // Configurar la lógica del botón de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = elemail.getText().toString();
                String pass = password.getText().toString();




                validateCredentials(email, pass, isValid -> {
                    if (isValid) {
                        Intent intent = new Intent(LoginActivity.this, JardinActivity.class);
                        startActivity(intent);
                        finish(); // Finaliza la LoginActivity para que no vuelva a ella al pulsar back
                    } else {
                        Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    private void validateCredentials(String email, String pass, ValidateCallback callback) {
        PlantRepository plantRepo = PlantRepository.getInstance(this);

        DatabaseExecutor.execute(() -> {
            User userLogged = plantRepo.getPlantaDAO().getUserByEmail(email);
            boolean isValid = userLogged != null && userLogged.getPassword().equals(pass);
            callback.onResult(isValid); // Llama al callback con el resultado
        });
    }

    public interface ValidateCallback {
        void onResult(boolean isValid);
    }
}
