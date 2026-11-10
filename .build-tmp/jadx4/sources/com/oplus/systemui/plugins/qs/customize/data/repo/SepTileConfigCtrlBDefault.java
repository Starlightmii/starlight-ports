package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.ChangePositionAction;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileConfigCtrlBDefault implements ISepTileConfigCtrlB {
    private final MutableSharedFlow changeEvents;
    private final SepStaticTileConfigRepository staticTileConfigRepo;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigCtrlBDefault$addTilesNonLoc$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepTileConfigCtrlBDefault.this.addTilesNonLoc(null, 0, false, this);
        }
    }

    public SepTileConfigCtrlBDefault(MutableSharedFlow mutableSharedFlow, SepStaticTileConfigRepository sepStaticTileConfigRepository) {
        this.changeEvents = mutableSharedFlow;
        this.staticTileConfigRepo = sepStaticTileConfigRepository;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:28:0x0110  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0110 -> B:29:0x011b). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlB
    public java.lang.Object addTilesNonLoc(java.util.Collection<java.lang.String> r18, int r19, boolean r20, kotlin.coroutines.Continuation r21) {
        /*
            Method dump skipped, instruction units count: 357
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigCtrlBDefault.addTilesNonLoc(java.util.Collection, int, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlB
    public Object dragItem(int i, int i2, boolean z, Continuation continuation) {
        MutableSharedFlow mutableSharedFlow = this.changeEvents;
        ChangePositionAction changePositionAction = new ChangePositionAction(z);
        changePositionAction.fromPosition = i;
        changePositionAction.toPosition = i2;
        VarHandle.storeStoreFence();
        Object objEmit = mutableSharedFlow.emit(changePositionAction, continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }
}
