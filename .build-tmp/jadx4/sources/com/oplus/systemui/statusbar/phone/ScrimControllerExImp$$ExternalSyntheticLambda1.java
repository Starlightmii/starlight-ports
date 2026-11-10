package com.oplus.systemui.statusbar.phone;

import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.keyguard.shared.model.ScrimAlpha;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.ScrimState;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScrimControllerExImp$$ExternalSyntheticLambda1 implements Function0 {
    public /* synthetic */ ScrimControllerExImp f$0;
    public /* synthetic */ ScrimAlpha f$1;
    public /* synthetic */ ScrimController f$2;
    public /* synthetic */ boolean f$3;

    public final Object invoke() {
        ScrimControllerExImp scrimControllerExImp = this.f$0;
        ScrimAlpha scrimAlpha = this.f$1;
        ScrimController scrimController = this.f$2;
        boolean z = this.f$3;
        ScrimControllerExImp.Companion companion = ScrimControllerExImp.Companion;
        ScrimState oldState = scrimControllerExImp.getOldState();
        float f = scrimController.mBehindAlpha;
        float f2 = scrimController.mInFrontAlpha;
        float f3 = scrimController.mNotificationsAlpha;
        StringBuilder sb = new StringBuilder("consumeScrimAlphaOverride oldState=");
        sb.append(oldState);
        sb.append(" alphas=");
        sb.append(scrimAlpha);
        sb.append(" mBehindAlpha=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f, " mInFrontAlpha:", f2, " mNotificationsAlpha:");
        sb.append(f3);
        sb.append(" isKeyguardGoingAway=");
        sb.append(z);
        return sb.toString();
    }
}
