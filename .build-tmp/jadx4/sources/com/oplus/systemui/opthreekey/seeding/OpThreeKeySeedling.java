package com.oplus.systemui.opthreekey.seeding;

import android.content.Context;
import com.android.systemui.communal.widgets.CommunalWidgetHost$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.statusbar.seeding.StartSeedlingService;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OpThreeKeySeedling {
    public static final Companion Companion = new Companion();
    private static final String KEY_RING_MODE_TYPE = "ringModeType";
    private static final String RING_MODE_EVENT = "ringModeEvent";
    private static final String TAG = "OpThreeKeySeedling";
    private final StartSeedlingService startSeedlingService;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OpThreeKeySeedling(StartSeedlingService startSeedlingService) {
        this.startSeedlingService = startSeedlingService;
    }

    public final void updateThreeKeySeeding(Context context, int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "updateThreeKeySeeding threeKeyState: ", "OpThreeKeySeedling-->", "SystemUi--Volume");
        }
        this.startSeedlingService.updateData(context, RING_MODE_EVENT, CommunalWidgetHost$.ExternalSyntheticOutline0.m(i, KEY_RING_MODE_TYPE));
    }
}
