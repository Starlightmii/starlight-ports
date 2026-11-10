package com.oplus.systemui.notification.row.overlay;

import android.content.Context;
import android.view.View;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusNotificationOverlayController {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final OplusNotificationOverlayController newInstance(Context context, CapsuleCardControl capsuleCardControl) {
            return new OplusNotificationOverlayControllerImpl(context, capsuleCardControl);
        }
    }

    void bindAuthorView(ExpandableView expandableView);

    ExpandableView getAuthorView();

    View getOplusNotificationOverlay();

    void setNotificationOverlayInfo(CharSequence charSequence, long j, boolean z);

    void unbindAuthorView(long j, boolean z, Function0 function0);
}
