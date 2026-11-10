package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import com.android.systemui.statusbar.phone.ScrimStateEx;
import com.oplus.keyguard.decorator.KeyguardBouncerScrimDecorator;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScrimStateExImp extends ScrimStateEx {
    public static final Companion Companion = new Companion();
    private final Context context;
    public final boolean isOplusLogic;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ScrimStateExImp(Context context) {
        super(context);
        this.context = context;
        this.isOplusLogic = true;
    }

    public final int adjustBouncerBehindTint(int i) {
        return KeyguardBouncerScrimDecorator.Companion.getBouncerScrimTintLegacy();
    }

    public final float adjustBouncerNotifAlpha(float f) {
        return 0.0f;
    }

    public final float adjustBouncerScrimmedFrontAlpha(float f) {
        return KeyguardBouncerScrimDecorator.Companion.getBouncerScrimAlphaLegacy$default(KeyguardBouncerScrimDecorator.Companion);
    }

    public final int adjustBouncerScrimmedFrontTint(int i) {
        return KeyguardBouncerScrimDecorator.Companion.getBouncerScrimTintLegacy();
    }

    public float getScrimAlpha() {
        if (GaussBlurUtils.isGaussBlurDisabled()) {
            return 1.0f;
        }
        NotificationFeatureOption.INSTANCE.getClass();
        if (NotificationFeatureOption.isPanViewBlurDisabled || ScrimUtil.isLowGaussianLevel(this.context)) {
            return 1.0f;
        }
        if (OplusDarkModeUtil.isNightMode(this.context)) {
            return 0.5f;
        }
        return QsColorUtil.isGlobalThemeApplied(this.context) ? 0.8f : 0.15f;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0017 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:9:0x0019 A[RETURN] */
    public final float getScrimAlphaWhenLeaveOpenOnKeyguard(boolean z) {
        if (!GaussBlurUtils.isGaussBlurDisabled()) {
            NotificationFeatureOption.INSTANCE.getClass();
            if (NotificationFeatureOption.isPanViewBlurDisabled || ScrimUtil.isLowGaussianLevel(this.context)) {
                if (!z) {
                    return 0.15f;
                }
            }
        } else if (!z) {
            return 0.15f;
        }
        return getScrimAlpha();
    }

    public long getScrimAnimDurationWhileSmoothTransition() {
        return SmoothTransitionController.Companion.getInstance(this.context).getScrimAnimDurationWhileSmoothTransition();
    }

    public final boolean isOplusLogic() {
        return this.isOplusLogic;
    }

    public final boolean shouldShowKGClockWhileEnterAod() {
        SmoothTransitionController.Companion companion = SmoothTransitionController.Companion;
        return companion.getInstance(this.context).isWorkShopAod() || companion.getInstance(this.context).isPanoramicAod();
    }
}
