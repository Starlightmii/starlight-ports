package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.phone.OplusConfigurationControllerEx;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.aod.utils.AodUtils;
import com.oplus.systemui.keyguard.helper.ScreenResolutionHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusConfigurationControllerExImpl extends OplusConfigurationControllerEx {
    private final Context mContext;
    public int mDensityDpi;

    public OplusConfigurationControllerExImpl(Context context) {
        this.mContext = context;
        this.mDensityDpi = context.getResources().getConfiguration().densityDpi;
    }

    public void onConfigurationChanged(Configuration configuration, Context context) {
        super.onConfigurationChanged(configuration, context);
        if (AodUtils.sFixScaleContext != null) {
            synchronized (AodUtils.class) {
                try {
                    if (AodUtils.sFixScaleContext != null) {
                        AodUtils.sFixScaleContext = AodUtils.getContextInner(AodUtils.sFixScaleContext, configuration);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        ScreenResolutionHelper.Companion.getInstance(context).updateDisplaySize();
        int i = this.mDensityDpi;
        int i2 = configuration.densityDpi;
        if (i != i2) {
            this.mDensityDpi = i2;
            ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).handleResolutionChanged();
            PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
            if (pictorialMediator != null) {
                pictorialMediator.mDefaultDensityContext = KeyguardUtils.Companion.updateDisplayConfiguration(pictorialMediator.mContext);
            }
        }
    }
}
