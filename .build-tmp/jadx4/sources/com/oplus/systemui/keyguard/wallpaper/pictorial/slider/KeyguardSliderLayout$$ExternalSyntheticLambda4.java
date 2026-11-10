package com.oplus.systemui.keyguard.wallpaper.pictorial.slider;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class KeyguardSliderLayout$$ExternalSyntheticLambda4 implements Runnable {
    public /* synthetic */ KeyguardSliderLayout f$0;
    public /* synthetic */ int f$1;

    @Override // java.lang.Runnable
    public final void run() {
        KeyguardSliderLayout keyguardSliderLayout = this.f$0;
        int i = this.f$1;
        Method method = KeyguardSliderLayout.sSetMeasuredDimensionRawMethod;
        keyguardSliderLayout.updateSliderPagers(i);
    }
}
