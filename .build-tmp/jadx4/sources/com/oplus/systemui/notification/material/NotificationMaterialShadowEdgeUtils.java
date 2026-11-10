package com.oplus.systemui.notification.material;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.foundation.shape.DpCornerSize$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.coui.appcompat.COUIMaterialStateManager;
import com.coui.appcompat.shadowedge.COUIShadowEdgeDrawable;
import com.coui.appcompat.shadowedge.IShadowEdgeEffect;
import com.oplus.systemui.notification.blur.NotificationBlurMixColorParams;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMaterialShadowEdgeUtils {
    public static final NotificationMaterialShadowEdgeUtils INSTANCE = new NotificationMaterialShadowEdgeUtils();
    private static final String TAG = "NotificationMaterialShadowEdgeUtils";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ShadowEdgeCornerParams {
        public final float radius;
        public final int type;
        public final float weight;

        public ShadowEdgeCornerParams(float f, float f2, int i) {
            this.type = i;
            this.radius = f;
            this.weight = f2;
        }

        public final ShadowEdgeCornerParams copy(int i, float f, float f2) {
            return new ShadowEdgeCornerParams(f, f2, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ShadowEdgeCornerParams)) {
                return false;
            }
            ShadowEdgeCornerParams shadowEdgeCornerParams = (ShadowEdgeCornerParams) obj;
            return this.type == shadowEdgeCornerParams.type && Float.compare(this.radius, shadowEdgeCornerParams.radius) == 0 && Float.compare(this.weight, shadowEdgeCornerParams.weight) == 0;
        }

        public final float getRadius() {
            return this.radius;
        }

        public final int getType() {
            return this.type;
        }

        public final float getWeight() {
            return this.weight;
        }

        public final int hashCode() {
            return Float.hashCode(this.weight) + ChangeSize$.ExternalSyntheticOutline0.m(this.radius, Integer.hashCode(this.type) * 31, 31);
        }

        public String toString() {
            return DpCornerSize$.ExternalSyntheticOutline0.m(this.weight, ")", NormalizedTouchData$.ExternalSyntheticOutline0.m(this.radius, this.type, "ShadowEdgeCornerParams(type=", ", radius=", ", weight="));
        }
    }

    public static final boolean isShadowEdgeEnabled(Context context) {
        COUIMaterialStateManager.INSTANCE.getClass();
        if (!COUIMaterialStateManager.MATERIAL_FEATURE_ENABLE || !ScrimUtil.isMiddleOrHighAnimLevel(context)) {
            return false;
        }
        NotificationFeatureOption.INSTANCE.getClass();
        return !NotificationFeatureOption.isGaussBlurDisabled;
    }

    public static final void updateShadowEdgeDrawable(COUIShadowEdgeDrawable cOUIShadowEdgeDrawable, ShadowEdgeCornerParams shadowEdgeCornerParams, boolean z) {
        if (cOUIShadowEdgeDrawable == null) {
            return;
        }
        NotificationBlurMixColorParams.HeadsUpCardBlurParams headsUpCardBlurParams = NotificationBlurMixColorParams.HeadsUpCardBlurParams.INSTANCE;
        IShadowEdgeEffect.EdgeStyle edgeStyle = (IShadowEdgeEffect.EdgeStyle) CollectionsKt___CollectionsKt.getOrNull(IShadowEdgeEffect.EdgeStyle.getEntries(), headsUpCardBlurParams.getHeadsUpLineStyle().get().intValue());
        if (edgeStyle == null) {
            edgeStyle = IShadowEdgeEffect.EdgeStyle.EDGE_STYLE_2;
        }
        IShadowEdgeEffect.ShadowStyle shadowStyle = (IShadowEdgeEffect.ShadowStyle) CollectionsKt___CollectionsKt.getOrNull(IShadowEdgeEffect.ShadowStyle.getEntries(), headsUpCardBlurParams.getHeadsUpShadowStyle().get().intValue());
        if (shadowStyle == null) {
            shadowStyle = IShadowEdgeEffect.ShadowStyle.SHADOW_STYLE_2;
        }
        float fFloatValue = (z ? headsUpCardBlurParams.getHeadsUpLineAlphaDark() : headsUpCardBlurParams.getHeadsUpLineAlpha()).get().floatValue();
        float fFloatValue2 = headsUpCardBlurParams.getHeadsUpLineWidth().get().floatValue();
        float fFloatValue3 = headsUpCardBlurParams.getHeadsUpLineAngle().get().floatValue();
        BlendMode blendMode = (BlendMode) CollectionsKt___CollectionsKt.getOrNull(EntriesMappings.entries$0, headsUpCardBlurParams.getHeadsUpLineBlendMode().get().intValue());
        if (blendMode == null) {
            blendMode = BlendMode.SRC_OVER;
        }
        float fFloatValue4 = headsUpCardBlurParams.getHeadsUpFadeFadeIn().get().floatValue();
        float fFloatValue5 = headsUpCardBlurParams.getHeadsUpFadeOut().get().floatValue();
        float fFloatValue6 = headsUpCardBlurParams.getHeadsUpFadeWidthScale().get().floatValue();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationMaterialShadowEdgeUtils-->".concat("updateShadowEdgeDrawable: cornerParams=" + shadowEdgeCornerParams + ", isDarkMode=" + z));
        }
        cOUIShadowEdgeDrawable.setCornerType(shadowEdgeCornerParams.getType());
        cOUIShadowEdgeDrawable.setCornerRadius(shadowEdgeCornerParams.getRadius());
        cOUIShadowEdgeDrawable.setIsDarkMode(false);
        cOUIShadowEdgeDrawable.setEdgeEnable(true);
        cOUIShadowEdgeDrawable.setShadowEnable(true);
        cOUIShadowEdgeDrawable.setEdgeStyle(edgeStyle);
        cOUIShadowEdgeDrawable.setShadowStyle(shadowStyle);
        cOUIShadowEdgeDrawable.setLineAlpha(fFloatValue);
        cOUIShadowEdgeDrawable.setLineWidth(fFloatValue2);
        cOUIShadowEdgeDrawable.setLineAngle(fFloatValue3);
        cOUIShadowEdgeDrawable.setFadeAlpha(fFloatValue4, fFloatValue5);
        cOUIShadowEdgeDrawable.setFadeWidthScale(fFloatValue6);
        cOUIShadowEdgeDrawable.setWeight(shadowEdgeCornerParams.getWeight());
        cOUIShadowEdgeDrawable.setPaintBlendMode(blendMode);
    }

    public static final void updateShadowEdgeDrawableParam(COUIShadowEdgeDrawable cOUIShadowEdgeDrawable, boolean z) {
        if (cOUIShadowEdgeDrawable == null) {
            return;
        }
        NotificationBlurMixColorParams.HeadsUpCardBlurParams headsUpCardBlurParams = NotificationBlurMixColorParams.HeadsUpCardBlurParams.INSTANCE;
        float fFloatValue = (z ? headsUpCardBlurParams.getHeadsUpLineAlphaDark() : headsUpCardBlurParams.getHeadsUpLineAlpha()).get().floatValue();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationMaterialShadowEdgeUtils-->".concat("updateShadowEdgeDrawableParam: lineAlpha=" + fFloatValue + ", isDarkMode=" + z));
        }
        cOUIShadowEdgeDrawable.setLineAlpha(fFloatValue);
    }

    public final void bindDrawable$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(View view, COUIShadowEdgeDrawable cOUIShadowEdgeDrawable) {
        if (cOUIShadowEdgeDrawable != null) {
            cOUIShadowEdgeDrawable.setCallback(view);
        }
    }

    public final void drawShadowEdge$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(Canvas canvas, COUIShadowEdgeDrawable cOUIShadowEdgeDrawable, int i, int i2, int i3, int i4) {
        if (cOUIShadowEdgeDrawable == null) {
            return;
        }
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (i5 <= 0 || i6 <= 0) {
            return;
        }
        float f = i;
        float f2 = i2;
        int iSave = canvas.save();
        canvas.translate(f, f2);
        try {
            cOUIShadowEdgeDrawable.setBounds(0, 0, i5, i6);
            cOUIShadowEdgeDrawable.draw(canvas);
        } finally {
            canvas.restoreToCount(iSave);
        }
    }

    public final void unbindDrawable$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(COUIShadowEdgeDrawable cOUIShadowEdgeDrawable) {
        if (cOUIShadowEdgeDrawable != null) {
            cOUIShadowEdgeDrawable.setCallback(null);
        }
    }

    public final void drawShadowEdge$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(Canvas canvas, COUIShadowEdgeDrawable cOUIShadowEdgeDrawable, Rect rect) {
        drawShadowEdge$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(canvas, cOUIShadowEdgeDrawable, rect.left, rect.top, rect.right, rect.bottom);
    }
}
