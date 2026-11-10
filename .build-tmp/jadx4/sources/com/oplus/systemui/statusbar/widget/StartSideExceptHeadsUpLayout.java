package com.oplus.systemui.statusbar.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.oplus.systemui.notification.utlils.DeviceStateUtils;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.seamless.widget.StartSideFakeFrame;
import com.oplus.systemui.seamless.widget.StatusBarFakeFrame;
import com.oplus.systemui.statusbar.seeding.DIRECTION;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import com.oplus.systemui.statusbar.seeding.IScaledAncestorView;
import com.oplus.systemui.statusbar.seeding.IScaledAncestorViewKt;
import com.oplus.systemui.statusbar.seeding.ScaleChangedListener;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StartSideExceptHeadsUpLayout extends LinearLayout implements CoordAnimIcon, HiddenIconDispatcher.HiddenReceiver, IScaledAncestorView {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StartSideExceptHeadsUpLayout";
    private ArrayList<StatusBarFakeFrame> fakeViews;
    public boolean scaleAnimateEnable;
    private final Lazy scaleAnimatorUpdateListener$delegate;
    private List<ScaleChangedListener> scaleChangedListeners;
    public boolean scaled;
    public float transXForCoord;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StartSideExceptHeadsUpLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scaleAnimateEnable = true;
        this.fakeViews = new ArrayList<>();
        StartSideExceptHeadsUpLayout$$ExternalSyntheticLambda0 startSideExceptHeadsUpLayout$$ExternalSyntheticLambda0 = new StartSideExceptHeadsUpLayout$$ExternalSyntheticLambda0();
        startSideExceptHeadsUpLayout$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.scaleAnimatorUpdateListener$delegate = LazyKt__LazyJVMKt.lazy(startSideExceptHeadsUpLayout$$ExternalSyntheticLambda0);
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
        printWriter.println("StartSideExceptHeadsUpLayout state: ");
        printWriter.println(" scaled=" + this.scaled + " scaleX=" + getScaleX());
        float transXForCoord = getTransXForCoord();
        StringBuilder sb = new StringBuilder(" transXForCoord=");
        sb.append(transXForCoord);
        printWriter.println(sb.toString());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m(" transX=", getTranslationX(), printWriter);
    }

    public final ArrayList<StatusBarFakeFrame> getFakeViews() {
        return this.fakeViews;
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public DIRECTION getPivotXDirection() {
        return isLayoutRtl() ? DIRECTION.RIGHT : DIRECTION.LEFT;
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public boolean getScaleAnimateEnable() {
        return this.scaleAnimateEnable;
    }

    public final ValueAnimator.AnimatorUpdateListener getScaleAnimatorUpdateListener() {
        return (ValueAnimator.AnimatorUpdateListener) this.scaleAnimatorUpdateListener$delegate.getValue();
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
            boolean zIsSupportCapsuleFake = SeedlingPluginManager.Companion.isSupportCapsuleFake(getContext());
            boolean z2 = getScaleAnimateEnable() && (isShown() || zIsSupportCapsuleFake);
            float f = z ? 0.95f : 1.0f;
            IScaledAncestorViewKt.applyAncestorViewScale$default(this, f, z2, zIsSupportCapsuleFake ? getScaleAnimatorUpdateListener() : null, 8);
            if (z2) {
                return;
            }
            onScaleChanged$1(f);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        setPivotX(getPivotXDirection() == DIRECTION.LEFT ? 0.0f : getWidth());
        setPivotY(getHeight() / 2.0f);
        super.onLayout(z, i, i2, i3, i4);
    }

    public final void onScaleChanged$1(float f) {
        for (StatusBarFakeFrame statusBarFakeFrame : this.fakeViews) {
            if (statusBarFakeFrame instanceof StartSideFakeFrame) {
                StartSideFakeFrame startSideFakeFrame = (StartSideFakeFrame) statusBarFakeFrame;
                startSideFakeFrame.parentWidth = getWidth();
                startSideFakeFrame.targetScale = f;
                if (startSideFakeFrame.getHasBind()) {
                    startSideFakeFrame.updateStartSideScale();
                }
            }
        }
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public /* bridge */ /* synthetic */ void removeScaleChangedListener(ScaleChangedListener scaleChangedListener) {
        super.removeScaleChangedListener(scaleChangedListener);
    }

    public final void setFakeViews(ArrayList<StatusBarFakeFrame> arrayList) {
        this.fakeViews = arrayList;
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
