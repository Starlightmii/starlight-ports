package com.oplus.systemui.plugins.qs.customize.data.roomdatabase;

import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.RoomOpenDelegateMarker;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao;
import com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper;
import com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper_Impl;
import com.oplus.systemui.plugins.qs.customize.data.dao.SepGridInfoDao;
import com.oplus.systemui.plugins.qs.customize.data.dao.SepGridInfoDao_Impl;
import com.oplus.utrace.lib.HLogConst;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileRoomDatabase_Impl extends SepTileRoomDatabase {
    public volatile SepEditTileDaoWrapper_Impl _sepEditTileDaoWrapper;
    public volatile SepGridInfoDao_Impl _sepGridInfoDao;

    public final InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), new String[]{SepEditTileDao.TILE_TABLE_NAME, SepGridInfoDao.TABLE_NAME});
    }

    public final RoomOpenDelegateMarker createOpenDelegate() {
        return new RoomOpenDelegate() { // from class: com.oplus.systemui.plugins.qs.customize.data.roomdatabase.SepTileRoomDatabase_Impl.1
            public final void createAllTables(SQLiteConnection sQLiteConnection) {
                SQLite.execSQL(sQLiteConnection, "CREATE TABLE IF NOT EXISTS `sep_edit_info_table` (`user_id` INTEGER NOT NULL, `tile_spec` TEXT NOT NULL, `loc` TEXT NOT NULL, `size` TEXT NOT NULL, `expand_info` TEXT DEFAULT null, `expand_info_two` TEXT DEFAULT null, `expand_info_three` TEXT DEFAULT null, PRIMARY KEY(`user_id`, `tile_spec`))");
                SQLite.execSQL(sQLiteConnection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_sep_edit_info_table_user_id_tile_spec` ON `sep_edit_info_table` (`user_id`, `tile_spec`)");
                SQLite.execSQL(sQLiteConnection, "CREATE TABLE IF NOT EXISTS `sep_edit_grid_size_table` (`user_id` INTEGER NOT NULL, `grid_col` INTEGER NOT NULL DEFAULT 4, `grid_row` INTEGER NOT NULL DEFAULT 4, PRIMARY KEY(`user_id`))");
                SQLite.execSQL(sQLiteConnection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                SQLite.execSQL(sQLiteConnection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'eb9b6c541df17094c1a3cc34cad444e1')");
            }

            public final void dropAllTables(SQLiteConnection sQLiteConnection) {
                SQLite.execSQL(sQLiteConnection, "DROP TABLE IF EXISTS `sep_edit_info_table`");
                SQLite.execSQL(sQLiteConnection, "DROP TABLE IF EXISTS `sep_edit_grid_size_table`");
            }

            public final void onCreate(SQLiteConnection sQLiteConnection) {
            }

            public final void onOpen(SQLiteConnection sQLiteConnection) {
                SepTileRoomDatabase_Impl.this.internalInitInvalidationTracker(sQLiteConnection);
            }

            public final void onPostMigrate(SQLiteConnection sQLiteConnection) {
            }

            public final void onPreMigrate(SQLiteConnection sQLiteConnection) {
                DBUtil.dropFtsSyncTriggers(sQLiteConnection);
            }

            public final RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection sQLiteConnection) {
                HashMap map = new HashMap(7);
                map.put("user_id", new TableInfo.Column("user_id", "INTEGER", true, 1, (String) null, 1));
                map.put("tile_spec", new TableInfo.Column("tile_spec", "TEXT", true, 2, (String) null, 1));
                map.put("loc", new TableInfo.Column("loc", "TEXT", true, 0, (String) null, 1));
                map.put(HLogConst.KEY_SIZE, new TableInfo.Column(HLogConst.KEY_SIZE, "TEXT", true, 0, (String) null, 1));
                map.put("expand_info", new TableInfo.Column("expand_info", "TEXT", false, 0, "null", 1));
                map.put("expand_info_two", new TableInfo.Column("expand_info_two", "TEXT", false, 0, "null", 1));
                map.put("expand_info_three", new TableInfo.Column("expand_info_three", "TEXT", false, 0, "null", 1));
                HashSet hashSet = new HashSet(0);
                HashSet hashSet2 = new HashSet(1);
                hashSet2.add(new TableInfo.Index("index_sep_edit_info_table_user_id_tile_spec", true, Arrays.asList("user_id", "tile_spec"), Arrays.asList("ASC", "ASC")));
                TableInfo tableInfo = new TableInfo(SepEditTileDao.TILE_TABLE_NAME, map, hashSet, hashSet2);
                TableInfo tableInfo2 = TableInfo.read(sQLiteConnection, SepEditTileDao.TILE_TABLE_NAME);
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenDelegate.ValidationResult(false, "sep_edit_info_table(com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                HashMap map2 = new HashMap(3);
                map2.put("user_id", new TableInfo.Column("user_id", "INTEGER", true, 1, (String) null, 1));
                map2.put("grid_col", new TableInfo.Column("grid_col", "INTEGER", true, 0, "4", 1));
                map2.put("grid_row", new TableInfo.Column("grid_row", "INTEGER", true, 0, "4", 1));
                TableInfo tableInfo3 = new TableInfo(SepGridInfoDao.TABLE_NAME, map2, new HashSet(0), new HashSet(0));
                TableInfo tableInfo4 = TableInfo.read(sQLiteConnection, SepGridInfoDao.TABLE_NAME);
                if (tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenDelegate.ValidationResult(true, (String) null);
                }
                return new RoomOpenDelegate.ValidationResult(false, "sep_edit_grid_size_table(com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
            }
        };
    }

    public final List getAutoMigrations(Map map) {
        return new ArrayList();
    }

    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(SepEditTileDaoWrapper.class, SepEditTileDaoWrapper_Impl.getRequiredConverters());
        map.put(SepGridInfoDao.class, SepGridInfoDao_Impl.getRequiredConverters());
        return map;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.roomdatabase.SepTileRoomDatabase
    public final SepEditTileDaoWrapper sepEditTileDao() {
        SepEditTileDaoWrapper_Impl sepEditTileDaoWrapper_Impl;
        if (this._sepEditTileDaoWrapper != null) {
            return this._sepEditTileDaoWrapper;
        }
        synchronized (this) {
            try {
                if (this._sepEditTileDaoWrapper == null) {
                    this._sepEditTileDaoWrapper = new SepEditTileDaoWrapper_Impl(this);
                }
                sepEditTileDaoWrapper_Impl = this._sepEditTileDaoWrapper;
            } catch (Throwable th) {
                throw th;
            }
        }
        return sepEditTileDaoWrapper_Impl;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.roomdatabase.SepTileRoomDatabase
    public final SepGridInfoDao sepGridInfoDao() {
        SepGridInfoDao_Impl sepGridInfoDao_Impl;
        if (this._sepGridInfoDao != null) {
            return this._sepGridInfoDao;
        }
        synchronized (this) {
            try {
                if (this._sepGridInfoDao == null) {
                    SepGridInfoDao_Impl sepGridInfoDao_Impl2 = new SepGridInfoDao_Impl();
                    sepGridInfoDao_Impl2.__db = this;
                    sepGridInfoDao_Impl2.__insertAdapterOfGridInfoEntity = new SepGridInfoDao_Impl.AnonymousClass1();
                    sepGridInfoDao_Impl2.__updateAdapterOfGridInfoEntity = new SepGridInfoDao_Impl.AnonymousClass2();
                    VarHandle.storeStoreFence();
                    this._sepGridInfoDao = sepGridInfoDao_Impl2;
                }
                sepGridInfoDao_Impl = this._sepGridInfoDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return sepGridInfoDao_Impl;
    }
}
