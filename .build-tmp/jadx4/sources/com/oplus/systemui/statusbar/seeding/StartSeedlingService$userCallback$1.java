package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import android.util.Log;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.settings.UserTracker;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StartSeedlingService$userCallback$1 implements UserTracker.Callback {
    public /* synthetic */ StartSeedlingService this$0;

    public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
        super.onBeforeUserSwitching(i, runnable);
    }

    public /* bridge */ /* synthetic */ void onProfilesChanged(List list) {
        super.onProfilesChanged(list);
    }

    public final void onUserChanged(int i, Context context) {
        StartSeedlingService startSeedlingService = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "StartSeedlingService-->".concat("onUserChanged, serverMessenger=" + startSeedlingService.serverMessenger + ", lastContext=" + startSeedlingService.lastContext + ", userContext=" + context));
        }
        try {
            if (startSeedlingService.serverMessenger != null) {
                Context context2 = startSeedlingService.lastContext;
                if (context2 != null) {
                    context2.unbindService(startSeedlingService.conn);
                }
                startSeedlingService.serverMessenger = null;
            }
        } catch (IllegalArgumentException e) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "unBindService: IllegalArgumentException error: ", e.getMessage(), LogUtil.TAG_COMMON, "StartSeedlingService");
        } catch (Exception e2) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "unBindService: unexpected error: ", e2.getMessage(), LogUtil.TAG_COMMON, "StartSeedlingService");
        }
        startSeedlingService.mData.clear();
        startSeedlingService.lastContext = context;
        StartSeedlingService.bindService$default(context, startSeedlingService);
        super.onUserChanged(i, context);
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context) {
        super.onUserChanging(i, context);
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
        super.onUserChanging(i, context, runnable);
    }
}
