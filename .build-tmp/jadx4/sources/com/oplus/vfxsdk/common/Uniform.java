package com.oplus.vfxsdk.common;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.compose.ui.spatial.RectList$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.oplus.transition.OplusAnimationController;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Uniform {
    private final Integer colorMode;
    private final Integer filterMode;
    private final boolean flip;
    private final int format;
    private final int height;
    private final int id;
    private final String mediaType;
    private final String name;
    private final UniformType type;
    private final Object value;
    private final float w;
    private final int width;
    private final Integer wrapMode;
    private final float x;
    private final float y;
    private final float z;

    public /* synthetic */ Uniform(int i, String str, UniformType uniformType, Object obj, float f, float f2, float f3, float f4, int i2, int i3, int i4, boolean z, Integer num, String str2, Integer num2, Integer num3, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, uniformType, obj, f, f2, f3, f4, (i5 & 256) != 0 ? 100 : i2, (i5 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? 100 : i3, (i5 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? 0 : i4, (i5 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? true : z, (i5 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? 10497 : num, (i5 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? "" : str2, (i5 & 16384) != 0 ? 9729 : num2, (i5 & 32768) != 0 ? 32856 : num3);
    }

    public final int component1() {
        return this.id;
    }

    public final int component10() {
        return this.height;
    }

    public final int component11() {
        return this.format;
    }

    public final boolean component12() {
        return this.flip;
    }

    public final Integer component13() {
        return this.wrapMode;
    }

    public final String component14() {
        return this.mediaType;
    }

    public final Integer component15() {
        return this.filterMode;
    }

    public final Integer component16() {
        return this.colorMode;
    }

    public final String component2() {
        return this.name;
    }

    public final UniformType component3() {
        return this.type;
    }

    public final Object component4() {
        return this.value;
    }

    public final float component5() {
        return this.x;
    }

    public final float component6() {
        return this.y;
    }

    public final float component7() {
        return this.z;
    }

    public final float component8() {
        return this.w;
    }

    public final int component9() {
        return this.width;
    }

    public final Uniform copy(int i, String str, UniformType uniformType, Object obj, float f, float f2, float f3, float f4, int i2, int i3, int i4, boolean z, Integer num, String str2, Integer num2, Integer num3) {
        return new Uniform(i, str, uniformType, obj, f, f2, f3, f4, i2, i3, i4, z, num, str2, num2, num3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Uniform)) {
            return false;
        }
        Uniform uniform = (Uniform) obj;
        return this.id == uniform.id && Intrinsics.areEqual(this.name, uniform.name) && this.type == uniform.type && Intrinsics.areEqual(this.value, uniform.value) && Float.compare(this.x, uniform.x) == 0 && Float.compare(this.y, uniform.y) == 0 && Float.compare(this.z, uniform.z) == 0 && Float.compare(this.w, uniform.w) == 0 && this.width == uniform.width && this.height == uniform.height && this.format == uniform.format && this.flip == uniform.flip && Intrinsics.areEqual(this.wrapMode, uniform.wrapMode) && Intrinsics.areEqual(this.mediaType, uniform.mediaType) && Intrinsics.areEqual(this.filterMode, uniform.filterMode) && Intrinsics.areEqual(this.colorMode, uniform.colorMode);
    }

    public final Integer getColorMode() {
        return this.colorMode;
    }

    public final Integer getFilterMode() {
        return this.filterMode;
    }

    public final boolean getFlip() {
        return this.flip;
    }

    public final int getFormat() {
        return this.format;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getId() {
        return this.id;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public final String getName() {
        return this.name;
    }

    public final UniformType getType() {
        return this.type;
    }

    public final Object getValue() {
        return this.value;
    }

    public final float getW() {
        return this.w;
    }

    public final int getWidth() {
        return this.width;
    }

    public final Integer getWrapMode() {
        return this.wrapMode;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final float getZ() {
        return this.z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [int] */
    /* JADX WARN: Type inference failed for: r2v14, types: [int] */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    public int hashCode() {
        int iHashCode = (this.type.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(Integer.hashCode(this.id) * 31, 31, this.name)) * 31;
        Object obj = this.value;
        int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.format, MonoSpline$.ExternalSyntheticOutline0.m(this.height, MonoSpline$.ExternalSyntheticOutline0.m(this.width, ChangeSize$.ExternalSyntheticOutline0.m(this.w, ChangeSize$.ExternalSyntheticOutline0.m(this.z, ChangeSize$.ExternalSyntheticOutline0.m(this.y, ChangeSize$.ExternalSyntheticOutline0.m(this.x, (iHashCode + (obj == null ? 0 : obj.hashCode())) * 31, 31), 31), 31), 31), 31), 31), 31);
        boolean z = this.flip;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iM + r2) * 31;
        Integer num = this.wrapMode;
        int iM2 = PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((i + (num == null ? 0 : num.hashCode())) * 31, 31, this.mediaType);
        Integer num2 = this.filterMode;
        int iHashCode2 = (iM2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.colorMode;
        return iHashCode2 + (num3 != null ? num3.hashCode() : 0);
    }

    public String toString() {
        int i = this.id;
        String str = this.name;
        UniformType uniformType = this.type;
        Object obj = this.value;
        float f = this.x;
        float f2 = this.y;
        float f3 = this.z;
        float f4 = this.w;
        int i2 = this.width;
        int i3 = this.height;
        int i4 = this.format;
        boolean z = this.flip;
        Integer num = this.wrapMode;
        String str2 = this.mediaType;
        Integer num2 = this.filterMode;
        Integer num3 = this.colorMode;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(i, "Uniform(id=", ", name=", str, ", type=");
        sbM.append(uniformType);
        sbM.append(", value=");
        sbM.append(obj);
        sbM.append(", x=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, ", y=", f2, ", z=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f3, ", w=", f4, ", width=");
        RectList$.ExternalSyntheticOutline0.m(i2, i3, ", height=", ", format=", sbM);
        ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i4, ", flip=", ", wrapMode=", sbM, z);
        sbM.append(num);
        sbM.append(", mediaType=");
        sbM.append(str2);
        sbM.append(", filterMode=");
        sbM.append(num2);
        sbM.append(", colorMode=");
        sbM.append(num3);
        sbM.append(")");
        return sbM.toString();
    }

    public Uniform(int i, String str, UniformType uniformType, Object obj, float f, float f2, float f3, float f4, int i2, int i3, int i4, boolean z, Integer num, String str2, Integer num2, Integer num3) {
        this.id = i;
        this.name = str;
        this.type = uniformType;
        this.value = obj;
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.w = f4;
        this.width = i2;
        this.height = i3;
        this.format = i4;
        this.flip = z;
        this.wrapMode = num;
        this.mediaType = str2;
        this.filterMode = num2;
        this.colorMode = num3;
    }
}
