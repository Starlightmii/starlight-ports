package com.oplus.systemui.qs.base.widget.strokeshader;

import android.graphics.Color;
import android.graphics.RuntimeShader;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GradientStrokeShader {
    public static final Companion Companion = new Companion();
    public static final String TAG = "GradientStrokeShader";
    public float cornerLB;
    public float cornerLT;
    public float cornerRB;
    public float cornerRT;
    public float drawHeight;
    public float drawWidth;
    public float offsetX;
    public float offsetY;
    public float strokeLineAlphaFar;
    public float strokeLineAlphaNear;
    public float strokeLineTransverseFarFade;
    public float strokeLineTransverseFarSolid;
    public float strokeLineTransverseNearFade;
    public float strokeLineTransverseNearSolid;
    public float strokeLineVerticalFarFade;
    public float strokeLineVerticalFarSolid;
    public float strokeLineVerticalNearFade;
    public float strokeLineVerticalNearSolid;
    public float viewHeight;
    public float viewWidth;
    public float weight;
    private CornerType cornerType = CornerType.G2;
    public float cornerRadius = 78.0f;
    public float cornerWeight = 1.0f;
    public int strokeLineColor = -1;
    public float ratio = 0.5f;
    public float strokeLinePow = 1.0f;
    public float strokeLineMix = 0.5f;
    public final RuntimeShader shader = new RuntimeShader("\n    uniform int u_conerType;\n    \n    uniform float u_commonArray[12];\n    \n    uniform float u_edge;\n    uniform float u_edgeArray[16];\n    \n    float getQuadrantValue(vec2 pos, vec4 values){\n        vec2 s = step(vec2(0.0), pos);\n        float valueTop = mix(values.x, values.y, s.x);\n        float valueBot = mix(values.w, values.z, s.x);\n        float value = mix(valueTop, valueBot, s.y);\n        return value;\n    }\n    \n    struct CommonDataRect{\n        vec2 halfResolution;\n        vec2 pos;\n        vec2 halfSize;\n        float coner;\n        float weight;\n        float preCalcMinC;\n    };\n    \n    float RBox(vec2 p, vec2 b, float r) {\n        vec2 q = abs(p) - b + r;\n        return min(max(q.x, q.y), 0.) + length(max(q, 0.)) - r;\n    }\n\n    float RBoxConer(vec2 p, vec2 b, float r) {\n        return RBox(p, b, r);\n    }\n    \n    //平滑圆角1.0（CONIC）\n    float cubicBezierDistance(float2 p, float2 p0, float2 p1, float2 p2, float2 p3) {\n        float2 c0 = p0;\n        float2 c1 = 3.0 * (p1 - p0);\n        float2 c2 = 3.0 * (p2 - 2.0 * p1 + p0);\n        float2 c3 = p3 - 3.0 * p2 + 3.0 * p1 - p0;\n        float t = 0.5;\n        for (int i = 0; i < 10; i++) {\n            float2 Bt   = ((c3 * t + c2) * t + c1) * t + c0;\n            float2 dBt  = (3.0 * c3 * t + 2.0 * c2) * t + c1;\n            float2 ddBt = 6.0 * c3 * t + 2.0 * c2;\n            float2 r    = Bt - p;\n            float f1 = dot(dBt, r);\n            float f2 = dot(ddBt, r) + dot(dBt, dBt);\n            float denom = max(f2, 1e-4);\n            t = clamp(t - f1 / denom, 0.0, 1.0);\n        }\n        float2 Bt = ((c3 * t + c2) * t + c1) * t + c0;\n        float2 re = Bt - p;\n        float2 tangent = (3.0 * c3 * t + 2.0 * c2) * t + c1;\n        float2 normal = float2(-tangent.y, tangent.x);\n        float2 normalizedNormal = normalize(normal);\n        float dis = length(re);\n        return dot(re, normalizedNormal) > 0.0 ? dis : -dis;\n    }\n    float sdBezierDistance(float2 p, float2 b, float corner, float weight) {\n        float sdf = 0.0;\n        float cc = corner;\n        if(any(lessThanEqual(abs(p), b - cc)) || cc <= 0.01) {\n            sdf = RBox(p, b, cc);\n        }else{\n            float point  = max(cc*weight*0.5,0.001);\n            sdf = cubicBezierDistance(abs(p)-b+cc,float2(cc, 0.0), float2(cc, point), float2(point, cc),float2(0.0, cc));\n        }\n        return sdf;\n    }\n    \n    //平滑圆角2.0算法\n    const float EPS = 1e-6;\n    const float DEFAULT_TRANSITION_PIXELS = 4.0;\n    const float kAbs = 1.0;\n    const float rRef = 80.0;\n    const float kMax = 26.6;\n    const float junctionXBand = 70.0;\n    const float junctionYBand = 60.0;\n    const float junctionPower = 1.0;\n    \n    float smoothAbs(float x, float k) {\n        return sqrt(x * x + k * k);\n    }\n    \n    float smoothMaxSqrt(float a, float b, float k) {\n        float d = a - b;\n        return 0.5 * (a + b + sqrt(d * d + k * k));\n    }\n    \n    float sdCapsule(vec2 pLocal, vec2 halfSize) {\n        bool isVertical = halfSize.x < halfSize.y;\n        vec2 hs = isVertical ? halfSize.yx : halfSize;\n        vec2 pl = isVertical ? pLocal.yx : pLocal;\n    \n        vec2 q = vec2(smoothAbs(pl.x, kAbs), smoothAbs(pl.y, kAbs));\n        float r = hs.y;\n        float a = max(hs.x - r, 0.0);\n    \n        float scale = r * 0.0125;\n        float kMaxEff = kMax * scale;\n        float xBandEff = junctionXBand * scale;\n        float yBandEff = junctionYBand * scale;\n    \n        float s = q.x - a;\n        float xHard = max(s, 0.0);\n        float xSoft = (kMaxEff > 0.0) ? smoothMaxSqrt(s, 0.0, kMaxEff) : xHard;\n        float wx = 1.0 - smoothstep(0.0, max(xBandEff, 1e-3), abs(s));\n        float wy = smoothstep(r - max(yBandEff, 1e-3), r, q.y);\n        float w = clamp(wx * wy, 0.0, 1.0);\n        float x = mix(xHard, xSoft, w);\n        return length(vec2(x, q.y)) - r;\n    }\n    \n    float powFast(float x, float n) {\n        x = max(x, 1e-8);\n        return exp2(log2(x) * n);\n    }\n    \n    float lpNorm(vec2 v, float n) {\n        v = abs(v);\n        float m = max(v.x, v.y);\n        if (m < 1e-8) return 0.0;\n        vec2 u = v / m;\n        float s = powFast(u.x, n) + powFast(u.y, n);\n        return m * exp2(log2(max(s, EPS)) / n);\n    }\n    \n    float lpNorm4(vec2 v) {\n        v = abs(v);\n        vec2 v2 = v * v;\n        vec2 v4 = v2 * v2;\n        return sqrt(sqrt(max(v4.x + v4.y, 0.0)));\n    }\n    \n    float sdRoundRect(vec2 p, vec2 halfSize, float r, float n) {\n        vec2 q = abs(p) - halfSize + vec2(r);\n        float nClamped = max(n, 2.0);\n        float dCorner = (abs(nClamped - 4.0) < 1e-3) ? lpNorm4(max(q, 0.0)) : lpNorm(max(q, 0.0), nClamped);\n        if (q.x > 0.0 && q.y > 0.0) return dCorner - r;\n        else return max(q.x, q.y) - r;\n    }\n    \n    \n    float smoothSDF_D(vec2 p, vec2 b, float corner, float weight){\n        float rectSdf = 0.0;\n        if (weight  < 2.0) {\n            float zoneCap = 0.0;\n            if(weight  > 1.005){\n                float t = smoothstep(2.0, 1.0, weight);\n                float d_G2 = sdRoundRect( p, b, corner, 2.0);\n                float d_Capsule = sdCapsule( p, b);\n                rectSdf = mix(d_G2, d_Capsule, t);\n            }\n            else\n            {\n                rectSdf = sdCapsule( p, b);\n            }\n        } else {\n            rectSdf = sdRoundRect( p, b, corner, weight);\n        }\n    \n        return rectSdf;\n    }\n    \n    //描边\n    float getDis(vec2 pos,vec2 halfsize){\n        vec2 rect=abs(pos-halfsize);\n        return rect.x+rect.y;\n    }\n    \n    vec4 blendEdge(CommonDataRect rectData, vec4 color, float d){\n        vec3 uRectLineColor = vec3(u_edgeArray[0], u_edgeArray[1], u_edgeArray[2]);\n        vec2 uRectLineNear = vec2(u_edgeArray[3], u_edgeArray[4]);\n        vec2 uRectLineFar = vec2(u_edgeArray[5], u_edgeArray[6]);\n        vec2 uRectLineAlpha = vec2(u_edgeArray[7], u_edgeArray[8]);\n        vec4 uRectLineFade = vec4(u_edgeArray[9], u_edgeArray[10], u_edgeArray[11], u_edgeArray[12]);\n        float uAngle = u_edgeArray[13];\n        float uRectLinePara = u_edgeArray[14];\n        vec2 rectLineNear=uRectLineNear;\n        rectLineNear.y+=uRectLineNear.x;\n        vec2 rectLineFar=uRectLineFar;\n        rectLineFar.y+=uRectLineFar.x;\n        vec4 rectLineFade=uRectLineFade;\n        rectLineFade.yw+=rectLineFade.xz;\n    \n        if(d<-max(rectLineNear.y, rectLineFar.y)){\n            return color;\n        }\n    \n        float distanceAll = (rectData.halfSize.x+rectData.halfSize.y)*2.0;\n        vec2 halfSize = rectData.halfSize;\n    \n        float ratio = mod(uAngle+rectData.halfSize.x/distanceAll*0.5+0.5,1.0);\n        vec2 dir_pos= normalize(rectData.pos);\n        vec2 dirAbs_pos = abs(dir_pos);\n        vec2 pos_n = min(vec2(dirAbs_pos.xy*halfSize.yx/max(vec2(.001),dirAbs_pos.yx)),halfSize)*sign(dir_pos);\n        vec2 halfSize_symobol = vec2(-halfSize.y,halfSize.x);\n        float flag = mix(-1.0,1.0, step(ratio,0.5))*dot(halfSize_symobol,pos_n);\n        float dis_point = distanceAll*min(ratio,1.0-ratio)*2.0;\n        float dis_pos = getDis(pos_n,halfSize);\n        float distance_near = mix(dis_point+dis_pos,abs(dis_point-dis_pos),step(0.,flag));\n        distance_near=min(2.*distanceAll-distance_near,distance_near);\n        float lineNearAlpha = uRectLineAlpha.x*smoothstep(rectLineFade.y*distanceAll, rectLineFade.x*distanceAll, distance_near);\n        float lineFarAlpha = uRectLineAlpha.y*smoothstep(rectLineFade.w*distanceAll, rectLineFade.z*distanceAll, distanceAll-distance_near);\n    \n        float lineNearSdf=smoothstep(-rectLineNear.y,-rectLineNear.x,d);\n        float lineFarSdf=smoothstep(-rectLineFar.y,-rectLineFar.x,d);\n    \n        float lineNearSdf_6 = lineNearSdf*lineNearSdf*lineNearSdf;\n        lineNearSdf_6 *= lineNearSdf_6;\n        lineNearSdf = mix(lineNearSdf, lineNearSdf_6, uRectLinePara);\n        float lineFarSdf_6 = lineFarSdf*lineFarSdf*lineFarSdf;\n        lineFarSdf_6 *= lineFarSdf_6;\n        lineFarSdf = mix(lineFarSdf, lineFarSdf_6, uRectLinePara);\n    \n        lineNearAlpha*=lineNearSdf;\n        lineFarAlpha*=lineFarSdf;\n    \n        float lineAlpha = clamp(lineNearAlpha+lineFarAlpha,0.,1.);\n    \n        vec4 rectLineColor = vec4(uRectLineColor, lineAlpha);\n        rectLineColor.rgb *= rectLineColor.a;\n    \n        vec4 reColor = vec4(0.0);\n        reColor.rgb = color.rgb*(1.0 - rectLineColor.a)+rectLineColor.rgb;\n        reColor.a = color.a*(1.0 - rectLineColor.a)+rectLineColor.a;\n        return reColor;\n    }\n    \n    vec4 main(vec2 fragCoord) {\n        float antiAliasing = u_commonArray[11];\n        CommonDataRect rectData;\n        rectData.halfResolution = vec2(u_commonArray[0], u_commonArray[1])*0.5;\n        rectData.pos = vec2(fragCoord.x, u_commonArray[1]-fragCoord.y) - vec2(u_commonArray[2], u_commonArray[3]) - rectData.halfResolution;\n        rectData.halfSize =  vec2(u_commonArray[4], u_commonArray[5])*0.5;\n        rectData.coner =  getQuadrantValue(rectData.pos, vec4(u_commonArray[6],u_commonArray[7],u_commonArray[8],u_commonArray[9]));\n        rectData.weight = u_commonArray[10];\n        rectData.preCalcMinC = min(rectData.halfSize.x, rectData.halfSize.y);\n    \n        float d = 0.0;\n        if(u_conerType == 0){\n            d = RBoxConer(rectData.pos, rectData.halfSize, min(rectData.coner, rectData.preCalcMinC));\n        } else if (u_conerType == 1) {\n            d = sdBezierDistance(rectData.pos, rectData.halfSize, min(rectData.coner, rectData.preCalcMinC), rectData.weight);\n        } else {\n            d = smoothSDF_D(rectData.pos, rectData.halfSize, min(rectData.coner, rectData.preCalcMinC), rectData.weight);\n        }\n    \n        float aa = 1.0;\n        float shape =  smoothstep(aa, -aa, d);\n        if(shape<0.001){\n            return vec4(0.0);\n        }\n    \n        vec4 color = vec4(0.0);\n    \n        if(u_edge>0.5){\n            color = blendEdge(rectData, color, d);\n        }\n    \n        return color * shape;\n    }\n");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.oplus.systemui.qs.base.widget.strokeshader.GradientStrokeShader$CornerType[], still in use, count: 1, list:
      (r0v1 com.oplus.systemui.qs.base.widget.strokeshader.GradientStrokeShader$CornerType[]) from 0x0022: INVOKE (r0v1 com.oplus.systemui.qs.base.widget.strokeshader.GradientStrokeShader$CornerType[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[WRAPPED]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CornerType {
        /* JADX INFO: Fake field, exist only in values array */
        FULL(0),
        CONIC(1),
        G2(2);

        public static final /* synthetic */ EnumEntries $ENTRIES;
        private final int shaderValue;

        static {
            $ENTRIES = EnumEntriesKt.enumEntries(cornerTypeArr);
        }

        public CornerType(int i) {
            super(str, i);
            this.shaderValue = i;
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static CornerType valueOf(String str) {
            return (CornerType) Enum.valueOf(CornerType.class, str);
        }

        public static CornerType[] values() {
            return (CornerType[]) $VALUES.clone();
        }

        public final int getShaderValue() {
            return this.shaderValue;
        }
    }

    public final void applyStrokeLineParams(GradientStrokeLineParams gradientStrokeLineParams) {
        int i;
        this.strokeLineColor = gradientStrokeLineParams.getStrokeLineColor().toArgb();
        this.ratio = gradientStrokeLineParams.getRatio();
        this.strokeLineAlphaNear = gradientStrokeLineParams.getStrokeLineAlphaNear();
        this.strokeLineAlphaFar = gradientStrokeLineParams.getStrokeLineAlphaFar();
        this.strokeLineVerticalNearSolid = gradientStrokeLineParams.getStrokeLineVerticalNearSolid();
        this.strokeLineVerticalNearFade = gradientStrokeLineParams.getStrokeLineVerticalNearFade();
        this.strokeLineVerticalFarSolid = gradientStrokeLineParams.getStrokeLineVerticalFarSolid();
        this.strokeLineVerticalFarFade = gradientStrokeLineParams.getStrokeLineVerticalFarFade();
        this.strokeLineTransverseNearSolid = gradientStrokeLineParams.getStrokeLineTransverseNearSolid();
        this.strokeLineTransverseNearFade = gradientStrokeLineParams.getStrokeLineTransverseNearFade();
        this.strokeLineTransverseFarSolid = gradientStrokeLineParams.getStrokeLineTransverseFarSolid();
        this.strokeLineTransverseFarFade = gradientStrokeLineParams.getStrokeLineTransverseFarFade();
        this.strokeLinePow = gradientStrokeLineParams.getStrokeLinePow();
        this.strokeLineMix = gradientStrokeLineParams.getStrokeLineMix();
        try {
            float fRed = Color.red(this.strokeLineColor) / 255.0f;
            float fGreen = Color.green(this.strokeLineColor) / 255.0f;
            float fBlue = Color.blue(this.strokeLineColor) / 255.0f;
            float f = this.strokeLineVerticalNearSolid;
            float f2 = this.strokeLineVerticalNearFade + f;
            float f3 = this.strokeLineVerticalFarSolid;
            float f4 = this.strokeLineVerticalFarFade + f3;
            float f5 = this.strokeLineAlphaNear;
            float f6 = this.strokeLineAlphaFar;
            float f7 = this.strokeLineTransverseNearSolid;
            float f8 = this.strokeLineTransverseNearFade + f7;
            float f9 = this.strokeLineTransverseFarSolid;
            i = 8;
            try {
                getShader().setFloatUniform("u_edgeArray", new float[]{fRed, fGreen, fBlue, f, f2, f3, f4, f5, f6, f7, f8, f9, this.strokeLineTransverseFarFade + f9, this.ratio, this.strokeLinePow, this.strokeLineMix});
                getShader().setFloatUniform("u_edge", 1.0f);
            } catch (IllegalArgumentException e) {
                e = e;
                LogUtil.w$default(i, LogUtil.TAG_QS, TAG, "updateEdgeUniform fail: " + e);
            }
        } catch (IllegalArgumentException e2) {
            e = e2;
            i = 8;
        }
    }

    public final float getCornerLB() {
        return this.cornerLB;
    }

    public final float getCornerLT() {
        return this.cornerLT;
    }

    public final float getCornerRB() {
        return this.cornerRB;
    }

    public final float getCornerRT() {
        return this.cornerRT;
    }

    public final CornerType getCornerType() {
        return this.cornerType;
    }

    public final float getDrawHeight() {
        return this.drawHeight;
    }

    public final float getDrawWidth() {
        return this.drawWidth;
    }

    public final float getOffsetX() {
        return this.offsetX;
    }

    public final float getOffsetY() {
        return this.offsetY;
    }

    public final float getRatio() {
        return this.ratio;
    }

    public final RuntimeShader getShader() {
        RuntimeShader runtimeShader = this.shader;
        if (runtimeShader != null) {
            return runtimeShader;
        }
        return null;
    }

    public final float getStrokeLineAlphaFar() {
        return this.strokeLineAlphaFar;
    }

    public final float getStrokeLineAlphaNear() {
        return this.strokeLineAlphaNear;
    }

    public final int getStrokeLineColor() {
        return this.strokeLineColor;
    }

    public final float getStrokeLineMix() {
        return this.strokeLineMix;
    }

    public final float getStrokeLinePow() {
        return this.strokeLinePow;
    }

    public final float getStrokeLineTransverseFarFade() {
        return this.strokeLineTransverseFarFade;
    }

    public final float getStrokeLineTransverseFarSolid() {
        return this.strokeLineTransverseFarSolid;
    }

    public final float getStrokeLineTransverseNearFade() {
        return this.strokeLineTransverseNearFade;
    }

    public final float getStrokeLineTransverseNearSolid() {
        return this.strokeLineTransverseNearSolid;
    }

    public final float getStrokeLineVerticalFarFade() {
        return this.strokeLineVerticalFarFade;
    }

    public final float getStrokeLineVerticalFarSolid() {
        return this.strokeLineVerticalFarSolid;
    }

    public final float getStrokeLineVerticalNearFade() {
        return this.strokeLineVerticalNearFade;
    }

    public final float getStrokeLineVerticalNearSolid() {
        return this.strokeLineVerticalNearSolid;
    }

    public final float getViewHeight() {
        return this.viewHeight;
    }

    public final float getViewWidth() {
        return this.viewWidth;
    }

    public final float getWeight() {
        return this.weight;
    }

    public final boolean setCorner(CornerType cornerType, float f, float f2) {
        CornerType cornerType2 = this.cornerType;
        if (cornerType2 == cornerType && this.cornerRadius == f && this.cornerWeight == f2) {
            return false;
        }
        boolean z = cornerType2 != cornerType;
        this.cornerType = cornerType;
        this.cornerRadius = f;
        this.cornerWeight = f2;
        if (z) {
            getShader().setIntUniform("u_conerType", this.cornerType.getShaderValue());
        }
        getShader().setIntUniform("u_conerType", this.cornerType.getShaderValue());
        return z;
    }
}
