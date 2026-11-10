package com.oplus.sysuiinlineview.host.controller;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelUuid;
import com.oplus.sysuiinlineview.ISysUiInlineCallback;
import com.oplus.sysuiinlineview.host.SysUiInlineServiceManager;
import com.oplus.sysuiinlineview.utils.SysUiInlineBundleUtils;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUiInlineHostViewController {
    private final String category;
    private final ParcelUuid hostToken;
    private final AtomicBoolean isRunning;
    private final Bundle mBaseExtras;
    private final SysUiInlineServiceManager mClientManager;

    public SysUiInlineHostViewController(String str, SysUiInlineServiceManager sysUiInlineServiceManager) {
        this.category = str;
        this.mClientManager = sysUiInlineServiceManager;
        ParcelUuid parcelUuid = new ParcelUuid(UUID.randomUUID());
        this.hostToken = parcelUuid;
        this.isRunning = new AtomicBoolean(false);
        this.mBaseExtras = SysUiInlineBundleUtils.setHostToken(new Bundle(), parcelUuid);
    }

    public final Bundle getBaseExtras() {
        return this.mBaseExtras;
    }

    public final boolean isHostViewRunning() {
        return this.isRunning.get();
    }

    public final void notifyHostViewConfigurationChanged(Configuration configuration) {
        this.mClientManager.notifyHostViewConfigurationChanged(this.hostToken, this.category, SysUiInlineBundleUtils.setViewConfiguration(this.mBaseExtras, configuration));
    }

    public final void notifyHostViewCreated(ISysUiInlineCallback iSysUiInlineCallback) {
        this.mClientManager.notifyHostViewCreated(this.hostToken, this.category, iSysUiInlineCallback, this.mBaseExtras);
    }

    public final void notifyHostViewDestroyed() {
        this.mClientManager.notifyHostViewDestroyed(this.hostToken, this.category, this.mBaseExtras);
    }

    public final void notifyHostViewLaidOut(IBinder iBinder, int i, int i2, int i3) {
        SysUiInlineBundleUtils.setViewHeight(SysUiInlineBundleUtils.setViewWidth(SysUiInlineBundleUtils.setDisplayId(SysUiInlineBundleUtils.setInputToken(this.mBaseExtras, iBinder), i), i2), i3);
        this.mClientManager.notifyHostViewLaidOut(this.hostToken, this.category, this.mBaseExtras);
    }

    public final void updateRunning(boolean z) {
        if (this.isRunning.compareAndSet(!z, z)) {
            SysUiInlineServiceManager sysUiInlineServiceManager = this.mClientManager;
            if (z) {
                sysUiInlineServiceManager.notifyHostViewResumed(this.hostToken, this.category, this.mBaseExtras);
            } else {
                sysUiInlineServiceManager.notifyHostViewPaused(this.hostToken, this.category, this.mBaseExtras);
            }
        }
    }
}
