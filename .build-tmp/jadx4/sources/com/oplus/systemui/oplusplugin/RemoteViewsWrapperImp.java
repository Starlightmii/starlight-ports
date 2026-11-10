package com.oplus.systemui.oplusplugin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import com.android.systemui.plugins.remoteViews.RemoteViewsWrapper;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RemoteViewsWrapperImp implements RemoteViewsWrapper {
    public View apply(RemoteViews remoteViews, Context context, ViewGroup viewGroup, RemoteViewsWrapper.InteractionHandler interactionHandler) {
        apply.1 r0 = new apply.1();
        r0.$interactionHandlerWrapper = interactionHandler;
        VarHandle.storeStoreFence();
        return remoteViews.apply(context, viewGroup, r0);
    }
}
