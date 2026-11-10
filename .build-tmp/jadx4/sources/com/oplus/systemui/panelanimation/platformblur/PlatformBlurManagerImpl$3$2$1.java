package com.oplus.systemui.panelanimation.platformblur;

import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class PlatformBlurManagerImpl$3$2$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ PlatformBlurManagerImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlatformBlurManagerImpl$3$2$1(PlatformBlurManagerImpl platformBlurManagerImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = platformBlurManagerImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new PlatformBlurManagerImpl$3$2$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        boolean zUseMotionBlur = this.this$0.useMotionBlur();
        this.this$0._useMotionBlur.setValue(Boxing.boxBoolean(zUseMotionBlur));
        BaseQSResPool.updateMirrorScaleValue(zUseMotionBlur ? 0.94f : 1.0f);
        PlatformBlurManager.PlatformBlurMode platformBlurMode = this.this$0.currentPlatformBlurMode;
        if (platformBlurMode != null) {
            this.this$0.setPlatformBlurMode(platformBlurMode);
        }
        return Unit.INSTANCE;
    }
}
