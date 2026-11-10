package com.oplus.systemui.notification.stackednotification.transformer;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Configuration;
import android.util.MathUtils;
import android.view.View;
import android.view.animation.PathInterpolator;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerExt;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationStackedProgressTransformer {
    private final Context context;
    public int fullStackedNotificationMuskColor;
    public int halfStackedNotificationMuskColor;
    private final StackedProgressSpringAnimator progressSpringAnimator;
    public static final Companion Companion = new Companion();
    private static final ArgbEvaluator ARGB_EVALUATOR = new ArgbEvaluator();
    private static final PathInterpolator INTERPOLATOR_HALF_MUSK_COLOR = new PathInterpolator(0.0f, 1.0f, 0.5f, 1.0f);

    /* JADX INFO: renamed from: com.oplus.systemui.notification.stackednotification.transformer.NotificationStackedProgressTransformer$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ NotificationStackedProgressTransformer this$0;

        public final void onConfigChanged(Configuration configuration) {
            NotificationStackedProgressTransformer notificationStackedProgressTransformer = this.this$0;
            Companion companion = NotificationStackedProgressTransformer.Companion;
            notificationStackedProgressTransformer.updateStackedMuskColor();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$aF4_Rt5w2ZjzIIxTFXam0xtTtw4(NotificationStackedProgressTransformer notificationStackedProgressTransformer, View view, float f) {
        float fConstrainedMap;
        int i;
        int i2;
        NotificationBackgroundView mBackgroundNormal;
        NotificationBackgroundViewExt ext;
        NotificationChildrenContainerExt ext2;
        NotificationBackgroundViewExt ext3;
        ExpandableNotificationRow expandableNotificationRow = (ExpandableView) view;
        if (getCurrProgress(expandableNotificationRow) != f) {
            expandableNotificationRow.setTag(2131365337, Float.valueOf(f));
            if (f <= 0.5f) {
                fConstrainedMap = INTERPOLATOR_HALF_MUSK_COLOR.getInterpolation(MathUtils.constrainedMap(0.0f, 1.0f, 0.0f, 0.5f, f));
                i2 = notificationStackedProgressTransformer.halfStackedNotificationMuskColor;
                i = 0;
            } else {
                fConstrainedMap = MathUtils.constrainedMap(0.0f, 1.0f, 0.5f, 1.0f, f);
                i = notificationStackedProgressTransformer.halfStackedNotificationMuskColor;
                i2 = notificationStackedProgressTransformer.fullStackedNotificationMuskColor;
            }
            int iIntValue = ((Integer) ARGB_EVALUATOR.evaluate(fConstrainedMap, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
            if (expandableNotificationRow instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                NotificationBackgroundView notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal;
                if (notificationBackgroundView != null && (ext3 = notificationBackgroundView.getExt()) != null) {
                    ext3.setStackedNotificationMaskColor(iIntValue);
                }
                NotificationChildrenContainer childrenContainer = expandableNotificationRow2.getChildrenContainer();
                if (childrenContainer != null && (ext2 = childrenContainer.getExt()) != null) {
                    ext2.setStackedNotificationMaskColor(iIntValue);
                }
            } else if ((expandableNotificationRow instanceof OplusCustomRow) && (mBackgroundNormal = ((OplusCustomRow) expandableNotificationRow).getMBackgroundNormal()) != null && (ext = mBackgroundNormal.getExt()) != null) {
                ext.setStackedNotificationMaskColor(iIntValue);
            }
        }
        return Unit.INSTANCE;
    }

    public NotificationStackedProgressTransformer(Context context, ConfigurationController configurationController) {
        this.context = context;
        this.fullStackedNotificationMuskColor = context.getResources().getColor(2131103660, context.getTheme());
        this.halfStackedNotificationMuskColor = context.getResources().getColor(2131103661, context.getTheme());
        NotificationStackedProgressTransformer$$ExternalSyntheticLambda0 notificationStackedProgressTransformer$$ExternalSyntheticLambda0 = new NotificationStackedProgressTransformer$$ExternalSyntheticLambda0();
        notificationStackedProgressTransformer$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.progressSpringAnimator = new StackedProgressSpringAnimator(2131365340, 0.1f, 0.3f, 0.0f, notificationStackedProgressTransformer$$ExternalSyntheticLambda0);
        updateStackedMuskColor();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        configurationController.addCallback(anonymousClass1);
    }

    public static float getCurrProgress(ExpandableView expandableView) {
        Object tag = expandableView.getTag(2131365337);
        Float f = tag instanceof Float ? (Float) tag : null;
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    public final void animationTo(ExpandableView expandableView, float f, AnimationProperties animationProperties) {
        NotificationBackgroundView notificationBackgroundView;
        NotificationBackgroundViewExt ext;
        this.progressSpringAnimator.animateTo(expandableView, getCurrProgress(expandableView), f, animationProperties);
        if (!(expandableView instanceof ExpandableNotificationRow) || (notificationBackgroundView = ((ActivatableNotificationView) ((ExpandableNotificationRow) expandableView)).mBackgroundNormal) == null || (ext = notificationBackgroundView.getExt()) == null || ext.getStackedProgress() == f) {
            return;
        }
        ext.setStackedProgress(f);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0019  */
    public final void apply(ExpandableView expandableView, float f) {
        boolean z;
        NotificationBackgroundView notificationBackgroundView;
        NotificationBackgroundViewExt ext;
        boolean z2 = true;
        if (expandableView instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow notificationParent = ((ExpandableNotificationRow) expandableView).getNotificationParent();
            if (notificationParent != null ? notificationParent.isExpanded(false) : false) {
                z = false;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        float currProgress = getCurrProgress(expandableView);
        if (currProgress != f || this.progressSpringAnimator.isAnimating(expandableView)) {
            boolean z3 = currProgress > 0.0f && currProgress < 1.0f;
            if (expandableView.getVisibility() == 0 && ((!expandableView.isChildInGroup() || !z) && Math.abs(currProgress - f) >= 0.1f && z3)) {
                z2 = false;
            }
            NotificationFeatureOption.INSTANCE.getClass();
            if (NotificationFeatureOption.isNotificationLightOs || (z2 && !this.progressSpringAnimator.isAnimating(expandableView))) {
                this.progressSpringAnimator.cancelAndSet(expandableView, f);
            } else {
                animationTo(expandableView, f, null);
            }
            if (!(expandableView instanceof ExpandableNotificationRow) || (notificationBackgroundView = ((ActivatableNotificationView) ((ExpandableNotificationRow) expandableView)).mBackgroundNormal) == null || (ext = notificationBackgroundView.getExt()) == null || ext.getStackedProgress() == f) {
                return;
            }
            ext.setStackedProgress(f);
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x005f  */
    /* JADX WARN: Code duplicated, block: B:7:0x0025  */
    public final void updateStackedMuskColor() {
        int color;
        int color2;
        if (ScrimUtil.isLowGaussianLevel(this.context)) {
            ScrimControllerEx.Companion.getClass();
            if (ScrimControllerEx.isThirdTheme) {
                color = this.context.getResources().getColor(2131103660, this.context.getTheme());
            } else {
                color = this.context.getResources().getColor(2131101859, this.context.getTheme());
            }
        } else {
            color = this.context.getResources().getColor(2131103660, this.context.getTheme());
        }
        this.fullStackedNotificationMuskColor = color;
        if (ScrimUtil.isLowGaussianLevel(this.context)) {
            ScrimControllerEx.Companion.getClass();
            if (ScrimControllerEx.isThirdTheme) {
                color2 = this.context.getResources().getColor(2131103661, this.context.getTheme());
            } else {
                color2 = this.context.getResources().getColor(2131101860, this.context.getTheme());
            }
        } else {
            color2 = this.context.getResources().getColor(2131103661, this.context.getTheme());
        }
        this.halfStackedNotificationMuskColor = color2;
    }
}
