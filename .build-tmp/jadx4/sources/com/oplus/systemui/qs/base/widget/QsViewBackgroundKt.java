package com.oplus.systemui.qs.base.widget;

import android.content.Context;
import android.view.View;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.util.StrokeCornerUtils;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class QsViewBackgroundKt {
    public static final LightStyleStrokeDrawable createMixColorDrawableForQsView(View view) {
        BlurConfig blurConfig = new BlurConfig(QSBlurConfigProvider.getDefaultBlurRadius(), 0, null, null, null, null, false, null, null, 0, null, 33554430);
        updateBlurConfig(blurConfig, view.getContext());
        ViewBlurProxy viewBlurProxy = new ViewBlurProxy(view, blurConfig, null, 24);
        viewBlurProxy.setBlurType(ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE);
        return new LightStyleStrokeDrawable(viewBlurProxy, null, null);
    }

    public static final boolean isMixColorSupport(Context context, boolean z) {
        return (ScrimUtil.isLowGaussianLevel(context) || z) ? false : true;
    }

    public static final boolean updateBlurConfig(BlurConfig blurConfig, Context context) {
        BlurMixConfig inactiveBlurConfig = QSBlurConfigProvider.getInactiveBlurConfig(context.getResources().getConfiguration().isNightModeActive(), StrokeCornerUtils.isSupportStroke(), BaseQSResPool.getMirrorBlurScaleValue());
        if (Intrinsics.areEqual(blurConfig.getPlatformMixConfig(), inactiveBlurConfig)) {
            return false;
        }
        blurConfig.setPlatformMixConfig(inactiveBlurConfig);
        return true;
    }

    public static final boolean isMixColorSupport(QsViewInfoProvider qsViewInfoProvider) {
        ScrimUtil.isLowGaussianLevel(qsViewInfoProvider.getContext());
        return false;
    }
}
