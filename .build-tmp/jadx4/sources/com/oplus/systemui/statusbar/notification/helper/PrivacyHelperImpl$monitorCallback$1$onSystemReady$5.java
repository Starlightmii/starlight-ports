package com.oplus.systemui.statusbar.notification.helper;

import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.oplus.systemui.notification.base.OplusPanelViewInteractor;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class PrivacyHelperImpl$monitorCallback$1$onSystemReady$5 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ PrivacyHelperImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.PrivacyHelperImpl$monitorCallback$1$onSystemReady$5$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements FlowCollector {
        public /* synthetic */ PrivacyHelperImpl this$0;

        public final Object emit(Object obj, Continuation continuation) {
            ((Boolean) obj).getClass();
            PrivacyHelperImpl privacyHelperImpl = this.this$0;
            if (((HeadsUpManager) privacyHelperImpl.headsUpManager.get()).hasNotifications()) {
                privacyHelperImpl.notifyAppEncryptListener();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrivacyHelperImpl$monitorCallback$1$onSystemReady$5(PrivacyHelperImpl privacyHelperImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = privacyHelperImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new PrivacyHelperImpl$monitorCallback$1$onSystemReady$5(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StateFlow stateFlowIsVisibility = ((OplusPanelViewInteractor) this.this$0.oplusPanelViewInteractor.get()).isVisibility();
            PrivacyHelperImpl privacyHelperImpl = this.this$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = privacyHelperImpl;
            VarHandle.storeStoreFence();
            this.label = 1;
            if (stateFlowIsVisibility.collect(anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Regex$.ExternalSyntheticBUOutline0.m$1();
        return null;
    }
}
