package com.oplus.systemui.notification.stackednotification;

import android.content.Context;
import android.util.IndentingPrintWriter;
import android.util.Property;
import android.view.View;
import androidx.collection.ArraySet;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.notification.stackednotification.StackedNotificationEx;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.android.systemui.statusbar.notification.footer.ui.view.FooterView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewProxy;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.SectionHeaderView;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.util.kotlin.ActivatableFlowDumperImpl;
import com.android.systemui.util.kotlin.FlowDumper;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.lockscreen.statemanager.FocusCard;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationRectManager;
import com.oplus.systemui.notification.lockscreen.statemanager.SeedlingFocusCard;
import com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayInteractor;
import com.oplus.systemui.notification.stackednotification.ruler.KeyguardNotificationStackedRuler;
import com.oplus.systemui.notification.stackednotification.ruler.ShadeNotificationStackedRuler;
import com.oplus.systemui.notification.utlils.DeviceStateUtils;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplus.systemui.statusbar.notification.stack.OplusExpandableViewStateExImpl;
import com.oplus.systemui.statusbar.util.KeyguardNotificationUploadUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStackedNotificationExImpl extends StackedNotificationEx implements FlowDumper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusStackedNotificationExImpl";
    public final /* synthetic */ ActivatableFlowDumperImpl $$delegate_0;
    private Boolean cacheIsStackedNotification;
    private final Lazy capsuleCardControl;
    private final Lazy capsuleContainer;
    private final Context context;
    private final Lazy immersiveCardControl;
    private final KeyguardNotificationStackedRuler keyguardNotificationStackedRuler;
    private final Lazy lockScreenExtStateController;
    private final Lazy lockScreenNotificationRectManager;
    private final Lazy nsslExt;
    private final OplusNotificationOverlayInteractor overlayInteractor;
    private final ShadeNotificationStackedRuler shadeNotificationStackedRuler;
    private final OplusLockscreenShadeTransitionControllerEx shadeTransitionControllerEx;
    private final Lazy stackCardControl;
    private final SysuiStatusBarStateController statusBarStateController;
    public int lastStatusBarState = -1;
    private final MutableStateFlow _isAnyNotificationVisible = dumpValue(StateFlowKt.MutableStateFlow(Boolean.TRUE), "isAnyNotificationVisible");
    private final kotlin.Lazy tmpExpandableViews$delegate = LazyKt__LazyJVMKt.lazy(new OplusStackedNotificationExImpl$$ExternalSyntheticLambda1());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusStackedNotificationExImpl(Context context, SysuiStatusBarStateController sysuiStatusBarStateController, KeyguardNotificationStackedRuler keyguardNotificationStackedRuler, ShadeNotificationStackedRuler shadeNotificationStackedRuler, OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, DumpManager dumpManager, Lazy lazy7) {
        this.$$delegate_0 = new ActivatableFlowDumperImpl(dumpManager, TAG);
        this.context = context;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.keyguardNotificationStackedRuler = keyguardNotificationStackedRuler;
        this.shadeNotificationStackedRuler = shadeNotificationStackedRuler;
        this.shadeTransitionControllerEx = oplusLockscreenShadeTransitionControllerEx;
        this.overlayInteractor = oplusNotificationOverlayInteractor;
        this.lockScreenExtStateController = lazy;
        this.capsuleCardControl = lazy2;
        this.stackCardControl = lazy3;
        this.immersiveCardControl = lazy4;
        this.nsslExt = lazy5;
        this.capsuleContainer = lazy6;
        this.lockScreenNotificationRectManager = lazy7;
    }

    private final NotificationStackedRuler getCurrentStackRuler() {
        return this.shadeTransitionControllerEx.isOnOrGoingKeyguard(this.statusBarStateController.getState() == 1) ? this.keyguardNotificationStackedRuler : this.shadeNotificationStackedRuler;
    }

    private final List<ExpandableView> getTmpExpandableViews() {
        return (List) this.tmpExpandableViews$delegate.getValue();
    }

    public void applyOverlayInfo(AnimationProperties animationProperties) {
        if (this.lastStatusBarState == 0 && this.statusBarStateController.getState() == 1) {
            OplusNotificationOverlayInteractor.applyOverlayInfo$default(this.overlayInteractor, 0L, 1);
        } else {
            OplusNotificationOverlayInteractor.applyOverlayInfo$default(this.overlayInteractor, animationProperties != null ? animationProperties.delay : 0L, 2);
        }
        this.lastStatusBarState = this.statusBarStateController.getState();
    }

    public void clearNotificationStackedState(ExpandableView expandableView) {
        List attachedChildren;
        OplusStackedNotificationExImpl$$ExternalSyntheticLambda0 oplusStackedNotificationExImpl$$ExternalSyntheticLambda0 = new OplusStackedNotificationExImpl$$ExternalSyntheticLambda0();
        oplusStackedNotificationExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        if ((expandableView instanceof ExpandableNotificationRow) && (attachedChildren = ((ExpandableNotificationRow) expandableView).getAttachedChildren()) != null) {
            Iterator it = attachedChildren.iterator();
            while (it.hasNext()) {
                oplusStackedNotificationExImpl$$ExternalSyntheticLambda0.invoke(it.next());
            }
        }
        oplusStackedNotificationExImpl$$ExternalSyntheticLambda0.invoke(expandableView);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0030 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:20:0x0032  */
    /* JADX WARN: Code duplicated, block: B:25:0x0040  */
    /* JADX WARN: Code duplicated, block: B:29:0x0048 A[RETURN] */
    public boolean disallowSwipeExpandableView(View view) {
        ExpandableView expandableView;
        float immersiveProgress;
        OplusExpandableViewEx oplusExpandableViewEx;
        boolean z = view instanceof ExpandableView;
        if (!isKgNotificationSummary(z ? (ExpandableView) view : null, true)) {
            if (view != null && (view instanceof ExpandableNotificationRow)) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
                if (!expandableNotificationRow.mIsSummaryWithChildren || !expandableNotificationRow.isGroupExpanded() || expandableNotificationRow.getAttachedChildren().size() <= 1) {
                    expandableView = z ? (ExpandableView) view : null;
                    if (expandableView != null || (oplusExpandableViewEx = expandableView.mExpandableViewEx) == null) {
                        immersiveProgress = 0.0f;
                    } else {
                        immersiveProgress = oplusExpandableViewEx.getImmersiveProgress();
                    }
                    if (immersiveProgress > 0.5f) {
                        return false;
                    }
                }
            } else {
                if (z) {
                }
                if (expandableView != null) {
                    immersiveProgress = 0.0f;
                } else {
                    immersiveProgress = 0.0f;
                }
                if (immersiveProgress > 0.5f) {
                    return false;
                }
            }
        }
        return true;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        this.$$delegate_0.dump(printWriter, strArr);
    }

    public void dumpFlows(IndentingPrintWriter indentingPrintWriter) {
        this.$$delegate_0.dumpFlows(indentingPrintWriter);
    }

    public <T, F extends SharedFlow> F dumpReplayCache(F f, String str) {
        return (F) this.$$delegate_0.dumpReplayCache(f, str);
    }

    public <T, F extends StateFlow> F dumpValue(F f, String str) {
        return (F) this.$$delegate_0.dumpValue(f, str);
    }

    public <T> Flow dumpWhileCollecting(Flow flow, String str) {
        return this.$$delegate_0.dumpWhileCollecting(flow, str);
    }

    public final Context getContext() {
        return this.context;
    }

    public AnimationFilter getFilter() {
        AnimationFilter animationFilter = new AnimationFilter();
        animationFilter.animateAlpha = true;
        animationFilter.animateHideSensitive = true;
        animationFilter.animateHeight = true;
        animationFilter.animateTopInset = true;
        animationFilter.animateY = true;
        animationFilter.animateZ = true;
        animationFilter.animateScale();
        OplusExpandableViewStateExImpl.Companion companion = OplusExpandableViewStateExImpl.Companion;
        Property property = companion.getCLIP_WIDTH_PROPERTY().getProperty();
        ArraySet arraySet = animationFilter.mAnimatedProperties;
        arraySet.add(property);
        arraySet.add(companion.getBLUR_RADIO_PROPERTY().getProperty());
        arraySet.add(companion.getANIMATION_CORNER_RADIUS_PROPERTY().getProperty());
        arraySet.add(companion.getANIMATION_CAPSULE_PROGRESS_PROPERTY().getProperty());
        arraySet.add(companion.getANIMATION_IMMERSIVE_PROGRESS_PROPERTY().getProperty());
        animationFilter.hasDelays = true;
        return animationFilter;
    }

    public StateFlow isAnyNotificationVisibleForClock() {
        return this._isAnyNotificationVisible;
    }

    public boolean isKgNotificationSummary(ExpandableView expandableView, boolean z) {
        if (expandableView == null) {
            return false;
        }
        return this.overlayInteractor.isOverlayShowing(expandableView, z);
    }

    public final boolean isStackedNotification$1() {
        Boolean bool = this.cacheIsStackedNotification;
        return bool != null ? bool.booleanValue() : getCurrentStackRuler().getCurrentIsStackedNotification();
    }

    public boolean markAnimateWhenNotificationRemoved(ExpandableView expandableView) {
        return (expandableView == null || expandableView.getVisibility() == 8 || this.statusBarStateController.getCurrentOrUpcomingState() != 1) ? false : true;
    }

    public void prepareAddOrRemoveAnimationForOverlay(View view, View view2, boolean z) {
        getCurrentStackRuler().prepareAddOrRemoveAnimationForOverlay(view, view2, z);
    }

    /* JADX WARN: Code duplicated, block: B:61:0x010d  */
    /* JADX WARN: Code duplicated, block: B:92:0x018d  */
    public void updateAppearanceForStackedNotification(Sequence sequence, Sequence sequence2, NotificationShelf notificationShelf) {
        float f;
        View proxyView;
        int iNextIndex;
        boolean zIsLockScreenExtStateAnimRunning = ((CapsuleCardControl) this.capsuleCardControl.get()).isLockScreenExtStateAnimRunning();
        ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType animateType = ((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getAnimateType();
        FocusCard focusCard = ((ImmersiveCardControl) this.immersiveCardControl.get()).getFocusCard();
        SeedlingFocusCard seedlingFocusCard = focusCard instanceof SeedlingFocusCard ? (SeedlingFocusCard) focusCard : null;
        OplusCustomRow stackRow = seedlingFocusCard != null ? seedlingFocusCard.getStackRow() : null;
        animateType.getClass();
        int i = 0;
        boolean z = animateType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.SINKING_TO_CAPSULE || animateType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.STACK_TO_CAPSULE;
        boolean z2 = this.statusBarStateController.getState() == 1;
        boolean z3 = !((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isStackState() && z2;
        List<ExpandableView> tmpExpandableViews = getTmpExpandableViews();
        CollectionsKt__MutableCollectionsKt.addAll(tmpExpandableViews, sequence);
        if (sequence2 != null) {
            Iterator it = sequence2.iterator();
            while (it.hasNext()) {
                ExpandableView expandableView = (ExpandableView) it.next();
                if (expandableView != null && expandableView.isVisibleToUser()) {
                    ListIterator<ExpandableView> listIterator = tmpExpandableViews.listIterator(tmpExpandableViews.size());
                    while (true) {
                        if (!listIterator.hasPrevious()) {
                            iNextIndex = -1;
                            break;
                        }
                        ExpandableView expandableViewPrevious = listIterator.previous();
                        if (expandableViewPrevious.isVisibleToUser() && expandableViewPrevious.getElevation() > expandableView.getElevation()) {
                            iNextIndex = listIterator.nextIndex();
                            break;
                        }
                    }
                    tmpExpandableViews.add(RangesKt___RangesKt.coerceIn(iNextIndex + 1, 0, tmpExpandableViews.size()), expandableView);
                }
            }
        }
        boolean zIsOnOrGoingOrLeavingNtfCenter = this.shadeTransitionControllerEx.isOnOrGoingOrLeavingNtfCenter();
        float f2 = 0.0f;
        int i2 = 0;
        float translationY = 0.0f;
        for (Object obj : tmpExpandableViews) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            SectionHeaderView sectionHeaderView = (ExpandableView) obj;
            if (sectionHeaderView instanceof NotificationShelf) {
                f = f2;
            } else {
                if (z2 && !zIsOnOrGoingOrLeavingNtfCenter) {
                    sectionHeaderView.setClipBottomAmount(i);
                }
                if ((z3 && !zIsLockScreenExtStateAnimRunning) || (Intrinsics.areEqual(stackRow, sectionHeaderView) && zIsLockScreenExtStateAnimRunning)) {
                    f = f2;
                } else if (sectionHeaderView instanceof SectionHeaderView) {
                    sectionHeaderView.setElevation(-3.4028235E38f);
                    f = f2;
                } else if (sectionHeaderView.getTranslationY() != f2 && sectionHeaderView.getVisibility() == 0 && sectionHeaderView.getTransientContainer() == null) {
                    sectionHeaderView.setElevation(isStackedNotification$1() ? f2 - (i2 * 8) : f2);
                    if (sectionHeaderView instanceof ExpandableNotificationRow) {
                        ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) sectionHeaderView;
                        if (expandableNotificationRow.mIsSummaryWithChildren) {
                            f = f2;
                            int i4 = i;
                            for (Object obj2 : expandableNotificationRow.getAttachedChildren()) {
                                int i5 = i4 + 1;
                                if (i4 < 0) {
                                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                                    throw null;
                                }
                                ((ExpandableNotificationRow) obj2).setElevation(isStackedNotification$1() ? expandableNotificationRow.getElevation() - i4 : f);
                                i4 = i5;
                            }
                        } else {
                            f = f2;
                        }
                    } else {
                        f = f2;
                    }
                    sectionHeaderView.setPivotY(sectionHeaderView.getClipHeight() * 0.5f);
                    sectionHeaderView.setPivotX(sectionHeaderView.getMeasuredWidth() * 0.5f);
                    if (translationY > sectionHeaderView.getTranslationY()) {
                        int translationY2 = (int) (translationY - sectionHeaderView.getTranslationY());
                        int clipTopAmount = sectionHeaderView.getClipTopAmount();
                        if (translationY2 < clipTopAmount) {
                            translationY2 = clipTopAmount;
                        }
                        sectionHeaderView.setClipTopAmount(translationY2);
                        sectionHeaderView.getViewState().clipTopAmount = sectionHeaderView.getClipTopAmount();
                    }
                    translationY = sectionHeaderView.getTranslationY();
                } else {
                    f = f2;
                    if (sectionHeaderView.getTranslationY() != f && sectionHeaderView.getVisibility() == 4 && sectionHeaderView.getTransientContainer() == null && zIsLockScreenExtStateAnimRunning) {
                        OplusExpandableViewProxy proxy = ((ExpandableView) sectionHeaderView).mExpandableViewEx.getProxy();
                        if (((proxy == null || (proxyView = proxy.getProxyView()) == null) ? false : Intrinsics.areEqual(((NotificationCapsuleContainer) this.capsuleContainer.get()).getBaseCard(), proxyView)) && z) {
                            translationY = sectionHeaderView.getTranslationY();
                        }
                    }
                }
            }
            f2 = f;
            i2 = i3;
            i = 0;
        }
        float f3 = f2;
        if (z2 && !z3 && ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isTopStackState() && this.shadeTransitionControllerEx.isOnOrGoingKeyguard(true) && tmpExpandableViews != null) {
            float stackedBottomBorder = ((NotificationStackScrollLayoutExtImpl) this.nsslExt.get()).getStackedBottomBorder();
            int size = tmpExpandableViews.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i6 = size - 1;
                    ExpandableNotificationRow expandableNotificationRow2 = (ExpandableView) tmpExpandableViews.get(size);
                    if (!(expandableNotificationRow2 instanceof NotificationShelf) && (expandableNotificationRow2 instanceof ExpandableNotificationRow) && expandableNotificationRow2.getVisibility() == 0) {
                        ExpandableNotificationRow expandableNotificationRow3 = expandableNotificationRow2;
                        if (expandableNotificationRow3.mIsSummaryWithChildren || expandableNotificationRow3.isChildInGroup() || !expandableNotificationRow3.mHasUserChangedExpansion || (!expandableNotificationRow3.isExpanded(false) && !expandableNotificationRow3.mExpandAnimationRunning && !ViewState.isAnimating(expandableNotificationRow3, ExpandableView.HEIGHT_PROPERTY))) {
                            break;
                            break;
                            break;
                            break;
                        }
                        ExpandableViewState viewState = expandableNotificationRow3.getViewState();
                        OplusExpandableViewStateEx oplusExpandableViewStateEx = viewState.mExpandableViewStateEx;
                        int translationY3 = (int) (((oplusExpandableViewStateEx != null ? oplusExpandableViewStateEx.getNotificationStackedProgress() : f3) > f3 ? expandableNotificationRow3.getTranslationY() + viewState.height : expandableNotificationRow3.getActualHeight() + expandableNotificationRow3.getTranslationY()) - stackedBottomBorder);
                        int i7 = translationY3 < 0 ? 0 : translationY3;
                        if (i7 > 0 && !((LockScreenNotificationRectManager) this.lockScreenNotificationRectManager.get()).getRectMayChangeNextFrame() && this.shadeTransitionControllerEx.getNotificationDragAmount() == f3) {
                            expandableNotificationRow3.setClipBottomAmount(i7);
                            expandableNotificationRow3.getViewState().clipBottomAmount = i7;
                            break;
                        } else {
                            break;
                            break;
                            break;
                        }
                    }
                    if (i6 < 0) {
                        break;
                    } else {
                        size = i6;
                    }
                }
            }
        }
        getTmpExpandableViews().clear();
    }

    public void updateShelfStateForStackedNotification(NotificationShelf notificationShelf, AmbientState ambientState, List<? extends ExpandableView> list) {
        if (!isStackedNotification$1()) {
            Context context = this.context;
            if (!DeviceStateUtils.isLandInSmallScreen(context, DeviceUtil.isLandscape(context))) {
                return;
            }
        }
        notificationShelf.getViewState().setYTranslation(getCurrentStackRuler().shelfPosition(ambientState), "OplusStackedNotificationExImpl.updateShelfStateForStackedNotification");
        if (isStackedNotification$1()) {
            return;
        }
        for (ExpandableView expandableView : list) {
            if (!(expandableView instanceof FooterView) && !(expandableView instanceof EmptyShadeView) && !Intrinsics.areEqual(expandableView, ambientState.getTrackedHeadsUpRow()) && ambientState.mExpansionChanging && notificationShelf.getViewState().getYTranslation() <= expandableView.getViewState().getYTranslation()) {
                ((ViewState) expandableView.getViewState()).hidden = (expandableView.isExpandAnimationRunning() || expandableView.hasExpandingChild()) ? false : true;
            }
        }
    }

    public void updateStackedNotification(List<? extends ExpandableView> list, AmbientState ambientState, float f) {
        this.cacheIsStackedNotification = Boolean.valueOf(getCurrentStackRuler().getCurrentIsStackedNotification());
        KeyguardNotificationUploadUtil companion = KeyguardNotificationUploadUtil.Companion.getInstance();
        list.size();
        companion.getClass();
        List<? extends ExpandableView> list2 = list;
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            clearNotificationStackedState((ExpandableView) it.next());
        }
        this.overlayInteractor.setOverlayInfo(null, null);
        getCurrentStackRuler().updateStackedState(list, ambientState, f);
        MutableStateFlow mutableStateFlow = this._isAnyNotificationVisible;
        boolean z = false;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            for (ExpandableView expandableView : list2) {
                if (!((ViewState) expandableView.getViewState()).hidden && !(expandableView instanceof EmptyShadeView) && !(expandableView instanceof NotificationShelf)) {
                    z = true;
                    break;
                }
            }
        }
        mutableStateFlow.setValue(Boolean.valueOf(z));
        this.cacheIsStackedNotification = null;
    }

    public void updateStackedStateForChildren(ExpandableNotificationRow expandableNotificationRow, ExpandableViewState expandableViewState, ExpandableViewState expandableViewState2, int i) {
        OplusExpandableViewStateEx oplusExpandableViewStateEx;
        OplusExpandableViewStateEx oplusExpandableViewStateEx2;
        if (expandableViewState2 == null || (oplusExpandableViewStateEx = expandableViewState2.mExpandableViewStateEx) == null) {
            return;
        }
        float notificationStackedProgress = oplusExpandableViewStateEx.getNotificationStackedProgress();
        if (expandableViewState == null || (oplusExpandableViewStateEx2 = expandableViewState.mExpandableViewStateEx) == null) {
            return;
        }
        oplusExpandableViewStateEx2.setNotificationStackedProgress(notificationStackedProgress);
    }
}
