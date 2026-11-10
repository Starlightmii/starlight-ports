package com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler;

import android.util.Log;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleDataOrderHandler extends CapsuleDataBaseHandler {
    private static final String TAG = "CapsuleDataOrderHandler";
    public static final CapsuleDataOrderHandler INSTANCE = new CapsuleDataOrderHandler();
    private static CapsuleNextModel nextModel = CapsuleNextModel.NATURAL;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleNextCardInfo {
        public static final Companion Companion = new Companion();
        private static final CapsuleNextCardInfo NONE = new CapsuleNextCardInfo(-1, null);
        private final CapsuleItemView nextCard;
        public final int nextCardIndex;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final CapsuleNextCardInfo getNONE() {
                return CapsuleNextCardInfo.NONE;
            }
        }

        public CapsuleNextCardInfo(int i, CapsuleItemView capsuleItemView) {
            this.nextCardIndex = i;
            this.nextCard = capsuleItemView;
        }

        public final CapsuleItemView component2() {
            return this.nextCard;
        }

        public final CapsuleNextCardInfo copy(int i, CapsuleItemView capsuleItemView) {
            return new CapsuleNextCardInfo(i, capsuleItemView);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CapsuleNextCardInfo)) {
                return false;
            }
            CapsuleNextCardInfo capsuleNextCardInfo = (CapsuleNextCardInfo) obj;
            return this.nextCardIndex == capsuleNextCardInfo.nextCardIndex && Intrinsics.areEqual(this.nextCard, capsuleNextCardInfo.nextCard);
        }

        public final CapsuleItemView getNextCard() {
            return this.nextCard;
        }

        public final int getNextCardIndex() {
            return this.nextCardIndex;
        }

        public final int hashCode() {
            int iHashCode = Integer.hashCode(this.nextCardIndex) * 31;
            ExpandableView expandableView = this.nextCard;
            return iHashCode + (expandableView == null ? 0 : expandableView.hashCode());
        }

        public String toString() {
            return "CapsuleNextCardInfo(nextCardIndex=" + this.nextCardIndex + ", nextCard=" + this.nextCard + ")";
        }

        public final boolean valid() {
            return (equals(NONE) || this.nextCardIndex < 0 || this.nextCard == null) ? false : true;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleNextModel {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ CapsuleNextModel[] $VALUES;
        public static final CapsuleNextModel NATURAL;
        public static final CapsuleNextModel PIN_BACK;
        public static final CapsuleNextModel REMIND;

        static {
            CapsuleNextModel capsuleNextModel = new CapsuleNextModel("NATURAL", 0);
            NATURAL = capsuleNextModel;
            CapsuleNextModel capsuleNextModel2 = new CapsuleNextModel("REMIND", 1);
            REMIND = capsuleNextModel2;
            CapsuleNextModel capsuleNextModel3 = new CapsuleNextModel("PIN_BACK", 2);
            PIN_BACK = capsuleNextModel3;
            CapsuleNextModel[] capsuleNextModelArr = {capsuleNextModel, capsuleNextModel2, capsuleNextModel3};
            $VALUES = capsuleNextModelArr;
            $ENTRIES = EnumEntriesKt.enumEntries(capsuleNextModelArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static CapsuleNextModel valueOf(String str) {
            return (CapsuleNextModel) Enum.valueOf(CapsuleNextModel.class, str);
        }

        public static CapsuleNextModel[] values() {
            return (CapsuleNextModel[]) $VALUES.clone();
        }
    }

    public final int getBaseCardIndex(CapsuleItemView capsuleItemView) {
        if (capsuleItemView == null) {
            return -1;
        }
        return getAlgorithmState().getVisibleChildren().indexOf(capsuleItemView);
    }

    public final CapsuleNextCardInfo getHeadupBackCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(CapsuleItemView capsuleItemView) {
        CapsuleNextCardInfo targetCardInfo = getTargetCardInfo(CapsuleViewExpandKtKt.highestPriorityCard(getCapsuleContainer()));
        return targetCardInfo == null ? CapsuleNextCardInfo.Companion.getNONE() : targetCardInfo;
    }

    public final CapsuleNextCardInfo getNextCardInfo(CapsuleItemView capsuleItemView) {
        CapsuleNextCardInfo none;
        if (capsuleItemView == null || CapsuleViewExpandKtKt.showOnlyOneCard(getCapsuleContainer())) {
            none = CapsuleNextCardInfo.Companion.getNONE();
        } else {
            int iOrdinal = nextModel.ordinal();
            if (iOrdinal != 0) {
                if (iOrdinal == 1) {
                    none = getRemindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic();
                } else {
                    if (iOrdinal != 2) {
                        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                        return null;
                    }
                    none = getHeadupBackCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(capsuleItemView);
                }
            } else if (getAmbientState().getScrollX() == 0) {
                none = CapsuleNextCardInfo.Companion.getNONE();
            } else {
                int size = getAlgorithmState().getVisibleChildren().size();
                List listSortedWith = CollectionsKt___CollectionsKt.sortedWith(getAlgorithmState().getVisibleChildren(), new CapsuleDataOrderHandler$getNaturalNextCardInfo$$inlined$sortedBy$1());
                int iIndexOf = listSortedWith.indexOf(capsuleItemView);
                int i = getAmbientState().getScrollX() < 0 ? (iIndexOf + 1) % size : ((iIndexOf + size) - 1) % size;
                none = new CapsuleNextCardInfo(getAlgorithmState().getVisibleChildren().indexOf(listSortedWith.get(i)), (CapsuleItemView) listSortedWith.get(i));
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleDataOrderHandler-->".concat("getNextCardInfo. nextModel:" + nextModel + ", baseCard:" + capsuleItemView + ", nextCarInfo:" + none));
        }
        return none;
    }

    public final CapsuleNextModel getNextModel() {
        return nextModel;
    }

    public final CapsuleNextCardInfo getRemindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
        CapsuleNextCardInfo targetCardInfo = getTargetCardInfo(CapsuleViewExpandKtKt.findRemindCard(getCapsuleContainer()));
        return targetCardInfo == null ? CapsuleNextCardInfo.Companion.getNONE() : targetCardInfo;
    }

    public final CapsuleNextCardInfo getTargetCardInfo(CapsuleItemView capsuleItemView) {
        Object obj;
        if (capsuleItemView == null) {
            return null;
        }
        try {
            CapsuleNextCardInfo capsuleNextCardInfo = new CapsuleNextCardInfo(getAlgorithmState().getVisibleChildren().indexOf(capsuleItemView), capsuleItemView);
            if (!capsuleNextCardInfo.valid()) {
                capsuleNextCardInfo = null;
            }
            obj = Result.constructor-impl(capsuleNextCardInfo);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        return (CapsuleNextCardInfo) (obj instanceof Result.Failure ? null : obj);
    }

    public final void setNextModel(CapsuleNextModel capsuleNextModel) {
        nextModel = capsuleNextModel;
    }
}
