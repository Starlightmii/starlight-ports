package com.oplus.systemui.notification.lockscreen.stack;

import com.android.systemui.statusbar.notification.row.ExpandableView;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IStackViewUpdater {
    static /* synthetic */ void getToExtState$annotations() {
    }

    boolean getHasNotificationStacked();

    StackCardControl.StackType getStackType();

    int getToExtState();

    void setStackType(StackCardControl.StackType stackType);

    void updateToExt(List<? extends ExpandableView> list);
}
