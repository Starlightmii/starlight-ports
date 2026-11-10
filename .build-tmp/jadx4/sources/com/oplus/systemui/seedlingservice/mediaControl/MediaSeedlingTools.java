package com.oplus.systemui.seedlingservice.mediaControl;

import com.oplus.pantanal.seedling.bean.CancelPanelActionConfigEnum;
import com.oplus.pantanal.seedling.bean.SeedlingCard;
import com.oplus.pantanal.seedling.bean.SeedlingHostEnum;
import com.oplus.pantanal.seedling.update.SeedlingCardOptions;
import com.oplus.pantanal.seedling.util.SeedlingTool;
import com.oplus.systemui.seedlingservice.datamanager.UiData;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaSeedlingTools {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MediaSeedlingTools";
    private static final SeedlingCardOptions baseCardOptions;
    private static final SeedlingCardOptions disableNotificationCardOptions;
    private static final SeedlingCardOptions disableSecondHomeCardOptions;
    private static final SeedlingCardOptions disableStatusBarAndNotificationCardOptions;
    private static final SeedlingCardOptions disableStatusBarCardOptions;

    static {
        SeedlingHostEnum seedlingHostEnum = SeedlingHostEnum.Notification;
        Boolean bool = Boolean.TRUE;
        Pair pair = TuplesKt.to(seedlingHostEnum, bool);
        SeedlingHostEnum seedlingHostEnum2 = SeedlingHostEnum.StatusBar;
        Pair pair2 = TuplesKt.to(seedlingHostEnum2, bool);
        SeedlingHostEnum seedlingHostEnum3 = SeedlingHostEnum.SecondaryNotification;
        Pair pair3 = TuplesKt.to(seedlingHostEnum3, bool);
        SeedlingHostEnum seedlingHostEnum4 = SeedlingHostEnum.SecondaryLockScreen;
        Pair pair4 = TuplesKt.to(seedlingHostEnum4, bool);
        SeedlingHostEnum seedlingHostEnum5 = SeedlingHostEnum.AOD;
        Boolean bool2 = Boolean.FALSE;
        SeedlingCardOptions seedlingCardOptions = new SeedlingCardOptions((String) null, (String) null, true, (Boolean) null, false, 4, (List) null, MapsKt__MapsKt.mapOf(new Pair[]{pair, pair2, pair3, pair4, TuplesKt.to(seedlingHostEnum5, bool2)}), MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(seedlingHostEnum, bool), TuplesKt.to(seedlingHostEnum2, bool2)}), (CancelPanelActionConfigEnum) null, (Map) null, (Integer) null, 0, false, (Long) null, (Map) null, 65115, (DefaultConstructorMarker) null);
        baseCardOptions = seedlingCardOptions;
        disableStatusBarCardOptions = SeedlingCardOptions.copy$default(seedlingCardOptions, (String) null, (String) null, true, (Boolean) null, false, (Integer) null, (List) null, MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(seedlingHostEnum, bool), TuplesKt.to(seedlingHostEnum2, bool2), TuplesKt.to(seedlingHostEnum3, bool), TuplesKt.to(seedlingHostEnum4, bool)}), (Map) null, (CancelPanelActionConfigEnum) null, (Map) null, (Integer) null, 0, false, (Long) null, (Map) null, 65403, (Object) null);
        disableNotificationCardOptions = SeedlingCardOptions.copy$default(seedlingCardOptions, (String) null, (String) null, true, (Boolean) null, false, (Integer) null, (List) null, MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(seedlingHostEnum, bool2), TuplesKt.to(seedlingHostEnum2, bool), TuplesKt.to(seedlingHostEnum3, bool), TuplesKt.to(seedlingHostEnum4, bool)}), (Map) null, (CancelPanelActionConfigEnum) null, (Map) null, (Integer) null, 0, false, (Long) null, (Map) null, 65403, (Object) null);
        disableStatusBarAndNotificationCardOptions = SeedlingCardOptions.copy$default(seedlingCardOptions, (String) null, (String) null, true, (Boolean) null, false, (Integer) null, (List) null, MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(seedlingHostEnum, bool2), TuplesKt.to(seedlingHostEnum2, bool2), TuplesKt.to(seedlingHostEnum3, bool), TuplesKt.to(seedlingHostEnum4, bool)}), (Map) null, (CancelPanelActionConfigEnum) null, (Map) null, (Integer) null, 0, false, (Long) null, (Map) null, 65403, (Object) null);
        disableSecondHomeCardOptions = SeedlingCardOptions.copy$default(seedlingCardOptions, (String) null, (String) null, true, (Boolean) null, false, (Integer) null, (List) null, MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(seedlingHostEnum, bool), TuplesKt.to(seedlingHostEnum2, bool), TuplesKt.to(seedlingHostEnum3, bool2), TuplesKt.to(seedlingHostEnum4, bool2)}), (Map) null, (CancelPanelActionConfigEnum) null, (Map) null, (Integer) null, 0, false, (Long) null, (Map) null, 65403, (Object) null);
    }

    public static /* synthetic */ SeedlingCardOptions getOptions$default(MediaSeedlingTools mediaSeedlingTools, boolean z, boolean z2, boolean z3, boolean z4, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        if ((i & 8) != 0) {
            z4 = true;
        }
        if ((i & 16) != 0) {
            str = null;
        }
        return mediaSeedlingTools.getOptions(z, z2, z3, z4, str);
    }

    public final SeedlingCardOptions getOptions(boolean z, boolean z2, boolean z3, boolean z4, String str) {
        if (z2 && z) {
            return disableStatusBarAndNotificationCardOptions;
        }
        if (z) {
            return disableStatusBarCardOptions;
        }
        if (z2) {
            return disableNotificationCardOptions;
        }
        return z3 ? disableSecondHomeCardOptions : SeedlingCardOptions.copy$default(baseCardOptions, (String) null, str, z4, (Boolean) null, false, (Integer) null, (List) null, (Map) null, (Map) null, (CancelPanelActionConfigEnum) null, (Map) null, (Integer) null, 0, false, (Long) null, (Map) null, 65529, (Object) null);
    }

    public final void hideSeedlingCard(SeedlingCard seedlingCard, SeedlingCardOptions seedlingCardOptions) {
        SeedlingTool.INSTANCE.updateData(seedlingCard, (JSONObject) null, seedlingCardOptions);
    }

    public final void updateSeedling(SeedlingCard seedlingCard, UiData uiData, SeedlingCardOptions seedlingCardOptions) {
        SeedlingTool.INSTANCE.updateData(seedlingCard, uiData.build(), seedlingCardOptions);
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
