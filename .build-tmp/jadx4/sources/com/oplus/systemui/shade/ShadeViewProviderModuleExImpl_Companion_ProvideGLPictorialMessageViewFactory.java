package com.oplus.systemui.shade;

import android.widget.FrameLayout;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMessageView;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ShadeViewProviderModuleExImpl_Companion_ProvideGLPictorialMessageViewFactory implements Factory {
    public static PictorialMessageView provideGLPictorialMessageView(FrameLayout frameLayout, NotificationShadeWindowView notificationShadeWindowView, PictorialMediator pictorialMediator) {
        PictorialMessageView pictorialMessageViewProvideGLPictorialMessageView = ShadeViewProviderModuleExImpl.Companion.provideGLPictorialMessageView(frameLayout, notificationShadeWindowView, pictorialMediator);
        pictorialMessageViewProvideGLPictorialMessageView.getClass();
        return pictorialMessageViewProvideGLPictorialMessageView;
    }
}
