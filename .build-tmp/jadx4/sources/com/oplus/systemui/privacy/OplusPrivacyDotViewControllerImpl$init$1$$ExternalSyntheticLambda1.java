package com.oplus.systemui.privacy;

import com.oplus.transition.OplusAnimationController;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPrivacyDotViewControllerImpl f$0;

    public /* synthetic */ OplusPrivacyDotViewControllerImpl$init$1$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.f$0;
        switch (i) {
            case 0:
                OplusPrivacyDotViewControllerImpl.access$tryApplyDisplayConfigIfStable(oplusPrivacyDotViewControllerImpl);
                return Unit.INSTANCE;
            case 1:
                OplusPrivacyDotViewControllerImpl.access$handleDensityOrFontScaleChanged(oplusPrivacyDotViewControllerImpl);
                return Unit.INSTANCE;
            case 2:
                OplusPrivacyDotViewControllerImpl.access$updateStatusBarState(oplusPrivacyDotViewControllerImpl);
                return Unit.INSTANCE;
            case 3:
                OplusPrivacyDotViewControllerImpl.access$updateStatusBarState(oplusPrivacyDotViewControllerImpl);
                return Unit.INSTANCE;
            case 4:
                synchronized (oplusPrivacyDotViewControllerImpl.lock) {
                    ViewState viewState = oplusPrivacyDotViewControllerImpl.nextViewState;
                    oplusPrivacyDotViewControllerImpl.setNextViewState(viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : false, (24575 & 64) != 0 ? viewState.rotation : 0, (24575 & 128) != 0 ? viewState.height : 0, (24575 & 256) != 0 ? viewState.x : 0, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : false));
                }
                return Unit.INSTANCE;
            case 5:
                synchronized (oplusPrivacyDotViewControllerImpl.lock) {
                    ViewState viewState2 = oplusPrivacyDotViewControllerImpl.nextViewState;
                    oplusPrivacyDotViewControllerImpl.setNextViewState(viewState2.copy((24575 & 1) != 0 ? viewState2.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState2.persistentDotFillColorRes : 0, (24575 & 4) != 0 ? viewState2.shadeExpanded : false, (24575 & 8) != 0 ? viewState2.needShow : false, (24575 & 16) != 0 ? viewState2.wakeAllowsPrivacyDot : true, (24575 & 32) != 0 ? viewState2.rtl : false, (24575 & 64) != 0 ? viewState2.rotation : 0, (24575 & 128) != 0 ? viewState2.height : 0, (24575 & 256) != 0 ? viewState2.x : 0, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState2.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState2.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState2.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState2.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState2.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState2.preDraw : false));
                }
                return Unit.INSTANCE;
            default:
                OplusPrivacyDotViewControllerImpl.access$removeWakefulnessObserverIfNeeded(oplusPrivacyDotViewControllerImpl);
                synchronized (oplusPrivacyDotViewControllerImpl.lock) {
                    ViewState viewState3 = oplusPrivacyDotViewControllerImpl.nextViewState;
                    oplusPrivacyDotViewControllerImpl.setNextViewState(viewState3.copy((24575 & 1) != 0 ? viewState3.systemPrivacyEventIsActive : false, (24575 & 2) != 0 ? viewState3.persistentDotFillColorRes : 2131103174, (24575 & 4) != 0 ? viewState3.shadeExpanded : false, (24575 & 8) != 0 ? viewState3.needShow : false, (24575 & 16) != 0 ? viewState3.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState3.rtl : false, (24575 & 64) != 0 ? viewState3.rotation : 0, (24575 & 128) != 0 ? viewState3.height : 0, (24575 & 256) != 0 ? viewState3.x : 0, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState3.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState3.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState3.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState3.contentDescription : null, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState3.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState3.preDraw : false));
                }
                return Unit.INSTANCE;
        }
    }
}
