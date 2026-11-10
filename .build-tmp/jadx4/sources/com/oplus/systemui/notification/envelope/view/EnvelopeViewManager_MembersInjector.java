package com.oplus.systemui.notification.envelope.view;

import dagger.Lazy;
import dagger.MembersInjector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class EnvelopeViewManager_MembersInjector implements MembersInjector {
    public static void injectMEnvelopeHelper(EnvelopeViewManager envelopeViewManager, Lazy lazy) {
        envelopeViewManager.mEnvelopeHelper = lazy;
    }

    public static void injectMHeadsUpHelper(EnvelopeViewManager envelopeViewManager, Lazy lazy) {
        envelopeViewManager.mHeadsUpHelper = lazy;
    }
}
