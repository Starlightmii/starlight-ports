package com.oplus.systemui.panelanimation.notification;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.panelanimation.notification.NotificationRowShowAnimationEx;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.animation.AlphaState;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateExKt;
import com.android.systemui.statusbar.notification.stack.SectionHeaderView;
import com.android.systemui.statusbar.notification.stack.StackScrollAlgorithm;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplus.systemui.notification.row.OplusShelfEx;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.separate.OplusPanelViewPagerController;
import com.oplus.systemui.shade.NotificationPanelSeparateAnimationManager;
import com.oplus.systemui.shade.NotificationPanelSeparateAnimationManagerImpl;
import com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationRowShowAnimationExImpl extends NotificationRowShowAnimationEx implements OplusNotificationEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationPullDownAnimationExImpl";
    private static final String TAG_FOR_SHOW_ANIMATION = "tag_for_show_animation";
    private Boolean cacheShouldSkipApplyViewState;
    public int currentAnimatingNotificationIndex;
    private final HeadsUpManager headsUpManager;
    public boolean isLastHeadsUpPined;
    public boolean isShadeExpanded;
    public boolean lastIsShadeExpanded;
    private final LogBuffer logBuffer;
    private final Lazy notificationPanelViewController;
    private final Lazy notificationStackScrollLayoutExtImpl;
    private final PanelAnimationEx panelAnimationEx;
    private final Lazy panelViewPagerController;
    private final SysuiStatusBarStateController statusBarStateController;
    private final NotificationPanelSeparateAnimationManager appearAnimationManager = NotificationPanelSeparateAnimationManager.Companion.create(true);
    private final kotlin.Lazy levelPositionAlgorithm$delegate = LazyKt__LazyJVMKt.lazy(new OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda1());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$y7FPPIW_sER3LVhYC1RbCZk0Tok(OplusNotificationRowShowAnimationExImpl oplusNotificationRowShowAnimationExImpl, ExpandableView expandableView, float f) {
        expandableView.setTag(2131365568, Float.valueOf(f));
        Lazy lazy = oplusNotificationRowShowAnimationExImpl.notificationStackScrollLayoutExtImpl;
        if (f == 0.0f) {
            NotificationStackScrollLayout notificationStackScrollLayoutM462getView = ((NotificationStackScrollLayoutExtImpl) lazy.get()).getView();
            if (notificationStackScrollLayoutM462getView != null) {
                getUpdateNegSpacing.1.1 r4 = new getUpdateNegSpacing.1.1();
                r4.this$0 = oplusNotificationRowShowAnimationExImpl;
                VarHandle.storeStoreFence();
                notificationStackScrollLayoutM462getView.postOnAnimation(r4);
            }
        } else {
            NotificationStackScrollLayout notificationStackScrollLayoutM462getView2 = ((NotificationStackScrollLayoutExtImpl) lazy.get()).getView();
            if (notificationStackScrollLayoutM462getView2 != null) {
                notificationStackScrollLayoutM462getView2.requestChildrenUpdate$1();
            }
        }
        return Unit.INSTANCE;
    }

    public OplusNotificationRowShowAnimationExImpl(PanelAnimationEx panelAnimationEx, HeadsUpManager headsUpManager, SysuiStatusBarStateController sysuiStatusBarStateController, Lazy lazy, Lazy lazy2, Lazy lazy3, LogBuffer logBuffer) {
        this.panelAnimationEx = panelAnimationEx;
        this.headsUpManager = headsUpManager;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.panelViewPagerController = lazy;
        this.notificationStackScrollLayoutExtImpl = lazy2;
        this.notificationPanelViewController = lazy3;
        this.logBuffer = logBuffer;
    }

    public static Pair getCurrentAlphaAndScale(ExpandableView expandableView) {
        RowAnimationStateEx rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableView);
        if (rowAnimationStateEx == null) {
            return new Pair(Float.valueOf(AlphaState.Companion.getINVALID().getAlpha()), Float.valueOf(ScaleState.Companion.getINVALID().getScaleX()));
        }
        RowAnimationStateExImpl rowAnimationStateExImpl = (RowAnimationStateExImpl) rowAnimationStateEx;
        return new Pair(Float.valueOf(rowAnimationStateExImpl.getRowAlpha(TAG_FOR_SHOW_ANIMATION).getAlpha()), Float.valueOf(rowAnimationStateExImpl.getRowScale(TAG_FOR_SHOW_ANIMATION).getScaleX()));
    }

    private final boolean getDisableRowShowHideAnimation() {
        return ScrimUtil.isBAnimLevel() || ScrimUtil.isBPAnimLevel();
    }

    private final LevelPositionAlgorithm getLevelPositionAlgorithm() {
        return (LevelPositionAlgorithm) this.levelPositionAlgorithm$delegate.getValue();
    }

    public static float getNegSpacing(View view) {
        Object tag = view.getTag(2131365568);
        Float f = tag instanceof Float ? (Float) tag : null;
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    public static boolean isFirstShow(ExpandableView expandableView) {
        Object tag = expandableView.getTag(2131365558);
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private final void setShadeExpanded(boolean z) {
        if (z != this.isShadeExpanded && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("isShadeExpanded: ", ", lastIsShadeExpanded field=", "OplusNotificationPullDownAnimationExImpl-->", z, this.isShadeExpanded));
        }
        this.lastIsShadeExpanded = this.isShadeExpanded;
        this.isShadeExpanded = z;
    }

    public void afterResetViewStates(AmbientState ambientState) {
        this.cacheShouldSkipApplyViewState = null;
    }

    public Pair<Integer, Integer> calculateStackHeightAndTranslationY(float f) {
        return new Pair<>(Integer.valueOf((int) f), 0);
    }

    public boolean canSkipAlphaScaleUpdateDuringScroll(AmbientState ambientState, View view) {
        return this.isShadeExpanded && this.lastIsShadeExpanded && !ambientState.mExpansionChanging && !ambientState.mIsSwipingUp && !((OplusPanelViewPagerController) this.panelViewPagerController.get()).isQsToNotificationScroll() && this.panelAnimationEx.isElementShowing() && !this.panelAnimationEx.isRunningAnimation() && view.getAlpha() == 1.0f && view.getTransitionAlpha() == 1.0f;
    }

    public void clearShowAnimationState(ExpandableView expandableView) {
        RowAnimationStateEx rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableView);
        if (rowAnimationStateEx != null) {
            RowAnimationStateExImpl rowAnimationStateExImpl = (RowAnimationStateExImpl) rowAnimationStateEx;
            rowAnimationStateExImpl.requestRowScale(TAG_FOR_SHOW_ANIMATION, ScaleState.Companion.getINVALID());
            rowAnimationStateExImpl.requestRowAlpha(TAG_FOR_SHOW_ANIMATION, AlphaState.Companion.getINVALID());
        }
    }

    public final void expandedNoAnimation() {
        if (getUseLayeredAnimation()) {
            setShadeExpanded(true);
        }
    }

    public final LogBuffer getLogBuffer() {
        return this.logBuffer;
    }

    public final float getNotificationAlpha(float f, float f2) {
        if (this.statusBarStateController.getState() == 0) {
            return f2 > 0.0f ? 1.0f : 0.0f;
        }
        return f;
    }

    public boolean getUseLayeredAnimation() {
        return FeatureOption.getUseLayeredPanelAnimation();
    }

    public float getViewNegativeExpandOffset(View view) {
        if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
            return getNegSpacing(view);
        }
        return 0.0f;
    }

    public boolean isSpacingAnimationRunning(View view) {
        return ((NotificationPanelSeparateAnimationManagerImpl) this.appearAnimationManager).isSpacingAnimationRunning(view);
    }

    public void onResetViewStates(AmbientState ambientState, ViewGroup viewGroup) {
        if (getUseLayeredAnimation()) {
            this.cacheShouldSkipApplyViewState = Boolean.valueOf(shouldSkipApplyViewState());
            boolean z = this.isShadeExpanded;
            boolean z2 = ambientState.mShadeExpanded;
            boolean z3 = true;
            boolean z4 = z != z2;
            setShadeExpanded(z2);
            NotificationStackScrollLayout notificationStackScrollLayout = viewGroup instanceof NotificationStackScrollLayout ? (NotificationStackScrollLayout) viewGroup : null;
            boolean z5 = notificationStackScrollLayout != null && notificationStackScrollLayout.mShouldUseSplitNotificationShade;
            int index = (z5 || QSFragmentHelper.getInstance().mConverFraction == 1.0f) ? 0 : getLevelPositionAlgorithm().getIndex(0, 1);
            if (!z4 && index == this.currentAnimatingNotificationIndex) {
                z3 = false;
            }
            this.currentAnimatingNotificationIndex = index;
            if (z3 && LogUtil.panicEnabled) {
                LogBuffer logBuffer = this.logBuffer;
                LogLevel logLevel = LogLevel.DEBUG;
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onResetViewStates, lastIsShadeExpanded=", ", isShadeExpanded=", ", isUsingSplitNotificationShade=", this.lastIsShadeExpanded, this.isShadeExpanded);
                sbM.append(z5);
                sbM.append(", currentAnimatingNotificationIndex=");
                sbM.append(index);
                logBuffer.log(TAG, logLevel, sbM.toString(), (Throwable) null);
            }
            if (getDisableRowShowHideAnimation() && !this.panelAnimationEx.isElementShowing() && this.statusBarStateController.getState() == 0) {
                ((NotificationStackScrollLayoutExtImpl) this.notificationStackScrollLayoutExtImpl.get()).ensureNsslHiddenWhenElementNotShowing();
            }
        }
    }

    public void onStatusBarStateChanged(NotificationStackScrollLayout notificationStackScrollLayout, int i) {
        Sequence<View> children;
        super.onStatusBarStateChanged(notificationStackScrollLayout, i);
        if (getUseLayeredAnimation()) {
            if (i == 1 && notificationStackScrollLayout != null && (children = ConvenienceExtensionsKt.getChildren(notificationStackScrollLayout)) != null) {
                for (View view : children) {
                    NotificationPanelSeparateAnimationManager notificationPanelSeparateAnimationManager = this.appearAnimationManager;
                    view.getClass();
                    ((NotificationPanelSeparateAnimationManagerImpl) notificationPanelSeparateAnimationManager).cancelViewAnimation(view, "onStateChanged = KEYGUARD");
                }
            }
            if (notificationStackScrollLayout != null) {
                notificationStackScrollLayout.requestChildrenUpdate$1();
            }
        }
    }

    public final void resetViewState(ExpandableView expandableView, boolean z) {
        expandableView.setTag(2131365563, null);
        RowAnimationStateEx rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableView);
        if (rowAnimationStateEx != null) {
            ScaleState.Companion companion = ScaleState.Companion;
            RowAnimationStateExImpl rowAnimationStateExImpl = (RowAnimationStateExImpl) rowAnimationStateEx;
            rowAnimationStateExImpl.requestRowScale(TAG_FOR_SHOW_ANIMATION, companion.getINVALID());
            rowAnimationStateExImpl.requestRowScale("headsup_zoom", companion.getINVALID());
            rowAnimationStateExImpl.requestRowAlpha(TAG_FOR_SHOW_ANIMATION, z ? new AlphaState(0.0f) : AlphaState.Companion.getINVALID());
        }
    }

    public final boolean shouldSkipApplyViewState() {
        return SeparateModeHelper.INSTANCE.getSeparateMode() && !((NotificationPanelViewController) this.notificationPanelViewController.get()).isShadeFullyExpanded();
    }

    public void updateShelfPosition(OplusShelfEx oplusShelfEx, NotificationStackScrollLayout notificationStackScrollLayout, AmbientState ambientState, ViewState viewState, ExpandableViewState expandableViewState, int i) {
        if (getUseLayeredAnimation()) {
            int i2 = 1;
            boolean z = notificationStackScrollLayout == null || oplusShelfEx == null;
            if (ambientState == null || viewState == null || expandableViewState == null || z) {
                this.logBuffer.log(TAG, LogLevel.ERROR, "updateShelfPosition, error! ambientState=" + ambientState + ", shelfViewState=" + viewState + ", lastViewState=" + expandableViewState + ", stackLayout=" + notificationStackScrollLayout + ", shelfEx=" + oplusShelfEx, (Throwable) null);
                return;
            }
            if (getLevelPositionAlgorithm().isInPanelAnimation()) {
                viewState.setYTranslation(ambientState.getLayoutMaxHeight() - i, "OplusNotificationPullDownAnimationExImpl.updateShelfPosition.2");
            } else {
                viewState.setYTranslation((ambientState.getStackEndHeight() + ambientState.getTopPadding()) - i, "OplusNotificationPullDownAnimationExImpl.updateShelfPosition.3");
                i2 = 2;
            }
            if (LogUtil.panicEnabled) {
                LogBuffer logBuffer = this.logBuffer;
                LogLevel logLevel = LogLevel.DEBUG;
                OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda0 oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda0 = new OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda0();
                LogBuffer.Companion.getClass();
                if (LogBuffer.isLogBufferDisabled) {
                    return;
                }
                LogMessage logMessageObtain = logBuffer.obtain(TAG, logLevel, oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda0, (Throwable) null);
                logMessageObtain.setDouble1(viewState.getYTranslation());
                logMessageObtain.setInt1(i2);
                logBuffer.commit(logMessageObtain);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0080  */
    /* JADX WARN: Multi-variable type inference failed */
    public void updateViewAlphaAndScale(ExpandableView expandableView, ViewState viewState, AmbientState ambientState, StackScrollAlgorithm.StackScrollAlgorithmState stackScrollAlgorithmState, ViewGroup viewGroup) {
        boolean z;
        OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2 oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2;
        OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2 oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3;
        NotificationChildrenContainer childrenContainer;
        List<ExpandableNotificationRow> attachedChildren;
        Float fValueOf = Float.valueOf(0.0f);
        if (getUseLayeredAnimation()) {
            if (expandableView == null || viewState == null) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "updateViewStateWhenPanelExpansionChanging: view=" + expandableView + ", viewState=" + viewState);
                return;
            }
            boolean z2 = expandableView instanceof ExpandableNotificationRow;
            if (z2 || (expandableView instanceof OplusCustomRow) || (expandableView instanceof EmptyShadeView) || (expandableView instanceof SectionHeaderView)) {
                ExpandableNotificationRow expandableNotificationRow = z2 ? (ExpandableNotificationRow) expandableView : null;
                boolean z3 = false;
                z3 = false;
                z3 = false;
                if (expandableNotificationRow != null && (childrenContainer = expandableNotificationRow.getChildrenContainer()) != null && (attachedChildren = childrenContainer.getAttachedChildren()) != null) {
                    for (ExpandableNotificationRow expandableNotificationRow2 : attachedChildren) {
                        expandableNotificationRow2.getClass();
                        resetViewState(expandableNotificationRow2, false);
                    }
                }
                int i = 1;
                if (viewGroup instanceof NotificationStackScrollLayout) {
                    NotificationPanelViewController notificationPanelController = ((NotificationStackScrollLayout) viewGroup).getNotificationPanelController();
                    if (notificationPanelController.mHeadsUpAnimatingAway || notificationPanelController.mExpandingFromHeadsUp || (this.headsUpManager.hasPinnedHeadsUp() && notificationPanelController.isFullyCollapsed())) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                }
                this.isLastHeadsUpPined = z;
                if (!ambientState.isOnKeyguard()) {
                    int i2 = 2;
                    if (this.statusBarStateController.getState() != 2 && !this.isLastHeadsUpPined) {
                        if (!ambientState.mShadeExpanded && !ambientState.mIsSwipingUp) {
                            resetViewState(expandableView, true);
                            expandableView.setTag(2131365558, Boolean.TRUE);
                            expandableView.setTag(2131365568, fValueOf);
                            expandableView.setTag(2131365563, null);
                            return;
                        }
                        boolean z4 = this.panelAnimationEx.isElementShowing() && this.isShadeExpanded && (this.lastIsShadeExpanded || (this.isShadeExpanded && !ambientState.mExpansionChanging && (this.panelAnimationEx.getPanelExpandFraction().getRawFraction() > 1.0f ? 1 : (this.panelAnimationEx.getPanelExpandFraction().getRawFraction() == 1.0f ? 0 : -1)) >= 0) == true);
                        if (getDisableRowShowHideAnimation()) {
                            clearShowAnimationState(expandableView);
                            return;
                        }
                        boolean z5 = !((OplusPanelViewPagerController) this.panelViewPagerController.get()).isQsToNotificationScroll() && this.isShadeExpanded == this.lastIsShadeExpanded && expandableView.getClipTopAmount() < expandableView.getActualHeight() && (!expandableView.getViewState().inShelf || Intrinsics.areEqual(stackScrollAlgorithmState.firstViewInShelf, expandableView) || this.currentAnimatingNotificationIndex <= 12 || (expandableView.getAlpha() > 0.0f && expandableView.getAlpha() < 1.0f)) && !this.panelAnimationEx.shouldSnapHideAfterCollapse();
                        int i3 = this.currentAnimatingNotificationIndex;
                        long j = ((long) i3) * 8;
                        if (z5) {
                            this.currentAnimatingNotificationIndex = i3 + 1;
                        }
                        if (z5) {
                            if (z4 ? ((NotificationPanelSeparateAnimationManagerImpl) this.appearAnimationManager).isShowAnimationsRunning(expandableView) : !isFirstShow(expandableView) && ((NotificationPanelSeparateAnimationManagerImpl) this.appearAnimationManager).isHideAnimationsRunning(expandableView)) {
                                return;
                            }
                        }
                        Object tag = expandableView.getTag(2131365563);
                        if (Intrinsics.areEqual(tag instanceof Boolean ? (Boolean) tag : null, Boolean.valueOf(z4))) {
                            return;
                        }
                        NotificationPanelSeparateAnimationManager notificationPanelSeparateAnimationManager = this.appearAnimationManager;
                        if (z4) {
                            int i4 = i3;
                            boolean zIsFirstShow = isFirstShow(expandableView);
                            SeparateModeHelper separateModeHelper = SeparateModeHelper.INSTANCE;
                            if (!separateModeHelper.getSeparateMode()) {
                                i4 = 0;
                            }
                            float endMotionVel = separateModeHelper.getSeparateMode() ? 0.0f : this.panelAnimationEx.getEndMotionVel();
                            OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2 oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda4 = new OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2(z3 ? 1 : 0);
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda4.f$0 = this;
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda4.f$1 = expandableView;
                            VarHandle.storeStoreFence();
                            OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3 oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda5 = new OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3(z3 ? 1 : 0);
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda5.f$0 = this;
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda5.f$1 = expandableView;
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda5.f$2 = z5;
                            VarHandle.storeStoreFence();
                            OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3 oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda6 = new OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3(i);
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda6.f$0 = this;
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda6.f$1 = expandableView;
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda6.f$2 = z5;
                            VarHandle.storeStoreFence();
                            float f = endMotionVel;
                            float negSpacing = getNegSpacing(expandableView);
                            if (separateModeHelper.getSeparateMode()) {
                                oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3 = new OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2(i2);
                                oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3.f$0 = this;
                                oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3.f$1 = expandableView;
                                VarHandle.storeStoreFence();
                            } else {
                                oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3 = null;
                            }
                            ((NotificationPanelSeparateAnimationManagerImpl) notificationPanelSeparateAnimationManager).show(expandableView, z5, zIsFirstShow, j, i4, f, oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda4, oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda5, oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda6, negSpacing, oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3);
                            expandableView.setTag(2131365558, Boolean.FALSE);
                        } else {
                            int i5 = i3;
                            boolean zIsFirstShow2 = isFirstShow(expandableView);
                            SeparateModeHelper separateModeHelper2 = SeparateModeHelper.INSTANCE;
                            if (!separateModeHelper2.getSeparateMode()) {
                                i5 = 0;
                            }
                            OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2 oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda7 = new OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2(i);
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda7.f$0 = this;
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda7.f$1 = expandableView;
                            VarHandle.storeStoreFence();
                            OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3 oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda8 = new OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3(i2);
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda8.f$0 = this;
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda8.f$1 = expandableView;
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda8.f$2 = z5;
                            VarHandle.storeStoreFence();
                            OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3 oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda9 = new OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda3(3);
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda9.f$0 = this;
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda9.f$1 = expandableView;
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda9.f$2 = z5;
                            VarHandle.storeStoreFence();
                            float negSpacing2 = getNegSpacing(expandableView);
                            if (separateModeHelper2.getSeparateMode()) {
                                oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2 = new OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2(i2);
                                oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2.f$0 = this;
                                oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2.f$1 = expandableView;
                                VarHandle.storeStoreFence();
                            } else {
                                oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2 = null;
                            }
                            OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda8 oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda10 = new OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda8();
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda10.f$0 = this;
                            oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda10.f$1 = z5;
                            VarHandle.storeStoreFence();
                            NotificationPanelSeparateAnimationManager.Companion companion = NotificationPanelSeparateAnimationManager.Companion;
                            ((NotificationPanelSeparateAnimationManagerImpl) notificationPanelSeparateAnimationManager).hide(expandableView, z5, zIsFirstShow2, 0L, i5, 0.0f, oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda7, oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda8, oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda9, negSpacing2, oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda2, oplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda10);
                        }
                        expandableView.setTag(2131365563, Boolean.valueOf(z4));
                        return;
                    }
                }
                if (this.isLastHeadsUpPined && !ambientState.mShadeExpanded && !ambientState.mIsSwipingUp) {
                    z3 = true;
                }
                resetViewState(expandableView, z3);
                expandableView.setTag(2131365558, Boolean.TRUE);
                expandableView.setTag(2131365568, fValueOf);
                expandableView.setTag(2131365563, null);
            }
        }
    }

    public final void updateViewStateAlpha(ExpandableView expandableView, float f, boolean z) {
        RowAnimationStateEx rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableView);
        if (rowAnimationStateEx != null) {
            ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowAlpha(TAG_FOR_SHOW_ANIMATION, new AlphaState(f));
            if (f != 1.0f) {
                Boolean bool = this.cacheShouldSkipApplyViewState;
                if (bool != null ? bool.booleanValue() : shouldSkipApplyViewState()) {
                    return;
                }
            }
            if (z) {
                expandableView.applyViewState();
            }
        }
    }

    public final void updateViewStateScale(ExpandableView expandableView, float f, boolean z) {
        RowAnimationStateEx rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableView);
        if (rowAnimationStateEx != null) {
            ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowScale(TAG_FOR_SHOW_ANIMATION, ScaleState.Companion.invoke(f));
            if (f != 1.0f) {
                Boolean bool = this.cacheShouldSkipApplyViewState;
                if (bool != null ? bool.booleanValue() : shouldSkipApplyViewState()) {
                    return;
                }
            }
            if (z) {
                expandableView.applyViewState();
            }
        }
    }
}
