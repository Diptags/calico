package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Dish
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Journal
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.repositories.DishRepository
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.repositories.JournalRepository

class DishViewModel : ViewModel() {

    var dishMutableLiveData: MutableLiveData<Dish> =
        MutableLiveData<Dish>()

    fun addDish(context: Context, dish: Dish) {
        DishRepository.addDish(context, dish)
        dishMutableLiveData.value = dish
    }

    fun getAllDish(context: Context): LiveData<List<Dish>>? {
        return DishRepository.getAllDish(context)
    }

    fun getDishById(context: Context, dishId: Long): LiveData<Dish>? {
        return DishRepository.getDishById(context, dishId)
    }

    fun updateDish(context: Context, dish: Dish) {
        DishRepository.updateDish(context, dish)
        dishMutableLiveData.value = dish
    }

    fun deleteDish(context: Context, journal: Journal) {
        JournalRepository.deleteJournal(context, journal)
    }
}