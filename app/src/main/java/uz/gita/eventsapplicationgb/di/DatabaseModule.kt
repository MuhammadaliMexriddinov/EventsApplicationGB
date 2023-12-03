package uz.gita.eventsapplicationgb.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.gita.eventsapplicationgb.data.room.AppDatabase
import uz.gita.eventsapplicationgb.data.room.dao.EventDao
import uz.gita.eventsapplicationgb.data.room.entity.EventEntity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "event_data.db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val list = listOf(
                        "Screen on",
                        "Screen off",
                        "Wifi connected",
                        "Wifi disconnected",
                        "Bluetooth on",
                        "Bluetooth off",
                        "HeadPhones on",
                        "HeadPhones off",
                        "Plane on",
                        "Plane off",
                        "Changed time",
                        "Shut down",
                        "Full battery",
                        "Low battery",
                        "Power on",
                        "Power off"
                    )
                    val dao = provideEventDao(provideDatabase(context))
                    GlobalScope.launch {
                        for (i in list) {
                            dao.insertEvent(EventEntity(0, i, 0))
                        }
                    }
                }
            })
            .build()
    }

    @[Provides Singleton]
    fun provideEventDao(appDatabase: AppDatabase): EventDao = appDatabase.eventDao()

}