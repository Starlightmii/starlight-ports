package com.oplus.systemui.keyguard.wallpaper.pictorial.factory;

import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.oplus.systemui.keyguard.wallpaper.pictorial.observer.FingerprintTipsBackgroundChange;
import com.oplus.systemui.keyguard.wallpaper.pictorial.observer.PictorialScreenConfigChange;
import com.oplus.systemui.keyguard.wallpaper.pictorial.observer.RmPicBroadcastReceiver;
import com.oplus.systemui.provider.ProviderUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class RmPictorialFactory extends PictorialFactory {
    public FingerprintTipsBackgroundChange mFingerprintTipsBackgroundChange;
    public boolean mHasRegisterReceiver;
    public final RmPicBroadcastReceiver mPicBroadcastReceiver;
    public final PictorialScreenConfigChange mPictorialScreenConfigChange;
    public final ScreenLifecycle mScreenLifecycle;

    public RmPictorialFactory() {
        this.mHasRegisterReceiver = false;
        RmPicBroadcastReceiver rmPicBroadcastReceiver = new RmPicBroadcastReceiver();
        this.mPicBroadcastReceiver = rmPicBroadcastReceiver;
        if (!this.mHasRegisterReceiver) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
            intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter.addDataScheme("package");
            ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).registerReceiver(rmPicBroadcastReceiver, intentFilter, (Executor) null, (UserHandle) null, 4, ProviderUtil.OPLUS_COMPONENT_SAFE);
            this.mHasRegisterReceiver = true;
            KgdLogUtil.i("RmPictorialFactory", "onReceive initTbEventObserver");
        }
        if (this.mPictorialScreenConfigChange == null) {
            this.mPictorialScreenConfigChange = new PictorialScreenConfigChange();
        }
        ScreenLifecycle screenLifecycle = (ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class);
        this.mScreenLifecycle = screenLifecycle;
        screenLifecycle.addObserver(this.mPictorialScreenConfigChange);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public void destory() {
        PictorialBottomViewHelper pictorialBottomViewHelper = getPictorialBottomViewHelper();
        if (pictorialBottomViewHelper != null) {
            pictorialBottomViewHelper.destory();
        }
        RmPicBroadcastReceiver rmPicBroadcastReceiver = this.mPicBroadcastReceiver;
        if (rmPicBroadcastReceiver != null && this.mHasRegisterReceiver) {
            ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).unregisterReceiver(rmPicBroadcastReceiver);
            this.mHasRegisterReceiver = false;
            KgdLogUtil.i("RmPictorialFactory", "onReceive destory");
        }
        if (this.mFingerprintTipsBackgroundChange != null) {
            KgdLogUtil.i("RmPictorialFactory", "removeKeyguardTipsCallback");
            ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBiometricAuthController().removeKeyguardTipsCallback(this.mFingerprintTipsBackgroundChange);
        }
        PictorialScreenConfigChange pictorialScreenConfigChange = this.mPictorialScreenConfigChange;
        if (pictorialScreenConfigChange != null) {
            KgdLogUtil.i("RmPictorialFactory", "mPictorialScreenConfigChange destroy");
            this.mScreenLifecycle.removeObserver(pictorialScreenConfigChange);
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory
    public final void registerFingerprintTipsShowCallback() {
        if (this.mFingerprintTipsBackgroundChange == null) {
            this.mFingerprintTipsBackgroundChange = new FingerprintTipsBackgroundChange();
            ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBiometricAuthController().registerKeyguardTipsCallback(this.mFingerprintTipsBackgroundChange);
        }
    }
}
