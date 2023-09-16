package com.cards.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cards.entites.Materia;

@Database(entities = {Materia.class},version = 1, exportSchema = false)
public abstract class MateriaDatabase extends RoomDatabase {

    public abstract MateriaDao materiaDao();

    private static MateriaDatabase instance;

    public static MateriaDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (MateriaDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context,
                            MateriaDatabase.class,
                            "flashcards.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
