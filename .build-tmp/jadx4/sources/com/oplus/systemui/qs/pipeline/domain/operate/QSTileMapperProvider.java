package com.oplus.systemui.qs.pipeline.domain.operate;

import android.content.res.Resources;
import com.oplus.systemui.qs.tiles.ScreenRecorderTile;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSTileMapperProvider {
    public static final Companion Companion = new Companion();
    private static final String SEPARATOR = ":";
    private static final String TAG = "QSTileMapperProvider";
    private final Resources resources;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSTileMapperProvider$getBatterySaverMapper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Provider {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public final List<String> get() {
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isPowerSaveCustomTileSupport) {
                return CollectionsKt__CollectionsJVMKt.listOf("custom(com.oplus.battery/com.oplus.powermanager.powersave.PowerSaveTileService)");
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSTileMapperProvider$getBatterySaverMapper$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Provider {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public final List<String> get() {
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isPowerSaveCustomTileSupport) {
                return null;
            }
            return CollectionsKt__CollectionsJVMKt.listOf("battery");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSTileMapperProvider$getScreenRecorderMapper$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C02651 implements Provider {
        public static final C02651 INSTANCE = new C02651();

        public final List<String> get() {
            ScreenRecorderTile.Companion.getClass();
            if (ScreenRecorderTile.Companion.useSysRecordTile()) {
                return CollectionsKt__CollectionsJVMKt.listOf(ScreenRecorderTile.TILE_SPEC);
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSTileMapperProvider$getScreenRecorderMapper$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C02662 implements Provider {
        public static final C02662 INSTANCE = new C02662();

        public final List<String> get() {
            ScreenRecorderTile.Companion.getClass();
            return ScreenRecorderTile.Companion.useSysRecordTile() ? CollectionsKt__CollectionsJVMKt.listOf(ScreenRecorderTile.TILE_SPEC) : CollectionsKt__CollectionsJVMKt.listOf("custom(com.oplus.screenrecorder/.floatwindow.services.QuickPanelService)");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSTileMapperProvider$getScreenRecorderMapper$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements Provider {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public final List<String> get() {
            ScreenRecorderTile.Companion.getClass();
            if (ScreenRecorderTile.Companion.useSysRecordTile()) {
                return null;
            }
            return CollectionsKt__CollectionsJVMKt.listOf("custom(com.oplus.screenrecorder/.floatwindow.services.QuickPanelService)");
        }
    }

    public QSTileMapperProvider(Resources resources) {
        this.resources = resources;
    }

    public final Map<String, Provider> getBatterySaverMapper() {
        return MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to("battery", AnonymousClass1.INSTANCE), TuplesKt.to("custom(com.oplus.battery/com.oplus.powermanager.powersave.PowerSaveTileService)", AnonymousClass2.INSTANCE)});
    }

    public final Map<String, Provider> getScreenRecorderMapper() {
        return MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to("custom(com.oplus.screenrecorder/.floatwindow.services.QuickPanelService)", C02651.INSTANCE), TuplesKt.to("custom(com.coloros.screenrecorder/.services.QuickPanelService)", C02662.INSTANCE), TuplesKt.to(ScreenRecorderTile.TILE_SPEC, AnonymousClass3.INSTANCE)});
    }

    public final Map<String, Provider> getTileMappers() {
        return MapsKt__MapsKt.plus(MapsKt__MapsKt.plus(getTileStaticMappers(), getBatterySaverMapper()), getScreenRecorderMapper());
    }

    public final Map<String, Provider> getTileStaticMappers() {
        Pair pair;
        String[] strArr = (String[]) ArraysKt___ArraysJvmKt.plus(this.resources.getStringArray(2130903277), this.resources.getStringArray(2130903278));
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            str.getClass();
            List listSplit$default = StringsKt__StringsKt.split$default(str, new String[]{SEPARATOR}, 2, 2);
            if (listSplit$default.size() != 2 || Intrinsics.areEqual(listSplit$default.get(0), listSplit$default.get(1))) {
                LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "Malformed item in array: ".concat(str));
                pair = null;
            } else {
                Object obj = listSplit$default.get(0);
                QSTileMapperProvider$getTileStaticMappers$1$1 qSTileMapperProvider$getTileStaticMappers$1$1 = new QSTileMapperProvider$getTileStaticMappers$1$1();
                qSTileMapperProvider$getTileStaticMappers$1$1.$elements = listSplit$default;
                VarHandle.storeStoreFence();
                pair = TuplesKt.to(obj, qSTileMapperProvider$getTileStaticMappers$1$1);
            }
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return MapsKt__MapsKt.toMap(arrayList);
    }
}
