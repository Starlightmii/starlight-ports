package com.oplus.systemui.qs.detail.data;

import android.content.Context;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QSDetailRepository {
    Flow getSwitchState();

    Context getThemeContext();

    Flow getUpdateConfigEvent();

    Context updateThemeContext();
}
