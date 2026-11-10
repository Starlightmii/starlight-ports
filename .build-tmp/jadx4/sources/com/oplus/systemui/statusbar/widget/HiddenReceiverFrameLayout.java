package com.oplus.systemui.statusbar.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.statusbar.AlphaOptimizedFrameLayout;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import com.oplus.systemui.statusbar.seeding.ILocationCalculatorKt;
import com.oplus.systemui.statusbar.seeding.IScaledAncestorView;
import com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon;
import java.io.PrintWriter;
import java.util.ArrayList;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HiddenReceiverFrameLayout extends AlphaOptimizedFrameLayout implements HiddenIconDispatcher.HiddenReceiver, CoordAnimIcon {
    public boolean hidden;
    private final ArrayList<Rect> hiddenAreas;
    public int lastWidth;
    public int layoutLeft;
    public boolean shouldScale;
    public float transXForCoord;

    public HiddenReceiverFrameLayout(Context context) {
        super(context);
        this.layoutLeft = -1;
        this.lastWidth = -1;
        this.hiddenAreas = new ArrayList<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dispatchDraw(Canvas canvas) {
        if (getTransXForCoord() == 0.0f) {
            super/*android.widget.FrameLayout*/.dispatchDraw(canvas);
            return;
        }
        canvas.save();
        canvas.translate(getTransXForCoord(), 0.0f);
        super/*android.widget.FrameLayout*/.dispatchDraw(canvas);
        canvas.restore();
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("HiddenReceiverFrameLayout state: ");
        printWriter.println("  view: " + this);
        printWriter.println("  hidden: " + this.hidden);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("  transXForCoord: ", getTransXForCoord(), printWriter);
        printWriter.println("  hiddenAreas: " + this.hiddenAreas);
    }

    public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
        return super/*com.android.systemui.animation.LaunchableView*/.getPaddingForLaunchAnimation();
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public float getTransXForCoord() {
        return this.transXForCoord;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public void onHiddenChanged(ArrayList<Rect> arrayList, boolean z) {
        Pair<Integer, Integer> locationByDefault;
        IScaledAncestorView scaledAncestorViewOrNull = ILocationCalculatorKt.getScaledAncestorViewOrNull(this);
        this.shouldScale = z;
        if (scaledAncestorViewOrNull != null) {
            locationByDefault = ILocationCalculatorKt.getLocationByScaledAncestorView(this, scaledAncestorViewOrNull.getScaledAncestorView(), scaledAncestorViewOrNull.getScaleFraction(z), scaledAncestorViewOrNull.getPivotXDirection());
        } else {
            locationByDefault = ILocationCalculatorKt.getLocationByDefault(this);
        }
        this.hidden = HiddenIconDispatcher.Companion.isInAreas(arrayList, locationByDefault);
        this.hiddenAreas.clear();
        this.hiddenAreas.addAll(arrayList);
        if (this.hidden) {
            setAlpha(0.0f);
        } else {
            setAlpha(1.0f);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super/*android.widget.FrameLayout*/.onLayout(z, i, i2, i3, i4);
        int[] locationOnScreen = getLocationOnScreen();
        if (this.layoutLeft == locationOnScreen[0] && this.lastWidth == getWidth()) {
            return;
        }
        this.layoutLeft = locationOnScreen[0];
        this.lastWidth = getWidth();
        onHiddenChanged(new ArrayList(this.hiddenAreas), this.shouldScale);
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public /* bridge */ /* synthetic */ void setHiddenIconListChanged(boolean z, ArrayList arrayList) {
        super.setHiddenIconListChanged(z, arrayList);
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

    public HiddenReceiverFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.layoutLeft = -1;
        this.lastWidth = -1;
        this.hiddenAreas = new ArrayList<>();
    }

    public HiddenReceiverFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.layoutLeft = -1;
        this.lastWidth = -1;
        this.hiddenAreas = new ArrayList<>();
    }
}
