package com.oplus.systemui.statusbar.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.android.keyguard.AlphaOptimizedLinearLayout;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.oplus.systemui.notification.utlils.DeviceStateUtils;
import com.oplus.systemui.qs.base.util.DeviceUtil;
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
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EndSideContentLayout extends AlphaOptimizedLinearLayout implements CoordAnimIcon, HiddenIconDispatcher.HiddenReceiver, IScaledAncestorView {
    public static final Companion Companion = new Companion();
    private static final String TAG = "EndSideContentLayout";
    public boolean scaleAnimateEnable;
    private final Lazy scaleAnimatorUpdateListener$delegate;
    private List<ScaleChangedListener> scaleChangedListeners;
    public boolean scaled;
    public float transXForCoord;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public EndSideContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scaleAnimateEnable = true;
        this.scaleChangedListeners = new ArrayList();
        EndSideContentLayout$$ExternalSyntheticLambda0 endSideContentLayout$$ExternalSyntheticLambda0 = new EndSideContentLayout$$ExternalSyntheticLambda0();
        endSideContentLayout$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.scaleAnimatorUpdateListener$delegate = LazyKt__LazyJVMKt.lazy(endSideContentLayout$$ExternalSyntheticLambda0);
    }

    private final ValueAnimator.AnimatorUpdateListener getScaleAnimatorUpdateListener() {
        return (ValueAnimator.AnimatorUpdateListener) this.scaleAnimatorUpdateListener$delegate.getValue();
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public void addScaleChangedListener(ScaleChangedListener scaleChangedListener) {
        List<ScaleChangedListener> scaleChangedListeners = getScaleChangedListeners();
        if (scaleChangedListeners != null) {
            scaleChangedListeners.add(scaleChangedListener);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dispatchDraw(Canvas canvas) {
        if (getTransXForCoord() == 0.0f) {
            super/*android.widget.LinearLayout*/.dispatchDraw(canvas);
            return;
        }
        canvas.save();
        canvas.translate(getTransXForCoord(), 0.0f);
        super/*android.widget.LinearLayout*/.dispatchDraw(canvas);
        canvas.restore();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("EndSideContentLayout state: ");
        printWriter.println(" scaled=" + this.scaled + " scaleX=" + getScaleX());
        float transXForCoord = getTransXForCoord();
        StringBuilder sb = new StringBuilder(" transXForCoord=");
        sb.append(transXForCoord);
        printWriter.println(sb.toString());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m(" transX=", getTranslationX(), printWriter);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public DIRECTION getPivotXDirection() {
        return isLayoutRtl() ? DIRECTION.LEFT : DIRECTION.RIGHT;
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public boolean getScaleAnimateEnable() {
        return this.scaleAnimateEnable;
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public List<ScaleChangedListener> getScaleChangedListeners() {
        return this.scaleChangedListeners;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public final float getScaleFraction(boolean z) {
        if (!z) {
            return 1.0f;
        }
        Context context = getContext();
        return DeviceStateUtils.isPortraitInSmallScreen(context, !DeviceUtil.isLandscape(context)) ? 0.85f : 1.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public ViewGroup getScaledAncestorView() {
        return this;
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public float getTransXForCoord() {
        return this.transXForCoord;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public void onHiddenChanged(ArrayList<Rect> arrayList, boolean z) {
        if (z != this.scaled) {
            this.scaled = z;
            boolean zIsSupportCapsuleFake = SeedlingPluginManager.Companion.isSupportCapsuleFake(getContext());
            boolean z2 = getScaleAnimateEnable() && (isShown() || zIsSupportCapsuleFake);
            IScaledAncestorViewKt.applyAncestorViewScale$default(this, z ? 0.85f : 1.0f, z2, zIsSupportCapsuleFake ? getScaleAnimatorUpdateListener() : null, 8);
            if (!zIsSupportCapsuleFake || z2) {
                return;
            }
            float f = z ? 0.85f : 1.0f;
            List<ScaleChangedListener> scaleChangedListeners = getScaleChangedListeners();
            if (scaleChangedListeners != null) {
                Iterator<T> it = scaleChangedListeners.iterator();
                while (it.hasNext()) {
                    ((ScaleChangedListener) it.next()).onScaleChanged(f);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        setPivotX(getPivotXDirection() == DIRECTION.RIGHT ? getWidth() : 0.0f);
        setPivotY(getHeight() / 2.0f);
        super/*android.widget.LinearLayout*/.onLayout(z, i, i2, i3, i4);
    }

    @Override // com.oplus.systemui.statusbar.seeding.IScaledAncestorView
    public void removeScaleChangedListener(ScaleChangedListener scaleChangedListener) {
        List<ScaleChangedListener> scaleChangedListeners = getScaleChangedListeners();
        if (scaleChangedListeners != null) {
            scaleChangedListeners.remove(scaleChangedListener);
        }
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public void setTransXForCoord(float f) {
        if (this.transXForCoord == f) {
            return;
        }
        this.transXForCoord = f;
        invalidate();
    }
}
