package com.oplus.systemui.plugins.qs.customize.data.repo;

import android.text.TextUtils;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepDefaultTileSpecRepository {
    public static final Companion Companion = new Companion();
    private final ContextInstance contextInstance;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Pair<List<String>, List<String>> parseSpecList$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(String str) {
            List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            List listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
            List listSplit$default = StringsKt__StringsKt.split$default(str, new String[]{";"}, 0, 6);
            ArrayList arrayList = new ArrayList();
            for (Object obj : listSplit$default) {
                if (!TextUtils.isEmpty((String) obj)) {
                    arrayList.add(obj);
                }
            }
            int size = arrayList.size();
            int i = 0;
            int i2 = 0;
            while (i2 < size) {
                Object obj2 = arrayList.get(i2);
                i2++;
                int i3 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                String str2 = (String) obj2;
                if (i == 0) {
                    listEmptyList = CollectionsKt___CollectionsKt.distinct(StringsKt__StringsKt.split$default(str2, new String[]{OplusPreventModeData.SEPARATOR}, 0, 6));
                } else if (1 == i) {
                    listEmptyList2 = CollectionsKt___CollectionsKt.distinct(StringsKt__StringsKt.split$default(str2, new String[]{OplusPreventModeData.SEPARATOR}, 0, 6));
                }
                i = i3;
            }
            return new Pair<>(listEmptyList, listEmptyList2);
        }
    }

    public SepDefaultTileSpecRepository(ContextInstance contextInstance) {
        this.contextInstance = contextInstance;
    }

    public final SepDefaultTileSpecRepository copy(ContextInstance contextInstance) {
        return new SepDefaultTileSpecRepository(contextInstance);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SepDefaultTileSpecRepository) && Intrinsics.areEqual(this.contextInstance, ((SepDefaultTileSpecRepository) obj).contextInstance);
    }

    public final Pair<List<String>, List<String>> getDefaultTileSpecs() {
        int i;
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isApplyLightOSQsTile) {
            i = 2131956234;
        } else {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isTablet5G()) {
                i = 2131956238;
            } else {
                i = FeatureOption.isTabletWifi() ? 2131956239 : 2131956237;
            }
        }
        return Companion.parseSpecList$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(this.contextInstance.getPluginContext().getResources().getString(i));
    }

    public final Pair<List<String>, List<String>> getSuperPowerSaveModeTileSpecs() {
        return Companion.parseSpecList$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(this.contextInstance.getPluginContext().getResources().getString(2131956240));
    }

    public final Pair<List<String>, List<String>> getWellBeingTileSpecs() {
        return Companion.parseSpecList$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(this.contextInstance.getPluginContext().getResources().getString(2131956241));
    }

    public final int hashCode() {
        return this.contextInstance.hashCode();
    }

    public String toString() {
        return "SepDefaultTileSpecRepository(contextInstance=" + this.contextInstance + ")";
    }
}
