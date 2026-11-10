package com.oplusos.systemui.common.observer;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TalkbackObserver$listener$1 implements IObserverListener {
    public /* synthetic */ TalkbackObserver this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        Context context = this.this$0.getContext();
        if (context == null) {
            return;
        }
        Context currentUserContext = MultiUserContentHelper.Companion.getCurrentUserContext(context);
        TalkbackObserver.Companion companion = TalkbackObserver.Companion;
        if (currentUserContext == null) {
            currentUserContext = context;
        }
        boolean zIsTalkbackEnabled = companion.isTalkbackEnabled(currentUserContext);
        Intent intent = new Intent(TalkbackObserver.ACTION_TALKBACK_SWITCH_CHAGNED);
        intent.putExtra(TalkbackObserver.EXTRA_TALKBACK_ENABLED, zIsTalkbackEnabled);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
