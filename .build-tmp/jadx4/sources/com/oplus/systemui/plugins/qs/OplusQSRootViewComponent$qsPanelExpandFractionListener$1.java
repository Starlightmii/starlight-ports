package com.oplus.systemui.plugins.qs;

import android.os.Trace;
import android.util.Log;
import android.view.View;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.util.JankUtils;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.NotificationShadeRegionHelper;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSRootViewComponent$qsPanelExpandFractionListener$1 implements QSPanelExpandFraction.ExpandedFractionChangeListener {
    public boolean hasExclusionDone;
    public /* synthetic */ OplusQSRootViewComponent this$0;

    public final boolean getHasExclusionDone() {
        return this.hasExclusionDone;
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onBlurFractionChange() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
        oplusQSRootViewComponent.stub.updateSeparateBlurExpansion(oplusQSRootViewComponent.expandedFraction, oplusQSRootViewComponent.isExpanded, oplusQSRootViewComponent.isTracking, oplusQSRootViewComponent.expandedHeight);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onBlurFractionEnd(boolean z) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
        float rawFraction = oplusQSRootViewComponent.getQsPanelAnimatorManager().getRawFraction();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            boolean zAccess$isNtfVisibleOnUnlock = OplusQSRootViewComponent.access$isNtfVisibleOnUnlock(oplusQSRootViewComponent);
            boolean ntIsFullCollapsed = oplusQSRootViewComponent.stub.getNtIsFullCollapsed();
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onBlurFractionEnd isExpanded=", " ", " rawFraction=", z, zAccess$isNtfVisibleOnUnlock);
            sbM.append(rawFraction);
            sbM.append(" NtIsFullCollapsed=");
            sbM.append(ntIsFullCollapsed);
            Log.d("SystemUi--QuickSettings", "OplusQSRootViewComponent-->".concat(sbM.toString()));
        }
        if (z && rawFraction == 1.0f) {
            oplusQSRootViewComponent.updateMoveExpandHeight(oplusQSRootViewComponent.getMaxPanelHeight(), 1.0f);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onFractionChanged(float f) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
        oplusQSRootViewComponent.curRawFraction = f;
        if (!OplusQSRootViewComponent.access$isNtfVisibleOnUnlock(oplusQSRootViewComponent) || f != 1.0f || !oplusQSRootViewComponent.isBlurFractionAnimationRunning()) {
            oplusQSRootViewComponent.updateMoveExpandHeight(oplusQSRootViewComponent.getMaxPanelHeight() * f, f);
        }
        if (oplusQSRootViewComponent.stub.getUseMotionBlur() && f < 0.2f && f > 0.0f && !this.hasExclusionDone) {
            this.hasExclusionDone = NotificationShadeRegionHelper.setTouchExclusionRegion(oplusQSRootViewComponent.isClosing && (!oplusQSRootViewComponent.stub.isKeyguardShowing() || oplusQSRootViewComponent.stub.isKeyguardOccluded()), ((OplusQSRootView) oplusQSRootViewComponent.mView).getRootView(), "onFractionChanged");
        }
        if (oplusQSRootViewComponent.curRawFraction <= 0.86f || oplusQSRootViewComponent.getQsPanelAnimatorManager().getGetCurCalculateFinalPosition() <= 0.86f) {
            return;
        }
        oplusQSRootViewComponent.yVelocity = 0.0f;
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onFractionEnd(boolean z) {
        boolean zIsAnimating;
        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
        if (z) {
            if (oplusQSRootViewComponent.panelClosedOnDown && oplusQSRootViewComponent.getStatusBarContainer().getTranslationY() <= 0.0f) {
                if (oplusQSRootViewComponent.isLowGaussianLevel) {
                    QSPanelAnimatorManager qsPanelAnimatorManager = oplusQSRootViewComponent.getQsPanelAnimatorManager();
                    View view = oplusQSRootViewComponent.otherTilesContainerView;
                    zIsAnimating = qsPanelAnimatorManager.isAnimating(view != null ? view : null);
                } else {
                    zIsAnimating = oplusQSRootViewComponent.getQsPanelAnimatorManager().isAnimating(oplusQSRootViewComponent.qsBottomComponent.getAnimatorView(0));
                }
                if (!zIsAnimating) {
                    oplusQSRootViewComponent.tryStopGfx();
                } else if (oplusQSRootViewComponent.isQsPullTraceStart) {
                    JankUtils.gfxSceneEnd(oplusQSRootViewComponent.getContext(), new int[]{7});
                    Trace.asyncTraceEnd(8L, "QS PULL", 7);
                    oplusQSRootViewComponent.isQsPullTraceStart = false;
                }
            }
            NotificationShadeRegionHelper.setTouchExclusionRegion(false, ((OplusQSRootView) oplusQSRootViewComponent.mView).getRootView(), "qs onFractionEnd");
        } else {
            if (!oplusQSRootViewComponent.hasSceneQSCloseEnd) {
                LogUtil.i$default(LogUtil.TAG_QS, "OplusQSRootViewComponent", "gfxSceneEnd(), onFractionEnd SCENE_QS_CLOSE", null, 8);
                JankUtils.gfxSceneEnd(oplusQSRootViewComponent.getContext(), new int[]{9});
                oplusQSRootViewComponent.hasSceneQSCloseEnd = true;
            }
            if (oplusQSRootViewComponent.collapsedEndBlock != null) {
                LogUtil.i$default(LogUtil.TAG_QS, "OplusQSRootViewComponent", "rawFraction 0f block invoke", null, 8);
                Function0 function0 = oplusQSRootViewComponent.collapsedEndBlock;
                if (function0 != null) {
                    function0.invoke();
                }
                oplusQSRootViewComponent.collapsedEndBlock = null;
            }
        }
        OplusQSRootViewComponent.Companion companion = OplusQSRootViewComponent.Companion;
        if (oplusQSRootViewComponent.mView.getVisibility() == 4) {
            oplusQSRootViewComponent.doTileZoneScale(true, false);
            oplusQSRootViewComponent.getQsPanelAnimatorManager().resetSpring();
            QSPanelAnimatorManager qsPanelAnimatorManager2 = oplusQSRootViewComponent.getQsPanelAnimatorManager();
            qsPanelAnimatorManager2.setFirstElementVisible(true, false);
            qsPanelAnimatorManager2.setSecondElementVisible(true, false);
        }
        this.hasExclusionDone = false;
    }
}
