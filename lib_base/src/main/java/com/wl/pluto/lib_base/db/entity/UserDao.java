package com.wl.pluto.lib_base.db.entity;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by johnwhe on 2017/7/12.
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE userId = :userId")
    User getById(int userId);

    @Insert
    void insert(User... users);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}
