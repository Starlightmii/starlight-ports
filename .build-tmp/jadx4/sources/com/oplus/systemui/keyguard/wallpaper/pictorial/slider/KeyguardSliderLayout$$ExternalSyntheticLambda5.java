package com.oplus.systemui.keyguard.wallpaper.pictorial.slider;

import java.lang.reflect.Method;
import java.util.Comparator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class KeyguardSliderLayout$$ExternalSyntheticLambda5 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Method method = KeyguardSliderLayout.sSetMeasuredDimensionRawMethod;
        int iDirection = ((IKeyguardSliderPager) obj).direction() - ((IKeyguardSliderPager) obj2).direction();
        if (iDirection > 0) {
            return 1;
        }
        return iDirection < 0 ? -1 : 0;
    }
}
