package com.oplus.systemui.volume.utils.material;

import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.oplus.systemui.volume.OplusVolumeSeekBar;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import java.lang.invoke.VarHandle;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeBarMaterialHost {
    public static final Companion Companion = new Companion();
    private final OplusVolumeSeekBarMaterialContext context;
    public boolean hasSetOutline;
    private final Rect visualShapeRect = new Rect();
    private final Rect backgroundRect = new Rect();
    private final Rect clipProgressRect = new Rect();
    private final Rect progressRect = new Rect();
    private OplusVolumeShapeGeometry shapeGeometry = OplusVolumeShapeGeometry.EMPTY;
    private final OplusVolumeBarMaterialHost$outlineProvider$1 outlineProvider = new ViewOutlineProvider() { // from class: com.oplus.systemui.volume.utils.material.OplusVolumeBarMaterialHost$outlineProvider$1
        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            OplusVolumeBarMaterialHost.access$applySidebarShadowOutline(this.this$0, outline);
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.utils.material.OplusVolumeBarMaterialHost$attachBackgroundGeometryResolver$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements OplusVolumeShapeGeometryResolver {
        public /* synthetic */ OplusVolumeBarMaterialHost this$0;

        public final OplusVolumeShapePressDrawOverride resolvePressDrawOverride(Rect rect) {
            return this.this$0.resolvePressDrawOverride(rect);
        }
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [com.oplus.systemui.volume.utils.material.OplusVolumeBarMaterialHost$outlineProvider$1] */
    public OplusVolumeBarMaterialHost(OplusVolumeSeekBarMaterialContext oplusVolumeSeekBarMaterialContext) {
        this.context = oplusVolumeSeekBarMaterialContext;
    }

    public static final void access$applySidebarShadowOutline(OplusVolumeBarMaterialHost oplusVolumeBarMaterialHost, Outline outline) {
        if (oplusVolumeBarMaterialHost.shapeGeometry.isEmpty()) {
            return;
        }
        OplusVolumeShapePathHelper.applyToOutline(outline, oplusVolumeBarMaterialHost.shapeGeometry);
        outline.setAlpha(0.999f);
    }

    public final void attachBackgroundGeometryResolver() {
        OplusVolumeBackgroundWrapper seekBarBackground = this.context.getSeekBarBackground();
        if (seekBarBackground instanceof OplusVolumeBackgroundWrapper) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            seekBarBackground.setGeometryResolver(anonymousClass1);
        }
    }

    public final void buildShapeClipPath(Path path, Rect rect) {
        if (rect.isEmpty()) {
            path.reset();
        } else if (this.shapeGeometry.isEmpty()) {
            OplusVolumeShapePathHelper.buildClipPath(path, rect, this.context.getCurBackgroundRadius(), this.context.getBackgroundRoundCornerWeight(), resolveSmoothCorner());
        } else {
            OplusVolumeShapePathHelper.buildClipPath(path, rect, this.shapeGeometry.getCornerRadius(), this.shapeGeometry.getCornerWeight(), this.shapeGeometry.getSmoothCorner());
        }
    }

    public final void refreshPlatformLightForGateChange() {
        if (!VolumeBlurManager.isVolumePlatformBlurEnabled(this.context.getAppContext())) {
            syncElevationAndOutline();
            ((OplusVolumeSeekBar) this.context).invalidate();
        } else {
            VolumeBlurManager.applyVolumeBarPanelBottomAmount(this.context.getMaterialHostView(), VolumeBlurManager.getVolumeBarPanelBottomAmount(), false);
            syncElevationAndOutline();
            ((OplusVolumeSeekBar) this.context).invalidate();
        }
    }

    public final void removeOutline() {
        View materialHostView = this.context.getMaterialHostView();
        this.hasSetOutline = false;
        materialHostView.setOutlineProvider(null);
        materialHostView.setClipToOutline(false);
    }

    public final float resolveCornerRadius(float f) {
        this.context.copyBackgroundRect(this.backgroundRect);
        int iWidth = this.backgroundRect.width();
        int iHeight = this.backgroundRect.height();
        float fAbs = Math.abs(f - 1.0f);
        OplusVolumeSeekBarMaterialContext oplusVolumeSeekBarMaterialContext = this.context;
        float curBackgroundRadius = fAbs > 0.02f ? oplusVolumeSeekBarMaterialContext.getCurBackgroundRadius() : oplusVolumeSeekBarMaterialContext.getCurBackgroundRadius() * f;
        return (iWidth <= 0 || iHeight <= 0) ? curBackgroundRadius : Math.min(curBackgroundRadius, Math.min(iWidth, iHeight) / 2.0f);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0077  */
    /* JADX WARN: Code duplicated, block: B:30:0x0086  */
    /* JADX WARN: Code duplicated, block: B:32:0x0094  */
    /* JADX WARN: Code duplicated, block: B:34:0x009c  */
    /* JADX WARN: Code duplicated, block: B:40:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:44:0x00d8  */
    public final OplusVolumeShapePressDrawOverride resolvePressDrawOverride(Rect rect) {
        float fResolveRealScale;
        float fMin;
        int iCoerceIn;
        Rect rect2;
        if (((OplusVolumeSeekBar) this.context).getVisibility() == 0) {
            Drawable seekBarBackground = this.context.getSeekBarBackground();
            if (OplusVolumeBlurDrawableFinder.needsPressDrawOverride(seekBarBackground)) {
                ((OplusVolumeSeekBar) this.context).preCalcMaterialClipPath();
                if (!((COUIVerticalSeekBar) ((OplusVolumeSeekBar) this.context)).mBackgroundRect.isEmpty()) {
                    this.context.copyBackgroundRect(this.backgroundRect);
                    if (!this.backgroundRect.isEmpty() && !rect.isEmpty()) {
                        Rect rect3 = this.backgroundRect;
                        if (rect3.width() > rect.width() + 1 || rect3.left < rect.left - 1 || rect3.right > rect.right + 1) {
                            resolveVisualShapeBounds(this.visualShapeRect);
                            if (!this.visualShapeRect.isEmpty()) {
                                fResolveRealScale = resolveRealScale();
                                if (!this.shapeGeometry.isEmpty()) {
                                    fMin = this.shapeGeometry.getCornerRadius();
                                } else if (fResolveRealScale == -1.0f && fResolveRealScale > 1.02f) {
                                    fMin = resolveCornerRadius(fResolveRealScale);
                                } else {
                                    Rect rect4 = this.visualShapeRect;
                                    fMin = Math.min(this.context.getCurBackgroundRadius(), Math.min(rect4.width(), rect4.height()) / 2.0f);
                                }
                                float f = fMin;
                                float backgroundRoundCornerWeight = this.context.getBackgroundRoundCornerWeight();
                                boolean zResolveSmoothCorner = resolveSmoothCorner();
                                iCoerceIn = 0;
                                if (OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(seekBarBackground) == null) {
                                    rect2 = this.visualShapeRect;
                                    if (fResolveRealScale != -1.0f && fResolveRealScale > 1.02f) {
                                        iCoerceIn = (int) (((fResolveRealScale - 1.0f) * rect2.width()) / 2.0f);
                                    }
                                    iCoerceIn = RangesKt___RangesKt.coerceIn(iCoerceIn + 2, 2, 8);
                                }
                                return new OplusVolumeShapePressDrawOverride(new Rect(this.visualShapeRect), new Rect(this.visualShapeRect), f, backgroundRoundCornerWeight, zResolveSmoothCorner, iCoerceIn);
                            }
                        } else {
                            float fResolveRealScale2 = resolveRealScale();
                            if (fResolveRealScale2 != -1.0f && fResolveRealScale2 > 1.02f) {
                                resolveVisualShapeBounds(this.visualShapeRect);
                                if (!this.visualShapeRect.isEmpty()) {
                                    fResolveRealScale = resolveRealScale();
                                    if (!this.shapeGeometry.isEmpty()) {
                                        fMin = this.shapeGeometry.getCornerRadius();
                                    } else if (fResolveRealScale == -1.0f) {
                                        Rect rect5 = this.visualShapeRect;
                                        fMin = Math.min(this.context.getCurBackgroundRadius(), Math.min(rect5.width(), rect5.height()) / 2.0f);
                                    } else {
                                        fMin = resolveCornerRadius(fResolveRealScale);
                                    }
                                    float f2 = fMin;
                                    float backgroundRoundCornerWeight2 = this.context.getBackgroundRoundCornerWeight();
                                    boolean zResolveSmoothCorner2 = resolveSmoothCorner();
                                    iCoerceIn = 0;
                                    if (OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(seekBarBackground) == null) {
                                        rect2 = this.visualShapeRect;
                                        if (fResolveRealScale != -1.0f) {
                                            iCoerceIn = (int) (((fResolveRealScale - 1.0f) * rect2.width()) / 2.0f);
                                        }
                                        iCoerceIn = RangesKt___RangesKt.coerceIn(iCoerceIn + 2, 2, 8);
                                    }
                                    return new OplusVolumeShapePressDrawOverride(new Rect(this.visualShapeRect), new Rect(this.visualShapeRect), f2, backgroundRoundCornerWeight2, zResolveSmoothCorner2, iCoerceIn);
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public final float resolveRealScale() {
        View materialHostView = this.context.getMaterialHostView();
        float scaleX = materialHostView.getScaleX();
        float scaleY = materialHostView.getScaleY();
        Object parent = materialHostView.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getVisibility() != 0) {
                return -1.0f;
            }
            scaleX *= view.getScaleX();
            scaleY *= view.getScaleY();
            parent = view.getParent();
        }
        return Math.max(scaleX, scaleY);
    }

    public final boolean resolveSmoothCorner() {
        OplusVolumeSeekBarMaterialContext oplusVolumeSeekBarMaterialContext = this.context;
        return ((COUIVerticalSeekBar) ((OplusVolumeSeekBar) oplusVolumeSeekBarMaterialContext)).mIsSupportSmoothRoundCorner && oplusVolumeSeekBarMaterialContext.getBackgroundRoundCornerWeight() != 0.0f;
    }

    public final void resolveVisualShapeBounds(Rect rect) {
        this.context.copyBackgroundRect(this.backgroundRect);
        if (this.backgroundRect.isEmpty()) {
            rect.setEmpty();
            return;
        }
        rect.set(this.backgroundRect);
        this.context.copyClipProgressRect(this.clipProgressRect);
        if (!this.clipProgressRect.isEmpty()) {
            rect.bottom = Math.min(rect.bottom, this.clipProgressRect.bottom);
        }
        this.context.copyProgressRect(this.progressRect);
        if (!this.progressRect.isEmpty()) {
            rect.bottom = Math.min(rect.bottom, this.progressRect.bottom);
        }
        if (rect.bottom <= rect.top) {
            rect.setEmpty();
        }
    }

    public final void resolveVisualShapeInto(Rect rect) {
        resolveVisualShapeBounds(rect);
    }

    public final void syncBeforeDraw() {
        if (((OplusVolumeSeekBar) this.context).getVisibility() == 0) {
            float fResolveRealScale = resolveRealScale();
            if (fResolveRealScale == -1.0f) {
                return;
            }
            ((OplusVolumeSeekBar) this.context).preCalcMaterialClipPath();
            if (((COUIVerticalSeekBar) ((OplusVolumeSeekBar) this.context)).mBackgroundRect.isEmpty()) {
                return;
            }
            resolveVisualShapeBounds(this.visualShapeRect);
            if (this.visualShapeRect.isEmpty()) {
                this.shapeGeometry = OplusVolumeShapeGeometry.EMPTY;
                return;
            }
            OplusVolumeShapeGeometry oplusVolumeShapeGeometryCreate = OplusVolumeShapeGeometry.Companion.create(this.visualShapeRect, resolveCornerRadius(fResolveRealScale), this.context.getBackgroundRoundCornerWeight(), resolveSmoothCorner());
            OplusVolumeBackgroundWrapper seekBarBackground = this.context.getSeekBarBackground();
            if (seekBarBackground instanceof OplusVolumeBackgroundWrapper) {
                seekBarBackground.applyShapeGeometry(oplusVolumeShapeGeometryCreate);
            }
            if (oplusVolumeShapeGeometryCreate.isEmpty() || !oplusVolumeShapeGeometryCreate.hasSameMaterialBounds(this.shapeGeometry)) {
                this.shapeGeometry = oplusVolumeShapeGeometryCreate;
                Rect rect = new Rect();
                oplusVolumeShapeGeometryCreate.shapeRect(rect);
                if (seekBarBackground != null) {
                    seekBarBackground.setBounds(rect);
                }
                float cornerRadius = oplusVolumeShapeGeometryCreate.getCornerRadius();
                BackgroundBlurDrawable backgroundBlurDrawableFindBackgroundBlurDrawable = OplusVolumeBlurDrawableFinder.findBackgroundBlurDrawable(seekBarBackground);
                if (backgroundBlurDrawableFindBackgroundBlurDrawable != null) {
                    OplusVolumeBlurDrawableFinder.syncBackgroundBlurCornerRadius(backgroundBlurDrawableFindBackgroundBlurDrawable, cornerRadius, 0);
                } else {
                    AutoBlurDrawable autoBlurDrawableFindAutoBlurDrawable = OplusVolumeBlurDrawableFinder.findAutoBlurDrawable(seekBarBackground);
                    if (autoBlurDrawableFindAutoBlurDrawable != null && Math.abs(autoBlurDrawableFindAutoBlurDrawable.getViewBlurProxy().getBlurConfig().getCornerRadius() - cornerRadius) > 0.5f) {
                        autoBlurDrawableFindAutoBlurDrawable.getViewBlurProxy().getBlurConfig().setCornerRadius(cornerRadius);
                        autoBlurDrawableFindAutoBlurDrawable.getViewBlurProxy().applyBlurConfig();
                    }
                }
                if (VolumeBlurManager.isVolumePlatformBlurEnabled(this.context.getAppContext()) && !rect.isEmpty() && !oplusVolumeShapeGeometryCreate.isEmpty()) {
                    VolumeBlurManager.syncVolumeBarPlatformLightBounds(this.context.getMaterialHostView(), rect.width(), rect.height(), oplusVolumeShapeGeometryCreate.getCornerRadius(), Float.valueOf(oplusVolumeShapeGeometryCreate.getCornerWeight()));
                }
                OplusVolumeBlurDrawableFinder.syncTintCornerRadius(seekBarBackground, oplusVolumeShapeGeometryCreate.getCornerRadius());
                if (this.hasSetOutline) {
                    this.context.getMaterialHostView().invalidateOutline();
                }
            }
        }
    }

    public final void syncElevationAndOutline() {
        View materialHostView = this.context.getMaterialHostView();
        if (((OplusVolumeSeekBar) this.context).isVolumePanelExpanded()) {
            materialHostView.setElevation(0.0f);
            removeOutline();
            return;
        }
        if (materialHostView.getElevation() == 0.0f) {
            materialHostView.setElevation(materialHostView.getResources().getDimension(2131173914));
        }
        ((OplusVolumeSeekBar) this.context).getClass();
        if (!OplusVolumeSeekBar.sIsDefaultVolumeTheme) {
            removeOutline();
            return;
        }
        if (!this.hasSetOutline) {
            this.hasSetOutline = true;
            materialHostView.setOutlineProvider(this.outlineProvider);
            materialHostView.setClipToOutline(false);
        }
        materialHostView.invalidateOutline();
    }
}
