package com.oplus.systemui.statusbar.phone;

import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScrimControllerExImp$$ExternalSyntheticLambda4 implements Function0 {
    public /* synthetic */ boolean f$0;
    public /* synthetic */ boolean f$1;
    public /* synthetic */ ScrimControllerExImp f$2;
    public /* synthetic */ boolean f$3;
    public /* synthetic */ boolean f$4;

    public final Object invoke() {
        boolean z = this.f$0;
        boolean z2 = this.f$1;
        ScrimControllerExImp scrimControllerExImp = this.f$2;
        boolean z3 = this.f$3;
        boolean z4 = this.f$4;
        boolean z5 = scrimControllerExImp.runnerUnlock;
        boolean z6 = scrimControllerExImp.hasHandleUnlockState;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("blur State to UNLOCK, bouncer=", " changeFromBouncerScrim= ", " runnerUnlock= ", z, z2);
        KeyguardLogger$.ExternalSyntheticOutline0.m(" goingAway=", " hasHandle=", sbM, z5, z3);
        return KeyguardLogger$.ExternalSyntheticOutline0.m(sbM, z6, " isOccludeAnimationPlaying=", z4);
    }
}
