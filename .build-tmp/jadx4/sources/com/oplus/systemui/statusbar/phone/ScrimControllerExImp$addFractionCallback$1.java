package com.oplus.systemui.statusbar.phone;

import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.ScrimState;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScrimControllerExImp$addFractionCallback$1 implements PanelAnimationEx.ExpandedFractionChangeListener {
    public /* synthetic */ ScrimControllerExImp this$0;

    public final void onBlurFractionChange() {
        ScrimControllerExImp scrimControllerExImp = this.this$0;
        ScrimControllerExImp.Companion companion = ScrimControllerExImp.Companion;
        ScrimView notificationsScrim = scrimControllerExImp.getScrimController().getNotificationsScrim();
        if (notificationsScrim != null) {
            notificationsScrim.invalidate();
        }
        ScrimController scrimController = scrimControllerExImp.getScrimController();
        if (scrimController.mExpansionAffectsAlpha || scrimController.getState() != ScrimState.KEYGUARD) {
            scrimControllerExImp.getScrimController().applyAndDispatchState();
        }
    }

    public final void onFractionChanged(float f) {
    }
}
