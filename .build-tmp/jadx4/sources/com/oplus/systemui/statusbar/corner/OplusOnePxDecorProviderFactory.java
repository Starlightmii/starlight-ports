package com.oplus.systemui.statusbar.corner;

import android.os.SystemProperties;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.decor.DecorProvider;
import com.android.systemui.decor.DecorProviderFactory;
import com.android.systemui.decor.RoundedCornerResDelegate;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusOnePxDecorProviderFactory implements DecorProviderFactory {
    private final RoundedCornerResDelegate roundedCornerResDelegate;
    public final boolean sDebugEdges = SystemProperties.getBoolean("debug.screen_decorations.edges.enable", false);

    public OplusOnePxDecorProviderFactory(RoundedCornerResDelegate roundedCornerResDelegate) {
        this.roundedCornerResDelegate = roundedCornerResDelegate;
    }

    public boolean getHasProviders() {
        boolean normalDebug = LogUtil.getNormalDebug();
        boolean z = this.sDebugEdges;
        if (normalDebug && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            FeatureOption.INSTANCE.getClass();
            boolean zIsFoldDevice = FeatureOption.isFoldDevice();
            StatusBarFeatureOption.INSTANCE.getClass();
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusOnePxDecorProviderFactory-->", "SystemUi--Common", TextFieldImplKt$.ExternalSyntheticOutline0.m("OnePx will be visible: isFoldDevice=", ", sDebugEdges=", ", isOnePxOptSupport=", zIsFoldDevice, z), StatusBarFeatureOption.isOnePxOptSupport);
        }
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isFoldDevice() && !z) {
            return false;
        }
        StatusBarFeatureOption.INSTANCE.getClass();
        return StatusBarFeatureOption.isOnePxOptSupport && StatusBarFeatureOption.isRoundCornerSupport();
    }

    public List<DecorProvider> getProviders() {
        if (!getHasProviders()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OplusOnePxDecorProviderFactory-->OnePx will be not visible");
            }
            return CollectionsKt__CollectionsKt.emptyList();
        }
        if (this.roundedCornerResDelegate.getHasTop() && this.roundedCornerResDelegate.getHasBottom()) {
            return CollectionsKt__CollectionsKt.listOf(new OplusOnePxDecorProviderImpl[]{new OplusOnePxDecorProviderImpl(2131364248, 1), new OplusOnePxDecorProviderImpl(2131364247, 3)});
        }
        if (this.roundedCornerResDelegate.getHasTop()) {
            return CollectionsKt__CollectionsJVMKt.listOf(new OplusOnePxDecorProviderImpl(2131364248, 1));
        }
        return this.roundedCornerResDelegate.getHasBottom() ? CollectionsKt__CollectionsJVMKt.listOf(new OplusOnePxDecorProviderImpl(2131364247, 3)) : CollectionsKt__CollectionsKt.emptyList();
    }
}
