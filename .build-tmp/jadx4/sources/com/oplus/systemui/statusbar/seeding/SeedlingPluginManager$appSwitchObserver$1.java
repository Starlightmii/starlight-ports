package com.oplus.systemui.statusbar.seeding;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.app.OplusAppEnterInfo;
import com.oplus.app.OplusAppExitInfo;
import com.oplus.app.OplusAppSwitchManager;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$appSwitchObserver$1 implements OplusAppSwitchManager.OnAppSwitchObserver {
    public Bundle bundle;
    public /* synthetic */ SeedlingPluginManager this$0;

    public final Bundle getBundle() {
        return this.bundle;
    }

    public final void onActivityEnter(OplusAppEnterInfo oplusAppEnterInfo) {
    }

    public final void onActivityExit(OplusAppExitInfo oplusAppExitInfo) {
    }

    public final void onAppEnter(OplusAppEnterInfo oplusAppEnterInfo) {
    }

    public final void onAppExit(OplusAppExitInfo oplusAppExitInfo) {
        if (oplusAppExitInfo != null) {
            SeedlingPluginManager seedlingPluginManager = this.this$0;
            Bundle bundle = oplusAppExitInfo.extension;
            int i = bundle != null ? bundle.getInt("displayid", 0) : 0;
            Bundle bundle2 = oplusAppExitInfo.extension;
            boolean z = bundle2 != null ? bundle2.getBoolean("isTransientHide", false) : false;
            Bundle bundle3 = oplusAppExitInfo.extension;
            int i2 = bundle3 != null ? bundle3.getInt("baseUserId", 0) : 0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->".concat("onAppExit-->info: " + oplusAppExitInfo + " appUserId = " + i2 + " displayId: " + i + " isTransientHide=" + z));
            }
            if (i != 0) {
                OplusSubDisplayUtils.Companion companion = OplusSubDisplayUtils.Companion;
                if (!companion.getInstance(seedlingPluginManager.context).getSupportSubDisplay()) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->onAppExit-->ignore virtual display");
                        return;
                    }
                    return;
                }
                int secondaryDisplayId = companion.getInstance(seedlingPluginManager.context).getSecondaryDisplayId();
                if (i != secondaryDisplayId) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(secondaryDisplayId, "onAppExit-->ignore virtual display. secondaryDisplayId:", "SeedlingPluginManager-->", "SystemUi--Statusbar");
                        return;
                    }
                    return;
                }
            }
            if (TextUtils.isEmpty(oplusAppExitInfo.resumingPackageName) || !oplusAppExitInfo.hasResumingActivity) {
                return;
            }
            String str = oplusAppExitInfo.targetName;
            if (str == null) {
                str = "";
            }
            String str2 = oplusAppExitInfo.resumingPackageName;
            String str3 = oplusAppExitInfo.resumingActivityName;
            String str4 = str3 != null ? str3 : "";
            if (Intrinsics.areEqual(str2, this.this$0.context.getPackageName())) {
                return;
            }
            Handler handler = this.this$0.mainHandler;
            SeedlingPluginManager seedlingPluginManager2 = this.this$0;
            SeedlingPluginManager$appSwitchObserver$1$onAppSwitch$1 seedlingPluginManager$appSwitchObserver$1$onAppSwitch$1 = new SeedlingPluginManager$appSwitchObserver$1$onAppSwitch$1();
            seedlingPluginManager$appSwitchObserver$1$onAppSwitch$1.this$0 = this;
            seedlingPluginManager$appSwitchObserver$1$onAppSwitch$1.$exitPkg = str;
            seedlingPluginManager$appSwitchObserver$1$onAppSwitch$1.$resumePkg = str2;
            seedlingPluginManager$appSwitchObserver$1$onAppSwitch$1.$resumePkgUserId = i2;
            seedlingPluginManager$appSwitchObserver$1$onAppSwitch$1.$resumingActivityName = str4;
            seedlingPluginManager$appSwitchObserver$1$onAppSwitch$1.$isTransientHide = z;
            seedlingPluginManager$appSwitchObserver$1$onAppSwitch$1.this$1 = seedlingPluginManager2;
            VarHandle.storeStoreFence();
            handler.post(seedlingPluginManager$appSwitchObserver$1$onAppSwitch$1);
        }
    }
}
