package com.oplus.systemui.statusbar.notification.stack;

import android.util.FloatProperty;
import android.view.View;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.notification.PropertyAnimator;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.systemui.notification.NotificationExtFlag;
import com.oplus.systemui.notification.base.RowAnimatorExtImplKt;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleViewState;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.OplusPhysicsPropertyAnimatorExtKt;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplus.systemui.notification.stackednotification.transformer.NotificationStackedProgressTransformer;
import com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusExpandableViewStateExImpl extends OplusViewStateExImpl implements OplusExpandableViewStateEx, OplusNotificationEx {
    private static final String TAG = "OplusExpandableViewStat";
    public float animationCornerRadius;
    public float blurRadio;
    public float capsuleProgress;
    public float groupExpandHeaderAlpha;
    public float groupExpansionProgress;
    public float immersiveProgress;
    public int notGoneIndexWithOutChildren;
    public float notificationStackedProgress;
    private final NotificationStackedProgressTransformer stackedCoverProgressTransformer;
    public static final Companion Companion = new Companion();
    public static final boolean CLIP_USE_SPRING = NotificationExtFlag.USE_OPLUS_PHYSICAL;
    private static final AnimatableProperty CLIP_WIDTH_PROPERTY = AnimatableProperty.from("clipWidth", OplusExpandableViewStateExImpl$Companion$CLIP_WIDTH_PROPERTY$1.INSTANCE, OplusExpandableViewStateExImpl$Companion$CLIP_WIDTH_PROPERTY$2.INSTANCE, 2131362558, 2131362557, 2131362556);
    private static final AnimatableProperty BLUR_RADIO_PROPERTY = AnimatableProperty.from("blurRadio", OplusExpandableViewStateExImpl$Companion$BLUR_RADIO_PROPERTY$1.INSTANCE, OplusExpandableViewStateExImpl$Companion$BLUR_RADIO_PROPERTY$2.INSTANCE, 2131362291, 2131362290, 2131362289);
    private static final AnimatableProperty ANIMATION_CORNER_RADIUS_PROPERTY = AnimatableProperty.from("animationCornerRadius", OplusExpandableViewStateExImpl$Companion$ANIMATION_CORNER_RADIUS_PROPERTY$1.INSTANCE, OplusExpandableViewStateExImpl$Companion$ANIMATION_CORNER_RADIUS_PROPERTY$2.INSTANCE, 2131362059, 2131362058, 2131362057);
    private static final AnimatableProperty ANIMATION_CAPSULE_PROGRESS_PROPERTY = AnimatableProperty.from("capsuleProgress", OplusExpandableViewStateExImpl$Companion$ANIMATION_CAPSULE_PROGRESS_PROPERTY$1.INSTANCE, OplusExpandableViewStateExImpl$Companion$ANIMATION_CAPSULE_PROGRESS_PROPERTY$2.INSTANCE, 2131362056, 2131362055, 2131362054);
    private static final AnimatableProperty ANIMATION_IMMERSIVE_PROGRESS_PROPERTY = AnimatableProperty.from("immersiveProgress", OplusExpandableViewStateExImpl$Companion$ANIMATION_IMMERSIVE_PROGRESS_PROPERTY$1.INSTANCE, OplusExpandableViewStateExImpl$Companion$ANIMATION_IMMERSIVE_PROGRESS_PROPERTY$2.INSTANCE, 2131362063, 2131362062, 2131362061);
    private static final AnimatableProperty GROUP_EXPANSION_PROGRESS_PROPERTY = AnimatableProperty.from("groupExpansionProgress", OplusExpandableViewStateExImpl$Companion$GROUP_EXPANSION_PROGRESS_PROPERTY$1.INSTANCE, OplusExpandableViewStateExImpl$Companion$GROUP_EXPANSION_PROGRESS_PROPERTY$2.INSTANCE, 2131364189, 2131364188, 2131364187);
    private static final AnimatableProperty GROUP_EXPAND_HEADER_ALPHA_PROPERTY = AnimatableProperty.from("groupOverlayAlpha", OplusExpandableViewStateExImpl$Companion$GROUP_EXPAND_HEADER_ALPHA_PROPERTY$1.INSTANCE, OplusExpandableViewStateExImpl$Companion$GROUP_EXPAND_HEADER_ALPHA_PROPERTY$2.INSTANCE, 2131364186, 2131364185, 2131364184);
    private static final AnimatableProperty GROUP_EXPAND_CONTENT_ALPHA_PROPERTY = AnimatableProperty.from("groupExpandContentAlpha", OplusExpandableViewStateExImpl$Companion$GROUP_EXPAND_CONTENT_ALPHA_PROPERTY$1.INSTANCE, OplusExpandableViewStateExImpl$Companion$GROUP_EXPAND_CONTENT_ALPHA_PROPERTY$2.INSTANCE, 2131364183, 2131364182, 2131364181);
    private final Lazy statusBarStateController$delegate = LazyKt__LazyJVMKt.lazy(new OplusExpandableViewStateExImpl$$ExternalSyntheticLambda0());
    private final RowAnimationStateEx rowAnimationStateEx = new RowAnimationStateExImpl();
    private MaterialColorMode materialColorMode = MaterialColorMode.UNINITIATED;
    private NotificationBlurMode notificationBlurMode = NotificationBlurMode.UNINITIATED;
    public float groupExpandContentAlpha = 1.0f;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final AnimatableProperty getANIMATION_CAPSULE_PROGRESS_PROPERTY() {
            return OplusExpandableViewStateExImpl.ANIMATION_CAPSULE_PROGRESS_PROPERTY;
        }

        public final AnimatableProperty getANIMATION_CORNER_RADIUS_PROPERTY() {
            return OplusExpandableViewStateExImpl.ANIMATION_CORNER_RADIUS_PROPERTY;
        }

        public final AnimatableProperty getANIMATION_IMMERSIVE_PROGRESS_PROPERTY() {
            return OplusExpandableViewStateExImpl.ANIMATION_IMMERSIVE_PROGRESS_PROPERTY;
        }

        public final AnimatableProperty getBLUR_RADIO_PROPERTY() {
            return OplusExpandableViewStateExImpl.BLUR_RADIO_PROPERTY;
        }

        public final boolean getCLIP_USE_SPRING() {
            return OplusExpandableViewStateExImpl.CLIP_USE_SPRING;
        }

        public final AnimatableProperty getCLIP_WIDTH_PROPERTY() {
            return OplusExpandableViewStateExImpl.CLIP_WIDTH_PROPERTY;
        }

        public final AnimatableProperty getGROUP_EXPAND_CONTENT_ALPHA_PROPERTY() {
            return OplusExpandableViewStateExImpl.GROUP_EXPAND_CONTENT_ALPHA_PROPERTY;
        }

        public final AnimatableProperty getGROUP_EXPAND_HEADER_ALPHA_PROPERTY() {
            return OplusExpandableViewStateExImpl.GROUP_EXPAND_HEADER_ALPHA_PROPERTY;
        }

        public final AnimatableProperty getGROUP_EXPANSION_PROGRESS_PROPERTY() {
            return OplusExpandableViewStateExImpl.GROUP_EXPANSION_PROGRESS_PROPERTY;
        }
    }

    public OplusExpandableViewStateExImpl(NotificationStackedProgressTransformer notificationStackedProgressTransformer) {
        this.stackedCoverProgressTransformer = notificationStackedProgressTransformer;
    }

    private final StatusBarStateController getStatusBarStateController() {
        return (StatusBarStateController) this.statusBarStateController$delegate.getValue();
    }

    public void animatePropertyIfChanged(ExpandableView expandableView, AnimationProperties animationProperties, Float f, float f2, AnimatableProperty animatableProperty, boolean z, float f3) {
        if (f == null) {
            f = (Float) ((FloatProperty) animatableProperty.getProperty()).get(expandableView);
        }
        if (Intrinsics.areEqual(f, f2)) {
            if (m463getBase() != null) {
                ViewState.abortAnimation(expandableView, animatableProperty.getAnimatorTag());
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("animatePropertyIfChanged: ", animatableProperty.getProperty().getName(), ", ", RowAnimatorExtImplKt.tagId(expandableView), ", currentValue=");
            sbM.append(f);
            sbM.append(", targetValue=");
            sbM.append(f2);
            sbM.append(", isSpring:");
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusExpandableViewStat-->", "SystemUi--Notification", sbM, z);
        }
        if (z) {
            OplusPhysicsPropertyAnimator.setProperty$default(expandableView, OplusExpandableViewStateExImplKt.toPhysicsProperty(animatableProperty, f3), f2, animationProperties, true, 32);
        } else {
            PropertyAnimator.startAnimation(expandableView, animatableProperty, f2, animationProperties);
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00ec  */
    public void beforeAnimateTo(View view, AnimationProperties animationProperties) {
        boolean z;
        ExpandableViewState expandableViewStateM463getBase;
        if (view != null) {
            view.setTransitionAlpha(RowAnimationStateEx.getRowAlpha$default(getRowAnimationStateEx()).getAlpha());
        }
        if (!(view instanceof CapsuleItemView)) {
            ScaleState rowScale$default = RowAnimationStateEx.getRowScale$default(getRowAnimationStateEx());
            ExpandableViewState expandableViewStateM463getBase2 = m463getBase();
            if (expandableViewStateM463getBase2 != null) {
                expandableViewStateM463getBase2.setScaleX(rowScale$default.getScaleX());
            }
            ExpandableViewState expandableViewStateM463getBase3 = m463getBase();
            if (expandableViewStateM463getBase3 != null) {
                expandableViewStateM463getBase3.setScaleY(rowScale$default.getScaleY());
            }
        }
        if ((view instanceof ExpandableNotificationRow) && (expandableViewStateM463getBase = m463getBase()) != null) {
            expandableViewStateM463getBase.setZTranslation(0.0f);
        }
        ExpandableView expandableView = view instanceof ExpandableView ? (ExpandableView) view : null;
        if (expandableView != null) {
            this.stackedCoverProgressTransformer.animationTo(expandableView, getNotificationStackedProgress(), animationProperties);
            ExpandableNotificationRow expandableNotificationRow = (ExpandableView) view;
            OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) expandableNotificationRow).mExpandableViewEx;
            oplusExpandableViewEx.setCurrentClipWidthSource(((RowAnimationStateExImpl) getRowAnimationStateEx()).getEffectiveClipWidthSource());
            if (oplusExpandableViewEx.getMaterialColorMode() != getMaterialColorMode()) {
                oplusExpandableViewEx.setMaterialColorMode(getMaterialColorMode());
            }
            if (oplusExpandableViewEx.getNotificationBlurMode() != getNotificationBlurMode()) {
                oplusExpandableViewEx.setNotificationBlurMode(getNotificationBlurMode());
            }
            Float fValueOf = Float.valueOf(oplusExpandableViewEx.getClipWidth());
            float clipWidth = getClipWidth();
            AnimatableProperty animatableProperty = CLIP_WIDTH_PROPERTY;
            boolean z2 = CLIP_USE_SPRING;
            OplusExpandableViewStateEx.animatePropertyIfChanged$default(this, expandableNotificationRow, animationProperties, fValueOf, clipWidth, animatableProperty, z2, 64);
            OplusExpandableViewStateEx.animatePropertyIfChanged$default(this, expandableNotificationRow, animationProperties, Float.valueOf(oplusExpandableViewEx.getBlurRadio()), getBlurRadio(), BLUR_RADIO_PROPERTY, true, 64);
            OplusExpandableViewStateEx.animatePropertyIfChanged$default(this, expandableNotificationRow, animationProperties, Float.valueOf(oplusExpandableViewEx.getAnimationCornerRadius()), getAnimationCornerRadius(), ANIMATION_CORNER_RADIUS_PROPERTY, true, 64);
            Float fValueOf2 = Float.valueOf(oplusExpandableViewEx.getCapsuleProgress());
            float capsuleProgress = getCapsuleProgress();
            AnimatableProperty animatableProperty2 = ANIMATION_CAPSULE_PROGRESS_PROPERTY;
            if (z2) {
                CapsuleConfig.INSTANCE.getClass();
                if (CapsuleConfig.isLightOs) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            boolean z3 = z;
            CapsuleConfig.INSTANCE.getClass();
            animatePropertyIfChanged(expandableNotificationRow, animationProperties, fValueOf2, capsuleProgress, animatableProperty2, z3, CapsuleConfig.isLightOs ? 0.00390625f : 0.1f);
            OplusExpandableViewStateEx.animatePropertyIfChanged$default(this, expandableNotificationRow, animationProperties, Float.valueOf(oplusExpandableViewEx.getImmersiveProgress()), getImmersiveProgress(), ANIMATION_IMMERSIVE_PROGRESS_PROPERTY, false, 96);
            if (expandableNotificationRow.isSummaryWithChildren() && (expandableNotificationRow instanceof ExpandableNotificationRow)) {
                OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx = expandableNotificationRow.mRowEx;
                OplusExpandableViewStateEx.animatePropertyIfChanged$default(this, expandableNotificationRow, animationProperties, Float.valueOf(oplusExpandableNotificationRowEx != null ? oplusExpandableNotificationRowEx.getGroupExpansionProgress() : 0.0f), getGroupExpansionProgress(), GROUP_EXPANSION_PROGRESS_PROPERTY, false, 96);
                OplusExpandableViewStateEx.animatePropertyIfChanged$default(this, expandableNotificationRow, animationProperties, Float.valueOf(oplusExpandableNotificationRowEx != null ? oplusExpandableNotificationRowEx.getGroupExpandHeaderAlpha() : 0.0f), getGroupExpandHeaderAlpha(), GROUP_EXPAND_HEADER_ALPHA_PROPERTY, false, 96);
            }
            if (expandableNotificationRow instanceof ExpandableNotificationRow) {
                OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx2 = expandableNotificationRow.mRowEx;
                OplusExpandableViewStateEx.animatePropertyIfChanged$default(this, expandableNotificationRow, animationProperties, Float.valueOf(oplusExpandableNotificationRowEx2 != null ? oplusExpandableNotificationRowEx2.getGroupExpandContentAlpha() : 0.0f), getGroupExpandContentAlpha(), GROUP_EXPAND_CONTENT_ALPHA_PROPERTY, true, 64);
            }
        }
    }

    public void beforeApplyToView(View view) {
        ExpandableViewState expandableViewStateM463getBase;
        ExpandableViewState expandableViewStateM463getBase2;
        ExpandableViewState expandableViewStateM463getBase3;
        boolean z = view instanceof ExpandableView;
        ExpandableView expandableView = z ? (ExpandableView) view : null;
        if (expandableView != null) {
            int clipWidth = getClipWidth();
            expandableView.mExpandableViewEx.setCurrentClipWidthSource(((RowAnimationStateExImpl) getRowAnimationStateEx()).getEffectiveClipWidthSource());
            if (expandableView.mExpandableViewEx.getClipWidth() != clipWidth) {
                if (CLIP_USE_SPRING) {
                    OplusPhysicsPropertyAnimator.setProperty$default(expandableView, OplusExpandableViewStateExImplKt.toPhysicsProperty(CLIP_WIDTH_PROPERTY, 0.1f), clipWidth, (AnimationProperties) null, false, 56);
                } else {
                    CLIP_WIDTH_PROPERTY.getProperty().set(expandableView, Float.valueOf(clipWidth));
                }
                expandableView.mExpandableViewEx.updateClipping();
                expandableView.invalidate();
            }
            if (expandableView.mExpandableViewEx.getBlurRadio() != getBlurRadio()) {
                expandableView.mExpandableViewEx.setBlurRadio(getBlurRadio());
            }
            if (expandableView.mExpandableViewEx.getAnimationCornerRadius() != getAnimationCornerRadius()) {
                expandableView.mExpandableViewEx.setAnimationCornerRadius(getAnimationCornerRadius());
            }
            if (expandableView.mExpandableViewEx.getCapsuleProgress() != getCapsuleProgress()) {
                expandableView.mExpandableViewEx.setCapsuleProgress(getCapsuleProgress());
            }
            if (expandableView.mExpandableViewEx.getImmersiveProgress() != getImmersiveProgress()) {
                expandableView.mExpandableViewEx.setImmersiveProgress(getImmersiveProgress());
            }
            if (expandableView.isSummaryWithChildren()) {
                if (expandableView.mExpandableViewEx.getGroupExpansionProgress() != getGroupExpansionProgress()) {
                    expandableView.mExpandableViewEx.setGroupExpansionProgress(getGroupExpansionProgress());
                }
                if (expandableView.mExpandableViewEx.getGroupExpandHeaderAlpha() != getGroupExpandHeaderAlpha()) {
                    expandableView.mExpandableViewEx.setGroupExpandHeaderAlpha(getGroupExpandHeaderAlpha());
                }
            }
            if ((expandableView instanceof ExpandableNotificationRow) && expandableView.mExpandableViewEx.getGroupExpandContentAlpha() != getGroupExpandContentAlpha()) {
                expandableView.mExpandableViewEx.setGroupExpandContentAlpha(getGroupExpandContentAlpha());
            }
            if (expandableView.mExpandableViewEx.getMaterialColorMode() != getMaterialColorMode()) {
                expandableView.mExpandableViewEx.setMaterialColorMode(getMaterialColorMode());
            }
            if (expandableView.mExpandableViewEx.getNotificationBlurMode() != getNotificationBlurMode()) {
                expandableView.mExpandableViewEx.setNotificationBlurMode(getNotificationBlurMode());
            }
        }
        if (view instanceof CapsuleItemView) {
            return;
        }
        float alpha = RowAnimationStateEx.getRowAlpha$default(getRowAnimationStateEx()).getAlpha();
        if (!Intrinsics.areEqual(view != null ? Float.valueOf(view.getTransitionAlpha()) : null, alpha) && view != null) {
            view.setTransitionAlpha(alpha);
        }
        ScaleState rowScale$default = RowAnimationStateEx.getRowScale$default(getRowAnimationStateEx());
        ExpandableViewState expandableViewStateM463getBase4 = m463getBase();
        if (!Intrinsics.areEqual(expandableViewStateM463getBase4 != null ? Float.valueOf(expandableViewStateM463getBase4.getScaleX()) : null, rowScale$default.getScaleX()) && (expandableViewStateM463getBase3 = m463getBase()) != null) {
            expandableViewStateM463getBase3.setScaleX(rowScale$default.getScaleX());
        }
        ExpandableViewState expandableViewStateM463getBase5 = m463getBase();
        if (!Intrinsics.areEqual(expandableViewStateM463getBase5 != null ? Float.valueOf(expandableViewStateM463getBase5.getScaleY()) : null, rowScale$default.getScaleY()) && (expandableViewStateM463getBase2 = m463getBase()) != null) {
            expandableViewStateM463getBase2.setScaleY(rowScale$default.getScaleY());
        }
        if ((view instanceof ExpandableNotificationRow) && (expandableViewStateM463getBase = m463getBase()) != null) {
            expandableViewStateM463getBase.setZTranslation(0.0f);
        }
        ExpandableView expandableView2 = z ? (ExpandableView) view : null;
        if (expandableView2 != null) {
            this.stackedCoverProgressTransformer.apply(expandableView2, getNotificationStackedProgress());
        }
        ExpandableViewState expandableViewStateM463getBase6 = m463getBase();
        if (expandableViewStateM463getBase6 == null) {
            return;
        }
        int state = getStatusBarStateController().getState();
        if (((ViewState) expandableViewStateM463getBase6).gone) {
            if (state == 1 || state == 2) {
                if (view != null) {
                    OplusPhysicsPropertyAnimatorExtKt.setFinalValue(view, OplusPhysicsPropertyAnimator.Y_TRANSLATION, expandableViewStateM463getBase6.getYTranslation());
                }
                if (view != null) {
                    view.setAlpha(expandableViewStateM463getBase6.getAlpha());
                }
                if (view != null) {
                    view.setScaleX(expandableViewStateM463getBase6.getScaleX());
                }
                if (view != null) {
                    view.setScaleY(expandableViewStateM463getBase6.getScaleY());
                }
            }
        }
    }

    public void cancelAnimations(View view) {
        if (m463getBase() != null) {
            ViewState.abortAnimation(view, CLIP_WIDTH_PROPERTY.getAnimatorTag());
        }
        if (m463getBase() != null) {
            ViewState.abortAnimation(view, BLUR_RADIO_PROPERTY.getAnimatorTag());
        }
        if (m463getBase() != null) {
            ViewState.abortAnimation(view, ANIMATION_CORNER_RADIUS_PROPERTY.getAnimatorTag());
        }
        if (m463getBase() != null) {
            ViewState.abortAnimation(view, CapsuleViewState.Companion.getWIDTH_PROPERTY().getAnimatorTag());
        }
        if (m463getBase() != null) {
            ViewState.abortAnimation(view, ANIMATION_CAPSULE_PROGRESS_PROPERTY.getAnimatorTag());
        }
        if (m463getBase() != null) {
            ViewState.abortAnimation(view, ANIMATION_IMMERSIVE_PROGRESS_PROPERTY.getAnimatorTag());
        }
        if (m463getBase() != null) {
            ViewState.abortAnimation(view, GROUP_EXPANSION_PROGRESS_PROPERTY.getAnimatorTag());
        }
        if (m463getBase() != null) {
            ViewState.abortAnimation(view, GROUP_EXPAND_HEADER_ALPHA_PROPERTY.getAnimatorTag());
        }
        if (m463getBase() != null) {
            ViewState.abortAnimation(view, GROUP_EXPAND_CONTENT_ALPHA_PROPERTY.getAnimatorTag());
        }
    }

    public void copyFrom(OplusExpandableViewStateEx oplusExpandableViewStateEx) {
        setClipWidth(oplusExpandableViewStateEx.getClipWidth());
        setBlurRadio(oplusExpandableViewStateEx.getBlurRadio());
        setAnimationCornerRadius(oplusExpandableViewStateEx.getAnimationCornerRadius());
        setCapsuleProgress(oplusExpandableViewStateEx.getCapsuleProgress());
        setImmersiveProgress(oplusExpandableViewStateEx.getImmersiveProgress());
        setGroupExpansionProgress(oplusExpandableViewStateEx.getGroupExpansionProgress());
        setGroupExpandHeaderAlpha(oplusExpandableViewStateEx.getGroupExpandHeaderAlpha());
        setGroupExpandContentAlpha(oplusExpandableViewStateEx.getGroupExpandContentAlpha());
        setMaterialColorMode(oplusExpandableViewStateEx.getMaterialColorMode());
        setNotificationBlurMode(oplusExpandableViewStateEx.getNotificationBlurMode());
    }

    public float getAnimationCornerRadius() {
        return this.animationCornerRadius;
    }

    public float getBlurRadio() {
        return this.blurRadio;
    }

    public float getCapsuleProgress() {
        return this.capsuleProgress;
    }

    public int getClipWidth() {
        return ((RowAnimationStateExImpl) getRowAnimationStateEx()).getClipWidth(null);
    }

    public float getGroupExpandContentAlpha() {
        return this.groupExpandContentAlpha;
    }

    public float getGroupExpandHeaderAlpha() {
        return this.groupExpandHeaderAlpha;
    }

    public float getGroupExpansionProgress() {
        return this.groupExpansionProgress;
    }

    public float getImmersiveProgress() {
        return this.immersiveProgress;
    }

    public MaterialColorMode getMaterialColorMode() {
        return this.materialColorMode;
    }

    public int getNotGoneIndexWithOutChildren() {
        return this.notGoneIndexWithOutChildren;
    }

    public NotificationBlurMode getNotificationBlurMode() {
        return this.notificationBlurMode;
    }

    public float getNotificationStackedProgress() {
        return this.notificationStackedProgress;
    }

    public RowAnimationStateEx getRowAnimationStateEx() {
        return this.rowAnimationStateEx;
    }

    public void onHeightAnimationEnd(ExpandableView expandableView, boolean z) {
        ExpandableViewState expandableViewStateM463getBase = m463getBase();
        if (expandableViewStateM463getBase == null || !z) {
            return;
        }
        int actualHeight = expandableView.getActualHeight();
        int i = expandableViewStateM463getBase.height;
        if (actualHeight == i) {
            expandableView.setFinalActualHeight(i);
        }
    }

    public void setAnimationCornerRadius(float f) {
        this.animationCornerRadius = f;
    }

    public void setBlurRadio(float f) {
        this.blurRadio = f;
    }

    public void setCapsuleProgress(float f) {
        this.capsuleProgress = f;
    }

    public void setClipWidth(int i) {
        ((RowAnimationStateExImpl) getRowAnimationStateEx()).requestClipWidth("clip_width_default", i);
    }

    public void setGroupExpandContentAlpha(float f) {
        if (this.groupExpandContentAlpha == f) {
            return;
        }
        this.groupExpandContentAlpha = f;
    }

    public void setGroupExpandHeaderAlpha(float f) {
        if (this.groupExpandHeaderAlpha == f) {
            return;
        }
        this.groupExpandHeaderAlpha = f;
    }

    public void setGroupExpansionProgress(float f) {
        if (this.groupExpansionProgress == f) {
            return;
        }
        this.groupExpansionProgress = f;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("set groupExpansionProgress to ", f, "OplusExpandableViewStat-->", "SystemUi--Notification");
        }
    }

    public void setImmersiveProgress(float f) {
        this.immersiveProgress = f;
    }

    public void setMaterialColorMode(MaterialColorMode materialColorMode) {
        if (this.materialColorMode != materialColorMode) {
            this.materialColorMode = materialColorMode;
        }
    }

    public void setNotGoneIndexWithOutChildren(int i) {
        this.notGoneIndexWithOutChildren = i;
    }

    public void setNotificationBlurMode(NotificationBlurMode notificationBlurMode) {
        if (this.notificationBlurMode != notificationBlurMode) {
            this.notificationBlurMode = notificationBlurMode;
        }
    }

    public void setNotificationStackedProgress(float f) {
        this.notificationStackedProgress = f;
    }

    public String toString() {
        return super.toString() + ", rowAnimationStateEx" + getRowAnimationStateEx() + "; notificationStackedProgress=" + getNotificationStackedProgress() + ";stackedCoverProgressTransformer=" + this.stackedCoverProgressTransformer;
    }
}
