package com.oplus.vfxsdk.common;

import android.hidl.base.V1_0.DebugInfo$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EventKey implements IKey {
    private final String action;
    private float cacheValue;
    private final String custom;
    private final String eventType;
    private final Integer[] linkIds;
    private final String name;
    private final float time;
    private final String type;
    private float value;

    public /* synthetic */ EventKey(float f, String str, float f2, float f3, String str2, String str3, String str4, Integer[] numArr, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, str, f2, f3, str2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : numArr, (i & 256) != 0 ? null : str5);
    }

    public static /* synthetic */ EventKey copy$default(EventKey eventKey, float f, String str, float f2, float f3, String str2, String str3, String str4, Integer[] numArr, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            f = eventKey.time;
        }
        if ((i & 2) != 0) {
            str = eventKey.type;
        }
        if ((i & 4) != 0) {
            f2 = eventKey.value;
        }
        if ((i & 8) != 0) {
            f3 = eventKey.cacheValue;
        }
        if ((i & 16) != 0) {
            str2 = eventKey.eventType;
        }
        if ((i & 32) != 0) {
            str3 = eventKey.action;
        }
        if ((i & 64) != 0) {
            str4 = eventKey.name;
        }
        if ((i & 128) != 0) {
            numArr = eventKey.linkIds;
        }
        if ((i & 256) != 0) {
            str5 = eventKey.custom;
        }
        Integer[] numArr2 = numArr;
        String str6 = str5;
        String str7 = str3;
        String str8 = str4;
        String str9 = str2;
        float f4 = f2;
        return eventKey.copy(f, str, f4, f3, str9, str7, str8, numArr2, str6);
    }

    public final float component1() {
        return this.time;
    }

    public final String component2() {
        return this.type;
    }

    public final float component3() {
        return this.value;
    }

    public final float component4() {
        return this.cacheValue;
    }

    public final String component5() {
        return this.eventType;
    }

    public final String component6() {
        return this.action;
    }

    public final String component7() {
        return this.name;
    }

    public final Integer[] component8() {
        return this.linkIds;
    }

    public final String component9() {
        return this.custom;
    }

    public final EventKey copy(float f, String str, float f2, float f3, String str2, String str3, String str4, Integer[] numArr, String str5) {
        return new EventKey(f, str, f2, f3, str2, str3, str4, numArr, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EventKey)) {
            return false;
        }
        EventKey eventKey = (EventKey) obj;
        return Float.compare(this.time, eventKey.time) == 0 && Intrinsics.areEqual(this.type, eventKey.type) && Float.compare(this.value, eventKey.value) == 0 && Float.compare(this.cacheValue, eventKey.cacheValue) == 0 && Intrinsics.areEqual(this.eventType, eventKey.eventType) && Intrinsics.areEqual(this.action, eventKey.action) && Intrinsics.areEqual(this.name, eventKey.name) && Intrinsics.areEqual(this.linkIds, eventKey.linkIds) && Intrinsics.areEqual(this.custom, eventKey.custom);
    }

    public final String getAction() {
        return this.action;
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public float getCacheValue() {
        return this.cacheValue;
    }

    public final String getCustom() {
        return this.custom;
    }

    public final String getEventType() {
        return this.eventType;
    }

    public final Integer[] getLinkIds() {
        return this.linkIds;
    }

    public final String getName() {
        return this.name;
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public float getTime() {
        return this.time;
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public String getType() {
        return this.type;
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public float getValue() {
        return this.value;
    }

    public int hashCode() {
        int iM = PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(this.cacheValue, ChangeSize$.ExternalSyntheticOutline0.m(this.value, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(Float.hashCode(this.time) * 31, 31, this.type), 31), 31), 31, this.eventType);
        String str = this.action;
        int iHashCode = (iM + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer[] numArr = this.linkIds;
        int iHashCode3 = (iHashCode2 + (numArr == null ? 0 : Arrays.hashCode(numArr))) * 31;
        String str3 = this.custom;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public void setCacheValue(float f) {
        this.cacheValue = f;
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public void setValue(float f) {
        this.value = f;
    }

    public String toString() {
        float f = this.time;
        String str = this.type;
        float f2 = this.value;
        float f3 = this.cacheValue;
        String str2 = this.eventType;
        String str3 = this.action;
        String str4 = this.name;
        String string = Arrays.toString(this.linkIds);
        String str5 = this.custom;
        StringBuilder sb = new StringBuilder("EventKey(time=");
        sb.append(f);
        sb.append(", type=");
        sb.append(str);
        sb.append(", value=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f2, ", cacheValue=", f3, ", eventType=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sb, str2, ", action=", str3, ", name=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sb, str4, ", linkIds=", string, ", custom=");
        return DebugInfo$.ExternalSyntheticOutline0.m(sb, str5, ")");
    }

    public EventKey(float f, String str, float f2, float f3, String str2, String str3, String str4, Integer[] numArr, String str5) {
        this.time = f;
        this.type = str;
        this.value = f2;
        this.cacheValue = f3;
        this.eventType = str2;
        this.action = str3;
        this.name = str4;
        this.linkIds = numArr;
        this.custom = str5;
    }
}
