package com.oplus.systemui.plugins.qs.customize.data.roomdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.oplus.systemui.aod.utils.NoneLocalHbmAodRusManager$;
import com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper;
import com.oplus.systemui.plugins.qs.customize.data.dao.SepGridInfoDao;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class SepTileRoomDatabase extends RoomDatabase {
    public static final Companion Companion = new Companion();
    private static final String DB_NAME = "SepEditTileRoomDatabase.db";
    private static volatile SepTileRoomDatabase INSTANCE = null;
    public static final String TAG = "SepTileRoomDatabase";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static SepTileRoomDatabase buildDatabase(Context context) {
            try {
                return (SepTileRoomDatabase) Room.databaseBuilder(context.getApplicationContext(), SepTileRoomDatabase.class, SepTileRoomDatabase.DB_NAME).addCallback(new UserTileDatabaseCallback()).setAutoCloseTimeout(10L, TimeUnit.MINUTES).fallbackToDestructiveMigrationFrom(true, new int[]{1}).build();
            } catch (SQLiteException e) {
                if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    return null;
                }
                Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("db was created failed, because of " + e));
                return null;
            }
        }

        public final SepTileRoomDatabase getDatabase(Context context) {
            SepTileRoomDatabase instance;
            if (getINSTANCE() != null) {
                return getINSTANCE();
            }
            synchronized (this) {
                Companion companion = SepTileRoomDatabase.Companion;
                instance = companion.getINSTANCE();
                if (instance == null) {
                    instance = buildDatabase(context);
                    companion.setINSTANCE(instance);
                }
            }
            return instance;
        }

        public final SepTileRoomDatabase getINSTANCE() {
            return SepTileRoomDatabase.INSTANCE;
        }

        public final void openDB(Context context) {
            SepTileRoomDatabase instance;
            if (getINSTANCE() == null || !((instance = getINSTANCE()) == null || instance.isOpen())) {
                setINSTANCE(buildDatabase(context));
            }
        }

        public final void setINSTANCE(SepTileRoomDatabase sepTileRoomDatabase) {
            SepTileRoomDatabase.INSTANCE = sepTileRoomDatabase;
        }

        public final <T> T use(Function0 function0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                return (T) function0.invoke();
            } catch (SQLiteException e) {
                LogUtil logUtil = LogUtil.INSTANCE;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                }
                return null;
            } finally {
                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->"));
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class UserTileDatabaseCallback extends RoomDatabase.Callback {
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            super.onCreate(supportSQLiteDatabase);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->db was created");
            }
        }

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            super.onOpen(supportSQLiteDatabase);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->db was opened");
            }
        }
    }

    public abstract SepEditTileDaoWrapper sepEditTileDao();

    public abstract SepGridInfoDao sepGridInfoDao();
}
