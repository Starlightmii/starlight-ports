package com.oplus.systemui.notification.blur;

import android.view.View;
import androidx.car.app.serialization.Bundler$;
import com.oplusos.systemui.common.blurability.DebugToolKt;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ViewBlurManager$$ExternalSyntheticLambda2 implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ViewBlurManager f$0;

    public /* synthetic */ ViewBlurManager$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj, Object obj2) {
        int i = this.$r8$classId;
        ViewBlurManager viewBlurManager = this.f$0;
        View view = (View) obj;
        ViewBlurProxy viewBlurProxy = (ViewBlurProxy) obj2;
        switch (i) {
            case 0:
                if (viewBlurProxy != null) {
                    ViewBlurManager.CardType cardType = (ViewBlurManager.CardType) ViewBlurManager.CardType.getEntries().get(viewBlurProxy.getBlurConfig().getCardType());
                    NotificationPlatFormBlurParamsManager.INSTANCE.updateMirrorScale(cardType, viewBlurManager.resolveBlurMode(cardType, view, null), viewBlurProxy.getBlurConfig().getPlatformMixConfig(), viewBlurManager.useMotionBlur());
                    viewBlurProxy.applyBlurConfig();
                }
                break;
            case 1:
                viewBlurManager.updatePlatformBlurParams(viewBlurProxy, true);
                break;
            default:
                if (viewBlurProxy != null) {
                    ViewBlurProxy.BlurType blurType = viewBlurProxy.getBlurType();
                    viewBlurManager.refreshCardBlurType(viewBlurProxy);
                    viewBlurManager.refreshMotionMixConfig(viewBlurProxy, null);
                    if (!Intrinsics.areEqual(blurType, viewBlurProxy.getBlurType())) {
                        ViewBlurManager.logViewBlurInfo(viewBlurProxy, "[updateRowsBlur]");
                    }
                } else {
                    LogUtil.w$default(8, LogUtil.TAG_COMMON, "ViewBlurManager", Bundler$.ExternalSyntheticOutline0.m("[updateRowsBlur] viewBlurProxy for ", DebugToolKt.getName(view, false), " has been released!"));
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
