package com.oplus.systemui.notification.lockscreen.effect;

import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.zoom.common.zoomparameter.SmallScreenZoomParameter;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "通用_1支持触摸反馈(0关闭，1开启)", "通用", 1, 16);
            case 1:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "胶囊到列表", "胶囊相关无缝比例设置(以位置为基准)", Float.valueOf(0.9f), 16);
            case 2:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "列表到胶囊", "胶囊相关无缝比例设置(以位置为基准)", Float.valueOf(0.5f), 16);
            case 3:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "列表到胶囊地图独立", "胶囊相关无缝比例设置(以位置为基准)", Float.valueOf(0.85f), 16);
            case 4:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "胶囊到沉静", "胶囊相关无缝比例设置(以位置为基准)", Float.valueOf(0.8f), 16);
            case 5:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "沉静到胶囊", "胶囊相关无缝比例设置(以位置为基准)", Float.valueOf(0.7f), 16);
            case 6:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "三态动画", "胶囊到列表_alpha淡出", 0.0f, 0.9f, 0.0f, 0.0f, 0.0f, null, null, "胶囊相关无缝比例设置(以位置为基准)", 496);
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "胶囊切沉静态_下一个卡片开始缩放比值", "胶囊切沉静态", Float.valueOf(0.0f), 16);
            case 8:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "三态动画", "胶囊切沉静态_下一个卡片缩放曲线", -0.4f, 0.35f, 0.0f, 0.0f, 0.0f, null, null, "胶囊切沉静态", 496);
            case 9:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "胶囊切沉静态_新胶囊显示的延迟时间", "胶囊切沉静态", 90L, 16);
            case 10:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "胶囊切沉静态_新胶囊显示的总时长（排除延迟外）", "胶囊切沉静态", 500L, 16);
            case 11:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "通用_1支持触摸比例", "通用", Float.valueOf(0.92f), 16);
            case 12:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "通用_1支持触摸时长", "通用", Float.valueOf(120.0f), 16);
            case 13:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "三态动画", "通用_退出触摸差值器", 0.0f, 0.3f, 0.0f, 0.0f, 0.0f, null, null, "通用", 496);
            case 14:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "三态动画", "通用_进入触摸差值器", 0.0f, 0.35f, 0.0f, 0.0f, 0.0f, null, null, "通用", 496);
            case 15:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "三态动画", "通用_指纹变化y值", 0.35f, 0.4f, 0.0f, 0.0f, 0.0f, null, null, "通用", 496);
            case 16:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "通用_时钟比例", "通用", Float.valueOf(0.7f), 16);
            case 17:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "堆叠底部额外缩放", "跟手动画", Float.valueOf(0.1f), 16);
            case 18:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "沉静态切胶囊_上一个卡片开始缩放比值", "沉静态切胶囊", Float.valueOf(0.0f), 16);
            case 19:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "三态动画", "沉静态切胶囊_上一个卡片缩放曲线", 0.0f, 0.9f, 0.0f, 0.0f, 0.0f, null, null, "沉静态切胶囊", 496);
            case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "沉静态切胶囊_大卡下移的延迟时间", "沉静态切胶囊", 0L, 16);
            case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "沉静态切胶囊_上一个卡片的时长（排除延迟时间）", "沉静态切胶囊", 400L, 16);
            case 22:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "列表切胶囊_多卡时单延迟", "列表切胶囊", 0L, 16);
            case 23:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "列表切胶囊_多卡时单缩放差值", "列表切胶囊", Float.valueOf(0.1f), 16);
            case 24:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "列表切胶囊_多卡时第一个比值", "列表切胶囊", Float.valueOf(0.8f), 16);
            case 25:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "列表切胶囊_多卡时单缩放最低值", "列表切胶囊", Float.valueOf(0.1f), 16);
            case 26:
                return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "三态动画", "列表进入与退出锁屏通知中心_插值器_时长", "列表进入与退出锁屏通知中心", 400L, 16);
            default:
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, "三态动画", "列表进入与退出锁屏通知中心_插值器_SCALE", 0.15f, 0.8f, 0.0f, 0.0f, 0.0f, null, null, "列表进入与退出锁屏通知中心", 496);
        }
    }
}
