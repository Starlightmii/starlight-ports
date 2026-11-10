package com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic;

import android.app.PendingIntent;
import android.content.Intent;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.base.HostCallback;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialEngineContainerController$mHostCallback$1 implements HostCallback {
    public Intent mIntent;
    public /* synthetic */ PictorialEngineContainerController this$0;

    public final void deliverIntent(Intent intent, PendingIntent pendingIntent) {
        KgdLogUtil.i("PictorialEngineContainerController", "intent = " + intent);
        this.mIntent = intent;
    }

    public final void keyguardDone(boolean z, int i) {
        PictorialEngineContainerController pictorialEngineContainerController = this.this$0;
        OplusLockIconView$.ExternalSyntheticOutline0.m(i, "keyguardDone actionAfterKeyguardDone = ", "PictorialEngineContainerController");
        Intent intentCreateOplusKeyguardIntent = i == 100 ? this.mIntent : KeyguardUtils.Companion.createOplusKeyguardIntent(pictorialEngineContainerController.getMContext(), i);
        OplusLockIconView$.ExternalSyntheticOutline0.m("keyguardDone intent = ", "PictorialEngineContainerController", intentCreateOplusKeyguardIntent != null);
        if (intentCreateOplusKeyguardIntent != null) {
            pictorialEngineContainerController.getMActivityStarter().startActivity(intentCreateOplusKeyguardIntent, true);
            return;
        }
        KeyguardViewMediator.5 r3 = pictorialEngineContainerController.mViewMediatorCallback;
        if (r3 != null) {
            KgdLogUtil.i("KeyguardViewMediator", "dismissKeyguard callback");
            r3.this$0.dismiss((IKeyguardDismissCallback) null, (CharSequence) null);
        }
    }

    public final void userActivity() {
    }
}
