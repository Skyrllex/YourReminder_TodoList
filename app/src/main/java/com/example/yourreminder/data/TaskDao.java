package com.example.yourreminder.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.yourreminder.module.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    List<Task> getAll();

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllLiveData();


    @Query("SELECT * FROM Task WHERE calendar IN (:set setDate)")
    List<Task> loadAllByCalendar(int[] setDate);

    @Query("SELECT * FROM Task WHERE texttask LIKE :searchtext Or " +
            " textsubtask LIKE :searchtext")
    Task findByText(String searchtext);


    @Query("SELECT * FROM Task WHERE uid=:uid LIMIT 1")
    Task findById(int uid);

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);
}
