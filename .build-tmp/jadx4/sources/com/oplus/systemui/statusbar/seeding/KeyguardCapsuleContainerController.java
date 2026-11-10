package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.util.ViewController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardCapsuleContainerController extends ViewController implements Listener {
    public static final Companion Companion = new Companion();
    public static final String TAG = "KeyguardCapsuleContainerController";
    private final KeyguardStatusBarCapsuleContainer keyguardCapsuleContainer;
    private final SeedlingPluginManager seedlingPluginManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public KeyguardCapsuleContainerController(SeedlingPluginManager seedlingPluginManager, KeyguardStatusBarCapsuleContainer keyguardStatusBarCapsuleContainer) {
        super(keyguardStatusBarCapsuleContainer);
        this.seedlingPluginManager = seedlingPluginManager;
        this.keyguardCapsuleContainer = keyguardStatusBarCapsuleContainer;
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public /* bridge */ /* synthetic */ void onDispatchTouchEvent(MotionEvent motionEvent) {
        super.onDispatchTouchEvent(motionEvent);
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public void onPluginAdded(SeedlingPlugin seedlingPlugin, Context context) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "KeyguardCapsuleContainerController-->onPluginAdded");
        }
        if (seedlingPlugin != null) {
            seedlingPlugin.onCreateView(1, this.keyguardCapsuleContainer);
        }
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public void onPluginRemove(SeedlingPlugin seedlingPlugin) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "KeyguardCapsuleContainerController-->onPluginRemove");
        }
        KeyguardStatusBarCapsuleContainer keyguardStatusBarCapsuleContainer = this.keyguardCapsuleContainer;
        if (keyguardStatusBarCapsuleContainer != null) {
            keyguardStatusBarCapsuleContainer.removeAllViews();
        }
    }

    public final void onViewAttached() {
        SeedlingPlugin seedlingPlugin;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onViewAttached: isAvailable=", "KeyguardCapsuleContainerController-->", "SystemUi--Keyguard", this.seedlingPluginManager.isPluginAvailable);
        }
        this.seedlingPluginManager.addCallback(this);
        SeedlingPluginManager seedlingPluginManager = this.seedlingPluginManager;
        if (!seedlingPluginManager.isPluginAvailable || (seedlingPlugin = seedlingPluginManager.getSeedlingPlugin()) == null) {
            return;
        }
        seedlingPlugin.onCreateView(1, this.keyguardCapsuleContainer);
    }

    public final void onViewDetached() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "KeyguardCapsuleContainerController-->onViewDetached");
        }
        this.seedlingPluginManager.removeCallback(this);
    }
}
