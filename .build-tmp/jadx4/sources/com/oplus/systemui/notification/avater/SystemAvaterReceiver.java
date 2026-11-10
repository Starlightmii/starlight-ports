package com.oplus.systemui.notification.avater;

import com.oplusos.systemui.common.receiver.AbstractReceiver;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemAvaterReceiver extends AbstractReceiver {
    public static volatile SystemAvaterReceiver sInstance;
    public boolean mClickAvatarNotificationFlag;
    public int mStatusBarState;

    public static SystemAvaterReceiver getInstance() {
        if (sInstance == null) {
            synchronized (SystemAvaterReceiver.class) {
                try {
                    if (sInstance == null) {
                        SystemAvaterReceiver systemAvaterReceiver = new SystemAvaterReceiver();
                        systemAvaterReceiver.mStatusBarState = 0;
                        systemAvaterReceiver.mClickAvatarNotificationFlag = false;
                        SystemAvaterReceiver$$ExternalSyntheticLambda0 systemAvaterReceiver$$ExternalSyntheticLambda0 = new SystemAvaterReceiver$$ExternalSyntheticLambda0();
                        systemAvaterReceiver$$ExternalSyntheticLambda0.f$0 = systemAvaterReceiver;
                        VarHandle.storeStoreFence();
                        systemAvaterReceiver.addListener(systemAvaterReceiver$$ExternalSyntheticLambda0);
                        sInstance = systemAvaterReceiver;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{"android.intent.switch_user", "android.intent.action.USER_SWITCHED", "android.intent.action.USER_REMOVED"};
    }
}
