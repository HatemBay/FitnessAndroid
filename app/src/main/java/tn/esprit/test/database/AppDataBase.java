package tn.esprit.test.database;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import tn.esprit.test.dao.BMIDao;
import tn.esprit.test.dao.UserDao;
import tn.esprit.test.entity.BMI;
import tn.esprit.test.entity.User;

@Database(entities = {User.class, BMI.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;
    public abstract UserDao userDao();
    public abstract BMIDao bmiDao();
    public static AppDataBase getAppDatabase(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "room_test_db").allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE IF NOT EXISTS `BMI` (`id` INTEGER, `id_fkUser` INTEGER, `value` DOUBLE, PRIMARY KEY(`id`), FOREIGN KEY (`id_fkUser`) REFERENCES `User` (`uid`))");
//        }
//    };
}
