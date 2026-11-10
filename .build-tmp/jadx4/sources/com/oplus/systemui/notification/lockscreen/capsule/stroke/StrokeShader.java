package com.oplus.systemui.notification.lockscreen.capsule.stroke;

import android.graphics.Color;
import android.graphics.RuntimeShader;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StrokeShader extends RuntimeShader {
    private static final String COMMON_ARRAY = "u_commonArray";
    public static final Companion Companion = new Companion();
    private static final String EDGE_ARRAY = "u_edgeArray";
    private static final String FULL_SHADER = "\n            uniform float2 u_resolution;\n\n            // Base数据\n            uniform float u_commonArray[12];\n\n            // 描边参数\n            uniform float u_edgeArray[20];\n        \n            // ============================================================\n            // Constants & Structures\n            // ============================================================\n\n            const float EPS = 1e-6;\n            const float kAbs = 1.0;\n            const float rRef = 80.0;\n            const float kMax = 26.6;\n            const float junctionXBand = 70.0;\n            const float junctionYBand = 60.0;\n            const float junctionPower = 1.0;\n\n            struct CommonDataRect {\n                float2 halfResolution;\n                float2 pos;\n                float2 halfSize;\n                float coner;\n                float weight;\n                float preCalcMinC;\n            };\n\n            // ============================================================\n            // Helper Functions\n            // ============================================================\n\n            float smoothAbs(float x, float k) {\n                return sqrt(x * x + k * k);\n            }\n\n            float smoothMaxSqrt(float a, float b, float k) {\n                float d = a - b;\n                return 0.5 * (a + b + sqrt(d * d + k * k));\n            }\n\n            float sdCapsule(float2 pLocal, float2 halfSize) {\n                bool isVertical = halfSize.x < halfSize.y;\n                float2 hs = isVertical ? halfSize.yx : halfSize;\n                float2 pl = isVertical ? pLocal.yx : pLocal;\n\n                float2 q = float2(smoothAbs(pl.x, kAbs), smoothAbs(pl.y, kAbs));\n                float r = hs.y;\n                float a = max(hs.x - r, 0.0);\n                float scale = r / max(rRef, 1e-3);\n                float kMaxEff = kMax * scale;\n                float xBandEff = junctionXBand * scale;\n                float yBandEff = junctionYBand * scale;\n                float s = q.x - a;\n                float xHard = max(s, 0.0);\n                float xSoft = (kMaxEff > 0.0) ? smoothMaxSqrt(s, 0.0, kMaxEff) : xHard;\n                float wx = 1.0 - smoothstep(0.0, max(xBandEff, 1e-3), abs(s));\n                float wy = smoothstep(r - max(yBandEff, 1e-3), r, q.y);\n                float w = pow(clamp(wx * wy, 0.0, 1.0), junctionPower);\n                float x = mix(xHard, xSoft, w);\n                return length(float2(x, q.y)) - r;\n            }\n\n            // ============ G2圆角矩形函数 ============\n\n            float powFast(float x, float n) {\n                x = max(x, 1e-8);\n                return exp2(log2(x) * n);\n            }\n\n            float lpNorm(float2 v, float n) {\n                v = abs(v);\n                float m = max(v.x, v.y);\n                if (m < 1e-8) return 0.0;\n                float2 u = v / m;\n                float s = powFast(u.x, n) + powFast(u.y, n);\n                return m * exp2(log2(max(s, EPS)) / n);\n            }\n\n            float lpNorm4(float2 v) {\n                v = abs(v);\n                float2 v2 = v * v;\n                float2 v4 = v2 * v2;\n                return sqrt(sqrt(max(v4.x + v4.y, 0.0)));\n            }\n\n            float4 normalizeRadiiIndependent(float2 halfSize, float4 r) {\n                float W = 2.0 * halfSize.x;\n                float H = 2.0 * halfSize.y;\n\n                float kTop    = min(1.0, W / max(r.x + r.y, EPS));\n                float kBottom = min(1.0, W / max(r.w + r.z, EPS));\n                float kLeft   = min(1.0, H / max(r.x + r.w, EPS));\n                float kRight  = min(1.0, H / max(r.y + r.z, EPS));\n\n                float4 k;\n                k.x = min(kTop, kLeft);\n                k.y = min(kTop, kRight);\n                k.z = min(kBottom, kRight);\n                k.w = min(kBottom, kLeft);\n\n                return r * k;\n            }\n\n            float sdRoundRect(float2 p, float2 halfSize, float4 r4, float n) {\n                float2 s = step(float2(0.0), p);\n\n                float rTop = mix(r4.x, r4.y, s.x);\n                float rBot = mix(r4.w, r4.z, s.x);\n                float r = mix(rTop, rBot, s.y);\n\n                float2 q = abs(p) - halfSize + float2(r);\n\n                float nClamped = max(n, 2.0);\n                float dCorner = (abs(nClamped - 4.0) < 1e-3)\n                    ? lpNorm4(max(q, 0.0))\n                    : lpNorm(max(q, 0.0), nClamped);\n\n                float dSide = min(max(q.x, q.y), 0.0);\n\n                if (q.x > 0.0 && q.y > 0.0) {\n                    return dCorner - r;\n                } else {\n                    return max(q.x, q.y) - r;\n                }\n            }\n\n            float4 getNormalizedRadius(float2 halfSize, float4 radiusVec) {\n                float rMax = min(halfSize.x, halfSize.y);\n                float4 rSafe = clamp(radiusVec, float4(0.0), float4(rMax));\n                return normalizeRadiiIndependent(halfSize, rSafe);\n            }\n        \n            // 描边辅助函数\n            float getDis(float2 pos, float2 halfsize) {\n                float2 rect = abs(pos - halfsize);\n                return rect.x + rect.y;\n            }\n\n            void blendEdge(CommonDataRect rectData, inout half4 color, float d) {\n                float3 uRectLineColor = float3(u_edgeArray[0], u_edgeArray[1], u_edgeArray[2]);\n                float2 uRectLineNear = float2(u_edgeArray[3], u_edgeArray[4]);\n                float2 uRectLineFar = float2(u_edgeArray[5], u_edgeArray[6]);\n                float2 uRectLineAlpha = float2(u_edgeArray[7], u_edgeArray[8]);\n                float4 uRectLineFade = float4(u_edgeArray[9], u_edgeArray[10], u_edgeArray[11], u_edgeArray[12]);\n                float uAngle = u_edgeArray[13];\n                float uRectLinePara1 = u_edgeArray[14];\n                float uRectLinePara2 = u_edgeArray[15];\n\n                float2 rectLineNear = uRectLineNear;\n                rectLineNear.y += uRectLineNear.x;\n                float2 rectLineFar = uRectLineFar;\n                rectLineFar.y += uRectLineFar.x;\n                float4 rectLineFade = uRectLineFade;\n                rectLineFade.yw += rectLineFade.xz;\n\n                float distanceAll = (rectData.halfSize.x + rectData.halfSize.y) * 2.0;\n                float2 halfSize = rectData.halfSize;\n\n                float ratio = mod(uAngle + rectData.halfSize.x / distanceAll * 0.5 + 0.5, 1.0);\n                float2 dir_pos = normalize(rectData.pos);\n                float2 dirAbs_pos = abs(dir_pos);\n                float2 pos_n = min(float2(dirAbs_pos.xy * halfSize.yx / max(float2(.001), dirAbs_pos.yx)), halfSize) * sign(dir_pos);\n                float2 halfSize_symobol = float2(-halfSize.y, halfSize.x);\n                float flag = mix(-1.0, 1.0, step(ratio, 0.5)) * dot(halfSize_symobol, pos_n);\n                float dis_point = distanceAll * min(ratio, 1.0 - ratio) * 2.0;\n                float dis_pos = getDis(pos_n, halfSize);\n                float distance_near = mix(dis_point + dis_pos, abs(dis_point - dis_pos), step(0., flag));\n                distance_near = min(2. * distanceAll - distance_near, distance_near);\n                float lineNearAlpha = uRectLineAlpha.x * smoothstep(rectLineFade.y * distanceAll, rectLineFade.x * distanceAll, distance_near);\n                float lineFarAlpha = uRectLineAlpha.y * smoothstep(rectLineFade.w * distanceAll, rectLineFade.z * distanceAll, distanceAll - distance_near);\n\n                float lineNearSdf = smoothstep(-rectLineNear.y, -rectLineNear.x, d);\n                float lineFarSdf = smoothstep(-rectLineFar.y, -rectLineFar.x, d);\n\n                if (uRectLinePara2 > 0.001) {\n                    lineNearSdf = pow(lineNearSdf, uRectLinePara1) * uRectLinePara2 + (1.0 - uRectLinePara2) * lineNearSdf;\n                    lineFarSdf = pow(lineFarSdf, uRectLinePara1) * uRectLinePara2 + (1.0 - uRectLinePara2) * lineFarSdf;\n                }\n\n                lineNearAlpha *= lineNearSdf;\n                lineFarAlpha *= lineFarSdf;\n\n                float lineAlpha = clamp(lineNearAlpha + lineFarAlpha, 0., 1.);\n                color = half4(uRectLineColor, lineAlpha);\n            }\n        \n            // ============================================================\n            // Main Entry Point\n            // ============================================================\n\n            half4 main(float2 fragCoord) {\n                // 数据准备\n                float antiAliasing = u_commonArray[8];\n                CommonDataRect rectData;\n                rectData.halfResolution = float2(u_commonArray[0], u_commonArray[1]) * 0.5;\n                rectData.pos = fragCoord.xy - float2(u_commonArray[2], u_commonArray[3]) - rectData.halfResolution;\n                rectData.halfSize = float2(u_commonArray[4], u_commonArray[5]) * 0.5;\n                rectData.coner = u_commonArray[6];\n                rectData.weight = u_commonArray[7];\n                rectData.preCalcMinC = min(rectData.halfSize.x, rectData.halfSize.y);\n                float4 u_cornerRadius_S1 = float4(rectData.coner);\n\n                float rectSdf = 0.0;\n                if (rectData.weight < 2.0) {\n                    if (rectData.weight > 1.005) {\n                        float4 r4 = getNormalizedRadius(rectData.halfSize, u_cornerRadius_S1);\n                        float t = smoothstep(2.0, 1.0, rectData.weight);\n                        float d_G2 = sdRoundRect(rectData.pos, rectData.halfSize, r4, 2.0);\n                        float d_Capsule = sdCapsule(rectData.pos, rectData.halfSize);\n                        rectSdf = mix(d_G2, d_Capsule, t);\n                    } else {\n                        rectSdf = sdCapsule(rectData.pos, rectData.halfSize);\n                    }\n                } else {\n                    float4 r4 = getNormalizedRadius(rectData.halfSize, u_cornerRadius_S1);\n                    rectSdf = sdRoundRect(rectData.pos, rectData.halfSize, r4, rectData.weight);\n                }\n\n                float shape = smoothstep(0.0, -antiAliasing, rectSdf);\n                if (shape > 0.001) {\n                    // 描边\n                    half4 color = half4(0.0);\n                    blendEdge(rectData, color, rectSdf);\n                    color.rgb *= color.a;\n                    return color * shape;\n                }\n                \n                return half4(0.0);\n            }\n        ";
    private static final String SHADER_MAIN = "\n            // ============================================================\n            // Main Entry Point\n            // ============================================================\n\n            half4 main(float2 fragCoord) {\n                // 数据准备\n                float antiAliasing = u_commonArray[8];\n                CommonDataRect rectData;\n                rectData.halfResolution = float2(u_commonArray[0], u_commonArray[1]) * 0.5;\n                rectData.pos = fragCoord.xy - float2(u_commonArray[2], u_commonArray[3]) - rectData.halfResolution;\n                rectData.halfSize = float2(u_commonArray[4], u_commonArray[5]) * 0.5;\n                rectData.coner = u_commonArray[6];\n                rectData.weight = u_commonArray[7];\n                rectData.preCalcMinC = min(rectData.halfSize.x, rectData.halfSize.y);\n                float4 u_cornerRadius_S1 = float4(rectData.coner);\n\n                float rectSdf = 0.0;\n                if (rectData.weight < 2.0) {\n                    if (rectData.weight > 1.005) {\n                        float4 r4 = getNormalizedRadius(rectData.halfSize, u_cornerRadius_S1);\n                        float t = smoothstep(2.0, 1.0, rectData.weight);\n                        float d_G2 = sdRoundRect(rectData.pos, rectData.halfSize, r4, 2.0);\n                        float d_Capsule = sdCapsule(rectData.pos, rectData.halfSize);\n                        rectSdf = mix(d_G2, d_Capsule, t);\n                    } else {\n                        rectSdf = sdCapsule(rectData.pos, rectData.halfSize);\n                    }\n                } else {\n                    float4 r4 = getNormalizedRadius(rectData.halfSize, u_cornerRadius_S1);\n                    rectSdf = sdRoundRect(rectData.pos, rectData.halfSize, r4, rectData.weight);\n                }\n\n                float shape = smoothstep(0.0, -antiAliasing, rectSdf);\n                if (shape > 0.001) {\n                    // 描边\n                    half4 color = half4(0.0);\n                    blendEdge(rectData, color, rectSdf);\n                    color.rgb *= color.a;\n                    return color * shape;\n                }\n                \n                return half4(0.0);\n            }\n        ";
    private static final String SHADER_SDF_LIB = "\n            // ============================================================\n            // Constants & Structures\n            // ============================================================\n\n            const float EPS = 1e-6;\n            const float kAbs = 1.0;\n            const float rRef = 80.0;\n            const float kMax = 26.6;\n            const float junctionXBand = 70.0;\n            const float junctionYBand = 60.0;\n            const float junctionPower = 1.0;\n\n            struct CommonDataRect {\n                float2 halfResolution;\n                float2 pos;\n                float2 halfSize;\n                float coner;\n                float weight;\n                float preCalcMinC;\n            };\n\n            // ============================================================\n            // Helper Functions\n            // ============================================================\n\n            float smoothAbs(float x, float k) {\n                return sqrt(x * x + k * k);\n            }\n\n            float smoothMaxSqrt(float a, float b, float k) {\n                float d = a - b;\n                return 0.5 * (a + b + sqrt(d * d + k * k));\n            }\n\n            float sdCapsule(float2 pLocal, float2 halfSize) {\n                bool isVertical = halfSize.x < halfSize.y;\n                float2 hs = isVertical ? halfSize.yx : halfSize;\n                float2 pl = isVertical ? pLocal.yx : pLocal;\n\n                float2 q = float2(smoothAbs(pl.x, kAbs), smoothAbs(pl.y, kAbs));\n                float r = hs.y;\n                float a = max(hs.x - r, 0.0);\n                float scale = r / max(rRef, 1e-3);\n                float kMaxEff = kMax * scale;\n                float xBandEff = junctionXBand * scale;\n                float yBandEff = junctionYBand * scale;\n                float s = q.x - a;\n                float xHard = max(s, 0.0);\n                float xSoft = (kMaxEff > 0.0) ? smoothMaxSqrt(s, 0.0, kMaxEff) : xHard;\n                float wx = 1.0 - smoothstep(0.0, max(xBandEff, 1e-3), abs(s));\n                float wy = smoothstep(r - max(yBandEff, 1e-3), r, q.y);\n                float w = pow(clamp(wx * wy, 0.0, 1.0), junctionPower);\n                float x = mix(xHard, xSoft, w);\n                return length(float2(x, q.y)) - r;\n            }\n\n            // ============ G2圆角矩形函数 ============\n\n            float powFast(float x, float n) {\n                x = max(x, 1e-8);\n                return exp2(log2(x) * n);\n            }\n\n            float lpNorm(float2 v, float n) {\n                v = abs(v);\n                float m = max(v.x, v.y);\n                if (m < 1e-8) return 0.0;\n                float2 u = v / m;\n                float s = powFast(u.x, n) + powFast(u.y, n);\n                return m * exp2(log2(max(s, EPS)) / n);\n            }\n\n            float lpNorm4(float2 v) {\n                v = abs(v);\n                float2 v2 = v * v;\n                float2 v4 = v2 * v2;\n                return sqrt(sqrt(max(v4.x + v4.y, 0.0)));\n            }\n\n            float4 normalizeRadiiIndependent(float2 halfSize, float4 r) {\n                float W = 2.0 * halfSize.x;\n                float H = 2.0 * halfSize.y;\n\n                float kTop    = min(1.0, W / max(r.x + r.y, EPS));\n                float kBottom = min(1.0, W / max(r.w + r.z, EPS));\n                float kLeft   = min(1.0, H / max(r.x + r.w, EPS));\n                float kRight  = min(1.0, H / max(r.y + r.z, EPS));\n\n                float4 k;\n                k.x = min(kTop, kLeft);\n                k.y = min(kTop, kRight);\n                k.z = min(kBottom, kRight);\n                k.w = min(kBottom, kLeft);\n\n                return r * k;\n            }\n\n            float sdRoundRect(float2 p, float2 halfSize, float4 r4, float n) {\n                float2 s = step(float2(0.0), p);\n\n                float rTop = mix(r4.x, r4.y, s.x);\n                float rBot = mix(r4.w, r4.z, s.x);\n                float r = mix(rTop, rBot, s.y);\n\n                float2 q = abs(p) - halfSize + float2(r);\n\n                float nClamped = max(n, 2.0);\n                float dCorner = (abs(nClamped - 4.0) < 1e-3)\n                    ? lpNorm4(max(q, 0.0))\n                    : lpNorm(max(q, 0.0), nClamped);\n\n                float dSide = min(max(q.x, q.y), 0.0);\n\n                if (q.x > 0.0 && q.y > 0.0) {\n                    return dCorner - r;\n                } else {\n                    return max(q.x, q.y) - r;\n                }\n            }\n\n            float4 getNormalizedRadius(float2 halfSize, float4 radiusVec) {\n                float rMax = min(halfSize.x, halfSize.y);\n                float4 rSafe = clamp(radiusVec, float4(0.0), float4(rMax));\n                return normalizeRadiiIndependent(halfSize, rSafe);\n            }\n        ";
    private static final String SHADER_STROKE_LIB = "\n            // 描边辅助函数\n            float getDis(float2 pos, float2 halfsize) {\n                float2 rect = abs(pos - halfsize);\n                return rect.x + rect.y;\n            }\n\n            void blendEdge(CommonDataRect rectData, inout half4 color, float d) {\n                float3 uRectLineColor = float3(u_edgeArray[0], u_edgeArray[1], u_edgeArray[2]);\n                float2 uRectLineNear = float2(u_edgeArray[3], u_edgeArray[4]);\n                float2 uRectLineFar = float2(u_edgeArray[5], u_edgeArray[6]);\n                float2 uRectLineAlpha = float2(u_edgeArray[7], u_edgeArray[8]);\n                float4 uRectLineFade = float4(u_edgeArray[9], u_edgeArray[10], u_edgeArray[11], u_edgeArray[12]);\n                float uAngle = u_edgeArray[13];\n                float uRectLinePara1 = u_edgeArray[14];\n                float uRectLinePara2 = u_edgeArray[15];\n\n                float2 rectLineNear = uRectLineNear;\n                rectLineNear.y += uRectLineNear.x;\n                float2 rectLineFar = uRectLineFar;\n                rectLineFar.y += uRectLineFar.x;\n                float4 rectLineFade = uRectLineFade;\n                rectLineFade.yw += rectLineFade.xz;\n\n                float distanceAll = (rectData.halfSize.x + rectData.halfSize.y) * 2.0;\n                float2 halfSize = rectData.halfSize;\n\n                float ratio = mod(uAngle + rectData.halfSize.x / distanceAll * 0.5 + 0.5, 1.0);\n                float2 dir_pos = normalize(rectData.pos);\n                float2 dirAbs_pos = abs(dir_pos);\n                float2 pos_n = min(float2(dirAbs_pos.xy * halfSize.yx / max(float2(.001), dirAbs_pos.yx)), halfSize) * sign(dir_pos);\n                float2 halfSize_symobol = float2(-halfSize.y, halfSize.x);\n                float flag = mix(-1.0, 1.0, step(ratio, 0.5)) * dot(halfSize_symobol, pos_n);\n                float dis_point = distanceAll * min(ratio, 1.0 - ratio) * 2.0;\n                float dis_pos = getDis(pos_n, halfSize);\n                float distance_near = mix(dis_point + dis_pos, abs(dis_point - dis_pos), step(0., flag));\n                distance_near = min(2. * distanceAll - distance_near, distance_near);\n                float lineNearAlpha = uRectLineAlpha.x * smoothstep(rectLineFade.y * distanceAll, rectLineFade.x * distanceAll, distance_near);\n                float lineFarAlpha = uRectLineAlpha.y * smoothstep(rectLineFade.w * distanceAll, rectLineFade.z * distanceAll, distanceAll - distance_near);\n\n                float lineNearSdf = smoothstep(-rectLineNear.y, -rectLineNear.x, d);\n                float lineFarSdf = smoothstep(-rectLineFar.y, -rectLineFar.x, d);\n\n                if (uRectLinePara2 > 0.001) {\n                    lineNearSdf = pow(lineNearSdf, uRectLinePara1) * uRectLinePara2 + (1.0 - uRectLinePara2) * lineNearSdf;\n                    lineFarSdf = pow(lineFarSdf, uRectLinePara1) * uRectLinePara2 + (1.0 - uRectLinePara2) * lineFarSdf;\n                }\n\n                lineNearAlpha *= lineNearSdf;\n                lineFarAlpha *= lineFarSdf;\n\n                float lineAlpha = clamp(lineNearAlpha + lineFarAlpha, 0., 1.);\n                color = half4(uRectLineColor, lineAlpha);\n            }\n        ";
    private static final String SHADER_UNIFORMS = "\n            uniform float2 u_resolution;\n\n            // Base数据\n            uniform float u_commonArray[12];\n\n            // 描边参数\n            uniform float u_edgeArray[20];\n        ";
    public float angle;
    public float antiAliasing;
    private final float[] commonArray;
    public float cornerRadius;
    private final float[] edgeArray;
    public float farAlpha;
    public float farFadeStart;
    public float farFadeWidth;
    public float farStrokeStart;
    public float farStrokeWidth;
    public float nearAlpha;
    public float nearFadeStart;
    public float nearFadeWidth;
    public float nearStrokeStart;
    public float nearStrokeWidth;
    public int strokeColor;
    public float strokePower;
    public float strokePowerMix;
    public float weight;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final StrokeShader create() {
            return new StrokeShader();
        }
    }

    public StrokeShader() {
        super(FULL_SHADER);
        this.commonArray = new float[12];
        this.edgeArray = new float[20];
        this.weight = 1.0f;
        this.antiAliasing = 2.0f;
        this.strokeColor = -16776961;
        this.nearStrokeStart = 1.0f;
        this.nearStrokeWidth = 2.0f;
        this.farStrokeStart = 1.0f;
        this.farStrokeWidth = 2.0f;
        this.nearAlpha = 1.0f;
        this.farAlpha = 1.0f;
        this.nearFadeWidth = 0.4f;
        this.farFadeWidth = 0.2f;
        this.strokePower = 9.23f;
        this.strokePowerMix = 0.678f;
    }

    public static final StrokeShader create() {
        return Companion.create();
    }

    public final float getAngle() {
        return this.angle;
    }

    public final float getAntiAliasing() {
        return this.antiAliasing;
    }

    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    public final float getFarAlpha() {
        return this.farAlpha;
    }

    public final float getFarFadeStart() {
        return this.farFadeStart;
    }

    public final float getFarFadeWidth() {
        return this.farFadeWidth;
    }

    public final float getFarStrokeStart() {
        return this.farStrokeStart;
    }

    public final float getFarStrokeWidth() {
        return this.farStrokeWidth;
    }

    public final float getNearAlpha() {
        return this.nearAlpha;
    }

    public final float getNearFadeStart() {
        return this.nearFadeStart;
    }

    public final float getNearFadeWidth() {
        return this.nearFadeWidth;
    }

    public final float getNearStrokeStart() {
        return this.nearStrokeStart;
    }

    public final float getNearStrokeWidth() {
        return this.nearStrokeWidth;
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    public final float getStrokePower() {
        return this.strokePower;
    }

    public final float getStrokePowerMix() {
        return this.strokePowerMix;
    }

    public final float getWeight() {
        return this.weight;
    }

    public final void setAngle(float f) {
        this.angle = f;
        this.edgeArray[13] = f;
        updateEdgeArray();
    }

    public final void setAntiAliasing(float f) {
        this.antiAliasing = f;
        this.commonArray[8] = f;
        updateCommonArray();
    }

    public final void setCornerRadius(float f) {
        this.cornerRadius = f;
        this.commonArray[6] = f;
        updateCommonArray();
    }

    public final void setFarAlpha(float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        this.farAlpha = fCoerceIn;
        this.edgeArray[8] = fCoerceIn;
        updateEdgeArray();
    }

    public final void setFarFadeStart(float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        this.farFadeStart = fCoerceIn;
        this.edgeArray[11] = fCoerceIn;
        updateEdgeArray();
    }

    public final void setFarFadeWidth(float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        this.farFadeWidth = fCoerceIn;
        this.edgeArray[12] = fCoerceIn;
        updateEdgeArray();
    }

    public final void setFarStrokeStart(float f) {
        this.farStrokeStart = f;
        this.edgeArray[5] = f;
        updateEdgeArray();
    }

    public final void setFarStrokeWidth(float f) {
        this.farStrokeWidth = f;
        this.edgeArray[6] = f;
        updateEdgeArray();
    }

    public final void setNearAlpha(float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        this.nearAlpha = fCoerceIn;
        this.edgeArray[7] = fCoerceIn;
        updateEdgeArray();
    }

    public final void setNearFadeStart(float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        this.nearFadeStart = fCoerceIn;
        this.edgeArray[9] = fCoerceIn;
        updateEdgeArray();
    }

    public final void setNearFadeWidth(float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        this.nearFadeWidth = fCoerceIn;
        this.edgeArray[10] = fCoerceIn;
        updateEdgeArray();
    }

    public final void setNearStrokeStart(float f) {
        this.nearStrokeStart = f;
        this.edgeArray[3] = f;
        updateEdgeArray();
    }

    public final void setNearStrokeWidth(float f) {
        this.nearStrokeWidth = f;
        this.edgeArray[4] = f;
        updateEdgeArray();
    }

    public final void setOffset(float f, float f2) {
        float[] fArr = this.commonArray;
        fArr[2] = f;
        fArr[3] = f2;
        updateCommonArray();
    }

    public final void setResolution(float f, float f2) {
        float[] fArr = this.commonArray;
        fArr[0] = f;
        fArr[1] = f2;
        updateCommonArray();
    }

    public final void setSize(float f, float f2) {
        float[] fArr = this.commonArray;
        fArr[4] = f;
        fArr[5] = f2;
        updateCommonArray();
    }

    public final void setStrokeColor(int i) {
        this.strokeColor = i;
        this.edgeArray[0] = Color.red(i) / 255.0f;
        this.edgeArray[1] = Color.green(i) / 255.0f;
        this.edgeArray[2] = Color.blue(i) / 255.0f;
        updateEdgeArray();
    }

    public final void setStrokePower(float f) {
        this.strokePower = f;
        this.edgeArray[14] = f;
        updateEdgeArray();
    }

    public final void setStrokePowerMix(float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        this.strokePowerMix = fCoerceIn;
        this.edgeArray[15] = fCoerceIn;
        updateEdgeArray();
    }

    public final void setWeight(float f) {
        this.weight = f;
        this.commonArray[7] = f;
        updateCommonArray();
    }

    public final void updateCommonArray() {
        setFloatUniform(COMMON_ARRAY, this.commonArray);
    }

    public final void updateEdgeArray() {
        setFloatUniform(EDGE_ARRAY, this.edgeArray);
    }
}
