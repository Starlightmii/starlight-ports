package com.oplus.systemui.notification.lockscreen.effect;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.android.systemui.statusbar.notification.OplusPhysicsInterpolator;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.olivelink.OLiveLinkBaseParams;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ExtStateAnimSinkingParams {
    public static final ExtStateAnimSinkingParams INSTANCE = new ExtStateAnimSinkingParams();
    private static final String OLIVE_LINK_SERVER_NAME = "下沉态动画";
    private static final String TAG = "ExtStateAnimSinkingParams";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleToSinkingParams extends SinkingLevelParam {
        public static final CapsuleToSinkingParams INSTANCE = new CapsuleToSinkingParams(1, "胶囊切下沉堆叠参数(最上面卡为基准卡,先动)", new InitValue(new ExtStateAnimParams.SpringParams(0.15f, 0.35f), new ExtStateAnimParams.SpringParams(0.35f, 0.45f), 31));
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleToStackParams extends SinkingLevelParam {
        public static final CapsuleToStackParams INSTANCE = new CapsuleToStackParams(5, "胶囊切列表堆叠参数(最上面卡为基准卡,先动)", new InitValue(new ExtStateAnimParams.SpringParams(0.16f, 0.35f), new ExtStateAnimParams.SpringParams(0.16f, 0.45f), 31));
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DefaultTYLevelParams extends TransYLevelParam {
        public static final DefaultTYLevelParams INSTANCE = new DefaultTYLevelParams(0, "Y轴位移默认分层参数(最上面为基准卡)", new ExtStateAnimParams.SpringParams(0.11f, 0.35f), new ExtStateAnimParams.SpringParams(0.35f, 0.45f));
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class GroupNotificationLevelParams extends TransYLevelParam {
        public static final GroupNotificationLevelParams INSTANCE = new GroupNotificationLevelParams(8, "组通知展开收起分层参数(最上面为基准卡)", new ExtStateAnimParams.SpringParams(0.015f, 0.35f), new ExtStateAnimParams.SpringParams(0.035f, 0.45f));
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class LevelParam extends OLiveLinkBaseParams.OLiveLinkGroupEx {
        public LevelParam(int i, String str) {
            super(i, str, ExtStateAnimSinkingParams.OLIVE_LINK_SERVER_NAME);
        }

        public static OplusPhysicsInterpolator midParam(Spring spring, Spring spring2) {
            return new OplusPhysicsInterpolator(RangesKt___RangesKt.coerceIn((spring2.bounce() + spring.bounce()) / 2.0f, 0.0f, 0.99f), RangesKt___RangesKt.coerceIn((spring2.response() + spring.response()) / 2.0f, 0.01f, 1.0f), (Float) null);
        }

        public final OplusPhysicsInterpolator getSpringLevelInterpolator(Spring spring, Spring spring2, int i, int i2, boolean z) {
            if (i < 0) {
                return toInterpolator(spring);
            }
            if (i2 > 1) {
                CapsuleConfig.INSTANCE.getClass();
                if (CapsuleConfig.isMiddleOrHighAnimLevel) {
                    if (i2 == 2) {
                        if (i == 0) {
                            return !z ? toInterpolator(spring) : midParam(spring, spring2);
                        }
                        if (i == 1) {
                            return z ? toInterpolator(spring) : midParam(spring, spring2);
                        }
                    }
                    if (z) {
                        i = (i2 - 1) - i;
                    }
                    float f = i2 - 1;
                    float f2 = i;
                    return new OplusPhysicsInterpolator(RangesKt___RangesKt.coerceIn((((spring2.bounce() - spring.bounce()) / f) * f2) + spring.bounce(), 0.0f, 0.99f), RangesKt___RangesKt.coerceIn((((spring2.response() - spring.response()) / f) * f2) + spring.response(), 0.01f, 1.0f), (Float) null);
                }
            }
            return toInterpolator(spring);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class SinkingLevelParam extends TransYLevelParam {
        private final Spring ALPHA;
        private final Spring BLUR;
        private final Spring CORNER_RADIUS;
        private final Spring HEIGHT;
        private final Scalar TIME;

        public SinkingLevelParam(int i, String str, InitValue initValue) {
            super(i, str, initValue.getTyBaseParam(), initValue.getTyFinalParam());
            this.TIME = toScalar(Long.valueOf(initValue.getDuration()), "插值器_时长");
            this.ALPHA = toScalar(initValue.getAlphaSpringParam(), "插值器_ALPHA");
            this.BLUR = toScalar(initValue.getBlurParam(), "插值器_模糊");
            this.CORNER_RADIUS = toScalar(initValue.getCornerRadiusParam(), "插值器_圆角");
            this.HEIGHT = toScalar(initValue.getHeightParam(), "插值器_高度变化");
        }

        public Spring getALPHA() {
            return this.ALPHA;
        }

        public Spring getBLUR() {
            return this.BLUR;
        }

        public Spring getCORNER_RADIUS() {
            return this.CORNER_RADIUS;
        }

        public Spring getHEIGHT() {
            return this.HEIGHT;
        }

        public Scalar getTIME() {
            return this.TIME;
        }

        public final OplusPhysicsInterpolator getWidthInterpolator(int i, int i2, boolean z) {
            return getYInterpolator(i, i2, z);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SinkingToCapsuleParams extends SinkingLevelParam {
        public static final SinkingToCapsuleParams INSTANCE = new SinkingToCapsuleParams(2, "下沉切胶囊堆叠参数(最下面卡为基准卡，先动)", new InitValue(new ExtStateAnimParams.SpringParams(0.15f, 0.35f), new ExtStateAnimParams.SpringParams(0.25f, 0.45f), 30));
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SinkingToStackParams extends SinkingLevelParam {
        public static final SinkingToStackParams INSTANCE;
        private static final Scalar staticStackedMaxScale;
        private static final Scalar staticStackedMinScale;

        static {
            SinkingToStackParams sinkingToStackParams = new SinkingToStackParams(4, "下沉切列表堆叠参数(最上面卡为基准卡,先动)", new InitValue(null, new ExtStateAnimParams.SpringParams(0.35f, 0.55f), 63));
            INSTANCE = sinkingToStackParams;
            staticStackedMinScale = sinkingToStackParams.safeScalar("堆叠摘要最小缩放", Float.valueOf(0.5f));
            staticStackedMaxScale = sinkingToStackParams.safeScalar("堆叠摘要最大缩放", Float.valueOf(1.0f));
        }

        public final Scalar getStaticStackedMaxScale() {
            return staticStackedMaxScale;
        }

        public final Scalar getStaticStackedMinScale() {
            return staticStackedMinScale;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StackToCapsuleParams extends SinkingLevelParam {
        public static final StackToCapsuleParams INSTANCE = new StackToCapsuleParams(6, "列表切胶囊堆叠参数(最上面卡为基准卡,先动)", new InitValue(new ExtStateAnimParams.SpringParams(0.16f, 0.35f), new ExtStateAnimParams.SpringParams(0.16f, 0.45f), 31));
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StackToNtfCenterTYLevelParams extends TransYLevelParam {
        public static final StackToNtfCenterTYLevelParams INSTANCE = new StackToNtfCenterTYLevelParams(7, "列表↔锁屏通知中心Y轴分层(进出共用,正逆序由调用方决定)", new ExtStateAnimParams.SpringParams(0.15f, 0.35f), new ExtStateAnimParams.SpringParams(0.35f, 0.45f));
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StackToSinkingParams extends SinkingLevelParam {
        public static final StackToSinkingParams INSTANCE = new StackToSinkingParams(3, "列表切下沉堆叠参数(最下面卡为基准卡,先动)", new InitValue(null, new ExtStateAnimParams.SpringParams(0.35f, 0.55f), 63));
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class TransYLevelParam extends LevelParam {
        private final Spring TRANSLATION_Y_BASE;
        private final Spring TRANSLATION_Y_FINAL;
        private final ExtStateAnimParams.SpringParams tyBaseParam;
        private final ExtStateAnimParams.SpringParams tyFinalParam;

        public TransYLevelParam(int i, String str, ExtStateAnimParams.SpringParams springParams, ExtStateAnimParams.SpringParams springParams2) {
            super(i, str);
            this.tyBaseParam = springParams;
            this.tyFinalParam = springParams2;
            this.TRANSLATION_Y_BASE = toScalar(springParams, "Y轴位移基准曲线");
            this.TRANSLATION_Y_FINAL = toScalar(springParams2, "Y轴位移最终曲线");
        }

        public Spring getTRANSLATION_Y_BASE() {
            return this.TRANSLATION_Y_BASE;
        }

        public Spring getTRANSLATION_Y_FINAL() {
            return this.TRANSLATION_Y_FINAL;
        }

        public final ExtStateAnimParams.SpringParams getTyBaseParam() {
            return this.tyBaseParam;
        }

        public final ExtStateAnimParams.SpringParams getTyFinalParam() {
            return this.tyFinalParam;
        }

        public final OplusPhysicsInterpolator getYInterpolator(int i, int i2, boolean z) {
            return getSpringLevelInterpolator(getTRANSLATION_Y_BASE(), getTRANSLATION_Y_FINAL(), i, i2, z);
        }
    }

    public final void initOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, OLIVE_LINK_SERVER_NAME);
        if (Build.IS_DEBUGGABLE) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ExtStateAnimSinkingParams-->".concat("DefaultTYLevelParams：" + DefaultTYLevelParams.INSTANCE));
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ExtStateAnimSinkingParams-->".concat("CapsuleToSinkingParams：" + CapsuleToSinkingParams.INSTANCE));
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ExtStateAnimSinkingParams-->".concat("StackToSinkingParams：" + StackToSinkingParams.INSTANCE));
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ExtStateAnimSinkingParams-->".concat("SinkingToStackParams：" + SinkingToStackParams.INSTANCE));
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ExtStateAnimSinkingParams-->".concat("CapsuleToStackParams：" + CapsuleToStackParams.INSTANCE));
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ExtStateAnimSinkingParams-->".concat("SinkingToStackParams：" + SinkingToStackParams.INSTANCE));
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ExtStateAnimSinkingParams-->".concat("StackToNtfCenterTYLevelParams：" + StackToNtfCenterTYLevelParams.INSTANCE));
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class InitValue {
        private final ExtStateAnimParams.SpringParams alphaSpringParam;
        private final ExtStateAnimParams.SpringParams blurParam;
        private final ExtStateAnimParams.SpringParams cornerRadiusParam;
        public final long duration;
        private final ExtStateAnimParams.SpringParams heightParam;
        private final ExtStateAnimParams.SpringParams tyBaseParam;
        private final ExtStateAnimParams.SpringParams tyFinalParam;

        public /* synthetic */ InitValue(ExtStateAnimParams.SpringParams springParams, ExtStateAnimParams.SpringParams springParams2, int i) {
            this(350L, new ExtStateAnimParams.SpringParams(0.1f, 0.3f), new ExtStateAnimParams.SpringParams(0.1f, 0.3f), new ExtStateAnimParams.SpringParams(0.0f, 0.35f), new ExtStateAnimParams.SpringParams(0.0f, 0.45f), (i & 32) != 0 ? new ExtStateAnimParams.SpringParams(0.15f, 0.35f) : springParams, springParams2);
        }

        public final ExtStateAnimParams.SpringParams component2() {
            return this.alphaSpringParam;
        }

        public final ExtStateAnimParams.SpringParams component3() {
            return this.blurParam;
        }

        public final ExtStateAnimParams.SpringParams component4() {
            return this.cornerRadiusParam;
        }

        public final ExtStateAnimParams.SpringParams component5() {
            return this.heightParam;
        }

        public final ExtStateAnimParams.SpringParams component6() {
            return this.tyBaseParam;
        }

        public final ExtStateAnimParams.SpringParams component7() {
            return this.tyFinalParam;
        }

        public final InitValue copy(long j, ExtStateAnimParams.SpringParams springParams, ExtStateAnimParams.SpringParams springParams2, ExtStateAnimParams.SpringParams springParams3, ExtStateAnimParams.SpringParams springParams4, ExtStateAnimParams.SpringParams springParams5, ExtStateAnimParams.SpringParams springParams6) {
            return new InitValue(j, springParams, springParams2, springParams3, springParams4, springParams5, springParams6);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof InitValue)) {
                return false;
            }
            InitValue initValue = (InitValue) obj;
            return this.duration == initValue.duration && Intrinsics.areEqual(this.alphaSpringParam, initValue.alphaSpringParam) && Intrinsics.areEqual(this.blurParam, initValue.blurParam) && Intrinsics.areEqual(this.cornerRadiusParam, initValue.cornerRadiusParam) && Intrinsics.areEqual(this.heightParam, initValue.heightParam) && Intrinsics.areEqual(this.tyBaseParam, initValue.tyBaseParam) && Intrinsics.areEqual(this.tyFinalParam, initValue.tyFinalParam);
        }

        public final ExtStateAnimParams.SpringParams getAlphaSpringParam() {
            return this.alphaSpringParam;
        }

        public final ExtStateAnimParams.SpringParams getBlurParam() {
            return this.blurParam;
        }

        public final ExtStateAnimParams.SpringParams getCornerRadiusParam() {
            return this.cornerRadiusParam;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final ExtStateAnimParams.SpringParams getHeightParam() {
            return this.heightParam;
        }

        public final ExtStateAnimParams.SpringParams getTyBaseParam() {
            return this.tyBaseParam;
        }

        public final ExtStateAnimParams.SpringParams getTyFinalParam() {
            return this.tyFinalParam;
        }

        public final int hashCode() {
            return this.tyFinalParam.hashCode() + ((this.tyBaseParam.hashCode() + ((this.heightParam.hashCode() + ((this.cornerRadiusParam.hashCode() + ((this.blurParam.hashCode() + ((this.alphaSpringParam.hashCode() + (Long.hashCode(this.duration) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
        }

        public String toString() {
            return "InitValue(duration=" + this.duration + ", alphaSpringParam=" + this.alphaSpringParam + ", blurParam=" + this.blurParam + ", cornerRadiusParam=" + this.cornerRadiusParam + ", heightParam=" + this.heightParam + ", tyBaseParam=" + this.tyBaseParam + ", tyFinalParam=" + this.tyFinalParam + ")";
        }

        public InitValue(long j, ExtStateAnimParams.SpringParams springParams, ExtStateAnimParams.SpringParams springParams2, ExtStateAnimParams.SpringParams springParams3, ExtStateAnimParams.SpringParams springParams4, ExtStateAnimParams.SpringParams springParams5, ExtStateAnimParams.SpringParams springParams6) {
            this.duration = j;
            this.alphaSpringParam = springParams;
            this.blurParam = springParams2;
            this.cornerRadiusParam = springParams3;
            this.heightParam = springParams4;
            this.tyBaseParam = springParams5;
            this.tyFinalParam = springParams6;
        }
    }
}
