package com.oplus.systemui.keyguard.wallpaper.pictorial.observer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RmPicBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Uri data = intent.getData();
        action.hashCode();
        if (action.equals("android.intent.action.PACKAGE_REPLACED") || action.equals("android.intent.action.PACKAGE_DATA_CLEARED")) {
            KgdLogUtil.i("RmPicBroadcastReceiver", "onReceive action ACTION_PACKAGE_DATA_CLEARED and ACTION_PACKAGE_REPLACED");
            if (data != null) {
                String schemeSpecificPart = data.getSchemeSpecificPart();
                PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
                if (TextUtils.equals(schemeSpecificPart, pictorialMediator.getPictorialPackageName())) {
                    pictorialMediator.mPictorialServiceConnected = false;
                    pictorialMediator.mPictorialFactory.getPictorialLoader().releaseDrawableAndInfo();
                    pictorialMediator.mPictorialFactory.getPictorialLoader().releaseDrawableCache();
                    pictorialMediator.firstProviderService(false);
                }
            }
        }
    }
}
