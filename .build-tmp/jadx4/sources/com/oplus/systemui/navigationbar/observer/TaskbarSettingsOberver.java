package com.oplus.systemui.navigationbar.observer;

import android.content.Context;
import android.net.Uri;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TaskbarSettingsOberver extends AbstractObserver {
    private static final String TAG = "TaskbarSettingsOberver";
    public static final TaskbarSettingsOberver INSTANCE = new TaskbarSettingsOberver();
    public static int taskbarEnable = 1;

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return NavBarSettingsValueProxy.KEY_TASKBAR_ENABLE;
    }

    public final int getTaskbarEnable() {
        return taskbarEnable;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Uri getUri(Context context) {
        return MultiUserContentHelper.Companion.getSystemUri(getKey());
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        taskbarEnable = NavBarSettingsValueProxy.Companion.getTaskbarEnable(context);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(taskbarEnable, " taskbarEnable ", "TaskbarSettingsOberver-->", "SystemUi--NavBar");
        }
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        taskbarEnable = NavBarSettingsValueProxy.Companion.getTaskbarEnable(context);
    }
}
