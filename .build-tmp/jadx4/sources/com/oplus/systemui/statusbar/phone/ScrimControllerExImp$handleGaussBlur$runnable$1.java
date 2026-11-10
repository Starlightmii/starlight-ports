package com.oplus.systemui.statusbar.phone;

import android.util.Log;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.statusbar.phone.ScrimBlurStateUtilEx;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.ScrimState;
import com.oplus.systemui.statusbar.notification.util.OpNotificationUtils;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScrimControllerExImp$handleGaussBlur$runnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object this$0;

    public /* synthetic */ ScrimControllerExImp$handleGaussBlur$runnable$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ScrimControllerExImp scrimControllerExImp = (ScrimControllerExImp) this.this$0;
                ScrimControllerExImp.Companion companion = ScrimControllerExImp.Companion;
                if (scrimControllerExImp.getScrimController().getState() == ScrimState.BOUNCER_SCRIMMED && !((ScrimControllerExImp) this.this$0).getRunnerUnlock()) {
                    KgdLogUtil.w("ScrimControllerExImp", "clean blur from unlock but now in bouncer_scrim");
                } else {
                    if (((ScrimBlurStateUtilEx) ((ScrimBlurStateUtilsExImpl) ((ScrimControllerExImp) this.this$0).scrimBlurStateUtils.get())).isNeedClearScrimAlphaWhenUnlock) {
                        ((ScrimBlurStateUtilsExImpl) ((ScrimControllerExImp) this.this$0).scrimBlurStateUtils.get()).setFrontScrimBlurAmount(0.0f, "clearFrontScrimWhenUnlock runnable");
                    } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                        Log.d("SystemUi--Keyguard", "ScrimControllerExImp-->isNeedClearScrimAlphaWhenUnlock is false");
                    }
                    ((ScrimControllerExImp) this.this$0).setRunnerUnlock(false);
                }
                break;
            default:
                boolean zIsNormalScreen = OpNotificationUtils.isNormalScreen();
                ScrimController scrimController = (ScrimController) this.this$0;
                if (!zIsNormalScreen) {
                    ScrimView scrimInFront = scrimController.getScrimInFront();
                    if (scrimInFront != null) {
                        scrimInFront.setBackgroundResource(2131235749);
                    }
                } else {
                    ScrimView scrimInFront2 = scrimController.getScrimInFront();
                    if (scrimInFront2 != null) {
                        scrimInFront2.setBackgroundResource(2131235748);
                    }
                }
                break;
        }
    }
}
