package com.oplusos.systemui.common.settingsvalue;

import android.content.Context;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SettingsValueProxyManager$mListener$1 implements IMultiUserListener {
    public /* synthetic */ SettingsValueProxyManager this$0;

    @Override // com.oplusos.systemui.common.multiuser.IMultiUserListener
    public final void onSwitched(int i) {
        SettingsValueProxyManager settingsValueProxyManager = this.this$0;
        if (settingsValueProxyManager.mWeakCtx != null) {
            WeakReference weakReference = settingsValueProxyManager.mWeakCtx;
            weakReference.getClass();
            if (weakReference.get() == null || KeyguardUpdateMonitor.getCurrentUser() == 0) {
                return;
            }
            MultiUserContentHelper.Companion companion = MultiUserContentHelper.Companion;
            WeakReference weakReference2 = settingsValueProxyManager.mWeakCtx;
            weakReference2.getClass();
            Object obj = weakReference2.get();
            obj.getClass();
            Context currentUserContext = companion.getCurrentUserContext((Context) obj);
            if (currentUserContext == null) {
                return;
            }
            settingsValueProxyManager.checkValidity(currentUserContext);
        }
    }
}
