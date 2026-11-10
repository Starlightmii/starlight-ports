package com.oplus.systemui.shade;

import android.widget.FrameLayout;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.oplusos.systemui.keyguard.view.OplusKeyguardTipsView;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ShadeViewProviderModuleExImpl_Companion_ProviderKeyguardTipsViewFactory implements Factory {
    public static OplusKeyguardTipsView providerKeyguardTipsView(FrameLayout frameLayout, NotificationShadeWindowView notificationShadeWindowView) {
        OplusKeyguardTipsView oplusKeyguardTipsViewProviderKeyguardTipsView = ShadeViewProviderModuleExImpl.Companion.providerKeyguardTipsView(frameLayout, notificationShadeWindowView);
        oplusKeyguardTipsViewProviderKeyguardTipsView.getClass();
        return oplusKeyguardTipsViewProviderKeyguardTipsView;
    }
}
