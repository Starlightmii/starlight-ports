package com.oplus.systemui.subdisplay;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import com.android.systemui.communal.widgets.CommunalWidgetHost$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.ISubFullscreenCallback;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SecondaryKeyguardProviderClient {
    public static final Companion Companion = new Companion();
    private static final String KEY_AOD_VISIBILITY_CHANGE_CALLBACK = "aodVisibilityCallback";
    private static final String KEY_AOD_VISIBILITY_TYPE = "aodVisibilityType";
    private static final String METHOD_ON_KEYGUARD_VIEW_DISMISS = "onKeyguardViewDismiss";
    private static final String METHOD_REQUEST_AOD_FULLSCREEN_VISIBILITY = "requestAodFullscreenVisibility";
    private static final String PROVIDER_URI = "com.oplus.secondaryhome.systemui.KeyguardProvider";
    private static final String TAG = "SecondaryKeyguardProviderClient";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final void onKeyguardViewDismiss(Context context) {
        ContentResolver contentResolver;
        if (context == null || (contentResolver = context.getContentResolver()) == null) {
            return;
        }
        try {
            contentResolver.call(PROVIDER_URI, METHOD_ON_KEYGUARD_VIEW_DISMISS, (String) null, new Bundle());
        } catch (IllegalArgumentException e) {
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m$1("IllegalArgumentException: ", e.getMessage(), TAG);
        } catch (Exception e2) {
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m$1("Exception: ", e2.getMessage(), TAG);
        }
    }

    public final void requestAodFullscreenVisibility(Context context, int i, ISubFullscreenCallback iSubFullscreenCallback) {
        ContentResolver contentResolver;
        if (context == null || (contentResolver = context.getContentResolver()) == null) {
            return;
        }
        Bundle bundleM = CommunalWidgetHost$.ExternalSyntheticOutline0.m(i, KEY_AOD_VISIBILITY_TYPE);
        bundleM.putBinder(KEY_AOD_VISIBILITY_CHANGE_CALLBACK, iSubFullscreenCallback.asBinder());
        try {
            contentResolver.call(PROVIDER_URI, METHOD_REQUEST_AOD_FULLSCREEN_VISIBILITY, (String) null, bundleM);
        } catch (IllegalArgumentException e) {
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m$1("IllegalArgumentException: ", e.getMessage(), TAG);
        } catch (Exception e2) {
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m$1("Exception: ", e2.getMessage(), TAG);
        }
    }
}
