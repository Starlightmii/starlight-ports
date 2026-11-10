package com.oplus.systemui.scrim;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.SeparateModeHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationBarDrawable extends Drawable implements ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationBarDrawable";
    public boolean brightnessMirrorVisible;
    private final Lazy centralSurfaces$delegate;
    public final Context context;
    private final NotificationBarDrawable$elementShowStateChangeListener$1 elementShowStateChangeListener;
    public final int handleHeight;
    public final int handleMarginTop;
    public final int handleRadius;
    public final int handleWidth;
    private final Lazy paint$delegate;
    private final Lazy panelAnimationEx$delegate;
    public boolean qsDialogVisible;
    public boolean qsFullyExpanded;
    private final Lazy rect$delegate;
    private final ScrimView scrimView;
    public boolean splitShadeEnabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationBarDrawable(ScrimView scrimView) {
        this.scrimView = scrimView;
        Context context = scrimView.getContext();
        this.context = context;
        this.handleWidth = context.getResources().getDimensionPixelSize(2131170772);
        this.handleHeight = context.getResources().getDimensionPixelSize(2131170769);
        this.handleMarginTop = context.getResources().getDimensionPixelSize(2131170770);
        this.handleRadius = context.getResources().getDimensionPixelSize(2131170771);
        NotificationBarDrawable$$ExternalSyntheticLambda0 notificationBarDrawable$$ExternalSyntheticLambda0 = new NotificationBarDrawable$$ExternalSyntheticLambda0();
        notificationBarDrawable$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.paint$delegate = LazyKt__LazyJVMKt.lazy(notificationBarDrawable$$ExternalSyntheticLambda0);
        final int i = 0;
        this.rect$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.scrim.NotificationBarDrawable$$ExternalSyntheticLambda1
            public final Object invoke() {
                switch (i) {
                    case 0:
                        NotificationBarDrawable.Companion companion = NotificationBarDrawable.Companion;
                        return new Rect();
                    case 1:
                        NotificationBarDrawable.Companion companion2 = NotificationBarDrawable.Companion;
                        return (CentralSurfacesImpl) DependencyEx.sDependency.getDependency(CentralSurfacesImpl.class);
                    default:
                        NotificationBarDrawable.Companion companion3 = NotificationBarDrawable.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                }
            }
        });
        final int i2 = 1;
        this.centralSurfaces$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.scrim.NotificationBarDrawable$$ExternalSyntheticLambda1
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        NotificationBarDrawable.Companion companion = NotificationBarDrawable.Companion;
                        return new Rect();
                    case 1:
                        NotificationBarDrawable.Companion companion2 = NotificationBarDrawable.Companion;
                        return (CentralSurfacesImpl) DependencyEx.sDependency.getDependency(CentralSurfacesImpl.class);
                    default:
                        NotificationBarDrawable.Companion companion3 = NotificationBarDrawable.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                }
            }
        });
        final int i3 = 2;
        this.panelAnimationEx$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.scrim.NotificationBarDrawable$$ExternalSyntheticLambda1
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        NotificationBarDrawable.Companion companion = NotificationBarDrawable.Companion;
                        return new Rect();
                    case 1:
                        NotificationBarDrawable.Companion companion2 = NotificationBarDrawable.Companion;
                        return (CentralSurfacesImpl) DependencyEx.sDependency.getDependency(CentralSurfacesImpl.class);
                    default:
                        NotificationBarDrawable.Companion companion3 = NotificationBarDrawable.Companion;
                        return (PanelAnimationEx) DependencyEx.sDependency.getDependency(PanelAnimationEx.class);
                }
            }
        });
        NotificationBarDrawable$elementShowStateChangeListener$1 notificationBarDrawable$elementShowStateChangeListener$1 = new NotificationBarDrawable$elementShowStateChangeListener$1();
        notificationBarDrawable$elementShowStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.elementShowStateChangeListener = notificationBarDrawable$elementShowStateChangeListener$1;
        ((ConfigurationController) Dependency.sDependency.getDependencyInner(ConfigurationController.class)).addCallback(this);
        getPanelAnimationEx().getElementShowState().addCallback(notificationBarDrawable$elementShowStateChangeListener$1);
    }

    private final CentralSurfacesImpl getCentralSurfaces() {
        return (CentralSurfacesImpl) this.centralSurfaces$delegate.getValue();
    }

    private final Paint getPaint() {
        return (Paint) this.paint$delegate.getValue();
    }

    private final PanelAnimationEx getPanelAnimationEx() {
        return (PanelAnimationEx) this.panelAnimationEx$delegate.getValue();
    }

    private final Rect getRect() {
        return (Rect) this.rect$delegate.getValue();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect drawableBounds;
        boolean zIsOnLockscreenNtfCenter = ((OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class)).isOnLockscreenNtfCenter();
        if (SeparateModeHelper.INSTANCE.getSeparateMode() || zIsOnLockscreenNtfCenter || this.splitShadeEnabled) {
            return;
        }
        if (this.scrimView.getViewAlpha() <= 0.0f || getCentralSurfaces().mState == 1 || this.brightnessMirrorVisible || this.qsFullyExpanded || this.qsDialogVisible || (drawableBounds = this.scrimView.getDrawableBounds()) == null) {
            return;
        }
        int left = this.scrimView.getLeft();
        int right = this.scrimView.getRight();
        int i = drawableBounds.top;
        int i2 = drawableBounds.bottom;
        int i3 = (((right - left) / 2) - (this.handleWidth / 2)) + left;
        getRect().left = i3;
        getRect().top = this.handleMarginTop + i;
        getRect().right = this.handleWidth + i3;
        getRect().bottom = i2;
        PanelAnimationEx panelAnimationEx = getPanelAnimationEx();
        getPaint().setAlpha((int) (51.0f * ((!panelAnimationEx.getUseLayeredAnimation() || panelAnimationEx.isElementShowing()) ? 1.0f : 0.0f)));
        if (getPaint().getAlpha() != 0) {
            int i4 = i + this.handleMarginTop;
            float f = i3 + this.handleWidth;
            float f2 = i4 + this.handleHeight;
            float f3 = this.handleRadius;
            canvas.drawRoundRect(i3, i4, f, f2, f3, f3, getPaint());
        }
    }

    public final boolean getBrightnessMirrorVisible() {
        return this.brightnessMirrorVisible;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final boolean getQsDialogVisible() {
        return this.qsDialogVisible;
    }

    public final boolean getQsFullyExpanded() {
        return this.qsFullyExpanded;
    }

    public final boolean getSplitShadeEnabled() {
        return this.splitShadeEnabled;
    }

    public void onConfigChanged(Configuration configuration) {
        getPaint().setColor(this.context.getColor(2131102932));
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    public final void setBrightnessMirrorVisible(boolean z) {
        if (this.brightnessMirrorVisible != z) {
            this.brightnessMirrorVisible = z;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[setBrightnessMirrorVisible] ", "NotificationBarDrawable-->", "SystemUi--Notification", this.brightnessMirrorVisible);
            }
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public final void setQsDialogVisible(boolean z) {
        if (this.qsDialogVisible != z) {
            this.qsDialogVisible = z;
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[setQsDialogVisible] ", z), null, 8);
            invalidateSelf();
        }
    }

    public final void setQsFullyExpanded(boolean z) {
        if (this.qsFullyExpanded != z) {
            this.qsFullyExpanded = z;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[setQsFullyExpanded] ", "NotificationBarDrawable-->", "SystemUi--Notification", this.qsFullyExpanded);
            }
            invalidateSelf();
        }
    }

    public final void setSplitShadeEnabled(boolean z) {
        if (this.splitShadeEnabled != z) {
            this.splitShadeEnabled = z;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[setSplitShadeEnabled] ", "NotificationBarDrawable-->", "SystemUi--Notification", this.splitShadeEnabled);
            }
            invalidateSelf();
        }
    }
}
