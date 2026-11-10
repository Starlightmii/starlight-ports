package com.oplus.systemui.qs.media.multilight;

import android.graphics.RuntimeShader;
import android.os.SystemClock;
import androidx.car.app.serialization.Bundler$;
import com.android.compose.gesture.NestedDraggableNode$detectDrags$2$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiLightShaderParams implements BaseShaderInterface {
    private static final String AGSL_CODE = "\n// MultiLight Shader - SDF Multi-Effect Overlay\n// AGSL Version (converted from WebGL2 GLSL ES 3.00)\n\nuniform int u_cornerType;\nuniform float u_commonArray[12];\n\nuniform float u_shadow;\nuniform float u_shadowArray[16];\n\nuniform float u_edge;\nuniform float u_edgeArray[20];\n\nuniform float u_circle;\nuniform float u_circleArray[14];\nuniform float u_time;\n\nfloat getQuadrantValue(float2 pos, float4 values) {\n    float2 s = step(float2(0.0), pos);\n    float valueTop = mix(values.x, values.y, s.x);\n    float valueBot = mix(values.w, values.z, s.x);\n    float value = mix(valueTop, valueBot, s.y);\n    return value;\n}\n\nstruct CommonDataRect {\n    float2 halfResolution;\n    float2 pos;\n    float2 halfSize;\n    float coner;\n    float weight;\n    float preCalcMinC;\n};\n\nfloat RBoxConer(float2 p, float2 b, float r) {\n    float2 q = abs(p) - b + r;\n    return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - r;\n}\n\nconst float EPS = 1e-6;\nconst float kAbs = 1.0;\nconst float rRef = 80.0;\nconst float kMax = 26.6;\nconst float junctionXBand = 70.0;\nconst float junctionYBand = 60.0;\nconst float junctionPower = 1.0;\n\nfloat smoothAbsVal(float x, float k) {\n    return sqrt(x * x + k * k);\n}\n\nfloat smoothMaxSqrt(float a, float b, float k) {\n    float d = a - b;\n    return 0.5 * (a + b + sqrt(d * d + k * k));\n}\n\nfloat sdCapsule(float2 pLocal, float2 halfSize) {\n    bool isVertical = halfSize.x < halfSize.y;\n    float2 hs = isVertical ? halfSize.yx : halfSize;\n    float2 pl = isVertical ? pLocal.yx : pLocal;\n\n    float2 q = float2(smoothAbsVal(pl.x, kAbs), smoothAbsVal(pl.y, kAbs));\n    float r = hs.y;\n    float a = max(hs.x - r, 0.0);\n    float sc = r / max(rRef, 1e-3);\n    float kMaxEff = kMax * sc;\n    float xBandEff = junctionXBand * sc;\n    float yBandEff = junctionYBand * sc;\n    float s = q.x - a;\n    float xHard = max(s, 0.0);\n    float xSoft = (kMaxEff > 0.0) ? smoothMaxSqrt(s, 0.0, kMaxEff) : xHard;\n    float wx = 1.0 - smoothstep(0.0, max(xBandEff, 1e-3), abs(s));\n    float wy = smoothstep(r - max(yBandEff, 1e-3), r, q.y);\n    float w = pow(clamp(wx * wy, 0.0, 1.0), junctionPower);\n    float x = mix(xHard, xSoft, w);\n    return length(float2(x, q.y)) - r;\n}\n\nfloat powFast(float x, float n) {\n    x = max(x, 1e-8);\n    return exp2(log2(x) * n);\n}\n\nfloat lpNorm(float2 v, float n) {\n    v = abs(v);\n    float m = max(v.x, v.y);\n    if (m < 1e-8) return 0.0;\n    float2 u = v / m;\n    float s = powFast(u.x, n) + powFast(u.y, n);\n    return m * exp2(log2(max(s, EPS)) / n);\n}\n\nfloat lpNorm4(float2 v) {\n    v = abs(v);\n    float2 v2 = v * v;\n    float2 v4 = v2 * v2;\n    return sqrt(sqrt(max(v4.x + v4.y, 0.0)));\n}\n\nfloat4 normalizeRadiiIndependent(float2 halfSize, float4 r) {\n    float W = 2.0 * halfSize.x;\n    float H = 2.0 * halfSize.y;\n\n    float kTop    = min(1.0, W / max(r.x + r.y, EPS));\n    float kBottom = min(1.0, W / max(r.w + r.z, EPS));\n    float kLeft   = min(1.0, H / max(r.x + r.w, EPS));\n    float kRight  = min(1.0, H / max(r.y + r.z, EPS));\n\n    float4 k;\n    k.x = min(kTop, kLeft);\n    k.y = min(kTop, kRight);\n    k.z = min(kBottom, kRight);\n    k.w = min(kBottom, kLeft);\n\n    return r * k;\n}\n\nfloat sdRoundRect(float2 p, float2 halfSize, float4 r4, float n) {\n    float2 s = step(float2(0.0), p);\n\n    float rTop = mix(r4.x, r4.y, s.x);\n    float rBot = mix(r4.w, r4.z, s.x);\n    float r = mix(rTop, rBot, s.y);\n\n    float2 q = abs(p) - halfSize + float2(r);\n\n    float nClamped = max(n, 2.0);\n    float dCorner = (abs(nClamped - 4.0) < 1e-3)\n        ? lpNorm4(max(q, 0.0))\n        : lpNorm(max(q, 0.0), nClamped);\n\n    if (q.x > 0.0 && q.y > 0.0) {\n        return dCorner - r;\n    } else {\n        return max(q.x, q.y) - r;\n    }\n}\n\nfloat4 getNormalizedRadius(float2 halfSize, float4 radiusVec) {\n    float rMax = min(halfSize.x, halfSize.y);\n    float4 rSafe = clamp(radiusVec, float4(0.0), float4(rMax));\n    return normalizeRadiiIndependent(halfSize, rSafe);\n}\n\nfloat smoothSDF_D(float2 p, float2 b, float corner, float weight) {\n    float4 cornerRadius_S1 = float4(corner);\n    float rectSdf = 0.0;\n    if (weight < 2.0) {\n        if (weight > 1.005) {\n            float4 r4 = getNormalizedRadius(b, cornerRadius_S1);\n            float t = smoothstep(2.0, 1.0, weight);\n            float d_G2 = sdRoundRect(p, b, r4, 2.0);\n            float d_Capsule = sdCapsule(p, b);\n            rectSdf = mix(d_G2, d_Capsule, t);\n        } else {\n            rectSdf = sdCapsule(p, b);\n        }\n    } else {\n        float4 r4 = getNormalizedRadius(b, cornerRadius_S1);\n        rectSdf = sdRoundRect(p, b, r4, weight);\n    }\n    return rectSdf;\n}\n\nfloat noise(float2 p) {\n    return fract(sin(dot(p.yx, float2(79.1214, 78.233))) * 43760.5453);\n}\n\nfloat softLight(float A, float B) {\n    float l = B - (1.0 - 2.0 * A) * B * (1.0 - B);\n    float h = B + (2.0 * A - 1.0) * (sqrt(B) - B);\n    return mix(l, h, step(0.5, A));\n}\n\nfloat4 blendShadowColor(CommonDataRect rectData, float4 color, float d, float2 fragCoord) {\n    float2 uFadeAlpha = float2(u_shadowArray[0], u_shadowArray[1]);\n    float3 uFadeInColor = float3(u_shadowArray[2], u_shadowArray[3], u_shadowArray[4]);\n    float uFadeIn = u_shadowArray[5];\n    float2 uFadeOffset = float2(u_shadowArray[6], u_shadowArray[7]);\n    float2 uFadeScale = float2(u_shadowArray[8], u_shadowArray[9]);\n    float uFadeInPara = u_shadowArray[10];\n    float3 uFadeOutColor = float3(u_shadowArray[11], u_shadowArray[12], u_shadowArray[13]);\n    float uFadeOut = u_shadowArray[14];\n    float uFadeOutPara = u_shadowArray[15];\n\n    float2 fadeConer = min(float2(rectData.preCalcMinC), float2(uFadeIn, uFadeOut) * 1.2);\n    fadeConer.y = max(fadeConer.y, rectData.coner);\n\n    float fadeInAlpha = 0.0;\n    float fadeOutAlpha = 0.0;\n\n    if (uFadeAlpha.x > 0.001) {\n        float2 pos_new = rectData.pos - uFadeOffset;\n        fadeConer.x = max(fadeConer.x, getQuadrantValue(pos_new,\n            float4(u_commonArray[6], u_commonArray[7], u_commonArray[8], u_commonArray[9])));\n        float fadeLine = uFadeIn * -1.0;\n        float fadeSdf = fadeLine;\n        float2 halfSizeFade = rectData.halfSize * uFadeScale;\n\n        if ((abs(pos_new.x) > (halfSizeFade.x - fadeConer.x))\n            || (abs(pos_new.y) > (halfSizeFade.y - fadeConer.x))\n            || (u_cornerType != 1 && rectData.weight < 2.0))\n        {\n            if (u_cornerType == 1) {\n                fadeSdf = RBoxConer(pos_new, halfSizeFade, fadeConer.x);\n            } else {\n                fadeSdf = smoothSDF_D(pos_new, halfSizeFade, fadeConer.x, rectData.weight);\n            }\n            float alpha = smoothstep(fadeLine, 0.0, fadeSdf);\n            float alpha_6 = alpha * alpha * alpha;\n            alpha_6 *= alpha_6;\n            alpha = mix(alpha, alpha_6, uFadeInPara);\n            alpha = alpha * uFadeAlpha.x;\n            fadeInAlpha = alpha;\n        }\n    }\n\n    if (uFadeAlpha.y > 0.001) {\n        float fadeLine = uFadeOut * -1.0;\n        float fadeSdf = fadeLine;\n\n        if ((abs(rectData.pos.x) > (rectData.halfSize.x - fadeConer.y))\n            || (abs(rectData.pos.y) > (rectData.halfSize.y - fadeConer.y))\n            || (u_cornerType != 1 && rectData.weight < 2.0))\n        {\n            if (abs(fadeConer.y - rectData.coner) < 0.001) {\n                fadeSdf = d;\n            } else {\n                if (u_cornerType == 1) {\n                    fadeSdf = RBoxConer(rectData.pos, rectData.halfSize, fadeConer.y);\n                } else {\n                    fadeSdf = smoothSDF_D(rectData.pos, rectData.halfSize, fadeConer.y, rectData.weight);\n                }\n            }\n            float alpha = smoothstep(fadeLine, 0.0, fadeSdf);\n            float alpha_6 = alpha * alpha * alpha;\n            alpha_6 *= alpha_6;\n            alpha = mix(alpha, alpha_6, uFadeOutPara);\n            alpha *= uFadeAlpha.y;\n            fadeOutAlpha = alpha;\n        }\n    }\n\n    float2 uv_pos = fragCoord / 1080.0;\n    float n = clamp((noise(uv_pos) - 0.5) * 0.01 + 0.5, 0.0, 1.0);\n    fadeInAlpha = clamp(softLight(n, fadeInAlpha), 0.0, 1.0);\n    fadeOutAlpha = clamp(softLight(n, fadeOutAlpha), 0.0, 1.0);\n\n    float4 colorIn = float4(uFadeInColor, fadeInAlpha);\n    colorIn.rgb *= colorIn.a;\n    float4 colorOut = float4(uFadeOutColor, fadeOutAlpha);\n    colorOut.rgb *= colorOut.a;\n\n    float4 fadeColor = float4(0.0);\n    fadeColor.rgb = colorIn.rgb * (1.0 - colorOut.a) + colorOut.rgb;\n    fadeColor.a = colorIn.a * (1.0 - colorOut.a) + colorOut.a;\n\n    float4 reColor = float4(0.0);\n    reColor.rgb = color.rgb * (1.0 - fadeColor.a) + fadeColor.rgb;\n    reColor.a = color.a * (1.0 - fadeColor.a) + fadeColor.a;\n    return reColor;\n}\n\nfloat getDis(float2 pos, float2 halfsize) {\n    float2 rect = abs(pos - halfsize);\n    return rect.x + rect.y;\n}\n\nfloat4 blendEdge(CommonDataRect rectData, float4 color, float d) {\n    float3 uRectLineColorStart = float3(u_edgeArray[0], u_edgeArray[1], u_edgeArray[2]);\n    float3 uRectLineColorEnd = float3(u_edgeArray[3], u_edgeArray[4], u_edgeArray[5]);\n    float2 uRectLineNear = float2(u_edgeArray[6], u_edgeArray[7]);\n    float2 uRectLineFar = float2(u_edgeArray[8], u_edgeArray[9]);\n    float2 uRectLineAlpha = float2(u_edgeArray[10], u_edgeArray[11]);\n    float4 uRectLineFade = float4(u_edgeArray[12], u_edgeArray[13], u_edgeArray[14], u_edgeArray[15]);\n    float uAngle = u_edgeArray[16];\n    float uRectLinePara = u_edgeArray[17];\n    float uRectLineColorPara = u_edgeArray[18];\n\n    float2 rectLineNear = uRectLineNear;\n    rectLineNear.y += uRectLineNear.x;\n    float2 rectLineFar = uRectLineFar;\n    rectLineFar.y += uRectLineFar.x;\n    float4 rectLineFade = uRectLineFade;\n    rectLineFade.yw += rectLineFade.xz;\n\n    if (d < -max(rectLineNear.y, rectLineFar.y)) {\n        return color;\n    }\n\n    float distanceAll = (rectData.halfSize.x + rectData.halfSize.y) * 2.0;\n    float2 halfSize = rectData.halfSize;\n\n    float ratio = mod(uAngle + rectData.halfSize.x / distanceAll * 0.5 + 0.5, 1.0);\n    float2 dir_pos = normalize(rectData.pos);\n    float2 dirAbs_pos = abs(dir_pos);\n    float2 pos_n = min(float2(dirAbs_pos.xy * halfSize.yx / max(float2(0.001), dirAbs_pos.yx)), halfSize) * sign(dir_pos);\n    float2 halfSize_symobol = float2(-halfSize.y, halfSize.x);\n    float flag = mix(-1.0, 1.0, step(ratio, 0.5)) * dot(halfSize_symobol, pos_n);\n    float dis_point = distanceAll * min(ratio, 1.0 - ratio) * 2.0;\n    float dis_pos = getDis(pos_n, halfSize);\n    float distance_near = mix(dis_point + dis_pos, abs(dis_point - dis_pos), step(0.0, flag));\n    distance_near = min(2.0 * distanceAll - distance_near, distance_near);\n    float lineNearAlpha = smoothstep(rectLineFade.y * distanceAll, rectLineFade.x * distanceAll, distance_near);\n    float lineFarAlpha = smoothstep(rectLineFade.w * distanceAll, rectLineFade.z * distanceAll, distanceAll - distance_near);\n\n    float lineNearSdf = smoothstep(-rectLineNear.y, -rectLineNear.x, d);\n    float lineFarSdf = smoothstep(-rectLineFar.y, -rectLineFar.x, d);\n\n    float lineNearSdf_6 = lineNearSdf * lineNearSdf * lineNearSdf;\n    lineNearSdf_6 *= lineNearSdf_6;\n    lineNearSdf = mix(lineNearSdf, lineNearSdf_6, uRectLinePara);\n    float lineFarSdf_6 = lineFarSdf * lineFarSdf * lineFarSdf;\n    lineFarSdf_6 *= lineFarSdf_6;\n    lineFarSdf = mix(lineFarSdf, lineFarSdf_6, uRectLinePara);\n\n    float colorNearMix = smoothstep(rectLineFade.y * distanceAll, 0.0, distance_near);\n    float colorNearMix_6 = colorNearMix * colorNearMix * colorNearMix;\n    colorNearMix_6 *= colorNearMix_6;\n    colorNearMix = mix(colorNearMix, colorNearMix_6, uRectLineColorPara);\n\n    float colorFarMix = smoothstep(rectLineFade.w * distanceAll, 0.0, distanceAll - distance_near);\n    float colorFarMix_6 = colorNearMix * colorNearMix * colorNearMix;\n    colorFarMix_6 *= colorFarMix_6;\n    colorFarMix = mix(colorFarMix, colorFarMix_6, uRectLineColorPara);\n\n    float4 colorNear = float4(mix(uRectLineColorEnd, uRectLineColorStart, colorNearMix), lineNearAlpha * uRectLineAlpha.x * lineNearSdf);\n    float4 colorFar = float4(mix(uRectLineColorEnd, uRectLineColorStart, colorFarMix), lineFarAlpha * uRectLineAlpha.y * lineFarSdf);\n\n    colorNear.rgb *= colorNear.a;\n    colorFar.rgb *= colorFar.a;\n\n    float4 rectLineColor;\n    rectLineColor.rgb = colorFar.rgb * (1.0 - colorNear.a) + colorNear.rgb;\n    rectLineColor.a = colorFar.a * (1.0 - colorNear.a) + colorNear.a;\n\n    float4 reColor = float4(0.0);\n    reColor.rgb = color.rgb * (1.0 - rectLineColor.a) + rectLineColor.rgb;\n    reColor.a = color.a * (1.0 - rectLineColor.a) + rectLineColor.a;\n    return reColor;\n}\n\n// Ring-band mask: rises from (factor.x - factor.z) to factor.x, plateau over\n// factor.y, then falls over factor.w. factor scaled by (u_resolution.x*0.000926)\n// where u_resolution.x is supplied via u_commonArray[0].\nfloat getCircleMask(float2 pos, float4 factor, float alpha, float2 scl) {\n    float2 circlePos1 = pos * scl;\n    float circleLen = length(circlePos1);\n    float ratio = u_commonArray[0] * 0.000926;\n    float4 u_CircleRadFactor = factor * ratio;\n    float circleMask = (smoothstep(u_CircleRadFactor.x - u_CircleRadFactor.z, u_CircleRadFactor.x, circleLen)\n        - smoothstep(u_CircleRadFactor.x + u_CircleRadFactor.y,\n                     u_CircleRadFactor.x + u_CircleRadFactor.y + u_CircleRadFactor.w,\n                     circleLen)) * alpha;\n    return circleMask;\n}\n\nfloat4 blendCircle(CommonDataRect rectData, float4 color) {\n    float2 uCirclePos = float2(u_circleArray[0], u_circleArray[1]); // reference pixels (same space as rectData.pos)\n    float uCircleSpeed = u_circleArray[2];\n    float uCircleFrequence = u_circleArray[3];\n    float uCircleAlpha = u_circleArray[4];\n    float3 uCircleColor = float3(u_circleArray[5], u_circleArray[6], u_circleArray[7]);\n    float2 uCircleWidth2 = float2(u_circleArray[8], u_circleArray[9]); // dist width range\n    float4 uCircleMaskDis = float4(u_circleArray[10], u_circleArray[11], u_circleArray[12], u_circleArray[13]); // ring mask params\n\n    float distFromCenter = length(rectData.pos - uCirclePos);\n\n    float circleMask = getCircleMask(rectData.pos - uCirclePos, uCircleMaskDis, 1.0, float2(1.0));\n    if (circleMask < 0.001)\n        return color;\n\n    float t = clamp(distFromCenter / uCircleMaskDis.x, 0.0, 1.0);\n    float widthByDist = mix(uCircleWidth2.x, uCircleWidth2.y, t);\n\n    // animation distance (same \"pixel space\" unit as distFromCenter)\n    float animDis = distFromCenter - u_time * uCircleSpeed * 1000.0;\n    float dis = sin(animDis * 6.2831285 / uCircleFrequence) + widthByDist;\n    dis = clamp(dis, 0.0, 1.0);\n    dis *= dis;\n    dis *= uCircleAlpha;\n\n    // apply mask falloff\n    dis *= circleMask;\n    if (dis < 0.001)\n        return color;\n\n    float4 maskColor = float4(uCircleColor, 1.0) * dis;\n    float4 retColor;\n    retColor.rgb = color.rgb * (1.0 - maskColor.a) + maskColor.rgb;\n    retColor.a = color.a * (1.0 - maskColor.a) + maskColor.a;\n    return retColor;\n}\n\nhalf4 main(float2 fragCoord) {\n    CommonDataRect rectData;\n    rectData.halfResolution = float2(u_commonArray[0], u_commonArray[1]) * 0.5;\n    rectData.pos = fragCoord - float2(u_commonArray[2], u_commonArray[3]) - rectData.halfResolution;\n    rectData.halfSize = float2(u_commonArray[4], u_commonArray[5]) * 0.5;\n    rectData.coner = getQuadrantValue(rectData.pos,\n        float4(u_commonArray[6], u_commonArray[7], u_commonArray[8], u_commonArray[9]));\n    rectData.weight = u_commonArray[10];\n    rectData.preCalcMinC = min(rectData.halfSize.x, rectData.halfSize.y);\n\n    float d = 0.0;\n    if (u_cornerType == 1) {\n        d = RBoxConer(rectData.pos, rectData.halfSize, rectData.coner);\n    } else {\n        d = smoothSDF_D(rectData.pos, rectData.halfSize, rectData.coner, rectData.weight);\n    }\n\n    float shape = smoothstep(1.0, -1.0, d);\n\n    if (shape < 0.001) {\n        return half4(0.0);\n    }\n\n    float4 color = float4(0.0);\n\n    if (u_shadow > 0.5) {\n        color = blendShadowColor(rectData, color, d, fragCoord);\n    }\n    if (u_circle > 0.5) {\n        color = blendCircle(rectData, color);\n    }\n    if (u_edge > 0.5) {\n        color = blendEdge(rectData, color, d);\n    }\n\n    return half4(color * shape);\n}\n";
    public static final Companion Companion = new Companion();
    private static final String TAG = "MultiLightShaderParams";
    public int circleRepeatCount;
    public boolean dirtyCircleArray;
    public float initialUTimeOffsetSec;
    public boolean maskZAnimDone;
    public long startTimeMs;
    public float uCircle;
    private final float[] uCircleArray;
    private final float[] uCircleArrayDefault;
    public float uEdge;
    private final float[] uEdgeArray;
    private final float[] uEdgeArrayDefault;
    public float uShadow;
    private final float[] uShadowArray;
    private final float[] uShadowArrayDefault;
    private final RuntimeShader runtimeShader = new RuntimeShader(AGSL_CODE);
    public int uCornerType = 2;
    public boolean dirtyConerType = true;
    private final float[] uCommonArray = {1080.0f, 2400.0f, 0.0f, 0.0f, 984.0f, 258.0f, 70.0f, 70.0f, 70.0f, 70.0f, 2.0f, 0.0f};
    public boolean dirtyCommonArray = true;
    public boolean dirtyShadow = true;
    public boolean dirtyShadowArray = true;
    public boolean dirtyEdge = true;
    public boolean dirtyEdgeArray = true;
    public boolean dirtyCircle = true;
    public boolean lastCircleRenderEnabled = true;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MultiLightShaderParams() {
        float[] fArr = {0.24f, 0.1f, 0.96f, 0.71f, 0.1f, 94.0f, -10.0f, 0.0f, 1.058f, 1.208f, 1.0f, 2.0f, 2.0f, 2.0f, 24.0f, 0.36f};
        this.uShadowArrayDefault = fArr;
        this.uShadowArray = Arrays.copyOf(fArr, 16);
        float[] fArr2 = {1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.5f, 2.0f, 0.5f, 4.0f, 0.5f, 0.0f, 0.01f, 0.81f, 1.0f, 1.0f, 0.182f, 0.0f, 0.0f, 0.0f};
        this.uEdgeArrayDefault = fArr2;
        this.uEdgeArray = Arrays.copyOf(fArr2, 20);
        float[] fArr3 = {0.0f, 0.0f, 0.3f, 500.0f, 0.5f, 1.0f, 0.0f, 0.0f, 0.1f, -1.0f, 700.0f, 0.0f, 2000.0f, 50.0f};
        this.uCircleArrayDefault = fArr3;
        float[] fArrCopyOf = Arrays.copyOf(fArr3, 14);
        this.uCircleArray = fArrCopyOf;
        this.dirtyCircleArray = true;
        this.circleRepeatCount = -1;
        this.startTimeMs = SystemClock.uptimeMillis();
        Companion companion = Companion;
        float f = fArrCopyOf[3];
        float f2 = fArrCopyOf[2];
        companion.getClass();
        this.initialUTimeOffsetSec = (f * 3.0f) / (f2 * 4000.0f);
        flushIfDirty(true, true);
    }

    public final void dumpParams(String str) {
        StringBuilder sb = new StringBuilder();
        NestedDraggableNode$detectDrags$2$.ExternalSyntheticOutline0.m("[", str, "] u_commonArray: ", sb);
        final int i = 0;
        sb.append(ArraysKt___ArraysKt.joinToString$default(this.uCommonArray, new Function1() { // from class: com.oplus.systemui.qs.media.multilight.MultiLightShaderParams$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                Float f = (Float) obj;
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
                return String.valueOf(f.floatValue());
            }
        }, 31));
        sb.append(", u_edgeArray: ");
        final int i2 = 1;
        sb.append(ArraysKt___ArraysKt.joinToString$default(this.uEdgeArray, new Function1() { // from class: com.oplus.systemui.qs.media.multilight.MultiLightShaderParams$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                Float f = (Float) obj;
                switch (i2) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
                return String.valueOf(f.floatValue());
            }
        }, 31));
        sb.append(", u_shadowArray: ");
        final int i3 = 2;
        sb.append(ArraysKt___ArraysKt.joinToString$default(this.uShadowArray, new Function1() { // from class: com.oplus.systemui.qs.media.multilight.MultiLightShaderParams$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                Float f = (Float) obj;
                switch (i3) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
                return String.valueOf(f.floatValue());
            }
        }, 31));
        sb.append(", u_circleArray: ");
        final int i4 = 3;
        sb.append(ArraysKt___ArraysKt.joinToString$default(this.uCircleArray, new Function1() { // from class: com.oplus.systemui.qs.media.multilight.MultiLightShaderParams$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                Float f = (Float) obj;
                switch (i4) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
                return String.valueOf(f.floatValue());
            }
        }, 31));
        int i5 = this.uCornerType;
        float f = this.uEdge;
        float f2 = this.uShadow;
        StringBuilder sbM = NormalizedTouchData$.ExternalSyntheticOutline0.m(f, i5, ", u_cornerType: ", ", u_edge: ", ", u_shadow: ");
        sbM.append(f2);
        sbM.append(", ");
        sb.append(sbM.toString());
        sb.append("u_circle: " + this.uCircle + ", circleRepeatCount: " + this.circleRepeatCount);
        LogUtil.i$default(LogUtil.TAG_MEDIA, TAG, sb.toString(), null, 8);
    }

    public final void flushIfDirty(boolean z, boolean z2) {
        float f;
        float fUptimeMillis = (SystemClock.uptimeMillis() - this.startTimeMs) / 1000.0f;
        getRuntimeShader().setFloatUniform("u_time", this.initialUTimeOffsetSec + fUptimeMillis);
        if (!this.maskZAnimDone) {
            float[] fArr = this.uCircleArray;
            float f2 = fArr[3];
            float f3 = fArr[2];
            int i = this.circleRepeatCount;
            float f4 = i > 0 ? i : 3.0f;
            if (i > 0) {
                f = f4 - 1.0f;
                if (f < 0.0f) {
                    f = 0.0f;
                }
            } else {
                f = 2.0f;
            }
            Companion.getClass();
            float f5 = f3 * 1000.0f;
            float f6 = (f * f2) / f5;
            float f7 = (f4 * f2) / f5;
            if (fUptimeMillis >= f7) {
                this.uCircleArray[12] = this.circleRepeatCount > 0 ? 200.0f : 2000.0f;
                this.dirtyCircleArray = true;
                this.maskZAnimDone = true;
            } else if (fUptimeMillis >= f6) {
                float f8 = f7 - f6;
                if (f8 < 0.001f) {
                    f8 = 0.001f;
                }
                this.uCircleArray[12] = (RangesKt___RangesKt.coerceIn((fUptimeMillis - f6) / f8, 0.0f, 1.0f) * (-1800.0f)) + 2000.0f;
                this.dirtyCircleArray = true;
            }
        }
        if (z || this.dirtyConerType) {
            getRuntimeShader().setIntUniform("u_cornerType", this.uCornerType);
            this.dirtyConerType = false;
        }
        if (z || this.dirtyCommonArray) {
            getRuntimeShader().setFloatUniform("u_commonArray", this.uCommonArray);
            this.dirtyCommonArray = false;
        }
        if (z || this.dirtyShadow) {
            getRuntimeShader().setFloatUniform("u_shadow", this.uShadow);
            this.dirtyShadow = false;
        }
        if (z || this.dirtyShadowArray) {
            getRuntimeShader().setFloatUniform("u_shadowArray", this.uShadowArray);
            this.dirtyShadowArray = false;
        }
        if (z || this.dirtyEdge) {
            getRuntimeShader().setFloatUniform("u_edge", this.uEdge);
            this.dirtyEdge = false;
        }
        if (z || this.dirtyEdgeArray) {
            getRuntimeShader().setFloatUniform("u_edgeArray", this.uEdgeArray);
            this.dirtyEdgeArray = false;
        }
        if (z || this.dirtyCircle || this.lastCircleRenderEnabled != z2) {
            getRuntimeShader().setFloatUniform("u_circle", z2 ? this.uCircle : 0.0f);
            this.dirtyCircle = false;
            this.lastCircleRenderEnabled = z2;
        }
        if (z || this.dirtyCircleArray) {
            getRuntimeShader().setFloatUniform("u_circleArray", this.uCircleArray);
            this.dirtyCircleArray = false;
        }
    }

    public final long getCircleAnimationDurationMillis() {
        int i = this.circleRepeatCount;
        if (i < 0) {
            return 1000L;
        }
        float[] fArr = this.uCircleArray;
        float f = fArr[3];
        if (f < 0.001f) {
            f = 0.001f;
        }
        float f2 = fArr[2];
        float f3 = f2 >= 0.001f ? f2 : 0.001f;
        Companion.getClass();
        long j = (long) (((i * f) / (f3 * 1000.0f)) * 1000.0f);
        if (j < 1) {
            return 1L;
        }
        return j;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final float getParamValueByArray(String str, int i) {
        switch (str.hashCode()) {
            case -2104134081:
                if (str.equals("u_conerType")) {
                    return this.uCornerType;
                }
                return 0.0f;
            case -1082077358:
                if (str.equals("u_edgeArray")) {
                    float[] fArr = this.uEdgeArray;
                    if (i < 0 || i >= fArr.length) {
                        return 0.0f;
                    }
                    return fArr[i];
                }
                return 0.0f;
            case -901366822:
                if (str.equals("u_circle")) {
                    return this.uCircle;
                }
                return 0.0f;
            case -854513849:
                if (str.equals("u_edge")) {
                    return this.uEdge;
                }
                return 0.0f;
            case -684679521:
                if (str.equals("u_circleArray")) {
                    float[] fArr2 = this.uCircleArray;
                    if (i < 0 || i >= fArr2.length) {
                        return 0.0f;
                    }
                    return fArr2[i];
                }
                return 0.0f;
            case -444729302:
                if (str.equals("u_shadow")) {
                    return this.uShadow;
                }
                return 0.0f;
            case 1751984740:
                if (str.equals("u_commonArray")) {
                    float[] fArr3 = this.uCommonArray;
                    if (i < 0 || i >= fArr3.length) {
                        return 0.0f;
                    }
                    return fArr3[i];
                }
                return 0.0f;
            case 2113016911:
                if (str.equals("u_shadowArray")) {
                    float[] fArr4 = this.uShadowArray;
                    if (i < 0 || i >= fArr4.length) {
                        return 0.0f;
                    }
                    return fArr4[i];
                }
                return 0.0f;
            default:
                return 0.0f;
        }
    }

    @Override // com.oplus.systemui.qs.media.multilight.BaseShaderInterface
    public RuntimeShader getRuntimeShader() {
        return this.runtimeShader;
    }

    public final void resetTime() {
        this.startTimeMs = SystemClock.uptimeMillis();
        this.maskZAnimDone = false;
        float[] fArr = this.uCircleArray;
        fArr[12] = 2000.0f;
        this.dirtyCircleArray = true;
        Companion companion = Companion;
        float f = fArr[3];
        float f2 = fArr[2];
        companion.getClass();
        this.initialUTimeOffsetSec = (f * 3.0f) / (f2 * 4000.0f);
    }

    public final void setAngle(float f) {
        float[] fArr = this.uEdgeArray;
        if (fArr[16] == f) {
            return;
        }
        fArr[16] = f;
        this.dirtyEdgeArray = true;
    }

    public final void setCircleAlpha(float f) {
        float[] fArr = this.uCircleArray;
        if (fArr[4] == f) {
            return;
        }
        fArr[4] = f;
        this.dirtyCircleArray = true;
    }

    public final void setCircleColor(float f, float f2, float f3) {
        float[] fArr = this.uCircleArray;
        fArr[5] = f;
        fArr[6] = f2;
        fArr[7] = f3;
        this.dirtyCircleArray = true;
    }

    public final void setCircleEnabled(boolean z) {
        this.uCircle = z ? 1.0f : 0.0f;
        this.dirtyCircle = true;
    }

    public final void setCircleMaskDis(Float f, Float f2, Float f3, Float f4) {
        if (f != null) {
            this.uCircleArray[10] = f.floatValue();
        }
        if (f2 != null) {
            this.uCircleArray[11] = f2.floatValue();
        }
        if (f3 != null) {
            this.uCircleArray[12] = f3.floatValue();
        }
        if (f4 != null) {
            this.uCircleArray[13] = f4.floatValue();
        }
        this.dirtyCircleArray = true;
    }

    public final void setCirclePos(Float f, Float f2) {
        if (f != null) {
            this.uCircleArray[0] = f.floatValue();
        }
        if (f2 != null) {
            this.uCircleArray[1] = f2.floatValue();
        }
        this.dirtyCircleArray = true;
    }

    public final void setCircleRepeatCount(int i) {
        this.circleRepeatCount = i;
    }

    public final void setCircleSpeed(Float f, Float f2) {
        if (f != null) {
            this.uCircleArray[2] = f.floatValue();
        }
        if (f2 != null) {
            this.uCircleArray[3] = f2.floatValue();
        }
        this.dirtyCircleArray = true;
    }

    public final void setCircleWidth(Float f, Float f2) {
        if (f != null) {
            this.uCircleArray[8] = f.floatValue();
        }
        if (f2 != null) {
            this.uCircleArray[9] = f2.floatValue();
        }
        this.dirtyCircleArray = true;
    }

    public final void setCornerRadii(Float f, Float f2, Float f3, Float f4) {
        if (f != null) {
            this.uCommonArray[6] = f.floatValue();
        }
        if (f2 != null) {
            this.uCommonArray[7] = f2.floatValue();
        }
        if (f3 != null) {
            this.uCommonArray[8] = f3.floatValue();
        }
        if (f4 != null) {
            this.uCommonArray[9] = f4.floatValue();
        }
        if (f == null && f2 == null && f3 == null && f4 == null) {
            return;
        }
        this.dirtyCommonArray = true;
    }

    public final void setCornerType(int i) {
        this.uCornerType = RangesKt___RangesKt.coerceIn(i, 1, 2);
        this.dirtyConerType = true;
    }

    public final void setEdgeEnabled(boolean z) {
        this.uEdge = z ? 1.0f : 0.0f;
        this.dirtyEdge = true;
    }

    public final void setFadeAlpha(Float f, Float f2) {
        if (f != null) {
            this.uShadowArray[0] = f.floatValue();
        }
        if (f2 != null) {
            this.uShadowArray[1] = f2.floatValue();
        }
        this.dirtyShadowArray = true;
    }

    public final void setFadeIn(float f) {
        float[] fArr = this.uShadowArray;
        if (fArr[5] == f) {
            return;
        }
        fArr[5] = f;
        this.dirtyShadowArray = true;
    }

    public final void setFadeInColor(float f, float f2, float f3) {
        float[] fArr = this.uShadowArray;
        fArr[2] = f;
        fArr[3] = f2;
        fArr[4] = f3;
        this.dirtyShadowArray = true;
    }

    public final void setFadeInPara(float f) {
        float[] fArr = this.uShadowArray;
        if (fArr[10] == f) {
            return;
        }
        fArr[10] = f;
        this.dirtyShadowArray = true;
    }

    public final void setFadeOffset(Float f, Float f2) {
        if (f != null) {
            this.uShadowArray[6] = f.floatValue();
        }
        if (f2 != null) {
            this.uShadowArray[7] = f2.floatValue();
        }
        this.dirtyShadowArray = true;
    }

    public final void setFadeOut(float f) {
        float[] fArr = this.uShadowArray;
        if (fArr[14] == f) {
            return;
        }
        fArr[14] = f;
        this.dirtyShadowArray = true;
    }

    public final void setFadeOutColor(float f, float f2, float f3) {
        float[] fArr = this.uShadowArray;
        fArr[11] = f;
        fArr[12] = f2;
        fArr[13] = f3;
        this.dirtyShadowArray = true;
    }

    public final void setFadeOutPara(float f) {
        float[] fArr = this.uShadowArray;
        if (fArr[15] == f) {
            return;
        }
        fArr[15] = f;
        this.dirtyShadowArray = true;
    }

    public final void setFadeScale(Float f, Float f2) {
        if (f != null) {
            this.uShadowArray[8] = f.floatValue();
        }
        if (f2 != null) {
            this.uShadowArray[9] = f2.floatValue();
        }
        this.dirtyShadowArray = true;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void setParamValueByArray(String str, int i, float f) {
        switch (str.hashCode()) {
            case -2104134081:
                if (str.equals("u_conerType")) {
                    this.uCornerType = (int) f;
                    this.dirtyConerType = true;
                    return;
                }
                break;
            case -1082077358:
                if (str.equals("u_edgeArray")) {
                    if (i >= 0) {
                        float[] fArr = this.uEdgeArray;
                        if (i < fArr.length) {
                            fArr[i] = f;
                            this.dirtyEdgeArray = true;
                            return;
                        }
                        return;
                    }
                    return;
                }
                break;
            case -901366822:
                if (str.equals("u_circle")) {
                    this.uCircle = f;
                    this.dirtyCircle = true;
                    return;
                }
                break;
            case -854513849:
                if (str.equals("u_edge")) {
                    this.uEdge = f;
                    this.dirtyEdge = true;
                    return;
                }
                break;
            case -684679521:
                if (str.equals("u_circleArray")) {
                    if (i >= 0) {
                        float[] fArr2 = this.uCircleArray;
                        if (i < fArr2.length) {
                            fArr2[i] = f;
                            this.dirtyCircleArray = true;
                            return;
                        }
                        return;
                    }
                    return;
                }
                break;
            case -444729302:
                if (str.equals("u_shadow")) {
                    this.uShadow = f;
                    this.dirtyShadow = true;
                    return;
                }
                break;
            case 1751984740:
                if (str.equals("u_commonArray")) {
                    if (i >= 0) {
                        float[] fArr3 = this.uCommonArray;
                        if (i < fArr3.length) {
                            fArr3[i] = f;
                            this.dirtyCommonArray = true;
                            return;
                        }
                        return;
                    }
                    return;
                }
                break;
            case 2113016911:
                if (str.equals("u_shadowArray")) {
                    if (i >= 0) {
                        float[] fArr4 = this.uShadowArray;
                        if (i < fArr4.length) {
                            fArr4[i] = f;
                            this.dirtyShadowArray = true;
                            return;
                        }
                        return;
                    }
                    return;
                }
                break;
        }
        LogUtil.e$default(8, LogUtil.TAG_MEDIA, TAG, Bundler$.ExternalSyntheticOutline0.m("unknown name '", str, "', skip"));
    }

    public final void setPosition(Float f, Float f2) {
        if (f != null) {
            this.uCommonArray[2] = f.floatValue();
        }
        if (f2 != null) {
            this.uCommonArray[3] = f2.floatValue();
        }
        if (f == null && f2 == null) {
            return;
        }
        this.dirtyCommonArray = true;
    }

    public final void setRectLineAlpha(Float f, Float f2) {
        if (f != null) {
            this.uEdgeArray[10] = f.floatValue();
        }
        if (f2 != null) {
            this.uEdgeArray[11] = f2.floatValue();
        }
        this.dirtyEdgeArray = true;
    }

    public final void setRectLineColorEnd(float f, float f2, float f3) {
        float[] fArr = this.uEdgeArray;
        fArr[3] = f;
        fArr[4] = f2;
        fArr[5] = f3;
        this.dirtyEdgeArray = true;
    }

    public final void setRectLineColorPara(float f) {
        float[] fArr = this.uEdgeArray;
        if (fArr[18] == f) {
            return;
        }
        fArr[18] = f;
        this.dirtyEdgeArray = true;
    }

    public final void setRectLineColorStart(float f, float f2, float f3) {
        float[] fArr = this.uEdgeArray;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        this.dirtyEdgeArray = true;
    }

    public final void setRectLineFade(Float f, Float f2, Float f3, Float f4) {
        if (f != null) {
            this.uEdgeArray[12] = f.floatValue();
        }
        if (f2 != null) {
            this.uEdgeArray[13] = f2.floatValue();
        }
        if (f3 != null) {
            this.uEdgeArray[14] = f3.floatValue();
        }
        if (f4 != null) {
            this.uEdgeArray[15] = f4.floatValue();
        }
        this.dirtyEdgeArray = true;
    }

    public final void setRectLineFar(Float f, Float f2) {
        if (f != null) {
            this.uEdgeArray[8] = f.floatValue();
        }
        if (f2 != null) {
            this.uEdgeArray[9] = f2.floatValue();
        }
        this.dirtyEdgeArray = true;
    }

    public final void setRectLineNear(Float f, Float f2) {
        if (f != null) {
            this.uEdgeArray[6] = f.floatValue();
        }
        if (f2 != null) {
            this.uEdgeArray[7] = f2.floatValue();
        }
        this.dirtyEdgeArray = true;
    }

    public final void setRectLinePara(float f) {
        float[] fArr = this.uEdgeArray;
        if (fArr[17] == f) {
            return;
        }
        fArr[17] = f;
        this.dirtyEdgeArray = true;
    }

    public final void setResolution(float f, float f2) {
        float[] fArr = this.uCommonArray;
        if (fArr[0] == f && fArr[1] == f2) {
            return;
        }
        fArr[0] = f;
        fArr[1] = f2;
        this.dirtyCommonArray = true;
    }

    public final void setShadowEnabled(boolean z) {
        this.uShadow = z ? 1.0f : 0.0f;
        this.dirtyShadow = true;
    }

    public final void setSize(Float f, Float f2) {
        if (f != null) {
            this.uCommonArray[4] = f.floatValue();
        }
        if (f2 != null) {
            this.uCommonArray[5] = f2.floatValue();
        }
        if (f == null && f2 == null) {
            return;
        }
        this.dirtyCommonArray = true;
    }

    public final void setWeight(float f) {
        this.uCommonArray[10] = RangesKt___RangesKt.coerceIn(f, 0.0f, 3.0f);
        this.dirtyCommonArray = true;
    }
}
