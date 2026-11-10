package com.oplus.systemui.statusbar.notification.row.wrapper;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusNotificationTemplateViewWrapperExImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusNotificationTemplateViewWrapperExImpl f$0;

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusNotificationTemplateViewWrapperExImpl oplusNotificationTemplateViewWrapperExImpl = this.f$0;
        switch (i) {
            case 0:
                Context context = oplusNotificationTemplateViewWrapperExImpl.getContext();
                Configuration configuration = new Configuration();
                configuration.fontScale = 1.0f;
                configuration.densityDpi = DisplayMetrics.DENSITY_DEVICE_STABLE;
                return Float.valueOf(context.createConfigurationContext(configuration).getResources().getDimension(2131170580));
            case 1:
                Resources resources = oplusNotificationTemplateViewWrapperExImpl.getContext().getResources();
                return Integer.valueOf(resources != null ? resources.getDimensionPixelOffset(2131170654) : 0);
            default:
                Resources resources2 = oplusNotificationTemplateViewWrapperExImpl.getContext().getResources();
                return Integer.valueOf(resources2 != null ? resources2.getDimensionPixelOffset(2131170636) : 0);
        }
    }
}
