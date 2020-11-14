//package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.repositories
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Dish
//import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.persistences.CalicoDatabase
//
//
//class DishRepository {
//
//    companion object {
//
//        var database: CalicoDatabase? = null
//        var dishModel: LiveData<Dish>? = null
//
//        private fun initializeDB(context: Context): CalicoDatabase {
//            return CalicoDatabase.getDatabaseClient(context)
//        }
//
//        fun getDishById(context: Context, dishId: Long): LiveData<Dish>? {
//            database = initializeDB(context)
//            return database!!.dishDao().getDishById(dishId)
//        }
//
//        fun getAllDishes(context: Context): LiveData<List<Dish>>? {
//            database = initializeDB(context)
//            return database!!.dishDao().getAllDishes()
//        }
//    }
//}