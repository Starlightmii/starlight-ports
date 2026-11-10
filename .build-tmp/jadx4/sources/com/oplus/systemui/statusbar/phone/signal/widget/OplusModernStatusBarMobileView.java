package com.oplus.systemui.statusbar.phone.signal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.android.systemui.Dumpable;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.pipeline.mobile.ui.view.ModernStatusBarMobileView;
import com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon;
import java.io.PrintWriter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusModernStatusBarMobileView extends ModernStatusBarMobileView implements Dumpable, CoordAnimIcon {
    public float transXForCoord;

    public OplusModernStatusBarMobileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchDraw(Canvas canvas) {
        if (getTransXForCoord() == 0.0f) {
            super/*com.android.systemui.statusbar.pipeline.shared.ui.view.ModernStatusBarView*/.dispatchDraw(canvas);
            return;
        }
        canvas.save();
        canvas.translate(getTransXForCoord(), 0.0f);
        super/*com.android.systemui.statusbar.pipeline.shared.ui.view.ModernStatusBarView*/.dispatchDraw(canvas);
        canvas.restore();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusModernStatusBarMobileView state: ");
        printWriter.println("  view: " + this);
        printWriter.println("  transXForCoord: " + getTransXForCoord());
        Object tag = getTag(2131365374);
        StatusIconContainer.StatusIconState statusIconState = tag instanceof StatusIconContainer.StatusIconState ? (StatusIconContainer.StatusIconState) tag : null;
        if (statusIconState != null) {
            statusIconState.dump(printWriter, strArr);
        }
    }

    public int getSlotTag() {
        return getSubId();
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public float getTransXForCoord() {
        return this.transXForCoord;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public void setTransXForCoord(float f) {
        if (this.transXForCoord == f) {
            return;
        }
        this.transXForCoord = f;
        invalidate();
    }

    public /* bridge */ /* synthetic */ void setVisibleState(int i) {
        super/*com.android.systemui.statusbar.StatusIconDisplayable*/.setVisibleState(i);
    }
}
