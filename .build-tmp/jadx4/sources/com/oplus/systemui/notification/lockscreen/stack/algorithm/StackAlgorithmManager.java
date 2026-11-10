package com.oplus.systemui.notification.lockscreen.stack.algorithm;

import android.content.Context;
import android.content.res.Configuration;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StackAlgorithmManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StackAlgorithmManager";
    private final Context context;
    private final DynamicStackAlgorithm dynamicStackAlgorithm;
    private DynamicStackRequest dynamicStackRequest;
    private final Lazy npvc;
    private final Lazy nsslController;
    private final Lazy nsslExt;
    private final ArrayList<ExpandableView> sinkingMixedViewsScratch;
    private final StaticStackAlgorithm staticStackAlgorithm;
    private StaticStackDebugMetrics staticStackDebugMetrics;
    private StaticStackEvaluation staticStackEvaluation;
    private StaticStackRequest staticStackRequest;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.stack.algorithm.StackAlgorithmManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ StackAlgorithmManager this$0;

        public final void onConfigChanged(Configuration configuration) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, StackAlgorithmManager.TAG, "onConfigChanged, refreshFromResources", null, 8);
            StackAlgorithmManager stackAlgorithmManager = this.this$0;
            DynamicStackDimensCache dynamicStackDimensCache = stackAlgorithmManager.dynamicStackAlgorithm.dimensCache;
            if (dynamicStackDimensCache != null) {
                dynamicStackDimensCache.refreshFromResources();
            }
            stackAlgorithmManager.staticStackAlgorithm.refreshDimens();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StackAlgorithmManager(Context context, ConfigurationController configurationController, Lazy lazy, Lazy lazy2, Lazy lazy3) {
        this.context = context;
        this.npvc = lazy;
        this.nsslExt = lazy2;
        this.nsslController = lazy3;
        DynamicStackAlgorithm dynamicStackAlgorithm = new DynamicStackAlgorithm(context);
        this.dynamicStackAlgorithm = dynamicStackAlgorithm;
        StaticStackAlgorithm staticStackAlgorithm = new StaticStackAlgorithm(context);
        this.staticStackAlgorithm = staticStackAlgorithm;
        this.sinkingMixedViewsScratch = new ArrayList<>();
        dynamicStackAlgorithm.initializeDimensCache();
        staticStackAlgorithm.refreshDimens();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        configurationController.addCallback(anonymousClass1);
    }

    public final boolean applyDynamic(List<? extends ExpandableView> list, float f) {
        DynamicStackAlgorithm dynamicStackAlgorithm = this.dynamicStackAlgorithm;
        StackPhase stackPhase = StackPhase.TOP_STACK;
        DynamicStackRequest dynamicStackRequest = this.dynamicStackRequest;
        if (dynamicStackRequest == null) {
            dynamicStackRequest = new DynamicStackRequest(stackPhase, list, f, 0.0f);
            this.dynamicStackRequest = dynamicStackRequest;
        }
        dynamicStackRequest.setPhase(stackPhase);
        dynamicStackRequest.setExpandableViews(list);
        dynamicStackRequest.setBottomBorder(f);
        dynamicStackRequest.setTopBorder(0.0f);
        boolean zApply = dynamicStackAlgorithm.apply(dynamicStackRequest);
        releaseCachedStackRequestViews();
        return zApply;
    }

    public final void applySinkingDynamicLayout(List<? extends ExpandableView> list, List<? extends ExpandableView> list2, float f, float f2) {
        this.sinkingMixedViewsScratch.addAll(list);
        this.sinkingMixedViewsScratch.addAll(list2);
        DynamicStackAlgorithm dynamicStackAlgorithm = this.dynamicStackAlgorithm;
        StackPhase stackPhase = StackPhase.SINKING_STACK;
        ArrayList<ExpandableView> arrayList = this.sinkingMixedViewsScratch;
        DynamicStackRequest dynamicStackRequest = this.dynamicStackRequest;
        if (dynamicStackRequest == null) {
            dynamicStackRequest = new DynamicStackRequest(stackPhase, arrayList, f2, f);
            this.dynamicStackRequest = dynamicStackRequest;
        }
        dynamicStackRequest.setPhase(stackPhase);
        dynamicStackRequest.setExpandableViews(arrayList);
        dynamicStackRequest.setBottomBorder(f2);
        dynamicStackRequest.setTopBorder(f);
        dynamicStackAlgorithm.apply(dynamicStackRequest);
        releaseCachedStackRequestViews();
    }

    public final void applySinkingStaticLayout(List<? extends ExpandableView> list, float f, float f2, float f3, float f4, List<? extends ExpandableView> list2) {
        this.staticStackAlgorithm.apply(getStaticStackRequest(StackPhase.SINKING_STACK, list, f, f2, f3, f4, list2));
        releaseCachedStackRequestViews();
    }

    public final boolean applyStatic(List<? extends ExpandableView> list, float f, float f2) {
        boolean zApply = this.staticStackAlgorithm.apply(getStaticStackRequest(StackPhase.TOP_STACK, list, f, f2, 0.0f, -1.0f, null));
        releaseCachedStackRequestViews();
        return zApply;
    }

    public final boolean canStayInPlaceOnExpand(ExpandableNotificationRow expandableNotificationRow) {
        List visibleChildren;
        ExpandableView expandableView;
        NotificationStackScrollLayoutExtImpl notificationStackScrollLayoutExtImpl = (NotificationStackScrollLayoutExtImpl) this.nsslExt.get();
        float stackedBottomBorder = notificationStackScrollLayoutExtImpl.getStackedBottomBorder();
        NotificationStackScrollLayout view = notificationStackScrollLayoutExtImpl.getView();
        Float fValueOf = (view == null || (visibleChildren = view.getVisibleChildren()) == null || (expandableView = (ExpandableView) CollectionsKt___CollectionsKt.firstOrNull(visibleChildren)) == null) ? null : Float.valueOf(expandableView.getTranslationY());
        float maxVerticalSpaceForLockScreenNotification = ((NotificationPanelViewController) this.npvc.get()).getExt().getMaxVerticalSpaceForLockScreenNotification();
        float fFloatValue = fValueOf != null ? stackedBottomBorder - fValueOf.floatValue() : notificationStackScrollLayoutExtImpl.getLockscreenNotificationDisplayHeight(false);
        float f = maxVerticalSpaceForLockScreenNotification - fFloatValue;
        ExpandableNotificationRow notificationParent = expandableNotificationRow.isChildInGroup() ? expandableNotificationRow.getNotificationParent() : expandableNotificationRow;
        float translationY = notificationParent.getTranslationY() + notificationParent.getIntrinsicHeight();
        float f2 = translationY - f;
        boolean zIsChildInGroup = expandableNotificationRow.isChildInGroup();
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("canStayInPlaceOnExpand, excessSpaceAbove：", f, ",currentNtfBottomPosition: ", translationY, ", minNtfBottomPosition: ");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f2, ", bottomBorder: ", stackedBottomBorder, ", isChildInGroup: ");
        sbM.append(zIsChildInGroup);
        sbM.append(", currentNotificationHeight= ");
        sbM.append(fFloatValue);
        sbM.append(", firstViewTop = ");
        sbM.append(fValueOf);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        if (f2 <= stackedBottomBorder) {
            if (Intrinsics.areEqual(((NotificationStackScrollLayoutController) this.nsslController.get()).getView().getLastChildNotGone(), notificationParent)) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "last child expand", null, 8);
                if (f2 < stackedBottomBorder) {
                    return true;
                }
            } else if (f2 < stackedBottomBorder - this.dynamicStackAlgorithm.currentDynamicStackDimens().getScaleDampingBaseHeight()) {
                return true;
            }
        }
        return false;
    }

    public final float computeSeedlingNeededHeight(List<? extends ExpandableView> list) {
        return this.staticStackAlgorithm.computeSeedlingNeededHeight(list);
    }

    public final float computeSinkingStaticStackedAreaHeight(List<? extends ExpandableView> list) {
        if (list.isEmpty()) {
            return 0.0f;
        }
        int size = list.size();
        if (size > 3) {
            size = 3;
        }
        return this.staticStackAlgorithm.computeSinkingStaticStackedAreaHeight(list, size);
    }

    public final DynamicStackDimens currentDynamicStackDimens() {
        return this.dynamicStackAlgorithm.currentDynamicStackDimens();
    }

    public final StaticStackEvaluation evaluateSinkingStackStaticStackFit(List<? extends ExpandableView> list, List<? extends ExpandableView> list2, float f) {
        int size = list.size();
        if (size > 3) {
            size = 3;
        }
        return evaluateStaticStackFit(list, list2, f, size);
    }

    public final StaticStackEvaluation evaluateStaticStackFit(List list, List list2, float f, int i) {
        float paddingBetweenElements = 0.0f;
        float fComputeSinkingStaticStackedAreaHeight = i <= 0 ? 0.0f : this.staticStackAlgorithm.computeSinkingStaticStackedAreaHeight(list, i);
        DynamicStackDimens dynamicStackDimensCurrentDynamicStackDimens = this.dynamicStackAlgorithm.currentDynamicStackDimens();
        float fComputeSeedlingNeededHeight = this.staticStackAlgorithm.computeSeedlingNeededHeight(list2);
        if (!list2.isEmpty() && !list.isEmpty()) {
            paddingBetweenElements = dynamicStackDimensCurrentDynamicStackDimens.getPaddingBetweenElements();
        }
        float f2 = fComputeSinkingStaticStackedAreaHeight + fComputeSeedlingNeededHeight + paddingBetweenElements;
        StaticStackDebugMetrics staticStackDebugMetrics = this.staticStackDebugMetrics;
        if (staticStackDebugMetrics == null) {
            staticStackDebugMetrics = new StaticStackDebugMetrics(fComputeSinkingStaticStackedAreaHeight, fComputeSeedlingNeededHeight, f2, i);
            this.staticStackDebugMetrics = staticStackDebugMetrics;
        }
        staticStackDebugMetrics.setStaticArea(fComputeSinkingStaticStackedAreaHeight);
        staticStackDebugMetrics.setSeedlingNeededHeight(fComputeSeedlingNeededHeight);
        staticStackDebugMetrics.setTotalStaticNeeded(f2);
        staticStackDebugMetrics.setStaticStackCount(i);
        StaticStackEvaluation staticStackEvaluation = this.staticStackEvaluation;
        if (staticStackEvaluation == null) {
            staticStackEvaluation = new StaticStackEvaluation(f2 <= f, paddingBetweenElements, staticStackDebugMetrics);
            this.staticStackEvaluation = staticStackEvaluation;
        }
        staticStackEvaluation.setCanStaticStack(f2 <= f);
        staticStackEvaluation.setGapBetweenSeedlingAndNotif(paddingBetweenElements);
        staticStackEvaluation.setDebugMetrics(staticStackDebugMetrics);
        return staticStackEvaluation;
    }

    public final StaticStackEvaluation evaluateTopStackStaticStackFit(List<? extends ExpandableView> list, List<? extends ExpandableView> list2, float f) {
        return evaluateStaticStackFit(list, list2, f, list.size());
    }

    public final StaticStackRequest getStaticStackRequest(StackPhase stackPhase, List list, float f, float f2, float f3, float f4, List list2) {
        StaticStackRequest staticStackRequest = this.staticStackRequest;
        if (staticStackRequest == null) {
            StaticStackRequest staticStackRequest2 = new StaticStackRequest(stackPhase, list, f, f2, f3, f4, list2);
            this.staticStackRequest = staticStackRequest2;
            staticStackRequest = staticStackRequest2;
        }
        staticStackRequest.setPhase(stackPhase);
        staticStackRequest.setNormalStackViews(list);
        staticStackRequest.setBottomBorder(f);
        staticStackRequest.setNotificationDragAmount(f2);
        staticStackRequest.setTopBorder(f3);
        staticStackRequest.setFingerprintTop(f4);
        staticStackRequest.setSeedlingViews(list2);
        return staticStackRequest;
    }

    public final void pinSinkingHiddenAtBottom(List<? extends ExpandableView> list, float f) {
        this.staticStackAlgorithm.pinSinkingHiddenAtBottom(list, f);
    }

    public final void releaseCachedStackRequestViews() {
        StaticStackRequest staticStackRequest = this.staticStackRequest;
        if (staticStackRequest != null) {
            staticStackRequest.setNormalStackViews(CollectionsKt__CollectionsKt.emptyList());
            staticStackRequest.setSeedlingViews(null);
        }
        DynamicStackRequest dynamicStackRequest = this.dynamicStackRequest;
        if (dynamicStackRequest != null) {
            dynamicStackRequest.setExpandableViews(CollectionsKt__CollectionsKt.emptyList());
        }
        this.sinkingMixedViewsScratch.clear();
    }
}
