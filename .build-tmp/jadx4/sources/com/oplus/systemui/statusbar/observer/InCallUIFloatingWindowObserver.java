package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class InCallUIFloatingWindowObserver extends ContentObserver {
    public static final Companion Companion = new Companion();
    private static final String INCALLUI_FLOATING_WINDOW_STATE = "oplus_common_center_incallui_floating_window_state";
    private static final String TAG = "InCallUIFloatingWindowObserver";
    private final Context context;
    public boolean showing;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public InCallUIFloatingWindowObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
    }

    private final boolean getState() {
        int i = Settings.Secure.getInt(this.context.getContentResolver(), INCALLUI_FLOATING_WINDOW_STATE, 0);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "Notification--suppressForCall show = ", "InCallUIFloatingWindowObserver-->", "SystemUi--Notification");
        }
        return i == 1;
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getShowing() {
        return this.showing;
    }

    public final void observe() {
        ObserverDispatcher.getInstance().registerObserver(Settings.Secure.getUriFor(INCALLUI_FLOATING_WINDOW_STATE), this, -1);
        this.showing = getState();
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        this.showing = getState();
    }

    public final void setShowing(boolean z) {
        this.showing = z;
    }
}
