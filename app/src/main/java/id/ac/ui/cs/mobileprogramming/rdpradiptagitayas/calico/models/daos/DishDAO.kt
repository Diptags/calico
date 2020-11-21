package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Dish

@Dao
interface DishDAO {

    @Insert
    fun addDish(dish: Dish)

    @Query("SELECT * from dish")
    fun getAllDish(): LiveData<List<Dish>>

    @Query("SELECT * FROM dish WHERE dishId = :dishId")
    fun getDishById(dishId: Long): LiveData<Dish>

    @Update
    fun updateDish(dish: Dish)

    @Delete
    fun deleteDish(dish: Dish)

}

