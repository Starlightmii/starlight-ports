package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import androidx.appcompat.widget.ListPopupWindow$;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GameBarrageObserver extends ContentObserver {
    public static final Companion Companion = new Companion();
    private static final String GAME_SPACE_BARRAGE_APPLICATION = "barrage_application";
    private static final String GAME_SPACE_BARRAGE_SWITCH = "barrage_switch";
    private static final String TAG = "GameBarrageObserver";
    private String allowedPkgs;
    public boolean barrageEnable;
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public GameBarrageObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
        this.allowedPkgs = "";
    }

    private final String getAllowedApps() {
        return Settings.Secure.getStringForUser(this.context.getContentResolver(), GAME_SPACE_BARRAGE_APPLICATION, -2);
    }

    public final String getAllowedPkgs() {
        return this.allowedPkgs;
    }

    public final boolean getBarrageEnable() {
        return this.barrageEnable;
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean isBarrageEnable() {
        int intForUser = Settings.Secure.getIntForUser(this.context.getContentResolver(), GAME_SPACE_BARRAGE_SWITCH, 0, -2);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(intForUser, "Notification--barrage_switch "), null, 8);
        return (intForUser & 1) != 0;
    }

    public final void observe() {
        ObserverDispatcher.getInstance().registerObserver(Settings.Secure.getUriFor(GAME_SPACE_BARRAGE_SWITCH), this, -1);
        ObserverDispatcher.getInstance().registerObserver(Settings.Secure.getUriFor(GAME_SPACE_BARRAGE_APPLICATION), this, -1);
        this.barrageEnable = isBarrageEnable();
        this.allowedPkgs = String.valueOf(getAllowedApps());
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        this.barrageEnable = isBarrageEnable();
        this.allowedPkgs = String.valueOf(getAllowedApps());
    }

    public final void setAllowedPkgs(String str) {
        this.allowedPkgs = str;
    }

    public final void setBarrageEnable(boolean z) {
        this.barrageEnable = z;
    }
}
