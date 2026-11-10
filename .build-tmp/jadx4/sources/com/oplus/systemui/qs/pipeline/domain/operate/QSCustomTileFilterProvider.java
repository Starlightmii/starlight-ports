package com.oplus.systemui.qs.pipeline.domain.operate;

import android.content.res.Resources;
import com.oplus.systemui.qs.helper.QSTileFilterHelper;
import com.oplus.systemui.qs.tiles.ScreenRecorderTile;
import com.oplusos.systemui.common.feature.ChargingFeatureOption;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSCustomTileFilterProvider {
    private final Resources resources;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSCustomTileFilterProvider$getTileFilters$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Provider {
        public /* synthetic */ QSCustomTileFilterProvider this$0;

        /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
        public final Boolean m290get() {
            QSFeatureOption.INSTANCE.getClass();
            return Boolean.valueOf(!QSFeatureOption.isPowerSaveCustomTileSupport);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSCustomTileFilterProvider$getTileFilters$10, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass10 implements Provider {
        public /* synthetic */ QSCustomTileFilterProvider this$0;

        /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
        public final Boolean m291get() {
            return Boolean.valueOf(CustomizeFeatureOption.sFloatAssistantFeature);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSCustomTileFilterProvider$getTileFilters$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Provider {
        public /* synthetic */ QSCustomTileFilterProvider this$0;

        /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
        public final Boolean m292get() {
            ScreenRecorderTile.Companion.getClass();
            return Boolean.valueOf(ScreenRecorderTile.Companion.useSysRecordTile());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSCustomTileFilterProvider$getTileFilters$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements Provider {
        public /* synthetic */ QSCustomTileFilterProvider this$0;

        /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
        public final Boolean m293get() {
            QSFeatureOption.INSTANCE.getClass();
            return Boolean.valueOf(QSFeatureOption.isSmartDriveDisable());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSCustomTileFilterProvider$getTileFilters$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements Provider {
        public /* synthetic */ QSCustomTileFilterProvider this$0;

        /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
        public final Boolean m294get() {
            QSFeatureOption.INSTANCE.getClass();
            return Boolean.valueOf(!QSFeatureOption.isTemperatureSupport());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSCustomTileFilterProvider$getTileFilters$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements Provider {
        public /* synthetic */ QSCustomTileFilterProvider this$0;

        /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
        public final Boolean m295get() {
            ChargingFeatureOption.INSTANCE.getClass();
            return Boolean.valueOf(!ChargingFeatureOption.isSupportWirelessCharging);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSCustomTileFilterProvider$getTileFilters$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass6 implements Provider {
        public /* synthetic */ QSCustomTileFilterProvider this$0;

        /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
        public final Boolean m296get() {
            QSFeatureOption.INSTANCE.getClass();
            return Boolean.valueOf(!QSFeatureOption.isIris5ModeSupport());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSCustomTileFilterProvider$getTileFilters$7, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass7 implements Provider {
        public /* synthetic */ QSCustomTileFilterProvider this$0;

        /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
        public final Boolean m297get() {
            QSFeatureOption.INSTANCE.getClass();
            return Boolean.valueOf(QSFeatureOption.isIris5ModeSupport() || !QSFeatureOption.isOsieModeSupport());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSCustomTileFilterProvider$getTileFilters$8, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass8 implements Provider {
        public /* synthetic */ QSCustomTileFilterProvider this$0;

        /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
        public final Boolean m298get() {
            FlavorOneFeatureOption.INSTANCE.getClass();
            return Boolean.valueOf(!FlavorOneFeatureOption.isSupportActiveSpaceGesture());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.operate.QSCustomTileFilterProvider$getTileFilters$9, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass9 implements Provider {
        public /* synthetic */ QSCustomTileFilterProvider this$0;

        /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
        public final Boolean m299get() {
            FlavorOneFeatureOption.INSTANCE.getClass();
            return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && FlavorOneFeatureOption.sIsDisableWindowTile);
        }
    }

    public QSCustomTileFilterProvider(Resources resources) {
        this.resources = resources;
    }

    public final Map<String, Provider> getTileFilters() {
        Map<String, Provider> tileStaticFilters = getTileStaticFilters();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        Pair pair = TuplesKt.to("custom(com.oplus.battery/com.oplus.powermanager.powersave.PowerSaveTileService)", anonymousClass1);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        Pair pair2 = TuplesKt.to("custom(com.oplus.screenrecorder/.floatwindow.services.QuickPanelService)", anonymousClass2);
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        Pair pair3 = TuplesKt.to("custom(com.coloros.smartdrive/.ui.SmartDriveTile)", anonymousClass3);
        AnonymousClass4 anonymousClass4 = new AnonymousClass4();
        anonymousClass4.this$0 = this;
        VarHandle.storeStoreFence();
        Pair pair4 = TuplesKt.to(QSTileFilterHelper.AUTO_SCREEN_TEMPERATURE, anonymousClass4);
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        Pair pair5 = TuplesKt.to("custom(com.oplus.battery/com.oplus.powermanager.wirelesscharg.WirelessReverseControService)", anonymousClass5);
        AnonymousClass6 anonymousClass6 = new AnonymousClass6();
        anonymousClass6.this$0 = this;
        VarHandle.storeStoreFence();
        Pair pair6 = TuplesKt.to("custom(com.android.settings/com.oplus.settings.feature.display.video.Iris5TileService)", anonymousClass6);
        AnonymousClass7 anonymousClass7 = new AnonymousClass7();
        anonymousClass7.this$0 = this;
        VarHandle.storeStoreFence();
        Pair pair7 = TuplesKt.to("custom(com.android.settings/com.oplus.settings.feature.display.video.OsieTileService)", anonymousClass7);
        AnonymousClass8 anonymousClass8 = new AnonymousClass8();
        anonymousClass8.this$0 = this;
        VarHandle.storeStoreFence();
        Pair pair8 = TuplesKt.to(QSTileFilterHelper.SPACE_GESTRUE_TILE, anonymousClass8);
        AnonymousClass9 anonymousClass9 = new AnonymousClass9();
        anonymousClass9.this$0 = this;
        VarHandle.storeStoreFence();
        Pair pair9 = TuplesKt.to(QSTileFilterHelper.DISABLE_WINDOW_TILE, anonymousClass9);
        AnonymousClass10 anonymousClass10 = new AnonymousClass10();
        anonymousClass10.this$0 = this;
        VarHandle.storeStoreFence();
        return MapsKt__MapsKt.plus(tileStaticFilters, MapsKt__MapsKt.mapOf(new Pair[]{pair, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair9, TuplesKt.to("custom(com.coloros.floatassistant/.FloatAssistantTileService)", anonymousClass10)}));
    }

    public final Map<String, Provider> getTileStaticFilters() {
        String[] stringArray = this.resources.getStringArray(2130903279);
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(stringArray.length);
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (String str : stringArray) {
            linkedHashMap.put(str, QSCustomTileFilterProvider$getTileStaticFilters$1$1.INSTANCE);
        }
        return linkedHashMap;
    }
}
