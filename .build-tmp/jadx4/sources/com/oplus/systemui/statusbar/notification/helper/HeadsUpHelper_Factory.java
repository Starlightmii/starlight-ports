package com.oplus.systemui.statusbar.notification.helper;

import android.content.Context;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.notification.interruption.gamespace.QSNoDisturbObserver;
import com.oplus.systemui.statusbar.observer.GameBarrageObserver;
import com.oplus.systemui.statusbar.observer.GameSpaceObserver;
import com.oplus.systemui.statusbar.observer.InCallUIFloatingWindowObserver;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class HeadsUpHelper_Factory implements Factory {
    public static HeadsUpHelper newInstance(Context context, GameSpaceObserver gameSpaceObserver, QSNoDisturbObserver qSNoDisturbObserver, GameBarrageObserver gameBarrageObserver, InCallUIFloatingWindowObserver inCallUIFloatingWindowObserver, ConfigurationController configurationController) {
        return new HeadsUpHelper(context, gameSpaceObserver, qSNoDisturbObserver, gameBarrageObserver, inCallUIFloatingWindowObserver, configurationController);
    }
}
