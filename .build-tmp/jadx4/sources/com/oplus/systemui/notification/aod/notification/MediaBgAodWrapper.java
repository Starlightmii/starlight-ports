package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.oplus.systemui.media.controls.ui.OplusMediaCarouselView;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import java.util.LinkedHashSet;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaBgAodWrapper implements AodViewWrapper {
    private final MediaAodBackgroundChanger aodBackgroundChanger;
    private final Context context;
    private Pair<Integer, Integer> layer0;
    public OplusMediaCarouselView view;

    public MediaBgAodWrapper(Context context, MediaAodBackgroundChanger mediaAodBackgroundChanger) {
        this.context = context;
        this.aodBackgroundChanger = mediaAodBackgroundChanger;
        this.layer0 = TuplesKt.to(Integer.valueOf(context.getColor(2131099868)), Integer.valueOf(context.getColor(2131099867)));
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public NormalBgAodColor createAodColor(int i) {
        BgAodData empty;
        Drawable blurDrawable = getView().getBlurDrawable();
        PlatformBlurDrawable platformBlurDrawable = blurDrawable instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable : null;
        if (platformBlurDrawable == null || (empty = AodBgViewWrapperKt.access$readBgAodData(platformBlurDrawable, 0)) == null) {
            empty = AodBgConstants.INSTANCE.getEMPTY();
        }
        return AodBgViewWrapperKt.access$createBgAodColor(empty, this.layer0, 0, new LinkedHashSet());
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ AodColor getAodColor() {
        return super.getAodColor();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public OplusMediaCarouselView getView() {
        OplusMediaCarouselView oplusMediaCarouselView = this.view;
        if (oplusMediaCarouselView != null) {
            return oplusMediaCarouselView;
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public boolean isSame(NormalBgAodColor normalBgAodColor) {
        BgAodData empty;
        Drawable blurDrawable = getView().getBlurDrawable();
        PlatformBlurDrawable platformBlurDrawable = blurDrawable instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable : null;
        if (platformBlurDrawable == null || (empty = AodBgViewWrapperKt.access$readBgAodData(platformBlurDrawable, 0)) == null) {
            empty = AodBgConstants.INSTANCE.getEMPTY();
        }
        return Intrinsics.areEqual(empty.getOrigin(), normalBgAodColor.getStart().getOrigin());
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void restoreColor(NormalBgAodColor normalBgAodColor) {
        OplusMediaCarouselView view = getView();
        BgAodData start = normalBgAodColor.getStart();
        Drawable blurDrawable = view.getBlurDrawable();
        PlatformBlurDrawable platformBlurDrawable = blurDrawable instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable : null;
        if (platformBlurDrawable != null) {
            AodBgViewWrapperKt.writeBgAodData(platformBlurDrawable, start);
        }
        this.aodBackgroundChanger.changeAodViewColor(getView(), 0.0f);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ void setAodColor(AodColor aodColor) {
        super.setAodColor(aodColor);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void updateColor(NormalBgAodColor normalBgAodColor, float f) {
        normalBgAodColor.setCurr(normalBgAodColor.getColor(f));
        OplusMediaCarouselView view = getView();
        BgAodData curr = normalBgAodColor.getCurr();
        Drawable blurDrawable = view.getBlurDrawable();
        PlatformBlurDrawable platformBlurDrawable = blurDrawable instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable : null;
        if (platformBlurDrawable != null) {
            AodBgViewWrapperKt.writeBgAodData(platformBlurDrawable, curr);
        }
        this.aodBackgroundChanger.changeAodViewColor(getView(), f);
    }

    public final void updateResource() {
        this.layer0 = TuplesKt.to(Integer.valueOf(this.context.getColor(2131099868)), Integer.valueOf(this.context.getColor(2131099867)));
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setView(OplusMediaCarouselView oplusMediaCarouselView) {
        this.view = oplusMediaCarouselView;
    }
}
