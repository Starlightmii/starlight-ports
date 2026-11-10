package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSElement;
import com.oplus.systemui.plugins.qs.OplusSeparateQSPluginImpl;
import com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperExImpl;
import kotlin.jvm.functions.Function0;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class EditableMediaViewHolder$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ Context f$0;

    public final Object invoke() {
        QSElement qSElementBindQSElement;
        Context context = this.f$0;
        EditableMediaViewHolder.Companion companion = EditableMediaViewHolder.Companion;
        OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
        if (proxy != null && (qSElementBindQSElement = proxy.bindQSElement(OplusNotificationTemplateViewWrapperExImpl.MEDIA_TAG, context)) != null) {
            return qSElementBindQSElement;
        }
        Path$.ExternalSyntheticBUOutline0.m$1("cannot get QSMedia from host");
        return null;
    }
}
