package com.oplus.systemui.keyguard.wallpaper.uiengine;

import com.android.systemui.keyguard.shared.model.StatusBarState;
import com.oplusos.keyguard.utils.KgdLogUtil;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class KeyguardEngineContainerController$2$1$1$keyguardStateFlow$2 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        KeyguardEngineContainerController$2$1$1$keyguardStateFlow$2 keyguardEngineContainerController$2$1$1$keyguardStateFlow$2 = new KeyguardEngineContainerController$2$1$1$keyguardStateFlow$2(3, (Continuation) obj3);
        keyguardEngineContainerController$2$1$1$keyguardStateFlow$2.L$0 = (StatusBarState) obj;
        keyguardEngineContainerController$2$1$1$keyguardStateFlow$2.Z$0 = zBooleanValue;
        return keyguardEngineContainerController$2$1$1$keyguardStateFlow$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i;
        StatusBarState statusBarState = (StatusBarState) this.L$0;
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        KgdLogUtil.i("KeyguardEngineContainer", "keyguardStateFlow: statusBarState=" + statusBarState + ", isKeyguardStable=" + z);
        int iOrdinal = statusBarState.ordinal();
        if (iOrdinal != 0) {
            i = 1;
            if (iOrdinal != 1) {
                if (iOrdinal != 2) {
                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                    return null;
                }
            } else if (!z) {
            }
            i = 2;
        } else {
            i = 0;
        }
        return Boxing.boxInt(i);
    }
}
