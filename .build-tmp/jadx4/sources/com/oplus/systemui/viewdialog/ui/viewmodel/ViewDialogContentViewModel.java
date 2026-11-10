package com.oplus.systemui.viewdialog.ui.viewmodel;

import android.content.Context;
import android.support.v4.content.FileProvider$;
import android.view.CrossWindowBlurListeners;
import android.view.View;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.systemui.DependencyEx;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.notification.helper.OsenseHelper;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplus.systemui.statusbar.notification.helper.OsenseHelperImpl;
import com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager;
import com.oplus.systemui.viewdialog.ui.utils.ViewDialogJankTrace;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharedFlow;
import oplus.util.IOplusGcSupressionExt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContentViewModel {
    private static final Companion Companion = new Companion();
    private final ViewDialogAdapter adapter;
    private final DetailAnimationManager.AnimationCallBack animationManager;
    private final Flow backActionHandler;
    private final SharedFlow cancelAnimationActionEvent;
    private final ViewDialogContainerViewModel containerViewModel;
    private final Context context;
    private final SysUIViewDialogInteractor dialogInteractor;
    private final SharedFlow dismissDialogEvent;
    private final IOplusGcSupressionExt gcSupressionExt;
    private final CoroutineScope lifecycleScope;
    private final String logTag;
    private final OplusSeparateNotificationAndQSEx notificationAndQSEx;
    private final OsenseHelper osenseHelper;
    private final QSFragmentHelper.BeginClosingCallback qsClosingCallBack;
    private final WeakReference<QSFragmentHelper.BeginClosingCallback> qsClosingCallBackRef;
    private final NotificationShadeWindowView rootView;
    private final String viewTag;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Factory {
        ViewDialogContentViewModel create(CoroutineScope coroutineScope, ViewDialogAdapter viewDialogAdapter, ViewDialogContainerViewModel viewDialogContainerViewModel, IOplusGcSupressionExt iOplusGcSupressionExt);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContentViewModel$onCreate$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ ViewDialogContentViewModel this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    ((OsenseHelperImpl) this.this$0.osenseHelper).requestQsDialogAnimBoost();
                    break;
                default:
                    ((OsenseHelperImpl) this.this$0.osenseHelper).releaseQsDialogAnimBoost();
                    break;
            }
        }
    }

    public ViewDialogContentViewModel(CoroutineScope coroutineScope, ViewDialogAdapter viewDialogAdapter, ViewDialogContainerViewModel viewDialogContainerViewModel, IOplusGcSupressionExt iOplusGcSupressionExt, Context context, NotificationShadeWindowView notificationShadeWindowView, SysUIViewDialogInteractor sysUIViewDialogInteractor, OplusSeparateNotificationAndQSEx oplusSeparateNotificationAndQSEx, OsenseHelper osenseHelper) {
        this.lifecycleScope = coroutineScope;
        this.adapter = viewDialogAdapter;
        this.containerViewModel = viewDialogContainerViewModel;
        this.gcSupressionExt = iOplusGcSupressionExt;
        this.context = context;
        this.rootView = notificationShadeWindowView;
        this.dialogInteractor = sysUIViewDialogInteractor;
        this.notificationAndQSEx = oplusSeparateNotificationAndQSEx;
        this.osenseHelper = osenseHelper;
        String viewTag = viewDialogAdapter.getViewTag();
        this.viewTag = viewTag;
        this.logTag = FileProvider$.ExternalSyntheticOutline0.m("ViewDialogContentViewModel_", viewTag);
        this.dismissDialogEvent = sysUIViewDialogInteractor.getDismissDialogEvent();
        this.cancelAnimationActionEvent = sysUIViewDialogInteractor.getCancelAnimationActionEvent();
        this.backActionHandler = FlowKt.onEach(sysUIViewDialogInteractor.getBackActionEvent(), new ViewDialogContentViewModel$backActionHandler$1(this, null));
        ViewDialogContentViewModel$qsClosingCallBack$1 viewDialogContentViewModel$qsClosingCallBack$1 = new ViewDialogContentViewModel$qsClosingCallBack$1();
        viewDialogContentViewModel$qsClosingCallBack$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsClosingCallBack = viewDialogContentViewModel$qsClosingCallBack$1;
        this.qsClosingCallBackRef = new WeakReference<>(viewDialogContentViewModel$qsClosingCallBack$1);
        ViewDialogContentViewModel$animationManager$1 viewDialogContentViewModel$animationManager$1 = new ViewDialogContentViewModel$animationManager$1();
        viewDialogContentViewModel$animationManager$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.animationManager = viewDialogContentViewModel$animationManager$1;
    }

    public static final void access$onDialogAnimationEnd(ViewDialogContentViewModel viewDialogContentViewModel, boolean z, boolean z2) {
        viewDialogContentViewModel.gcSupressionExt.callGcDesupression(3);
        LogUtil.i$default(LogUtil.TAG_QS, viewDialogContentViewModel.logTag, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onDialogAnimationEnd: isLaunching=", " isInterrupt=", z, z2), null, 8);
        if (!z) {
            BuildersKt.launch$default(viewDialogContentViewModel.lifecycleScope, (CoroutineContext) null, (CoroutineStart) null, new ViewDialogContentViewModel$removeView$1(viewDialogContentViewModel, null), 3);
        }
        if (z && !z2) {
            viewDialogContentViewModel.setBehindViewAlpha(0.0f);
        } else if (!z) {
            viewDialogContentViewModel.setBehindViewAlpha(1.0f);
        }
        if (z) {
            ViewDialogJankTrace.INSTANCE.onDialogAnimationEnd(viewDialogContentViewModel.context, viewDialogContentViewModel.viewTag);
        }
    }

    public static final void access$onDialogAnimationStart(ViewDialogContentViewModel viewDialogContentViewModel, boolean z) {
        viewDialogContentViewModel.gcSupressionExt.callGcSupression(3, 800);
        if (z) {
            ViewDialogJankTrace.INSTANCE.onDialogAnimationStart(viewDialogContentViewModel.context, viewDialogContentViewModel.viewTag);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBehindViewAlpha(float f) {
        View viewFindViewById = this.rootView.findViewById(2131364179);
        OplusSeparateNotificationAndQSEx oplusSeparateNotificationAndQSEx = (OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class);
        if (!oplusSeparateNotificationAndQSEx.enableSeparateNotificationAndQS()) {
            viewFindViewById.setAlpha(f);
            return;
        }
        View qSPanelView = oplusSeparateNotificationAndQSEx.getQSPanelView();
        if (qSPanelView != null) {
            qSPanelView.setAlpha(f);
        }
        if (QsUtil.isPowerSavingMode() || !CrossWindowBlurListeners.getInstance().isCrossWindowBlurEnabled()) {
            viewFindViewById.setAlpha(f);
        } else if (f == 1.0f) {
            if (Intrinsics.areEqual(viewFindViewById != null ? Float.valueOf(viewFindViewById.getAlpha()) : null, 1.0f)) {
                return;
            }
            viewFindViewById.setAlpha(f);
        }
    }

    public final DetailAnimationManager.AnimationCallBack getAnimationManager() {
        return this.animationManager;
    }

    public final Flow getBackActionHandler() {
        return this.backActionHandler;
    }

    public final SharedFlow getCancelAnimationActionEvent() {
        return this.cancelAnimationActionEvent;
    }

    public final int getContentViewId() {
        return this.adapter.getContentViewId();
    }

    public final SharedFlow getDismissDialogEvent() {
        return this.dismissDialogEvent;
    }

    public final int getHideContentViewId() {
        return this.adapter.getHideContentViewId();
    }

    public final View getOriginView() {
        View expandableView = this.adapter.getExpandableView();
        return expandableView == null ? this.rootView.findViewWithTag(this.viewTag) : expandableView;
    }

    public final void onCreate() {
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "onCreate: ", null, 8);
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        WeakReference<QSFragmentHelper.BeginClosingCallback> weakReference = this.qsClosingCallBackRef;
        if (!qSFragmentHelper.mCallbacks.contains(weakReference)) {
            qSFragmentHelper.mCallbacks.add(weakReference);
        }
        ViewDialogJankTrace.INSTANCE.onDialogStartShow(this.viewTag);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(anonymousClass1);
        this.adapter.onShow();
    }

    public final void onDestroy() {
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "onDestroy: ", null, 8);
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        qSFragmentHelper.mCallbacks.remove(this.qsClosingCallBackRef);
        this.adapter.onDismiss();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(anonymousClass1);
    }
}
