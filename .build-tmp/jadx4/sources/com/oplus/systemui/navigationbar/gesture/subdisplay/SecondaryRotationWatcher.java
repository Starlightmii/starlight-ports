package com.oplus.systemui.navigationbar.gesture.subdisplay;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.IRotationWatcher;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SecondaryRotationWatcher {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SecondaryRotationWatcher";
    public static int secondaryRotation;
    private final Context mContext;
    private final EdgeBackGestureHandler mEdgeBackGestureHandler;
    private final IRotationWatcher mWatcher;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getSecondaryRotation() {
            return SecondaryRotationWatcher.secondaryRotation;
        }

        public final void setSecondaryRotation(int i) {
            SecondaryRotationWatcher.secondaryRotation = i;
        }
    }

    public SecondaryRotationWatcher(Context context, EdgeBackGestureHandler edgeBackGestureHandler) {
        this.mContext = context;
        this.mEdgeBackGestureHandler = edgeBackGestureHandler;
        IRotationWatcher.Stub stub = new IRotationWatcher.Stub() { // from class: com.oplus.systemui.navigationbar.gesture.subdisplay.SecondaryRotationWatcher$mWatcher$1
            public void onRotationChanged(int i) {
                SecondaryRotationWatcher.Companion companion = SecondaryRotationWatcher.Companion;
                LogUtil.i$default(LogUtil.TAG_NAVBAR, "SecondaryRotationWatcher", TextFieldBuffer$.ExternalSyntheticOutline0.m(companion.getSecondaryRotation(), i, "onRotationChanged secondaryRotation: ", ", rotation: "), null, 8);
                if (companion.getSecondaryRotation() != i) {
                    companion.setSecondaryRotation(i);
                    this.this$0.mEdgeBackGestureHandler.onRotationChanged(i);
                }
            }
        };
        this.mWatcher = stub;
        if (CustomizeFeatureOption.sIsSupportMiniApp) {
            ConfigurationOnSubDisplay companion = ConfigurationOnSubDisplay.Companion.getInstance(context);
            int displayId = context.getDisplayId();
            companion.getClass();
            if (ConfigurationOnSubDisplay.isSubDisplayId(displayId)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                    OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(context.getDisplayId(), "register: ", "SecondaryRotationWatcher-->", "SystemUi--NavBar");
                }
                try {
                    IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
                    if (windowManagerService != null) {
                        windowManagerService.watchRotation(stub, context.getDisplayId());
                    }
                } catch (RemoteException e) {
                    Log.w(TAG, "Failed to set rotation watcher", e);
                }
            }
        }
    }
}
