package com.oplus.systemui.qs.pipeline.data.repository;

import android.text.TextUtils;
import com.android.systemui.qs.pipeline.data.repository.IQsSettingInitialStateRepository;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.feature.FeatureOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsSettingInitialStateRepository implements IQsSettingInitialStateRepository {
    private final HashMap<Integer, List<String>> initQsSettings = new HashMap<>();
    private final SecureSettings secureSettings;

    public OplusQsSettingInitialStateRepository(SecureSettings secureSettings) {
        this.secureSettings = secureSettings;
    }

    public synchronized List<String> getInitOrCurrentTileSpecs(int i) {
        List listSplit$default;
        if (FeatureOption.INSTANCE.getFirstApiLevel() > 35) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List arrayList = this.initQsSettings.get(Integer.valueOf(i));
        if (arrayList == null) {
            String stringForUser = this.secureSettings.getStringForUser("sysui_qs_tiles", i);
            if (TextUtils.isEmpty(stringForUser) || stringForUser == null || (listSplit$default = StringsKt__StringsKt.split$default(stringForUser, new String[]{OplusPreventModeData.SEPARATOR}, 0, 6)) == null) {
                List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                arrayList = listEmptyList;
            } else {
                arrayList = new ArrayList();
                for (Object obj : listSplit$default) {
                    if (!TextUtils.isEmpty((String) obj)) {
                        arrayList.add(obj);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized List<String> getInitTileSpecs(int i) {
        if (FeatureOption.INSTANCE.getFirstApiLevel() > 35) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List<String> listEmptyList = this.initQsSettings.get(Integer.valueOf(i));
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        return listEmptyList;
    }

    public synchronized void setInitTileSpecs(int i, List<String> list) {
        if (FeatureOption.INSTANCE.getFirstApiLevel() > 35) {
            return;
        }
        this.initQsSettings.put(Integer.valueOf(i), list);
    }
}
