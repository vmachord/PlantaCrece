package com.pim.planta.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.pim.planta.models.Calendar;
import com.pim.planta.models.EntradaDiario;
import com.pim.planta.models.Plant;
import com.pim.planta.models.User;
import com.pim.planta.models.Convertidores;

@Database(entities = {Plant.class, User.class, Calendar.class, EntradaDiario.class}, version = 1)
@TypeConverters({Convertidores.class})
public abstract class DatabasePlantoo extends RoomDatabase {
    public abstract DAO DAO();
}
    /*
    Uso de Room:
    Se crea un PlantaRepository en el onCreate para interactuar con la base de datos de Planta usando Room.
    En el código se muestra cómo agregar una nueva planta a la base de datos en un hilo de fondo, para evitar bloquear el hilo principal de la interfaz de usuario.

    Operación de base de datos en hilo de fondo:
    Las operaciones de base de datos, como la inserción de una planta, se realizan dentro de un Thread para asegurarnos de que no bloqueamos la UI principal, lo cual es una buena práctica.

    Manejo de excepciones más robusto:
    He añadido un try-catch en el bloque donde se interactúa con la base de datos para asegurarnos de capturar cualquier posible error.

    Feedback al usuario:
    Se incluye un Toast para notificar al usuario que la planta se ha agregado correctamente.
     */





