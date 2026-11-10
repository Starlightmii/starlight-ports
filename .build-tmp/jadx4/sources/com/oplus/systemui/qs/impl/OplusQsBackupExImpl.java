package com.oplus.systemui.qs.impl;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.qs.OplusQsBackUpEx;
import com.oplus.systemui.qs.helper.QSTileFilterHelper;
import com.oplus.systemui.qs.tiles.AutoScreenTemperatureTile;
import com.oplus.systemui.qs.tiles.DarkModeTile;
import com.oplus.systemui.qs.tiles.InputMethodTile;
import com.oplus.systemui.qs.tiles.Iris5Tile;
import com.oplus.systemui.qs.tiles.OsieTile;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBackupExImpl extends OplusQsBackUpEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsBackupExImpl";
    private final Context context;
    private String mCurrentQsTileString;

    public OplusQsBackupExImpl(Context context) {
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public boolean replaceQsTileToNew(int i, String str) {
        this.mCurrentQsTileString = str;
        upgradeQsTile("custom(com.android.settings/com.oplus.settings.feature.display.video.Iris5TileService)", Iris5Tile.TILE_SPEC);
        upgradeQsTile("custom(com.android.settings/com.oplus.settings.feature.display.video.OsieTileService)", OsieTile.TILE_SPEC);
        upgradeQsTile("custom(com.android.settings/com.oplus.settings.feature.display.darkmode.DarkModeTileService)", DarkModeTile.TILE_SPEC);
        upgradeQsTile(QSTileFilterHelper.AUTO_SCREEN_TEMPERATURE, AutoScreenTemperatureTile.TILE_SPEC);
        upgradeQsTile("custom(com.android.settings/com.oplus.settings.feature.display.video.InputMethodTile)", InputMethodTile.TILE_SPEC);
        if (!TextUtils.equals(str, this.mCurrentQsTileString)) {
            return Settings.Secure.putStringForUser(this.context.getContentResolver(), "sysui_qs_tiles", this.mCurrentQsTileString, i);
        }
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_QS)) {
            return false;
        }
        Log.d("SystemUi--QuickSettings", "OplusQsBackupExImpl-->qs tile do not need to adjust.");
        return false;
    }

    public final void upgradeQsTile(String str, String str2) {
        String str3 = this.mCurrentQsTileString;
        if (str3 == null || str3.length() == 0) {
            return;
        }
        String str4 = this.mCurrentQsTileString;
        if (str4 != null && StringsKt__StringsKt.indexOf$default(str4, str, 0, false, 6) == -1) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusQsBackupExImpl-->didn't found tile");
                return;
            }
            return;
        }
        String str5 = this.mCurrentQsTileString;
        this.mCurrentQsTileString = str5 != null ? StringsKt__StringsJVMKt.replace(str5, str, str2, false) : null;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsBackupExImpl-->qs tile need to adjust, done.");
        }
    }
}
