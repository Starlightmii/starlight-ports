package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.FloatList$;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleViewState;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleEarShapeTool;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallController;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallScrollCalculator;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallUtils;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataOrderHandler;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.DampingCurve;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleAnimParams;
import com.oplus.systemui.notification.lockscreen.seedling.CapsuleSeedingData;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightEffectManager;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ContainerAlgorithm {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StackScrollAlgorithm";
    private final Context context;
    private final ContainerAnimatorViewHelper hostLayoutHelper;
    private final ViewGroup hostView;
    public int itemHeight;
    public int itemWidth;
    private final CapsuleAlgorithmState tempAlgorithmState = new CapsuleAlgorithmState();
    private final CapsuleMetaBallController metaBallController = new CapsuleMetaBallController();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleAlgorithmState {
        public int scrollX;
        private final ArrayList<CapsuleItemView> visibleChildren = new ArrayList<>();
        private final ArrayList<CapsuleItemView> needIgnore = new ArrayList<>();
        public int baseIndex = -1;
        public int nextIndex = -1;

        public final int getBaseIndex() {
            return this.baseIndex;
        }

        public final ArrayList<CapsuleItemView> getNeedIgnore() {
            return this.needIgnore;
        }

        public final int getNextIndex() {
            return this.nextIndex;
        }

        public final int getScrollX() {
            return this.scrollX;
        }

        public final ArrayList<CapsuleItemView> getVisibleChildren() {
            return this.visibleChildren;
        }

        public final void setBaseIndex(int i) {
            this.baseIndex = i;
        }

        public final void setNextIndex(int i) {
            this.nextIndex = i;
        }

        public final void setScrollX(int i) {
            this.scrollX = i;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void debugLog(String str) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, ContainerAlgorithm.TAG, str, null, 8);
        }

        public final void debugLogView(View view, String str) {
            String str2 = view instanceof CapsuleItemView ? CapsuleItemView.TAG : null;
            if (str2 == null) {
                String string = view != null ? view.toString() : null;
                str2 = string == null ? "View is null" : string;
            }
            debugLog(str2 + " " + str);
        }

        public final float getBLUR_H_SCROLL_MAX_RADIUS() {
            return CapsuleAnimParams.CapsuleScrollParams.INSTANCE.getBLUR_MAX().get().floatValue();
        }
    }

    public ContainerAlgorithm(Context context, ViewGroup viewGroup, ContainerAnimatorViewHelper containerAnimatorViewHelper) {
        this.context = context;
        this.hostView = viewGroup;
        this.hostLayoutHelper = containerAnimatorViewHelper;
        initView(context);
    }

    public static int getTouchFeedBackSize(float f, int i) {
        return (int) ((((1.0f - ExtStateAnimParams.CommonParams.INSTANCE.getTOUCH_SCALE().get().floatValue()) * i) * f) / 2.0f);
    }

    public final List<CapsuleItemView> getIgnoreViews() {
        return this.tempAlgorithmState.getNeedIgnore();
    }

    public final CapsuleMetaBallController getMetaBallController() {
        return this.metaBallController;
    }

    public final CapsuleAlgorithmState getTempAlgorithmState() {
        return this.tempAlgorithmState;
    }

    public final List<ExpandableView> getVisibleChildren() {
        return this.tempAlgorithmState.getVisibleChildren();
    }

    public final void initView(Context context) {
    }

    /* JADX WARN: Code duplicated, block: B:133:0x030f  */
    /* JADX WARN: Code duplicated, block: B:34:0x00cf  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v35 */
    /* JADX WARN: Type inference failed for: r6v36, types: [android.widget.FrameLayout, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v38 */
    public final void resetViewStates(LockScreenAmbientState lockScreenAmbientState) {
        int i;
        Object next;
        ?? r6;
        OplusCustomRow row;
        CapsuleItemView capsuleItemView;
        lockScreenAmbientState.getPaddingBetweenElements();
        this.itemWidth = CapsuleConfig.INSTANCE.getItemWidthMultiCard();
        this.itemHeight = lockScreenAmbientState.getItemHeight();
        CapsuleAlgorithmState capsuleAlgorithmState = this.tempAlgorithmState;
        int childCount = this.hostView.getChildCount();
        boolean z = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            this.hostView.getChildAt(i2).resetViewState();
        }
        CapsuleScrollCalculator calculator = CapsuleScrollCalculatorFactory.INSTANCE.getCalculator(this.context);
        int i3 = this.itemWidth;
        int i4 = this.itemHeight;
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        calculator.initCapsuleParams(i3, i4, capsuleConfig.getEarWidth(), capsuleConfig.getEarMargin());
        lockScreenAmbientState.setScrollCalculator(calculator);
        capsuleAlgorithmState.setScrollX(lockScreenAmbientState.getScrollX());
        int childCount2 = this.hostView.getChildCount();
        capsuleAlgorithmState.getVisibleChildren().clear();
        capsuleAlgorithmState.getNeedIgnore().clear();
        capsuleAlgorithmState.getVisibleChildren().ensureCapacity(childCount2);
        int i5 = 0;
        int i6 = 0;
        while (true) {
            CapsuleSeedingData capsuleSeedingData = null;
            if (i5 >= childCount2) {
                break;
            }
            Object childAt = this.hostView.getChildAt(i5);
            if (childAt instanceof CapsuleItemView) {
                capsuleItemView = (CapsuleItemView) childAt;
            } else {
                r6 = 0;
            }
            if (r6 == 0 || r6.getVisibility() == 8) {
                r6 = capsuleItemView;
            } else if (!(r6.getEntry() instanceof CapsuleSeedingData) || r6.isHScrolling) {
                r6 = capsuleItemView;
                r6 = capsuleItemView;
                ((ExpandableViewState) r6.getViewState()).notGoneIndex = i6;
                capsuleAlgorithmState.getVisibleChildren().add(r6);
                i6++;
            } else {
                CapsuleItemData entry = r6.getEntry();
                if (entry instanceof CapsuleSeedingData) {
                    r6 = capsuleItemView;
                    capsuleSeedingData = (CapsuleSeedingData) entry;
                }
                if (capsuleSeedingData == null || (row = capsuleSeedingData.getRow()) == null || ((ExpandableView) row).mOnHeightChangedListener != null || !(r6.isBindToStackView() || r6.getVisibility() == 4 || r6.getAlpha() < 0.5f)) {
                    r6 = capsuleItemView;
                    r6 = capsuleItemView;
                    ((ExpandableViewState) r6.getViewState()).notGoneIndex = i6;
                    capsuleAlgorithmState.getVisibleChildren().add(r6);
                    i6++;
                } else {
                    Companion.debugLog("initAlgorithmState:is need removed view not show");
                    capsuleAlgorithmState.getNeedIgnore().add(r6);
                }
            }
            i5++;
        }
        CapsuleItemView immersiveCard = lockScreenAmbientState.getImmersiveCard();
        if (immersiveCard != null) {
            int iIndexOf = capsuleAlgorithmState.getVisibleChildren().indexOf(immersiveCard);
            capsuleAlgorithmState.getVisibleChildren().remove(immersiveCard);
            if (Intrinsics.areEqual(lockScreenAmbientState.getBaseCard(), immersiveCard) && !capsuleAlgorithmState.getVisibleChildren().isEmpty() && iIndexOf != -1) {
                lockScreenAmbientState.setBaseCard(capsuleAlgorithmState.getVisibleChildren().get((iIndexOf + 1) % capsuleAlgorithmState.getVisibleChildren().size()));
            }
        }
        CapsuleItemView baseCard = lockScreenAmbientState.getBaseCard();
        if (baseCard != null && capsuleAlgorithmState.getVisibleChildren().indexOf(baseCard) == -1) {
            lockScreenAmbientState.setBaseCard(null);
        }
        CapsuleItemView nextCard = lockScreenAmbientState.getNextCard();
        if (nextCard != null && (capsuleAlgorithmState.getVisibleChildren().indexOf(nextCard) == -1 || nextCard == lockScreenAmbientState.getBaseCard())) {
            lockScreenAmbientState.setNextCard(null);
        }
        if (lockScreenAmbientState.getBaseCard() == null && !capsuleAlgorithmState.getVisibleChildren().isEmpty()) {
            Companion.debugLog("initAlgorithmState: no base card ,set first child as base card.");
            Iterator<T> it = capsuleAlgorithmState.getVisibleChildren().iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    int orderPriority = ((CapsuleItemView) next).getEntry().getOrderPriority();
                    do {
                        Object next2 = it.next();
                        int orderPriority2 = ((CapsuleItemView) next2).getEntry().getOrderPriority();
                        if (orderPriority > orderPriority2) {
                            next = next2;
                            orderPriority = orderPriority2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            lockScreenAmbientState.setBaseCard((CapsuleItemView) next);
        }
        lockScreenAmbientState.setShowingCapsuleOnlyOne(capsuleAlgorithmState.getVisibleChildren().size() <= 1);
        capsuleAlgorithmState.setBaseIndex(-1);
        capsuleAlgorithmState.setNextIndex(-1);
        CapsuleItemView baseCard2 = lockScreenAmbientState.getBaseCard();
        int size = capsuleAlgorithmState.getVisibleChildren().size();
        CapsuleDataOrderHandler capsuleDataOrderHandler = CapsuleDataOrderHandler.INSTANCE;
        int baseCardIndex = capsuleDataOrderHandler.getBaseCardIndex(baseCard2);
        int nextCardIndex = capsuleDataOrderHandler.getNextCardInfo(baseCard2).getNextCardIndex();
        capsuleAlgorithmState.setBaseIndex(baseCardIndex);
        capsuleAlgorithmState.setNextIndex(capsuleAlgorithmState.getScrollX() != 0 ? nextCardIndex : -1);
        Companion companion = Companion;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("updatePositionsForStateIndex:", size, baseCardIndex, ", [", " --> nextIndex:");
        sbM.append(nextCardIndex);
        sbM.append("]");
        companion.debugLog(sbM.toString());
        if (baseCardIndex != -1) {
            updateChild(baseCardIndex, capsuleAlgorithmState, lockScreenAmbientState);
        }
        for (int i7 = 0; i7 < size; i7++) {
            if (i7 != baseCardIndex) {
                updateChild(i7, capsuleAlgorithmState, lockScreenAmbientState);
            }
        }
        CapsuleItemView immersiveCard2 = lockScreenAmbientState.getImmersiveCard();
        if (immersiveCard2 != null) {
            CapsuleViewState viewState = immersiveCard2.getViewState();
            ((ExpandableViewState) viewState).location = 2;
            viewState.setAlpha(0.0f, "StackScrollAlgorithm.updatePositionsForState");
            ((ViewState) viewState).hidden = true;
            viewState.setXTranslation(0.0f);
            viewState.setYTranslation((lockScreenAmbientState.getStackY() + this.hostLayoutHelper.getCapsuleFinalTransY()) - 300.0f, "StackScrollAlgorithm.updatePositionsForState");
        }
        ArrayList<CapsuleItemView> needIgnore = capsuleAlgorithmState.getNeedIgnore();
        int size2 = needIgnore.size();
        int i8 = 0;
        while (i8 < size2) {
            CapsuleItemView capsuleItemView2 = needIgnore.get(i8);
            i8++;
            CapsuleViewState viewState2 = capsuleItemView2.getViewState();
            ((ExpandableViewState) viewState2).location = 64;
            viewState2.setAlpha(0.0f, "StackScrollAlgorithm.updatePositionsForState:needIgnore");
            ((ViewState) viewState2).hidden = true;
            viewState2.setXTranslation(0.0f);
            viewState2.setYTranslation(lockScreenAmbientState.getStackY() + this.hostLayoutHelper.getCapsuleFinalTransY(), "StackScrollAlgorithm.updatePositionsForState:needIgnore");
        }
        CapsuleEarController earController = lockScreenAmbientState.getEarController();
        float stackY = lockScreenAmbientState.getStackY() + this.hostLayoutHelper.getCapsuleFinalTransY();
        if (earController.baseTransitionY != stackY) {
            earController.baseTransitionY = stackY;
            earController.updateTransitionY();
        }
        if (NotificationSpotLightEffectManager.spotLightEnabled) {
            boolean z2 = capsuleAlgorithmState.getNextIndex() != -1;
            if (CapsuleMetaBallUtils.isSupportMetaBall(this.context) && z2) {
                float fAbs = Math.abs(capsuleAlgorithmState.getScrollX()) / CapsuleEarShapeTool.INSTANCE.getEarEndHeightForCapsule();
                if (fAbs > 1.0f) {
                    fAbs = 1.0f;
                }
                i = (int) ((1.0f - fAbs) * 255.0f);
            } else {
                i = 255;
            }
            NotificationSpotLightDelegate.Companion.updateCapsuleSpotLightAlpha(i);
        }
        if (!this.hostLayoutHelper.getStateAnimRunning() && !this.hostLayoutHelper.readyToAnimAddORemove()) {
            CapsuleEarController earController2 = lockScreenAmbientState.getEarController();
            if (earController2.getNeedIntercept()) {
                CapsuleItemView baseCard3 = lockScreenAmbientState.getBaseCard();
                if (baseCard3 == null || !baseCard3.isBindToStackView()) {
                    earController2.setNeedIntercept(false);
                    earController2.setBaseCard(null);
                    Companion.debugLog("updateEarPositions init needIntercept");
                } else {
                    CapsuleItemView baseCard4 = lockScreenAmbientState.getBaseCard();
                    WeakReference<CapsuleItemView> baseCard5 = earController2.getBaseCard();
                    if (!Intrinsics.areEqual(baseCard4, baseCard5 != null ? baseCard5.get() : null)) {
                        earController2.setNeedIntercept(false);
                        earController2.setBaseCard(null);
                        Companion.debugLog("updateEarPositions init needIntercept");
                    }
                }
            }
            if (capsuleAlgorithmState.getVisibleChildren().size() > 1 && !lockScreenAmbientState.isStackState) {
                z = true;
            }
            earController2.setNeedShow(z);
            lockScreenAmbientState.getScrollCalculator().updateEarState(earController2, lockScreenAmbientState.getScrollX(), lockScreenAmbientState.isHorizontalFling);
        }
        if (lockScreenAmbientState.getScrollCalculator() instanceof CapsuleMetaBallScrollCalculator) {
            this.metaBallController.updateMetaBallAndEarState(lockScreenAmbientState);
        }
    }

    /* JADX WARN: Code duplicated, block: B:74:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:76:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:77:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:79:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:80:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:82:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:85:0x01f5  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.widget.FrameLayout, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView, java.lang.Object] */
    public final void updateChild(int i, CapsuleAlgorithmState capsuleAlgorithmState, LockScreenAmbientState lockScreenAmbientState) {
        int capsuleBetweenMargin;
        int i2;
        int iAbs;
        float f;
        float fAbs;
        int i3;
        int i4;
        int i5;
        CapsuleViewState.ScrollClipInfo scrollClipInfo;
        CapsuleItemView capsuleItemView = capsuleAlgorithmState.getVisibleChildren().get(i);
        CapsuleViewState viewState = capsuleItemView.getViewState();
        float scrollX = capsuleAlgorithmState.getScrollX();
        float fAbs2 = Math.abs(scrollX);
        float f2 = this.itemWidth;
        if (fAbs2 > f2) {
            fAbs2 = f2;
        }
        float f3 = fAbs2 / f2;
        CapsuleScrollCalculator scrollCalculator = lockScreenAmbientState.getScrollCalculator();
        capsuleItemView.setHScrolling((i == capsuleAlgorithmState.getBaseIndex() || i == capsuleAlgorithmState.getNextIndex()) && scrollX != 0.0f);
        if (i == capsuleAlgorithmState.getBaseIndex()) {
            CapsuleScrollCalculator scrollCalculator2 = lockScreenAmbientState.getScrollCalculator();
            ((ExpandableViewState) viewState).location = 1;
            if (!capsuleItemView.isAppearAnimating().get()) {
                ((ViewState) viewState).hidden = false;
                if (scrollX != 0.0f) {
                    ((ExpandableViewState) viewState).mExpandableViewStateEx.setBlurRadio(Companion.getBLUR_H_SCROLL_MAX_RADIUS() * f3);
                }
                viewState.setZTranslation(0.0f);
                float touchProgress = lockScreenAmbientState.getTouchProgress();
                int touchFeedBackSize = getTouchFeedBackSize(touchProgress, this.itemWidth);
                int touchFeedBackSize2 = getTouchFeedBackSize(touchProgress, this.itemHeight);
                Math.abs(scrollX);
                int baseCardMinWidth = scrollCalculator2.getBaseCardMinWidth();
                int i6 = (int) scrollX;
                int iAbs2 = Math.abs(i6);
                int i7 = (this.itemWidth - baseCardMinWidth) - touchFeedBackSize;
                if (iAbs2 > i7) {
                    iAbs2 = i7;
                }
                int i8 = -touchFeedBackSize;
                if (i6 >= i8) {
                    i8 = i6;
                }
                if (i8 > 0) {
                    i8 = 0;
                }
                int i9 = i8 + touchFeedBackSize;
                if (i6 > touchFeedBackSize) {
                    i6 = touchFeedBackSize;
                }
                int i10 = touchFeedBackSize - (i6 >= 0 ? i6 : 0);
                if (scrollX == 0.0f) {
                    scrollClipInfo = new CapsuleViewState.ScrollClipInfo(i9, i10, 12);
                } else {
                    scrollClipInfo = scrollX < 0.0f ? new CapsuleViewState.ScrollClipInfo(i9, i10 + iAbs2, 12) : new CapsuleViewState.ScrollClipInfo(i9 + iAbs2, i10, 12);
                }
                CapsuleViewState.ScrollClipInfo scrollClipInfo2 = scrollClipInfo;
                int iCalculateBaseCardVerticalClip = scrollCalculator2.calculateBaseCardVerticalClip(iAbs2, scrollX, touchFeedBackSize2, capsuleItemView, viewState, scrollClipInfo2);
                scrollCalculator2.updateBaseCardScale(capsuleItemView, viewState, scrollX);
                viewState.setXTranslation(scrollCalculator2.getBaseCardTransX(scrollX));
                viewState.setScrollClipInfo(CapsuleViewState.ScrollClipInfo.copy$default(scrollClipInfo2, iCalculateBaseCardVerticalClip, iCalculateBaseCardVerticalClip, 3));
                viewState.setAlpha((!this.hostLayoutHelper.getAmbientState().isHorizontalBackFling || capsuleAlgorithmState.getScrollX() == 0) ? 1.0f : 0.0f, "StackScrollAlgorithm.updateBaseChild");
            }
            Companion.debugLog("updateChild:Base(" + capsuleItemView + "): visible:" + capsuleItemView.getVisibility() + ", scrollX: " + scrollX + ", viewState(" + viewState + ")");
        } else if (i == capsuleAlgorithmState.getNextIndex()) {
            ((ExpandableViewState) viewState).location = 4;
            if (!capsuleItemView.isAppearAnimating().get()) {
                viewState.setAlpha(capsuleAlgorithmState.getScrollX() != 0 ? 1.0f : 0.0f, "StackScrollAlgorithm.updateChild.nextIndex");
                ((ViewState) viewState).hidden = false;
                if (scrollX != 0.0f) {
                    ((ExpandableViewState) viewState).mExpandableViewStateEx.setBlurRadio((1.0f - f3) * Companion.getBLUR_H_SCROLL_MAX_RADIUS());
                }
                viewState.setZTranslation(2.0f);
                int touchFeedBackSize3 = getTouchFeedBackSize(0.0f, this.itemWidth);
                int maxOverScrollWidth = scrollCalculator.getMaxOverScrollWidth();
                int nextCardMinWidth = scrollCalculator.getNextCardMinWidth();
                int i11 = (int) scrollX;
                int iAbs3 = Math.abs(i11);
                int i12 = this.itemWidth;
                if (iAbs3 <= i12) {
                    iAbs3 += nextCardMinWidth;
                    if (iAbs3 <= i12) {
                    }
                    capsuleBetweenMargin = i12 - scrollCalculator.getCapsuleBetweenMargin(i12, touchFeedBackSize3);
                    i2 = this.itemWidth;
                    if (capsuleBetweenMargin <= i2) {
                        viewState.setWidth(i2);
                        if (scrollX < 0.0f) {
                            i3 = this.itemWidth - (touchFeedBackSize3 + capsuleBetweenMargin);
                            i5 = touchFeedBackSize3;
                        } else if (scrollX > 0.0f) {
                            i5 = this.itemWidth - (touchFeedBackSize3 + capsuleBetweenMargin);
                            i3 = touchFeedBackSize3;
                        } else {
                            i3 = 0;
                            i5 = 0;
                        }
                        scrollCalculator.updateNextCardScrollState(capsuleItemView, viewState, scrollX, capsuleBetweenMargin);
                        i4 = i5;
                    } else {
                        iAbs = Math.abs(i11) - this.itemWidth;
                        DampingCurve dampingCurve = DampingCurve.INSTANCE;
                        float f4 = maxOverScrollWidth;
                        float fFloatValue = CapsuleAnimParams.CapsuleScrollParams.INSTANCE.getNextCardExpandRadio().get().floatValue();
                        f = iAbs;
                        dampingCurve.getClass();
                        fAbs = (1.0f - (1.0f / (((Math.abs(f) * fFloatValue) / f4) + 1.0f))) * f4;
                        if (f < 0.0f) {
                            fAbs = -fAbs;
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(FloatList$.ExternalSyntheticOutline0.m("getNextCardExpandWidth: expandMax:", maxOverScrollWidth, iAbs, ", ", LogUtil.SEPARATOR), fAbs, "StackScrollAlgorithm-->", "SystemUi--Notification");
                        }
                        scrollCalculator.updateNextCardOverScrollState(capsuleItemView, viewState, scrollX, fAbs);
                        i3 = 0;
                        i4 = 0;
                    }
                    int iCalculateNextCardVerticalClip = (int) (scrollCalculator.calculateNextCardVerticalClip(capsuleBetweenMargin) / 2.0f);
                    viewState.setScrollClipInfo(new CapsuleViewState.ScrollClipInfo(i3, i4, iCalculateNextCardVerticalClip, iCalculateNextCardVerticalClip));
                    lockScreenAmbientState.setNextCard(capsuleItemView);
                } else {
                    int i13 = i12 + maxOverScrollWidth;
                    if (iAbs3 > i13) {
                        iAbs3 = i13;
                    }
                }
                i12 = iAbs3;
                capsuleBetweenMargin = i12 - scrollCalculator.getCapsuleBetweenMargin(i12, touchFeedBackSize3);
                i2 = this.itemWidth;
                if (capsuleBetweenMargin <= i2) {
                    viewState.setWidth(i2);
                    if (scrollX < 0.0f) {
                        i3 = this.itemWidth - (touchFeedBackSize3 + capsuleBetweenMargin);
                        i5 = touchFeedBackSize3;
                    } else if (scrollX > 0.0f) {
                        i5 = this.itemWidth - (touchFeedBackSize3 + capsuleBetweenMargin);
                        i3 = touchFeedBackSize3;
                    } else {
                        i3 = 0;
                        i5 = 0;
                    }
                    scrollCalculator.updateNextCardScrollState(capsuleItemView, viewState, scrollX, capsuleBetweenMargin);
                    i4 = i5;
                } else {
                    iAbs = Math.abs(i11) - this.itemWidth;
                    DampingCurve dampingCurve2 = DampingCurve.INSTANCE;
                    float f5 = maxOverScrollWidth;
                    float fFloatValue2 = CapsuleAnimParams.CapsuleScrollParams.INSTANCE.getNextCardExpandRadio().get().floatValue();
                    f = iAbs;
                    dampingCurve2.getClass();
                    fAbs = (1.0f - (1.0f / (((Math.abs(f) * fFloatValue2) / f5) + 1.0f))) * f5;
                    if (f < 0.0f) {
                        fAbs = -fAbs;
                    }
                    if (LogUtil.getNormalDebug()) {
                        VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(FloatList$.ExternalSyntheticOutline0.m("getNextCardExpandWidth: expandMax:", maxOverScrollWidth, iAbs, ", ", LogUtil.SEPARATOR), fAbs, "StackScrollAlgorithm-->", "SystemUi--Notification");
                    }
                    scrollCalculator.updateNextCardOverScrollState(capsuleItemView, viewState, scrollX, fAbs);
                    i3 = 0;
                    i4 = 0;
                }
                int iCalculateNextCardVerticalClip2 = (int) (scrollCalculator.calculateNextCardVerticalClip(capsuleBetweenMargin) / 2.0f);
                viewState.setScrollClipInfo(new CapsuleViewState.ScrollClipInfo(i3, i4, iCalculateNextCardVerticalClip2, iCalculateNextCardVerticalClip2));
                lockScreenAmbientState.setNextCard(capsuleItemView);
            }
            Companion.debugLog("updateChild:Next(" + capsuleItemView + "): visible:" + capsuleItemView.getVisibility() + ", scrollX: " + scrollX + ", viewState:" + viewState);
        } else {
            ((ExpandableViewState) capsuleItemView.getViewState()).location = 0;
            if (!capsuleItemView.isAppearAnimating().get()) {
                viewState.setXTranslation(0.0f);
                ((ViewState) viewState).hidden = true;
                viewState.setAlpha(0.0f, "StackScrollAlgorithm.updateChild.else");
                ((ExpandableViewState) viewState).mExpandableViewStateEx.setBlurRadio(0.0f);
                viewState.setScrollClipInfo(new CapsuleViewState.ScrollClipInfo(0, 0, 15));
            }
            Companion.debugLog("updateChild:Other(" + capsuleItemView + "): visible:" + capsuleItemView.getVisibility() + ", scrollX: " + scrollX + ", viewState:" + viewState);
        }
        if (lockScreenAmbientState.isStackState) {
            ((ViewState) viewState).hidden = true;
            viewState.setAlpha(0.0f, "StackScrollAlgorithm.updateChild.isStackState");
        }
        viewState.setYTranslation(lockScreenAmbientState.getStackY() + this.hostLayoutHelper.getCapsuleFinalTransY(), "StackScrollAlgorithm.updateChild");
        viewState.setContentWidth(lockScreenAmbientState.isShowingCapsuleOnlyOne ? CapsuleConfig.INSTANCE.getItemWidth() : CapsuleConfig.INSTANCE.getItemWidthMultiCard());
        Companion companion = Companion;
        int height = this.hostView.getHeight();
        int marginBottom = lockScreenAmbientState.getMarginBottom();
        int itemHeight = lockScreenAmbientState.getItemHeight();
        float stackY = lockScreenAmbientState.getStackY();
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("hostView.height:", height, marginBottom, ", marginBottom:", ", itemHeight:");
        sbM.append(itemHeight);
        sbM.append(", stackY:");
        sbM.append(stackY);
        companion.debugLog(sbM.toString());
    }
}
