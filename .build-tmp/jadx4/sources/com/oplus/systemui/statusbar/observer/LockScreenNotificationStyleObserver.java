package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationStyleObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final String KEY = "lock_screen_notification_style";
    private static final String TAG = "LockScreenNotificationStyleObserver";
    public int lockScreenNotificationStyle;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return KEY;
    }

    public final int getLockScreenNotificationStyle() {
        return this.lockScreenNotificationStyle;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        update$3(getContext());
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        update$3(context);
    }

    public final void update$3(Context context) {
        if (context == null) {
            return;
        }
        this.lockScreenNotificationStyle = AbsSettingsValueProxy.Companion.getSystemIntValue(context, KEY, 0);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.lockScreenNotificationStyle, "updateLockScreenNotificationStyle: ", "LockScreenNotificationStyleObserver-->", "SystemUi--Notification");
        }
    }
}
