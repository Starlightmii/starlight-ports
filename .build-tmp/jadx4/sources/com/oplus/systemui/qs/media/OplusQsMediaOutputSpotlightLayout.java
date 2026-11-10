package com.oplus.systemui.qs.media;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.oplus.systemui.notification.material.NotificationSpotLightEffectManager;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaOutputSpotlightLayout extends LinearLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final Runnable onSpotLightAvailabilityChanged;
    private final QsMediaSpotLightHelper spotLight;
    public boolean spotLightActive;

    public OplusQsMediaOutputSpotlightLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.spotLight = new QsMediaSpotLightHelper(this, QsMediaSpotLightHelper.ClipShape.CAPSULE);
        OplusQsMediaOutputSpotlightLayout$onSpotLightAvailabilityChanged$1 oplusQsMediaOutputSpotlightLayout$onSpotLightAvailabilityChanged$1 = new OplusQsMediaOutputSpotlightLayout$onSpotLightAvailabilityChanged$1();
        oplusQsMediaOutputSpotlightLayout$onSpotLightAvailabilityChanged$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onSpotLightAvailabilityChanged = oplusQsMediaOutputSpotlightLayout$onSpotLightAvailabilityChanged$1;
    }

    public final void applySystemSpotLightPolicy() {
        if (isAttachedToWindow() && getVisibility() == 0 && NotificationSpotLightEffectManager.spotLightEnabled) {
            this.spotLight.ensureDrawable();
            this.spotLight.updateDrawableBounds(getWidth(), getHeight());
            this.spotLight.syncDrawableEnabledFromHost();
            this.spotLightActive = true;
            invalidate();
            return;
        }
        this.spotLight.releaseDrawable();
        if (this.spotLightActive) {
            this.spotLightActive = false;
            invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.spotLight.drawSpotLightEffect(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            ViewParent parent2 = getParent();
            if (parent2 != null) {
                parent2.requestDisallowInterceptTouchEvent(true);
            }
        } else if ((actionMasked == 1 || actionMasked == 3) && (parent = getParent()) != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        this.spotLight.updateDrawableBounds(getWidth(), getHeight());
        this.spotLight.handleMotionEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationSpotLightEffectManager.addOnSpotLightAvailabilityChangedListener(this.onSpotLightAvailabilityChanged);
        applySystemSpotLightPolicy();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        NotificationSpotLightEffectManager.removeOnSpotLightAvailabilityChangedListener(this.onSpotLightAvailabilityChanged);
        this.spotLight.releaseDrawable();
        this.spotLightActive = false;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.spotLight.updateDrawableBounds(i, i2);
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view != this) {
            return;
        }
        applySystemSpotLightPolicy();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.spotLight.syncDrawableEnabledFromHost();
    }

    public OplusQsMediaOutputSpotlightLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ OplusQsMediaOutputSpotlightLayout(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public OplusQsMediaOutputSpotlightLayout(Context context) {
        this(context, null, 0, 6, null);
    }
}
