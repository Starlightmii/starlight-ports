package com.oplus.systemui.statusbar.notification.view;

import android.content.Context;
import android.graphics.Canvas;
import com.oplus.systemui.statusbar.notification.row.menupanel.RoundClipView;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GroupOverlayContainerView extends RoundClipView {
    private Function1 aodMaskDrawer;
    private Function1 overlayMaskDrawer;
    private Function1 stackMaskDrawer;

    public GroupOverlayContainerView(Context context) {
        super(context);
    }

    public final Function1 getAodMaskDrawer() {
        return this.aodMaskDrawer;
    }

    public final Function1 getOverlayMaskDrawer() {
        return this.overlayMaskDrawer;
    }

    public final Function1 getStackMaskDrawer() {
        return this.stackMaskDrawer;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Function1 function1 = this.overlayMaskDrawer;
        if (function1 != null) {
            function1.invoke(canvas);
        }
        Function1 function2 = this.stackMaskDrawer;
        if (function2 != null) {
            function2.invoke(canvas);
        }
        Function1 function3 = this.aodMaskDrawer;
        if (function3 != null) {
            function3.invoke(canvas);
        }
        super.onDraw(canvas);
    }

    public final void setAodMaskDrawer(Function1 function1) {
        this.aodMaskDrawer = function1;
    }

    public final void setOverlayMaskDrawer(Function1 function1) {
        this.overlayMaskDrawer = function1;
    }

    public final void setStackMaskDrawer(Function1 function1) {
        this.stackMaskDrawer = function1;
    }
}
