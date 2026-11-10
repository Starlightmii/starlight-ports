package com.oplus.systemui.statusbar.pipeline.mobile.ui.view;

import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileViewBinderFactory {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusMobileViewBinderFactory";
    private final Lazy bigTypeStatusBarMobileViewBinderLazy;
    private final Lazy bigTypeStatusBarMobileViewBinderLazyLegacy;
    private final Lazy legacyOplusStatusBarMobileViewBinderLazy;
    private final Lazy oplusStatusBarMobileViewBinderLazy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusMobileViewBinderFactory(Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        this.bigTypeStatusBarMobileViewBinderLazy = lazy;
        this.bigTypeStatusBarMobileViewBinderLazyLegacy = lazy2;
        this.legacyOplusStatusBarMobileViewBinderLazy = lazy3;
        this.oplusStatusBarMobileViewBinderLazy = lazy4;
    }

    public final ICustStatusBarMobileViewBinder getMobileViewBinder(boolean z) {
        if (!CustomizeFeatureOption.sIsShowBig5G && !CustomizeFeatureOption.sIsShowBig5GUWC) {
            return z ? (ICustStatusBarMobileViewBinder) this.oplusStatusBarMobileViewBinderLazy.get() : (ICustStatusBarMobileViewBinder) this.legacyOplusStatusBarMobileViewBinderLazy.get();
        }
        if (z) {
            Object obj = this.bigTypeStatusBarMobileViewBinderLazy.get();
            obj.getClass();
            return (ICustStatusBarMobileViewBinder) obj;
        }
        Object obj2 = this.bigTypeStatusBarMobileViewBinderLazyLegacy.get();
        obj2.getClass();
        return (ICustStatusBarMobileViewBinder) obj2;
    }
}
