package com.oplus.systemui.notification.lockscreen.stack.algorithm;

import android.content.Context;
import android.content.res.Resources;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicStackDimensCache {
    private final Context context;
    private DynamicStackDimens dimens = new DynamicStackDimens(0, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

    public DynamicStackDimensCache(Context context) {
        this.context = context;
        refreshFromResources();
    }

    public final DynamicStackDimens getDimens() {
        return this.dimens;
    }

    public final void refreshFromResources() {
        Resources resources = this.context.getResources();
        this.dimens.setLargeNotificationHeight(resources.getDimensionPixelOffset(2131172951));
        this.dimens.setStackedLargeNotificationLimitedHeight(resources.getDimensionPixelOffset(2131172952));
        this.dimens.setTranslationDampingBaseHeight(resources.getDimension(2131172955));
        this.dimens.setScaleDampingBaseHeight(resources.getDimension(2131172954));
        this.dimens.setStackedNotificationFadeOutRange(resources.getDimensionPixelOffset(2131172949));
        this.dimens.setNotificationChildrenContainerDivider(resources.getDimensionPixelOffset(2131170607));
        this.dimens.setPaddingBetweenElements(resources.getDimensionPixelSize(2131170633));
    }
}
