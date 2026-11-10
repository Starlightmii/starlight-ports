package com.oplus.systemui.notification.lockscreen.stack.updater;

import android.content.Context;
import android.os.SystemProperties;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater;
import com.oplus.systemui.notification.lockscreen.stack.StackCardControl;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StackAlgorithmManager;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StaticStackDebugMetrics;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StaticStackEvaluation;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationRectManager;
import com.oplus.systemui.notification.lockscreen.statistic.LockScreenStatisticUtil;
import com.oplus.systemui.notification.lockscreen.statistic.LockScreenStatisticUtil$;
import com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayInteractor;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplus.systemui.statusbar.util.KeyguardNotificationUploadUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SinkingStackUpdater implements IStackViewUpdater {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SinkingStackUpdater";
    private final Lazy ambientState;
    private final Context context;
    public boolean isStacked;
    private final LockScreenNotificationRectManager lockNotificationRectManager;
    private final LogBuffer logBuffer;
    private final Lazy npvc;
    private final Lazy nsslExt;
    private final OplusNotificationOverlayInteractor overlayInteractor;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final OplusLockscreenShadeTransitionControllerEx shadeTransitionControllerEx;
    private final StackAlgorithmManager stackAlgorithmManager;
    private final ArrayList<ExpandableView> visibleViewsScratch = new ArrayList<>();
    private final ArrayList<ExpandableView> seedlingViews = new ArrayList<>();
    private final ArrayList<ExpandableView> notificationViews = new ArrayList<>();
    private StackCardControl.StackType stackType = StackCardControl.StackType.NOT_STACK;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SinkingStackUpdater(Context context, Lazy lazy, Lazy lazy2, OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, StackAlgorithmManager stackAlgorithmManager, Lazy lazy3, LockScreenNotificationRectManager lockScreenNotificationRectManager, LogBuffer logBuffer) {
        this.context = context;
        this.npvc = lazy;
        this.ambientState = lazy2;
        this.shadeTransitionControllerEx = oplusLockscreenShadeTransitionControllerEx;
        this.overlayInteractor = oplusNotificationOverlayInteractor;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.stackAlgorithmManager = stackAlgorithmManager;
        this.nsslExt = lazy3;
        this.lockNotificationRectManager = lockScreenNotificationRectManager;
        this.logBuffer = logBuffer;
    }

    private final int getScreenHeight() {
        return this.context.getResources().getConfiguration().windowConfiguration.getBounds().height();
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
        return 3;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public void setStackType(StackCardControl.StackType stackType) {
        StackCardControl.StackType stackType2 = this.stackType;
        if (stackType == stackType2) {
            return;
        }
        if (stackType2 == StackCardControl.StackType.STATIC_STACK && stackType == StackCardControl.StackType.DYNAMIC_STACK) {
            int size = this.seedlingViews.size();
            LockScreenStatisticUtil lockScreenStatisticUtil = LockScreenStatisticUtil.INSTANCE;
            LockScreenStatisticUtil$.ExternalSyntheticLambda4 externalSyntheticLambda4 = new LockScreenStatisticUtil$.ExternalSyntheticLambda4();
            externalSyntheticLambda4.f$0 = size;
            VarHandle.storeStoreFence();
            lockScreenStatisticUtil.getClass();
            LockScreenStatisticUtil.postThread(externalSyntheticLambda4);
        }
        this.stackType = stackType;
    }

    @Override // com.oplus.systemui.notification.lockscreen.stack.IStackViewUpdater
    public void updateToExt(List<? extends ExpandableView> list) {
        StaticStackDebugMetrics debugMetrics;
        StaticStackDebugMetrics debugMetrics2;
        StaticStackDebugMetrics debugMetrics3;
        SinkingStackUpdater sinkingStackUpdater = this;
        float lockscreenNotificationDisplayHeight$default = NotificationStackScrollLayoutExt.getLockscreenNotificationDisplayHeight$default((NotificationStackScrollLayoutExt) sinkingStackUpdater.nsslExt.get());
        float stackedBottomBorder = ((NotificationStackScrollLayoutExtImpl) sinkingStackUpdater.nsslExt.get()).getStackedBottomBorder();
        float f = stackedBottomBorder - lockscreenNotificationDisplayHeight$default;
        for (ExpandableView expandableView : list) {
            if (!((ViewState) expandableView.getViewState()).hidden && !(expandableView instanceof EmptyShadeView)) {
                sinkingStackUpdater.visibleViewsScratch.add(expandableView);
                if (expandableView instanceof OplusCustomRow) {
                    sinkingStackUpdater.seedlingViews.add(expandableView);
                } else {
                    sinkingStackUpdater.notificationViews.add(expandableView);
                }
            }
        }
        sinkingStackUpdater.stackAlgorithmManager.pinSinkingHiddenAtBottom(list, stackedBottomBorder);
        if (sinkingStackUpdater.visibleViewsScratch.isEmpty()) {
            sinkingStackUpdater.isStacked = false;
        } else {
            StaticStackEvaluation staticStackEvaluationEvaluateSinkingStackStaticStackFit = !sinkingStackUpdater.seedlingViews.isEmpty() ? sinkingStackUpdater.stackAlgorithmManager.evaluateSinkingStackStaticStackFit(sinkingStackUpdater.notificationViews, sinkingStackUpdater.seedlingViews, lockscreenNotificationDisplayHeight$default) : null;
            boolean z = true;
            if (staticStackEvaluationEvaluateSinkingStackStaticStackFit != null ? staticStackEvaluationEvaluateSinkingStackStaticStackFit.getCanStaticStack() : true) {
                sinkingStackUpdater.isStacked = sinkingStackUpdater.notificationViews.size() > 1;
                sinkingStackUpdater.stackAlgorithmManager.applySinkingStaticLayout(sinkingStackUpdater.notificationViews, stackedBottomBorder, sinkingStackUpdater.shadeTransitionControllerEx.getNotificationDragAmount(), f, sinkingStackUpdater.lockNotificationRectManager.getCurrHighFingerprintIconTop(), sinkingStackUpdater.seedlingViews);
                sinkingStackUpdater.setStackType(sinkingStackUpdater.isStacked ? StackCardControl.StackType.STATIC_STACK : StackCardControl.StackType.NOT_STACK);
                StackCardControl.SummaryInfo summaryInfoUpdateKgNotificationSummaryInfo = StackCardControl.Companion.updateKgNotificationSummaryInfo(sinkingStackUpdater.notificationViews);
                sinkingStackUpdater.overlayInteractor.setOverlayInfo(summaryInfoUpdateKgNotificationSummaryInfo != null ? summaryInfoUpdateKgNotificationSummaryInfo.getExpandableView() : null, summaryInfoUpdateKgNotificationSummaryInfo != null ? summaryInfoUpdateKgNotificationSummaryInfo.getInfo() : null);
            } else {
                sinkingStackUpdater.stackAlgorithmManager.applySinkingDynamicLayout(sinkingStackUpdater.seedlingViews, sinkingStackUpdater.notificationViews, f, stackedBottomBorder);
                sinkingStackUpdater.isStacked = true;
                sinkingStackUpdater.setStackType(StackCardControl.StackType.DYNAMIC_STACK);
                z = false;
            }
            StackCardControl.Companion.getClass();
            if (SystemProperties.getBoolean("ro.oplus.dailyota", false) || StringsKt__StringsKt.contains(SystemProperties.get("ro.build.version.ota"), "PRE", false)) {
                LogBuffer logBuffer = sinkingStackUpdater.logBuffer;
                LogLevel logLevel = LogLevel.DEBUG;
                String str = z ? "static" : "dynamic";
                float notificationDragAmount = sinkingStackUpdater.shadeTransitionControllerEx.getNotificationDragAmount();
                float fComputeExpansionFraction = ((NotificationPanelViewController) sinkingStackUpdater.npvc.get()).getQsController().computeExpansionFraction();
                boolean zIsOnKeyguard = ((AmbientState) sinkingStackUpdater.ambientState.get()).isOnKeyguard();
                Object value = sinkingStackUpdater.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue();
                int topPadding = ((AmbientState) sinkingStackUpdater.ambientState.get()).getTopPadding();
                int keyguardNotificationStaticPadding = ((NotificationPanelViewController) sinkingStackUpdater.npvc.get()).getKeyguardNotificationStaticPadding();
                int screenHeight = sinkingStackUpdater.getScreenHeight();
                int size = sinkingStackUpdater.seedlingViews.size();
                StaticStackEvaluation staticStackEvaluation = staticStackEvaluationEvaluateSinkingStackStaticStackFit;
                int size2 = sinkingStackUpdater.notificationViews.size();
                Float fValueOf = (staticStackEvaluation == null || (debugMetrics3 = staticStackEvaluation.getDebugMetrics()) == null) ? null : Float.valueOf(debugMetrics3.getStaticArea());
                Float fValueOf2 = (staticStackEvaluation == null || (debugMetrics2 = staticStackEvaluation.getDebugMetrics()) == null) ? null : Float.valueOf(debugMetrics2.getSeedlingNeededHeight());
                Float fValueOf3 = staticStackEvaluation != null ? Float.valueOf(staticStackEvaluation.getGapBetweenSeedlingAndNotif()) : null;
                Float fValueOf4 = (staticStackEvaluation == null || (debugMetrics = staticStackEvaluation.getDebugMetrics()) == null) ? null : Float.valueOf(debugMetrics.getTotalStaticNeeded());
                Float f2 = fValueOf3;
                Float f3 = fValueOf;
                StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m("[Sinking] branch=", str, ",bottomBorder=", stackedBottomBorder, ", topBorder=");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, ", displayArea=", lockscreenNotificationDisplayHeight$default, ",  dragAmount=");
                VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, notificationDragAmount, ", qsExpFraction=", fComputeExpansionFraction, ", isOnKg=");
                sbM.append(zIsOnKeyguard);
                sbM.append(", separateEnabled=");
                sbM.append(value);
                sbM.append(", topPadding=");
                RectList$.ExternalSyntheticOutline0.m(topPadding, keyguardNotificationStaticPadding, ", staticPadding=", ", screenHeight=", sbM);
                RectList$.ExternalSyntheticOutline0.m(screenHeight, size, ", seedling=", ", notif=", sbM);
                sbM.append(size2);
                sbM.append(", staticArea=");
                sbM.append(f3);
                sbM.append(", seedlingNeededHeight=");
                sbM.append(fValueOf2);
                sbM.append(", gapBetweenSeedlingAndNotif=");
                sbM.append(f2);
                sbM.append(", totalStaticNeeded=");
                sbM.append(fValueOf4);
                logBuffer.log(TAG, logLevel, sbM.toString(), (Throwable) null);
                sinkingStackUpdater = this;
            }
        }
        if (!sinkingStackUpdater.visibleViewsScratch.isEmpty()) {
            KeyguardNotificationUploadUtil.Companion.getInstance().collectStackNotificationData(sinkingStackUpdater.visibleViewsScratch);
        }
        sinkingStackUpdater.visibleViewsScratch.clear();
        sinkingStackUpdater.seedlingViews.clear();
        sinkingStackUpdater.notificationViews.clear();
    }
}
