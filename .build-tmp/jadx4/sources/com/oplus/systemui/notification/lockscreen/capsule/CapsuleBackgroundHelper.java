package com.oplus.systemui.notification.lockscreen.capsule;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.android.systemui.DependencyEx;
import com.oplus.posteffect.BlurDrawable;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.posteffect.params.CornerParams;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.blur.NotificationPlatFormBlurParamsManager;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.notification.lockscreen.capsule.background.CapsuleShardBackgroundDrawableManager;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleBlurCornerParams;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleBlurHelper;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleScrimAlphaHelper;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.AutoRoundRectPathProvider;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleBackgroundHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleBackgroundHelper";
    private final CornerParams conicConerParam;
    private final Context context;
    private final CornerParams g2ConerParam;
    public boolean isImmersiveState;
    public AutoRoundRectPathProvider pathProvider;
    public final int scrimColor;
    private final Paint scrimPaint;
    private CapsuleShardBackgroundDrawableManager sharedBgManager;
    private ViewBlurProxy smoothSharedViewBlurProxy;
    private ViewBlurProxy viewBlurProxy;
    public final ViewBlurManager viewBlurManager = (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
    private final Lazy materialColorManager$delegate = LazyKt__LazyJVMKt.lazy(new CapsuleBackgroundHelper$$ExternalSyntheticLambda1());
    private final OplusPanoramicAodRepository oplusPanoramicAodRepository = (OplusPanoramicAodRepository) DependencyEx.sDependency.getDependency(OplusPanoramicAodRepository.class);
    private final CapsuleBlurCornerParams capsuleBlurCornerParams = new CapsuleBlurCornerParams();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleBackgroundHelper(Context context) {
        this.context = context;
        int color = context.getColor(2131100007);
        this.scrimColor = color;
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(0);
        this.scrimPaint = paint;
        SmoothRoundEx smoothRoundEx = SmoothRoundEx.INSTANCE;
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        this.g2ConerParam = smoothRoundEx.createCapsuleCornerParams(capsuleConfig.getDefaultRadius());
        this.conicConerParam = smoothRoundEx.createMetaBallsCornerParams(capsuleConfig.getDefaultRadius());
    }

    private final MaterialColorManager getMaterialColorManager() {
        return (MaterialColorManager) this.materialColorManager$delegate.getValue();
    }

    public final void drawCapsuleBg(View view, Canvas canvas) {
        Drawable blurDrawable;
        ViewBlurProxy viewBlurProxy;
        int iSave = canvas.save();
        try {
            AutoRoundRectPathProvider autoRoundRectPathProvider = this.pathProvider;
            AutoRoundRectPathProvider autoRoundRectPathProvider2 = null;
            if (autoRoundRectPathProvider == null) {
                autoRoundRectPathProvider = null;
            }
            Rect clipRect = autoRoundRectPathProvider.getClipRect();
            if (CapsuleBlurHelper.INSTANCE.userNormalDrawable(this.context)) {
                ViewBlurProxy viewBlurProxy2 = this.viewBlurProxy;
                ViewBlurProxy.BlurType blurType = viewBlurProxy2 != null ? viewBlurProxy2.getBlurType() : null;
                ViewBlurProxy.BlurType.BlurTypeNon blurTypeNon = ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
                if (!Intrinsics.areEqual(blurType, blurTypeNon) && (viewBlurProxy = this.viewBlurProxy) != null) {
                    viewBlurProxy.setBlurType(blurTypeNon);
                }
            }
            ViewBlurProxy viewBlurProxy3 = this.smoothSharedViewBlurProxy;
            if (viewBlurProxy3 == null || (blurDrawable = viewBlurProxy3.getBlurDrawable(null)) == null) {
                ViewBlurProxy viewBlurProxy4 = this.viewBlurProxy;
                blurDrawable = viewBlurProxy4 != null ? viewBlurProxy4.getBlurDrawable(null) : null;
            }
            if (blurDrawable != null) {
                blurDrawable.setBounds(clipRect);
                blurDrawable.draw(canvas);
            } else {
                AutoRoundRectPathProvider autoRoundRectPathProvider3 = this.pathProvider;
                if (autoRoundRectPathProvider3 == null) {
                    autoRoundRectPathProvider3 = null;
                }
                canvas.clipPath(autoRoundRectPathProvider3.getClipPath());
                canvas.drawColor(getBackgroundColor());
                if (this.scrimPaint.getAlpha() > 0) {
                    AutoRoundRectPathProvider autoRoundRectPathProvider4 = this.pathProvider;
                    if (autoRoundRectPathProvider4 != null) {
                        autoRoundRectPathProvider2 = autoRoundRectPathProvider4;
                    }
                    canvas.drawPath(autoRoundRectPathProvider2.getClipPath(), this.scrimPaint);
                }
            }
            canvas.restore();
        } finally {
            canvas.restoreToCount(iSave);
        }
    }

    public final boolean enablePathProvider(boolean z, boolean z2) {
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (viewBlurProxy == null) {
            return false;
        }
        boolean zUpdateBlurDrawableCornerParams = this.capsuleBlurCornerParams.updateBlurDrawableCornerParams(viewBlurProxy.getBlurConfig(), z);
        if (zUpdateBlurDrawableCornerParams && z2) {
            viewBlurProxy.applyBlurConfig();
        }
        return zUpdateBlurDrawableCornerParams;
    }

    public final void ensureBlurDrawable() {
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (viewBlurProxy != null && viewBlurProxy.getCachePlatformStaticBlurDrawable() == null) {
            viewBlurProxy.getBlurDrawable(null);
        }
    }

    public final int getBackgroundColor() {
        if (this.isImmersiveState) {
            return this.context.getColor(2131101850);
        }
        return getMaterialColorManager().getCurrentCapsuleMaterialColor() != 0 ? getMaterialColorManager().getCurrentCapsuleMaterialColor() : this.context.getColor(2131100005);
    }

    public final MetaBallBlurDrawable getBlurDrawable() {
        PlatformBlurDrawable cachePlatformStaticBlurDrawable;
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        BlurDrawable blurDrawable = (viewBlurProxy == null || (cachePlatformStaticBlurDrawable = viewBlurProxy.getCachePlatformStaticBlurDrawable()) == null) ? null : cachePlatformStaticBlurDrawable.getBlurDrawable();
        if (blurDrawable instanceof MetaBallBlurDrawable) {
            return (MetaBallBlurDrawable) blurDrawable;
        }
        return null;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void initBackground(View view, AutoRoundRectPathProvider autoRoundRectPathProvider) {
        this.pathProvider = autoRoundRectPathProvider;
        ViewBlurProxy viewBlurProxyRequireBlurProxyForView = requireBlurProxyForView(view);
        this.viewBlurProxy = viewBlurProxyRequireBlurProxyForView;
        if (viewBlurProxyRequireBlurProxyForView != null) {
            viewBlurProxyRequireBlurProxyForView.applyBlurConfig();
        }
        CapsuleConfig.INSTANCE.getClass();
        if (CapsuleConfig.isMiddleOrHighAnimLevel) {
            view.setLayerType(2, null);
        }
    }

    public final void initSharedBgManager(CapsuleShardBackgroundDrawableManager capsuleShardBackgroundDrawableManager) {
        this.sharedBgManager = capsuleShardBackgroundDrawableManager;
    }

    public final void onClearTransient(View view) {
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (viewBlurProxy != null) {
            viewBlurProxy.onVisibilityAggregated(false);
        }
        this.viewBlurManager.releaseBlurProxyForView(view, true, "capsule remove");
        this.viewBlurProxy = null;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (viewBlurProxy != null) {
            BlurConfig blurConfig = viewBlurProxy.getBlurConfig();
            blurConfig.setCornerRadius(CapsuleConfig.INSTANCE.getDefaultRadius());
            blurConfig.getGradientStrokeCornerParam().setRadius(blurConfig.getCornerRadius());
            viewBlurProxy.applyBlurConfig();
        }
    }

    public final ViewBlurProxy requireBlurProxyForView(View view) {
        ViewBlurProxy viewBlurProxyRequireBlurProxyForView$default = ViewBlurManager.requireBlurProxyForView$default(this.viewBlurManager, view, ViewBlurManager.CardType.CAPSULE, 0.0f, null, 8);
        viewBlurProxyRequireBlurProxyForView$default.getBlurConfig().setCornerRadius(CapsuleConfig.INSTANCE.getDefaultRadius());
        viewBlurProxyRequireBlurProxyForView$default.getBlurConfig().setRadiusWeight(null);
        viewBlurProxyRequireBlurProxyForView$default.getBlurConfig().setGradientStrokeCornerParam(SmoothRoundEx.INSTANCE.createCapsuleCornerParams(viewBlurProxyRequireBlurProxyForView$default.getBlurConfig().getCornerRadius()));
        viewBlurProxyRequireBlurProxyForView$default.setBlurType(ScrimUtil.isMiddleOrHighAnimLevel(this.context) ? ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE : ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE);
        viewBlurProxyRequireBlurProxyForView$default.getBlurConfig().setSupportMetaBall(true);
        BlurConfig blurConfig = viewBlurProxyRequireBlurProxyForView$default.getBlurConfig();
        CapsuleBackgroundHelper$$ExternalSyntheticLambda0 capsuleBackgroundHelper$$ExternalSyntheticLambda0 = new CapsuleBackgroundHelper$$ExternalSyntheticLambda0();
        capsuleBackgroundHelper$$ExternalSyntheticLambda0.f$0 = viewBlurProxyRequireBlurProxyForView$default;
        VarHandle.storeStoreFence();
        blurConfig.setOnBlurDrawableCreated(capsuleBackgroundHelper$$ExternalSyntheticLambda0);
        return viewBlurProxyRequireBlurProxyForView$default;
    }

    public final void setImmersiveState(boolean z) {
        this.isImmersiveState = z;
    }

    public final void setScrimAlpha(float f) {
        int iComputeScrimAlphaBase = (int) (CapsuleScrimAlphaHelper.INSTANCE.computeScrimAlphaBase(CapsuleScrimAlphaHelper.ScrimAlphaType.CAPSULE_ITEM_BACKGROUND, f) * f);
        if (iComputeScrimAlphaBase != this.scrimPaint.getAlpha()) {
            this.scrimPaint.setAlpha(iComputeScrimAlphaBase);
            MetaBallBlurDrawable blurDrawable = getBlurDrawable();
            if (blurDrawable != null) {
                blurDrawable.setMetaColor(ColorUtils.setAlphaComponent(this.scrimColor, iComputeScrimAlphaBase));
            }
        }
    }

    public final void setStrokeAndShadowAlpha(float f, float f2, Float f3) {
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (viewBlurProxy == null || !NotificationPlatFormBlurParamsManager.INSTANCE.applyAodProgressToCapsule(viewBlurProxy.getBlurConfig().getGradientStrokeLineParam(), viewBlurProxy.getBlurConfig().getInnerShadowParams(), f, f2, f3)) {
            return;
        }
        viewBlurProxy.applyBlurConfig();
    }

    public final void setStrokeRadius(float f, boolean z) {
        PlatformBlurDrawable platformBlurDrawable;
        BlurDrawable blurDrawable;
        BlurDrawable blurDrawable2;
        if (z) {
            ViewBlurProxy viewBlurProxy = this.smoothSharedViewBlurProxy;
            Drawable blurDrawable3 = viewBlurProxy != null ? viewBlurProxy.getBlurDrawable(null) : null;
            platformBlurDrawable = blurDrawable3 instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable3 : null;
            if (platformBlurDrawable == null || (blurDrawable2 = platformBlurDrawable.getBlurDrawable()) == null) {
                return;
            }
            this.conicConerParam.setRadius(f);
            blurDrawable2.setCornerParams(this.conicConerParam);
            return;
        }
        ViewBlurProxy viewBlurProxy2 = this.viewBlurProxy;
        Drawable blurDrawable4 = viewBlurProxy2 != null ? viewBlurProxy2.getBlurDrawable(null) : null;
        platformBlurDrawable = blurDrawable4 instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable4 : null;
        if (platformBlurDrawable == null || (blurDrawable = platformBlurDrawable.getBlurDrawable()) == null) {
            return;
        }
        this.g2ConerParam.setRadius(f);
        blurDrawable.setCornerParams(this.g2ConerParam);
    }

    public final void setStrokeState(boolean z) {
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (viewBlurProxy != null) {
            viewBlurProxy.getBlurConfig();
            if (z) {
                if (!enablePathProvider(false, false)) {
                    return;
                }
            } else if (!enablePathProvider(false, false)) {
                return;
            }
            viewBlurProxy.applyBlurConfig();
        }
    }

    public final void useSmoothRadius(String str, boolean z) {
        CapsuleShardBackgroundDrawableManager capsuleShardBackgroundDrawableManager = this.sharedBgManager;
        if (z) {
            this.smoothSharedViewBlurProxy = capsuleShardBackgroundDrawableManager != null ? capsuleShardBackgroundDrawableManager.requestSharedProxy() : null;
            return;
        }
        if (capsuleShardBackgroundDrawableManager != null) {
            capsuleShardBackgroundDrawableManager.releaseSharedProxy(this.smoothSharedViewBlurProxy);
        }
        this.smoothSharedViewBlurProxy = null;
    }

    public final boolean verifyDrawable(Drawable drawable) {
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        return viewBlurProxy != null && viewBlurProxy.verifyDrawable(drawable);
    }
}
