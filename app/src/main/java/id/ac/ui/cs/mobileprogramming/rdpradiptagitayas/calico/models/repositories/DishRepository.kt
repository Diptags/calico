package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.ApplicationDatabase
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Dish

@Suppress("DEPRECATION")
class DishRepository {

    companion object {

        var applicationDatabase: ApplicationDatabase? = null

        private fun initializeDB(context: Context): ApplicationDatabase {
            return ApplicationDatabase.getDatabaseClient(context)
        }

        fun addDish(context: Context, dish: Dish) {
            applicationDatabase = initializeDB(context)
            AddDishAsync().execute(dish)
        }

        fun getAllDish(context: Context): LiveData<List<Dish>>? {
            applicationDatabase = initializeDB(context)
            return applicationDatabase!!.dishDao().getAllDish()
        }

        fun getDishById(context: Context, dishId: Long): LiveData<Dish>? {
            applicationDatabase = initializeDB(context)
            return applicationDatabase!!.dishDao().getDishById(dishId)
        }

        fun updateDish(context: Context, dish: Dish) {
            applicationDatabase = initializeDB(context)
            UpdateDishAsync().execute(dish)
        }

        fun deleteDish(context: Context, dish: Dish) {
            applicationDatabase = initializeDB(context)
            DeleteDishAsync().execute(dish)
        }

        private class AddDishAsync : AsyncTask<Dish, Void, Void>() {
            override fun doInBackground(vararg dishes: Dish): Void? {
                applicationDatabase!!.dishDao().addDish(dishes[0])
                return null
            }
        }

        private class UpdateDishAsync : AsyncTask<Dish, Void, Void>() {
            override fun doInBackground(vararg dishes: Dish): Void? {
                applicationDatabase!!.dishDao().updateDish(dishes[0])
                return null
            }
        }

        private class DeleteDishAsync : AsyncTask<Dish, Void, Void>() {
            override fun doInBackground(vararg dishes: Dish): Void? {
                applicationDatabase!!.dishDao().deleteDish(dishes[0])
                return null
            }
        }
    }
}