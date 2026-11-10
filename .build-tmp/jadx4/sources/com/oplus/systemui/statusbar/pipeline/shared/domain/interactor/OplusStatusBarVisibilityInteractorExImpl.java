package com.oplus.systemui.statusbar.pipeline.shared.domain.interactor;

import com.android.systemui.statusbar.phone.StatusBarHideIconsForBouncerManager;
import com.android.systemui.statusbar.pipeline.shared.domain.interactor.OplusStatusBarVisibilityInteractorEx;
import com.oplus.systemui.statusbar.phone.fragment.CollapsedStatusBarViewModelEx;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarVisibilityInteractorExImpl extends OplusStatusBarVisibilityInteractorEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusStatusBarVisibilityInteractorExImpl";
    private final StateFlow miniCapsuleShowing;
    private final StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.shared.domain.interactor.OplusStatusBarVisibilityInteractorExImpl$getOplusHomeStatusBarAllowed$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        /* synthetic */ boolean Z$0;
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            boolean zBooleanValue = ((Boolean) obj2).booleanValue();
            AnonymousClass1 anonymousClass1 = OplusStatusBarVisibilityInteractorExImpl.this.new AnonymousClass1((Continuation) obj3);
            anonymousClass1.L$0 = (FlowCollector) obj;
            anonymousClass1.Z$0 = zBooleanValue;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:27:0x0071  */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0083, code lost:
        
            if (r0.emit(r8, r7) == r2) goto L29;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = r7.L$0
                kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
                boolean r1 = r7.Z$0
                java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r3 = r7.label
                r4 = 3
                r5 = 2
                r6 = 1
                if (r3 == 0) goto L2a
                if (r3 == r6) goto L26
                if (r3 == r5) goto L22
                if (r3 != r4) goto L1b
                kotlin.ResultKt.throwOnFailure(r8)
                goto L86
            L1b:
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
                r7 = 0
                return r7
            L22:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L71
            L26:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L62
            L2a:
                kotlin.ResultKt.throwOnFailure(r8)
                if (r1 == 0) goto L71
                com.oplus.systemui.statusbar.pipeline.shared.domain.interactor.OplusStatusBarVisibilityInteractorExImpl r8 = com.oplus.systemui.statusbar.pipeline.shared.domain.interactor.OplusStatusBarVisibilityInteractorExImpl.this
                com.android.systemui.statusbar.phone.StatusBarHideIconsForBouncerManager r8 = com.oplus.systemui.statusbar.pipeline.shared.domain.interactor.OplusStatusBarVisibilityInteractorExImpl.access$getStatusBarHideIconsForBouncerManager$p(r8)
                boolean r8 = r8.getTopAppHidesStatusBar()
                if (r8 == 0) goto L71
                boolean r8 = com.oplusos.systemui.common.util.LogUtil.getNormalDebug()
                if (r8 == 0) goto L50
                java.lang.String r8 = "Statusbar"
                boolean r8 = com.oplusos.systemui.common.util.LogUtil.isTagValid(r8)
                if (r8 == 0) goto L50
                java.lang.String r8 = "SystemUi--Statusbar"
                java.lang.String r3 = "OplusStatusBarVisibilityInteractorExImpl-->delay to show status bar!"
                android.util.Log.d(r8, r3)
            L50:
                r8 = 0
                java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)
                r7.L$0 = r0
                r7.Z$0 = r1
                r7.label = r6
                java.lang.Object r8 = r0.emit(r8, r7)
                if (r8 != r2) goto L62
                goto L85
            L62:
                r7.L$0 = r0
                r7.Z$0 = r1
                r7.label = r5
                r5 = 1300(0x514, double:6.423E-321)
                java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r5, r7)
                if (r8 != r2) goto L71
                goto L85
            L71:
                java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)
                java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                r7.L$0 = r3
                r7.Z$0 = r1
                r7.label = r4
                java.lang.Object r7 = r0.emit(r8, r7)
                if (r7 != r2) goto L86
            L85:
                return r2
            L86:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.shared.domain.interactor.OplusStatusBarVisibilityInteractorExImpl.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public OplusStatusBarVisibilityInteractorExImpl(CollapsedStatusBarViewModelEx collapsedStatusBarViewModelEx, StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager) {
        this.statusBarHideIconsForBouncerManager = statusBarHideIconsForBouncerManager;
        this.miniCapsuleShowing = collapsedStatusBarViewModelEx.getMiniCapsuleShowing();
    }

    public StateFlow getMiniCapsuleShowing() {
        return this.miniCapsuleShowing;
    }

    public Flow getOplusHomeStatusBarAllowed(Flow flow) {
        return FlowKt.distinctUntilChanged(FlowKt.transformLatest(flow, new AnonymousClass1(null)));
    }
}
