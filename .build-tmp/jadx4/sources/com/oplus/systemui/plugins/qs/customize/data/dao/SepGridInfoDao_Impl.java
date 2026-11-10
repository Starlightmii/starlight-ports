package com.oplus.systemui.plugins.qs.customize.data.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.util.DBUtil;
import androidx.sqlite.SQLiteStatement;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.roomdatabase.SepTileRoomDatabase_Impl;
import java.lang.invoke.VarHandle;
import java.util.Collections;
import java.util.List;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepGridInfoDao_Impl implements SepGridInfoDao {
    public SepTileRoomDatabase_Impl __db;
    public AnonymousClass1 __insertAdapterOfGridInfoEntity;
    public AnonymousClass2 __updateAdapterOfGridInfoEntity;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.dao.SepGridInfoDao_Impl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends EntityInsertAdapter {
        public final void bind(SQLiteStatement sQLiteStatement, Object obj) {
            GridInfoEntity gridInfoEntity = (GridInfoEntity) obj;
            sQLiteStatement.bindLong(1, gridInfoEntity.getUserId());
            sQLiteStatement.bindLong(2, gridInfoEntity.getGridCol());
            sQLiteStatement.bindLong(3, gridInfoEntity.getGridRow());
        }

        public final String createQuery() {
            return "INSERT OR REPLACE INTO `sep_edit_grid_size_table` (`user_id`,`grid_col`,`grid_row`) VALUES (?,?,?)";
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.dao.SepGridInfoDao_Impl$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 extends EntityDeleteOrUpdateAdapter {
        public final void bind(SQLiteStatement sQLiteStatement, Object obj) {
            GridInfoEntity gridInfoEntity = (GridInfoEntity) obj;
            sQLiteStatement.bindLong(1, gridInfoEntity.getUserId());
            sQLiteStatement.bindLong(2, gridInfoEntity.getGridCol());
            sQLiteStatement.bindLong(3, gridInfoEntity.getGridRow());
            sQLiteStatement.bindLong(4, gridInfoEntity.getUserId());
        }

        public final String createQuery() {
            return "UPDATE OR ABORT `sep_edit_grid_size_table` SET `user_id` = ?,`grid_col` = ?,`grid_row` = ? WHERE `user_id` = ?";
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepGridInfoDao
    public final Object insertGridInfo(GridInfoEntity gridInfoEntity, Continuation continuation) {
        gridInfoEntity.getClass();
        SepTileRoomDatabase_Impl sepTileRoomDatabase_Impl = this.__db;
        SepGridInfoDao_Impl$$ExternalSyntheticLambda0 sepGridInfoDao_Impl$$ExternalSyntheticLambda0 = new SepGridInfoDao_Impl$$ExternalSyntheticLambda0(1);
        sepGridInfoDao_Impl$$ExternalSyntheticLambda0.f$0 = this;
        sepGridInfoDao_Impl$$ExternalSyntheticLambda0.f$1 = gridInfoEntity;
        VarHandle.storeStoreFence();
        return DBUtil.performSuspending(sepTileRoomDatabase_Impl, false, true, sepGridInfoDao_Impl$$ExternalSyntheticLambda0, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepGridInfoDao
    public final GridInfoEntity queryGridInfo(int i) {
        SepTileRoomDatabase_Impl sepTileRoomDatabase_Impl = this.__db;
        SepGridInfoDao_Impl$$ExternalSyntheticLambda2 sepGridInfoDao_Impl$$ExternalSyntheticLambda2 = new SepGridInfoDao_Impl$$ExternalSyntheticLambda2();
        sepGridInfoDao_Impl$$ExternalSyntheticLambda2.f$0 = i;
        VarHandle.storeStoreFence();
        return (GridInfoEntity) DBUtil.performBlocking(sepTileRoomDatabase_Impl, true, false, sepGridInfoDao_Impl$$ExternalSyntheticLambda2);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepGridInfoDao
    public final List queryGridInfos() {
        return (List) DBUtil.performBlocking(this.__db, true, false, new SepGridInfoDao_Impl$.ExternalSyntheticLambda1());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepGridInfoDao
    public final Object updateGridInfo(GridInfoEntity gridInfoEntity, Continuation continuation) {
        gridInfoEntity.getClass();
        SepTileRoomDatabase_Impl sepTileRoomDatabase_Impl = this.__db;
        SepGridInfoDao_Impl$$ExternalSyntheticLambda0 sepGridInfoDao_Impl$$ExternalSyntheticLambda0 = new SepGridInfoDao_Impl$$ExternalSyntheticLambda0(0);
        sepGridInfoDao_Impl$$ExternalSyntheticLambda0.f$0 = this;
        sepGridInfoDao_Impl$$ExternalSyntheticLambda0.f$1 = gridInfoEntity;
        VarHandle.storeStoreFence();
        return DBUtil.performSuspending(sepTileRoomDatabase_Impl, false, true, sepGridInfoDao_Impl$$ExternalSyntheticLambda0, continuation);
    }
}
