package com.oplus.systemui.statusbar.seeding;

import com.android.systemui.plugins.statusbar.SeedlingPlugin;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$appSwitchObserver$1$onAppSwitch$1 implements Runnable {
    public /* synthetic */ String $exitPkg;
    public /* synthetic */ boolean $isTransientHide;
    public /* synthetic */ String $resumePkg;
    public /* synthetic */ int $resumePkgUserId;
    public /* synthetic */ String $resumingActivityName;
    public /* synthetic */ SeedlingPluginManager$appSwitchObserver$1 this$0;
    public /* synthetic */ SeedlingPluginManager this$1;

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.getBundle().putString("state", "onFrontAppSwitched");
        this.this$0.getBundle().putString("app_switch_exit_pkg", this.$exitPkg);
        this.this$0.getBundle().putString("app_switch_resume_pkg", this.$resumePkg);
        this.this$0.getBundle().putInt("app_switch_resume_pkg_user_id", this.$resumePkgUserId);
        this.this$0.getBundle().putString("app_switch_resume_activity", this.$resumingActivityName);
        this.this$0.getBundle().putBoolean("isTransientHide", this.$isTransientHide);
        SeedlingPlugin seedlingPlugin = this.this$1.getSeedlingPlugin();
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(this.this$0.getBundle());
        }
    }
}
