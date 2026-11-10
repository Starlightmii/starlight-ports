package com.oplus.systemui.seamless.widget;

import android.content.Context;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ClockFakeFrame extends StartSideFakeFrame {
    public ClockFakeFrame(Context context) {
        super(context);
    }

    @Override // com.oplus.systemui.seamless.widget.StatusBarFakeFrame
    public float getRealStatusElementLocX() {
        if (getScaleX() == 1.0f) {
            return super.getRealStatusElementLocX();
        }
        return getAbsoluteTranslationX(getMView()) + getAbsoluteLeft(getMView());
    }
}
