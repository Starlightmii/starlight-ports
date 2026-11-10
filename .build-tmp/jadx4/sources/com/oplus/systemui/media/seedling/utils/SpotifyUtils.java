package com.oplus.systemui.media.seedling.utils;

import android.content.Context;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpotifyUtils {
    public static final SpotifyUtils INSTANCE = new SpotifyUtils();
    public static final String METADATA_EXTRA_KEY_TRACK_ARTWORK_COLOR = "com.spotify.music.extra.EXTRA_EXTRACTED_COLOR_DARK_HIGH_CONTRAST_BACKGROUND_BASE";
    public static final String SPOTIFY_PACKAGE_NAME = "com.spotify.music";
    private static final String TAG = "SpotifyUtils";

    public static final boolean isSpotifyWhichSupportsOplusMediaController(Context context, String str, boolean z) {
        Object obj;
        if (!Intrinsics.areEqual(str, "com.spotify.music")) {
            return false;
        }
        try {
            obj = Result.constructor-impl(Long.valueOf(context.getPackageManager().getPackageInfo(str, 0).getLongVersionCode()));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusNotificationLogger.logMediaSeedling(TAG, str + " getPackageVersionCode onFailure " + th2.getMessage());
        }
        if (obj instanceof Result.Failure) {
            obj = -1L;
        }
        boolean z2 = ((Number) obj).longValue() >= 116658084;
        if (z) {
            OplusNotificationLogger.logMediaSeedling(TAG, "isSpotifyWhichSupportsOplusMediaController " + z2);
        }
        return z2;
    }
}
