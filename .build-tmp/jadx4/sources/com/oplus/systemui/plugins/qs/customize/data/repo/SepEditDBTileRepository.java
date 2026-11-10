package com.oplus.systemui.plugins.qs.customize.data.repo;

import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.oplus.systemui.aod.utils.NoneLocalHbmAodRusManager$;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper;
import com.oplus.systemui.plugins.qs.customize.data.dao.SepGridInfoDao;
import com.oplus.systemui.plugins.qs.customize.data.dao.SepGridInfoDao_Impl;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity;
import com.oplus.systemui.plugins.qs.customize.data.roomdatabase.SepTileRoomDatabase;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepEditDBTileRepository {
    private final ContextInstance contextInstance;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepEditDBTileRepository$deleteTilesBySpec$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepEditDBTileRepository.this.deleteTilesBySpec(null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepEditDBTileRepository$deleteTilesByUserID$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02011 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C02011(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepEditDBTileRepository.this.deleteTilesByUserID(0, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepEditDBTileRepository$insertBatchTilesByTransaction$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02021 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C02021(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepEditDBTileRepository.this.insertBatchTilesByTransaction(null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepEditDBTileRepository$insertGridInfo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02031 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C02031(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepEditDBTileRepository.this.insertGridInfo(null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepEditDBTileRepository$setTilesByTransaction$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02041 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C02041(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepEditDBTileRepository.this.setTilesByTransaction(0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepEditDBTileRepository$updateTileInfo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02051 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C02051(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepEditDBTileRepository.this.updateTileInfo(null, this);
        }
    }

    public SepEditDBTileRepository(ContextInstance contextInstance) {
        this.contextInstance = contextInstance;
    }

    private final SepEditTileDaoWrapper getSepEditTileDao() {
        SepTileRoomDatabase database = SepTileRoomDatabase.Companion.getDatabase(this.contextInstance.getSysUiContext());
        if (database != null) {
            return database.sepEditTileDao();
        }
        return null;
    }

    private final SepGridInfoDao getSepGridInfoDao() {
        SepTileRoomDatabase database = SepTileRoomDatabase.Companion.getDatabase(this.contextInstance.getSysUiContext());
        if (database != null) {
            return database.sepGridInfoDao();
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x008b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v5 */
    public final Object deleteTilesBySpec(List<SepTileEntity> list, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        long jCurrentTimeMillis;
        long j;
        long jCurrentTimeMillis2;
        String strM;
        long jCurrentTimeMillis3;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                SepTileRoomDatabase.Companion companion = SepTileRoomDatabase.Companion;
                jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    SepEditTileDaoWrapper sepEditTileDao = getSepEditTileDao();
                    if (sepEditTileDao != null) {
                        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(list);
                        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(companion);
                        anonymousClass1.I$0 = 0;
                        anonymousClass1.J$0 = jCurrentTimeMillis;
                        anonymousClass1.I$1 = 0;
                        anonymousClass1.label = 1;
                        if (sepEditTileDao.deleteTilesBySpec(list, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        j = jCurrentTimeMillis;
                        jCurrentTimeMillis = j;
                        jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        if (LogUtil.getNormalDebug()) {
                            strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                            Log.d("SystemUi--QuickSettings", strM);
                        }
                    } else {
                        jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        if (LogUtil.getNormalDebug()) {
                            strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                            Log.d("SystemUi--QuickSettings", strM);
                        }
                    }
                } catch (SQLiteException e) {
                    e = e;
                    j = jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug()) {
                        Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                    }
                    jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (LogUtil.getNormalDebug()) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    this = jCurrentTimeMillis;
                    long jCurrentTimeMillis4 = System.currentTimeMillis() - this;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis4, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->"));
                    }
                    throw th;
                }
            } else {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                j = anonymousClass1.J$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    jCurrentTimeMillis = j;
                    jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                        Log.d("SystemUi--QuickSettings", strM);
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                    }
                    jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                    }
                    return Unit.INSTANCE;
                }
            }
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0083  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object deleteTilesByUserID(int i, Continuation continuation) throws Throwable {
        C02011 c02011;
        long jCurrentTimeMillis;
        long j;
        long jCurrentTimeMillis2;
        long jCurrentTimeMillis3;
        String strM;
        long jCurrentTimeMillis4;
        if (continuation instanceof C02011) {
            c02011 = (C02011) continuation;
            int i2 = c02011.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c02011.label = i2 - Integer.MIN_VALUE;
            } else {
                c02011 = new C02011(continuation);
            }
        } else {
            c02011 = new C02011(continuation);
        }
        Object obj = c02011.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c02011.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            SepTileRoomDatabase.Companion companion = SepTileRoomDatabase.Companion;
            jCurrentTimeMillis = System.currentTimeMillis();
            try {
                SepEditTileDaoWrapper sepEditTileDao = getSepEditTileDao();
                if (sepEditTileDao != null) {
                    c02011.L$0 = SpillingKt.nullOutSpilledVariable(companion);
                    c02011.I$0 = i;
                    c02011.I$1 = 0;
                    c02011.J$0 = jCurrentTimeMillis;
                    c02011.I$2 = 0;
                    c02011.label = 1;
                    if (sepEditTileDao.deleteTileInfoByUserID(i, c02011) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    j = jCurrentTimeMillis;
                    jCurrentTimeMillis = j;
                    jCurrentTimeMillis4 = System.currentTimeMillis() - jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug()) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis4, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                        Log.d("SystemUi--QuickSettings", strM);
                    }
                } else {
                    jCurrentTimeMillis4 = System.currentTimeMillis() - jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug()) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis4, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                        Log.d("SystemUi--QuickSettings", strM);
                    }
                }
            } catch (SQLiteException e) {
                e = e;
                j = jCurrentTimeMillis;
                if (LogUtil.getNormalDebug()) {
                    Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                }
                jCurrentTimeMillis3 = System.currentTimeMillis() - j;
                if (LogUtil.getNormalDebug()) {
                    strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                th = th;
                j = jCurrentTimeMillis;
                jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                if (LogUtil.getNormalDebug()) {
                    Log.d("SystemUi--QuickSettings", NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->"));
                }
                throw th;
            }
        } else {
            if (i3 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            j = c02011.J$0;
            try {
                try {
                    ResultKt.throwOnFailure(obj);
                    jCurrentTimeMillis = j;
                    jCurrentTimeMillis4 = System.currentTimeMillis() - jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis4, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                        Log.d("SystemUi--QuickSettings", strM);
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                    }
                    jCurrentTimeMillis3 = System.currentTimeMillis() - j;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                    }
                    return Unit.INSTANCE;
                }
            } catch (Throwable th2) {
                th = th2;
                jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->"));
                }
                throw th;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x008b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v5 */
    public final Object insertBatchTilesByTransaction(List<SepTileEntity> list, Continuation continuation) throws Throwable {
        C02021 c02021;
        long jCurrentTimeMillis;
        long j;
        long jCurrentTimeMillis2;
        String strM;
        long jCurrentTimeMillis3;
        if (continuation instanceof C02021) {
            c02021 = (C02021) continuation;
            int i = c02021.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c02021.label = i - Integer.MIN_VALUE;
            } else {
                c02021 = new C02021(continuation);
            }
        } else {
            c02021 = new C02021(continuation);
        }
        Object obj = c02021.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c02021.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                SepTileRoomDatabase.Companion companion = SepTileRoomDatabase.Companion;
                jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    SepEditTileDaoWrapper sepEditTileDao = getSepEditTileDao();
                    if (sepEditTileDao != null) {
                        c02021.L$0 = SpillingKt.nullOutSpilledVariable(list);
                        c02021.L$1 = SpillingKt.nullOutSpilledVariable(companion);
                        c02021.I$0 = 0;
                        c02021.J$0 = jCurrentTimeMillis;
                        c02021.I$1 = 0;
                        c02021.label = 1;
                        if (sepEditTileDao.insertBatchTilesByTransaction(list, c02021) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        j = jCurrentTimeMillis;
                        jCurrentTimeMillis = j;
                        jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        if (LogUtil.getNormalDebug()) {
                            strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                            Log.d("SystemUi--QuickSettings", strM);
                        }
                    } else {
                        jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        if (LogUtil.getNormalDebug()) {
                            strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                            Log.d("SystemUi--QuickSettings", strM);
                        }
                    }
                } catch (SQLiteException e) {
                    e = e;
                    j = jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug()) {
                        Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                    }
                    jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (LogUtil.getNormalDebug()) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    this = jCurrentTimeMillis;
                    long jCurrentTimeMillis4 = System.currentTimeMillis() - this;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis4, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->"));
                    }
                    throw th;
                }
            } else {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                j = c02021.J$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    jCurrentTimeMillis = j;
                    jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                        Log.d("SystemUi--QuickSettings", strM);
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                    }
                    jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                    }
                    return Unit.INSTANCE;
                }
            }
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0096  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v5 */
    public final Object insertGridInfo(GridInfoEntity gridInfoEntity, Continuation continuation) throws Throwable {
        C02031 c02031;
        long jCurrentTimeMillis;
        long j;
        long jCurrentTimeMillis2;
        String strM;
        long jCurrentTimeMillis3;
        if (continuation instanceof C02031) {
            c02031 = (C02031) continuation;
            int i = c02031.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c02031.label = i - Integer.MIN_VALUE;
            } else {
                c02031 = new C02031(continuation);
            }
        } else {
            c02031 = new C02031(continuation);
        }
        Object obj = c02031.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c02031.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                SepTileRoomDatabase.Companion companion = SepTileRoomDatabase.Companion;
                jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    SepGridInfoDao sepGridInfoDao = getSepGridInfoDao();
                    if (sepGridInfoDao != null) {
                        c02031.L$0 = SpillingKt.nullOutSpilledVariable(gridInfoEntity);
                        c02031.L$1 = SpillingKt.nullOutSpilledVariable(companion);
                        c02031.I$0 = 0;
                        c02031.J$0 = jCurrentTimeMillis;
                        c02031.I$1 = 0;
                        c02031.label = 1;
                        try {
                            if (((SepGridInfoDao_Impl) sepGridInfoDao).insertGridInfo(gridInfoEntity, c02031) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            j = jCurrentTimeMillis;
                            jCurrentTimeMillis = j;
                            jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                            if (LogUtil.getNormalDebug()) {
                                strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                                Log.d("SystemUi--QuickSettings", strM);
                            }
                        } catch (SQLiteException e) {
                            e = e;
                            j = jCurrentTimeMillis;
                            if (LogUtil.getNormalDebug()) {
                                Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                            }
                            jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                            if (LogUtil.getNormalDebug()) {
                                strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                            }
                            return Unit.INSTANCE;
                        } catch (Throwable th) {
                            th = th;
                            this = jCurrentTimeMillis;
                            long jCurrentTimeMillis4 = System.currentTimeMillis() - this;
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                                Log.d("SystemUi--QuickSettings", NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis4, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->"));
                            }
                            throw th;
                        }
                    } else {
                        jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        if (LogUtil.getNormalDebug()) {
                            strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                            Log.d("SystemUi--QuickSettings", strM);
                        }
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                j = c02031.J$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    jCurrentTimeMillis = j;
                    jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                        Log.d("SystemUi--QuickSettings", strM);
                    }
                } catch (SQLiteException e3) {
                    e = e3;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                    }
                    jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                    }
                    return Unit.INSTANCE;
                }
            }
            return Unit.INSTANCE;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public final Object queryGridInfo(int i, Continuation continuation) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            SepGridInfoDao sepGridInfoDao = getSepGridInfoDao();
            return sepGridInfoDao != null ? ((SepGridInfoDao_Impl) sepGridInfoDao).queryGridInfo(i) : null;
        } catch (SQLiteException e) {
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

    public final Object queryGridInfos(Continuation continuation) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            SepGridInfoDao sepGridInfoDao = getSepGridInfoDao();
            return sepGridInfoDao != null ? ((SepGridInfoDao_Impl) sepGridInfoDao).queryGridInfos() : null;
        } catch (SQLiteException e) {
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

    public final Object queryTileInfoByUserId(int i, Continuation continuation) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            SepEditTileDaoWrapper sepEditTileDao = getSepEditTileDao();
            return sepEditTileDao != null ? sepEditTileDao.queryTileInfoByUserId(i) : null;
        } catch (SQLiteException e) {
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

    public final Object queryTileInfoByUserIdAndSpec(int i, String str, Continuation continuation) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            SepEditTileDaoWrapper sepEditTileDao = getSepEditTileDao();
            return sepEditTileDao != null ? sepEditTileDao.queryTileInfoByUserIdAndSpec(i, str) : null;
        } catch (SQLiteException e) {
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

    public final Object queryTilesByFlow(int i, Continuation continuation) {
        SepEditTileDaoWrapper sepEditTileDao = getSepEditTileDao();
        if (sepEditTileDao != null) {
            return sepEditTileDao.queryTileInfoByFlow(i);
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x008d  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v5 */
    public final Object setTilesByTransaction(int i, List<SepTileEntity> list, Continuation continuation) throws Throwable {
        C02041 c02041;
        long jCurrentTimeMillis;
        long j;
        long jCurrentTimeMillis2;
        String strM;
        long jCurrentTimeMillis3;
        if (continuation instanceof C02041) {
            c02041 = (C02041) continuation;
            int i2 = c02041.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c02041.label = i2 - Integer.MIN_VALUE;
            } else {
                c02041 = new C02041(continuation);
            }
        } else {
            c02041 = new C02041(continuation);
        }
        Object obj = c02041.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c02041.label;
        try {
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                SepTileRoomDatabase.Companion companion = SepTileRoomDatabase.Companion;
                jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    SepEditTileDaoWrapper sepEditTileDao = getSepEditTileDao();
                    if (sepEditTileDao != null) {
                        c02041.L$0 = SpillingKt.nullOutSpilledVariable(list);
                        c02041.L$1 = SpillingKt.nullOutSpilledVariable(companion);
                        c02041.I$0 = i;
                        c02041.I$1 = 0;
                        c02041.J$0 = jCurrentTimeMillis;
                        c02041.I$2 = 0;
                        c02041.label = 1;
                        if (sepEditTileDao.setTileInfoByTransaction(i, list, c02041) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        j = jCurrentTimeMillis;
                        jCurrentTimeMillis = j;
                        jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        if (LogUtil.getNormalDebug()) {
                            strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                            Log.d("SystemUi--QuickSettings", strM);
                        }
                    } else {
                        jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        if (LogUtil.getNormalDebug()) {
                            strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                            Log.d("SystemUi--QuickSettings", strM);
                        }
                    }
                } catch (SQLiteException e) {
                    e = e;
                    j = jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug()) {
                        Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                    }
                    jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (LogUtil.getNormalDebug()) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    this = jCurrentTimeMillis;
                    long jCurrentTimeMillis4 = System.currentTimeMillis() - this;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis4, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->"));
                    }
                    throw th;
                }
            } else {
                if (i3 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                j = c02041.J$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    jCurrentTimeMillis = j;
                    jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                        Log.d("SystemUi--QuickSettings", strM);
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                    }
                    jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                    }
                    return Unit.INSTANCE;
                }
            }
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x008b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v5 */
    public final Object updateTileInfo(SepTileEntity sepTileEntity, Continuation continuation) throws Throwable {
        C02051 c02051;
        long jCurrentTimeMillis;
        long j;
        long jCurrentTimeMillis2;
        String strM;
        long jCurrentTimeMillis3;
        if (continuation instanceof C02051) {
            c02051 = (C02051) continuation;
            int i = c02051.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c02051.label = i - Integer.MIN_VALUE;
            } else {
                c02051 = new C02051(continuation);
            }
        } else {
            c02051 = new C02051(continuation);
        }
        Object obj = c02051.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c02051.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                SepTileRoomDatabase.Companion companion = SepTileRoomDatabase.Companion;
                jCurrentTimeMillis = System.currentTimeMillis();
                try {
                    SepEditTileDaoWrapper sepEditTileDao = getSepEditTileDao();
                    if (sepEditTileDao != null) {
                        c02051.L$0 = SpillingKt.nullOutSpilledVariable(sepTileEntity);
                        c02051.L$1 = SpillingKt.nullOutSpilledVariable(companion);
                        c02051.I$0 = 0;
                        c02051.J$0 = jCurrentTimeMillis;
                        c02051.I$1 = 0;
                        c02051.label = 1;
                        if (sepEditTileDao.updateTileInfo(sepTileEntity, c02051) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        j = jCurrentTimeMillis;
                        jCurrentTimeMillis = j;
                        jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        if (LogUtil.getNormalDebug()) {
                            strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                            Log.d("SystemUi--QuickSettings", strM);
                        }
                    } else {
                        jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                        if (LogUtil.getNormalDebug()) {
                            strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                            Log.d("SystemUi--QuickSettings", strM);
                        }
                    }
                } catch (SQLiteException e) {
                    e = e;
                    j = jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug()) {
                        Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                    }
                    jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (LogUtil.getNormalDebug()) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    this = jCurrentTimeMillis;
                    long jCurrentTimeMillis4 = System.currentTimeMillis() - this;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis4, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->"));
                    }
                    throw th;
                }
            } else {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                j = c02051.J$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    jCurrentTimeMillis = j;
                    jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis3, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                        Log.d("SystemUi--QuickSettings", strM);
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "SepTileRoomDatabase-->".concat("use: " + e));
                    }
                    jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        strM = NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(jCurrentTimeMillis2, "DB Operation Time: ", " ms", "SepTileRoomDatabase-->");
                    }
                    return Unit.INSTANCE;
                }
            }
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
