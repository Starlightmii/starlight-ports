package com.oplus.systemui.notification.clearall;

import androidx.appcompat.widget.AppCompatImageView;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplusos.systemui.common.rendercache.RenderBooster;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ClearAllController$elementShowStateChangeListener$1 implements PanelAnimationEx.ElementShowingChangeListener {
    public /* synthetic */ ClearAllController this$0;

    public final void onElementShowingChanged() {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "ClearAllController", "onElementShowingChanged", null, 8);
        this.this$0.updateVisibility(true);
    }

    public final void onFirstElementShowingChanged() {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "ClearAllController", "onFirstElementShowingChanged", null, 8);
    }

    public final void onShadeShowingChanged(int i, boolean z) {
        RenderBooster renderBoosterEnsureRenderBooster$default;
        RenderBooster renderBoosterEnsureRenderBooster$default2;
        ClearAllController clearAllController = this.this$0;
        if (z && i == 0) {
            if (clearAllController.clearAll != null && !ScrimUtil.isBAnimLevel() && !GaussBlurUtils.isGaussBlurDisabled()) {
                ScrimControllerEx.Companion.getClass();
                if (!ScrimControllerEx.isThirdTheme) {
                    AppCompatImageView appCompatImageView = clearAllController.clearAll;
                    if (appCompatImageView == null || (renderBoosterEnsureRenderBooster$default2 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, appCompatImageView, null, 3)) == null) {
                        return;
                    }
                    RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default2, 9, false, 6);
                    return;
                }
            }
            AppCompatImageView appCompatImageView2 = clearAllController.clearAll;
            if (appCompatImageView2 == null || (renderBoosterEnsureRenderBooster$default = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, appCompatImageView2, null, 3)) == null) {
                return;
            }
            RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default, 1, false, 6);
        }
    }
}
