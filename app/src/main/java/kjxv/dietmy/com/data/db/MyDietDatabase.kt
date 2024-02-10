package kjxv.dietmy.com.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kjxv.dietmy.com.data.entity.ArticleEntity
import kjxv.dietmy.com.data.entity.DietEntity
import kjxv.dietmy.com.data.entity.ExerciseEntity

@Database(entities = [ArticleEntity::class, DietEntity::class, ExerciseEntity::class], version = 1)
abstract class MyDietDatabase() : RoomDatabase() {
    abstract fun myDietDao(): MyDietDao
}