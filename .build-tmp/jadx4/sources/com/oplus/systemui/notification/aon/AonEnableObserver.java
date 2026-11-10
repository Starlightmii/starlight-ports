package com.oplus.systemui.notification.aon;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AonEnableObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    public static final String SMART_ANTI_VOYEUR_ENABLE = "smart_anti_voyeur_enable";
    private static final String TAG = "AonEnableObserver";
    public boolean isAonEnabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return SMART_ANTI_VOYEUR_ENABLE;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Uri getUri(Context context) {
        return MultiUserContentHelper.Companion.getSecureUri(getKey());
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "AonEnableObserver-->onChange");
        }
        this.isAonEnabled = AonEnableSettingsValueProxy.Companion.getAonSwitchState(context);
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        this.isAonEnabled = AonEnableSettingsValueProxy.Companion.getAonSwitchState(context);
    }
}
