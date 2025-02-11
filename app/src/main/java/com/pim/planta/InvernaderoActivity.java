package com.pim.planta;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.pim.planta.db.DAO;
import com.pim.planta.db.DatabaseExecutor;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.ImageAdapter;
import com.pim.planta.models.Plant;
import com.pim.planta.models.UserLogged;
import com.pim.planta.models.UserPlantRelation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvernaderoActivity extends NotificationActivity {
    private ImageView plantoo;
    private Map<String, Integer> displayedPlantCounts = new HashMap<>();
    private DAO dao;
    private ImageView agregar1;
    private ImageView agregar2;
    private ImageView agregar3;
    private ImageView agregar4;
    private ImageView agregar5;

    private static int[] IMAGES = {};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invernadero);
        ConstraintLayout macetaPlantoo = findViewById(R.id.macetaPlantoo);
        plantoo = macetaPlantoo.findViewById(R.id.plantooImageView);

        PlantRepository repository = PlantRepository.getInstance(this);
        dao = repository.getPlantaDAO();

        ImageButton imageButtonOjo = findViewById(R.id.imageButtonOjo);
        imageButtonOjo.setOnClickListener(v -> {
            Intent intent = new Intent(InvernaderoActivity.this, JardinActivity.class);
            startActivity(intent); // Volver a pantalla anterior
        });

        setImage(dao);
        ConstraintLayout maceta1 = findViewById(R.id.macetaAgregar1);
        ConstraintLayout maceta2 = findViewById(R.id.macetaAgregar2);
        ConstraintLayout maceta3 = findViewById(R.id.macetaAgregar3);
        ConstraintLayout maceta4 = findViewById(R.id.macetaAgregar4);
        ConstraintLayout maceta5 = findViewById(R.id.macetaAgregar5);
        agregar1 = maceta1.findViewById(R.id.agregarImageView);
        agregar2 = maceta2.findViewById(R.id.agregarImageView);
        agregar3 = maceta3.findViewById(R.id.agregarImageView);
        agregar4 = maceta4.findViewById(R.id.agregarImageView);
        agregar5 = maceta5.findViewById(R.id.agregarImageView);

        restorePlantedPlants();
        // Establecer un OnClickListener para la imagen
        agregar1.setOnClickListener(v -> showImagePickerDialog("agregar1"));
        agregar2.setOnClickListener(v -> showImagePickerDialog("agregar2"));
        agregar3.setOnClickListener(v -> showImagePickerDialog("agregar3"));
        agregar4.setOnClickListener(v -> showImagePickerDialog("agregar4"));
        agregar5.setOnClickListener(v -> showImagePickerDialog("agregar5"));
    }

    private void showImagePickerDialog(final String agregarId) {
        // Crear el Dialog
        final Dialog dialog = new Dialog(InvernaderoActivity.this);
        dialog.setContentView(R.layout.dialog_image_picker);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.tooltip_background);
        // Configurar el GridView
        GridView gridView = dialog.findViewById(R.id.grid_view);
        int[] filteredImages = getFilteredImages(dao);
        ImageAdapter adapter = new ImageAdapter(InvernaderoActivity.this, filteredImages);
        gridView.setAdapter(adapter);

        // Configurar la acción al seleccionar una imagen
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            int selectedImageId = filteredImages[position];
            ImageView imageView = null;
            switch (agregarId) {
                case "agregar1":
                    imageView = agregar1;
                    break;
                case "agregar2":
                    imageView = agregar2;
                    break;
                case "agregar3":
                    imageView = agregar3;
                    break;
                case "agregar4":
                    imageView = agregar4;
                    break;
                case "agregar5":
                    imageView = agregar5;
                    break;
            }
            if (selectedImageId == R.drawable.ic_x) {
                // Handle "X" click
                if (imageView != null) {
                    imageView.setImageResource(R.drawable.agregar_planta);
                }

                // Remove the plant from displayedPlantCounts
                String plantName = getDisplayedPlantName(agregarId);
                if (plantName != null) {
                    int currentCount = displayedPlantCounts.getOrDefault(plantName, 0);
                    if (currentCount > 0) {
                        displayedPlantCounts.put(plantName, currentCount - 1);
                        if (displayedPlantCounts.get(plantName) == 0) {
                            displayedPlantCounts.remove(plantName);
                        }
                    }
                }

                // Clear SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(agregarId);
                editor.apply();

                dialog.dismiss();
            } else {
                String selectedPlantName = getPlantNameFromImageId(selectedImageId);
                ImageView finalImageView = imageView;
                new CanAddPlantTask(selectedPlantName, dao, canAdd -> {
                    if (canAdd) {
                        SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt(agregarId, selectedImageId); // Save the selected image ID
                        editor.apply();

                        if (finalImageView != null) {
                            finalImageView.setImageResource(selectedImageId);
                        }

                        // Update displayedPlantCounts
                        displayedPlantCounts.put(selectedPlantName, displayedPlantCounts.getOrDefault(selectedPlantName, 0) + 1);

                        dialog.dismiss();
                    } else {
                        Toast.makeText(InvernaderoActivity.this, "You have already displayed all of this plant type", Toast.LENGTH_SHORT).show();
                    }
                }).execute();
            }
        });
        dialog.show();
    }
    private void restorePlantedPlants() {
        SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String agregarId = entry.getKey();
            if (agregarId.startsWith("agregar")) {
                int selectedImageId = (int) entry.getValue();
                ImageView imageView = null;
                switch (agregarId) {
                    case "agregar1":
                        imageView = agregar1;
                        break;
                    case "agregar2":
                        imageView = agregar2;
                        break;
                    case "agregar3":
                        imageView = agregar3;
                        break;
                    case "agregar4":
                        imageView = agregar4;
                        break;
                    case "agregar5":
                        imageView = agregar5;
                        break;
                }
                if (imageView != null) {
                    imageView.setImageResource(selectedImageId);
                }

                // Update displayedPlantCounts
                String plantName = getPlantNameFromImageId(selectedImageId);
                if (plantName != null) {
                    displayedPlantCounts.put(plantName, displayedPlantCounts.getOrDefault(plantName, 0) + 1);
                }
            }
        }
    }

    private void setImage(DAO dao) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            int userId = UserLogged.getInstance().getCurrentUser().getId();
            List<UserPlantRelation> relations = dao.getUserPlantRelations(userId);

            // Lista dinámica para almacenar las imágenes de plantas con growCount > 0
            List<Integer> filteredImages = new ArrayList<>();

            for (UserPlantRelation relation : relations) {
                Plant plant = dao.getPlantaById(relation.plantId);

                // Verifica si el growCount es mayor que 0
                if (relation.growCount > 0) {
                    // Agrega la imagen correspondiente al arreglo filtrado
                    switch (plant.getName()) {case "Rosa":
                        filteredImages.add(R.drawable.image_rosa);
                        break;
                        case "Girasol":
                            filteredImages.add(R.drawable.image_girasol);
                            break;
                        case "Diente de León":
                            filteredImages.add(R.drawable.image_diente_de_leon);
                            break;
                        case "Margarita":
                            filteredImages.add(R.drawable.image_margarita);
                            break;
                        case "Tulipan":
                            filteredImages.add(R.drawable.image_tulipan);
                            break;
                    }
                }
            }

            filteredImages.add(R.drawable.ic_x);
            int[] resultImages = new int[filteredImages.size()];
            for (int i = 0; i < filteredImages.size(); i++) {
                resultImages[i] = filteredImages.get(i);
            }

            synchronized (IMAGES) {
                IMAGES = resultImages;
            }
        });
    }

    public static void incrementGrowCountInBackground(final int userId, final String plantName, final DAO dao) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Plant plant = dao.getPlantaByName(plantName);
                if (plant != null) {
                    dao.incrementGrowCount(userId, plant.getId());
                }
                return null;
            }
        }.execute();
    }

    private void updateImage(String plantName) {
        // Reemplazar los espacios por guiones bajos y eliminar los acentos
        String sanitizedPlantName = plantName.replace(" ", "_").toLowerCase();
        sanitizedPlantName = removeAccents(sanitizedPlantName); // Eliminar caracteres acentuados

        // Crear el nombre del recurso de la imagen usando el nombre de la planta
        String imageName = "image_" + sanitizedPlantName + JardinActivity.currentImageIndex;
        ;
        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());

        if (resID != 0) {
            plantoo.setImageResource(resID);
        } else {
            String imageName2 = "image_" + JardinActivity.currentImageIndex;
            // Accede a la variable estática para la imagen
            int resID2 = getResources().getIdentifier(imageName2, "drawable", getPackageName());
            plantoo.setImageResource(resID2);
        }
    }

    private String removeAccents(String str) {
        return str.replaceAll("[áàäâ]", "a")
                .replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i")
                .replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u")
                .replaceAll("[ç]", "c");
    }

    @Override
    public void onResume() {
        super.onResume();
        restorePlantedPlants();

        SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);

        String[] agregarIds = {"agregar1", "agregar2", "agregar3", "agregar4", "agregar5"};
        for (String agregarId : agregarIds) {
            int selectedImageId = sharedPreferences.getInt(agregarId, -1);
            ImageView imageView = null;
            switch (agregarId) {
                case "agregar1":
                    imageView = agregar1;
                    break;
                case "agregar2":
                    imageView = agregar2;
                    break;
                case "agregar3":
                    imageView = agregar3;
                    break;
                case "agregar4":
                    imageView = agregar4;
                    break;
                case "agregar5":
                    imageView = agregar5;
                    break;
            }
            if (selectedImageId != -1 && imageView != null) {
                imageView.setImageResource(selectedImageId);
            }
        }
        String selectedPlant = sharedPreferences.getString("selectedPlant", null);
        if (selectedPlant != null) {
            updateImage(selectedPlant);
        }
    }

    private int[] getFilteredImages(DAO dao) {
        int userId = UserLogged.getInstance().getCurrentUser().getId();
        List<Integer> filteredImages = new ArrayList<>();
        DatabaseExecutor.executeAndWait(() -> {
            List<UserPlantRelation> relations = dao.getUserPlantRelations(userId);
            for (UserPlantRelation relation : relations) {
                Plant plant = dao.getPlantaById(relation.plantId);
                if (plant != null) {
                    int growCount = relation.growCount;
                    int displayedCount = displayedPlantCounts.getOrDefault(plant.getName(), 0);
                    if (growCount > displayedCount) {
                        if ("Rosa".equals(plant.getName())) {
                            filteredImages.add(R.drawable.image_rosa);
                        } else if ("Girasol".equals(plant.getName())) {
                            filteredImages.add(R.drawable.image_girasol);
                        } else if ("Diente de León".equals(plant.getName())) {
                            filteredImages.add(R.drawable.image_diente_de_leon);
                        } else if ("Margarita".equals(plant.getName())) {
                            filteredImages.add(R.drawable.image_margarita);
                        } else if ("Tulipan".equals(plant.getName())) {
                            filteredImages.add(R.drawable.image_tulipan);
                        }
                    }
                }
            }
        });
        filteredImages.add(R.drawable.ic_x);
        return filteredImages.stream().mapToInt(i -> i).toArray();
    }

    private String getPlantNameFromImageId(int imageId) {
        if (imageId == R.drawable.image_rosa) {
            return "Rosa";
        } else if (imageId == R.drawable.image_girasol) {
            return "Girasol";
        } else if (imageId == R.drawable.image_diente_de_leon) {
            return "Diente de León";
        } else if (imageId == R.drawable.image_margarita) {
            return "Margarita";
        } else if (imageId == R.drawable.image_tulipan) {
            return "Tulipan";
        } else {
            return "";
        }
    }

    private String getDisplayedPlantName(String agregarId) {
        SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
        int imageId = sharedPreferences.getInt(agregarId, -1);
        if (imageId != -1) {
            return getPlantNameFromImageId(imageId);
        }
        return null;
    }

    private class CanAddPlantTask extends AsyncTask<Void, Void, Boolean> {
        private String plantName;
        private DAO dao;
        private CanAddPlantCallback callback;

        public CanAddPlantTask(String plantName, DAO dao, CanAddPlantCallback callback) {
            this.plantName = plantName;
            this.dao = dao;
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            int userId = UserLogged.getInstance().getCurrentUser().getId();
            Plant plant = dao.getPlantaByName(plantName);
            if (plant == null) {
                return false;
            }
            int growCount = dao.getGrowCount(userId, plant.getId());
            int displayedCount = displayedPlantCounts.getOrDefault(plantName, 0);
            return displayedCount < growCount;
        }

        @Override
        protected void onPostExecute(Boolean canAdd) {
            callback.onResult(canAdd);
        }
    }

    interface CanAddPlantCallback {
        void onResult(boolean canAdd);
    }
}