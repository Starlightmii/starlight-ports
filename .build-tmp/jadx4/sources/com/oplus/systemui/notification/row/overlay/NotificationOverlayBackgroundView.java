package com.oplus.systemui.notification.row.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.notification.base.radius.SmoothRoundExKt;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.platformblur.MultiPlatformBlurHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationOverlayBackgroundView extends View {
    public static final /* synthetic */ int $r8$clinit = 0;
    private Drawable aodDrawable;
    private Function0 authorViewBlurProxyGetter;
    private final Rect bounds;
    private ViewBlurProxy currentBlurProxy;
    private Drawable defaultDrawable;
    private Function1 drawBoundsGetter;
    public boolean enableBlur;
    private Function0 maskColorGetter;
    private final Lazy materialColorDrawable$delegate;
    public int normalColor;

    public NotificationOverlayBackgroundView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.authorViewBlurProxyGetter = NotificationOverlayBackgroundView$authorViewBlurProxyGetter$1.INSTANCE;
        this.maskColorGetter = new NotificationOverlayBackgroundView$$ExternalSyntheticLambda0();
        this.drawBoundsGetter = new NotificationOverlayBackgroundView$$ExternalSyntheticLambda1();
        this.defaultDrawable = SmoothRoundExKt.getSmoothDrawable(context, 2131102886);
        NotificationOverlayBackgroundView$$ExternalSyntheticLambda2 notificationOverlayBackgroundView$$ExternalSyntheticLambda2 = new NotificationOverlayBackgroundView$$ExternalSyntheticLambda2();
        notificationOverlayBackgroundView$$ExternalSyntheticLambda2.f$0 = context;
        VarHandle.storeStoreFence();
        this.materialColorDrawable$delegate = LazyKt__LazyJVMKt.lazy(notificationOverlayBackgroundView$$ExternalSyntheticLambda2);
        this.normalColor = context.getColor(2131102886);
        this.bounds = new Rect();
    }

    private final Drawable getMaterialColorDrawable() {
        return (Drawable) this.materialColorDrawable$delegate.getValue();
    }

    private final int getOverlayBackgroundColor() {
        int currentMaterialColor = ((MaterialColorManager) DependencyEx.sDependency.getDependency(MaterialColorManager.class)).getCurrentMaterialColor();
        return currentMaterialColor != 0 ? currentMaterialColor : this.normalColor;
    }

    public final void cloneBlurConfig(ViewBlurProxy viewBlurProxy) {
        BlurConfig blurConfig;
        ViewBlurProxy viewBlurProxy2;
        ViewBlurProxy viewBlurProxy3 = this.currentBlurProxy;
        if (viewBlurProxy3 == null || (blurConfig = viewBlurProxy3.getBlurConfig()) == null) {
            return;
        }
        BlurConfig blurConfig2 = viewBlurProxy.getBlurConfig();
        boolean z = (blurConfig.getLeftTopCornerRadius() == blurConfig2.getLeftTopCornerRadius() && blurConfig.getRightTopCornerRadius() == blurConfig2.getRightTopCornerRadius() && blurConfig.getLeftBottomCornerRadius() == blurConfig2.getLeftBottomCornerRadius() && blurConfig.getRightBottomCornerRadius() == blurConfig2.getRightBottomCornerRadius()) ? false : true;
        if (z) {
            blurConfig.setLeftTopCornerRadius(blurConfig2.getLeftTopCornerRadius());
            blurConfig.setRightTopCornerRadius(blurConfig2.getRightTopCornerRadius());
            blurConfig.setLeftBottomCornerRadius(blurConfig2.getLeftBottomCornerRadius());
            blurConfig.setRightBottomCornerRadius(blurConfig2.getRightBottomCornerRadius());
        }
        ViewBlurProxy viewBlurProxy4 = this.currentBlurProxy;
        if (Intrinsics.areEqual(viewBlurProxy4 != null ? viewBlurProxy4.getBlurType() : null, viewBlurProxy.getBlurType())) {
            if (!z || (viewBlurProxy2 = this.currentBlurProxy) == null) {
                return;
            }
            viewBlurProxy2.applyBlurConfig();
            return;
        }
        ViewBlurProxy viewBlurProxy5 = this.currentBlurProxy;
        if (viewBlurProxy5 != null) {
            viewBlurProxy5.setBlurType(viewBlurProxy.getBlurType());
        }
    }

    public final Drawable getAodDrawable() {
        return this.aodDrawable;
    }

    public final Function0 getAuthorViewBlurProxyGetter() {
        return this.authorViewBlurProxyGetter;
    }

    public final Drawable getBlurDrawable() {
        if (!ScrimUtil.isHighGaussianLevel(getContext()) || FeatureOption.getDisableKeyguardBlurStacking()) {
            ViewBlurProxy viewBlurProxy = this.currentBlurProxy;
            if (viewBlurProxy != null) {
                return viewBlurProxy.getBlurDrawable(null);
            }
        } else {
            ViewBlurProxy viewBlurProxy2 = this.currentBlurProxy;
            if (viewBlurProxy2 != null) {
                return viewBlurProxy2.getStackBlurDrawable(2, null);
            }
        }
        return null;
    }

    public final ViewBlurProxy getCurrentBlurProxy() {
        return this.currentBlurProxy;
    }

    public final Function1 getDrawBoundsGetter() {
        return this.drawBoundsGetter;
    }

    public final boolean getEnableBlur() {
        return this.enableBlur;
    }

    public final Function0 getMaskColorGetter() {
        return this.maskColorGetter;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Drawable blurDrawable;
        Drawable drawable;
        Drawable drawable2;
        ViewBlurProxy viewBlurProxy;
        Drawable stackBlurDrawable;
        ViewBlurProxy viewBlurProxy2;
        this.drawBoundsGetter.invoke(this.bounds);
        Function0 function0 = this.authorViewBlurProxyGetter;
        if (function0 != null && (viewBlurProxy2 = (ViewBlurProxy) function0.invoke()) != null) {
            cloneBlurConfig(viewBlurProxy2);
        }
        if (ScrimUtil.isHighGaussianLevel(getContext()) && !FeatureOption.getDisableKeyguardBlurStacking() && (viewBlurProxy = this.currentBlurProxy) != null && (stackBlurDrawable = viewBlurProxy.getStackBlurDrawable(2, null)) != null) {
            Rect rect = this.bounds;
            rect.inset(-1, -1);
            stackBlurDrawable.setBounds(rect);
            stackBlurDrawable.draw(canvas);
            return;
        }
        ViewBlurProxy viewBlurProxy3 = this.currentBlurProxy;
        ViewBlurProxy.BlurType blurType = viewBlurProxy3 != null ? viewBlurProxy3.getBlurType() : null;
        ViewBlurProxy.BlurType.BlurTypePlatformStatic blurTypePlatformStatic = ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
        if (!Intrinsics.areEqual(blurType, blurTypePlatformStatic) && (drawable2 = this.aodDrawable) != null && (drawable2 == null || drawable2.getAlpha() != 0)) {
            this.bounds.inset(1, 1);
        }
        if (ScrimUtil.isBPAnimLevel()) {
            Drawable materialColorDrawable = getMaterialColorDrawable();
            if (materialColorDrawable != null) {
                materialColorDrawable.setTint(getOverlayBackgroundColor());
            }
            Drawable materialColorDrawable2 = getMaterialColorDrawable();
            if (materialColorDrawable2 != null) {
                materialColorDrawable2.setBounds(this.bounds);
            }
            Drawable materialColorDrawable3 = getMaterialColorDrawable();
            if (materialColorDrawable3 != null) {
                materialColorDrawable3.draw(canvas);
            }
        } else {
            ViewBlurProxy viewBlurProxy4 = this.currentBlurProxy;
            if (viewBlurProxy4 == null || (blurDrawable = viewBlurProxy4.getBlurDrawable(null)) == null) {
                blurDrawable = this.defaultDrawable;
            }
            if (blurDrawable != null) {
                Drawable drawable3 = this.defaultDrawable;
                if (drawable3 != null) {
                    drawable3.setTint(getOverlayBackgroundColor());
                }
                blurDrawable.setBounds(this.bounds);
                blurDrawable.draw(canvas);
            }
        }
        int iIntValue = ((Number) this.maskColorGetter.invoke()).intValue();
        if (iIntValue != 0) {
            Drawable drawable4 = this.defaultDrawable;
            if (drawable4 != null) {
                drawable4.setBounds(this.bounds);
            }
            Drawable drawable5 = this.defaultDrawable;
            if (drawable5 != null) {
                drawable5.setTint(iIntValue);
            }
            Drawable drawable6 = this.defaultDrawable;
            if (drawable6 != null) {
                drawable6.draw(canvas);
            }
        }
        ViewBlurProxy viewBlurProxy5 = this.currentBlurProxy;
        if (Intrinsics.areEqual(viewBlurProxy5 != null ? viewBlurProxy5.getBlurType() : null, blurTypePlatformStatic) || (drawable = this.aodDrawable) == null) {
            return;
        }
        Rect rect2 = this.bounds;
        if (drawable != null) {
            rect2.inset(-1, -1);
            drawable.setBounds(rect2);
            drawable.draw(canvas);
        }
    }

    public final void preApplyBlurConfig() {
        ViewBlurProxy viewBlurProxy;
        Function0 function0 = this.authorViewBlurProxyGetter;
        if (function0 == null || (viewBlurProxy = (ViewBlurProxy) function0.invoke()) == null) {
            return;
        }
        cloneBlurConfig(viewBlurProxy);
    }

    public final void setAodDrawable(Drawable drawable) {
        this.aodDrawable = drawable;
    }

    public final void setAuthorViewBlurProxyGetter(Function0 function0) {
        this.authorViewBlurProxyGetter = function0;
    }

    public final void setDrawBoundsGetter(Function1 function1) {
        this.drawBoundsGetter = function1;
    }

    public final void setEnableBlur(boolean z) {
        MultiPlatformBlurHelper multiPlatformBlurHelper;
        if (this.enableBlur == z) {
            return;
        }
        this.enableBlur = z;
        if (z) {
            ViewBlurManager viewBlurManager = (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
            ViewBlurProxy viewBlurProxyRequireBlurProxyForView = viewBlurManager != null ? viewBlurManager.requireBlurProxyForView(this, ViewBlurManager.CardType.NOTIFICATION) : null;
            this.currentBlurProxy = viewBlurProxyRequireBlurProxyForView;
            if (viewBlurProxyRequireBlurProxyForView != null && (multiPlatformBlurHelper = viewBlurProxyRequireBlurProxyForView.getMultiPlatformBlurHelper()) != null) {
                multiPlatformBlurHelper.setBlurEnabled(ScrimUtil.isHighGaussianLevel(getContext()));
            }
        } else {
            ViewBlurManager viewBlurManager2 = (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
            if (viewBlurManager2 != null) {
                viewBlurManager2.releaseBlurProxyForView(this, true, "NotificationOverlayBackgroundView setEnableBlur false");
            }
            ViewBlurProxy viewBlurProxy = this.currentBlurProxy;
            if (viewBlurProxy != null) {
                viewBlurProxy.setBlurEnabled(false);
            }
            this.currentBlurProxy = null;
        }
        invalidate();
    }

    public final void setMaskColorGetter(Function0 function0) {
        this.maskColorGetter = function0;
    }

    @Override // android.view.View
    public String toString() {
        return super.toString() + ", enableBlur=" + this.enableBlur + ", currentBlurProxy=" + this.currentBlurProxy;
    }

    public final void updateResource() {
        this.defaultDrawable = SmoothRoundExKt.getSmoothDrawable(getContext(), 2131102886);
        this.normalColor = getContext().getColor(2131102886);
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        if (super.verifyDrawable(drawable)) {
            return true;
        }
        ViewBlurProxy viewBlurProxy = this.currentBlurProxy;
        return Intrinsics.areEqual(drawable, viewBlurProxy != null ? viewBlurProxy.getBlurDrawable(null) : null) || Intrinsics.areEqual(drawable, this.defaultDrawable);
    }

    public NotificationOverlayBackgroundView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public NotificationOverlayBackgroundView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public /* synthetic */ NotificationOverlayBackgroundView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public NotificationOverlayBackgroundView(Context context) {
        this(context, null, 0, 0, 14, null);
    }
}
