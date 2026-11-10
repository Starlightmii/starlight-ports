package com.oplus.systemui.volume.utils.spotlight;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import com.coui.appcompat.spotlight.COUISpotLightEffectDrawable;
import com.oplus.systemui.volume.utils.effect.OplusVolumeSpotLightGate;
import com.oplus.systemui.volume.utils.material.OplusVolumeBarMaterialHost;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeSeekBarSpotLightHelper {
    private VolumeSpotLightTarget appliedTarget = VolumeSpotLightTarget.SEEK_BAR_SIDE;
    private final Path clipPath;
    private final View host;
    private final OplusVolumeBarMaterialHost materialHost;
    private final COUISpotLightEffectDrawable spotLightDrawable;
    private final Rect visualShapeRect;

    public OplusVolumeSeekBarSpotLightHelper(View view, OplusVolumeBarMaterialHost oplusVolumeBarMaterialHost) {
        this.host = view;
        this.materialHost = oplusVolumeBarMaterialHost;
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = new COUISpotLightEffectDrawable();
        OplusVolumeSpotLightEffectParams.apply(this.appliedTarget, view.getContext(), cOUISpotLightEffectDrawable);
        this.spotLightDrawable = cOUISpotLightEffectDrawable;
        this.visualShapeRect = new Rect();
        this.clipPath = new Path();
    }

    public final void drawSpotLightEffect(Canvas canvas) {
        if (this.spotLightDrawable.mEnabled) {
            int width = this.host.getWidth();
            int height = this.host.getHeight();
            if (width <= 0 || height <= 0) {
                return;
            }
            this.materialHost.resolveVisualShapeInto(this.visualShapeRect);
            boolean zIsEmpty = this.visualShapeRect.isEmpty();
            COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = this.spotLightDrawable;
            if (zIsEmpty) {
                cOUISpotLightEffectDrawable.setBounds(0, 0, width, height);
                this.clipPath.rewind();
                float fMin = Math.min(width, height) * 0.5f;
                this.clipPath.addRoundRect(0.0f, 0.0f, width, height, fMin, fMin, Path.Direction.CW);
            } else {
                cOUISpotLightEffectDrawable.setBounds(this.visualShapeRect);
                this.materialHost.buildShapeClipPath(this.clipPath, this.visualShapeRect);
            }
            Path path = this.clipPath;
            int iSave = canvas.save();
            canvas.clipPath(path);
            try {
                this.spotLightDrawable.draw(canvas);
            } finally {
                canvas.restoreToCount(iSave);
            }
        }
    }

    public final void handleMotionEvent(MotionEvent motionEvent) {
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = this.spotLightDrawable;
        if (cOUISpotLightEffectDrawable.mEnabled) {
            cOUISpotLightEffectDrawable.onTouchEvent(motionEvent);
            this.spotLightDrawable.invalidateSelf();
        }
    }

    public final void onAttachedToWindow() {
        this.spotLightDrawable.setCallback(this.host);
    }

    public final void onDetachedFromWindow() {
        this.spotLightDrawable.setCallback(null);
        this.spotLightDrawable.cancel();
    }

    public final void onSizeChanged(int i, int i2) {
        this.spotLightDrawable.setBounds(0, 0, i, i2);
        this.host.invalidate();
    }

    public final void refreshEnableState(boolean z, boolean z2, boolean z3) {
        boolean z4 = OplusVolumeSpotLightGate.isVolumeSpotLightEnabled(this.host.getContext(), z) && this.host.isEnabled() && z2 && !z3;
        this.spotLightDrawable.setEnabled(z4);
        if (!z4) {
            this.spotLightDrawable.cancel();
        }
        this.host.invalidate();
    }

    public final void refreshSpotLightType(boolean z, boolean z2) {
        VolumeSpotLightTarget volumeSpotLightTargetForSeekBar = VolumeSpotLightTarget.Companion.forSeekBar(z);
        if (z2 || volumeSpotLightTargetForSeekBar != this.appliedTarget) {
            this.appliedTarget = volumeSpotLightTargetForSeekBar;
            OplusVolumeSpotLightEffectParams.apply(volumeSpotLightTargetForSeekBar, this.host.getContext(), this.spotLightDrawable);
            this.host.invalidate();
        }
    }
}
