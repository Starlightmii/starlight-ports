package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import android.content.Context;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.car.app.serialization.Bundler$;
import androidx.core.view.ViewGroupKt;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.base.LockScreenNotificationKotlinExKt;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ContainerAnimatorViewHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ContainerAnimatorViewHe";
    private MutableStateFlow _childCountFlow;
    private MutableStateFlow _preChildCountFlow;
    private final LockScreenAmbientState ambientState;
    private final ArrayList<AnimationEvent> animationEvents;
    private final HashSet<Runnable> animationFinishedRunnables;
    public boolean animationRunning;
    private final HashSet<CapsuleItemView>[] animationViewSet;
    public boolean animationsEnabled;
    private final StateFlow childCountFlow;
    public boolean childrenUpdateRequested;
    private final ViewTreeObserver.OnPreDrawListener childrenUpdater;
    private List<String> commonAnimationTags;
    private final ContainerAlgorithm containerScrollAlgorithm;
    private final Context context;
    private final NotificationCapsuleContainer hostView;
    private Integer lastContainerHeight;
    public ILockScreenExtStateController lockScreenExtStateController;
    public boolean needsAnimation;
    private Function1 onContainerHeightChangeListener;
    private final StateFlow preChildCountFlow;
    private final ContainerStateAnimator stateAnimator;
    private final String tag;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ContainerAnimatorViewHelper(Context context, NotificationCapsuleContainer notificationCapsuleContainer, String str) {
        this.context = context;
        this.hostView = notificationCapsuleContainer;
        this.tag = str;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(0);
        this._childCountFlow = MutableStateFlow;
        this.childCountFlow = MutableStateFlow;
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(0);
        this._preChildCountFlow = MutableStateFlow2;
        this.preChildCountFlow = MutableStateFlow2;
        this.animationFinishedRunnables = new HashSet<>();
        this.animationEvents = new ArrayList<>();
        this.ambientState = new LockScreenAmbientState(notificationCapsuleContainer.getCapsuleEarController());
        HashSet<CapsuleItemView>[] hashSetArr = new HashSet[7];
        for (int i = 0; i < 7; i++) {
            hashSetArr[i] = new HashSet<>();
        }
        this.animationViewSet = hashSetArr;
        this.containerScrollAlgorithm = new ContainerAlgorithm(this.context, this.hostView, this);
        this.stateAnimator = new ContainerStateAnimator(this.context, this.hostView, this);
        ContainerAnimatorViewHelper$childrenUpdater$1 containerAnimatorViewHelper$childrenUpdater$1 = new ContainerAnimatorViewHelper$childrenUpdater$1();
        containerAnimatorViewHelper$childrenUpdater$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.childrenUpdater = containerAnimatorViewHelper$childrenUpdater$1;
        this.animationsEnabled = true;
        this.commonAnimationTags = new ArrayList();
    }

    public static final void access$updateChildren(ContainerAnimatorViewHelper containerAnimatorViewHelper) {
        boolean z;
        CapsuleItemView capsuleItemView;
        Trace.beginSection(containerAnimatorViewHelper.tag + "#updateChildren");
        CapsuleItemView capsuleItemView2 = (CapsuleItemView) CollectionsKt___CollectionsKt.lastOrNull(containerAnimatorViewHelper.animationViewSet[4]);
        if (capsuleItemView2 != null) {
            LockScreenAmbientState lockScreenAmbientState = containerAnimatorViewHelper.ambientState;
            lockScreenAmbientState.setLastBaseCard(lockScreenAmbientState.getBaseCard());
            containerAnimatorViewHelper.ambientState.setBaseCard(capsuleItemView2);
        } else {
            CapsuleItemView capsuleItemView3 = (CapsuleItemView) CollectionsKt___CollectionsKt.lastOrNull(containerAnimatorViewHelper.animationViewSet[0]);
            if (capsuleItemView3 != null) {
                LockScreenAmbientState lockScreenAmbientState2 = containerAnimatorViewHelper.ambientState;
                lockScreenAmbientState2.setLastBaseCard(lockScreenAmbientState2.getBaseCard());
                CapsuleDataPinHandler.INSTANCE.setupPinCard(capsuleItemView3, "onViewAddedInternal:animation");
                containerAnimatorViewHelper.ambientState.setBaseCard(capsuleItemView3);
            }
        }
        containerAnimatorViewHelper.containerScrollAlgorithm.resetViewStates(containerAnimatorViewHelper.ambientState);
        Boolean boolValueOf = null;
        if (containerAnimatorViewHelper.needsAnimation || containerAnimatorViewHelper.stateAnimator.isRunning()) {
            if (containerAnimatorViewHelper.needsAnimation) {
                for (CapsuleItemView capsuleItemView4 : containerAnimatorViewHelper.animationViewSet[0]) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("generateAllAnimationEvents: ANIMATION_TYPE_ADD，", capsuleItemView4.getEntry().getKey(), "ContainerAnimatorViewHe-->", "SystemUi--Notification");
                    }
                    containerAnimatorViewHelper.animationEvents.add(new AnimationEvent(capsuleItemView4, 0, 384L, containerAnimatorViewHelper.stateAnimator.getAnimationFilter(), containerAnimatorViewHelper.ambientState.getLastBaseCard(), (ExpandableView) null, 96));
                }
                for (CapsuleItemView capsuleItemView5 : containerAnimatorViewHelper.animationViewSet[1]) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("generateAllAnimationEvents: ANIMATION_VIEW_SET_TYPE_REMOVE，", capsuleItemView5.getEntry().getKey(), "ContainerAnimatorViewHe-->", "SystemUi--Notification");
                    }
                    containerAnimatorViewHelper.animationEvents.add(new AnimationEvent(capsuleItemView5, 1, 384L, containerAnimatorViewHelper.stateAnimator.getAnimationFilter(), containerAnimatorViewHelper.ambientState.getBaseCard(), (ExpandableView) null, 96));
                }
                containerAnimatorViewHelper.animationViewSet[0].clear();
                containerAnimatorViewHelper.animationViewSet[1].clear();
                for (CapsuleItemView capsuleItemView6 : containerAnimatorViewHelper.animationViewSet[4]) {
                    boolean zIsEmpty = containerAnimatorViewHelper.animationViewSet[3].isEmpty();
                    ExpandableView referStackViewForImmersiveShowItem = containerAnimatorViewHelper.ambientState.getReferStackViewForImmersiveShowItem();
                    if (referStackViewForImmersiveShowItem == null || referStackViewForImmersiveShowItem.mOnHeightChangedListener != null) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("generateAllAnimationEvents: ANIMATION_VIEW_SET_TYPE_IMMERSIVE_SHOW_ITEM，", capsuleItemView6.getEntry().getKey(), "ContainerAnimatorViewHe-->", "SystemUi--Notification");
                        }
                        containerAnimatorViewHelper.animationEvents.add(new AnimationEvent(capsuleItemView6, 2, ExtStateAnimParams.ImmerToCapsule.INSTANCE.getLAST_CARD_DURATION().get().longValue(), containerAnimatorViewHelper.stateAnimator.getAnimationFilter(), !zIsEmpty ? null : containerAnimatorViewHelper.ambientState.getLastBaseCard(), containerAnimatorViewHelper.ambientState.getReferStackViewForImmersiveShowItem(), 64));
                    } else {
                        LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, Bundler$.ExternalSyntheticOutline0.m("generateAllAnimationEvents: ANIMATION_VIEW_SET_TYPE_IMMERSIVE_SHOW_ITEM，", capsuleItemView6.getEntry().getKey(), ",isRemoved , skip"));
                    }
                }
                for (CapsuleItemView capsuleItemView7 : containerAnimatorViewHelper.animationViewSet[3]) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("generateAllAnimationEvents: ANIMATION_VIEW_SET_TYPE_IMMERSIVE_HIDE_ITEM，", capsuleItemView7.getEntry().getKey(), "ContainerAnimatorViewHe-->", "SystemUi--Notification");
                    }
                    ArrayList<AnimationEvent> arrayList = containerAnimatorViewHelper.animationEvents;
                    if (arrayList != null && arrayList.isEmpty()) {
                        z = false;
                        break;
                    }
                    int size = arrayList.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            z = false;
                            break;
                        }
                        AnimationEvent animationEvent = arrayList.get(i);
                        i++;
                        if (animationEvent.getAnimationType() == 2) {
                            z = true;
                            break;
                        }
                    }
                    OplusAnimationFilter oplusAnimationFilter = new OplusAnimationFilter();
                    ((AnimationFilter) oplusAnimationFilter).hasDelays = true;
                    ExtStateAnimParams.CapsuleToImmer capsuleToImmer = ExtStateAnimParams.CapsuleToImmer.INSTANCE;
                    ((AnimationFilter) oplusAnimationFilter).customDelay = capsuleToImmer.getNEXT_CARD_DELAY().get().longValue();
                    containerAnimatorViewHelper.animationEvents.add(new AnimationEvent(capsuleItemView7, 3, capsuleToImmer.getNEXT_CARD_DURATION().get().longValue(), oplusAnimationFilter, z ? null : containerAnimatorViewHelper.ambientState.getBaseCard(), containerAnimatorViewHelper.ambientState.getReferStackView(), 64));
                }
                containerAnimatorViewHelper.animationViewSet[3].clear();
                containerAnimatorViewHelper.animationViewSet[4].clear();
                for (CapsuleItemView capsuleItemView8 : containerAnimatorViewHelper.animationViewSet[5]) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("generateAllAnimationEvents: ANIMATION_VIEW_SET_TYPE_STACK_HIDE_ITEM，", capsuleItemView8.getEntry().getKey(), "ContainerAnimatorViewHe-->", "SystemUi--Notification");
                    }
                    containerAnimatorViewHelper.animationEvents.add(new AnimationEvent(capsuleItemView8, 5, 350L, containerAnimatorViewHelper.stateAnimator.getAnimationFilter(), (CapsuleItemView) null, containerAnimatorViewHelper.ambientState.getReferStackView(), 64));
                }
                for (CapsuleItemView capsuleItemView9 : containerAnimatorViewHelper.animationViewSet[6]) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("generateAllAnimationEvents: ANIMATION_VIEW_SET_TYPE_STACK_SHOW_ITEM，", capsuleItemView9.getEntry().getKey(), "ContainerAnimatorViewHe-->", "SystemUi--Notification");
                    }
                    CapsuleItemView baseCard = containerAnimatorViewHelper.ambientState.getBaseCard();
                    if (baseCard != null) {
                        containerAnimatorViewHelper.animationEvents.add(new AnimationEvent(baseCard, 4, 350L, containerAnimatorViewHelper.stateAnimator.getAnimationFilter(), (CapsuleItemView) null, containerAnimatorViewHelper.ambientState.getReferStackView(), 64));
                    } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "ContainerAnimatorViewHe-->generateAllAnimationEvents: ANIMATION_VIEW_SET_TYPE_STACK_SHOW_ITEM，no baseCard,so skip");
                    }
                }
                containerAnimatorViewHelper.animationViewSet[5].clear();
                containerAnimatorViewHelper.animationViewSet[6].clear();
                if (!containerAnimatorViewHelper.commonAnimationTags.isEmpty()) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("generateAllAnimationEvents: commonAnimationTags，", CollectionsKt___CollectionsKt.joinToString$default(containerAnimatorViewHelper.commonAnimationTags, (CharSequence) null, (CharSequence) null, (CharSequence) null, (Function1) null, 63), "ContainerAnimatorViewHe-->", "SystemUi--Notification");
                    }
                    if (containerAnimatorViewHelper.hostView.getChildCount() > 0) {
                        Iterator<T> it = containerAnimatorViewHelper.commonAnimationTags.iterator();
                        while (it.hasNext()) {
                            containerAnimatorViewHelper.animationEvents.add(new AnimationEvent((CapsuleItemView) null, 7, 350L, containerAnimatorViewHelper.stateAnimator.getAnimationFilter(), (CapsuleItemView) null, (ExpandableView) null, (String) it.next()));
                        }
                    }
                    containerAnimatorViewHelper.commonAnimationTags.clear();
                }
                containerAnimatorViewHelper.ambientState.setReferStackView(null);
                containerAnimatorViewHelper.ambientState.setReferStackViewForImmersiveShowItem(null);
                containerAnimatorViewHelper.needsAnimation = false;
            }
            if (!containerAnimatorViewHelper.animationEvents.isEmpty() || containerAnimatorViewHelper.stateAnimator.isRunning()) {
                containerAnimatorViewHelper.setAnimationRunning(true);
                containerAnimatorViewHelper.stateAnimator.startAnimationForEvents(containerAnimatorViewHelper.animationEvents, 0L, containerAnimatorViewHelper.ambientState);
                containerAnimatorViewHelper.animationEvents.clear();
            } else {
                containerAnimatorViewHelper.applyCurrentState();
            }
        } else {
            containerAnimatorViewHelper.applyCurrentState();
        }
        CapsuleEarController earController = containerAnimatorViewHelper.ambientState.getEarController();
        WeakReference<CapsuleItemView> baseCard2 = earController.getBaseCard();
        if (baseCard2 != null && (capsuleItemView = baseCard2.get()) != null) {
            boolValueOf = Boolean.valueOf(capsuleItemView.isBindToStackView());
        }
        if (boolValueOf == null) {
            earController.setEarVisible(earController.getNeedShow());
        }
        Trace.endSection();
    }

    private final void setAnimationRunning(boolean z) {
        if (z != this.animationRunning) {
            this.animationRunning = z;
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0050  */
    public final void applyCurrentState() {
        int childCount = this.hostView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ExpandableView childAt = this.hostView.getChildAt(i);
            if ((childAt instanceof CapsuleItemView) && ((CapsuleItemView) childAt).isBindToStackView()) {
                CapsuleConfig.INSTANCE.getClass();
                if (CapsuleConfig.isLightOs) {
                    childAt.applyViewState();
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "ContainerAnimatorViewHe-->".concat("ignoreApplyCurrentState for bindToStackView:" + childAt));
                }
            } else {
                childAt.applyViewState();
            }
        }
        runAnimationFinishedRunnables();
        setAnimationRunning(false);
    }

    public final void generateAodHdrAnimation(boolean z) {
        this.ambientState.setAodState(z);
        requestChildrenUpdate("requestHdrTempMode:AODChange:" + z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void generateImmersiveAnimation(CapsuleItemView capsuleItemView, ExpandableView expandableView, boolean z, boolean z2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("generateImmersiveAnimation:: row = ", capsuleItemView.getKey(), ", enter = ", z2, "ContainerAnimatorViewHe-->"));
        }
        this.hostView.requestCancelTouchFeedback(false);
        if (!this.animationsEnabled || !z) {
            requestChildrenUpdate("generateImmersiveAnimation");
            return;
        }
        LockScreenAmbientState lockScreenAmbientState = this.ambientState;
        if (z2) {
            lockScreenAmbientState.setReferStackView(expandableView);
            this.animationViewSet[3].add(capsuleItemView);
            this.needsAnimation = true;
        } else {
            if (lockScreenAmbientState.getImmersiveCard() == null && capsuleItemView.getParent() == null) {
                return;
            }
            this.ambientState.setReferStackViewForImmersiveShowItem(expandableView);
            this.animationViewSet[4].add(capsuleItemView);
            this.needsAnimation = true;
        }
        requestChildrenUpdate("generateImmersiveAnimation:animation");
    }

    public final void generateStackAnimation(CapsuleItemView capsuleItemView, ExpandableView expandableView, boolean z, boolean z2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("ContainerAnimatorViewHe-->");
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("generateStackAnimation:: row = ", capsuleItemView != null ? capsuleItemView.getKey() : null, ",stack=", expandableView != null ? LockScreenNotificationKotlinExKt.getLogKey(expandableView) : null, ", isStack = ");
            sbM.append(z2);
            sb.append(sbM.toString());
            Log.d("SystemUi--Notification", sb.toString());
        }
        this.hostView.requestCancelTouchFeedback(false);
        if (!this.animationsEnabled || !z) {
            requestChildrenUpdate("generateStackAnimation");
            return;
        }
        if (!z2) {
            this.ambientState.setBaseCard(CapsuleViewExpandKtKt.highestPriorityCard(this.hostView));
        }
        this.ambientState.setReferStackView(expandableView);
        CapsuleItemView baseCard = this.ambientState.getBaseCard();
        if (baseCard != null) {
            HashSet<CapsuleItemView>[] hashSetArr = this.animationViewSet;
            if (z2) {
                hashSetArr[5].add(baseCard);
            } else {
                hashSetArr[6].add(baseCard);
            }
            this.needsAnimation = true;
        }
        requestChildrenUpdate("generateStackAnimation:animation");
    }

    public final void generateTouchFeedbackProgress(CapsuleItemView capsuleItemView, float f) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ContainerAnimatorViewHe-->".concat("generateTouchFeedbackProgress:: " + (capsuleItemView != null ? LockScreenNotificationKotlinExKt.getLogKey(capsuleItemView) : null) + ", progress = " + f));
        }
        if (capsuleItemView != null) {
            this.ambientState.setTouchView(new WeakReference<>(capsuleItemView));
        }
        if (this.ambientState.getTouchProgress() == f) {
            return;
        }
        if (f == 0.0f) {
            this.ambientState.setTouchView(new WeakReference<>(null));
        }
        this.ambientState.setTouchProgress(f);
        requestChildrenUpdate("generateTouchFeedbackAnimation:" + f);
    }

    public final LockScreenAmbientState getAmbientState() {
        return this.ambientState;
    }

    public final boolean getAnimationsEnabled() {
        return this.animationsEnabled;
    }

    public final float getBackScrollX(float f) {
        if (this.containerScrollAlgorithm.getVisibleChildren().size() <= 1) {
            return 0.0f;
        }
        float paddingBetweenElements = this.ambientState.getPaddingBetweenElements() + CapsuleConfig.INSTANCE.getItemWidthMultiCard();
        float f2 = -paddingBetweenElements;
        if (f < f2 / 4.0f) {
            return f2;
        }
        if (f > paddingBetweenElements / 4.0f) {
            return paddingBetweenElements;
        }
        return 0.0f;
    }

    public final int getCapsuleFinalTransY() {
        return (this.hostView.getHeight() - this.ambientState.getMarginBottom()) - this.ambientState.getItemHeight();
    }

    public final CapsuleItemView getChildAtPosition(float f, float f2) {
        Object next;
        CapsuleItemView baseCard = this.ambientState.getBaseCard();
        if (baseCard != null && (baseCard.isInTouchArea(f, f2) || isInEarArea(f, f2))) {
            return baseCard;
        }
        CapsuleItemView nextCard = this.ambientState.getNextCard();
        if (nextCard != null && (nextCard.isInTouchArea(f, f2) || isInEarArea(f, f2))) {
            return nextCard;
        }
        CapsuleItemView immersiveCard = this.ambientState.getImmersiveCard();
        if (immersiveCard != null && ((immersiveCard.isInTouchArea(f, f2) || isInEarArea(f, f2)) && ((ExpandableView) immersiveCard).mExpandableViewEx.getCapsuleProgress() > 0.5f)) {
            return immersiveCard;
        }
        Iterator<T> it = CapsuleViewExpandKtKt.getRemovingCapsuleChildren(this.hostView).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (((CapsuleItemView) next).isInTouchArea(f, f2)) {
                return (CapsuleItemView) next;
            }
        }
        next = null;
        return (CapsuleItemView) next;
    }

    public final StateFlow getChildCountFlow() {
        return this.childCountFlow;
    }

    public final ContainerAlgorithm getContainerScrollAlgorithm() {
        return this.containerScrollAlgorithm;
    }

    public final List<CapsuleItemView> getIgnoreViews() {
        return this.containerScrollAlgorithm.getIgnoreViews();
    }

    public final StateFlow getPreChildCountFlow() {
        return this.preChildCountFlow;
    }

    public final boolean getStateAnimRunning() {
        return this.stateAnimator.getItemAnimator().isRunning;
    }

    public final void initView() {
        this.containerScrollAlgorithm.initView(this.context);
        this.stateAnimator.initView(this.context);
    }

    public final boolean isCurrentlyAnimating() {
        return this.stateAnimator.isRunning();
    }

    public final boolean isInEarArea(float f, float f2) {
        if (this.hostView.getCapsuleEarController().getEarVisible()) {
            return this.hostView.getCapsuleEarController().getLeftEarView().isInTouchArea(f, f2) || this.hostView.getCapsuleEarController().getRightEarView().isInTouchArea(f, f2);
        }
        return false;
    }

    public final void onBeforeChildrenUpdate() {
        int height = this.hostView.getHeight();
        Integer num = this.lastContainerHeight;
        if (num != null && num.intValue() != height) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ContainerAnimatorViewHe-->".concat("containerHeightChange: " + this.lastContainerHeight + "  --> " + height));
            }
            Function1 function1 = this.onContainerHeightChangeListener;
            if (function1 != null) {
                function1.invoke(Integer.valueOf(height));
            }
        }
        this.lastContainerHeight = Integer.valueOf(height);
    }

    public final void onChildAnimationFinished() {
        setAnimationRunning(false);
        requestChildrenUpdate("onChildAnimationFinished");
        runAnimationFinishedRunnables();
        for (CapsuleItemView capsuleItemView : this.animationViewSet[2]) {
            capsuleItemView.removeFromTransientContainer();
            onViewRemovedAfterAnim(capsuleItemView);
        }
        this.animationViewSet[2].clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onViewAddedInternal(View view, ViewGroup viewGroup) {
        if (view instanceof CapsuleItemView) {
            this._preChildCountFlow.setValue(Integer.valueOf(this.hostView.getChildCount()));
            ILockScreenExtStateController iLockScreenExtStateController = this.lockScreenExtStateController;
            if (iLockScreenExtStateController == null) {
                iLockScreenExtStateController = null;
            }
            if (iLockScreenExtStateController.isStackState()) {
                requestChildrenUpdate("onViewAddedInternal:isStackState");
                return;
            }
            CapsuleItemView capsuleItemView = (CapsuleItemView) view;
            if (!capsuleItemView.getEntry().getAnimAdd()) {
                if (this.hostView.getAnimatorHelper().containerScrollAlgorithm.getVisibleChildren().size() != 1) {
                    requestChildrenUpdate("onViewAddedInternal:notNeedAnim");
                    return;
                }
                this.commonAnimationTags.add(AnimationEvent.COMMON_ANIMATION_CONTENT_WIDTH_CHANGED);
                this.needsAnimation = true;
                requestChildrenUpdate("onViewAddedInternal:withAnim");
                return;
            }
            ILockScreenExtStateController iLockScreenExtStateController2 = this.lockScreenExtStateController;
            if (iLockScreenExtStateController2 == null) {
                iLockScreenExtStateController2 = null;
            }
            if (iLockScreenExtStateController2.isImmersiveState()) {
                ILockScreenExtStateController iLockScreenExtStateController3 = this.lockScreenExtStateController;
                if (iLockScreenExtStateController3 == null) {
                    iLockScreenExtStateController3 = null;
                }
                Bundle extra = ((ILockScreenExtStateController.ExtStateEvent) iLockScreenExtStateController3.getExtStateEvent().getValue()).getExtra();
                if (Intrinsics.areEqual(extra != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(extra) : null, capsuleItemView.getKey()) && this.ambientState.getImmersiveCard() == null) {
                    this.ambientState.setImmersiveCard(capsuleItemView);
                    requestChildrenUpdate("onViewAddedInternal:alreadyImmersive:" + capsuleItemView.getKey());
                    return;
                }
            }
            if (!SequencesKt___SequencesKt.contains(ViewGroupKt.getChildren(this.hostView), view)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "ContainerAnimatorViewHe-->onViewAddedInternal:alreadyRemoved");
                    return;
                }
                return;
            }
            if (this.animationsEnabled && !this.ambientState.isStackState) {
                this.animationViewSet[0].add(view);
                this.needsAnimation = true;
                requestChildrenUpdate("onViewAddedInternal:animation");
                return;
            }
            requestChildrenUpdate("onViewAddedInternal:notKeyguard");
            LockScreenAmbientState lockScreenAmbientState = this.ambientState;
            lockScreenAmbientState.setLastBaseCard(lockScreenAmbientState.getBaseCard());
            this.ambientState.setBaseCard(capsuleItemView);
            CapsuleDataPinHandler capsuleDataPinHandler = CapsuleDataPinHandler.INSTANCE;
            capsuleDataPinHandler.setupPinCard(capsuleItemView, "onViewAddedInternal");
            capsuleDataPinHandler.startPinTimeout();
        }
    }

    public final void onViewRemovedAfterAnim(CapsuleItemView capsuleItemView) {
        capsuleItemView.onClearTransient();
        if (Intrinsics.areEqual(this.ambientState.getLastBaseCard(), capsuleItemView)) {
            this.ambientState.setLastBaseCard(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onViewRemovedInternal(View view, ViewGroup viewGroup) {
        boolean z = view instanceof CapsuleItemView;
        CapsuleDataPinHandler.INSTANCE.checkIfRemovePinCard(z ? (CapsuleItemView) view : null);
        this._preChildCountFlow.setValue(Integer.valueOf(this.hostView.getChildCount()));
        ILockScreenExtStateController iLockScreenExtStateController = this.lockScreenExtStateController;
        if (iLockScreenExtStateController == null) {
            iLockScreenExtStateController = null;
        }
        if (iLockScreenExtStateController.isImmersiveState() && Intrinsics.areEqual(view, this.ambientState.getImmersiveCard())) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ContainerAnimatorViewHe-->onViewRemovedInternal: is immersive");
            }
            this.ambientState.setImmersiveCard(null);
            requestChildrenUpdate("onViewRemovedInternal:NoAnimation:removeImmersiveCard");
            this.hostView.getContainerControllerImpl().onImmersiveViewRemoved();
            return;
        }
        if (!this.animationsEnabled || !z || this.ambientState.isStackState) {
            requestChildrenUpdate("onViewRemovedInternal:NoAnimation:isStackState");
            if (z) {
                onViewRemovedAfterAnim((CapsuleItemView) view);
                return;
            }
            return;
        }
        if (this.animationViewSet[0].contains(view)) {
            this.animationViewSet[0].remove(view);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ContainerAnimatorViewHe-->onViewRemovedInternal:NoAnimation:tryAddView");
            }
            onViewRemovedAfterAnim((CapsuleItemView) view);
            return;
        }
        if (!Intrinsics.areEqual(this.ambientState.getBaseCard(), view)) {
            if (CapsuleViewExpandKtKt.showOnlyOneCard(this.hostView)) {
                this.commonAnimationTags.add(AnimationEvent.COMMON_ANIMATION_CONTENT_WIDTH_CHANGED);
                this.needsAnimation = true;
            }
            requestChildrenUpdate("onViewRemovedInternal:animation:NotBaseCard");
            onViewRemovedAfterAnim((CapsuleItemView) view);
            return;
        }
        if (view.getVisibility() != 0) {
            requestChildrenUpdate("onViewRemovedInternal:NoAnimation:Invisible");
            onViewRemovedAfterAnim((CapsuleItemView) view);
            return;
        }
        this.animationViewSet[2].add(view);
        this.animationViewSet[1].add(view);
        viewGroup.addTransientView(view, 0);
        ((CapsuleItemView) view).setTransientContainer(viewGroup);
        this.needsAnimation = true;
        requestChildrenUpdate("onViewRemovedInternal:animation");
    }

    public final boolean readyToAnimAddORemove(CapsuleItemView capsuleItemView) {
        if (this.animationViewSet[0].isEmpty() || !Intrinsics.areEqual(this.ambientState.getLastBaseCard(), capsuleItemView)) {
            return (!this.animationViewSet[1].isEmpty() && Intrinsics.areEqual(this.ambientState.getBaseCard(), capsuleItemView)) || CollectionsKt___CollectionsKt.toList(this.animationViewSet[0]).contains(capsuleItemView) || CollectionsKt___CollectionsKt.toList(this.animationViewSet[1]).contains(capsuleItemView) || this.stateAnimator.getItemAnimator().readyToAnimAddORemove(capsuleItemView);
        }
        return true;
    }

    public final void requestChildrenUpdate(String str) {
        if (this.childrenUpdateRequested) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("requestChildrenUpdate: ,reason=", str, "ContainerAnimatorViewHe-->", "SystemUi--Notification");
        }
        if (CapsuleDataPinHandler.INSTANCE.getNeedKeepAodDisplayDoze().get()) {
            SmoothTransitionController.Companion.getInstance(this.context).requestScreenStateForSysUiComponent("requestChildrenUpdate:" + str, 1000);
        }
        this.hostView.getViewTreeObserver().addOnPreDrawListener(this.childrenUpdater);
        this.childrenUpdateRequested = true;
        this.hostView.invalidate();
    }

    public final void resetDate() {
        this.ambientState.reset();
        requestChildrenUpdate("resetDate");
    }

    public final void runAfterAnimationFinished(Runnable runnable) {
        this.animationFinishedRunnables.add(runnable);
    }

    public final void runAnimationFinishedRunnables() {
        this._childCountFlow.setValue(Integer.valueOf(this.hostView.getChildCount()));
        Iterator<Runnable> it = this.animationFinishedRunnables.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        this.animationFinishedRunnables.clear();
    }

    public final void setAnimationsEnabled(boolean z) {
        this.animationsEnabled = z;
    }

    public final void setLockScreenExtStateController(ILockScreenExtStateController iLockScreenExtStateController) {
        this.lockScreenExtStateController = iLockScreenExtStateController;
    }

    public final void setupContainerHeightChangeListener(Function1 function1) {
        this.onContainerHeightChangeListener = function1;
    }

    public final void updateImmersiveCard(CapsuleItemView capsuleItemView, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("ContainerAnimatorViewHe-->");
            CapsuleItemView immersiveCard = this.ambientState.getImmersiveCard();
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("updateImmersiveCard: ", immersiveCard != null ? immersiveCard.getKey() : null, " -> ", capsuleItemView != null ? capsuleItemView.getKey() : null, ", reason = ");
            sbM.append(str);
            sb.append(sbM.toString());
            Log.d("SystemUi--Notification", sb.toString());
        }
        this.ambientState.setImmersiveCard(capsuleItemView);
        requestChildrenUpdate("updateImmersiveCard");
    }

    public final void updatePosition(int i) {
        if (this.ambientState.getMarginBottom() != i) {
            this.ambientState.setMarginBottom(i);
            if (!this.animationsEnabled) {
                requestChildrenUpdate("updatePosition：" + i);
                return;
            }
            this.commonAnimationTags.add(AnimationEvent.COMMON_ANIMATION_BOTTOM_CHANGED);
            this.needsAnimation = true;
            requestChildrenUpdate("updatePosition：" + i + " anim");
            ILockScreenExtStateController iLockScreenExtStateController = this.lockScreenExtStateController;
            if (iLockScreenExtStateController == null) {
                return;
            }
            int newState = ((ILockScreenExtStateController.ExtStateEvent) iLockScreenExtStateController.getExtStateEvent().getValue()).getNewState();
            if (this.hostView.isBindToStackView()) {
                ILockScreenExtStateController.Companion.getClass();
                if (newState == 1) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "ContainerAnimatorViewHe-->requestNSSLUpdateWhenPositionChange");
                    }
                    this.hostView.getContainerControllerImpl().requestNsslChildrenUpdateWithAnimation();
                }
            }
        }
    }

    public final boolean readyToAnimAddORemove() {
        return (this.animationViewSet[0].isEmpty() && this.animationViewSet[1].isEmpty()) ? false : true;
    }
}
