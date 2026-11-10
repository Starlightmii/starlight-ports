package com.oplusos.systemui.common.multiuser;

import android.content.Context;
import android.content.Intent;
import android.os.Trace;
import android.text.TextUtils;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiUserReceiver$mMultiUserReceiverListener$1 implements IReceiverListener {
    public /* synthetic */ MultiUserReceiver this$0;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        Trace.traceBegin(8L, "MultiUserReceiver_" + action);
        if (action != null) {
            final int i = 0;
            switch (action.hashCode()) {
                case 823795052:
                    if (action.equals("android.intent.action.USER_PRESENT")) {
                        final int i2 = 1;
                        MultiUserManager.Companion.getInstance().dispatchToListeners("onPresent", new Function1() { // from class: com.oplusos.systemui.common.multiuser.MultiUserManager$$ExternalSyntheticLambda0
                            public final Object invoke(Object obj) {
                                IMultiUserListener iMultiUserListener = (IMultiUserListener) obj;
                                switch (i2) {
                                    case 0:
                                        iMultiUserListener.onUnlocked();
                                        break;
                                    case 1:
                                        iMultiUserListener.onPresent();
                                        break;
                                    default:
                                        iMultiUserListener.getClass();
                                        break;
                                }
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    break;
                case 833559602:
                    if (action.equals("android.intent.action.USER_UNLOCKED")) {
                        MultiUserManager.Companion.getInstance().dispatchToListeners("onUnlocked", new Function1() { // from class: com.oplusos.systemui.common.multiuser.MultiUserManager$$ExternalSyntheticLambda0
                            public final Object invoke(Object obj) {
                                IMultiUserListener iMultiUserListener = (IMultiUserListener) obj;
                                switch (i) {
                                    case 0:
                                        iMultiUserListener.onUnlocked();
                                        break;
                                    case 1:
                                        iMultiUserListener.onPresent();
                                        break;
                                    default:
                                        iMultiUserListener.getClass();
                                        break;
                                }
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    break;
                case 959232034:
                    if (action.equals("android.intent.action.USER_SWITCHED")) {
                        int intExtra = intent.getIntExtra("android.intent.extra.user_handle", 0);
                        MultiUserManager companion = MultiUserManager.Companion.getInstance();
                        companion.getClass();
                        MultiUserManager$$ExternalSyntheticLambda2 multiUserManager$$ExternalSyntheticLambda2 = new MultiUserManager$$ExternalSyntheticLambda2();
                        multiUserManager$$ExternalSyntheticLambda2.f$0 = intExtra;
                        VarHandle.storeStoreFence();
                        companion.dispatchToListeners("onSwitched", multiUserManager$$ExternalSyntheticLambda2);
                    }
                    break;
                case 1121780209:
                    if (action.equals("android.intent.action.USER_ADDED")) {
                        final int i3 = 2;
                        MultiUserManager.Companion.getInstance().dispatchToListeners("onAdded", new Function1() { // from class: com.oplusos.systemui.common.multiuser.MultiUserManager$$ExternalSyntheticLambda0
                            public final Object invoke(Object obj) {
                                IMultiUserListener iMultiUserListener = (IMultiUserListener) obj;
                                switch (i3) {
                                    case 0:
                                        iMultiUserListener.onUnlocked();
                                        break;
                                    case 1:
                                        iMultiUserListener.onPresent();
                                        break;
                                    default:
                                        iMultiUserListener.getClass();
                                        break;
                                }
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    break;
            }
        }
        Trace.traceEnd(8L);
    }
}
