package com.oplus.systemui.qs.base.widget.strokeshader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.oplus.graphics.OplusPathAdapter;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Objects;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class StrokeShaderProxy implements IStrokeShader {
    public static final Companion Companion = new Companion();
    private static final String OLIVE_LINK_DEBUG_TAG = "光感描边";
    private static final String TAG = "StrokeShaderProxy";
    private final Context context;
    private Function0 offerDrawable;
    private GradientStrokeLineParams shaderLineParams;
    public boolean strokeEnable;
    private OplusPathAdapter strokeOPlusPath;
    private final OplusPathAdapter strokeOPlusPathSmooth;
    private final Paint strokePaint;
    private final Path strokePath;
    private GradientStrokeShader strokeShader;
    private GradientStrokeShader.CornerType cornerType = GradientStrokeShader.CornerType.G2;
    public float radius = 78.0f;
    public float weight = 1.0f;
    private Rect pathRect = new Rect();
    private RectF strokeRectF = new RectF();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StrokeShaderProxy(Context context, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate) {
        GradientStrokeLineParams strokeParams;
        this.context = context;
        Path path = new Path();
        this.strokePath = path;
        this.strokeOPlusPath = new OplusPathAdapter(path, 0);
        this.strokeOPlusPathSmooth = new OplusPathAdapter(path, 1);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        this.strokePaint = paint;
        this.shaderLineParams = (strokeParamsTemplate == null || (strokeParams = strokeParamsTemplate.getStrokeParams()) == null) ? null : StrokeShaderProxyKt.access$deepCopy(strokeParams);
    }

    public final void applyLineParamsToShader(GradientStrokeShader gradientStrokeShader) {
        GradientStrokeLineParams gradientStrokeLineParams = this.shaderLineParams;
        if (gradientStrokeLineParams != null) {
            gradientStrokeShader.applyStrokeLineParams(gradientStrokeLineParams);
            this.strokePaint.setStrokeWidth(Math.max(Math.max(gradientStrokeShader.strokeLineVerticalNearSolid, gradientStrokeShader.strokeLineVerticalNearFade), Math.max(gradientStrokeShader.strokeLineVerticalFarSolid, gradientStrokeShader.strokeLineVerticalFarFade)) * 2.0f);
        }
    }

    @Override // com.oplus.systemui.qs.base.widget.strokeshader.IStrokeShader
    public void drawShader(Canvas canvas, Function0 function0) {
        Drawable drawable;
        if (!this.strokeEnable) {
            if (function0 != null) {
                function0.invoke();
                return;
            }
            return;
        }
        int iSaveLayer = canvas.saveLayer(null, null);
        canvas.clipPath(this.strokePath);
        Function0 function1 = this.offerDrawable;
        if (function1 != null && (drawable = (Drawable) function1.invoke()) != null) {
            Rect rect = this.pathRect;
            drawable.setBounds(rect.left, rect.top, rect.right, rect.bottom);
            drawable.draw(canvas);
        }
        if (function0 != null) {
            function0.invoke();
        }
        canvas.restoreToCount(iSaveLayer);
        canvas.drawRect(this.strokeRectF, this.strokePaint);
    }

    @Override // com.oplus.systemui.qs.base.widget.strokeshader.IStrokeShader
    public GradientStrokeLineParams getStrokeLineParams() {
        return this.shaderLineParams;
    }

    public final GradientStrokeShader getStrokeShader() {
        return this.strokeShader;
    }

    public final void layoutShader(int i, int i2) {
        this.pathRect.set(0, 0, i, i2);
        updatePaddingRect(this.pathRect);
        this.strokeRectF.set(this.pathRect);
        this.strokePath.reset();
        int iOrdinal = this.cornerType.ordinal();
        if (iOrdinal == 1) {
            OplusPathAdapter oplusPathAdapter = this.strokeOPlusPath;
            RectF rectF = this.strokeRectF;
            float f = this.radius;
            oplusPathAdapter.addSmoothRoundRect(rectF, f, f, this.weight, Path.Direction.CW);
        } else if (iOrdinal != 2) {
            Path path = this.strokePath;
            RectF rectF2 = this.strokeRectF;
            float f2 = this.radius;
            path.addRoundRect(rectF2, f2, f2, Path.Direction.CW);
        } else {
            OplusPathAdapter oplusPathAdapter2 = this.strokeOPlusPathSmooth;
            RectF rectF3 = this.strokeRectF;
            float f3 = this.radius;
            oplusPathAdapter2.addSmoothRoundRect(rectF3, f3, f3, this.weight, Path.Direction.CW);
        }
        GradientStrokeShader gradientStrokeShader = this.strokeShader;
        if (gradientStrokeShader != null) {
            GradientStrokeLineParams gradientStrokeLineParams = this.shaderLineParams;
            if (gradientStrokeLineParams != null) {
                float fMax = Math.max(gradientStrokeLineParams.getStrokeLineVerticalNearSolid(), gradientStrokeLineParams.getStrokeLineVerticalNearFade());
                float fMax2 = Math.max(gradientStrokeLineParams.getStrokeLineVerticalFarSolid(), gradientStrokeLineParams.getStrokeLineVerticalFarFade());
                float strokeLineTransverseNearSolid = gradientStrokeLineParams.getStrokeLineTransverseNearSolid();
                float strokeLineTransverseNearFade = gradientStrokeLineParams.getStrokeLineTransverseNearFade();
                float strokeLineTransverseFarSolid = gradientStrokeLineParams.getStrokeLineTransverseFarSolid();
                float strokeLineTransverseFarFade = gradientStrokeLineParams.getStrokeLineTransverseFarFade();
                GradientStrokeLineAdapter.INSTANCE.updateRatioAndSides(gradientStrokeLineParams, i, i2, this.radius);
                gradientStrokeLineParams.setStrokeLineVerticalNearSolid(fMax);
                gradientStrokeLineParams.setStrokeLineVerticalNearFade(fMax);
                gradientStrokeLineParams.setStrokeLineVerticalFarSolid(fMax2);
                gradientStrokeLineParams.setStrokeLineVerticalFarFade(fMax2);
                gradientStrokeLineParams.setStrokeLineTransverseNearSolid(strokeLineTransverseNearSolid);
                gradientStrokeLineParams.setStrokeLineTransverseNearFade(strokeLineTransverseNearFade);
                gradientStrokeLineParams.setStrokeLineTransverseFarSolid(strokeLineTransverseFarSolid);
                gradientStrokeLineParams.setStrokeLineTransverseFarFade(strokeLineTransverseFarFade);
            }
            float f4 = i;
            float f5 = i2;
            float fWidth = this.pathRect.width();
            float fHeight = this.pathRect.height();
            Rect rect = this.pathRect;
            float f6 = rect.left;
            float f7 = rect.top;
            float f8 = this.radius;
            float f9 = this.weight;
            gradientStrokeShader.viewWidth = f4;
            gradientStrokeShader.viewHeight = f5;
            gradientStrokeShader.drawWidth = fWidth;
            gradientStrokeShader.drawHeight = fHeight;
            float f10 = ((fWidth / 2.0f) + f6) - (f4 / 2.0f);
            gradientStrokeShader.offsetX = f10;
            float f11 = ((f5 / 2.0f) - f7) - (fHeight / 2.0f);
            gradientStrokeShader.offsetY = f11;
            gradientStrokeShader.cornerLT = f8;
            gradientStrokeShader.cornerLB = f8;
            gradientStrokeShader.cornerRT = f8;
            gradientStrokeShader.cornerRB = f8;
            gradientStrokeShader.weight = f9;
            try {
                gradientStrokeShader.getShader().setFloatUniform("u_commonArray", new float[]{f4, f5, f10, f11, fWidth, fHeight, f8, f8, f8, f8, f9, 0.0f});
            } catch (IllegalArgumentException e) {
                LogUtil.w$default(8, LogUtil.TAG_QS, GradientStrokeShader.TAG, "updateCommonUniform fail: " + e);
            }
            applyLineParamsToShader(gradientStrokeShader);
            Objects.toString(this.strokeRectF);
        }
    }

    @Override // com.oplus.systemui.qs.base.widget.strokeshader.IStrokeShader
    public void setCorner(GradientStrokeShader.CornerType cornerType, float f, float f2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sb = new StringBuilder("setCorner: cornerType = ");
            sb.append(cornerType);
            sb.append(", radius = ");
            sb.append(f);
            sb.append(", weight = ");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sb, f2, "StrokeShaderProxy-->", "SystemUi--QuickSettings");
        }
        GradientStrokeShader.CornerType cornerType2 = this.cornerType;
        this.cornerType = cornerType;
        this.radius = f;
        this.weight = f2;
        GradientStrokeShader gradientStrokeShader = this.strokeShader;
        if (gradientStrokeShader != null) {
            gradientStrokeShader.setCorner(cornerType, f, f2);
        }
        if (cornerType2 != this.cornerType) {
            Paint paint = this.strokePaint;
            GradientStrokeShader gradientStrokeShader2 = this.strokeShader;
            paint.setShader(gradientStrokeShader2 != null ? gradientStrokeShader2.getShader() : null);
        }
    }

    @Override // com.oplus.systemui.qs.base.widget.strokeshader.IStrokeShader
    public void setStrokeBackground(Function0 function0) {
        this.offerDrawable = function0;
        Objects.toString(function0.invoke());
    }

    @Override // com.oplus.systemui.qs.base.widget.strokeshader.IStrokeShader
    public void setStrokeEnable(boolean z) {
        GradientStrokeLineParams strokeParams;
        GradientStrokeLineParams gradientStrokeLineParamsAccess$deepCopy = null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("setStrokeEnable: enable = ", ", strokeEnable = ", z, this.strokeEnable), null, 8);
        if (z != this.strokeEnable) {
            this.strokeEnable = z;
            if (!z) {
                this.offerDrawable = null;
                this.strokeShader = null;
                this.strokePaint.setShader(null);
                return;
            }
            GradientStrokeShader gradientStrokeShader = new GradientStrokeShader();
            if (this.shaderLineParams == null) {
                GradientStrokeLineAdapter.StrokeParamsTemplate mediaOutPutStrokeParamsTemplate = GradientStrokeLineAdapter.getMediaOutPutStrokeParamsTemplate(OplusDarkModeUtil.isNightMode(this.context), true);
                if (mediaOutPutStrokeParamsTemplate != null && (strokeParams = mediaOutPutStrokeParamsTemplate.getStrokeParams()) != null) {
                    gradientStrokeLineParamsAccess$deepCopy = StrokeShaderProxyKt.access$deepCopy(strokeParams);
                }
                this.shaderLineParams = gradientStrokeLineParamsAccess$deepCopy;
            }
            gradientStrokeShader.setCorner(gradientStrokeShader.getCornerType(), this.radius, gradientStrokeShader.getWeight());
            applyLineParamsToShader(gradientStrokeShader);
            this.strokePaint.setShader(gradientStrokeShader.getShader());
            this.strokeShader = gradientStrokeShader;
        }
    }

    @Override // com.oplus.systemui.qs.base.widget.strokeshader.IStrokeShader
    public /* bridge */ /* synthetic */ void updatePaddingRect(Rect rect) {
        super.updatePaddingRect(rect);
    }
}
