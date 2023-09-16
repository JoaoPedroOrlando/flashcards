package com.cards.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cards.entites.Materia;

import java.util.List;

@Dao
public interface MateriaDao {

    @Insert
    long insert(Materia materia);

    @Delete
    void delete(Materia materia);

    @Update
    void update(Materia materia);

    @Query("SELECT * FROM materia WHERE id = :id")
    Materia queryForId(long id);

    @Query("SELECT * FROM materia ORDER BY nome ASC")
    List<Materia> queryAll();

    @Query("SELECT * FROM materia WHERE nome IN (:nome) ORDER BY nome ASC")
    List<Materia> queryByName(String nome);
}
