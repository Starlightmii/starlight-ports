package com.oplus.systemui.scenario.host.impl.domain.binder;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioCapsuleViewBinder$CapsuleContent$1$1 implements PointerInputEventHandler {
    public /* synthetic */ HapticFeedback $haptic;
    public /* synthetic */ PrivacyScenarioCapsuleViewModel $viewModel;

    public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
        HapticFeedback hapticFeedback = this.$haptic;
        PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel = this.$viewModel;
        ScenarioCapsuleViewBinder$CapsuleContent$1$1$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ScenarioCapsuleViewBinder$CapsuleContent$1$1$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = hapticFeedback;
        externalSyntheticLambda0.f$1 = privacyScenarioCapsuleViewModel;
        VarHandle.storeStoreFence();
        ScenarioCapsuleViewBinder$$ExternalSyntheticLambda16 scenarioCapsuleViewBinder$$ExternalSyntheticLambda16 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda16(2);
        scenarioCapsuleViewBinder$$ExternalSyntheticLambda16.f$0 = privacyScenarioCapsuleViewModel;
        VarHandle.storeStoreFence();
        Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, (Function1) null, externalSyntheticLambda0, (Function3) null, scenarioCapsuleViewBinder$$ExternalSyntheticLambda16, continuation, 5);
        return objDetectTapGestures$default == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapGestures$default : Unit.INSTANCE;
    }
}
