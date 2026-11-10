package com.oplusos.systemui.common.utils;

import com.oplus.multiapp.OplusMultiAppManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMultiAppUtils {
    public static final OplusMultiAppUtils INSTANCE = new OplusMultiAppUtils();

    public static final String getMultiAppName(Integer num, String str) {
        if (num == null || str == null || str.length() == 0) {
            return null;
        }
        return OplusMultiAppManager.getInstance().getMultiAppAlias(str, num.intValue());
    }

    public static final boolean isClonedForPkg(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return !OplusMultiAppManager.getInstance().getMultiAppUids(str).isEmpty();
    }

    public static final boolean isOplusMultiAppUser(Integer num) {
        if (num == null) {
            return false;
        }
        return OplusMultiAppManager.getInstance().isMultiAppUserId(num.intValue());
    }
}
