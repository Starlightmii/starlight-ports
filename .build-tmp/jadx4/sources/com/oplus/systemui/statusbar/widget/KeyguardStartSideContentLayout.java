package com.oplus.systemui.statusbar.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.oplus.systemui.notification.utlils.DeviceStateUtils;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.statusbar.phone.dynamic.DynamicContentLayout;
import com.oplus.systemui.statusbar.seeding.DIRECTION;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import com.oplus.systemui.statusbar.seeding.IScaledAncestorView;
import com.oplus.systemui.statusbar.seeding.IScaledAncestorViewKt;
import com.oplus.systemui.statusbar.seeding.ScaleChangedListener;
import com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardStartSideContentLayout extends DynamicContentLayout implements CoordAnimIcon, HiddenIconDispatcher.HiddenReceiver, IScaledAncestorView {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyguardStartSideContentLayout";
    public boolean scaleAnimateEnable;
    private List<ScaleChangedListener> scaleChangedListeners;
    public boolean scaled;
    public float transXForCoord;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public KeyguardStartSideContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scaleAnimateEnable = true;
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public /* bridge */ /* synthetic */ void addScaleChangedListener(ScaleChangedListener scaleChangedListener) {
        super.addScaleChangedListener(scaleChangedListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        if (getTransXForCoord() == 0.0f) {
            super.dispatchDraw(canvas);
            return;
        }
        canvas.save();
        canvas.translate(getTransXForCoord(), 0.0f);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("KeyguardStartSideContentLayout state: ");
        printWriter.println(" scaled=" + this.scaled + " scaleX=" + getScaleX());
        float transXForCoord = getTransXForCoord();
        StringBuilder sb = new StringBuilder(" transXForCoord=");
        sb.append(transXForCoord);
        printWriter.println(sb.toString());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m(" transX=", getTranslationX(), printWriter);
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public DIRECTION getPivotXDirection() {
        return isLayoutRtl() ? DIRECTION.RIGHT : DIRECTION.LEFT;
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public boolean getScaleAnimateEnable() {
        return this.scaleAnimateEnable;
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public List<ScaleChangedListener> getScaleChangedListeners() {
        return this.scaleChangedListeners;
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public final float getScaleFraction(boolean z) {
        if (!z) {
            return 1.0f;
        }
        Context context = getContext();
        return DeviceStateUtils.isPortraitInSmallScreen(context, !DeviceUtil.isLandscape(context)) ? 0.95f : 1.0f;
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public ViewGroup getScaledAncestorView() {
        return this;
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public float getTransXForCoord() {
        return this.transXForCoord;
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public void onHiddenChanged(ArrayList<Rect> arrayList, boolean z) {
        if (z != this.scaled) {
            this.scaled = z;
            IScaledAncestorViewKt.applyAncestorViewScale$default(this, z ? 0.95f : 1.0f, getScaleAnimateEnable() && isShown(), null, 12);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        setPivotX(getPivotXDirection() == DIRECTION.LEFT ? 0.0f : getWidth());
        setPivotY(getHeight() / 2.0f);
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public /* bridge */ /* synthetic */ void removeScaleChangedListener(ScaleChangedListener scaleChangedListener) {
        super.removeScaleChangedListener(scaleChangedListener);
    }

    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public /* bridge */ /* synthetic */ void setHiddenIconListChanged(boolean z, ArrayList arrayList) {
        super.setHiddenIconListChanged(z, arrayList);
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public void setScaleAnimateEnable(boolean z) {
        this.scaleAnimateEnable = z;
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public void setScaleChangedListeners(List<ScaleChangedListener> list) {
        this.scaleChangedListeners = list;
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public void setTransXForCoord(float f) {
        if (this.transXForCoord == f) {
            return;
        }
        this.transXForCoord = f;
        invalidate();
    }
}
