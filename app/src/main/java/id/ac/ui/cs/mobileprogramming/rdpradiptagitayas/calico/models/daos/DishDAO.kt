package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Dish

@Dao
interface DishDAO {

    @Query("SELECT * FROM Dish WHERE dishId = :dishId")
    fun getDishById(dishId: Long): LiveData<Dish>

    @Query("SELECT * FROM Dish")
    fun getAllDishes(): LiveData<List<Dish>>

}