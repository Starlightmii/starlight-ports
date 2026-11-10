package com.oplus.systemui.keyguard.wallpaper.pictorial.taboola;

import android.util.Pair;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dependency;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBottomViewHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBuriedHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialPreloadHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.RmPictorialFactory;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TbPictorialFactory extends RmPictorialFactory {
    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialBottomViewHelper newPictorialBottomViewHelper() {
        return new TbPictorialBottomViewHelper();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialBuriedHelper newPictorialBuriedHelper() {
        return TbPictorialBuriedHelper.getInstance();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialCursorHelper newPictorialCursorHelper() {
        return new TbPictorialCursorHelper();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialLoader newPictorialLoader() {
        TbPictorialLoader tbPictorialLoader = new TbPictorialLoader();
        tbPictorialLoader.mCoverInfoList = new ArrayList();
        tbPictorialLoader.mLockScreenNextDrawableCache = new Pair(null, null);
        tbPictorialLoader.mPreloadNextDrawableCache = new Pair(null, null);
        tbPictorialLoader.mEnable = true;
        tbPictorialLoader.mUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
        VarHandle.storeStoreFence();
        return tbPictorialLoader;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialMediatorHelper newPictorialMediatorHelper() {
        return new TbPictorialMediatorHelper();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final PictorialPreloadHelper newPictorialPreloadHelper() {
        TbPictorialPreloadHelper tbPictorialPreloadHelper = new TbPictorialPreloadHelper();
        tbPictorialPreloadHelper.mPreloadViewLoadSuccessed = false;
        return tbPictorialPreloadHelper;
    }
}
