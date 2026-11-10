package com.oplus.systemui.plugins.qs.customize.data.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.SQLiteStatement;
import com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity;
import com.oplus.systemui.plugins.qs.customize.data.roomdatabase.Converters;
import com.oplus.systemui.plugins.qs.customize.data.roomdatabase.SepTileRoomDatabase_Impl;
import java.lang.invoke.VarHandle;
import java.util.Collections;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepEditTileDaoWrapper_Impl extends SepEditTileDaoWrapper {
    public final Converters __converters = new Converters();
    public final SepTileRoomDatabase_Impl __db;
    public final AnonymousClass1 __insertAdapterOfSepTileEntity;
    public final AnonymousClass2 __updateAdapterOfSepTileEntity;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper_Impl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends EntityInsertAdapter {
        public /* synthetic */ SepEditTileDaoWrapper_Impl this$0;

        public final void bind(SQLiteStatement sQLiteStatement, Object obj) {
            SepTileEntity sepTileEntity = (SepTileEntity) obj;
            Converters converters = this.this$0.__converters;
            sQLiteStatement.bindLong(1, sepTileEntity.getUserId());
            if (sepTileEntity.getTileSpec() == null) {
                sQLiteStatement.bindNull(2);
            } else {
                sQLiteStatement.bindText(2, sepTileEntity.getTileSpec());
            }
            String strLocToString = converters.locToString(sepTileEntity.getTileLoc());
            if (strLocToString == null) {
                sQLiteStatement.bindNull(3);
            } else {
                sQLiteStatement.bindText(3, strLocToString);
            }
            String strSpanSizeToString = converters.spanSizeToString(sepTileEntity.getTileSize());
            if (strSpanSizeToString == null) {
                sQLiteStatement.bindNull(4);
            } else {
                sQLiteStatement.bindText(4, strSpanSizeToString);
            }
            if (sepTileEntity.getExpandInfo() == null) {
                sQLiteStatement.bindNull(5);
            } else {
                sQLiteStatement.bindText(5, sepTileEntity.getExpandInfo());
            }
            if (sepTileEntity.getExpandInfoTwo() == null) {
                sQLiteStatement.bindNull(6);
            } else {
                sQLiteStatement.bindText(6, sepTileEntity.getExpandInfoTwo());
            }
            if (sepTileEntity.getExpandInfoThree() == null) {
                sQLiteStatement.bindNull(7);
            } else {
                sQLiteStatement.bindText(7, sepTileEntity.getExpandInfoThree());
            }
        }

        public final String createQuery() {
            return "INSERT OR REPLACE INTO `sep_edit_info_table` (`user_id`,`tile_spec`,`loc`,`size`,`expand_info`,`expand_info_two`,`expand_info_three`) VALUES (?,?,?,?,?,?,?)";
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper_Impl$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 extends EntityDeleteOrUpdateAdapter {
        public /* synthetic */ SepEditTileDaoWrapper_Impl this$0;

        public final void bind(SQLiteStatement sQLiteStatement, Object obj) {
            SepTileEntity sepTileEntity = (SepTileEntity) obj;
            Converters converters = this.this$0.__converters;
            sQLiteStatement.bindLong(1, sepTileEntity.getUserId());
            if (sepTileEntity.getTileSpec() == null) {
                sQLiteStatement.bindNull(2);
            } else {
                sQLiteStatement.bindText(2, sepTileEntity.getTileSpec());
            }
            String strLocToString = converters.locToString(sepTileEntity.getTileLoc());
            if (strLocToString == null) {
                sQLiteStatement.bindNull(3);
            } else {
                sQLiteStatement.bindText(3, strLocToString);
            }
            String strSpanSizeToString = converters.spanSizeToString(sepTileEntity.getTileSize());
            if (strSpanSizeToString == null) {
                sQLiteStatement.bindNull(4);
            } else {
                sQLiteStatement.bindText(4, strSpanSizeToString);
            }
            if (sepTileEntity.getExpandInfo() == null) {
                sQLiteStatement.bindNull(5);
            } else {
                sQLiteStatement.bindText(5, sepTileEntity.getExpandInfo());
            }
            if (sepTileEntity.getExpandInfoTwo() == null) {
                sQLiteStatement.bindNull(6);
            } else {
                sQLiteStatement.bindText(6, sepTileEntity.getExpandInfoTwo());
            }
            if (sepTileEntity.getExpandInfoThree() == null) {
                sQLiteStatement.bindNull(7);
            } else {
                sQLiteStatement.bindText(7, sepTileEntity.getExpandInfoThree());
            }
            sQLiteStatement.bindLong(8, sepTileEntity.getUserId());
            if (sepTileEntity.getTileSpec() == null) {
                sQLiteStatement.bindNull(9);
            } else {
                sQLiteStatement.bindText(9, sepTileEntity.getTileSpec());
            }
        }

        public final String createQuery() {
            return "UPDATE OR ABORT `sep_edit_info_table` SET `user_id` = ?,`tile_spec` = ?,`loc` = ?,`size` = ?,`expand_info` = ?,`expand_info_two` = ?,`expand_info_three` = ? WHERE `user_id` = ? AND `tile_spec` = ?";
        }
    }

    public SepEditTileDaoWrapper_Impl(SepTileRoomDatabase_Impl sepTileRoomDatabase_Impl) {
        this.__db = sepTileRoomDatabase_Impl;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.__insertAdapterOfSepTileEntity = anonymousClass1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.__updateAdapterOfSepTileEntity = anonymousClass2;
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper, com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public final Object deleteAll(Continuation continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new SepEditTileDaoWrapper_Impl$.ExternalSyntheticLambda2(), continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper, com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public final Object deleteTileInfoBySpec(int i, String str, Continuation continuation) {
        SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3 sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3 = new SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3(2);
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3.f$1 = i;
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3.f$0 = str;
        VarHandle.storeStoreFence();
        return DBUtil.performSuspending(this.__db, false, true, sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper, com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public final Object deleteTileInfoByUserID(int i, Continuation continuation) {
        SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda6 sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda6 = new SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda6();
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda6.f$0 = i;
        VarHandle.storeStoreFence();
        return DBUtil.performSuspending(this.__db, false, true, sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda6, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper
    public final Object deleteTilesBySpec(List list, Continuation continuation) {
        SepEditTileDaoWrapper_Impl$.ExternalSyntheticLambda9 externalSyntheticLambda9 = new SepEditTileDaoWrapper_Impl$.ExternalSyntheticLambda9(0);
        externalSyntheticLambda9.f$0 = this;
        externalSyntheticLambda9.f$1 = list;
        VarHandle.storeStoreFence();
        return DBUtil.performInTransactionSuspending(this.__db, externalSyntheticLambda9, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper
    public final Object insertBatchTilesByTransaction(List list, Continuation continuation) {
        SepEditTileDaoWrapper_Impl$.ExternalSyntheticLambda9 externalSyntheticLambda9 = new SepEditTileDaoWrapper_Impl$.ExternalSyntheticLambda9(1);
        externalSyntheticLambda9.f$0 = this;
        externalSyntheticLambda9.f$1 = list;
        VarHandle.storeStoreFence();
        return DBUtil.performInTransactionSuspending(this.__db, externalSyntheticLambda9, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper, com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public final Object insertTileInfo(SepTileEntity sepTileEntity, Continuation continuation) {
        sepTileEntity.getClass();
        SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0 sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0 = new SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0(1);
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0.f$0 = this;
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0.f$1 = sepTileEntity;
        VarHandle.storeStoreFence();
        return DBUtil.performSuspending(this.__db, false, true, sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper, com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public final Flow queryTileInfoByFlow(int i) {
        String[] strArr = {SepEditTileDao.TILE_TABLE_NAME};
        SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3 sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3 = new SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3(0);
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3.f$0 = this;
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3.f$1 = i;
        VarHandle.storeStoreFence();
        return FlowUtil.createFlow(this.__db, false, strArr, sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper, com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public final List queryTileInfoByUserId(int i) {
        SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3 sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3 = new SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3(1);
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3.f$0 = this;
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3.f$1 = i;
        VarHandle.storeStoreFence();
        return (List) DBUtil.performBlocking(this.__db, true, false, sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda3);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper, com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public final SepTileEntity queryTileInfoByUserIdAndSpec(int i, String str) {
        SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5 sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5 = new SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5(0);
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5.f$0 = this;
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5.f$1 = i;
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5.f$2 = str;
        VarHandle.storeStoreFence();
        return (SepTileEntity) DBUtil.performBlocking(this.__db, true, false, sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper
    public final Object setTileInfoByTransaction(int i, List list, Continuation continuation) {
        SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5 sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5 = new SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5(1);
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5.f$0 = this;
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5.f$1 = i;
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5.f$2 = list;
        VarHandle.storeStoreFence();
        return DBUtil.performInTransactionSuspending(this.__db, sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda5, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper, com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public final Object updateTileInfo(SepTileEntity sepTileEntity, Continuation continuation) {
        sepTileEntity.getClass();
        SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0 sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0 = new SepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0(0);
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0.f$0 = this;
        sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0.f$1 = sepTileEntity;
        VarHandle.storeStoreFence();
        return DBUtil.performSuspending(this.__db, false, true, sepEditTileDaoWrapper_Impl$$ExternalSyntheticLambda0, continuation);
    }
}
