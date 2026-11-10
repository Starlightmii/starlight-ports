package com.oplus.systemui.qs.media.multilight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.animation.AnimCornerRadiusDrawable;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaItemBackgroundDrawable extends Drawable implements AnimCornerRadiusDrawable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusMediaItemBackgroundDrawable";
    private final Context context;
    public float cornerRadius;
    public float cornerWeight;
    public final boolean isMiddleOrHighAnimLevel;
    private MultiLightDrawable mediaLightDrawable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusMediaItemBackgroundDrawable(Context context) {
        this.context = context;
        boolean zIsMiddleOrHighAnimLevel = ScrimUtil.isMiddleOrHighAnimLevel(context);
        this.isMiddleOrHighAnimLevel = zIsMiddleOrHighAnimLevel;
        this.cornerWeight = 3.0f;
        if (zIsMiddleOrHighAnimLevel) {
            this.mediaLightDrawable = new MultiLightDrawable(context, TAG);
        }
    }

    public final void applyDefaultShadowAndOutlineLight(boolean z) {
        if (this.isMiddleOrHighAnimLevel) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("applyDefaultShadowAndOutlineLight: isNightModel=", "OplusMediaItemBackgroundDrawable-->", "SystemUi--Media", z);
            }
            MultiLightModel multiLightModel = new MultiLightModel(2, -1, -1, PositionEnum.BOTTOM, 0, 0.0f, 0.0f, 464);
            MultiLightModel multiLightModel2 = new MultiLightModel(1, -1, -1, PositionEnum.DEFAULT, 0, 0.0f, 0.0f, 464);
            MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
            if (multiLightDrawable != null) {
                multiLightDrawable.setInnerShadowLightParam(multiLightModel);
                multiLightDrawable.setOutlineLightParam(multiLightModel2);
                if (z) {
                    multiLightDrawable.setShadowParams(ShadowParams.Companion.getMEDIA_TWO_DEFAULT_NIGHT());
                    multiLightDrawable.setOutlineParams(OutlineParams.Companion.getMEDIA_TWO_DEFAULT_NIGHT());
                } else {
                    multiLightDrawable.setShadowParams(ShadowParams.Companion.getMEDIA_TWO_DEFAULT_LIGHT());
                    multiLightDrawable.setOutlineParams(OutlineParams.Companion.getMEDIA_TWO_DEFAULT_LIGHT());
                }
                multiLightDrawable.dump("OplusMediaItemBackgroundDrawable:applyDefaultShadowAndOutlineLight");
            }
            invalidateSelf();
        }
    }

    public final void applyShadowAndOutlineLight(int i) {
        if (this.isMiddleOrHighAnimLevel) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "applyShadowAndOutlineLight: color=", "OplusMediaItemBackgroundDrawable-->", "SystemUi--Media");
            }
            MultiLightModel multiLightModel = new MultiLightModel(2, i, Integer.valueOf(i), PositionEnum.BOTTOM, 0, 0.0f, 0.0f, 464);
            MultiLightModel multiLightModel2 = new MultiLightModel(1, i, Integer.valueOf(i), PositionEnum.DEFAULT, 0, 0.0f, 0.0f, 464);
            MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
            if (multiLightDrawable != null) {
                multiLightDrawable.setInnerShadowLightParam(multiLightModel);
                multiLightDrawable.setShadowParams(ShadowParams.Companion.getMEDIA_TWO());
                multiLightDrawable.setOutlineLightParam(multiLightModel2);
                multiLightDrawable.setOutlineParams(OutlineParams.Companion.getMEDIA_TWO());
                multiLightDrawable.dump("OplusMediaItemBackgroundDrawable:applyShadowAndOutlineLight");
            }
            invalidateSelf();
        }
    }

    public final void clearLight() {
        MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
        if (multiLightDrawable != null) {
            multiLightDrawable.applyLight(CollectionsKt__CollectionsKt.emptyList());
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
        if (multiLightDrawable != null) {
            multiLightDrawable.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
        if (multiLightDrawable != null) {
            multiLightDrawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
        if (multiLightDrawable != null) {
            multiLightDrawable.setAlpha(i);
        }
        invalidateSelf();
    }

    public void setAnimCornerRadius(float f) {
        setCornerParams(f, this.cornerWeight);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
        if (multiLightDrawable != null) {
            multiLightDrawable.setColorFilter(colorFilter);
        }
        invalidateSelf();
    }

    public final void setCornerParams(float f, float f2) {
        LogUtil.i$default(LogUtil.TAG_MEDIA, TAG, "setCornerParams: radius=" + f + ", weight=" + f2, null, 8);
        boolean z = (this.cornerRadius == f && this.cornerWeight == f2) ? false : true;
        this.cornerRadius = f;
        this.cornerWeight = f2;
        if (z) {
            invalidateSelf();
        }
        MultiLightDrawable multiLightDrawable = this.mediaLightDrawable;
        if (multiLightDrawable != null) {
            multiLightDrawable.setCornerParams(2, Float.valueOf(f), Float.valueOf(f2));
        }
    }
}
