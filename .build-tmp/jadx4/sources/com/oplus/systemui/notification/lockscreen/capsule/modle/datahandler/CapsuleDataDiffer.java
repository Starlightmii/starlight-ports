package com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler;

import _COROUTINE.CoroutineDebuggingKt$;
import android.content.Context;
import android.util.Log;
import androidx.collection.FloatList$;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationData;
import com.oplus.systemui.notification.lockscreen.seedling.CapsuleSeedingData;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleDataDiffer {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleDataDiffer";
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ChangeInfo {
        private final CapsuleItemData newData;
        private final CapsuleItemView oldView;

        public ChangeInfo(CapsuleItemView capsuleItemView, CapsuleItemData capsuleItemData) {
            this.oldView = capsuleItemView;
            this.newData = capsuleItemData;
        }

        public final void applyToView() {
            if (!Intrinsics.areEqual(this.oldView.getEntry().getKey(), this.newData.getKey()) || StringsKt__StringsKt.contains(this.newData.getKey(), CapsuleNotificationData.CAPSULE_NOTIF_KEY_PREFIX, false)) {
                return;
            }
            this.oldView.attachCapsuleEntry(this.newData, "onChangedApplyToView");
        }

        public final CapsuleItemView component1() {
            return this.oldView;
        }

        public final CapsuleItemData component2() {
            return this.newData;
        }

        public final ChangeInfo copy(CapsuleItemView capsuleItemView, CapsuleItemData capsuleItemData) {
            return new ChangeInfo(capsuleItemView, capsuleItemData);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ChangeInfo)) {
                return false;
            }
            ChangeInfo changeInfo = (ChangeInfo) obj;
            return Intrinsics.areEqual(this.oldView, changeInfo.oldView) && Intrinsics.areEqual(this.newData, changeInfo.newData);
        }

        public final CapsuleItemData getNewData() {
            return this.newData;
        }

        public final CapsuleItemView getOldView() {
            return this.oldView;
        }

        public final int hashCode() {
            return this.newData.hashCode() + (this.oldView.hashCode() * 31);
        }

        public String toString() {
            return "ChangeInfo(oldView=" + this.oldView + ", newData=" + this.newData + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleDataDiffer(Context context) {
        this.context = context;
    }

    public static void checkSeedlingRemindChange(CapsuleItemView capsuleItemView, CapsuleItemData capsuleItemData, List list) {
        if (Intrinsics.areEqual(capsuleItemView.getKey(), capsuleItemData.getKey())) {
            CapsuleItemData entry = capsuleItemView.getEntry();
            CapsuleSeedingData capsuleSeedingData = entry instanceof CapsuleSeedingData ? (CapsuleSeedingData) entry : null;
            if (capsuleSeedingData != null) {
                boolean remind = capsuleSeedingData.getRemind();
                CapsuleSeedingData capsuleSeedingData2 = capsuleItemData instanceof CapsuleSeedingData ? (CapsuleSeedingData) capsuleItemData : null;
                if (capsuleSeedingData2 != null) {
                    boolean remind2 = capsuleSeedingData2.getRemind();
                    if (remind || !remind2) {
                        return;
                    }
                    list.add(new ChangeInfo(capsuleItemView, capsuleItemData));
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r7v3, types: [com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataDiffer$$ExternalSyntheticLambda0] */
    public final void dispatchUpdatesToContainer(CapsuleListContainer capsuleListContainer, List<CapsuleItemView> list, List<? extends CapsuleItemData> list2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        List mutableList = CollectionsKt___CollectionsKt.toMutableList(CollectionsKt___CollectionsKt.sortedWith(list2, new CapsuleDataDiffer$dispatchUpdatesToContainer$lambda$5$$inlined$sortedByDescending$1()));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator<T> it = list.iterator();
        while (true) {
            Object obj = null;
            if (!it.hasNext()) {
                break;
            }
            CapsuleItemView capsuleItemView = (CapsuleItemView) it.next();
            for (Object obj2 : mutableList) {
                if (Intrinsics.areEqual(capsuleItemView.getKey(), ((CapsuleItemData) obj2).getKey())) {
                    obj = obj2;
                    break;
                }
            }
            CapsuleItemData capsuleItemData = (CapsuleItemData) obj;
            if (capsuleItemData == null) {
                arrayList.add(capsuleItemView);
            } else if (Intrinsics.areEqual(capsuleItemView.getEntry().getContentView(), capsuleItemData.getContentView()) && capsuleItemView.getEntry().getOrderPriority() == capsuleItemData.getOrderPriority()) {
                mutableList.remove(capsuleItemData);
                checkSeedlingRemindChange(capsuleItemView, capsuleItemData, arrayList3);
            } else {
                arrayList2.add(new ChangeInfo(capsuleItemView, capsuleItemData));
                mutableList.remove(capsuleItemData);
                checkSeedlingRemindChange(capsuleItemView, capsuleItemData, arrayList3);
            }
        }
        final int i = 2;
        String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList2, OplusPreventModeData.SEPARATOR, (CharSequence) null, (CharSequence) null, new Function1() { // from class: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataDiffer$$ExternalSyntheticLambda0
            public final Object invoke(Object obj3) {
                switch (i) {
                    case 0:
                        return ((CapsuleItemView) obj3).getKey();
                    case 1:
                        return ((CapsuleItemView) obj3).getKey();
                    case 2:
                        CapsuleDataDiffer.ChangeInfo changeInfo = (CapsuleDataDiffer.ChangeInfo) obj3;
                        return CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(changeInfo.getOldView().getKey(), " to ", changeInfo.getNewData().getKey());
                    default:
                        ((CapsuleDataDiffer.ChangeInfo) obj3).applyToView();
                        return Unit.INSTANCE;
                }
            }
        }, 30);
        if (strJoinToString$default.length() > 0 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            KeyguardSettingProvider$.ExternalSyntheticOutline0.m("onCapsuleChanged: ", strJoinToString$default, "CapsuleDataDiffer-->", "SystemUi--Notification");
        }
        final int i2 = 3;
        ?? r7 = new Function1() { // from class: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataDiffer$$ExternalSyntheticLambda0
            public final Object invoke(Object obj3) {
                switch (i2) {
                    case 0:
                        return ((CapsuleItemView) obj3).getKey();
                    case 1:
                        return ((CapsuleItemView) obj3).getKey();
                    case 2:
                        CapsuleDataDiffer.ChangeInfo changeInfo = (CapsuleDataDiffer.ChangeInfo) obj3;
                        return CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(changeInfo.getOldView().getKey(), " to ", changeInfo.getNewData().getKey());
                    default:
                        ((CapsuleDataDiffer.ChangeInfo) obj3).applyToView();
                        return Unit.INSTANCE;
                }
            }
        };
        int size = arrayList2.size();
        final int i3 = 0;
        int i4 = 0;
        while (i4 < size) {
            int i5 = i4 + 1;
            try {
                r7.invoke(arrayList2.get(i4));
                Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.constructor-impl(ResultKt.createFailure(th));
            }
            i4 = i5;
        }
        final int i6 = 1;
        if (mutableList.isEmpty() && arrayList.isEmpty()) {
            ChangeInfo changeInfo = (ChangeInfo) CollectionsKt___CollectionsKt.lastOrNull(arrayList3);
            if (changeInfo != null) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "CapsuleDataDiffer-->".concat("RemindTest:remindItemChanged: " + changeInfo));
                }
                capsuleListContainer.reTopContainerView(changeInfo.getOldView(), true);
            }
        } else {
            List<CapsuleItemData> list3 = mutableList;
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
            for (CapsuleItemData capsuleItemData2 : list3) {
                CapsuleItemView capsuleItemView2 = new CapsuleItemView(this.context, null);
                capsuleItemView2.attachCapsuleEntry(capsuleItemData2, "onInserted");
                arrayList4.add(capsuleItemView2);
            }
            String strJoinToString$default2 = CollectionsKt___CollectionsKt.joinToString$default(arrayList4, OplusPreventModeData.SEPARATOR, (CharSequence) null, (CharSequence) null, new Function1() { // from class: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataDiffer$$ExternalSyntheticLambda0
                public final Object invoke(Object obj3) {
                    switch (i3) {
                        case 0:
                            return ((CapsuleItemView) obj3).getKey();
                        case 1:
                            return ((CapsuleItemView) obj3).getKey();
                        case 2:
                            CapsuleDataDiffer.ChangeInfo changeInfo2 = (CapsuleDataDiffer.ChangeInfo) obj3;
                            return CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(changeInfo2.getOldView().getKey(), " to ", changeInfo2.getNewData().getKey());
                        default:
                            ((CapsuleDataDiffer.ChangeInfo) obj3).applyToView();
                            return Unit.INSTANCE;
                    }
                }
            }, 30);
            if (strJoinToString$default2.length() > 0 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                KeyguardSettingProvider$.ExternalSyntheticOutline0.m("onCapsuleInserted: ", strJoinToString$default2, "CapsuleDataDiffer-->", "SystemUi--Notification");
            }
            int size2 = arrayList4.size();
            int i7 = 0;
            while (i7 < size2) {
                int i8 = i7 + 1;
                try {
                    capsuleListContainer.addContainerView((CapsuleItemView) arrayList4.get(i7));
                    Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th2) {
                    Result.constructor-impl(ResultKt.createFailure(th2));
                }
                i7 = i8;
            }
            String strJoinToString$default3 = CollectionsKt___CollectionsKt.joinToString$default(arrayList, OplusPreventModeData.SEPARATOR, (CharSequence) null, (CharSequence) null, new Function1() { // from class: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataDiffer$$ExternalSyntheticLambda0
                public final Object invoke(Object obj3) {
                    switch (i6) {
                        case 0:
                            return ((CapsuleItemView) obj3).getKey();
                        case 1:
                            return ((CapsuleItemView) obj3).getKey();
                        case 2:
                            CapsuleDataDiffer.ChangeInfo changeInfo2 = (CapsuleDataDiffer.ChangeInfo) obj3;
                            return CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(changeInfo2.getOldView().getKey(), " to ", changeInfo2.getNewData().getKey());
                        default:
                            ((CapsuleDataDiffer.ChangeInfo) obj3).applyToView();
                            return Unit.INSTANCE;
                    }
                }
            }, 30);
            if (strJoinToString$default3.length() > 0 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                KeyguardSettingProvider$.ExternalSyntheticOutline0.m("onCapsuleRemoved: ", strJoinToString$default3, "CapsuleDataDiffer-->", "SystemUi--Notification");
            }
            int size3 = arrayList.size();
            int i9 = 0;
            while (i9 < size3) {
                Object obj3 = arrayList.get(i9);
                i9++;
                try {
                    capsuleListContainer.removeContainerView((CapsuleItemView) obj3);
                    Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th3) {
                    Result.constructor-impl(ResultKt.createFailure(th3));
                }
            }
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("dispatchUpdates oldData=", list.size(), list2.size(), ", newData =", ", constTime:[");
            sbM.append(jCurrentTimeMillis2);
            sbM.append("]ms");
            Log.d("SystemUi--Notification", "CapsuleDataDiffer-->".concat(sbM.toString()));
        }
    }
}
