package com.oplus.systemui.qs.base.util;

import android.content.Context;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplus.util.OplusContextUtil;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsGlobalThemeResourceUtil {
    public static final QsGlobalThemeResourceUtil INSTANCE = new QsGlobalThemeResourceUtil();

    public static final int getDefaultAccentColor() {
        FlavorTwoFeatureOption.INSTANCE.getClass();
        return FlavorTwoFeatureOption.isFlavorTwoDeviceExp() ? 2131103734 : 2131103733;
    }

    public static final int getDetailTileBgActiveColor() {
        return 2131103203;
    }

    public static final int getDetailTileBgInactiveColor() {
        return 2131103683;
    }

    public static final int getDetailTileIconActiveColor(Context context) {
        return context.getColor(2131103204);
    }

    public static final int getDetailTileIconInactiveColor(Context context) {
        return OplusContextUtil.getAttrColor(context.getApplicationContext(), 2130969296);
    }

    public static final int getDetailTileIconUnavailableColor(Context context) {
        return QSConstant.setColorAlpha(getDetailTileIconInactiveColor(context));
    }

    public static final int getHighlightBgActiveDrawable() {
        return 2131237162;
    }

    public static final int getHighlightBgInactiveDrawable() {
        return 2131237163;
    }

    public static final int getMediaPanelDrawable() {
        return 2131237184;
    }

    public static final int getPrivacyAppNameColor() {
        return BaseQSResPool.globalThemeApplied() ? 2131103706 : 2131103292;
    }

    public static final int getTileBgActiveDrawable() {
        return 2131237227;
    }

    public static final int getTileBgInactiveDrawable() {
        return 2131237228;
    }
}
