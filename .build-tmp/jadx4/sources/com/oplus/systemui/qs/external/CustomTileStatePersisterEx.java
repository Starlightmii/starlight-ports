package com.oplus.systemui.qs.external;

import android.content.Context;
import android.content.SharedPreferences;
import android.service.quicksettings.Tile;
import com.android.systemui.qs.external.CustomTileStatePersister;
import com.android.systemui.qs.external.CustomTileStatePersisterImpl;
import com.android.systemui.qs.external.TileServiceKey;
import java.util.Locale;
import kotlin.Result;
import kotlin.ResultKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomTileStatePersisterEx implements CustomTileStatePersister {
    public static final Companion Companion = new Companion();
    private static final String FILE_NAME = "custom_tiles_state_ex";
    private static final String LOCALE = "locale";
    private final Context context;
    private final CustomTileStatePersisterImpl originImpl;
    private Locale prevLocale;
    private final SharedPreferences sharedPreferences;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CustomTileStatePersisterEx(Context context, CustomTileStatePersisterImpl customTileStatePersisterImpl) {
        this.context = context;
        this.originImpl = customTileStatePersisterImpl;
        this.sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
    }

    public Locale getPrevLocale() {
        return this.prevLocale;
    }

    public void persistState(TileServiceKey tileServiceKey, Tile tile) {
        this.originImpl.persistState(tileServiceKey, tile);
        Companion companion = Companion;
        Context context = this.context;
        companion.getClass();
        new JSONObject().put(LOCALE, context.getResources().getConfiguration().locale.toLanguageTag()).toString();
    }

    public Tile readState(TileServiceKey tileServiceKey) {
        Object obj;
        Companion companion = Companion;
        Locale locale = null;
        String string = this.sharedPreferences.getString(tileServiceKey.toString(), null);
        companion.getClass();
        if (string != null) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                companion.getClass();
                obj = Result.constructor-impl(Locale.forLanguageTag(jSONObject.has(LOCALE) ? jSONObject.getString(LOCALE) : null));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            locale = (Locale) (obj instanceof Result.Failure ? null : obj);
        }
        this.prevLocale = locale;
        return this.originImpl.readState(tileServiceKey);
    }

    public void removeState(TileServiceKey tileServiceKey) {
        this.originImpl.removeState(tileServiceKey);
        this.sharedPreferences.edit().remove(tileServiceKey.toString()).apply();
    }

    public void removeStateForPackageAndUser(String str, int i) {
        this.originImpl.removeStateForPackageAndUser(str, i);
    }
}
