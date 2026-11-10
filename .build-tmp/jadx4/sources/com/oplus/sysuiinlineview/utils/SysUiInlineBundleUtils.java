package com.oplus.sysuiinlineview.utils;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelUuid;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUiInlineBundleUtils {
    public static final SysUiInlineBundleUtils INSTANCE = new SysUiInlineBundleUtils();
    public static final String KEY_DISPLAY_ID = "bundle_key_host_view_display_id";
    public static final String KEY_HOST_UUID = "bundle_key_host_view_uuid";
    public static final String KEY_INPUT_TOKEN = "bundle_key_host_view_input_token";
    public static final String KEY_VIEW_CONFIGURATION = "bundle_key_host_view_configuration";
    public static final String KEY_VIEW_HEIGHT = "bundle_key_host_view_height";
    public static final String KEY_VIEW_WIDTH = "bundle_key_host_view_width";

    public static final int getDisplayId(Bundle bundle, int i) {
        return bundle == null ? i : bundle.getInt(KEY_DISPLAY_ID, i);
    }

    public static final ParcelUuid getHostToken(Bundle bundle) {
        return (ParcelUuid) bundle.getParcelable(KEY_HOST_UUID, ParcelUuid.class);
    }

    public static final IBinder getInputToken(Bundle bundle) {
        return bundle.getBinder(KEY_INPUT_TOKEN);
    }

    public static final Configuration getViewConfiguration(Bundle bundle) {
        return (Configuration) bundle.getParcelable(KEY_VIEW_CONFIGURATION, Configuration.class);
    }

    public static final int getViewHeight(Bundle bundle, int i) {
        return bundle == null ? i : bundle.getInt(KEY_VIEW_HEIGHT, i);
    }

    public static final int getViewWidth(Bundle bundle, int i) {
        return bundle == null ? i : bundle.getInt(KEY_VIEW_WIDTH, i);
    }

    public static final Bundle setDisplayId(Bundle bundle, int i) {
        bundle.putInt(KEY_DISPLAY_ID, i);
        return bundle;
    }

    public static final Bundle setHostToken(Bundle bundle, ParcelUuid parcelUuid) {
        bundle.putParcelable(KEY_HOST_UUID, parcelUuid);
        return bundle;
    }

    public static final Bundle setInputToken(Bundle bundle, IBinder iBinder) {
        bundle.putBinder(KEY_INPUT_TOKEN, iBinder);
        return bundle;
    }

    public static final Bundle setViewConfiguration(Bundle bundle, Configuration configuration) {
        bundle.putParcelable(KEY_VIEW_CONFIGURATION, configuration);
        return bundle;
    }

    public static final Bundle setViewHeight(Bundle bundle, int i) {
        bundle.putInt(KEY_VIEW_HEIGHT, i);
        return bundle;
    }

    public static final Bundle setViewWidth(Bundle bundle, int i) {
        bundle.putInt(KEY_VIEW_WIDTH, i);
        return bundle;
    }
}
