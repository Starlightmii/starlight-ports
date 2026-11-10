package com.oplus.systemui.privacy;

import com.android.systemui.privacy.OplusPrivacyConfigEx;
import com.android.systemui.privacy.PrivacyItem;
import com.android.systemui.privacy.PrivacyType;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPrivacyConfigExImpl extends OplusPrivacyConfigEx {
    public PrivacyType dominantPrivacyType(List<PrivacyItem> list) {
        return OplusPrivacyConfigExImplKt.access$resolveDominantPrivacyType(list);
    }

    public int privacyDotFillColorResForPrivacyItems(List<PrivacyItem> list) {
        OplusPrivacyConfigKind oplusPrivacyConfigKindFromPrivacyType;
        PrivacyType privacyTypeAccess$resolveDominantPrivacyType = OplusPrivacyConfigExImplKt.access$resolveDominantPrivacyType(list);
        if (privacyTypeAccess$resolveDominantPrivacyType == null || (oplusPrivacyConfigKindFromPrivacyType = OplusPrivacyConfigKind.Companion.fromPrivacyType(privacyTypeAccess$resolveDominantPrivacyType)) == null) {
            oplusPrivacyConfigKindFromPrivacyType = OplusPrivacyConfigKind.NONE;
        }
        return oplusPrivacyConfigKindFromPrivacyType.fillColorRes();
    }
}
