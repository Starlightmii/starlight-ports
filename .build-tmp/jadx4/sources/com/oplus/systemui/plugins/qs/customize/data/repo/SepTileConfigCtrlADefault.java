package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.ClipTileSize;
import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.DeformAction;
import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.DragAction;
import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.RemoveEmptyPage;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileConfigCtrlADefault implements ISepTileConfigCtrlA {
    private final MutableSharedFlow changeEvents;
    private final SepStaticTileConfigRepository staticTileConfigRepo;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigCtrlADefault$addTileWithLoc$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepTileConfigCtrlADefault.this.addTileWithLoc(null, 0, null, null, false, this);
        }
    }

    public SepTileConfigCtrlADefault(MutableSharedFlow mutableSharedFlow, SepStaticTileConfigRepository sepStaticTileConfigRepository) {
        this.changeEvents = mutableSharedFlow;
        this.staticTileConfigRepo = sepStaticTileConfigRepository;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00ba, code lost:
    
        if (r14.emit(r6, r2) == r3) goto L26;
     */
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object addTileWithLoc(java.lang.String r15, int r16, com.oplusos.systemui.common.model.Loc r17, com.oplusos.systemui.common.model.SpanSize r18, boolean r19, kotlin.coroutines.Continuation r20) throws java.lang.Throwable {
        /*
            r14 = this;
            r1 = r20
            boolean r2 = r1 instanceof com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigCtrlADefault.AnonymousClass1
            if (r2 == 0) goto L15
            r2 = r1
            com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigCtrlADefault$addTileWithLoc$1 r2 = (com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigCtrlADefault.AnonymousClass1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L15
            int r3 = r3 - r4
            r2.label = r3
            goto L1a
        L15:
            com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigCtrlADefault$addTileWithLoc$1 r2 = new com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigCtrlADefault$addTileWithLoc$1
            r2.<init>(r1)
        L1a:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L5f
            if (r4 == r6) goto L46
            if (r4 != r5) goto L3f
            java.lang.Object r14 = r2.L$3
            com.oplus.systemui.plugins.qs.customize.data.model.TileStaticConfig r14 = (com.oplus.systemui.plugins.qs.customize.data.model.TileStaticConfig) r14
            java.lang.Object r14 = r2.L$2
            com.oplusos.systemui.common.model.SpanSize r14 = (com.oplusos.systemui.common.model.SpanSize) r14
            java.lang.Object r14 = r2.L$1
            com.oplusos.systemui.common.model.Loc r14 = (com.oplusos.systemui.common.model.Loc) r14
            java.lang.Object r14 = r2.L$0
            java.lang.String r14 = (java.lang.String) r14
            kotlin.ResultKt.throwOnFailure(r1)
            goto Lbd
        L3f:
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r14)
            r14 = 0
            return r14
        L46:
            boolean r0 = r2.Z$0
            int r4 = r2.I$0
            java.lang.Object r6 = r2.L$2
            com.oplusos.systemui.common.model.SpanSize r6 = (com.oplusos.systemui.common.model.SpanSize) r6
            java.lang.Object r7 = r2.L$1
            com.oplusos.systemui.common.model.Loc r7 = (com.oplusos.systemui.common.model.Loc) r7
            java.lang.Object r8 = r2.L$0
            java.lang.String r8 = (java.lang.String) r8
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = r0
            r10 = r4
            r12 = r6
            r11 = r7
            r7 = r8
            goto L8d
        L5f:
            kotlin.ResultKt.throwOnFailure(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r15)
            if (r1 == 0) goto L6b
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L6b:
            com.oplus.systemui.plugins.qs.customize.data.repo.SepStaticTileConfigRepository r1 = r14.staticTileConfigRepo
            r2.L$0 = r15
            r4 = r17
            r2.L$1 = r4
            r7 = r18
            r2.L$2 = r7
            r8 = r16
            r2.I$0 = r8
            r9 = r19
            r2.Z$0 = r9
            r2.label = r6
            java.lang.Object r1 = r1.getTileConfig(r15, r2)
            if (r1 != r3) goto L88
            goto Lbc
        L88:
            r11 = r4
            r12 = r7
            r10 = r8
            r13 = r9
            r7 = r15
        L8d:
            r8 = r1
            com.oplus.systemui.plugins.qs.customize.data.model.TileStaticConfig r8 = (com.oplus.systemui.plugins.qs.customize.data.model.TileStaticConfig) r8
            kotlinx.coroutines.flow.MutableSharedFlow r14 = r14.changeEvents
            com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.AddTileWithLoc r6 = new com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.AddTileWithLoc
            r9 = -1
            r6.<init>(r7, r8, r9, r10, r11, r12, r13)
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r2.L$0 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r2.L$1 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r2.L$2 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r2.L$3 = r0
            r2.I$0 = r10
            r2.Z$0 = r13
            r2.label = r5
            java.lang.Object r14 = r14.emit(r6, r2)
            if (r14 != r3) goto Lbd
        Lbc:
            return r3
        Lbd:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigCtrlADefault.addTileWithLoc(java.lang.String, int, com.oplusos.systemui.common.model.Loc, com.oplusos.systemui.common.model.SpanSize, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    public Object changeGridInfo(int i, int i2, Continuation continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    public Object clipSize(String str, SpanSize spanSize, Continuation continuation) {
        Object objEmit = this.changeEvents.emit(new ClipTileSize(str, spanSize), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    public Object deform(int i, SpanSize spanSize, boolean z, Continuation continuation) {
        Object objEmit = this.changeEvents.emit(new DeformAction(i, spanSize, z), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    public Object dragItem(int i, Loc loc, boolean z, Continuation continuation) {
        Object objEmit = this.changeEvents.emit(new DragAction(i, loc, z), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    public Object removeEmptyPage(Continuation continuation) {
        Object objEmit = this.changeEvents.emit(new RemoveEmptyPage(true), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }
}
