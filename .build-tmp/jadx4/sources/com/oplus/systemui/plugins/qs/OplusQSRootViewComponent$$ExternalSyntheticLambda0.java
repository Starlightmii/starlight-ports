package com.oplus.systemui.plugins.qs;

import android.util.DisplayMetrics;
import com.oplus.systemui.plugins.qs.util.FlingAnimationUtils;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSRootViewComponent$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSRootViewComponent f$0;

    public /* synthetic */ OplusQSRootViewComponent$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusQSRootViewComponent oplusQSRootViewComponent = this.f$0;
        switch (i) {
            case 0:
                OplusQSRootViewComponent.Companion companion = OplusQSRootViewComponent.Companion;
                return Integer.valueOf(oplusQSRootViewComponent.getContext().getResources().getDimensionPixelSize(2131172082));
            case 1:
                OplusQSRootViewComponent.Companion companion2 = OplusQSRootViewComponent.Companion;
                DisplayMetrics displayMetrics = new DisplayMetrics();
                oplusQSRootViewComponent.getContext().getDisplay().getMetrics(displayMetrics);
                DisplayMetrics displayMetrics2 = new FlingAnimationUtils.Builder(displayMetrics).setMaxLengthSeconds(0.5f).setSpeedUpFactor(0.1f).mDisplayMetrics;
                FlingAnimationUtils flingAnimationUtils = new FlingAnimationUtils();
                float f = displayMetrics2.density;
                flingAnimationUtils.mMinVelocityPxPerSecond = 250.0f * f;
                flingAnimationUtils.mHighVelocityPxPerSecond = f * 3000.0f;
                VarHandle.storeStoreFence();
                return flingAnimationUtils;
            case 2:
                return Float.valueOf(oplusQSRootViewComponent.panelNegY);
            default:
                return OplusQSRootViewComponent.$r8$lambda$CZFCS0XhN003hG2l4EpGkKXImho(oplusQSRootViewComponent);
        }
    }
}
