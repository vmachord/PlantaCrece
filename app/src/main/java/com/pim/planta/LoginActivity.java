package com.pim.planta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pim.planta.db.DatabaseExecutor;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.User;
import com.pim.planta.models.UserLogged;

public class LoginActivity extends NotificationActivity {
    private PlantRepository plantRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Tu archivo XML del login
        plantRepo = PlantRepository.getInstance(this);

        // Obtener referencia a los elementos del layout
        EditText elemail = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);
        TextView registerText = findViewById(R.id.textViewToRegister);
        registerText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Configurar la lógica del botón de login
        loginButton.setOnClickListener(v -> {
            String email = elemail.getText().toString();
            String pass = password.getText().toString();

            validateCredentials(email, pass, isValid -> {
                if (isValid) {
                    DatabaseExecutor.execute(() -> {
                        User userLogged = plantRepo.getPlantaDAO().getUserByEmail(email);
                        UserLogged.getInstance().setCurrentUser(userLogged);
                    });
                    SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
                    if (sharedPreferences.getString("selectedPlant", null) == null) {
                        Intent intent = new Intent(LoginActivity.this, PlantListActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    }
                    Intent intent = new Intent(LoginActivity.this, JardinActivity.class);
                    startActivity(intent);
                    finish(); // Finaliza la LoginActivity para que no vuelva a ella al pulsar back
                } else {
                    Looper.prepare();
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void validateCredentials(String email, String pass, ValidateCallback callback) {
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
