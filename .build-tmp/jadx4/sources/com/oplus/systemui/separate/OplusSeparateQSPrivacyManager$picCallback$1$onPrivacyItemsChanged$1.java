package com.oplus.systemui.separate;

import android.support.v4.content.FileProvider$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.systemui.plugins.qs.QSPrivary;
import com.android.systemui.privacy.PrivacyApplication;
import com.android.systemui.privacy.PrivacyItem;
import com.android.systemui.privacy.PrivacyType;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSPrivacyManager$picCallback$1$onPrivacyItemsChanged$1 implements Runnable {
    public /* synthetic */ List $privacyItems;
    public /* synthetic */ OplusSeparateQSPrivacyManager this$0;

    @Override // java.lang.Runnable
    public final void run() {
        String packageName;
        PrivacyApplication application;
        PrivacyApplication application2;
        List listSortedWith = CollectionsKt___CollectionsKt.sortedWith(this.$privacyItems, new OplusSeparateQSPrivacyManager$filterAndSelect$lambda$9$$inlined$sortedByDescending$1(1));
        List list = listSortedWith;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((PrivacyItem) it.next()).getPrivacyType());
        }
        List<PrivacyType> listDistinct = CollectionsKt___CollectionsKt.distinct(arrayList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listDistinct, 10));
        for (PrivacyType privacyType : listDistinct) {
            LogUtil.i$default(LogUtil.TAG_QS, "OplusSeparateQSPrivacyManager", FileProvider$.ExternalSyntheticOutline0.m("onPrivacyItemsChanged permGroupName ", privacyType.getPermGroupName()), null, 8);
            arrayList2.add(privacyType.getPermGroupName());
        }
        PrivacyItem privacyItem = (PrivacyItem) CollectionsKt___CollectionsKt.firstOrNull(listSortedWith);
        if (privacyItem == null || (application2 = privacyItem.getApplication()) == null || (packageName = application2.getPackageName()) == null) {
            packageName = "";
        }
        PrivacyItem privacyItem2 = (PrivacyItem) CollectionsKt___CollectionsKt.firstOrNull(listSortedWith);
        int uid = (privacyItem2 == null || (application = privacyItem2.getApplication()) == null) ? 0 : application.getUid();
        if (packageName.length() == 0 && Intrinsics.areEqual(this.this$0.lastPrivacyPackageName, "")) {
            return;
        }
        this.this$0.lastPrivacyPackageName = packageName;
        CharSequence labelForPackage = this.this$0.getLabelForPackage(uid, packageName);
        int size = arrayList2.size();
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(uid, "onPrivacyItemsChanged packageName ", packageName, ", uid ", ", size: ");
        sbM.append(size);
        sbM.append(", appName: ");
        sbM.append((Object) labelForPackage);
        LogUtil.i$default(LogUtil.TAG_QS, "OplusSeparateQSPrivacyManager", sbM.toString(), null, 8);
        this.this$0.qsPrivacyInfo = new QSPrivary.QSPrivacyInfo(arrayList2, packageName, uid, labelForPackage.toString());
        CopyOnWriteArrayList<QSPrivary.CallBack> copyOnWriteArrayList = this.this$0.qsPrivaryCallBacks;
        OplusSeparateQSPrivacyManager oplusSeparateQSPrivacyManager = this.this$0;
        for (QSPrivary.CallBack callBack : copyOnWriteArrayList) {
            QSPrivary.QSPrivacyInfo qSPrivacyInfo = oplusSeparateQSPrivacyManager.qsPrivacyInfo;
            if (qSPrivacyInfo != null) {
                callBack.onPrivacyItemsChanged(oplusSeparateQSPrivacyManager.context, qSPrivacyInfo);
            }
        }
    }
}
