package com.oplus.systemui.plugins.qs.customize.data.dao;

import com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.coroutines.flow.Flow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class SepEditTileDaoWrapper implements SepEditTileDao {

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper$deleteTilesBySpec$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepEditTileDaoWrapper.deleteTilesBySpec$suspendImpl(SepEditTileDaoWrapper.this, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper$insertBatchTilesByTransaction$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01991 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C01991(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepEditTileDaoWrapper.insertBatchTilesByTransaction$suspendImpl(SepEditTileDaoWrapper.this, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper$setTileInfoByTransaction$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02001 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C02001(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepEditTileDaoWrapper.setTileInfoByTransaction$suspendImpl(SepEditTileDaoWrapper.this, 0, null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static /* synthetic */ Object deleteTilesBySpec$suspendImpl(SepEditTileDaoWrapper sepEditTileDaoWrapper, List list, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Iterator it;
        Iterable iterable;
        List list2;
        int i;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i2 = anonymousClass1.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i2 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = sepEditTileDaoWrapper.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = sepEditTileDaoWrapper.new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass1.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            List list3 = list;
            it = list3.iterator();
            iterable = list3;
            list2 = list;
            i = 0;
        } else {
            if (i3 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            int i4 = anonymousClass1.I$0;
            Iterator it2 = (Iterator) anonymousClass1.L$3;
            Iterable iterable2 = (Iterable) anonymousClass1.L$2;
            List list4 = (List) anonymousClass1.L$1;
            SepEditTileDaoWrapper sepEditTileDaoWrapper2 = (SepEditTileDaoWrapper) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            list2 = list4;
            iterable = iterable2;
            it = it2;
            i = i4;
            sepEditTileDaoWrapper = sepEditTileDaoWrapper2;
        }
        while (it.hasNext()) {
            Object next = it.next();
            SepTileEntity sepTileEntity = (SepTileEntity) next;
            int userId = sepTileEntity.getUserId();
            String tileSpec = sepTileEntity.getTileSpec();
            anonymousClass1.L$0 = sepEditTileDaoWrapper;
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(list2);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(iterable);
            anonymousClass1.L$3 = it;
            anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(next);
            anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(sepTileEntity);
            anonymousClass1.I$0 = i;
            anonymousClass1.I$1 = 0;
            anonymousClass1.label = 1;
            if (sepEditTileDaoWrapper.deleteTileInfoBySpec(userId, tileSpec, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static /* synthetic */ Object insertBatchTilesByTransaction$suspendImpl(SepEditTileDaoWrapper sepEditTileDaoWrapper, List list, Continuation continuation) {
        C01991 c01991;
        Iterator it;
        Iterable iterable;
        List list2;
        int i;
        if (continuation instanceof C01991) {
            c01991 = (C01991) continuation;
            int i2 = c01991.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c01991.label = i2 - Integer.MIN_VALUE;
            } else {
                c01991 = sepEditTileDaoWrapper.new C01991(continuation);
            }
        } else {
            c01991 = sepEditTileDaoWrapper.new C01991(continuation);
        }
        Object obj = c01991.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c01991.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            List list3 = list;
            it = list3.iterator();
            iterable = list3;
            list2 = list;
            i = 0;
        } else {
            if (i3 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            int i4 = c01991.I$0;
            Iterator it2 = (Iterator) c01991.L$3;
            Iterable iterable2 = (Iterable) c01991.L$2;
            List list4 = (List) c01991.L$1;
            SepEditTileDaoWrapper sepEditTileDaoWrapper2 = (SepEditTileDaoWrapper) c01991.L$0;
            ResultKt.throwOnFailure(obj);
            list2 = list4;
            iterable = iterable2;
            it = it2;
            i = i4;
            sepEditTileDaoWrapper = sepEditTileDaoWrapper2;
        }
        while (it.hasNext()) {
            Object next = it.next();
            SepTileEntity sepTileEntity = (SepTileEntity) next;
            c01991.L$0 = sepEditTileDaoWrapper;
            c01991.L$1 = SpillingKt.nullOutSpilledVariable(list2);
            c01991.L$2 = SpillingKt.nullOutSpilledVariable(iterable);
            c01991.L$3 = it;
            c01991.L$4 = SpillingKt.nullOutSpilledVariable(next);
            c01991.L$5 = SpillingKt.nullOutSpilledVariable(sepTileEntity);
            c01991.I$0 = i;
            c01991.I$1 = 0;
            c01991.label = 1;
            if (sepEditTileDaoWrapper.insertTileInfo(sepTileEntity, c01991) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x007f  */
    /* JADX WARN: Code duplicated, block: B:28:0x00b0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:? A[LOOP:0: B:20:0x0079->B:30:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x006a, code lost:
    
        if (r9.deleteTileInfoByUserID(r10, r0) == r1) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object setTileInfoByTransaction$suspendImpl(com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper r9, int r10, java.util.List r11, kotlin.coroutines.Continuation r12) {
        /*
            boolean r0 = r12 instanceof com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper.C02001
            if (r0 == 0) goto L13
            r0 = r12
            com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper$setTileInfoByTransaction$1 r0 = (com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper.C02001) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper$setTileInfoByTransaction$1 r0 = new com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper$setTileInfoByTransaction$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L5b
            if (r2 == r5) goto L4c
            if (r2 != r4) goto L45
            int r9 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r11 = r0.L$5
            com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity r11 = (com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity) r11
            java.lang.Object r11 = r0.L$3
            java.util.Iterator r11 = (java.util.Iterator) r11
            java.lang.Object r2 = r0.L$2
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.lang.Object r5 = r0.L$1
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r6 = r0.L$0
            com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper r6 = (com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper) r6
            kotlin.ResultKt.throwOnFailure(r12)
            goto L79
        L45:
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r9)
            r9 = 0
            return r9
        L4c:
            int r10 = r0.I$0
            java.lang.Object r9 = r0.L$1
            r11 = r9
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r9 = r0.L$0
            com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper r9 = (com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper) r9
            kotlin.ResultKt.throwOnFailure(r12)
            goto L6d
        L5b:
            kotlin.ResultKt.throwOnFailure(r12)
            r0.L$0 = r9
            r0.L$1 = r11
            r0.I$0 = r10
            r0.label = r5
            java.lang.Object r12 = r9.deleteTileInfoByUserID(r10, r0)
            if (r12 != r1) goto L6d
            goto Lb0
        L6d:
            r12 = r11
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.Iterator r2 = r12.iterator()
            r6 = r9
            r5 = r11
            r11 = r2
            r9 = r3
            r2 = r12
        L79:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto Lb1
            java.lang.Object r12 = r11.next()
            r7 = r12
            com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity r7 = (com.oplus.systemui.plugins.qs.customize.data.model.SepTileEntity) r7
            r0.L$0 = r6
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
            r0.L$1 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$2 = r8
            r0.L$3 = r11
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r0.L$4 = r12
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$5 = r12
            r0.I$0 = r10
            r0.I$1 = r9
            r0.I$2 = r3
            r0.label = r4
            java.lang.Object r12 = r6.insertTileInfo(r7, r0)
            if (r12 != r1) goto L79
        Lb0:
            return r1
        Lb1:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper.setTileInfoByTransaction$suspendImpl(com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDaoWrapper, int, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public abstract /* synthetic */ Object deleteAll(Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public abstract /* synthetic */ Object deleteTileInfoBySpec(int i, String str, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public abstract /* synthetic */ Object deleteTileInfoByUserID(int i, Continuation continuation);

    public Object deleteTilesBySpec(List<SepTileEntity> list, Continuation continuation) {
        return deleteTilesBySpec$suspendImpl(this, list, continuation);
    }

    public Object insertBatchTilesByTransaction(List<SepTileEntity> list, Continuation continuation) {
        return insertBatchTilesByTransaction$suspendImpl(this, list, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public abstract /* synthetic */ Object insertTileInfo(SepTileEntity sepTileEntity, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public abstract /* synthetic */ Flow queryTileInfoByFlow(int i);

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public abstract /* synthetic */ List queryTileInfoByUserId(int i);

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public abstract /* synthetic */ SepTileEntity queryTileInfoByUserIdAndSpec(int i, String str);

    public Object setTileInfoByTransaction(int i, List<SepTileEntity> list, Continuation continuation) {
        return setTileInfoByTransaction$suspendImpl(this, i, list, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.dao.SepEditTileDao
    public abstract /* synthetic */ Object updateTileInfo(SepTileEntity sepTileEntity, Continuation continuation);
}
