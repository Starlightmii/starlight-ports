package com.oplus.systemui.statusbar.notification.animation;

import android.animation.ValueAnimator;
import android.util.MathUtils;
import android.view.View;
import android.widget.COUISpringInterpolator;
import com.android.systemui.DependencyEx;
import com.android.systemui.notification.stackednotification.StackedNotificationEx;
import com.android.systemui.statusbar.notification.animation.AlphaState;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.RowAppearAnimationEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateExKt;
import com.oplus.systemui.notification.extend.OplusNotificationEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RowAppearAnimationExImpl implements RowAppearAnimationEx, OplusNotificationEx {
    public static final Companion Companion = new Companion();
    private static final COUISpringInterpolator INTERPOLATOR = new COUISpringInterpolator(600.0d, 1.0d, 0.0d, 1.0f, 6666.0f);
    private static final String TAG = "RowAppearAnimationExImpl";
    private static final String TAG_FOR_APPEAR_ANIMATION = "tag_for_appear_animation";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public void doAppearAnimation(boolean z, boolean z2, float f, ExpandableView expandableView) {
        RowAnimationStateEx rowAnimationStateEx;
        if (z2 || expandableView == null || (rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableView)) == null) {
            return;
        }
        if (((StackedNotificationEx) DependencyEx.sDependency.getDependency(StackedNotificationEx.class)).isKgNotificationSummary(expandableView, true)) {
            resetAppearAnimation(expandableView);
        } else {
            COUISpringInterpolator cOUISpringInterpolator = INTERPOLATOR;
            ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowScale(TAG_FOR_APPEAR_ANIMATION, ScaleState.Companion.invoke(MathUtils.lerp(0.8f, 1.0f, !z ? 1.0f - cOUISpringInterpolator.getInterpolation(1.0f - f) : cOUISpringInterpolator.getInterpolation(f))));
        }
        COUISpringInterpolator cOUISpringInterpolator2 = INTERPOLATOR;
        ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowAlpha(TAG_FOR_APPEAR_ANIMATION, new AlphaState(!z ? 1.0f - cOUISpringInterpolator2.getInterpolation(1.0f - f) : cOUISpringInterpolator2.getInterpolation(f)));
        expandableView.applyViewState();
    }

    public void doAppearBoost(ActivatableNotificationView activatableNotificationView, ValueAnimator valueAnimator, boolean z, boolean z2) {
        if (z2 && activatableNotificationView != null && (activatableNotificationView instanceof ExpandableNotificationRow)) {
            ((ExpandableNotificationRow) activatableNotificationView).mRowEx.boostFrameRate(valueAnimator);
        }
    }

    public void resetAppearAnimation(ExpandableView expandableView) {
        RowAnimationStateEx rowAnimationStateEx;
        RowAnimationStateEx rowAnimationStateEx2;
        if (expandableView != null && (rowAnimationStateEx2 = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableView)) != null) {
            ((RowAnimationStateExImpl) rowAnimationStateEx2).requestRowScale(TAG_FOR_APPEAR_ANIMATION, ScaleState.Companion.getINVALID());
        }
        if (expandableView == null || (rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableView)) == null) {
            return;
        }
        ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowAlpha(TAG_FOR_APPEAR_ANIMATION, AlphaState.Companion.getINVALID());
    }

    public void tryOverlayInterpolatorForAppearAnimation(boolean z, AnimationProperties animationProperties) {
        if (z) {
            animationProperties.setCustomInterpolator(View.TRANSLATION_Y, INTERPOLATOR);
        } else {
            animationProperties.removeCustomInterpolator(View.TRANSLATION_Y);
        }
    }
}
