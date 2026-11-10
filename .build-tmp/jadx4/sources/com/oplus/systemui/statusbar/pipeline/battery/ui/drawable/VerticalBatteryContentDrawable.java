package com.oplus.systemui.statusbar.pipeline.battery.ui.drawable;

import android.content.Context;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VerticalBatteryContentDrawable extends BatteryBarDrawableBase {
    public static final Companion Companion = new Companion();
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public VerticalBatteryContentDrawable(Context context, int i) {
        super(context, i);
        this.context = context;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public Context getContext() {
        return this.context;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public Drawable getFrameDrawable() {
        return getMContext().getDrawable(2131236713);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public Drawable getOutSideDrawable() {
        return getMContext().getDrawable(2131236711);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public Drawable getProgressDrawable() {
        return getMContext().getDrawable(2131236715);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public final int getProgressLevel(int i) {
        return (int) (((((i * 12.67f) / 100.0f) + 2.0f) / 16.0f) * 10000.0f);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryBarDrawableBase
    public final void updatePadding() {
        setPaddingLeft(getContext().getResources().getDimension(2131173009));
        setPaddingTop(getContext().getResources().getDimension(2131173011));
        setPaddingRight(getContext().getResources().getDimension(2131173010));
        setPaddingBottom(getContext().getResources().getDimension(2131173008));
    }
}
