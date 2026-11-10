package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.oplus.systemui.plugins.qs.customize.data.SepTileType;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SepMoreTileConfigRepository$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SepMoreTileConfigRepository f$0;

    public final Object invoke() {
        int i = this.$r8$classId;
        SepMoreTileConfigRepository sepMoreTileConfigRepository = this.f$0;
        switch (i) {
            case 0:
                Map map = sepMoreTileConfigRepository.typeToTileSpecs;
                List list = (List) (map != null ? map : null).get(SepTileType.RECOMMEND);
                return list == null ? CollectionsKt__CollectionsKt.emptyList() : list;
            case 1:
                Map map2 = sepMoreTileConfigRepository.typeToTileSpecs;
                List list2 = (List) (map2 != null ? map2 : null).get(SepTileType.CONNECTIVITY);
                return list2 == null ? CollectionsKt__CollectionsKt.emptyList() : list2;
            case 2:
                Map map3 = sepMoreTileConfigRepository.typeToTileSpecs;
                List list3 = (List) (map3 != null ? map3 : null).get(SepTileType.DISPLAY);
                return list3 == null ? CollectionsKt__CollectionsKt.emptyList() : list3;
            default:
                Map map4 = sepMoreTileConfigRepository.typeToTileSpecs;
                List list4 = (List) (map4 != null ? map4 : null).get(SepTileType.TOOLS);
                return list4 == null ? CollectionsKt__CollectionsKt.emptyList() : list4;
        }
    }
}
