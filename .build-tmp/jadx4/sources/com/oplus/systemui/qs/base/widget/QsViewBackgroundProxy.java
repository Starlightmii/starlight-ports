package com.oplus.systemui.qs.base.widget;

import android.content.Context;
import com.android.systemui.plugins.qs.QSTile;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class QsViewBackgroundProxy implements QsViewBackground {
    public static final Companion Companion = new Companion();
    private final AtomicReference<QsViewBackground> backgroundReference = new AtomicReference<>();
    private QsViewOutlineProvider mBgOutlineProvider;
    private final QsViewInfoProvider qsView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final QsViewBackgroundProxy getQsStaticViewBackgroundProxy(QsStaticViewInfoProvider qsStaticViewInfoProvider) {
            return new QsStaticBackgroundProxy(qsStaticViewInfoProvider);
        }
    }

    public QsViewBackgroundProxy(QsStaticViewInfoProvider qsStaticViewInfoProvider) {
        this.qsView = qsStaticViewInfoProvider;
    }

    public static final QsViewBackgroundProxy getQsStaticViewBackgroundProxy(QsStaticViewInfoProvider qsStaticViewInfoProvider) {
        return Companion.getQsStaticViewBackgroundProxy(qsStaticViewInfoProvider);
    }

    public final QsViewOutlineProvider ensureBgOutlineProvider() {
        QsViewOutlineProvider bgOutlineProvider = this.mBgOutlineProvider;
        if (bgOutlineProvider == null) {
            bgOutlineProvider = this.qsView.getBgOutlineProvider();
        }
        this.mBgOutlineProvider = bgOutlineProvider;
        return bgOutlineProvider;
    }

    @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
    public Pair<Integer, Integer> getDeformBackgroundColor() {
        return getTargetQsViewBackground().getDeformBackgroundColor();
    }

    public abstract QsViewBackground getTargetQsViewBackground();

    public final Context getViewContext() {
        return this.qsView.getContext();
    }

    @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
    public void handleStateChanged(QSTile.State state) {
        switchBackgroundTo(getTargetQsViewBackground()).handleStateChanged(state);
    }

    @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
    public final void onBackgroundAttach() {
        switchBackgroundTo(getTargetQsViewBackground());
    }

    @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
    public final void onBackgroundDetach() {
        QsViewBackground qsViewBackground = this.backgroundReference.get();
        if (qsViewBackground != null) {
            qsViewBackground.onBackgroundDetach();
        }
    }

    @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
    public final void refreshViewBackground() {
        switchBackgroundTo(getTargetQsViewBackground()).refreshViewBackground();
    }

    @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
    public void setInDeform(boolean z) {
        getTargetQsViewBackground().setInDeform(z);
    }

    public synchronized QsViewBackground switchBackgroundTo(QsViewBackground qsViewBackground) {
        try {
            QsViewBackground andSet = this.backgroundReference.getAndSet(qsViewBackground);
            if (!Intrinsics.areEqual(andSet, qsViewBackground)) {
                if (andSet != null) {
                    andSet.onBackgroundDetach();
                }
                qsViewBackground.onBackgroundAttach();
            }
        } catch (Throwable th) {
            throw th;
        }
        return qsViewBackground;
    }
}
