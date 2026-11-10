package com.oplus.systemui.privacy;

import com.oplus.transition.OplusAnimationController;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPrivacyDotViewControllerImpl$systemStatusAnimationCallback$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ OplusPrivacyDotViewControllerImpl f$0;
    public /* synthetic */ int f$1;
    public /* synthetic */ String f$2;

    public final Object invoke() {
        OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = this.f$0;
        int i = this.f$1;
        String str = this.f$2;
        OplusPrivacyDotViewControllerImpl.access$addWakefulnessObserverIfNeeded(oplusPrivacyDotViewControllerImpl);
        synchronized (oplusPrivacyDotViewControllerImpl.lock) {
            ViewState viewState = oplusPrivacyDotViewControllerImpl.nextViewState;
            oplusPrivacyDotViewControllerImpl.setNextViewState(viewState.copy((24575 & 1) != 0 ? viewState.systemPrivacyEventIsActive : true, (24575 & 2) != 0 ? viewState.persistentDotFillColorRes : i, (24575 & 4) != 0 ? viewState.shadeExpanded : false, (24575 & 8) != 0 ? viewState.needShow : false, (24575 & 16) != 0 ? viewState.wakeAllowsPrivacyDot : false, (24575 & 32) != 0 ? viewState.rtl : false, (24575 & 64) != 0 ? viewState.rotation : 0, (24575 & 128) != 0 ? viewState.height : 0, (24575 & 256) != 0 ? viewState.x : 0, (24575 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? viewState.y : 0, (24575 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? viewState.cornerIndex : 0, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? viewState.designatedCorner : null, (24575 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? viewState.contentDescription : str, (24575 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? viewState.chipAnimationStart : false, (24575 & 16384) != 0 ? viewState.preDraw : false));
        }
        return Unit.INSTANCE;
    }
}
