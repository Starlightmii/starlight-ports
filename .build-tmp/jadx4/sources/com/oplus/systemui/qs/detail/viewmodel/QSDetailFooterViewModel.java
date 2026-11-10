package com.oplus.systemui.qs.detail.viewmodel;

import android.content.Intent;
import android.support.v4.content.FileProvider$;
import android.view.View;
import android.widget.TextView;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import com.oplus.systemui.qs.detail.data.QSDetailRepository;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailFooterViewModel {
    private final DetailAdapter detailAdapter;
    private final QSDetailRepository detailRepository;
    private final Flow dividerColor;
    private final Flow dividerVisible;
    private final String logTag;
    private final OplusQSActivityStarter oplusQSActivityStarter;
    private final Flow settingsBtnTextColor;
    private final SysUIViewDialogInteractor viewDialogInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Factory {
        QSDetailFooterViewModel create(DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository);
    }

    public QSDetailFooterViewModel(DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository, SysUIViewDialogInteractor sysUIViewDialogInteractor, OplusQSActivityStarter oplusQSActivityStarter) {
        this.detailAdapter = detailAdapter;
        this.detailRepository = qSDetailRepository;
        this.viewDialogInteractor = sysUIViewDialogInteractor;
        this.oplusQSActivityStarter = oplusQSActivityStarter;
        this.logTag = FileProvider$.ExternalSyntheticOutline0.m("QSDetailFooterViewModel_", detailAdapter.getTag());
        Flow updateConfigEvent = qSDetailRepository.getUpdateConfigEvent();
        QSDetailFooterViewModel$special$$inlined$map$1 qSDetailFooterViewModel$special$$inlined$map$1 = new QSDetailFooterViewModel$special$$inlined$map$1(0);
        qSDetailFooterViewModel$special$$inlined$map$1.$this_unsafeTransform$inlined = updateConfigEvent;
        qSDetailFooterViewModel$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.dividerVisible = qSDetailFooterViewModel$special$$inlined$map$1;
        Flow updateConfigEvent2 = qSDetailRepository.getUpdateConfigEvent();
        QSDetailFooterViewModel$special$$inlined$map$1 qSDetailFooterViewModel$special$$inlined$map$2 = new QSDetailFooterViewModel$special$$inlined$map$1(1);
        qSDetailFooterViewModel$special$$inlined$map$2.$this_unsafeTransform$inlined = updateConfigEvent2;
        qSDetailFooterViewModel$special$$inlined$map$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.dividerColor = FlowKt.distinctUntilChanged(qSDetailFooterViewModel$special$$inlined$map$2);
        Flow updateConfigEvent3 = qSDetailRepository.getUpdateConfigEvent();
        QSDetailFooterViewModel$special$$inlined$map$1 qSDetailFooterViewModel$special$$inlined$map$3 = new QSDetailFooterViewModel$special$$inlined$map$1(2);
        qSDetailFooterViewModel$special$$inlined$map$3.$this_unsafeTransform$inlined = updateConfigEvent3;
        qSDetailFooterViewModel$special$$inlined$map$3.this$0 = this;
        VarHandle.storeStoreFence();
        this.settingsBtnTextColor = qSDetailFooterViewModel$special$$inlined$map$3;
    }

    public final Flow getDividerColor() {
        return this.dividerColor;
    }

    public final Flow getDividerVisible() {
        return this.dividerVisible;
    }

    public final Flow getSettingsBtnTextColor() {
        return this.settingsBtnTextColor;
    }

    public final void onSettingsBtnClick(View view) {
        Intent settingsIntent = this.detailAdapter.getSettingsIntent();
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "settingsBtnClickListener: onClick settingsIntent：=" + settingsIntent, null, 8);
        this.oplusQSActivityStarter.onAnimationButtonClicked(view, settingsIntent, OplusQSActivityStarter.QSTransitionAnimator.DIALOG);
    }

    public final void setDetailSettingsButton(TextView textView) {
        this.detailAdapter.setDetailSettingsButton(textView);
    }
}
