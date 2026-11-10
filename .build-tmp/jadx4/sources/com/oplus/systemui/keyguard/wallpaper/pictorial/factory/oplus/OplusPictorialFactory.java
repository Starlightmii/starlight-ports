package com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus;

import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBottomViewHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBuriedHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPictorialFactory extends PictorialFactory {
    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialBottomViewHelper newPictorialBottomViewHelper() {
        OplusPictorialBottomViewHelper oplusPictorialBottomViewHelper = new OplusPictorialBottomViewHelper();
        oplusPictorialBottomViewHelper.mIsVerticalLayout = true;
        return oplusPictorialBottomViewHelper;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialBuriedHelper newPictorialBuriedHelper() {
        return new OplusPictorialBuriedHelper();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialCursorHelper newPictorialCursorHelper() {
        return new OplusPictorialCursorHelper();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialLoader newPictorialLoader() {
        return new OplusPictorialLoader();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialMediatorHelper newPictorialMediatorHelper() {
        return new OplusPictorialMediatorHelper();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialPreloadHelper newPictorialPreloadHelper() {
        return new OplusPictorialPreloadHelper();
    }
}
