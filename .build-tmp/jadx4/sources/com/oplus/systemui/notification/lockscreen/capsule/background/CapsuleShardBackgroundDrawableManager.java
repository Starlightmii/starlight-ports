package com.oplus.systemui.notification.lockscreen.capsule.background;

import android.content.Context;
import android.util.Log;
import android.util.Pools;
import android.view.View;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleShardBackgroundDrawableManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleShardBackground";
    public final ViewBlurManager viewBlurManager = (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
    private final Pools.SimplePool<ViewBlurProxy> proxyCachePools = new Pools.SimplePool<>(2);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final void initManager(Context context, View view, View view2) {
        if (!ScrimUtil.isMiddleOrHighAnimLevel(context)) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "initManager:unSupport", null, 8);
            return;
        }
        releaseSharedProxy(requireBlurProxyForView(view));
        releaseSharedProxy(requireBlurProxyForView(view2));
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "initManager:hostView1=" + view + ", hostView2=" + view2, null, 8);
    }

    public final void releaseSharedProxy(ViewBlurProxy viewBlurProxy) {
        if (viewBlurProxy == null) {
            return;
        }
        try {
            Result.constructor-impl(Boolean.valueOf(this.proxyCachePools.release(viewBlurProxy)));
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public final ViewBlurProxy requestSharedProxy() {
        Object obj;
        try {
            obj = Result.constructor-impl((ViewBlurProxy) this.proxyCachePools.acquire());
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        ViewBlurProxy viewBlurProxy = (ViewBlurProxy) obj;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleShardBackground-->".concat("requestSharedProxy: " + viewBlurProxy));
        }
        return viewBlurProxy;
    }

    public final ViewBlurProxy requireBlurProxyForView(View view) {
        ViewBlurProxy viewBlurProxyRequireBlurProxyForView = this.viewBlurManager.requireBlurProxyForView(view, ViewBlurManager.CardType.CAPSULE);
        BlurConfig blurConfig = viewBlurProxyRequireBlurProxyForView.getBlurConfig();
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        blurConfig.setCornerRadius(capsuleConfig.getDefaultRadius());
        viewBlurProxyRequireBlurProxyForView.getBlurConfig().setSupportMetaBall(false);
        viewBlurProxyRequireBlurProxyForView.getBlurConfig().setRadiusWeight(null);
        viewBlurProxyRequireBlurProxyForView.getBlurConfig().setGradientStrokeCornerParam(SmoothRoundEx.INSTANCE.createMetaBallsCornerParams(capsuleConfig.getDefaultRadius()));
        viewBlurProxyRequireBlurProxyForView.setBlurType(ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE);
        viewBlurProxyRequireBlurProxyForView.applyBlurConfig();
        return viewBlurProxyRequireBlurProxyForView;
    }
}
