package kjxv.dietmy.com.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kjxv.dietmy.com.data.db.MyDietDatabase
import kjxv.dietmy.com.data.repository.MyDietRepositoryImp
import kjxv.dietmy.com.domain.repository.MyDietRepository
import javax.inject.Singleton

private const val DB_NAME = "my-diet"

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideMyDietRepository(database: MyDietDatabase): MyDietRepository =
        MyDietRepositoryImp(database)

    @Provides
    @Singleton
    fun provideMyDietDatabase(@ApplicationContext context: Context): MyDietDatabase =
        Room.databaseBuilder(context, MyDietDatabase::class.java, DB_NAME)
            .createFromAsset("database/mydiet.db").build()
}