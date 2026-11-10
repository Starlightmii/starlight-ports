package com.oplus.systemui.qs.media.multilight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.systemui.qs.base.res.drawable.TileTransitionDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaBackgroundDrawable extends TileTransitionDrawable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsMediaBackgroundDrawable";
    private final Drawable backgroundDrawable;
    private final Context context;
    public float cornerRadius;
    public float cornerWeight;
    private final Drawable foregroundDrawable;
    public final boolean isMiddleOrHighAnimLevel;
    private MultiLightDrawable mediaLightDrawable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQsMediaBackgroundDrawable(Context context, Drawable drawable, Drawable drawable2) {
        super(drawable, drawable2);
        this.context = context;
        this.backgroundDrawable = drawable;
        this.foregroundDrawable = drawable2;
        boolean zIsMiddleOrHighAnimLevel = ScrimUtil.isMiddleOrHighAnimLevel(context);
        this.isMiddleOrHighAnimLevel = zIsMiddleOrHighAnimLevel;
        this.cornerWeight = 3.0f;
        if (zIsMiddleOrHighAnimLevel) {
            MultiLightDrawable multiLightDrawable = new MultiLightDrawable(context, TAG);
            this.mediaLightDrawable = multiLightDrawable;
            multiLightDrawable.setCallback(this);
        }
    }

    public static void clearLight$default(OplusQsMediaBackgroundDrawable oplusQsMediaBackgroundDrawable) {
        MultiLightDrawable multiLightDrawable = oplusQsMediaBackgroundDrawable.mediaLightDrawable;
        if (multiLightDrawable != null) {
            multiLightDrawable.setOutlineLightParam(null);
            multiLightDrawable.setInnerShadowLightParam(null);
            multiLightDrawable.setSpreadLightParam(null);
        }
        oplusQsMediaBackgroundDrawable.invalidateSelf();
    }

    public final void applyShadowAndOutlineLight(int i, boolean z) {
        if (this.isMiddleOrHighAnimLevel) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
                Log.d("SystemUi--Media", MobileInputLogger$.ExternalSyntheticOutline0.m("applyShadowAndOutlineLight: color=", i, ", isNight=", "OplusQsMediaBackgroundDrawable-->", z));
            }
            MultiLightModel multiLightModel = new MultiLightModel(2, i, Integer.valueOf(i), PositionEnum.BOTTOM, 0, 0.0f, 0.0f, 464);
            MultiLightModel multiLightModel2 = new MultiLightModel(1, i, Integer.valueOf(i), PositionEnum.DEFAULT, 0, 0.0f, 0.0f, 464);
            MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
            if (multiLightDrawable != null) {
                multiLightDrawable.setInnerShadowLightParam(multiLightModel);
                multiLightDrawable.setOutlineLightParam(multiLightModel2);
                if (z) {
                    multiLightDrawable.setShadowParams(ShadowParams.Companion.getMEDIA_ONE_NIGHT());
                    multiLightDrawable.setOutlineParams(OutlineParams.Companion.getMEDIA_ONE_NIGHT());
                } else {
                    multiLightDrawable.setShadowParams(ShadowParams.Companion.getMEDIA_ONE_LIGHT());
                    multiLightDrawable.setOutlineParams(OutlineParams.Companion.getMEDIA_ONE_LIGHT());
                }
                multiLightDrawable.dump("OplusQsMediaBackgroundDrawable:applyShadowAndOutlineLight");
            }
            invalidateSelf();
        }
    }

    public final void applySpreadLight(float f, float f2) {
        if (this.isMiddleOrHighAnimLevel) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
                Log.d("SystemUi--Media", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("applySpreadLightParam: diffusionPosX=", f, "， diffusionPosY=", f2, "OplusQsMediaBackgroundDrawable-->"));
            }
            MultiLightModel multiLightModel = new MultiLightModel(3, -1, null, PositionEnum.SPECIFIED, 1, f, f2, 68);
            MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
            if (multiLightDrawable != null) {
                multiLightDrawable.setSpreadLightParam(null);
                multiLightDrawable.setSpreadLightParam(multiLightModel);
                multiLightDrawable.setCircleParams(CircleParams.Companion.getMEDIA_ONE());
                multiLightDrawable.dump("OplusQsMediaBackgroundDrawable:applySpreadLight");
            }
            invalidateSelf();
        }
    }

    @Override // com.oplus.systemui.qs.base.res.drawable.TileTransitionDrawable, android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
        if (multiLightDrawable != null) {
            multiLightDrawable.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
        if (multiLightDrawable != null) {
            multiLightDrawable.setBounds(rect);
        }
    }

    public final void setCornerParams(float f, Float f2) {
        float fFloatValue = f2 != null ? f2.floatValue() : 3.0f;
        LogUtil.i$default(LogUtil.TAG_MEDIA, TAG, "setCornerParams: radius=" + f + ", weight=" + fFloatValue, null, 8);
        boolean z = (this.cornerRadius == f && this.cornerWeight == fFloatValue) ? false : true;
        this.cornerRadius = f;
        this.cornerWeight = fFloatValue;
        if (z) {
            invalidateSelf();
        }
        MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
        if (multiLightDrawable != null) {
            multiLightDrawable.setCornerParams(null, Float.valueOf(f), f2);
        }
    }
}
