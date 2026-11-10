package com.oplus.systemui.keyguard.wallpaper.uiengine;

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
public final class KeyguardEngineContainerController$mHostCallback$1 implements HostCallback {
    public Intent mIntent;
    public /* synthetic */ KeyguardEngineContainerController this$0;

    public final void deliverIntent(Intent intent, PendingIntent pendingIntent) {
        KgdLogUtil.i("KeyguardEngineContainer", "intent = " + intent);
        this.mIntent = intent;
    }

    public final void keyguardDone(boolean z, int i) {
        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
        OplusLockIconView$.ExternalSyntheticOutline0.m(i, "keyguardDone actionAfterKeyguardDone = ", "KeyguardEngineContainer");
        Intent intentCreateOplusKeyguardIntent = i == 100 ? this.mIntent : KeyguardUtils.Companion.createOplusKeyguardIntent(keyguardEngineContainerController.getMContext(), i);
        OplusLockIconView$.ExternalSyntheticOutline0.m("keyguardDone intent = ", "KeyguardEngineContainer", intentCreateOplusKeyguardIntent != null);
        if (intentCreateOplusKeyguardIntent != null) {
            keyguardEngineContainerController.getMActivityStarter().startActivity(intentCreateOplusKeyguardIntent, true);
            return;
        }
        KeyguardViewMediator.5 viewMediatorCallback = ((KeyguardViewMediator) keyguardEngineContainerController.getKeyguardViewMediator().get()).getViewMediatorCallback();
        if (viewMediatorCallback != null) {
            KgdLogUtil.i("KeyguardViewMediator", "dismissKeyguard callback");
            viewMediatorCallback.this$0.dismiss((IKeyguardDismissCallback) null, (CharSequence) null);
        }
    }

    public final void userActivity() {
    }
}
