package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities

import androidx.room.*

// Journal and Dish relationship
data class JournalWithDishes(
    @Embedded val journal: Journal,
    @Relation(
        parentColumn = "journalId",
        entityColumn = "dishId",
        associateBy = Junction(JournalDishCrossRef::class)
    )
    val dishes: List<Dish>
)

// User and Journal and Dish relationship
data class UserWithJournalsAndDishes(
    @Embedded val user: User,
    @Relation(
        entity = Journal::class,
        parentColumn = "userId",
        entityColumn = "userCreatorId"
    )
    val journals: List<JournalWithDishes>
)