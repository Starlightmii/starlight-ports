package com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel;

import com.android.keyguard.OplusLockIconView$;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
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
final class OplusHomeStatusBarViewModelImpl$oplusShouldShowOperatorName$1 extends SuspendLambda implements Function3 {
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        int iIntValue = ((Number) obj2).intValue();
        OplusHomeStatusBarViewModelImpl$oplusShouldShowOperatorName$1 oplusHomeStatusBarViewModelImpl$oplusShouldShowOperatorName$1 = new OplusHomeStatusBarViewModelImpl$oplusShouldShowOperatorName$1(3, (Continuation) obj3);
        oplusHomeStatusBarViewModelImpl$oplusShouldShowOperatorName$1.Z$0 = zBooleanValue;
        oplusHomeStatusBarViewModelImpl$oplusShouldShowOperatorName$1.I$0 = iIntValue;
        return oplusHomeStatusBarViewModelImpl$oplusShouldShowOperatorName$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        int i = this.I$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        boolean z2 = CustomizeFeatureOption.sIsShowOperatorName;
        boolean z3 = z2 && !z && i >= 0;
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("isFeatureShowOperatorName = ", i, ", slot = ", ", oplusShouldShowOperatorName = ", z2);
        sbM.append(z3);
        LogUtil.i$default(LogUtil.TAG_QS, "OplusHomeStatusBarViewModel", sbM.toString(), null, 8);
        return Boxing.boxBoolean(z3);
    }
}
