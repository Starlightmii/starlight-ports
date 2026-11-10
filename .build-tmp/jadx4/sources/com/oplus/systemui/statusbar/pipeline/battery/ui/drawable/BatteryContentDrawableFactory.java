package com.oplus.systemui.statusbar.pipeline.battery.ui.drawable;

import android.content.Context;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryContentDrawableFactory {
    public static final BatteryContentDrawableFactory INSTANCE = new BatteryContentDrawableFactory();

    public final BatteryContentDrawableBase createContentDrawable(Context context, int i, int i2) {
        if (i == 1) {
            return new HorizontalBatteryContentDrawable(context, i2);
        }
        if (i != 3) {
            return i != 4 ? new BatteryContentDrawableBase(context, i2) : new CircleBatteryContentDrawable(context, i2);
        }
        return new VerticalBatteryContentDrawable(context, i2);
    }
}
