package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.content.res.Configuration;
import com.oplus.systemui.media.PageIndicator;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PageIndicatorAodWrapper implements AodViewWrapper {
    public static final Companion Companion = new Companion();
    private final Context context;
    private final Context nightContext;
    public PageIndicator view;

    public PageIndicatorAodWrapper(Context context) {
        this.context = context;
        Configuration configuration = new Configuration();
        configuration.uiMode = 32;
        this.nightContext = context.createConfigurationContext(configuration);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public PairAodColor createAodColor(int i) {
        Companion companion = Companion;
        return new PairAodColor(2, Companion.access$getCurrentColor(companion, getView()), Companion.access$getCurrentColor(companion, getView()), TuplesKt.to(Integer.valueOf(this.nightContext.getColor(2131102724)), Integer.valueOf(this.nightContext.getColor(2131102725))));
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ AodColor getAodColor() {
        return super.getAodColor();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public PageIndicator getView() {
        PageIndicator pageIndicator = this.view;
        if (pageIndicator != null) {
            return pageIndicator;
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public boolean isSame(PairAodColor pairAodColor) {
        return Intrinsics.areEqual(Companion.access$getCurrentColor(Companion, getView()), pairAodColor.getCurr());
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void restoreColor(PairAodColor pairAodColor) {
        Companion companion = Companion;
        PageIndicator view = getView();
        Pair start = pairAodColor.getStart();
        companion.getClass();
        view.setActiveColor(((Number) start.getFirst()).intValue());
        view.setInActiveColor(((Number) start.getSecond()).intValue());
        getView().invalidate();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ void setAodColor(AodColor aodColor) {
        super.setAodColor(aodColor);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void updateColor(PairAodColor pairAodColor, float f) {
        pairAodColor.setCurr(pairAodColor.getColor(f));
        Companion companion = Companion;
        PageIndicator view = getView();
        Pair curr = pairAodColor.getCurr();
        companion.getClass();
        view.setActiveColor(((Number) curr.getFirst()).intValue());
        view.setInActiveColor(((Number) curr.getSecond()).intValue());
        getView().invalidate();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setView(PageIndicator pageIndicator) {
        this.view = pageIndicator;
    }
}
