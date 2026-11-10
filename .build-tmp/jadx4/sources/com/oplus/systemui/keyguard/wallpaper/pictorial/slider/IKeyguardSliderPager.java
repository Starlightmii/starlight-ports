package com.oplus.systemui.keyguard.wallpaper.pictorial.slider;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IKeyguardSliderPager {
    void attached();

    void detached();

    int direction();

    View getView();

    void hide();

    void onPageScrollStateChanged(int i);

    void onPageScrolled(float f, int i);

    void onPageSelected(boolean z);

    int pagerType();

    default void setImmersive(boolean z) {
    }

    default void setProcessY(float f) {
    }

    default void setQsExpanded(boolean z) {
    }

    void show();
}
