package com.oplus.vfxsdk.common;

import androidx.car.app.SurfaceContainer$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.transition.OplusAnimationController;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Layer {
    private HashMap<String, AnimatorValue> animParams;
    private int blendDfactor;
    private int blendDfactorAlpha;
    private int blendSfactor;
    private int blendSfactorAlpha;
    private boolean enable;
    private boolean enableBlend;
    private final String id;
    private final String name;
    private int order;
    private HashMap<String, PassParams[]> params;
    private PostProcessorData[] postProcessor;
    private RendPass[] render;
    private TransformData transform;
    private final String type;

    public /* synthetic */ Layer(String str, String str2, String str3, TransformData transformData, PostProcessorData[] postProcessorDataArr, RendPass[] rendPassArr, HashMap map, HashMap map2, boolean z, boolean z2, int i, int i2, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, transformData, postProcessorDataArr, rendPassArr, map, map2, z, (i6 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? true : z2, (i6 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0 ? 770 : i, (i6 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) != 0 ? 771 : i2, (i6 & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0 ? 770 : i3, (i6 & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0 ? 771 : i4, (i6 & 16384) != 0 ? 0 : i5);
    }

    public final String component1() {
        return this.id;
    }

    public final boolean component10() {
        return this.enable;
    }

    public final int component11() {
        return this.blendSfactor;
    }

    public final int component12() {
        return this.blendDfactor;
    }

    public final int component13() {
        return this.blendSfactorAlpha;
    }

    public final int component14() {
        return this.blendDfactorAlpha;
    }

    public final int component15() {
        return this.order;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.type;
    }

    public final TransformData component4() {
        return this.transform;
    }

    public final PostProcessorData[] component5() {
        return this.postProcessor;
    }

    public final RendPass[] component6() {
        return this.render;
    }

    public final HashMap<String, PassParams[]> component7() {
        return this.params;
    }

    public final HashMap<String, AnimatorValue> component8() {
        return this.animParams;
    }

    public final boolean component9() {
        return this.enableBlend;
    }

    public final Layer copy(String str, String str2, String str3, TransformData transformData, PostProcessorData[] postProcessorDataArr, RendPass[] rendPassArr, HashMap<String, PassParams[]> map, HashMap<String, AnimatorValue> map2, boolean z, boolean z2, int i, int i2, int i3, int i4, int i5) {
        return new Layer(str, str2, str3, transformData, postProcessorDataArr, rendPassArr, map, map2, z, z2, i, i2, i3, i4, i5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Layer)) {
            return false;
        }
        Layer layer = (Layer) obj;
        return Intrinsics.areEqual(this.id, layer.id) && Intrinsics.areEqual(this.name, layer.name) && Intrinsics.areEqual(this.type, layer.type) && Intrinsics.areEqual(this.transform, layer.transform) && Intrinsics.areEqual(this.postProcessor, layer.postProcessor) && Intrinsics.areEqual(this.render, layer.render) && Intrinsics.areEqual(this.params, layer.params) && Intrinsics.areEqual(this.animParams, layer.animParams) && this.enableBlend == layer.enableBlend && this.enable == layer.enable && this.blendSfactor == layer.blendSfactor && this.blendDfactor == layer.blendDfactor && this.blendSfactorAlpha == layer.blendSfactorAlpha && this.blendDfactorAlpha == layer.blendDfactorAlpha && this.order == layer.order;
    }

    public final HashMap<String, AnimatorValue> getAnimParams() {
        return this.animParams;
    }

    public final int getBlendDfactor() {
        return this.blendDfactor;
    }

    public final int getBlendDfactorAlpha() {
        return this.blendDfactorAlpha;
    }

    public final int getBlendSfactor() {
        return this.blendSfactor;
    }

    public final int getBlendSfactorAlpha() {
        return this.blendSfactorAlpha;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final boolean getEnableBlend() {
        return this.enableBlend;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final int getOrder() {
        return this.order;
    }

    public final HashMap<String, PassParams[]> getParams() {
        return this.params;
    }

    public final PostProcessorData[] getPostProcessor() {
        return this.postProcessor;
    }

    public final RendPass[] getRender() {
        return this.render;
    }

    public final TransformData getTransform() {
        return this.transform;
    }

    public final String getType() {
        return this.type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [int] */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r2v16, types: [int] */
    /* JADX WARN: Type inference failed for: r2v18, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    public int hashCode() {
        int iM = PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.id.hashCode() * 31, 31, this.name), 31, this.type);
        TransformData transformData = this.transform;
        int iHashCode = (iM + (transformData == null ? 0 : transformData.hashCode())) * 31;
        PostProcessorData[] postProcessorDataArr = this.postProcessor;
        int iHashCode2 = (Arrays.hashCode(this.render) + ((iHashCode + (postProcessorDataArr == null ? 0 : Arrays.hashCode(postProcessorDataArr))) * 31)) * 31;
        HashMap<String, PassParams[]> map = this.params;
        int iHashCode3 = (iHashCode2 + (map == null ? 0 : map.hashCode())) * 31;
        HashMap<String, AnimatorValue> map2 = this.animParams;
        int iHashCode4 = (iHashCode3 + (map2 != null ? map2.hashCode() : 0)) * 31;
        boolean z = this.enableBlend;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = (iHashCode4 + r0) * 31;
        boolean z2 = this.enable;
        return Integer.hashCode(this.order) + MonoSpline$.ExternalSyntheticOutline0.m(this.blendDfactorAlpha, MonoSpline$.ExternalSyntheticOutline0.m(this.blendSfactorAlpha, MonoSpline$.ExternalSyntheticOutline0.m(this.blendDfactor, MonoSpline$.ExternalSyntheticOutline0.m(this.blendSfactor, (i + (z2 ? 1 : z2)) * 31, 31), 31), 31), 31);
    }

    public final void setAnimParams(HashMap<String, AnimatorValue> map) {
        this.animParams = map;
    }

    public final void setBlendDfactor(int i) {
        this.blendDfactor = i;
    }

    public final void setBlendDfactorAlpha(int i) {
        this.blendDfactorAlpha = i;
    }

    public final void setBlendSfactor(int i) {
        this.blendSfactor = i;
    }

    public final void setBlendSfactorAlpha(int i) {
        this.blendSfactorAlpha = i;
    }

    public final void setEnable(boolean z) {
        this.enable = z;
    }

    public final void setEnableBlend(boolean z) {
        this.enableBlend = z;
    }

    public final void setOrder(int i) {
        this.order = i;
    }

    public final void setParams(HashMap<String, PassParams[]> map) {
        this.params = map;
    }

    public final void setPostProcessor(PostProcessorData[] postProcessorDataArr) {
        this.postProcessor = postProcessorDataArr;
    }

    public final void setRender(RendPass[] rendPassArr) {
        this.render = rendPassArr;
    }

    public final void setTransform(TransformData transformData) {
        this.transform = transformData;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.type;
        TransformData transformData = this.transform;
        String string = Arrays.toString(this.postProcessor);
        String string2 = Arrays.toString(this.render);
        HashMap<String, PassParams[]> map = this.params;
        HashMap<String, AnimatorValue> map2 = this.animParams;
        boolean z = this.enableBlend;
        boolean z2 = this.enable;
        int i = this.blendSfactor;
        int i2 = this.blendDfactor;
        int i3 = this.blendSfactorAlpha;
        int i4 = this.blendDfactorAlpha;
        int i5 = this.order;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("Layer(id=", str, ", name=", str2, ", type=");
        sbM.append(str3);
        sbM.append(", transform=");
        sbM.append(transformData);
        sbM.append(", postProcessor=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, string, ", render=", string2, ", params=");
        sbM.append(map);
        sbM.append(", animParams=");
        sbM.append(map2);
        sbM.append(", enableBlend=");
        KeyguardLogger$.ExternalSyntheticOutline0.m(", enable=", ", blendSfactor=", sbM, z, z2);
        RectList$.ExternalSyntheticOutline0.m(i, i2, ", blendDfactor=", ", blendSfactorAlpha=", sbM);
        RectList$.ExternalSyntheticOutline0.m(i3, i4, ", blendDfactorAlpha=", ", order=", sbM);
        return SurfaceContainer$.ExternalSyntheticOutline0.m(i5, ")", sbM);
    }

    public Layer(String str, String str2, String str3, TransformData transformData, PostProcessorData[] postProcessorDataArr, RendPass[] rendPassArr, HashMap<String, PassParams[]> map, HashMap<String, AnimatorValue> map2, boolean z, boolean z2, int i, int i2, int i3, int i4, int i5) {
        this.id = str;
        this.name = str2;
        this.type = str3;
        this.transform = transformData;
        this.postProcessor = postProcessorDataArr;
        this.render = rendPassArr;
        this.params = map;
        this.animParams = map2;
        this.enableBlend = z;
        this.enable = z2;
        this.blendSfactor = i;
        this.blendDfactor = i2;
        this.blendSfactorAlpha = i3;
        this.blendDfactorAlpha = i4;
        this.order = i5;
    }
}
