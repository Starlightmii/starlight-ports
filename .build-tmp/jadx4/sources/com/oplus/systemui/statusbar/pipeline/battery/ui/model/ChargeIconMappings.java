package com.oplus.systemui.statusbar.pipeline.battery.ui.model;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.car.app.SurfaceContainer$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ChargeIconMappings {
    public static final Companion Companion = new Companion();
    private final HashMap<Integer, ChargeIconGroup> charIconGroupEntries;
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class ChargeIconGroup {
        private final Context context;
        private final HashMap<Integer, ChargeIconItem> entries = new HashMap<>();

        public ChargeIconGroup(Context context) {
            this.context = context;
        }

        public int[] getCharIconBgList() {
            return new int[0];
        }

        public int[] getCharIconList() {
            return new int[0];
        }

        public int[] getCharIconTypeList() {
            return this.context.getResources().getIntArray(2130903075);
        }

        public HashMap<Integer, ChargeIconItem> getEntries() {
            return this.entries;
        }

        public final int[] getIconList(int i) {
            if (i <= 0) {
                return new int[]{0};
            }
            TypedArray typedArrayObtainTypedArray = this.context.getResources().obtainTypedArray(i);
            int[] iArr = new int[typedArrayObtainTypedArray.length()];
            int length = typedArrayObtainTypedArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = typedArrayObtainTypedArray.getResourceId(i2, 0);
            }
            typedArrayObtainTypedArray.recycle();
            return iArr;
        }

        public final void initEntries() {
            int[] charIconTypeList = getCharIconTypeList();
            int[] charIconList = getCharIconList();
            int[] charIconBgList = getCharIconBgList();
            int length = charIconTypeList.length;
            int length2 = charIconList.length;
            if (length > length2) {
                length = length2;
            }
            int i = 0;
            while (i < length) {
                getEntries().put(Integer.valueOf(charIconTypeList[i]), new ChargeIconItem(charIconTypeList[i], charIconList[i], i < charIconBgList.length ? charIconBgList[i] : 0));
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ChargeIconItem {
        public int chargeIconBgId;
        public int chargeIconId;
        public int chargeType;

        public ChargeIconItem(int i, int i2, int i3) {
            this.chargeType = i;
            this.chargeIconId = i2;
            this.chargeIconBgId = i3;
        }

        public final ChargeIconItem copy(int i, int i2, int i3) {
            return new ChargeIconItem(i, i2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ChargeIconItem)) {
                return false;
            }
            ChargeIconItem chargeIconItem = (ChargeIconItem) obj;
            return this.chargeType == chargeIconItem.chargeType && this.chargeIconId == chargeIconItem.chargeIconId && this.chargeIconBgId == chargeIconItem.chargeIconBgId;
        }

        public final int getChargeIconBgId() {
            return this.chargeIconBgId;
        }

        public final int getChargeIconId() {
            return this.chargeIconId;
        }

        public final int getChargeType() {
            return this.chargeType;
        }

        public final int hashCode() {
            return Integer.hashCode(this.chargeIconBgId) + MonoSpline$.ExternalSyntheticOutline0.m(this.chargeIconId, Integer.hashCode(this.chargeType) * 31, 31);
        }

        public final void setChargeIconBgId(int i) {
            this.chargeIconBgId = i;
        }

        public final void setChargeIconId(int i) {
            this.chargeIconId = i;
        }

        public final void setChargeType(int i) {
            this.chargeType = i;
        }

        public String toString() {
            return SurfaceContainer$.ExternalSyntheticOutline0.m(this.chargeIconBgId, ")", FloatList$.ExternalSyntheticOutline0.m("ChargeIconItem(chargeType=", this.chargeType, this.chargeIconId, ", chargeIconId=", ", chargeIconBgId="));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CircleBatteryIcon extends ChargeIconGroup {
        private final Context context;

        public CircleBatteryIcon(Context context) {
            super(context);
            this.context = context;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.ChargeIconMappings.ChargeIconGroup
        public int[] getCharIconList() {
            return getIconList(2130903076);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class HorizontalBatteryIcon extends ChargeIconGroup {
        private final Context context;

        public HorizontalBatteryIcon(Context context) {
            super(context);
            this.context = context;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.ChargeIconMappings.ChargeIconGroup
        public int[] getCharIconBgList() {
            return getIconList(2130903078);
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.ChargeIconMappings.ChargeIconGroup
        public int[] getCharIconList() {
            return getIconList(2130903077);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class VerticalBatteryIcon extends ChargeIconGroup {
        private final Context context;

        public VerticalBatteryIcon(Context context) {
            super(context);
            this.context = context;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.ChargeIconMappings.ChargeIconGroup
        public int[] getCharIconList() {
            return getIconList(2130903081);
        }
    }

    public ChargeIconMappings(Context context) {
        this.context = context;
        HashMap<Integer, ChargeIconGroup> map = new HashMap<>();
        this.charIconGroupEntries = map;
        HorizontalBatteryIcon horizontalBatteryIcon = new HorizontalBatteryIcon(context);
        horizontalBatteryIcon.initEntries();
        map.put(1, horizontalBatteryIcon);
        VerticalBatteryIcon verticalBatteryIcon = new VerticalBatteryIcon(context);
        verticalBatteryIcon.initEntries();
        map.put(3, verticalBatteryIcon);
        CircleBatteryIcon circleBatteryIcon = new CircleBatteryIcon(context);
        circleBatteryIcon.initEntries();
        map.put(4, circleBatteryIcon);
    }

    public final ChargeIconItem getChargeIconItem(int i, boolean z, boolean z2) {
        int i2;
        HashMap<Integer, ChargeIconItem> entries;
        if (!z && !z2) {
            i2 = 0;
        } else if (z || !z2) {
            i2 = (!z || z2) ? 3 : 2;
        } else {
            i2 = 1;
        }
        ChargeIconGroup chargeIconGroup = this.charIconGroupEntries.get(Integer.valueOf(i));
        if (chargeIconGroup == null || (entries = chargeIconGroup.getEntries()) == null) {
            return null;
        }
        return entries.get(Integer.valueOf(i2));
    }
}
