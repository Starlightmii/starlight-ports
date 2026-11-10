package com.oplus.systemui.qs.detail.interactor;

import android.content.Context;
import android.support.v4.content.FileProvider$;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.oplus.systemui.qs.detail.binder.QSDetailContainerBinder;
import com.oplus.systemui.qs.detail.binder.QSDetailFooterBinder;
import com.oplus.systemui.qs.detail.binder.QSDetailHeaderBinder;
import com.oplus.systemui.qs.detail.data.QSDetailRepository;
import com.oplus.systemui.qs.detail.data.QSDetailRepositoryImpl;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailContainerViewModel;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailFooterViewModel;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailHeaderViewModel;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSDetailInteractor implements QSDetailInteractor, ViewDialogAdapter, QSDetailRepository {
    private final DetailAdapter adapter;
    private final QSDetailContainerViewModel.Factory contentViewModelFactory;
    private final QSDetailRepositoryImpl detailRepository;
    private final QSDetailFooterViewModel.Factory footerViewModelFactory;
    private final QSDetailHeaderViewModel.Factory headerViewModelFactory;
    private final SysUIViewDialogInteractor viewDialogInteractor;
    private final String logTag = FileProvider$.ExternalSyntheticOutline0.m("QSDetailInteractor_", getViewTag());
    private final AtomicReference<Boolean> isShowing = new AtomicReference<>(Boolean.FALSE);
    private WeakReference<View> expandableViewRef = new WeakReference<>(null);
    private final DisposableHandles disposableHandles = new DisposableHandles();
    private final AtomicReference<View> qsDetail = new AtomicReference<>(null);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Factory {
        OplusQSDetailInteractor create(DetailAdapter detailAdapter);
    }

    public OplusQSDetailInteractor(DetailAdapter detailAdapter, SysUIViewDialogInteractor sysUIViewDialogInteractor, QSDetailHeaderViewModel.Factory factory, QSDetailContainerViewModel.Factory factory2, QSDetailFooterViewModel.Factory factory3, QSDetailRepositoryImpl qSDetailRepositoryImpl) {
        this.adapter = detailAdapter;
        this.viewDialogInteractor = sysUIViewDialogInteractor;
        this.headerViewModelFactory = factory;
        this.contentViewModelFactory = factory2;
        this.footerViewModelFactory = factory3;
        this.detailRepository = qSDetailRepositoryImpl;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public /* bridge */ /* synthetic */ ViewDialogAnimationManager createAnimationManager(View view) {
        return super.createAnimationManager(view);
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public View createDialogView(Context context) {
        View viewPrepareDetailView = prepareDetailView();
        this.disposableHandles.plusAssign(QSDetailHeaderBinder.INSTANCE.bind(viewPrepareDetailView, this.adapter, this, this.headerViewModelFactory));
        this.disposableHandles.plusAssign(QSDetailContainerBinder.INSTANCE.bind(viewPrepareDetailView, this.adapter, this, this.contentViewModelFactory));
        this.disposableHandles.plusAssign(QSDetailFooterBinder.INSTANCE.bind(viewPrepareDetailView, this.adapter, this, this.footerViewModelFactory));
        return viewPrepareDetailView;
    }

    public final boolean dismiss(boolean z) {
        boolean zIsShowing = isShowing();
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, KeyguardLogger$.ExternalSyntheticOutline0.m("dismiss: isShowing=", zIsShowing), null, 8);
        if (!zIsShowing) {
            return false;
        }
        this.viewDialogInteractor.dismissDialog(z ? "instant" : "animate");
        return true;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public int getContentViewId() {
        return 2131362831;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public View getExpandableView() {
        return this.expandableViewRef.get();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public /* bridge */ /* synthetic */ int getHideContentViewId() {
        return super.getHideContentViewId();
    }

    @Override // com.oplus.systemui.qs.detail.data.QSDetailRepository
    public Flow getSwitchState() {
        return this.detailRepository.getSwitchState();
    }

    @Override // com.oplus.systemui.qs.detail.data.QSDetailRepository
    public Context getThemeContext() {
        return this.detailRepository.getThemeContext();
    }

    @Override // com.oplus.systemui.qs.detail.data.QSDetailRepository
    public Flow getUpdateConfigEvent() {
        return this.detailRepository.getUpdateConfigEvent();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public String getViewTag() {
        return this.adapter.getTag();
    }

    public final boolean isDarkTheme() {
        return this.detailRepository.isDarkTheme();
    }

    public final boolean isShowing() {
        return this.isShowing.get().booleanValue();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final boolean onBackAction() {
        return this.adapter.onBackAction();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onDismiss() {
        this.isShowing.set(Boolean.FALSE);
        this.adapter.onDismiss();
        releaseDetailView();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onIntercept() {
        releaseDetailView();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onShow() {
        this.isShowing.set(Boolean.TRUE);
        this.adapter.onShow();
    }

    public final View prepareDetailView() {
        ViewStub viewStub;
        View viewCreateDetailView;
        ViewStub viewStub2;
        View view = this.qsDetail.get();
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, ChildHelper$.ExternalSyntheticOutline0.m(view, "prepareDetailView: check qsDetail="), null, 8);
        if (view != null) {
            return view;
        }
        Context contextUpdateThemeContext = updateThemeContext();
        View viewInflate = LayoutInflater.from(contextUpdateThemeContext).inflate(2131559516, (ViewGroup) null);
        if (this.adapter.hasHeader()) {
            ViewStub viewStub3 = (ViewStub) viewInflate.findViewById(2131362836);
            View viewInflate2 = viewStub3 != null ? viewStub3.inflate() : null;
            Boolean toggleState = this.adapter.getToggleState();
            if (toggleState != null) {
                updateSwitchState(toggleState.booleanValue());
                if (viewInflate2 != null && (viewStub2 = (ViewStub) viewInflate2.findViewById(2131362847)) != null) {
                    viewStub2.inflate();
                }
            }
        }
        ViewGroup viewGroup = (ViewGroup) viewInflate.findViewById(2131362832);
        if (viewGroup != null && (viewCreateDetailView = this.adapter.createDetailView(contextUpdateThemeContext, viewInflate, viewGroup, false)) != null) {
            viewGroup.addView(viewCreateDetailView);
        }
        if (this.adapter.hasFooter() && (viewStub = (ViewStub) viewInflate.findViewById(2131362834)) != null) {
            viewStub.inflate();
        }
        this.qsDetail.set(viewInflate);
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "prepareDetailView: prepare qsDetail=" + this.qsDetail, null, 8);
        return viewInflate;
    }

    public final void releaseDetailView() {
        if (isShowing()) {
            return;
        }
        View andSet = this.qsDetail.getAndSet(null);
        if (andSet != null) {
            LogUtil.i$default(LogUtil.TAG_QS, this.logTag, ChildHelper$.ExternalSyntheticOutline0.m(andSet, "releaseDetailView: qsDetail="), null, 8);
        }
        this.disposableHandles.dispose();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public void setExpandableView(View view) {
        this.expandableViewRef = new WeakReference<>(view);
    }

    @Override // com.oplus.systemui.qs.detail.interactor.QSDetailInteractor
    public boolean show(View view) {
        boolean zIsShowing = isShowing();
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, KeyguardLogger$.ExternalSyntheticOutline0.m("show: isShowing=", zIsShowing), null, 8);
        if (zIsShowing) {
            return false;
        }
        setExpandableView(view);
        this.viewDialogInteractor.showDialog(this);
        return true;
    }

    public final boolean updateSwitchState(boolean z) {
        boolean zUpdateSwitchState = this.detailRepository.updateSwitchState(z);
        if (!zUpdateSwitchState) {
            LogUtil.w$default(8, LogUtil.TAG_QS, this.logTag, "updateSwitchState: tryEmit fail");
        }
        return zUpdateSwitchState;
    }

    @Override // com.oplus.systemui.qs.detail.data.QSDetailRepository
    public Context updateThemeContext() {
        return this.detailRepository.updateThemeContext();
    }
}
