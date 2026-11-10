package com.oplus.systemui.qs.media.multilight;

import android.hidl.base.V1_0.DebugInfo$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiLightModel {
    public static final Companion Companion = new Companion();
    private final String bindBtn;
    public final int color;
    private final PositionEnum pos;
    public final float posX;
    public final float posY;
    public final int repeat;
    public final boolean started;
    private final Integer subColor;
    public final int type;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ MultiLightModel(int i, int i2, Integer num, PositionEnum positionEnum, int i3, float f, float f2, int i4) {
        this(i, i2, (i4 & 4) != 0 ? null : num, positionEnum, (i4 & 16) == 0, i3, "", (i4 & 128) != 0 ? 0.0f : f, (i4 & 256) != 0 ? 0.0f : f2);
    }

    public final Integer component3() {
        return this.subColor;
    }

    public final PositionEnum component4() {
        return this.pos;
    }

    public final String component7() {
        return this.bindBtn;
    }

    public final MultiLightModel copy(int i, int i2, Integer num, PositionEnum positionEnum, boolean z, int i3, String str, float f, float f2) {
        return new MultiLightModel(i, i2, num, positionEnum, z, i3, str, f, f2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MultiLightModel)) {
            return false;
        }
        MultiLightModel multiLightModel = (MultiLightModel) obj;
        return this.type == multiLightModel.type && this.color == multiLightModel.color && Intrinsics.areEqual(this.subColor, multiLightModel.subColor) && this.pos == multiLightModel.pos && this.started == multiLightModel.started && this.repeat == multiLightModel.repeat && Intrinsics.areEqual(this.bindBtn, multiLightModel.bindBtn) && Float.compare(this.posX, multiLightModel.posX) == 0 && Float.compare(this.posY, multiLightModel.posY) == 0;
    }

    public final String getBindBtn() {
        return this.bindBtn;
    }

    public final int getColor() {
        return this.color;
    }

    public final PositionEnum getPos() {
        return this.pos;
    }

    public final float getPosX() {
        return this.posX;
    }

    public final float getPosY() {
        return this.posY;
    }

    public final int getRepeat() {
        return this.repeat;
    }

    public final boolean getStarted() {
        return this.started;
    }

    public final Integer getSubColor() {
        return this.subColor;
    }

    public final int getType() {
        return this.type;
    }

    public final int hashCode() {
        int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.color, Integer.hashCode(this.type) * 31, 31);
        Integer num = this.subColor;
        return Float.hashCode(this.posY) + ChangeSize$.ExternalSyntheticOutline0.m(this.posX, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.repeat, ChangeSize$.ExternalSyntheticOutline0.m((this.pos.hashCode() + ((iM + (num == null ? 0 : num.hashCode())) * 31)) * 31, 31, this.started), 31), 31, this.bindBtn), 31);
    }

    public String toString() {
        String str = String.format("%08X", Arrays.copyOf(new Object[]{Integer.valueOf(this.color)}, 1));
        String str2 = String.format("%08X", Arrays.copyOf(new Object[]{this.subColor}, 1));
        PositionEnum positionEnum = this.pos;
        String str3 = this.bindBtn;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(this.type, "MultiLightModel(type=", ", color=#", str, ", subColor=#");
        sbM.append(str2);
        sbM.append(", pos=");
        sbM.append(positionEnum);
        sbM.append(", started=");
        PromptIconState$.ExternalSyntheticOutline0.m(this.repeat, ", repeat=", ", bindBtn='", sbM, this.started);
        return DebugInfo$.ExternalSyntheticOutline0.m(sbM, str3, "')");
    }

    public MultiLightModel(int i, int i2, Integer num, PositionEnum positionEnum, boolean z, int i3, String str, float f, float f2) {
        this.type = i;
        this.color = i2;
        this.subColor = num;
        this.pos = positionEnum;
        this.started = z;
        this.repeat = i3;
        this.bindBtn = str;
        this.posX = f;
        this.posY = f2;
    }
}
