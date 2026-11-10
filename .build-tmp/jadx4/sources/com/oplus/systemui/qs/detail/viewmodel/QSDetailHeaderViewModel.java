package com.oplus.systemui.qs.detail.viewmodel;

import com.android.systemui.plugins.qs.DetailAdapter;
import com.oplus.systemui.qs.detail.data.QSDetailRepository;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailHeaderViewModel {
    private final DetailAdapter detailAdapter;
    private final QSDetailRepository detailRepository;
    private final Flow switchState;
    private final Flow titleTextColor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Factory {
        QSDetailHeaderViewModel create(DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository);
    }

    public QSDetailHeaderViewModel(DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository) {
        this.detailAdapter = detailAdapter;
        this.detailRepository = qSDetailRepository;
        Flow updateConfigEvent = qSDetailRepository.getUpdateConfigEvent();
        QSDetailHeaderViewModel$special$$inlined$map$1 qSDetailHeaderViewModel$special$$inlined$map$1 = new QSDetailHeaderViewModel$special$$inlined$map$1();
        qSDetailHeaderViewModel$special$$inlined$map$1.$this_unsafeTransform$inlined = updateConfigEvent;
        qSDetailHeaderViewModel$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.titleTextColor = FlowKt.distinctUntilChanged(qSDetailHeaderViewModel$special$$inlined$map$1);
        this.switchState = qSDetailRepository.getSwitchState();
    }

    public final Flow getSwitchState() {
        return this.switchState;
    }

    public final CharSequence getTitle() {
        return this.detailAdapter.getTitle();
    }

    public final Flow getTitleTextColor() {
        return this.titleTextColor;
    }

    public final void setToggleState(boolean z) {
        this.detailAdapter.setToggleState(z);
    }

    public final boolean turnOnSwitchWhenAttach() {
        return this.detailAdapter.turnOnSwitchWhenAttach();
    }
}
