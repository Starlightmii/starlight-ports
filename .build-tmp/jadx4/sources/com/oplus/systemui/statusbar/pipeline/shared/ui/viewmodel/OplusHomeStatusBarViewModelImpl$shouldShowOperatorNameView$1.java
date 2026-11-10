package com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel;

import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.statusbar.pipeline.shared.domain.model.StatusBarDisableFlagsVisibilityModel;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusHomeStatusBarViewModelImpl$shouldShowOperatorNameView$1 extends SuspendLambda implements Function5 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        boolean zBooleanValue3 = ((Boolean) obj4).booleanValue();
        OplusHomeStatusBarViewModelImpl$shouldShowOperatorNameView$1 oplusHomeStatusBarViewModelImpl$shouldShowOperatorNameView$1 = new OplusHomeStatusBarViewModelImpl$shouldShowOperatorNameView$1(5, (Continuation) obj5);
        oplusHomeStatusBarViewModelImpl$shouldShowOperatorNameView$1.Z$0 = zBooleanValue;
        oplusHomeStatusBarViewModelImpl$shouldShowOperatorNameView$1.L$0 = (StatusBarDisableFlagsVisibilityModel) obj2;
        oplusHomeStatusBarViewModelImpl$shouldShowOperatorNameView$1.Z$1 = zBooleanValue2;
        oplusHomeStatusBarViewModelImpl$shouldShowOperatorNameView$1.Z$2 = zBooleanValue3;
        return oplusHomeStatusBarViewModelImpl$shouldShowOperatorNameView$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        StatusBarDisableFlagsVisibilityModel statusBarDisableFlagsVisibilityModel = (StatusBarDisableFlagsVisibilityModel) this.L$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        boolean z4 = z && statusBarDisableFlagsVisibilityModel.isSystemInfoAllowed && z2 && !z3;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sb = new StringBuilder("OplusHomeStatusBarViewModel-->");
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("shouldStatusBarBeVisible:", " isSystemInfoAllowed:", " shouldShowOperator:", z, statusBarDisableFlagsVisibilityModel.isSystemInfoAllowed);
            sbM.append(z2);
            sbM.append(" miniCapsuleShowing:");
            sbM.append(z3);
            sb.append(sbM.toString());
            Log.d("SystemUi--QuickSettings", sb.toString());
        }
        return Boxing.boxBoolean(z4);
    }
}
