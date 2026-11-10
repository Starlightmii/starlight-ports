package com.oplus.systemui.navigationbar.eximpl;

import android.content.Context;
import android.graphics.Rect;
import com.android.systemui.navigationbar.NavigationBarSubDisplayEx;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.oplus.systemui.navigationbar.gesture.subdisplay.ConfigurationOnSubDisplay;
import com.oplus.systemui.navigationbar.gesture.subdisplay.SecondaryRotationWatcher;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavigationBarSubDisplayExImpl extends NavigationBarSubDisplayEx {
    public void createSecondaryRotationWatcher(Context context, EdgeBackGestureHandler edgeBackGestureHandler) {
        new SecondaryRotationWatcher(context, edgeBackGestureHandler);
    }

    public int getSecondaryRotation() {
        return SecondaryRotationWatcher.Companion.getSecondaryRotation();
    }

    public Rect getSubScreenBounds(Context context, int i) {
        return ConfigurationOnSubDisplay.Companion.getInstance(context).getScreenBounds(i);
    }

    public boolean isSecondaryDisplay(Context context, int i) {
        OplusSubDisplayUtils companion = OplusSubDisplayUtils.Companion.getInstance(context);
        return companion.getSupportSubDisplay() && i == companion.getSecondaryDisplayId();
    }

    public boolean isSubDisplayId(Context context, int i) {
        ConfigurationOnSubDisplay.Companion.getInstance(context).getClass();
        return ConfigurationOnSubDisplay.isSubDisplayId(i);
    }
}
