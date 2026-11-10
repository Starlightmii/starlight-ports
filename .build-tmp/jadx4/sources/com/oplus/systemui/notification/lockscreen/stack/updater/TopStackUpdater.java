package com.oplus.systemui.notification.lockscreen.stack.updater;

import android.os.SystemProperties;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater;
import com.oplus.systemui.notification.lockscreen.stack.StackCardControl;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StackAlgorithmManager;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StaticStackAlgorithm;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StaticStackDebugMetrics;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StaticStackEvaluation;
import com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayInteractor;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TopStackUpdater implements IStackViewUpdater {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TopStackUpdater";
    public boolean isStacked;
    private final LogBuffer logBuffer;
    private final Lazy nsslExt;
    private final OplusNotificationOverlayInteractor overlayInteractor;
    private final OplusLockscreenShadeTransitionControllerEx shadeTransitionControllerEx;
    private final StackAlgorithmManager stackAlgorithmManager;
    private StackCardControl.StackType stackType = StackCardControl.StackType.NOT_STACK;
    private final ArrayList<ExpandableView> seedlingViews = new ArrayList<>();
    private final ArrayList<ExpandableView> notificationViews = new ArrayList<>();
    private final GroupExpansionStackPolicy groupExpansionStackPolicy = new GroupExpansionStackPolicy();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class GroupExpansionStackPolicy {
        public boolean shouldUseDynamic;

        /* JADX WARN: Code duplicated, block: B:10:0x0021  */
        public final boolean shouldUseDynamicStack(List<? extends ExpandableView> list, float f) {
            int iNextIndex;
            boolean zIsGroupExpanded;
            ListIterator<? extends ExpandableView> listIterator = list.listIterator(list.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    iNextIndex = -1;
                    break;
                }
                ExpandableNotificationRow expandableNotificationRow = (ExpandableView) listIterator.previous();
                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow instanceof ExpandableNotificationRow ? expandableNotificationRow : null;
                if (expandableNotificationRow2 == null) {
                    zIsGroupExpanded = false;
                } else if (expandableNotificationRow2.mIsSummaryWithChildren) {
                    zIsGroupExpanded = expandableNotificationRow2.isGroupExpanded();
                } else if (expandableNotificationRow2.isExpanded(false) && expandableNotificationRow2.mHasUserChangedExpansion) {
                    zIsGroupExpanded = true;
                } else {
                    zIsGroupExpanded = false;
                }
                if (zIsGroupExpanded) {
                    iNextIndex = listIterator.nextIndex();
                    break;
                }
            }
            ExpandableView expandableView = (ExpandableView) CollectionsKt___CollectionsKt.getOrNull(list, iNextIndex);
            int size = list.size() - (iNextIndex + 1);
            if (expandableView != null) {
                float yTranslation = expandableView.getViewState().getYTranslation() + expandableView.getIntrinsicHeight();
                if (size != 0) {
                    if (size != 1) {
                        StaticStackAlgorithm.Companion companion = StaticStackAlgorithm.Companion;
                        yTranslation = companion.getSecondStackedNotificationVisibleHeight() + companion.getFirstStackedNotificationVisibleHeight() + yTranslation;
                    } else {
                        yTranslation += StaticStackAlgorithm.Companion.getFirstStackedNotificationVisibleHeight();
                    }
                }
                boolean z = !(yTranslation <= f);
                boolean z2 = this.shouldUseDynamic;
                if (z2 != z) {
                    StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("update shouldUseDynamic, ", " -> ", ", bottomRemainingNotificationCount: ", z2, z);
                    sbM.append(size);
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TopStackUpdater.TAG, sbM.toString(), null, 8);
                    this.shouldUseDynamic = z;
                }
            } else if (this.shouldUseDynamic) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TopStackUpdater.TAG, "update shouldUseDynamic to false", null, 8);
                this.shouldUseDynamic = false;
            }
            return this.shouldUseDynamic;
        }
    }

    public TopStackUpdater(OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor, StackAlgorithmManager stackAlgorithmManager, Lazy lazy, LogBuffer logBuffer) {
        this.shadeTransitionControllerEx = oplusLockscreenShadeTransitionControllerEx;
        this.overlayInteractor = oplusNotificationOverlayInteractor;
        this.stackAlgorithmManager = stackAlgorithmManager;
        this.nsslExt = lazy;
        this.logBuffer = logBuffer;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public boolean getHasNotificationStacked() {
        return this.isStacked;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public StackCardControl.StackType getStackType() {
        return this.stackType;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public int getToExtState() {
        return 0;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public void setStackType(StackCardControl.StackType stackType) {
        this.stackType = stackType;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public void updateToExt(List<? extends ExpandableView> list) {
        float stackedBottomBorder = ((NotificationStackScrollLayoutExtImpl) this.nsslExt.get()).getStackedBottomBorder();
        boolean zShouldUseDynamicStack = this.groupExpansionStackPolicy.shouldUseDynamicStack(list, stackedBottomBorder);
        for (ExpandableView expandableView : list) {
            if (!((ViewState) expandableView.getViewState()).hidden && !(expandableView instanceof EmptyShadeView)) {
                if (expandableView instanceof OplusCustomRow) {
                    this.seedlingViews.add(expandableView);
                } else {
                    this.notificationViews.add(expandableView);
                }
            }
        }
        float lockscreenNotificationDisplayHeight$default = NotificationStackScrollLayoutExt.getLockscreenNotificationDisplayHeight$default((NotificationStackScrollLayoutExt) this.nsslExt.get());
        StaticStackEvaluation staticStackEvaluationEvaluateTopStackStaticStackFit = !this.seedlingViews.isEmpty() ? this.stackAlgorithmManager.evaluateTopStackStaticStackFit(this.notificationViews, this.seedlingViews, lockscreenNotificationDisplayHeight$default) : null;
        boolean z = zShouldUseDynamicStack || !(staticStackEvaluationEvaluateTopStackStaticStackFit == null || staticStackEvaluationEvaluateTopStackStaticStackFit.getCanStaticStack());
        StackAlgorithmManager stackAlgorithmManager = this.stackAlgorithmManager;
        if (z) {
            this.isStacked = stackAlgorithmManager.applyDynamic(list, stackedBottomBorder);
            setStackType(StackCardControl.StackType.DYNAMIC_STACK);
        } else {
            boolean zApplyStatic = stackAlgorithmManager.applyStatic(list, stackedBottomBorder, this.shadeTransitionControllerEx.getNotificationDragAmount());
            if (zApplyStatic) {
                StackCardControl.SummaryInfo summaryInfoUpdateKgNotificationSummaryInfo = StackCardControl.Companion.updateKgNotificationSummaryInfo(list);
                this.overlayInteractor.setOverlayInfo(summaryInfoUpdateKgNotificationSummaryInfo != null ? summaryInfoUpdateKgNotificationSummaryInfo.getExpandableView() : null, summaryInfoUpdateKgNotificationSummaryInfo != null ? summaryInfoUpdateKgNotificationSummaryInfo.getInfo() : null);
            }
            this.isStacked = zApplyStatic;
            setStackType(zApplyStatic ? StackCardControl.StackType.STATIC_STACK : StackCardControl.StackType.NOT_STACK);
        }
        StackCardControl.Companion.getClass();
        if (SystemProperties.getBoolean("ro.oplus.dailyota", false) || StringsKt__StringsKt.contains(SystemProperties.get("ro.build.version.ota"), "PRE", false)) {
            float f = stackedBottomBorder - lockscreenNotificationDisplayHeight$default;
            StaticStackDebugMetrics debugMetrics = staticStackEvaluationEvaluateTopStackStaticStackFit != null ? staticStackEvaluationEvaluateTopStackStaticStackFit.getDebugMetrics() : null;
            String str = !z ? "static" : "dynamic";
            int size = this.seedlingViews.size();
            int size2 = this.notificationViews.size();
            Boolean boolValueOf = staticStackEvaluationEvaluateTopStackStaticStackFit != null ? Boolean.valueOf(staticStackEvaluationEvaluateTopStackStaticStackFit.getCanStaticStack()) : null;
            Float fValueOf = debugMetrics != null ? Float.valueOf(debugMetrics.getStaticArea()) : null;
            Float fValueOf2 = debugMetrics != null ? Float.valueOf(debugMetrics.getSeedlingNeededHeight()) : null;
            Float fValueOf3 = staticStackEvaluationEvaluateTopStackStaticStackFit != null ? Float.valueOf(staticStackEvaluationEvaluateTopStackStaticStackFit.getGapBetweenSeedlingAndNotif()) : null;
            Float fValueOf4 = debugMetrics != null ? Float.valueOf(debugMetrics.getTotalStaticNeeded()) : null;
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m("[TopStack] branch=", str, ",bottomBorder=", stackedBottomBorder, ", topBorder=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, ", displayArea=", lockscreenNotificationDisplayHeight$default, ", seedling=");
            RectList$.ExternalSyntheticOutline0.m(size, size2, ", notif=", ", canStatic=", sbM);
            sbM.append(boolValueOf);
            sbM.append(", staticArea=");
            sbM.append(fValueOf);
            sbM.append(", seedlingFitH=");
            sbM.append(fValueOf2);
            sbM.append(", gap=");
            sbM.append(fValueOf3);
            sbM.append(", totalStatic=");
            sbM.append(fValueOf4);
            this.logBuffer.log(TAG, LogLevel.DEBUG, sbM.toString(), (Throwable) null);
        }
        this.seedlingViews.clear();
        this.notificationViews.clear();
    }
}
