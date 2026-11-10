package com.oplus.systemui.keyguard.wallpaper.pictorial.slider;

import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class KeyguardSliderLayout$3$$ExternalSyntheticLambda0 implements Consumer {
    public /* synthetic */ int f$0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((IKeyguardSliderPager) obj).onPageScrollStateChanged(this.f$0);
    }
}
