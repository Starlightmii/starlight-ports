package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GameSpaceObserver extends ContentObserver {
    public static final Companion Companion = new Companion();
    private static final String GAME_SPACE_DISTURB_MODE = "disturb_for_game_space_mode_flag";
    private static final String TAG = "GameSpaceObserver";
    private final Context context;
    public boolean isInGameSpaceDisturb;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public GameSpaceObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean inGameSpaceDisturb() {
        return (Settings.Global.getInt(this.context.getContentResolver(), GAME_SPACE_DISTURB_MODE, 0) & 1) != 0;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        this.isInGameSpaceDisturb = inGameSpaceDisturb();
    }

    public final void setInGameSpaceDisturb(boolean z) {
        this.isInGameSpaceDisturb = z;
    }
}
