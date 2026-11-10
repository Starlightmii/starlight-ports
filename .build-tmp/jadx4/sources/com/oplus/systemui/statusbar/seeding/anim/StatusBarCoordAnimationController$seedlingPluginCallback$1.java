package com.oplus.systemui.statusbar.seeding.anim;

import android.content.Context;
import android.view.MotionEvent;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.oplus.systemui.statusbar.seeding.Listener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarCoordAnimationController$seedlingPluginCallback$1 implements Listener {
    public /* synthetic */ StatusBarCoordAnimationController this$0;

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public /* bridge */ /* synthetic */ void onDispatchTouchEvent(MotionEvent motionEvent) {
        super.onDispatchTouchEvent(motionEvent);
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public final void onPluginAdded(SeedlingPlugin seedlingPlugin, Context context) {
        if (seedlingPlugin != null) {
            seedlingPlugin.addChangeListener(this.this$0);
        }
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public final void onPluginRemove(SeedlingPlugin seedlingPlugin) {
        if (seedlingPlugin != null) {
            seedlingPlugin.removeChangeListener(this.this$0);
        }
    }
}
