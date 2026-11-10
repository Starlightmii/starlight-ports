package com.oplus.systemui.privacy;

import com.android.systemui.privacy.PrivacyItem;
import com.android.systemui.privacy.PrivacyType;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusPrivacyConfigExImplKt {
    public static final PrivacyType access$resolveDominantPrivacyType(List list) {
        PrivacyType privacyType = null;
        if (list.isEmpty()) {
            return null;
        }
        Iterator it = list.iterator();
        int i = Integer.MAX_VALUE;
        while (it.hasNext()) {
            PrivacyType privacyType2 = ((PrivacyItem) it.next()).getPrivacyType();
            int iOrdinal = OplusPrivacyConfigKind.Companion.fromPrivacyType(privacyType2).ordinal();
            if (iOrdinal < i) {
                privacyType = privacyType2;
                i = iOrdinal;
            }
        }
        return privacyType;
    }
}
