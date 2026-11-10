package com.oplus.systemui.plugins.qs.customize.data.repo;

import android.text.TextUtils;
import com.oplus.systemui.plugins.qs.ContextInstance;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepDefaultGridInfoRepository {
    private final ContextInstance contextInstance;

    public SepDefaultGridInfoRepository(ContextInstance contextInstance) {
        this.contextInstance = contextInstance;
    }

    public final SepDefaultGridInfoRepository copy(ContextInstance contextInstance) {
        return new SepDefaultGridInfoRepository(contextInstance);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SepDefaultGridInfoRepository) && Intrinsics.areEqual(this.contextInstance, ((SepDefaultGridInfoRepository) obj).contextInstance);
    }

    public final List<String> getDefaultGridInfo() {
        List listSplit$default = StringsKt__StringsKt.split$default(this.contextInstance.getPluginContext().getResources().getString(2131956229), new String[]{"*"}, 0, 6);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSplit$default) {
            if (!TextUtils.isEmpty((String) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final int hashCode() {
        return this.contextInstance.hashCode();
    }

    public String toString() {
        return "SepDefaultGridInfoRepository(contextInstance=" + this.contextInstance + ")";
    }
}
