package com.oplus.systemui.qs.media;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.MediaViewProvider;
import com.android.systemui.animation.OplusMediaPanelAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.common.dialog.OplusThemeSystemUiDialog;
import com.oplus.systemui.media.output.OplusMediaOutputPageController;
import com.oplus.systemui.media.output.data.OutputAudioSharingInteractor;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager;
import com.oplusos.systemui.animation.MediaDialogLaunchAnimatorController;
import com.oplusos.systemui.animation.MediaViewAnimatorController;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaDialogController implements OplusThemeSystemUiDialog.ILifeCycleCallback, ViewDialogAdapter, DetailAnimationManager.AnimationCallBack {
    public static final Companion Companion = new Companion();
    private static final String SUPER_POWER_SAVE_DESKTOP_APP_LIST = "super_power_save_desktop_app_list";
    public static final String TAG = "OplusQsMediaDialogController";
    private final OutputAudioSharingInteractor audioSharingInteractor;
    private WeakReference<View> expandableViewRef = new WeakReference<>(null);
    private OplusQsMediaDetailPanelDialog mDialog;
    private final ContentObserver mSuperPowerListObserve;
    private final MediaDataManager mediaDataManager;
    private final OplusQsMediaDialogController$mediaDataUpdateListener$1 mediaDataUpdateListener;
    private MediaDialogLaunchAnimatorController mediaDialogAnimatorController;
    private final OplusMediaOutputPageController mediaOutputDetailAdapter;
    private final CoroutineScope scope;
    private final SeekBarViewModel.Factory seekBarViewModelFactory;
    private final SysUIViewDialogInteractor viewDialogInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQsMediaDialogController(MediaDataManager mediaDataManager, SeekBarViewModel.Factory factory, CoroutineScope coroutineScope, SysUIViewDialogInteractor sysUIViewDialogInteractor, OplusMediaOutputPageController oplusMediaOutputPageController, OutputAudioSharingInteractor outputAudioSharingInteractor) {
        this.mediaDataManager = mediaDataManager;
        this.seekBarViewModelFactory = factory;
        this.scope = coroutineScope;
        this.viewDialogInteractor = sysUIViewDialogInteractor;
        this.mediaOutputDetailAdapter = oplusMediaOutputPageController;
        this.audioSharingInteractor = outputAudioSharingInteractor;
        OplusQsMediaDialogController$mediaDataUpdateListener$1 oplusQsMediaDialogController$mediaDataUpdateListener$1 = new OplusQsMediaDialogController$mediaDataUpdateListener$1();
        oplusQsMediaDialogController$mediaDataUpdateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mediaDataUpdateListener = oplusQsMediaDialogController$mediaDataUpdateListener$1;
        final Handler handler = new Handler();
        this.mSuperPowerListObserve = new ContentObserver(handler) { // from class: com.oplus.systemui.qs.media.OplusQsMediaDialogController$mSuperPowerListObserve$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog = this.this$0.mDialog;
                if (oplusQsMediaDetailPanelDialog != null) {
                    oplusQsMediaDetailPanelDialog.updateSuperPowerList();
                }
            }
        };
    }

    public static final void access$finishMediaPanelAnimationEarly(OplusQsMediaDialogController oplusQsMediaDialogController) {
        OplusMediaPanelAnimator oplusMediaPanelAnimator;
        OplusNotificationLogger.logMediaPanel(TAG, "finishMediaPanelAnimationEarly: viewpager_drag");
        MediaDialogLaunchAnimatorController mediaDialogLaunchAnimatorController = oplusQsMediaDialogController.mediaDialogAnimatorController;
        if (mediaDialogLaunchAnimatorController != null && (oplusMediaPanelAnimator = mediaDialogLaunchAnimatorController.getOplusMediaPanelAnimator()) != null) {
            oplusMediaPanelAnimator.finishExpandEarly("viewpager_drag", true);
        }
        oplusQsMediaDialogController.viewDialogInteractor.cancelDetailAnimation();
    }

    public final void clearMediaPanelAnimator() {
        OplusMediaPanelAnimator oplusMediaPanelAnimator;
        OplusNotificationLogger.logMediaPanel(TAG, "OplusQsMediaDialogController::clearMediaPanelAnimator");
        MediaDialogLaunchAnimatorController mediaDialogLaunchAnimatorController = this.mediaDialogAnimatorController;
        if (mediaDialogLaunchAnimatorController != null && (oplusMediaPanelAnimator = mediaDialogLaunchAnimatorController.getOplusMediaPanelAnimator()) != null) {
            oplusMediaPanelAnimator.finishExpand(true);
        }
        MediaDialogLaunchAnimatorController mediaDialogLaunchAnimatorController2 = this.mediaDialogAnimatorController;
        if (mediaDialogLaunchAnimatorController2 != null) {
            mediaDialogLaunchAnimatorController2.resetView();
        }
        this.mediaDialogAnimatorController = null;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public /* bridge */ /* synthetic */ ViewDialogAnimationManager createAnimationManager(View view) {
        return super.createAnimationManager(view);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.View, com.oplus.systemui.qs.media.OplusQsMediaDetailPanelDialog] */
    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public View createDialogView(Context context) {
        SeekBarViewModel.Factory factory = this.seekBarViewModelFactory;
        CoroutineScope coroutineScope = this.scope;
        OutputAudioSharingInteractor outputAudioSharingInteractor = this.audioSharingInteractor;
        OplusQsMediaDialogController$createQsMediaDialog$1 oplusQsMediaDialogController$createQsMediaDialog$1 = new OplusQsMediaDialogController$createQsMediaDialog$1();
        oplusQsMediaDialogController$createQsMediaDialog$1.this$0 = this;
        VarHandle.storeStoreFence();
        ?? oplusQsMediaDetailPanelDialog = new OplusQsMediaDetailPanelDialog(context, factory, coroutineScope, outputAudioSharingInteractor, oplusQsMediaDialogController$createQsMediaDialog$1, null, 0);
        this.mDialog = oplusQsMediaDetailPanelDialog;
        return oplusQsMediaDetailPanelDialog;
    }

    @Override // com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager.AnimationCallBack
    public TransitionAnimator.Controller createEndController(Pair<? extends View, Float> pair, Triple<? extends View, Integer, ? extends Function0> triple, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsMediaDialogController-->".concat("createEndController: isLaunching = " + z + " fromData:" + pair + ", targetData:" + triple));
        }
        if (!ScrimUtil.isLowGaussianLevel(((View) triple.getFirst()).getContext())) {
            QsColorUtil.INSTANCE.getClass();
            if (!QsColorUtil.isSuperPowerSaveState && !isShowMediaOutputDialog() && z) {
                View view = (View) triple.getFirst();
                Function0 function0 = (Function0) triple.getThird();
                int iIntValue = ((Number) triple.getSecond()).intValue();
                Object first = pair.getFirst();
                MediaDialogLaunchAnimatorController mediaDialogLaunchAnimatorController = new MediaDialogLaunchAnimatorController(view, function0, z, iIntValue, first instanceof MediaViewProvider ? (MediaViewProvider) first : null, needUseAlpha(((View) triple.getFirst()).getContext()), null);
                this.mediaDialogAnimatorController = mediaDialogLaunchAnimatorController;
                return mediaDialogLaunchAnimatorController;
            }
        }
        return null;
    }

    @Override // com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager.AnimationCallBack
    public TransitionAnimator.Controller createStartController(Pair<? extends View, Float> pair, Triple<? extends View, Integer, ? extends Function0> triple, boolean z) {
        OplusMediaPanelAnimator oplusMediaPanelAnimator;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsMediaDialogController-->".concat("createStartController: isLaunching = " + z + " fromData:" + pair + ", targetData:" + triple));
        }
        if (!z) {
            pair.getFirst();
        }
        if (!ScrimUtil.isLowGaussianLevel(((View) triple.getFirst()).getContext())) {
            QsColorUtil.INSTANCE.getClass();
            if (!QsColorUtil.isSuperPowerSaveState && !isShowMediaOutputDialog()) {
                if (!z) {
                    MediaDialogLaunchAnimatorController mediaDialogLaunchAnimatorController = this.mediaDialogAnimatorController;
                    OplusMediaPanelAnimator.MediaAnimValue interruptedAnimValue = (mediaDialogLaunchAnimatorController == null || (oplusMediaPanelAnimator = mediaDialogLaunchAnimatorController.getOplusMediaPanelAnimator()) == null) ? null : oplusMediaPanelAnimator.getInterruptedAnimValue();
                    View view = (View) triple.getFirst();
                    Function0 function0 = (Function0) triple.getThird();
                    int iIntValue = ((Number) triple.getSecond()).intValue();
                    Object first = pair.getFirst();
                    MediaDialogLaunchAnimatorController mediaDialogLaunchAnimatorController2 = new MediaDialogLaunchAnimatorController(view, function0, z, iIntValue, first instanceof MediaViewProvider ? (MediaViewProvider) first : null, needUseAlpha(((View) triple.getFirst()).getContext()), interruptedAnimValue);
                    this.mediaDialogAnimatorController = mediaDialogLaunchAnimatorController2;
                    return mediaDialogLaunchAnimatorController2;
                }
                View view2 = (View) pair.getFirst();
                if (view2 != null) {
                    return new MediaViewAnimatorController(view2, (Float) pair.getSecond(), z);
                }
            }
        }
        return null;
    }

    public final void destroy(boolean z) {
        clearMediaPanelAnimator();
        this.viewDialogInteractor.dismissDialog(z ? "instant" : "animate");
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public int getContentViewId() {
        return 2131364325;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public View getExpandableView() {
        return this.expandableViewRef.get();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public int getHideContentViewId() {
        if (isShowMediaOutputDialog()) {
            return 2131362831;
        }
        return getContentViewId();
    }

    public final MediaDialogLaunchAnimatorController getMediaDialogAnimatorController() {
        return this.mediaDialogAnimatorController;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public String getViewTag() {
        return TAG;
    }

    public final boolean isShowMediaOutputDialog() {
        OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog = this.mDialog;
        return (oplusQsMediaDetailPanelDialog == null || oplusQsMediaDetailPanelDialog.getIsMediaDetailStatus()) ? false : true;
    }

    public final boolean needUseAlpha(Context context) {
        if (QsColorUtil.isGlobalThemeApplied(context)) {
            return true;
        }
        FlavorTwoFeatureOption.INSTANCE.getClass();
        return FlavorTwoFeatureOption.isFlavorTwoDeviceExp();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager.AnimationCallBack
    public final void onAnimationEnd(boolean z, boolean z2) {
        OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog = this.mDialog;
        if (oplusQsMediaDetailPanelDialog != null) {
            oplusQsMediaDetailPanelDialog.onAnimationEnd(z, z2);
        }
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager.AnimationCallBack
    public final void onAnimationStart(boolean z) {
        OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog = this.mDialog;
        if (oplusQsMediaDetailPanelDialog != null) {
            OplusNotificationLogger.logMediaPanel("OplusQsMediaDetailPanelDialog", "onAnimationStart: isLaunching:" + z);
            oplusQsMediaDetailPanelDialog.isLaunching = z;
            oplusQsMediaDetailPanelDialog.isAnimating = true;
        }
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final boolean onBackAction() {
        if (isShowMediaOutputDialog()) {
            return this.mediaOutputDetailAdapter.onBackAction();
        }
        return false;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onDismiss() {
        onStop();
        if (isShowMediaOutputDialog()) {
            this.mediaOutputDetailAdapter.onDismiss();
        }
        OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog = this.mDialog;
        if (oplusQsMediaDetailPanelDialog != null) {
            oplusQsMediaDetailPanelDialog.onStop();
        }
        this.mDialog = null;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onShow() {
        onStart();
    }

    public final void onStart() {
        OplusNotificationLogger.logMediaPanel(TAG, "OplusQsMediaDialogController::onStart");
        this.mediaDataManager.addListener(this.mediaDataUpdateListener);
        ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("super_power_save_desktop_app_list"), this.mSuperPowerListObserve);
    }

    public final void onStop() {
        OplusNotificationLogger.logMediaPanel(TAG, "OplusQsMediaDialogController::onStop");
        clearMediaPanelAnimator();
        ((ScrimControllerEx) DependencyEx.sDependency.getDependency(ScrimControllerEx.class)).setQsDialogVisible(false);
        this.mediaDataManager.removeListener(this.mediaDataUpdateListener);
        ObserverDispatcher.getInstance().unregisterObserver(this.mSuperPowerListObserve);
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public void setExpandableView(View view) {
        this.expandableViewRef = new WeakReference<>(view);
    }

    public final void setMediaDialogAnimatorController(MediaDialogLaunchAnimatorController mediaDialogLaunchAnimatorController) {
        this.mediaDialogAnimatorController = mediaDialogLaunchAnimatorController;
    }

    public final void showPrompt(View view) {
        setExpandableView(view);
        this.viewDialogInteractor.showDialog(this);
    }
}
