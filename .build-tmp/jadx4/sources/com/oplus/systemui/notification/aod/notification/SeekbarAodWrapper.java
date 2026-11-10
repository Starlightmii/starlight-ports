package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.content.res.Configuration;
import com.coui.appcompat.seekbar.COUISeekBar;
import kotlin.Pair;
import kotlin.TuplesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeekbarAodWrapper implements AodViewWrapper {
    public static final Companion Companion = new Companion();
    private final Context context;
    private final Context nightContext;
    public COUISeekBar view;

    public SeekbarAodWrapper(Context context) {
        this.context = context;
        Configuration configuration = new Configuration();
        configuration.uiMode = 32;
        this.nightContext = context.createConfigurationContext(configuration);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public PairAodColor createAodColor(int i) {
        Companion companion = Companion;
        COUISeekBar view = getView();
        companion.getClass();
        Pair pair = TuplesKt.to(Integer.valueOf(view.getContext().getColor(2131100882)), Integer.valueOf(view.getContext().getColor(2131100714)));
        COUISeekBar view2 = getView();
        return new PairAodColor(2, pair, TuplesKt.to(Integer.valueOf(view2.getContext().getColor(2131100882)), Integer.valueOf(view2.getContext().getColor(2131100714))), TuplesKt.to(Integer.valueOf(this.nightContext.getColor(2131100882)), Integer.valueOf(this.nightContext.getColor(2131100714))));
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ AodColor getAodColor() {
        return super.getAodColor();
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public COUISeekBar getView() {
        COUISeekBar cOUISeekBar = this.view;
        if (cOUISeekBar != null) {
            return cOUISeekBar;
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void restoreColor(PairAodColor pairAodColor) {
        Companion.access$setCurrentColor(Companion, getView(), pairAodColor.getStart());
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public /* bridge */ /* synthetic */ void setAodColor(AodColor aodColor) {
        super.setAodColor(aodColor);
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void updateColor(PairAodColor pairAodColor, float f) {
        pairAodColor.setCurr(pairAodColor.getColor(f));
        Companion.access$setCurrentColor(Companion, getView(), pairAodColor.getCurr());
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public void setView(COUISeekBar cOUISeekBar) {
        this.view = cOUISeekBar;
    }

    @Override // com.oplus.systemui.notification.aod.notification.AodViewWrapper
    public boolean isSame(PairAodColor pairAodColor) {
        return true;
    }
}
