package com.oplus.systemui.preventmode;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.oplus.keyguard.settings.KeyguardSettingUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardPreventModeVisibilityProvider extends ContentProvider {
    public static final Companion Companion = new Companion();
    public static final int KEYGUARD_PREVENT_MODE_INVISIBLE = 1;
    public static final int KEYGUARD_PREVENT_MODE_VISIBLE = 0;
    public static final String KEY_KEYGUARD_PREVENT_MODE_VISIBLE = "com.oplus.settings.item_visible";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (KeyguardSettingUtil.Companion.isCanShowPreventMode(requireContext())) {
            bundle2.putInt(KEY_KEYGUARD_PREVENT_MODE_VISIBLE, 0);
            return bundle2;
        }
        bundle2.putInt(KEY_KEYGUARD_PREVENT_MODE_VISIBLE, 1);
        return bundle2;
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
