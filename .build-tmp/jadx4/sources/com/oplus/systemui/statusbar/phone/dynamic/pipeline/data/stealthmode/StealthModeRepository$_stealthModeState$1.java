package com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.stealthmode;

import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.kairos.BuildScopeKt;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStateListener;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStateObserver;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStealthModeObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class StealthModeRepository$_stealthModeState$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    final /* synthetic */ StealthModeRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StealthModeRepository$_stealthModeState$1(StealthModeRepository stealthModeRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = stealthModeRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        StealthModeRepository$_stealthModeState$1 stealthModeRepository$_stealthModeState$1 = new StealthModeRepository$_stealthModeState$1(this.this$0, continuation);
        stealthModeRepository$_stealthModeState$1.L$0 = obj;
        return stealthModeRepository$_stealthModeState$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ThreeKeyStateObserver threeKeyStateObserver = new observer.1();
            ((observer.1) threeKeyStateObserver).$$this$conflatedCallbackFlow = producerScope;
            VarHandle.storeStoreFence();
            ThreeKeyStateListener.getInstance().addObserver(this.this$0.context, threeKeyStateObserver);
            boolean zIsStealthModeOn = ThreeKeyStealthModeObserver.Companion.isStealthModeOn(this.this$0.context);
            LogUtil.i$default(LogUtil.TAG, "StealthModeRepository", KeyguardLogger$.ExternalSyntheticOutline0.m("stealthModeOn = ", zIsStealthModeOn), null, 8);
            producerScope.trySend-JP2dKIU(Boxing.boxBoolean(zIsStealthModeOn));
            StealthModeRepository stealthModeRepository = this.this$0;
            StealthModeRepository$_stealthModeState$1$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new StealthModeRepository$_stealthModeState$1$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = stealthModeRepository;
            externalSyntheticLambda0.f$1 = threeKeyStateObserver;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(threeKeyStateObserver);
            this.Z$0 = zIsStealthModeOn;
            this.label = 1;
            if (BuildScopeKt.awaitClose(externalSyntheticLambda0, this) == coroutine_suspended) {
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
