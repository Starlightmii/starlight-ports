package com.oplus.systemui.notification.envelope;

import dagger.Lazy;
import dagger.MembersInjector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class EnvelopeHandler_MembersInjector implements MembersInjector {
    public static void injectMEnvelopeHelper(WeChatEnvelopeHandler weChatEnvelopeHandler, Lazy lazy) {
        weChatEnvelopeHandler.mEnvelopeHelper = lazy;
    }

    public static void injectMHeadsUpHelper(WeChatEnvelopeHandler weChatEnvelopeHandler, Lazy lazy) {
        weChatEnvelopeHandler.mHeadsUpHelper = lazy;
    }

    public static void injectMPrivacyHelper(WeChatEnvelopeHandler weChatEnvelopeHandler, Lazy lazy) {
        weChatEnvelopeHandler.mPrivacyHelper = lazy;
    }
}
