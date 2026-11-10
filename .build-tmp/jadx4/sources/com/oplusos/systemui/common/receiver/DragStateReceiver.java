package com.oplusos.systemui.common.receiver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DragStateReceiver extends AbstractReceiver {
    public static volatile DragStateReceiver sInstance;

    public static DragStateReceiver getInstance() {
        if (sInstance == null) {
            synchronized (DragStateReceiver.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new DragStateReceiver();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{"com.oplus.intent.action.EXIT_DRAG_WINDOW", "com.oplus.intent.action.SUCCESS_DRAG_WINDOW", "com.oplus.intent.action.START_DRAG_WINDOW"};
    }
}
