package com.oplusos.systemui.common.feature;

import android.content.Context;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import com.oplusos.systemui.common.util.BrandUtils;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlavorTwoFeatureOption {
    public static boolean sIsSupportPacMan;
    public static final FlavorTwoFeatureOption INSTANCE = new FlavorTwoFeatureOption();
    private static final String IS_SUPPORT_PACMAN = "com.android.systemui.support_pmf";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsJVMKt.listOf(IS_SUPPORT_PACMAN);
    private static final Set<String> OPAQUE_PLATE_ICON_PACKAGES = SetsKt__SetsJVMKt.setOf("com.yatra.base");
    private static final Lazy isFlavorTwoDeviceExpLessThanC$delegate = LazyKt__LazyJVMKt.lazy(new FlavorTwoFeatureOption$$ExternalSyntheticLambda0());

    public static boolean isFlavorTwoDevice() {
        BrandUtils.INSTANCE.getClass();
        return BrandUtils.isOneplus;
    }

    public static boolean isFlavorTwoDeviceCN() {
        if (!isFlavorTwoDevice()) {
            return false;
        }
        FeatureOption.INSTANCE.getClass();
        return !FeatureOption.isExpRegion();
    }

    public static boolean isFlavorTwoDeviceExp() {
        if (!isFlavorTwoDevice()) {
            return false;
        }
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isExpRegion();
    }

    public static boolean isFlavorTwoDeviceExpLessThanC() {
        return ((Boolean) isFlavorTwoDeviceExpLessThanC$delegate.getValue()).booleanValue();
    }

    public final List<String> getAppFeatureCacheList() {
        return isFlavorTwoDevice() ? appFeatureList : CollectionsKt__CollectionsKt.emptyList();
    }

    public final void loadAppFeature(Context context) {
        sIsSupportPacMan = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT, IS_SUPPORT_PACMAN);
    }

    public final boolean shouldUseOpaquePlateIcon(String str) {
        return isFlavorTwoDeviceExpLessThanC() && str != null && OPAQUE_PLATE_ICON_PACKAGES.contains(str);
    }
}
