package com.oplus.systemui.volume.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.android.keyguard.AlphaOptimizedImageButton;
import com.oplus.systemui.volume.utils.effect.OplusVolumeSpotLightGate;
import com.oplus.systemui.volume.utils.material.VolumeBlurManager;
import com.oplus.systemui.volume.utils.spotlight.OplusVolumeButtonSpotLightHelper;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeSettingsButton extends AlphaOptimizedImageButton {
    public boolean isDefaultVolumeTheme;
    private final OplusVolumeButtonSpotLightHelper spotLight;

    /* JADX WARN: Multi-variable type inference failed */
    public OplusVolumeSettingsButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.spotLight = new OplusVolumeButtonSpotLightHelper(this);
        this.isDefaultVolumeTheme = true;
        setPadding(0, 0, 0, 0);
        setMinimumWidth(0);
        setMinimumHeight(0);
        setBackground(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void applySpotLightPolicy() {
        if (!isAttachedToWindow() || getVisibility() != 0) {
            this.spotLight.releaseDrawable();
            return;
        }
        boolean zIsVolumeSpotLightEnabled = OplusVolumeSpotLightGate.isVolumeSpotLightEnabled(getContext(), this.isDefaultVolumeTheme);
        OplusVolumeButtonSpotLightHelper oplusVolumeButtonSpotLightHelper = this.spotLight;
        if (!zIsVolumeSpotLightEnabled) {
            oplusVolumeButtonSpotLightHelper.releaseDrawable();
            return;
        }
        oplusVolumeButtonSpotLightHelper.ensureDrawable();
        this.spotLight.updateDrawableBounds(getWidth(), getHeight());
        this.spotLight.syncDrawableEnabledFromHost();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.ImageButton*/.onAttachedToWindow();
        refreshBlurLightEnableState(this.isDefaultVolumeTheme);
        applySpotLightPolicy();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        this.spotLight.releaseDrawable();
        super/*android.widget.ImageButton*/.onDetachedFromWindow();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDraw(Canvas canvas) {
        super/*android.widget.ImageButton*/.onDraw(canvas);
        if (this.isDefaultVolumeTheme) {
            this.spotLight.drawSpotLightEffect(canvas);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super/*android.widget.ImageButton*/.onSizeChanged(i, i2, i3, i4);
        this.spotLight.updateDrawableBounds(i, i2);
        if (VolumeBlurManager.isVolumePlatformBlurEnabled(getContext())) {
            VolumeBlurManager.applySettingsButtonMixColors(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isDefaultVolumeTheme && this.spotLight.shouldHandleMotionEvent(motionEvent)) {
            this.spotLight.handleMotionEvent(motionEvent);
        }
        return super/*android.widget.ImageButton*/.onTouchEvent(motionEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onVisibilityChanged(View view, int i) {
        super/*android.widget.ImageButton*/.onVisibilityChanged(view, i);
        if (view != this) {
            return;
        }
        applySpotLightPolicy();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void refreshBlurLightEnableState(boolean z) {
        this.isDefaultVolumeTheme = z;
        if (VolumeBlurManager.isVolumePlatformBlurEnabled(getContext())) {
            VolumeBlurManager.applySettingsButtonMixColors(this);
        }
        refreshIconTintForPlatformBlur();
        invalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void refreshIconTintForPlatformBlur() {
        if (this.isDefaultVolumeTheme && VolumeBlurManager.isVolumePlatformBlurEnabled(getContext())) {
            setImageTintList(ColorStateList.valueOf(-1));
        }
    }

    public final void refreshSpotLightType() {
        this.spotLight.refreshSpotLightType();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setEnabled(boolean z) {
        super/*android.widget.ImageButton*/.setEnabled(z);
        this.spotLight.syncDrawableEnabledFromHost();
    }

    public /* synthetic */ OplusVolumeSettingsButton(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OplusVolumeSettingsButton(Context context) {
        AttributeSet attributeSet = null;
        this(context, attributeSet, 2, attributeSet);
    }
}
