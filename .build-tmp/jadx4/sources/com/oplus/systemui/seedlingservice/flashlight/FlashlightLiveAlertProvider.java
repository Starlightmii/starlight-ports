package com.oplus.systemui.seedlingservice.flashlight;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface$;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlashlightLiveAlertProvider extends ContentProvider {
    public static final Companion Companion = new Companion(null);
    private static final String METHOD_LIVE_ALERT_CREATE = "onLiveAlertCreate";
    private static final String METHOD_LIVE_ALERT_DESTROY = "onLiveAlertDestroy";
    private static final String METHOD_LIVE_ALERT_SIZE_CHANGED = "onLiveAlertSizeChanged";
    private static final String TAG = "FlashlightLiveAlertProvider";

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("call: method=", str, " arg=", str2, " extras=");
        sbM.append(bundle);
        LogUtil.i(TAG, sbM.toString());
        int iHashCode = str.hashCode();
        if (iHashCode != -359307767) {
            if (iHashCode != -187339262) {
                if (iHashCode == 1079729421 && str.equals(METHOD_LIVE_ALERT_CREATE)) {
                    FlashlightTip.INSTANCE.liveAlertCardCreate();
                }
            } else if (str.equals(METHOD_LIVE_ALERT_SIZE_CHANGED) && bundle != null) {
                FlashlightTip.INSTANCE.onSizeChanged(bundle.getInt("oldSize"), bundle.getInt("newSize"));
            }
        } else if (str.equals(METHOD_LIVE_ALERT_DESTROY)) {
            FlashlightTip.INSTANCE.liveAlertCardDestroy();
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
        LogUtil.i(TAG, "onCreate");
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

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
