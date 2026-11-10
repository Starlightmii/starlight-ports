package com.oplusos.systemui.common.olivelink;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.FileProvider$;
import androidx.activity.result.ActivityResultRegistry$;
import com.innopeaktech.olivelink.server_1_18.OliveLink;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OliveLinkConnector {
    public static final String SEP_QS_SERVER_NAME = "separate_qs";
    private static final String TAG = "OliveLinkConnector";
    public static final boolean isOliveAvailable;
    public static final OliveLinkConnector INSTANCE = new OliveLinkConnector();
    private static final Map<String, OliveLink.Server> serverMap = new LinkedHashMap();

    /* JADX INFO: renamed from: com.oplusos.systemui.common.olivelink.OliveLinkConnector$color$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements AndroidColor {
        public /* synthetic */ Color $initialValue;

        @Override // com.oplusos.systemui.common.olivelink.AndroidColor
        public Color get() {
            return this.$initialValue;
        }

        @Override // com.oplusos.systemui.common.olivelink.AndroidColor
        public final void registerChangeListener(Object obj, Function0 function0) {
        }

        @Override // com.oplusos.systemui.common.olivelink.AndroidColor
        public void set(Color color) {
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.olivelink.OliveLinkConnector$condition$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C03201 implements Condition {
        public /* synthetic */ boolean $initialValue;
        public Function0 listener;
        public Boolean value;

        @Override // com.oplusos.systemui.common.olivelink.Condition
        public boolean get() {
            Boolean bool = this.value;
            return bool != null ? bool.booleanValue() : this.$initialValue;
        }

        @Override // com.oplusos.systemui.common.olivelink.Condition
        public final void registerChangeListener(Object obj, Function0 function0) {
            this.listener = function0;
        }

        @Override // com.oplusos.systemui.common.olivelink.Condition
        public void set(boolean z) {
            if (Intrinsics.areEqual(this.value, Boolean.valueOf(z))) {
                return;
            }
            this.value = Boolean.valueOf(z);
            Function0 function0 = this.listener;
            if (function0 != null) {
                function0.invoke();
            }
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.olivelink.OliveLinkConnector$safeCubicBezier$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C03211 implements CubicBezier {
        public /* synthetic */ float $x1;
        public /* synthetic */ float $x2;
        public /* synthetic */ float $y1;
        public /* synthetic */ float $y2;

        @Override // com.oplusos.systemui.common.olivelink.CubicBezier
        public final float x1() {
            return this.$x1;
        }

        @Override // com.oplusos.systemui.common.olivelink.CubicBezier
        public final float x2() {
            return this.$x2;
        }

        @Override // com.oplusos.systemui.common.olivelink.CubicBezier
        public final float y1() {
            return this.$y1;
        }

        @Override // com.oplusos.systemui.common.olivelink.CubicBezier
        public final float y2() {
            return this.$y2;
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.olivelink.OliveLinkConnector$safeScalar$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C03221 implements Scalar {
        public /* synthetic */ Number $initialValue;
        public Function0 listener;
        public Number value;

        @Override // com.oplusos.systemui.common.olivelink.Scalar
        public Number get() {
            Number number = this.value;
            return number == null ? this.$initialValue : number;
        }

        @Override // com.oplusos.systemui.common.olivelink.Scalar
        public final void registerChangeListener(Object obj, Function0 function0) {
            this.listener = function0;
        }

        @Override // com.oplusos.systemui.common.olivelink.Scalar
        public void set(Number number) {
            if (Intrinsics.areEqual(number, this.value)) {
                return;
            }
            this.value = number;
            Function0 function0 = this.listener;
            if (function0 != null) {
                function0.invoke();
            }
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.olivelink.OliveLinkConnector$safeSpring$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C03231 implements Spring {
        public /* synthetic */ float $bounce;
        public /* synthetic */ float $dampingRatio;
        public /* synthetic */ float $response;
        public /* synthetic */ SpringType $springType;
        public /* synthetic */ float $stiffness;
        public /* synthetic */ float $velocity;

        @Override // com.oplusos.systemui.common.olivelink.Spring
        public final float bounce() {
            return this.$bounce;
        }

        @Override // com.oplusos.systemui.common.olivelink.Spring
        public final float dampingRatio() {
            return this.$dampingRatio;
        }

        @Override // com.oplusos.systemui.common.olivelink.Spring
        public final float response() {
            return this.$response;
        }

        @Override // com.oplusos.systemui.common.olivelink.Spring
        public final SpringType springType() {
            return this.$springType;
        }

        @Override // com.oplusos.systemui.common.olivelink.Spring
        public final float stiffness() {
            return this.$stiffness;
        }

        @Override // com.oplusos.systemui.common.olivelink.Spring
        public final float velocity() {
            return this.$velocity;
        }
    }

    static {
        boolean z;
        try {
            Class.forName("com.innopeaktech.olivelink.server_1_18.OliveLink");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        isOliveAvailable = z;
    }

    public static /* synthetic */ Scalar safeScalar$default(OliveLinkConnector oliveLinkConnector, String str, String str2, String str3, Number number, int i) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        return oliveLinkConnector.safeScalar(str, str2, str3, number, (i & 16) == 0 ? "删除开关时延" : "");
    }

    public static /* synthetic */ Spring safeSpring$default(OliveLinkConnector oliveLinkConnector, String str, String str2, float f, float f2, float f3, float f4, float f5, SpringType springType, String str3, String str4, int i) {
        if ((i & 4) != 0) {
            f = 0.0f;
        }
        if ((i & 8) != 0) {
            f2 = 0.0f;
        }
        if ((i & 16) != 0) {
            f3 = 0.0f;
        }
        if ((i & 32) != 0) {
            f4 = 0.0f;
        }
        if ((i & 64) != 0) {
            f5 = 0.0f;
        }
        if ((i & 128) != 0) {
            springType = SpringType.SPRING_ANIMATION;
        }
        if ((i & 256) != 0) {
            str3 = "";
        }
        if ((i & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0) {
            str4 = "";
        }
        return oliveLinkConnector.safeSpring(str, str2, f, f2, f3, f4, f5, springType, str3, str4);
    }

    public final <T extends Color> AndroidColor color(String str, String str2, String str3, T t, String str4) {
        OliveLink.Color color;
        OliveLink.Color color2;
        if (!isOliveAvailable) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.$initialValue = t;
            VarHandle.storeStoreFence();
            return anonymousClass1;
        }
        OliveLink.Server server = serverMap.get(str);
        if (server == null || (color2 = server.color(str2, t, str4, str3)) == null) {
            color = color2;
            OliveLinkConnector$color$oliveColor$1 oliveLinkConnector$color$oliveColor$1 = new OliveLinkConnector$color$oliveColor$1();
            oliveLinkConnector$color$oliveColor$1.$initialValue = t;
            oliveLinkConnector$color$oliveColor$1.$name = str2;
            VarHandle.storeStoreFence();
            color = oliveLinkConnector$color$oliveColor$1;
        }
        color = color2;
        return new OliveLinkColorAdapter(color);
    }

    public final Condition condition(String str, String str2, String str3, boolean z, String str4) {
        OliveLink.Condition condition;
        OliveLink.Condition condition2;
        if (!isOliveAvailable) {
            C03201 c03201 = new C03201();
            c03201.$initialValue = z;
            VarHandle.storeStoreFence();
            return c03201;
        }
        OliveLink.Server server = serverMap.get(str);
        if (server == null || (condition2 = server.condition(str2, z, str4, str3)) == null) {
            condition = condition2;
            OliveLinkConnector$condition$oliveCondition$1 oliveLinkConnector$condition$oliveCondition$1 = new OliveLinkConnector$condition$oliveCondition$1();
            oliveLinkConnector$condition$oliveCondition$1.$initialValue = z;
            oliveLinkConnector$condition$oliveCondition$1.name = str2;
            VarHandle.storeStoreFence();
            condition = oliveLinkConnector$condition$oliveCondition$1;
        }
        condition = condition2;
        return new OliveLinkConditionAdapter(condition);
    }

    public final CubicBezier safeCubicBezier(String str, String str2, float f, float f2, float f3, float f4, String str3, String str4) {
        OliveLink.CubicBezier cubicBezier;
        OliveLink.CubicBezier cubicBezierBezier;
        if (!isOliveAvailable) {
            C03211 c03211 = new C03211();
            c03211.$x1 = f;
            c03211.$x2 = f3;
            c03211.$y1 = f2;
            c03211.$y2 = f4;
            VarHandle.storeStoreFence();
            return c03211;
        }
        OliveLink.Server server = serverMap.get(str);
        if (server == null || (cubicBezierBezier = server.bezier(str2, f, f2, f3, f4, str4, str3)) == null) {
            cubicBezier = cubicBezierBezier;
            OliveLinkConnector$safeCubicBezier$oliveCubicBezier$1 oliveLinkConnector$safeCubicBezier$oliveCubicBezier$1 = new OliveLinkConnector$safeCubicBezier$oliveCubicBezier$1();
            oliveLinkConnector$safeCubicBezier$oliveCubicBezier$1.$x1 = f;
            oliveLinkConnector$safeCubicBezier$oliveCubicBezier$1.$y1 = f2;
            oliveLinkConnector$safeCubicBezier$oliveCubicBezier$1.$x2 = f3;
            oliveLinkConnector$safeCubicBezier$oliveCubicBezier$1.$y2 = f4;
            oliveLinkConnector$safeCubicBezier$oliveCubicBezier$1.name = str2;
            VarHandle.storeStoreFence();
            cubicBezier = oliveLinkConnector$safeCubicBezier$oliveCubicBezier$1;
        }
        cubicBezier = cubicBezierBezier;
        return new OliveLinkBezierAdapter(cubicBezier);
    }

    public final <T extends Number> Scalar safeScalar(String str, String str2, String str3, T t, String str4) {
        OliveLink.Scalar scalar;
        OliveLink.Scalar scalar2;
        if (!isOliveAvailable) {
            C03221 c03221 = new C03221();
            c03221.$initialValue = t;
            VarHandle.storeStoreFence();
            return c03221;
        }
        OliveLink.Server server = serverMap.get(str);
        if (server == null || (scalar2 = server.scalar(str2, t, str4, str3)) == null) {
            scalar = scalar2;
            OliveLinkConnector$safeScalar$oliveScalar$1 oliveLinkConnector$safeScalar$oliveScalar$1 = new OliveLinkConnector$safeScalar$oliveScalar$1();
            oliveLinkConnector$safeScalar$oliveScalar$1.$initialValue = t;
            oliveLinkConnector$safeScalar$oliveScalar$1.name = str2;
            VarHandle.storeStoreFence();
            scalar = oliveLinkConnector$safeScalar$oliveScalar$1;
        }
        scalar = scalar2;
        return new OliveLinkScalarAdapter(scalar);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0061  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Spring safeSpring(String str, String str2, float f, float f2, float f3, float f4, float f5, SpringType springType, String str3, String str4) {
        OliveLink.SpringType springType2;
        float f6;
        float f7;
        float f8;
        OliveLink.Spring spring;
        OliveLinkConnector$safeSpring$oliveSpring$1 oliveLinkConnector$safeSpring$oliveSpring$1;
        if (!isOliveAvailable) {
            C03231 c03231 = new C03231();
            c03231.$bounce = f;
            c03231.$response = f2;
            c03231.$dampingRatio = f5;
            c03231.$stiffness = f4;
            c03231.$velocity = f3;
            c03231.$springType = springType;
            VarHandle.storeStoreFence();
            return c03231;
        }
        int iOrdinal = springType.ordinal();
        OliveLink.Spring spring2 = null;
        spring2 = null;
        if (iOrdinal == 0) {
            springType2 = OliveLink.SpringType.SPRING_ANIMATION;
        } else {
            if (iOrdinal != 1) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return null;
            }
            springType2 = OliveLink.SpringType.SPRING_INTERPOLATOR;
        }
        OliveLink.SpringType springType3 = springType2;
        if (springType != SpringType.SPRING_INTERPOLATOR) {
            f6 = f4;
            f7 = f5;
            OliveLink.Server server = serverMap.get(str);
            if (server != null) {
                f8 = f3;
                spring = server.spring(str2, f, f2, f8, springType3, str3, str4);
            }
            if (oliveLinkConnector$safeSpring$oliveSpring$1 == 0) {
                oliveLinkConnector$safeSpring$oliveSpring$1 = spring;
                oliveLinkConnector$safeSpring$oliveSpring$1 = new OliveLinkConnector$safeSpring$oliveSpring$1();
                oliveLinkConnector$safeSpring$oliveSpring$1.$bounce = f;
                oliveLinkConnector$safeSpring$oliveSpring$1.$response = f2;
                oliveLinkConnector$safeSpring$oliveSpring$1.$dampingRatio = f7;
                oliveLinkConnector$safeSpring$oliveSpring$1.$stiffness = f6;
                oliveLinkConnector$safeSpring$oliveSpring$1.$velocity = f8;
                oliveLinkConnector$safeSpring$oliveSpring$1.$oliveSpringType = springType3;
                oliveLinkConnector$safeSpring$oliveSpring$1.name = str2;
                VarHandle.storeStoreFence();
            }
            oliveLinkConnector$safeSpring$oliveSpring$1 = spring;
            return new OliveLinkSpringAdapter(oliveLinkConnector$safeSpring$oliveSpring$1);
        }
        OliveLink.Server server2 = serverMap.get(str);
        if (server2 != null) {
            OliveLink.Spring spring3 = server2.spring(str2, f5, f4, springType3, str3, str4);
            f7 = f5;
            f6 = f4;
            springType3 = springType3;
            spring2 = spring3;
        } else {
            f6 = f4;
            f7 = f5;
        }
        f8 = f3;
        oliveLinkConnector$safeSpring$oliveSpring$1 = spring2;
        if (oliveLinkConnector$safeSpring$oliveSpring$1 == 0) {
            oliveLinkConnector$safeSpring$oliveSpring$1 = spring;
            oliveLinkConnector$safeSpring$oliveSpring$1 = new OliveLinkConnector$safeSpring$oliveSpring$1();
            oliveLinkConnector$safeSpring$oliveSpring$1.$bounce = f;
            oliveLinkConnector$safeSpring$oliveSpring$1.$response = f2;
            oliveLinkConnector$safeSpring$oliveSpring$1.$dampingRatio = f7;
            oliveLinkConnector$safeSpring$oliveSpring$1.$stiffness = f6;
            oliveLinkConnector$safeSpring$oliveSpring$1.$velocity = f8;
            oliveLinkConnector$safeSpring$oliveSpring$1.$oliveSpringType = springType3;
            oliveLinkConnector$safeSpring$oliveSpring$1.name = str2;
            VarHandle.storeStoreFence();
        }
        oliveLinkConnector$safeSpring$oliveSpring$1 = spring;
        return new OliveLinkSpringAdapter(oliveLinkConnector$safeSpring$oliveSpring$1);
    }

    public final void start(Context context, String str) {
        if (isOliveAvailable) {
            StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("[start] for ", str, ", serverMap=");
            Map<String, OliveLink.Server> map = serverMap;
            sbM.append(map);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
            if (map.get(str) == null) {
                OliveLink.Server serverCreateServer$default = OliveLink.Companion.createServer$default(OliveLink.Companion, "1.18.1", context, str, (String) null, 8, (Object) null);
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "[start] for " + str + ", result=" + serverCreateServer$default, null, 8);
                map.put(str, serverCreateServer$default);
            }
        }
    }

    public final void stop(String str) {
        if (isOliveAvailable) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("[stop] for ", str), null, 8);
            OliveLink.Server serverRemove = serverMap.remove(str);
            if (serverRemove != null) {
                serverRemove.discard();
            }
        }
    }
}
