package com.oplus.systemui.notification.lockscreen.effect;

import androidx.car.app.serialization.Bundler$;
import androidx.remotecallback.CallbackHandlerRegistry$;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ExtStateAnimParams.ExtStateParams f$0;

    public /* synthetic */ ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        ExtStateAnimParams.ExtStateParams extStateParams = this.f$0;
        switch (i) {
            case 0:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, ExtStateAnimParams.SERVER_NAME, Bundler$.ExternalSyntheticOutline0.m(extStateParams.groupName, "_插值器_时长"), extStateParams.groupName, Long.valueOf(extStateParams.stateExtStateParams.getTime()), 16);
            case 1:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, ExtStateAnimParams.SERVER_NAME, CallbackHandlerRegistry$.ExternalSyntheticOutline0.m(extStateParams.groupName, "_", extStateParams.dragerType, " 跟手_阈值dp"), extStateParams.groupName, Integer.valueOf(extStateParams.stateExtStateParams.getDragMaxAction()), 16);
            case 2:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, ExtStateAnimParams.SERVER_NAME, CallbackHandlerRegistry$.ExternalSyntheticOutline0.m(extStateParams.groupName, "_插值器_", extStateParams.dragerType, " _ALPHA"), extStateParams.stateExtStateParams.getAlpha().getBounce(), extStateParams.stateExtStateParams.getAlpha().getResponse(), 0.0f, 0.0f, 0.0f, null, null, extStateParams.groupName, 496);
            case 3:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, ExtStateAnimParams.SERVER_NAME, CallbackHandlerRegistry$.ExternalSyntheticOutline0.m(extStateParams.groupName, "_插值器_", extStateParams.dragerType, " 模糊"), extStateParams.stateExtStateParams.getBlur().getBounce(), extStateParams.stateExtStateParams.getBlur().getResponse(), 0.0f, 0.0f, 0.0f, null, null, extStateParams.groupName, 496);
            case 4:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, ExtStateAnimParams.SERVER_NAME, Bundler$.ExternalSyntheticOutline0.m(extStateParams.groupName, "_插值器_圆角"), extStateParams.stateExtStateParams.getCornerRadius().getBounce(), extStateParams.stateExtStateParams.getCornerRadius().getResponse(), 0.0f, 0.0f, 0.0f, null, null, extStateParams.groupName, 496);
            case 5:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, ExtStateAnimParams.SERVER_NAME, Bundler$.ExternalSyntheticOutline0.m(extStateParams.groupName, "_插值器_宽度变化"), extStateParams.stateExtStateParams.getClip().getBounce(), extStateParams.stateExtStateParams.getClip().getResponse(), 0.0f, 0.0f, 0.0f, null, null, extStateParams.groupName, 496);
            case 6:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, ExtStateAnimParams.SERVER_NAME, Bundler$.ExternalSyntheticOutline0.m(extStateParams.groupName, "_插值器_高度变化"), extStateParams.stateExtStateParams.getHeight().getBounce(), extStateParams.stateExtStateParams.getHeight().getResponse(), 0.0f, 0.0f, 0.0f, null, null, extStateParams.groupName, 496);
            default:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, ExtStateAnimParams.SERVER_NAME, Bundler$.ExternalSyntheticOutline0.m(extStateParams.groupName, "_插值器_Y变化"), extStateParams.stateExtStateParams.getTranslationY().getBounce(), extStateParams.stateExtStateParams.getTranslationY().getResponse(), 0.0f, 0.0f, 0.0f, null, null, extStateParams.groupName, 496);
        }
    }
}
