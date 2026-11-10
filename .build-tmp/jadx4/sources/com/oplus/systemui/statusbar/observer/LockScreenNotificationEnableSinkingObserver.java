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
public final class LockScreenNotificationEnableSinkingObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final String KEY = "lock_screen_notification_stack_switch";
    private static final String TAG = "LockScreenNotificationEnableSinkingStackObserver";
    public int _lockScreenNotificationEnableSinking;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return KEY;
    }

    public final boolean getLockScreenNotificationEnableSinking() {
        return this._lockScreenNotificationEnableSinking != 0;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        update$2(getContext());
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        update$2(context);
    }

    public final void update$2(Context context) {
        if (context == null) {
            return;
        }
        this._lockScreenNotificationEnableSinking = AbsSettingsValueProxy.Companion.getSystemIntValue(context, KEY, 0);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this._lockScreenNotificationEnableSinking, "update lockScreenNotificationEnableSinking: ", "LockScreenNotificationEnableSinkingStackObserver-->", "SystemUi--Notification");
        }
    }
}
