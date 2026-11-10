package com.oplus.systemui.scrim;

import com.android.systemui.panelanimation.PanelAnimationEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationBarDrawable$elementShowStateChangeListener$1 implements PanelAnimationEx.ElementShowingChangeListener {
    public /* synthetic */ NotificationBarDrawable this$0;

    public final void onElementShowingChanged() {
        this.this$0.invalidateSelf();
    }

    public final void onFirstElementShowingChanged() {
    }
}
