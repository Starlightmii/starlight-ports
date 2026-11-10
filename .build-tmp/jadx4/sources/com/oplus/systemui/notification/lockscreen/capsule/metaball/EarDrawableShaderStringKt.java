package com.oplus.systemui.notification.lockscreen.capsule.metaball;

import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class EarDrawableShaderStringKt {
    public static final String EAR_BASE_METHOD = "\nvec2 rotate2d_Ear(vec2 uv,float angle){\n    float angle_r=mod(angle,6.28);\n    mat2 mat=mat2(cos(angle_r),-sin(angle_r),sin(angle_r),cos(angle_r));\n    return(mat*uv);\n}\nfloat gain_Ear( float x, float k ) \n{\n    float a = 0.5*pow(2.0*((x<0.5)?x:1.0-x), k);\n    return (x<0.5)?a:1.0-a;\n}\n";
    public static final String EAR_DRAWABLE_SHADER_STRING_HEAD_UNIFORMS = "\nuniform float u_earParams[20];\n\n";
    public static final String EAR_DRAWABLE_SHADER_UNIFORM_ARC_ANGLE = "arcAngle";
    public static final String EAR_DRAWABLE_SHADER_UNIFORM_ARC_POINT0 = "arcPoint0";
    public static final String EAR_DRAWABLE_SHADER_UNIFORM_ARC_POINT1 = "arcPoint1";
    public static final String EAR_DRAWABLE_SHADER_UNIFORM_ARC_POINT2 = "arcPoint2";
    public static final String EAR_DRAWABLE_SHADER_UNIFORM_ARC_POS = "arcPos";
    public static final String EAR_DRAWABLE_SHADER_UNIFORM_EAR_PARAMS = "u_earParams";
    public static final String EAR_DRAWABLE_SHADER_UNIFORM_PRE_CALC_IS_FC = "preCalcIsFC";
    public static final String EAR_DRAWABLE_SHADER_UNIFORM_PROCESS = "process";
    public static final String EAR_DRAWABLE_SHADER_UNIFORM_PROCESS_POINT = "processPoint";
    public static final String EAR_DRAWABLE_SHADER_UNIFORM_SPEED_GAIN = "speedGain";
    public static final String EAR_DRAWABLE_SHADER_UNIFORM_WEIGHT = "weight";
    public static final String EAR_MAIN_METHOD_FOR_META_BALL = "\n\nfloat clip_shape = 1.0;\nif(enable_clip_shape != 0) {\n    float sdf = 0.0;\n    float process = u_earParams[0];\n    vec2 processPoint = vec2(u_earParams[1], u_earParams[2]);\n    vec3 speedGain = vec3(u_earParams[3], u_earParams[4], u_earParams[5]);\n    float weight = u_earParams[6];\n    float preCalcIsFC = u_earParams[7];\n    float arcAngle = u_earParams[8];\n    vec3 arcPoint0 = vec3(u_earParams[9], u_earParams[10], u_earParams[11]);\n    vec3 arcPoint1 = vec3(u_earParams[12], u_earParams[13], u_earParams[14]);\n    vec3 arcPoint2 = vec3(u_earParams[15], u_earParams[16], u_earParams[17]);\n    vec2 arcPos = vec2(u_earParams[18], u_earParams[19]);\n    \n    if(process<=processPoint.y){\n        vec2 u_resolution = u_size;\n\t\tvec2 pos = coords.xy - 0.5*u_resolution;\n        pos -= arcPos;\n        vec3 arcPoint = vec3(0.0);\n        if(process<processPoint.x){\n            float rate = 0.0;\n            if(process> 0.001){\n                rate = smoothstep(0.0, processPoint.x, process);\n            }\n            rate = gain_Ear(rate, speedGain.x);\n            arcPoint = mix(arcPoint0, arcPoint1, vec3(rate));\n        }\n        else{\n            float rate = 0.0;\n            if(process>processPoint.x+0.001 ){\n                rate = smoothstep(processPoint.x, processPoint.y, process);\n            }\n            rate = gain_Ear(rate, speedGain.y);\n            arcPoint = mix(arcPoint1, arcPoint2, vec3(rate));\n        }\n        float arcRadius = arcPoint.x;\n        float arcWidth = arcPoint.y;\n        float arcAngleRange = arcPoint.z;\n\n        float rotateAngle = arcAngle - 1.57;\n        pos += (arcRadius+arcWidth)*vec2(cos(arcAngle), sin(arcAngle));\n        pos = rotate2d_Ear(pos, rotateAngle);\n        float arcA = 1.57-arcAngleRange;\n        vec2 angle = vec2(cos(arcA), sin(arcA));\n        sdf = sdArcSmooth_Ear(pos, angle, arcRadius, arcWidth, weight, preCalcIsFC);\n    }\n\tclip_shape = smoothstep( 0.0, -3.0, sdf);\n}\n    outputCol *= clip_shape;\n\n";
    public static final String EAR_METHOD_CUBIC_BEZIER_DISTANCE_EAR = "\nfloat cubicBezierDistance_Ear(vec2 p, vec2 p0, vec2 p1, vec2 p2, vec2 p3) {\n    vec2 c0 = p0;\n    vec2 c1 = 3.0 * (p1 - p0);\n    vec2 c2 = 3.0 * (p2 - 2.0 * p1 + p0);\n    vec2 c3 = p3 - 3.0 * p2 + 3.0 * p1 - p0;\n    float t = 0.5;\n    for (int i = 0; i < 10; i++) {\n        vec2 Bt   = ((c3 * t + c2) * t + c1) * t + c0;\n        vec2 dBt  = (3.0 * c3 * t + 2.0 * c2) * t + c1;\n        vec2 ddBt = 6.0 * c3 * t + 2.0 * c2;\n        vec2 r    = Bt - p;\n        float f1 = dot(dBt, r);\n        float f2 = dot(ddBt, r) + dot(dBt, dBt);\n        float denom = max(f2, 1e-4);\n        t = clamp(t - f1 / denom, 0.0, 1.0);\n    }\n    vec2 Bt = ((c3 * t + c2) * t + c1) * t + c0;\n    vec2 re = Bt - p;\n    vec2 tangent = (3.0 * c3 * t + 2.0 * c2) * t + c1;\n    vec2 normal = vec2(-tangent.y, tangent.x);\n    vec2 normalizedNormal = normalize(normal);\n    float dis = length(re);\n    float signedDistance = dot(re, normalizedNormal) > 0.0 ? dis : -dis;\n    return signedDistance;\n}\n";
    public static final String EAR_METHOD_RBOX_CORNER_EAR = "\nfloat RBoxConer_Ear(vec2 p,vec2 b, float r){\n    vec2 q=abs(p)-b+r;\n    return min(max(q.x,q.y),0.)+length(max(q,0.))-r;\n}\n";
    public static final String EAR_METHOD_SD_ARC_SMOOTH = "\nfloat sdArcSmooth_Ear( in vec2 p, in vec2 sc, in float ra, float rb ,float weight, float isFc)\n{\n    p.x = abs(p.x);\n    if(sc.y*p.x>sc.x*p.y){\n        vec2 pos = p-sc*ra;\n        mat2 mat=mat2(sc.x,sc.y,-sc.y,sc.x);\n        pos*= mat;\n        float sdf = sdBezierDistance_Ear(pos, vec2(rb), rb, weight, isFc);\n        return sdf;\n    }\n    return abs(length(p)-ra) - rb;\n}\n";
    public static final String EAR_METHOD_SD_BEZIER_DISTANCE_EAR = "\nfloat sdBezierDistance_Ear(vec2 p, vec2 b, float coner, float weight, float isFC) {\n    float sdf = 0.0;\n    float cc = coner;\n\n    if(isFC > 0.5 || any(lessThanEqual(abs(p), b - cc))) {\n        sdf =  RBoxConer_Ear(p, b, cc);\n    }else{\n        float point  = max(cc*weight*0.5,0.001);\n        sdf = cubicBezierDistance_Ear(abs(p)-b+cc, \n                                        vec2(cc, 0.0), \n                                        vec2(cc, point), \n                                        vec2(point, cc),\n                                        vec2(0.0, cc));\n    }\n    return sdf;\n}\n";

    public static final float[] buildEarParamsArray(EarParams earParams) {
        if (earParams.getProcessPoint().length != 2) {
            Path$.ExternalSyntheticBUOutline0.m("processPoint requires exactly 2 elements");
            return null;
        }
        if (earParams.getSpeedGain().length != 3) {
            Path$.ExternalSyntheticBUOutline0.m("speedGain requires exactly 3 elements");
            return null;
        }
        if (earParams.getArcPoint0().length != 3) {
            Path$.ExternalSyntheticBUOutline0.m("arcPoint0 requires exactly 3 elements");
            return null;
        }
        if (earParams.getArcPoint1().length != 3) {
            Path$.ExternalSyntheticBUOutline0.m("arcPoint1 requires exactly 3 elements");
            return null;
        }
        if (earParams.getArcPoint2().length != 3) {
            Path$.ExternalSyntheticBUOutline0.m("arcPoint2 requires exactly 3 elements");
            return null;
        }
        if (earParams.getArcPos().length == 2) {
            return new float[]{earParams.getProcess(), earParams.getProcessPoint()[0], earParams.getProcessPoint()[1], earParams.getSpeedGain()[0], earParams.getSpeedGain()[1], earParams.getSpeedGain()[2], earParams.getWeight(), earParams.getPreCalcIsFC(), earParams.getArcAngle(), earParams.getArcPoint0()[0], earParams.getArcPoint0()[1], earParams.getArcPoint0()[2], earParams.getArcPoint1()[0], earParams.getArcPoint1()[1], earParams.getArcPoint1()[2], earParams.getArcPoint2()[0], earParams.getArcPoint2()[1], earParams.getArcPoint2()[2], earParams.getArcPos()[0], earParams.getArcPos()[1]};
        }
        Path$.ExternalSyntheticBUOutline0.m("arcPos requires exactly 2 elements");
        return null;
    }

    public static final String getEarShaderHead() {
        return "uniform float u_earParams[20];\n";
    }

    public static final String getEarShaderMainMethod() {
        return EAR_MAIN_METHOD_FOR_META_BALL;
    }

    public static final String getEarShaderMethod() {
        return "vec2 rotate2d_Ear(vec2 uv,float angle){\n    float angle_r=mod(angle,6.28);\n    mat2 mat=mat2(cos(angle_r),-sin(angle_r),sin(angle_r),cos(angle_r));\n    return(mat*uv);\n}\nfloat gain_Ear( float x, float k ) \n{\n    float a = 0.5*pow(2.0*((x<0.5)?x:1.0-x), k);\n    return (x<0.5)?a:1.0-a;\n}float RBoxConer_Ear(vec2 p,vec2 b, float r){\n    vec2 q=abs(p)-b+r;\n    return min(max(q.x,q.y),0.)+length(max(q,0.))-r;\n}float cubicBezierDistance_Ear(vec2 p, vec2 p0, vec2 p1, vec2 p2, vec2 p3) {\n    vec2 c0 = p0;\n    vec2 c1 = 3.0 * (p1 - p0);\n    vec2 c2 = 3.0 * (p2 - 2.0 * p1 + p0);\n    vec2 c3 = p3 - 3.0 * p2 + 3.0 * p1 - p0;\n    float t = 0.5;\n    for (int i = 0; i < 10; i++) {\n        vec2 Bt   = ((c3 * t + c2) * t + c1) * t + c0;\n        vec2 dBt  = (3.0 * c3 * t + 2.0 * c2) * t + c1;\n        vec2 ddBt = 6.0 * c3 * t + 2.0 * c2;\n        vec2 r    = Bt - p;\n        float f1 = dot(dBt, r);\n        float f2 = dot(ddBt, r) + dot(dBt, dBt);\n        float denom = max(f2, 1e-4);\n        t = clamp(t - f1 / denom, 0.0, 1.0);\n    }\n    vec2 Bt = ((c3 * t + c2) * t + c1) * t + c0;\n    vec2 re = Bt - p;\n    vec2 tangent = (3.0 * c3 * t + 2.0 * c2) * t + c1;\n    vec2 normal = vec2(-tangent.y, tangent.x);\n    vec2 normalizedNormal = normalize(normal);\n    float dis = length(re);\n    float signedDistance = dot(re, normalizedNormal) > 0.0 ? dis : -dis;\n    return signedDistance;\n}float sdBezierDistance_Ear(vec2 p, vec2 b, float coner, float weight, float isFC) {\n    float sdf = 0.0;\n    float cc = coner;\n\n    if(isFC > 0.5 || any(lessThanEqual(abs(p), b - cc))) {\n        sdf =  RBoxConer_Ear(p, b, cc);\n    }else{\n        float point  = max(cc*weight*0.5,0.001);\n        sdf = cubicBezierDistance_Ear(abs(p)-b+cc, \n                                        vec2(cc, 0.0), \n                                        vec2(cc, point), \n                                        vec2(point, cc),\n                                        vec2(0.0, cc));\n    }\n    return sdf;\n}float sdArcSmooth_Ear( in vec2 p, in vec2 sc, in float ra, float rb ,float weight, float isFc)\n{\n    p.x = abs(p.x);\n    if(sc.y*p.x>sc.x*p.y){\n        vec2 pos = p-sc*ra;\n        mat2 mat=mat2(sc.x,sc.y,-sc.y,sc.x);\n        pos*= mat;\n        float sdf = sdBezierDistance_Ear(pos, vec2(rb), rb, weight, isFc);\n        return sdf;\n    }\n    return abs(length(p)-ra) - rb;\n}";
    }
}
