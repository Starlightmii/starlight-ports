package com.oplusos.systemui.aod.mediapanel;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodMediaPanelSettingObserver extends ContentObserver {
    public static final Companion Companion = new Companion();
    private static final String KEY_AOD_MEDIA = "Setting_AodSceneMusicSwitchEnable";
    private static final String TAG = "AodMediaPanelSettingObs";
    public boolean aodMediaEnable;
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AodMediaPanelSettingObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
    }

    public final boolean getAodMediaEnable() {
        return this.aodMediaEnable;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        this.aodMediaEnable = Settings.Secure.getIntForUser(this.context.getContentResolver(), KEY_AOD_MEDIA, 0, 0) == 1;
    }

    public final void register() {
        this.aodMediaEnable = Settings.Secure.getIntForUser(this.context.getContentResolver(), KEY_AOD_MEDIA, 0, 0) == 1;
        this.context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(KEY_AOD_MEDIA), false, this, 0);
    }

    public final void setAodMediaEnable(boolean z) {
        this.aodMediaEnable = z;
    }
}
