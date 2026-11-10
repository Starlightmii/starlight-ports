package com.oplusos.systemui.common.multiuser;

import com.oplusos.systemui.common.receiver.AbstractReceiver;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiUserReceiver extends AbstractReceiver {
    public static final Companion Companion = new Companion();
    private static volatile MultiUserReceiver sInstance;
    private final MultiUserReceiver$mMultiUserReceiverListener$1 mMultiUserReceiverListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final MultiUserReceiver getInstance() {
            if (MultiUserReceiver.sInstance == null) {
                synchronized (MultiUserReceiver.class) {
                    if (MultiUserReceiver.sInstance == null) {
                        MultiUserReceiver.sInstance = new MultiUserReceiver();
                    }
                }
            }
            MultiUserReceiver multiUserReceiver = MultiUserReceiver.sInstance;
            multiUserReceiver.getClass();
            return multiUserReceiver;
        }
    }

    public MultiUserReceiver() {
        MultiUserReceiver$mMultiUserReceiverListener$1 multiUserReceiver$mMultiUserReceiverListener$1 = new MultiUserReceiver$mMultiUserReceiverListener$1();
        multiUserReceiver$mMultiUserReceiverListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mMultiUserReceiverListener = multiUserReceiver$mMultiUserReceiverListener$1;
        addListener(multiUserReceiver$mMultiUserReceiverListener$1);
    }

    public static final MultiUserReceiver getInstance() {
        return Companion.getInstance();
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{"android.intent.action.USER_ADDED", "android.intent.action.USER_SWITCHED", "android.intent.action.USER_UNLOCKED", "android.intent.action.USER_PRESENT"};
    }
}
