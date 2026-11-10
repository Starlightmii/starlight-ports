package com.oplus.vfxsdk.common;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.android.keyguard.logging.KeyguardLogger$;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimatorValue {
    private final AnimLine[] animLines;
    private final float currentTime;
    private final AnimLine eventLine;
    private final int id;
    private final String name;

    public /* synthetic */ AnimatorValue(int i, String str, float f, AnimLine[] animLineArr, AnimLine animLine, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, f, animLineArr, (i2 & 16) != 0 ? null : animLine);
    }

    public static /* synthetic */ AnimatorValue copy$default(AnimatorValue animatorValue, int i, String str, float f, AnimLine[] animLineArr, AnimLine animLine, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = animatorValue.id;
        }
        if ((i2 & 2) != 0) {
            str = animatorValue.name;
        }
        if ((i2 & 4) != 0) {
            f = animatorValue.currentTime;
        }
        if ((i2 & 8) != 0) {
            animLineArr = animatorValue.animLines;
        }
        if ((i2 & 16) != 0) {
            animLine = animatorValue.eventLine;
        }
        AnimLine animLine2 = animLine;
        float f2 = f;
        return animatorValue.copy(i, str, f2, animLineArr, animLine2);
    }

    public final int component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final float component3() {
        return this.currentTime;
    }

    public final AnimLine[] component4() {
        return this.animLines;
    }

    public final AnimLine component5() {
        return this.eventLine;
    }

    public final AnimatorValue copy(int i, String str, float f, AnimLine[] animLineArr, AnimLine animLine) {
        return new AnimatorValue(i, str, f, animLineArr, animLine);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnimatorValue)) {
            return false;
        }
        AnimatorValue animatorValue = (AnimatorValue) obj;
        return this.id == animatorValue.id && Intrinsics.areEqual(this.name, animatorValue.name) && Float.compare(this.currentTime, animatorValue.currentTime) == 0 && Intrinsics.areEqual(this.animLines, animatorValue.animLines) && Intrinsics.areEqual(this.eventLine, animatorValue.eventLine);
    }

    public final AnimLine[] getAnimLines() {
        return this.animLines;
    }

    public final float getCurrentTime() {
        return this.currentTime;
    }

    public final AnimLine getEventLine() {
        return this.eventLine;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        int iHashCode = (Arrays.hashCode(this.animLines) + ChangeSize$.ExternalSyntheticOutline0.m(this.currentTime, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(Integer.hashCode(this.id) * 31, 31, this.name), 31)) * 31;
        AnimLine animLine = this.eventLine;
        return iHashCode + (animLine == null ? 0 : animLine.hashCode());
    }

    public String toString() {
        int i = this.id;
        String str = this.name;
        float f = this.currentTime;
        String string = Arrays.toString(this.animLines);
        AnimLine animLine = this.eventLine;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(i, "AnimatorValue(id=", ", name=", str, ", currentTime=");
        sbM.append(f);
        sbM.append(", animLines=");
        sbM.append(string);
        sbM.append(", eventLine=");
        sbM.append(animLine);
        sbM.append(")");
        return sbM.toString();
    }

    public AnimatorValue(int i, String str, float f, AnimLine[] animLineArr, AnimLine animLine) {
        this.id = i;
        this.name = str;
        this.currentTime = f;
        this.animLines = animLineArr;
        this.eventLine = animLine;
    }
}
