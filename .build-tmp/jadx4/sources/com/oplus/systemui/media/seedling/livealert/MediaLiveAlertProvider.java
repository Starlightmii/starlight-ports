package com.oplus.systemui.media.seedling.livealert;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaLiveAlertProvider extends ContentProvider {
    public static final String EVENT_TYPE = "EventType";
    private static final String METHOD_LIVE_ALERT_BTN_CLICK = "buttonClick";
    private static final String METHOD_LIVE_ALERT_DESTROY = "onLiveAlertDestroy";
    private static final String METHOD_LIVE_ALERT_SIZE_CHANGED = "onLiveAlertSizeChanged";
    private static final String TAG = "MediaLiveAlertProvider";
    public static final Companion Companion = new Companion();
    private static final ConcurrentHashMap<String, Function1> serverEventMap = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void registerServerEvent(String str, Function1 function1) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("registerServerEvent, event:", str, "MediaLiveAlertProvider-->", "SystemUi--SeedlingPlugin");
            }
            if (function1 != null) {
                MediaLiveAlertProvider.serverEventMap.put(str, function1);
            }
        }
    }

    private final void handleButtonClick(Bundle bundle) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
            Log.d("SystemUi--SeedlingPlugin", "MediaLiveAlertProvider-->".concat("handleButtonClick, serverEventMap=" + serverEventMap));
        }
        Function1 function1 = serverEventMap.get(bundle.getString("EventType"));
        if (function1 != null) {
            function1.invoke(bundle);
        }
    }

    public static final void registerServerEvent(String str, Function1 function1) {
        Companion.registerServerEvent(str, function1);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("call: method=", str, " arg=", str2, " extras=");
            sbM.append(bundle);
            Log.d("SystemUi--SeedlingPlugin", "MediaLiveAlertProvider-->".concat(sbM.toString()));
        }
        Context context = getContext();
        MediaLiveAlertController companion = context != null ? MediaLiveAlertController.Companion.getInstance(context) : null;
        int iHashCode = str.hashCode();
        if (iHashCode != -1785504394) {
            if (iHashCode != -359307767) {
                if (iHashCode == -187339262 && str.equals(METHOD_LIVE_ALERT_SIZE_CHANGED) && bundle != null && companion != null) {
                    companion.onSizeChange(bundle);
                }
            } else if (str.equals(METHOD_LIVE_ALERT_DESTROY) && companion != null) {
                companion.destroy(bundle);
            }
        } else if (str.equals(METHOD_LIVE_ALERT_BTN_CLICK) && bundle != null) {
            handleButtonClick(bundle);
        }
        return super.call(str, str2, bundle);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
            return true;
        }
        Log.d("SystemUi--SeedlingPlugin", "MediaLiveAlertProvider-->onCreate");
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
