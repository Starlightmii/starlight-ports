package com.oplus.systemui.statusbar.notification.stack;

import android.os.Bundle;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.notification.stackednotification.NotificationGroupSplitEx;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.OplusPhysicsInterpolator;
import com.android.systemui.statusbar.notification.OplusPhysicsProperty;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.PhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.mergeanimator.OplusMergedAnimator;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.OplusStackStateAnimatorEx;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.base.LockScreenNotificationKotlinExKt;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimSinkingParams;
import com.oplus.systemui.notification.stackednotification.NotificationGroupSplitExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStackStateAnimatorExImpl extends OplusStackStateAnimatorEx {
    private static final String TAG = "OplusStackStateAnimatorExImpl";
    private final Lazy capsuleCardController;
    private final Lazy capsuleContainer;
    private final Lazy keyguardStateController;
    private final Lazy lockScreenExtStateController;
    private final Lazy notificationGroupSplit;
    private final Lazy nsslController;
    private final OplusLockscreenShadeTransitionControllerEx shadeTransitionControllerEx;
    public static final Companion Companion = new Companion();
    private static final COUISpringInterpolator CAPSULE_TO_STACK_INTERPOLATOR = new COUISpringInterpolator(0.8d, 0.15d);
    private static final kotlin.Lazy enableMergeAnimator$delegate = LazyKt__LazyJVMKt.lazy(new OplusStackStateAnimatorExImpl$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final boolean getEnableMergeAnimator() {
            return ((Boolean) OplusStackStateAnimatorExImpl.enableMergeAnimator$delegate.getValue()).booleanValue();
        }

        public static boolean hasType(int i, long j) {
            return (j & (1 << i)) != 0;
        }
    }

    public OplusStackStateAnimatorExImpl(OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6) {
        this.shadeTransitionControllerEx = oplusLockscreenShadeTransitionControllerEx;
        this.nsslController = lazy;
        this.lockScreenExtStateController = lazy2;
        this.notificationGroupSplit = lazy3;
        this.capsuleCardController = lazy4;
        this.capsuleContainer = lazy5;
        this.keyguardStateController = lazy6;
    }

    public static void initMergedAnimator(AnimationProperties animationProperties, OplusPhysicsProperty oplusPhysicsProperty, int i) {
        OplusPhysicsInterpolator customInterpolator = animationProperties.getCustomInterpolator(oplusPhysicsProperty.getProperty());
        OplusPhysicsInterpolator oplusPhysicsInterpolator = customInterpolator instanceof OplusPhysicsInterpolator ? customInterpolator : null;
        OplusMergedAnimator oplusMergedAnimatorEnsureMergedAnimator = OplusMergedAnimator.Companion.ensureMergedAnimator(oplusPhysicsInterpolator != null ? (float) oplusPhysicsInterpolator.getBounce() : 0.11f, oplusPhysicsInterpolator != null ? (float) oplusPhysicsInterpolator.getResponse() : 0.3f, animationProperties.delay);
        int animationType = oplusMergedAnimatorEnsureMergedAnimator.getAnimationType();
        if ((animationType == 100 && i == 99) || ((i == 100 && animationType == 99) || ((animationType == 19 && i == 18) || ((i == 19 && animationType == 18) || ((animationType == 20 && i == 21) || ((i == 20 && animationType == 21) || ((animationType == 22 && i == 23) || (i == 22 && animationType == 23)))))))) {
            oplusMergedAnimatorEnsureMergedAnimator.updateStartAndEndValue(oplusMergedAnimatorEnsureMergedAnimator.currProgress, oplusMergedAnimatorEnsureMergedAnimator.getEndValue() != 0.0f ? 0.0f : 1.0f);
        } else if (animationType != i || -1 == i) {
            oplusMergedAnimatorEnsureMergedAnimator.updateStartAndEndValue(0.0f, 1.0f);
        } else {
            oplusMergedAnimatorEnsureMergedAnimator.updateStartAndEndValue(oplusMergedAnimatorEnsureMergedAnimator.currProgress, oplusMergedAnimatorEnsureMergedAnimator.endValue);
        }
        oplusMergedAnimatorEnsureMergedAnimator.setAnimationType(i);
    }

    public static void kgNotifiCenterTransitionCustomInterpolator(AnimationProperties animationProperties, int i, int i2, boolean z) {
        ExtStateAnimParams.StackToNtfCenter stackToNtfCenter = ExtStateAnimParams.StackToNtfCenter.INSTANCE;
        animationProperties.duration = stackToNtfCenter.getTIME().get().longValue();
        OplusPhysicsInterpolator yInterpolator = ExtStateAnimSinkingParams.StackToNtfCenterTYLevelParams.INSTANCE.getYInterpolator(i2, i, z);
        animationProperties.setCustomInterpolator(View.TRANSLATION_Y, yInterpolator);
        animationProperties.setCustomInterpolator(ExpandableView.HEIGHT_PROPERTY.getProperty(), yInterpolator);
        Interpolator interpolator = ExtStateAnimParams.INSTANCE.toInterpolator(stackToNtfCenter.getSCALE());
        animationProperties.setCustomInterpolator(View.SCALE_X, interpolator);
        animationProperties.setCustomInterpolator(View.SCALE_Y, interpolator);
    }

    public static void notificationGroupExpansionInterpolator$default(OplusStackStateAnimatorExImpl oplusStackStateAnimatorExImpl, AnimationProperties animationProperties, ExpandableView expandableView, OplusPhysicsInterpolator oplusPhysicsInterpolator, int i) {
        boolean z = (i & 4) == 0;
        if ((i & 8) != 0) {
            oplusPhysicsInterpolator = null;
        }
        if (expandableView instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
            ((NotificationGroupSplitExImpl) oplusStackStateAnimatorExImpl.notificationGroupSplit.get()).notificationGroupExpansionInterpolator(animationProperties, expandableNotificationRow.mIsSummaryWithChildren ? expandableNotificationRow.mChildrenExpanded : false, z, oplusPhysicsInterpolator);
        }
    }

    public void applyAnimFilterAdditional(long j, AnimationFilter animationFilter) {
        Companion.getClass();
        if (Companion.hasType(10, j)) {
            animationFilter.animateAlpha = false;
        }
    }

    public final void applyCapsuleHandoffStartVelocity(AnimationProperties animationProperties) {
        OplusPhysicsInterpolator customInterpolator = animationProperties.getCustomInterpolator(View.TRANSLATION_Y);
        OplusPhysicsInterpolator oplusPhysicsInterpolator = customInterpolator instanceof OplusPhysicsInterpolator ? customInterpolator : null;
        if (oplusPhysicsInterpolator == null) {
            return;
        }
        Bundle extra = ((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getExtra();
        oplusPhysicsInterpolator.setStartVelocity(extra != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getStartVelocity(extra) : null);
    }

    public boolean applyWithoutAnimation(ExpandableView expandableView, ExpandableViewState expandableViewState) {
        if (!(expandableView instanceof NotificationShelf) && expandableView != null && expandableView.getVisibility() == 4 && expandableViewState != null && ((ViewState) expandableViewState).hidden) {
            OplusExpandableViewEx oplusExpandableViewEx = expandableView.mExpandableViewEx;
            if ((oplusExpandableViewEx != null ? oplusExpandableViewEx.getProxy() : null) == null) {
                expandableViewState.applyToView(expandableView);
                return true;
            }
        }
        if (expandableView != null && expandableView.getVisibility() == 0 && expandableViewState != null && ((ViewState) expandableViewState).hidden) {
            OplusExpandableViewEx oplusExpandableViewEx2 = expandableView.mExpandableViewEx;
            if ((oplusExpandableViewEx2 != null ? oplusExpandableViewEx2.getProxy() : null) == null && !((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isStackState()) {
                if (((CapsuleCardControl) this.capsuleCardController.get()).isStaticShowIng()) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("applyWithoutAnimation: ", LockScreenNotificationKotlinExKt.getLogKey(expandableView), ", isStaticShowIng", "OplusStackStateAnimatorExImpl-->", "SystemUi--Notification");
                    }
                    expandableView.setVisibility(4);
                    expandableViewState.cancelAnimations(expandableView);
                    expandableViewState.applyToView(expandableView);
                    return true;
                }
                if (((KeyguardStateController) this.keyguardStateController.get()).mShowing && !((NotificationCapsuleContainer) this.capsuleContainer.get()).isBindToStackView()) {
                    boolean z = expandableView.getTag(OplusExpandableViewStateExImpl.Companion.getCLIP_WIDTH_PROPERTY().getAnimatorTag()) != null;
                    PhysicsPropertyAnimator.Companion companion = PhysicsPropertyAnimator.Companion;
                    boolean z2 = expandableView.getTag(2131365784) != null;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("clipWAnimating: ", ", transYAnimation:", "OplusStackStateAnimatorExImpl-->", z, z2));
                    }
                    if (z && !z2) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("applyWithoutAnimation: ", LockScreenNotificationKotlinExKt.getLogKey(expandableView), ", onlyClipWAnimating", "OplusStackStateAnimatorExImpl-->", "SystemUi--Notification");
                        }
                        expandableView.setVisibility(4);
                        expandableViewState.cancelAnimations(expandableView);
                        expandableViewState.applyToView(expandableView);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public long calculateChildAnimationDelayEx(long j, long j2, ExpandableView expandableView, ExpandableViewState expandableViewState, int i) {
        Companion.getClass();
        if (Companion.hasType(10, j2)) {
            NotificationGroupSplitEx notificationGroupSplitEx = (NotificationGroupSplitEx) DependencyEx.sDependency.getDependency(NotificationGroupSplitEx.class);
            int i2 = notificationGroupSplitEx.expandRowIndex;
            long expandGroupMaxDelay = notificationGroupSplitEx.getExpandGroupMaxDelay(expandableView);
            if (i >= i2) {
                if (i != i2) {
                    return Math.max(expandGroupMaxDelay, Math.max(j, notificationGroupSplitEx.maxDelay));
                }
                if (expandableView instanceof ExpandableNotificationRow) {
                    ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
                    if (expandableNotificationRow.mIsSummaryWithChildren && !expandableNotificationRow.mChildrenExpanded) {
                        return Math.max(expandGroupMaxDelay, Math.max(j, notificationGroupSplitEx.maxDelay));
                    }
                }
            }
        }
        return j;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0059  */
    /* JADX WARN: Code duplicated, block: B:27:0x0074 A[RETURN] */
    public long calculateChildAnimationDelayForStacked(int i, ExpandableViewState expandableViewState, NotificationStackScrollLayout notificationStackScrollLayout) {
        long jLongValue;
        if (i == 18) {
            jLongValue = ExtStateAnimParams.StackToCapsule.INSTANCE.getDELAY_FOR_CAPSULE_STACK_SWIPE().get().longValue() * ((long) expandableViewState.mExpandableViewStateEx.getNotGoneIndexWithOutChildren());
            if (jLongValue < 0) {
                return jLongValue;
            }
        } else if (i == 19) {
            int i2 = -1;
            int i3 = 0;
            for (Object obj : SequencesKt___SequencesKt.filter(ConvenienceExtensionsKt.getChildren(notificationStackScrollLayout), OplusStackStateAnimatorExImpl$initAnimationCustomInterpolator$$inlined$filterIsInstance$1.INSTANCE$1)) {
                if (i3 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                if (((ExpandableView) obj).isVisibleToUser()) {
                    i2 = i3;
                }
                i3++;
            }
            long jLongValue2 = ExtStateAnimParams.StackToCapsule.INSTANCE.getDELAY_FOR_CAPSULE_STACK_SWIPE().get().longValue() * ((long) (i2 - expandableViewState.mExpandableViewStateEx.getNotGoneIndexWithOutChildren()));
            if (jLongValue2 >= 0) {
                return jLongValue2;
            }
        } else if (i == 23) {
            jLongValue = ExtStateAnimParams.StackToCapsule.INSTANCE.getDELAY_FOR_CAPSULE_STACK_SWIPE().get().longValue() * ((long) expandableViewState.mExpandableViewStateEx.getNotGoneIndexWithOutChildren());
            if (jLongValue < 0) {
                return jLongValue;
            }
        }
        return 0L;
    }

    public void handleRemoveTransientContainer(NotificationStackScrollLayout notificationStackScrollLayout, ExpandableView expandableView, ArrayList<ExpandableView> arrayList) {
        if (((NotificationStackScrollLayoutController) this.nsslController.get()).getNotificationListContainer().getSwipeActionHelper().mDismissPendingMap.containsKey(expandableView)) {
            return;
        }
        arrayList.add(expandableView);
    }

    /* JADX WARN: Code duplicated, block: B:151:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:95:0x035d  */
    public void initAnimationCustomInterpolator(ExpandableView expandableView, AnimationProperties animationProperties, long j, NotificationStackScrollLayout notificationStackScrollLayout) {
        int i;
        ExpandableView expandableView2;
        AnimationProperties animationProperties2;
        int i2;
        ExtStateAnimParams.ExtStateParams data;
        ExtStateAnimParams.ExtStateParams data2;
        Sequence children;
        Sequence sequenceFilter;
        animationProperties.getClass();
        int notGoneIndexWithOutChildren = expandableView.getViewState().mExpandableViewStateEx.getNotGoneIndexWithOutChildren();
        if (notificationStackScrollLayout == null || (children = ConvenienceExtensionsKt.getChildren(notificationStackScrollLayout)) == null || (sequenceFilter = SequencesKt___SequencesKt.filter(children, OplusStackStateAnimatorExImpl$initAnimationCustomInterpolator$$inlined$filterIsInstance$1.INSTANCE)) == null) {
            i = 0;
        } else {
            Iterator it = sequenceFilter.iterator();
            i = 0;
            while (it.hasNext()) {
                if (((ExpandableView) it.next()).getViewState().mExpandableViewStateEx.getNotGoneIndexWithOutChildren() >= 0 && (i = i + 1) < 0) {
                    CollectionsKt__CollectionsKt.throwCountOverflow();
                    throw null;
                }
            }
        }
        if (((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isCapsuleState() && ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getLastExtState() == 2) {
            animationProperties.delay = ExtStateAnimParams.ImmerToCapsule.INSTANCE.getLAST_CARD_DELAY().get().longValue();
        }
        if (!this.shadeTransitionControllerEx.isAnimatingToCollapse()) {
            if (this.shadeTransitionControllerEx.isAnimatingToExpand()) {
                kgNotifiCenterTransitionCustomInterpolator(animationProperties, i, notGoneIndexWithOutChildren, false);
                Companion.getClass();
                if (Companion.hasType(10, j)) {
                    notificationGroupExpansionInterpolator$default(this, animationProperties, expandableView, null, 8);
                }
                i2 = 99;
            } else {
                Companion.getClass();
                int i3 = 19;
                if (Companion.hasType(19, j)) {
                    updatHeightAndWidthInterpolator(i, animationProperties, expandableView, notGoneIndexWithOutChildren);
                    Property property = View.SCALE_X;
                    COUISpringInterpolator cOUISpringInterpolator = CAPSULE_TO_STACK_INTERPOLATOR;
                    animationProperties.setCustomInterpolator(property, cOUISpringInterpolator);
                    animationProperties.setCustomInterpolator(View.SCALE_Y, cOUISpringInterpolator);
                    if (((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isImmersiveState()) {
                        data2 = ExtStateAnimParams.StackToImmer.INSTANCE.getData();
                    } else {
                        data2 = (((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isCapsuleState() && ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getLastExtState() == 2) ? ExtStateAnimParams.ImmerToCapsule.INSTANCE.getData() : ExtStateAnimParams.StackToCapsule.INSTANCE.getData();
                    }
                    animationProperties.duration = data2.getTIME().get().longValue();
                    Property property2 = View.ALPHA;
                    ExtStateAnimParams extStateAnimParams = ExtStateAnimParams.INSTANCE;
                    animationProperties.setCustomInterpolator(property2, extStateAnimParams.toInterpolator(data2.getALPHA()));
                    animationProperties.setCustomInterpolator(ExpandableView.HEIGHT_PROPERTY.getProperty(), extStateAnimParams.toInterpolator(data2.getHEIGHT()));
                    OplusExpandableViewStateExImpl.Companion companion = OplusExpandableViewStateExImpl.Companion;
                    animationProperties.setCustomInterpolator(companion.getBLUR_RADIO_PROPERTY().getProperty(), extStateAnimParams.toInterpolator(data2.getBLUR()));
                    animationProperties.setCustomInterpolator(companion.getANIMATION_CORNER_RADIUS_PROPERTY().getProperty(), extStateAnimParams.toInterpolator(data2.getCORNER_RADIUS()));
                    Property property3 = companion.getANIMATION_CAPSULE_PROGRESS_PROPERTY().getProperty();
                    CapsuleConfig.INSTANCE.getClass();
                    animationProperties.setCustomInterpolator(property3, CapsuleConfig.isLightOs ? extStateAnimParams.toInterpolator(ExtStateAnimParams.LightOSAnimParams.INSTANCE.getSTACK_TO_CAPSULE_ALPHA()) : new LinearInterpolator());
                } else {
                    i3 = 18;
                    if (Companion.hasType(18, j)) {
                        updatHeightAndWidthInterpolator(i, animationProperties, expandableView, notGoneIndexWithOutChildren);
                        Property property4 = View.SCALE_X;
                        COUISpringInterpolator cOUISpringInterpolator2 = CAPSULE_TO_STACK_INTERPOLATOR;
                        animationProperties.setCustomInterpolator(property4, cOUISpringInterpolator2);
                        animationProperties.setCustomInterpolator(View.SCALE_Y, cOUISpringInterpolator2);
                        int lastExtState = ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getLastExtState();
                        Lazy lazy = this.lockScreenExtStateController;
                        if (lastExtState == 1) {
                            data = ((ILockScreenExtStateController) lazy.get()).isStackState() ? ExtStateAnimParams.CapsuleToStack.INSTANCE.getData() : ExtStateAnimParams.CapsuleToImmer.INSTANCE.getData();
                        } else if (((ILockScreenExtStateController) lazy.get()).getLastExtState() == 2) {
                            data = ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isImmersiveState() ? ExtStateAnimParams.CapsuleToImmer.INSTANCE.getData() : ExtStateAnimParams.ImmerToStack.INSTANCE.getData();
                        } else {
                            data = ExtStateAnimParams.CapsuleToStack.INSTANCE.getData();
                        }
                        animationProperties.duration = data.getTIME().get().longValue();
                        Property property5 = View.ALPHA;
                        ExtStateAnimParams extStateAnimParams2 = ExtStateAnimParams.INSTANCE;
                        animationProperties.setCustomInterpolator(property5, extStateAnimParams2.toInterpolator(data.getALPHA()));
                        animationProperties.setCustomInterpolator(ExpandableView.HEIGHT_PROPERTY.getProperty(), extStateAnimParams2.toInterpolator(data.getHEIGHT()));
                        OplusExpandableViewStateExImpl.Companion companion2 = OplusExpandableViewStateExImpl.Companion;
                        animationProperties.setCustomInterpolator(companion2.getBLUR_RADIO_PROPERTY().getProperty(), extStateAnimParams2.toInterpolator(data.getBLUR()));
                        animationProperties.setCustomInterpolator(companion2.getANIMATION_CORNER_RADIUS_PROPERTY().getProperty(), extStateAnimParams2.toInterpolator(data.getCORNER_RADIUS()));
                        Property property6 = companion2.getANIMATION_CAPSULE_PROGRESS_PROPERTY().getProperty();
                        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
                        capsuleConfig.getClass();
                        animationProperties.setCustomInterpolator(property6, CapsuleConfig.isLightOs ? extStateAnimParams2.toInterpolator(ExtStateAnimParams.LightOSAnimParams.INSTANCE.getCAPSULE_TO_STACK_ALPHA()) : new LinearInterpolator());
                        applyCapsuleHandoffStartVelocity(animationProperties);
                        expandableView.getViewState().mExpandableViewStateEx.setAnimationCornerRadius(capsuleConfig.getCardRadius());
                    } else {
                        i3 = 23;
                        if (Companion.hasType(23, j)) {
                            expandableView2 = expandableView;
                            animationProperties2 = animationProperties;
                            loadSinkingCustomInterpolator(i, animationProperties2, expandableView2, ExtStateAnimSinkingParams.SinkingToStackParams.INSTANCE, notGoneIndexWithOutChildren, Companion.hasType(10, j));
                            i2 = i3;
                        } else {
                            expandableView2 = expandableView;
                            animationProperties2 = animationProperties;
                            int i4 = 22;
                            if (Companion.hasType(22, j)) {
                                loadSinkingCustomInterpolator(i, animationProperties2, expandableView2, ExtStateAnimSinkingParams.StackToSinkingParams.INSTANCE, notGoneIndexWithOutChildren, false);
                            } else {
                                i4 = 21;
                                if (Companion.hasType(21, j)) {
                                    loadSinkingCustomInterpolator(i, animationProperties2, expandableView2, ExtStateAnimSinkingParams.SinkingToCapsuleParams.INSTANCE, notGoneIndexWithOutChildren, false);
                                } else {
                                    i4 = 20;
                                    if (Companion.hasType(20, j)) {
                                        loadSinkingCustomInterpolator(i, animationProperties2, expandableView2, ExtStateAnimSinkingParams.CapsuleToSinkingParams.INSTANCE, notGoneIndexWithOutChildren, false);
                                        applyCapsuleHandoffStartVelocity(animationProperties2);
                                        expandableView2.getViewState().mExpandableViewStateEx.setAnimationCornerRadius(CapsuleConfig.INSTANCE.getCardRadius());
                                    } else if (Companion.hasType(10, j)) {
                                        notificationGroupExpansionInterpolator$default(this, animationProperties2, expandableView2, ExtStateAnimSinkingParams.GroupNotificationLevelParams.INSTANCE.getYInterpolator(notGoneIndexWithOutChildren, i, false), 4);
                                        i2 = 10;
                                    } else {
                                        animationProperties2.removeCustomInterpolator(View.ALPHA);
                                        animationProperties2.removeCustomInterpolator(View.SCALE_X);
                                        animationProperties2.removeCustomInterpolator(View.SCALE_Y);
                                        Property property7 = View.TRANSLATION_Y;
                                        animationProperties2.removeCustomInterpolator(property7);
                                        animationProperties2.removeCustomInterpolator(ExpandableView.HEIGHT_PROPERTY.getProperty());
                                        OplusExpandableViewStateExImpl.Companion companion3 = OplusExpandableViewStateExImpl.Companion;
                                        animationProperties2.removeCustomInterpolator(companion3.getCLIP_WIDTH_PROPERTY().getProperty());
                                        animationProperties2.removeCustomInterpolator(companion3.getGROUP_EXPANSION_PROGRESS_PROPERTY().getProperty());
                                        animationProperties2.removeCustomInterpolator(companion3.getGROUP_EXPAND_HEADER_ALPHA_PROPERTY().getProperty());
                                        animationProperties2.removeCustomInterpolator(companion3.getGROUP_EXPAND_CONTENT_ALPHA_PROPERTY().getProperty());
                                        animationProperties2.setCustomInterpolator(property7, ExtStateAnimSinkingParams.DefaultTYLevelParams.INSTANCE.getYInterpolator(notGoneIndexWithOutChildren, i, false));
                                        i2 = -1;
                                    }
                                }
                            }
                            i2 = i4;
                        }
                    }
                }
                expandableView2 = expandableView;
                animationProperties2 = animationProperties;
                i2 = i3;
            }
            if (Companion.getEnableMergeAnimator()) {
                CapsuleConfig.INSTANCE.getClass();
                if (!CapsuleConfig.isMiddleOrHighAnimLevel || i2 == 10) {
                }
                ExpandableViewState viewState = expandableView2.getViewState();
                if (animationProperties2.getAnimationFilter().animateY && expandableView2.getTranslationY() != viewState.getYTranslation()) {
                    initMergedAnimator(animationProperties2, OplusPhysicsPropertyAnimator.Y_TRANSLATION, i2);
                }
                if (animationProperties2.getAnimationFilter().shouldAnimateProperty(View.SCALE_X) && expandableView2.getScaleX() != viewState.getScaleX()) {
                    initMergedAnimator(animationProperties2, OplusPhysicsPropertyAnimator.X_SCALE, i2);
                }
                if (animationProperties2.getAnimationFilter().animateAlpha && expandableView2.getAlpha() != viewState.getAlpha()) {
                    initMergedAnimator(animationProperties2, OplusPhysicsPropertyAnimator.ALPHA, i2);
                }
                OplusExpandableViewEx oplusExpandableViewEx = expandableView2.mExpandableViewEx;
                if (oplusExpandableViewEx.getClipWidth() != viewState.mExpandableViewStateEx.getClipWidth()) {
                    initMergedAnimator(animationProperties2, OplusExpandableViewStateExImplKt.toPhysicsProperty(OplusExpandableViewStateExImpl.Companion.getCLIP_WIDTH_PROPERTY(), 0.1f), i2);
                }
                if (oplusExpandableViewEx.getBlurRadio() != viewState.mExpandableViewStateEx.getBlurRadio()) {
                    initMergedAnimator(animationProperties2, OplusExpandableViewStateExImplKt.toPhysicsProperty(OplusExpandableViewStateExImpl.Companion.getBLUR_RADIO_PROPERTY(), 0.1f), i2);
                }
                if (oplusExpandableViewEx.getAnimationCornerRadius() != viewState.mExpandableViewStateEx.getAnimationCornerRadius()) {
                    initMergedAnimator(animationProperties2, OplusExpandableViewStateExImplKt.toPhysicsProperty(OplusExpandableViewStateExImpl.Companion.getANIMATION_CORNER_RADIUS_PROPERTY(), 0.1f), i2);
                }
                if (CapsuleConfig.isLightOs && oplusExpandableViewEx.getCapsuleProgress() != viewState.mExpandableViewStateEx.getCapsuleProgress()) {
                    initMergedAnimator(animationProperties2, OplusExpandableViewStateExImplKt.toPhysicsProperty(OplusExpandableViewStateExImpl.Companion.getANIMATION_CAPSULE_PROGRESS_PROPERTY(), 0.1f), i2);
                }
                if (expandableView2 instanceof ExpandableNotificationRow) {
                    OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx = ((ExpandableNotificationRow) expandableView2).mRowEx;
                    if ((oplusExpandableNotificationRowEx != null ? oplusExpandableNotificationRowEx.getGroupExpandContentAlpha() : 0.0f) == viewState.mExpandableViewStateEx.getGroupExpandContentAlpha()) {
                        return;
                    }
                    initMergedAnimator(animationProperties2, OplusExpandableViewStateExImplKt.toPhysicsProperty(OplusExpandableViewStateExImpl.Companion.getGROUP_EXPAND_CONTENT_ALPHA_PROPERTY(), 0.1f), i2);
                    return;
                }
                return;
            }
        }
        kgNotifiCenterTransitionCustomInterpolator(animationProperties, i, notGoneIndexWithOutChildren, true);
        Companion.getClass();
        if (Companion.hasType(10, j)) {
            notificationGroupExpansionInterpolator$default(this, animationProperties, expandableView, null, 12);
        }
        i2 = 100;
        expandableView2 = expandableView;
        animationProperties2 = animationProperties;
        if (Companion.getEnableMergeAnimator()) {
            CapsuleConfig.INSTANCE.getClass();
            if (CapsuleConfig.isMiddleOrHighAnimLevel) {
            }
        }
    }

    public final void loadSinkingCustomInterpolator(int i, AnimationProperties animationProperties, ExpandableView expandableView, ExtStateAnimSinkingParams.SinkingLevelParam sinkingLevelParam, int i2, boolean z) {
        updatHeightAndWidthInterpolator(i, animationProperties, expandableView, i2);
        Property property = View.SCALE_X;
        COUISpringInterpolator cOUISpringInterpolator = CAPSULE_TO_STACK_INTERPOLATOR;
        animationProperties.setCustomInterpolator(property, cOUISpringInterpolator);
        animationProperties.setCustomInterpolator(View.SCALE_Y, cOUISpringInterpolator);
        animationProperties.duration = sinkingLevelParam.getTIME().get().longValue();
        Property property2 = View.ALPHA;
        ExtStateAnimParams extStateAnimParams = ExtStateAnimParams.INSTANCE;
        animationProperties.setCustomInterpolator(property2, extStateAnimParams.toInterpolator(sinkingLevelParam.getALPHA()));
        if (z && expandableView.isSummaryWithChildren() && expandableView.areChildrenExpanded()) {
            animationProperties.setCustomInterpolator(ExpandableView.HEIGHT_PROPERTY.getProperty(), animationProperties.getCustomInterpolator(View.TRANSLATION_Y));
        } else {
            animationProperties.setCustomInterpolator(ExpandableView.HEIGHT_PROPERTY.getProperty(), extStateAnimParams.toInterpolator(sinkingLevelParam.getHEIGHT()));
        }
        OplusExpandableViewStateExImpl.Companion companion = OplusExpandableViewStateExImpl.Companion;
        animationProperties.setCustomInterpolator(companion.getBLUR_RADIO_PROPERTY().getProperty(), extStateAnimParams.toInterpolator(sinkingLevelParam.getBLUR()));
        animationProperties.setCustomInterpolator(companion.getANIMATION_CORNER_RADIUS_PROPERTY().getProperty(), extStateAnimParams.toInterpolator(sinkingLevelParam.getCORNER_RADIUS()));
        Property property3 = companion.getANIMATION_CAPSULE_PROGRESS_PROPERTY().getProperty();
        CapsuleConfig.INSTANCE.getClass();
        animationProperties.setCustomInterpolator(property3, CapsuleConfig.isLightOs ? extStateAnimParams.toInterpolator(ExtStateAnimParams.LightOSAnimParams.INSTANCE.getCAPSULE_TO_STACK_ALPHA()) : new LinearInterpolator());
    }

    public void recordExpansionAnimState(ArrayList<NotificationStackScrollLayout.AnimationEvent> arrayList) {
        ((NotificationGroupSplitExImpl) this.notificationGroupSplit.get()).recordExpansionAnimState(arrayList);
    }

    public final void updatHeightAndWidthInterpolator(int i, AnimationProperties animationProperties, ExpandableView expandableView, int i2) {
        OplusPhysicsInterpolator interpolator;
        OplusPhysicsInterpolator interpolator2;
        OplusPhysicsInterpolator oplusPhysicsInterpolator;
        ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType extStateEventAnimType;
        ILockScreenExtStateController.ExtStateEvent extStateEvent = (ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getExtStateEvent().getValue();
        ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType animateType = extStateEvent.getAnimateType();
        OplusPhysicsInterpolator oplusPhysicsInterpolator2 = new OplusPhysicsInterpolator(0.10999999940395355d, 0.30000001192092896d, (Float) null);
        animateType.getClass();
        if (animateType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.CAPSULE_TO_IMMERSIVE || (animateType == (extStateEventAnimType = ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.IMMERSIVE_TO_IMMERSIVE) && expandableView.getViewState().mExpandableViewStateEx.getImmersiveProgress() == 1.0f)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("updatHeightAndWidthInterpolator: ", LockScreenNotificationKotlinExKt.getLogKey(expandableView), ", capsule ->imm", "OplusStackStateAnimatorExImpl-->", "SystemUi--Notification");
            }
            ExtStateAnimParams extStateAnimParams = ExtStateAnimParams.INSTANCE;
            ExtStateAnimParams.CapsuleToImmer capsuleToImmer = ExtStateAnimParams.CapsuleToImmer.INSTANCE;
            interpolator = extStateAnimParams.toInterpolator(capsuleToImmer.getData().getTRANSLATION_Y());
            interpolator2 = extStateAnimParams.toInterpolator(capsuleToImmer.getData().getCLIP());
        } else if (animateType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.IMMERSIVE_TO_CAPSULE || (animateType == extStateEventAnimType && expandableView.getViewState().mExpandableViewStateEx.getImmersiveProgress() != 1.0f)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("updatHeightAndWidthInterpolator: ", LockScreenNotificationKotlinExKt.getLogKey(expandableView), ", imm -> capsule ", "OplusStackStateAnimatorExImpl-->", "SystemUi--Notification");
            }
            ExtStateAnimParams extStateAnimParams2 = ExtStateAnimParams.INSTANCE;
            ExtStateAnimParams.ImmerToCapsule immerToCapsule = ExtStateAnimParams.ImmerToCapsule.INSTANCE;
            interpolator = extStateAnimParams2.toInterpolator(immerToCapsule.getData().getTRANSLATION_Y());
            interpolator2 = extStateAnimParams2.toInterpolator(immerToCapsule.getData().getCLIP());
        } else if (animateType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.SINKING_TO_CAPSULE) {
            ExtStateAnimSinkingParams.SinkingToCapsuleParams sinkingToCapsuleParams = ExtStateAnimSinkingParams.SinkingToCapsuleParams.INSTANCE;
            interpolator = sinkingToCapsuleParams.getYInterpolator(i2, i, true);
            interpolator2 = sinkingToCapsuleParams.getWidthInterpolator(i2, i, true);
        } else if (animateType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.CAPSULE_TO_SINKING) {
            ExtStateAnimSinkingParams.CapsuleToSinkingParams capsuleToSinkingParams = ExtStateAnimSinkingParams.CapsuleToSinkingParams.INSTANCE;
            interpolator = capsuleToSinkingParams.getYInterpolator(i2, i, false);
            interpolator2 = capsuleToSinkingParams.getWidthInterpolator(i2, i, false);
        } else if (animateType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.STACK_TO_SINKING) {
            ExtStateAnimSinkingParams.StackToSinkingParams stackToSinkingParams = ExtStateAnimSinkingParams.StackToSinkingParams.INSTANCE;
            interpolator = stackToSinkingParams.getYInterpolator(i2, i, true);
            interpolator2 = stackToSinkingParams.getWidthInterpolator(i2, i, true);
        } else if (animateType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.SINKING_TO_STACK) {
            ExtStateAnimSinkingParams.SinkingToStackParams sinkingToStackParams = ExtStateAnimSinkingParams.SinkingToStackParams.INSTANCE;
            interpolator = sinkingToStackParams.getYInterpolator(i2, i, false);
            interpolator2 = sinkingToStackParams.getWidthInterpolator(i2, i, false);
        } else {
            ILockScreenExtStateController.Companion companion = ILockScreenExtStateController.Companion;
            int oldState = extStateEvent.getOldState();
            companion.getClass();
            if (oldState != 0) {
                if (extStateEvent.getNewState() == 0) {
                    ExtStateAnimSinkingParams.CapsuleToStackParams capsuleToStackParams = ExtStateAnimSinkingParams.CapsuleToStackParams.INSTANCE;
                    interpolator = capsuleToStackParams.getYInterpolator(i2, i, false);
                    interpolator2 = capsuleToStackParams.getWidthInterpolator(i2, i, false);
                } else {
                    oplusPhysicsInterpolator = oplusPhysicsInterpolator2;
                }
                animationProperties.setCustomInterpolator(OplusExpandableViewStateExImpl.Companion.getCLIP_WIDTH_PROPERTY().getProperty(), oplusPhysicsInterpolator2);
                animationProperties.setCustomInterpolator(View.TRANSLATION_Y, oplusPhysicsInterpolator);
            }
            ExtStateAnimSinkingParams.StackToCapsuleParams stackToCapsuleParams = ExtStateAnimSinkingParams.StackToCapsuleParams.INSTANCE;
            interpolator = stackToCapsuleParams.getYInterpolator(i2, i, true);
            interpolator2 = stackToCapsuleParams.getWidthInterpolator(i2, i, true);
        }
        OplusPhysicsInterpolator oplusPhysicsInterpolator3 = interpolator;
        oplusPhysicsInterpolator2 = interpolator2;
        oplusPhysicsInterpolator = oplusPhysicsInterpolator3;
        animationProperties.setCustomInterpolator(OplusExpandableViewStateExImpl.Companion.getCLIP_WIDTH_PROPERTY().getProperty(), oplusPhysicsInterpolator2);
        animationProperties.setCustomInterpolator(View.TRANSLATION_Y, oplusPhysicsInterpolator);
    }
}
