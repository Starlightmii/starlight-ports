package com.oplus.systemui.panelanimation;

import android.hardware.display.DisplayManagerGlobal;
import android.os.SystemClock;
import android.view.DisplayInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelAnimationExImpl$handleOverLongFirstFrameIfNeeded$$inlined$doOnPreDraw$1 implements Runnable {
    public /* synthetic */ OplusPanelAnimationExImpl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        DisplayInfo displayInfo = DisplayManagerGlobal.getInstance().getDisplayInfo(0);
        long refreshRate = displayInfo != null ? (long) (1000.0f / displayInfo.getRefreshRate()) : 8L;
        if (this.this$0.getPanelExpandFraction().getFractionAnimation().isRunning()) {
            OplusPanelAnimationExImpl oplusPanelAnimationExImpl = this.this$0;
            OplusPanelAnimationExImpl.access$doAnimationFrameSmooth(oplusPanelAnimationExImpl, oplusPanelAnimationExImpl.getPanelExpandFraction().getFractionAnimation(), jUptimeMillis, refreshRate);
        }
        if (this.this$0.getPanelExpandFraction().getBlurFractionAnimation().isRunning()) {
            OplusPanelAnimationExImpl oplusPanelAnimationExImpl2 = this.this$0;
            OplusPanelAnimationExImpl.access$doAnimationFrameSmooth(oplusPanelAnimationExImpl2, oplusPanelAnimationExImpl2.getPanelExpandFraction().getBlurFractionAnimation(), jUptimeMillis, refreshRate);
        }
        if (this.this$0.getTranslationYValue().getTranslationYAnimation().isRunning()) {
            OplusPanelAnimationExImpl oplusPanelAnimationExImpl3 = this.this$0;
            OplusPanelAnimationExImpl.access$doAnimationFrameSmooth(oplusPanelAnimationExImpl3, oplusPanelAnimationExImpl3.getTranslationYValue().getTranslationYAnimation(), jUptimeMillis, refreshRate);
        }
    }
}
