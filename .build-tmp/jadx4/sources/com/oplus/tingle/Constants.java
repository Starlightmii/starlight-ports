package com.oplus.tingle;

import com.oplus.tingle.ipc.utils.VersionUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class Constants {
    public static String getAppPlatformPackageName() {
        if (VersionUtils.isOsVersion11_3()) {
            return "com.oplus.appplatform";
        }
        String str = (String) getAppPlatformPackageNameCompat();
        return str == null ? "" : str;
    }

    private static Object getAppPlatformPackageNameCompat() {
        return null;
    }

    public static String getBinderDescriptor() {
        return VersionUtils.isOsVersion11_3() ? "com.oplus.epona.binder" : (String) getBinderDescriptorCompat();
    }

    private static Object getBinderDescriptorCompat() {
        return null;
    }

    public static String getExtraBinder() {
        return VersionUtils.isOsVersion11_3() ? "com.oplus.epona.ext_binder" : (String) getExtraBinderCompat();
    }

    private static Object getExtraBinderCompat() {
        return null;
    }

    private static Object getMasterProviderCompat() {
        return null;
    }

    public static String getMasterProviderUri() {
        return VersionUtils.isOsVersion11_3() ? "com.oplus.appplatform.master.provider" : (String) getMasterProviderCompat();
    }
}
