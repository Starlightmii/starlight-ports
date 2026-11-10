package com.oplus.systemui.statusbar.corner;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import com.android.settingslib.bluetooth.devicesettings.DeviceSettingItem$;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;
import com.android.systemui.decor.CutoutDecorProviderFactory;
import com.android.systemui.decor.DecorProvider;
import com.android.systemui.decor.FaceScanningProviderFactoryKt;
import com.android.systemui.decor.RoundedCornerResDelegate;
import com.android.systemui.statusbar.OplusStatusBarDependencyInstance;
import com.oplus.systemui.statusbar.util.OplusStatusBarUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCutoutDecorProviderFactory extends CutoutDecorProviderFactory {
    private final Context context;
    private final Display display;
    private final Resources res;
    private final RoundedCornerResDelegate roundedCornerResDelegate;

    public OplusCutoutDecorProviderFactory(Context context, Resources resources, Display display, RoundedCornerResDelegate roundedCornerResDelegate) {
        super(resources, display);
        this.context = context;
        this.res = resources;
        this.display = display;
        this.roundedCornerResDelegate = roundedCornerResDelegate;
    }

    public boolean getHasProviders() {
        Display display = this.display;
        if (display != null) {
            display.getDisplayInfo(getDisplayInfo());
        } else {
            Log.w("OplusCutoutDecorProviderFactory", "display is null, can't update displayInfo");
        }
        String uniqueId = this.roundedCornerResDelegate.getUniqueId();
        if (uniqueId == null) {
            uniqueId = getDisplayInfo().uniqueId;
        }
        boolean zIsOplusDisplaySoftRoundedCornerEnabled = ScreenDecorations.isOplusDisplaySoftRoundedCornerEnabled(this.res, uniqueId);
        boolean fillBuiltInDisplayCutout = DisplayCutout.getFillBuiltInDisplayCutout(this.res, uniqueId);
        FeatureOption.INSTANCE.getClass();
        boolean zIsCenterHole = FeatureOption.isLargeScreenFolding() ? OplusStatusBarDependencyInstance.INSTANCE.getOplusStatusBarDependencyEx().getOplusScreenDecorationsEx().isCenterHole(getDisplayInfo()) : OplusStatusBarUtils.isCenterHole();
        boolean z = false;
        boolean z2 = FeatureOption.isEarScreen() && !(OplusStatusBarUtils.getScreenHoleHeight(this.context) > 0);
        boolean z3 = fillBuiltInDisplayCutout && zIsCenterHole;
        if (z2 || z3) {
            StatusBarFeatureOption.INSTANCE.getClass();
            if (StatusBarFeatureOption.isRoundCornerSupport() && zIsOplusDisplaySoftRoundedCornerEnabled) {
                z = true;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            String str = getDisplayInfo().uniqueId;
            String uniqueId2 = this.roundedCornerResDelegate.getUniqueId();
            StatusBarFeatureOption.INSTANCE.getClass();
            boolean zIsRoundCornerSupport = StatusBarFeatureOption.isRoundCornerSupport();
            StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("cutoutView will be visible: shouldFillCutout=", ", displayInfo.uniqueId: ", str, fillBuiltInDisplayCutout, " displayInfo.uniqueId=");
            DeviceSettingItem$.ExternalSyntheticOutline0.m(uniqueId2, " isRoundCornerSupport=", " isOplusDisplayEnabled=", sbM, zIsRoundCornerSupport);
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusCutoutDecorProviderFactory-->", "SystemUi--Common", sbM, zIsOplusDisplaySoftRoundedCornerEnabled);
        }
        return z;
    }

    public List<DecorProvider> getProviders() {
        List boundBaseOnCurrentRotation;
        if (!getHasProviders()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OplusCutoutDecorProviderFactory-->cutoutView will be not visible");
            }
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        DisplayCutout displayCutout = getDisplayInfo().displayCutout;
        if (displayCutout != null && (boundBaseOnCurrentRotation = FaceScanningProviderFactoryKt.getBoundBaseOnCurrentRotation(displayCutout)) != null) {
            Iterator it = boundBaseOnCurrentRotation.iterator();
            while (it.hasNext()) {
                arrayList.add(new OplusCutoutDecorProviderImpl(FaceScanningProviderFactoryKt.baseOnRotation0(((Number) it.next()).intValue(), getDisplayInfo().rotation), this.roundedCornerResDelegate));
            }
        }
        return arrayList;
    }
}
