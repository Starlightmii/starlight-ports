package com.oplus.systemui.navigationbar.gesture.subdisplay;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ConfigurationOnSubDisplay {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ConfigurationOnSubDisplay";
    private static volatile ConfigurationOnSubDisplay instance = null;
    public static int secondaryDisplayId = -1;
    public static int subScreenHeight = 720;
    public static int subScreenSideGestureSize = 37;
    public static int subScreenWidth = 382;
    private final Context mContext;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final ConfigurationOnSubDisplay getInstance(Context context) {
            ConfigurationOnSubDisplay configurationOnSubDisplay;
            ConfigurationOnSubDisplay configurationOnSubDisplay2 = ConfigurationOnSubDisplay.instance;
            if (configurationOnSubDisplay2 != null) {
                return configurationOnSubDisplay2;
            }
            synchronized (this) {
                configurationOnSubDisplay = ConfigurationOnSubDisplay.instance;
                if (configurationOnSubDisplay == null) {
                    configurationOnSubDisplay = new ConfigurationOnSubDisplay(context);
                    ConfigurationOnSubDisplay.instance = configurationOnSubDisplay;
                }
            }
            return configurationOnSubDisplay;
        }
    }

    public ConfigurationOnSubDisplay(Context context) {
        this.mContext = context;
    }

    private final int getSecondaryDisplayId() {
        return secondaryDisplayId;
    }

    public static boolean isSubDisplayId(int i) {
        return i == secondaryDisplayId;
    }

    public final Rect getScreenBounds(int i) {
        return i == secondaryDisplayId ? new Rect(0, 0, subScreenWidth, subScreenHeight) : new Rect();
    }

    public final int getSubBottomGestureAreaHeight(Context context) {
        Resources resources;
        if (context == null || (resources = context.getResources()) == null) {
            return 0;
        }
        return resources.getDimensionPixelSize(2131173165);
    }

    public final int getSubScreenHeight() {
        return subScreenHeight;
    }

    public final int getSubScreenWidth() {
        return subScreenWidth;
    }

    public final int getSubSideGestureSize() {
        return subScreenSideGestureSize;
    }

    public final float getSubscreenCancelDistance() {
        return 30.0f;
    }

    public final void initData() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("isSupportMiniApp = , ", "ConfigurationOnSubDisplay-->", "SystemUi--NavBar", CustomizeFeatureOption.sIsSupportMiniApp);
        }
        Context context = this.mContext;
        if (context == null || !CustomizeFeatureOption.sIsSupportMiniApp) {
            return;
        }
        OplusSubDisplayUtils.Companion companion = OplusSubDisplayUtils.Companion;
        if (companion.getInstance(context).getSupportSubDisplay()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("supportSubDisplay = , ", "ConfigurationOnSubDisplay-->", "SystemUi--NavBar", companion.getInstance(this.mContext).getSupportSubDisplay());
            }
            secondaryDisplayId = companion.getInstance(this.mContext).getSecondaryDisplayId();
            subScreenWidth = companion.getInstance(this.mContext).getSecondaryDisplayWidth(1.0f);
            subScreenHeight = companion.getInstance(this.mContext).getSecondaryDisplayHeight(1.0f);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("subScreenWidth = ", subScreenWidth, subScreenHeight, ", subScreenHeight = ", "ConfigurationOnSubDisplay-->"));
            }
        }
    }

    public final void setSubSideGestureSize(int i) {
        subScreenSideGestureSize = i;
    }
}
