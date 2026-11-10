package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import android.view.MotionEvent;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface Listener {
    default void onDispatchTouchEvent(MotionEvent motionEvent) {
    }

    void onPluginAdded(SeedlingPlugin seedlingPlugin, Context context);

    void onPluginRemove(SeedlingPlugin seedlingPlugin);
}
