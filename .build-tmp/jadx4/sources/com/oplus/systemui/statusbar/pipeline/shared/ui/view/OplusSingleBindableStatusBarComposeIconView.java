package com.oplus.systemui.statusbar.pipeline.shared.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import androidx.collection.FloatList$;
import com.android.systemui.Dumpable;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.pipeline.shared.ui.view.ModernStatusBarView;
import com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarComposeIconView;
import com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon;
import java.io.PrintWriter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSingleBindableStatusBarComposeIconView extends SingleBindableStatusBarComposeIconView implements Dumpable, CoordAnimIcon {
    public float transXForCoord;

    public OplusSingleBindableStatusBarComposeIconView(Context context, AttributeSet attributeSet) {
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
        printWriter.println("OplusSingleBindableStatusBarComposeIconView state: ");
        printWriter.println("  view: " + this);
        printWriter.println("  transXForCoord: " + getTransXForCoord());
        printWriter.println("  lastDispatcherTint: 0x" + Integer.toHexString(getLastDispatcherTint()));
        printWriter.println("  lastAppliedTint: 0x" + Integer.toHexString(getLastAppliedTint()));
        printWriter.println("  boundTint: 0x" + Integer.toHexString(getLastBoundIconTint()));
        printWriter.println("  composeTint: 0x" + Integer.toHexString(getLastComposeTint()));
        printWriter.println("  tintMismatch: " + (getLastBoundIconTint() != getLastComposeTint()) + " (bound!=compose indicates Dispatcher/Compose disconnect)");
        int width = getWidth();
        int height = getHeight();
        boolean zIsCollecting = ((ModernStatusBarView) this).binding != null ? getBinding$vendor__oplus__packages__apps__systemui__android_common__SystemUI_core().isCollecting() : false;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("  size: ", width, height, "x", " collecting=");
        sbM.append(zIsCollecting);
        printWriter.println(sbM.toString());
        Object tag = getTag(2131365374);
        StatusIconContainer.StatusIconState statusIconState = tag instanceof StatusIconContainer.StatusIconState ? (StatusIconContainer.StatusIconState) tag : null;
        if (statusIconState != null) {
            statusIconState.dump(printWriter, strArr);
        }
    }

    public /* bridge */ /* synthetic */ int getSlotTag() {
        return super/*com.android.systemui.statusbar.StatusIconDisplayable*/.getSlotTag();
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
