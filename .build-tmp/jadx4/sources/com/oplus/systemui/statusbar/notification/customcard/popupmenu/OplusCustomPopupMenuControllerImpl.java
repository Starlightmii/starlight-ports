package com.oplus.systemui.statusbar.notification.customcard.popupmenu;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.framerate.BoostFrameRateManager;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationsInteractor;
import com.oplusos.systemui.common.blurability.motion.BlurUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomPopupMenuControllerImpl implements OplusCustomPopupMenuController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusCustomPopupMenuController";
    private static final String WINDOW_NAME = "OplusCustomPopupMenuPanelWindow";
    private final Lazy blurUtils$delegate;
    private final Context context;
    private final CoroutineScope coroutineScope;
    private OplusCustomMenuPanelView currentPanelContainer;
    private final PopupWindow.OnDismissListener dismissListener;
    public boolean isOnStatusBar;
    public boolean isPopupMenuShowing;
    private final KeyguardDismissUtil keyguardDismissUtil;
    private KeyguardUpdateMonitor keyguardUpdateMonitor;
    private final KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback;
    private final KeyguardViewMediator keyguardViewMediator;
    private final OplusLiveAlertNotificationsInteractor liveAlertInteractor;
    private final NotifCollection notifCollection;
    private Function0 onConfigChanged;
    private final Lazy oplusCustomPopupMenu$delegate;
    private OplusCustomRow oplusCustomRow;
    private View originSeedling;
    private View showingSeedling;
    private final OplusCustomPopupMenuControllerImpl$sysStateControllerListener$1 sysStateControllerListener;
    private final SysUiStateControllerImpl sysUiStateController;
    private final ViewBlurManager viewBlurManager;
    private final Lazy viewCloner$delegate;
    private final Function0 windowInsetChangeListener;
    private final Lazy windowManager$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$RPAevUWDSyXmIr-5kZ-3u3q4oWM, reason: not valid java name */
    public static Unit m386$r8$lambda$RPAevUWDSyXmIr5kZ3u3q4oWM(Ref.BooleanRef booleanRef, NotificationStackScrollLayoutExt notificationStackScrollLayoutExt, OplusCustomPopupMenuControllerImpl oplusCustomPopupMenuControllerImpl, View view, CustomRowEntry customRowEntry, boolean z, String str) {
        if (booleanRef.element) {
            notificationStackScrollLayoutExt.setWindowInsetChangedListener((Function0) null);
            oplusCustomPopupMenuControllerImpl.onConfigChanged = null;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusCustomPopupMenuController-->reopenSettingPanel");
            }
            oplusCustomPopupMenuControllerImpl.showPopupMenu(view, false, customRowEntry, z, str);
        }
        return Unit.INSTANCE;
    }

    public static WindowManager $r8$lambda$YLSIcoToDgWQ3EhJL7iUFYd_E7g(OplusCustomPopupMenuControllerImpl oplusCustomPopupMenuControllerImpl) {
        return (WindowManager) oplusCustomPopupMenuControllerImpl.context.getSystemService("window");
    }

    public OplusCustomPopupMenuControllerImpl(Context context, ViewBlurManager viewBlurManager, SysUiStateControllerImpl sysUiStateControllerImpl, KeyguardDismissUtil keyguardDismissUtil, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardViewMediator keyguardViewMediator, OplusLiveAlertNotificationsInteractor oplusLiveAlertNotificationsInteractor, NotifCollection notifCollection) {
        this.context = context;
        this.viewBlurManager = viewBlurManager;
        this.sysUiStateController = sysUiStateControllerImpl;
        this.keyguardDismissUtil = keyguardDismissUtil;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.keyguardViewMediator = keyguardViewMediator;
        this.liveAlertInteractor = oplusLiveAlertNotificationsInteractor;
        this.notifCollection = notifCollection;
        OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0 oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0 = new OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0(0);
        oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusCustomPopupMenu$delegate = LazyKt__LazyJVMKt.lazy(oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0);
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob((Job) null).plus(Dispatchers.getMain()));
        this.blurUtils$delegate = LazyKt__LazyJVMKt.lazy(new OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda1());
        OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0 oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda1 = new OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0(1);
        oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.windowInsetChangeListener = oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda1;
        OplusCustomPopupMenuControllerImpl$dismissListener$1 oplusCustomPopupMenuControllerImpl$dismissListener$1 = new OplusCustomPopupMenuControllerImpl$dismissListener$1();
        oplusCustomPopupMenuControllerImpl$dismissListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.dismissListener = oplusCustomPopupMenuControllerImpl$dismissListener$1;
        OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0 oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda2 = new OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0(2);
        oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.windowManager$delegate = LazyKt__LazyJVMKt.lazy(oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda2);
        OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0 oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda3 = new OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda0(3);
        oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.viewCloner$delegate = LazyKt__LazyJVMKt.lazy(oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda3);
        OplusCustomPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1 oplusCustomPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1 = new OplusCustomPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1();
        oplusCustomPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardUpdateMonitorCallback = oplusCustomPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1;
        OplusCustomPopupMenuControllerImpl$sysStateControllerListener$1 oplusCustomPopupMenuControllerImpl$sysStateControllerListener$1 = new OplusCustomPopupMenuControllerImpl$sysStateControllerListener$1();
        oplusCustomPopupMenuControllerImpl$sysStateControllerListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.sysStateControllerListener = oplusCustomPopupMenuControllerImpl$sysStateControllerListener$1;
        this.keyguardUpdateMonitor.registerCallback(oplusCustomPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1);
    }

    public static final void access$initPanelView(OplusCustomPopupMenuControllerImpl oplusCustomPopupMenuControllerImpl, OplusCustomMenuPanelView oplusCustomMenuPanelView) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "initPanelView:", null, 8);
        NotificationStackScrollLayoutExt notificationStackScrollLayoutExt = (NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class);
        if (notificationStackScrollLayoutExt != null) {
            notificationStackScrollLayoutExt.setWindowInsetChangedListener(oplusCustomPopupMenuControllerImpl.windowInsetChangeListener);
        }
        oplusCustomPopupMenuControllerImpl.sysUiStateController.addListener(oplusCustomPopupMenuControllerImpl.sysStateControllerListener);
        oplusCustomPopupMenuControllerImpl.currentPanelContainer = oplusCustomMenuPanelView;
        oplusCustomPopupMenuControllerImpl.getWindowManager().addView(oplusCustomMenuPanelView, oplusCustomPopupMenuControllerImpl.getLayoutParams());
        initPanelView.1 r0 = new initPanelView.1();
        r0.this$0 = oplusCustomPopupMenuControllerImpl;
        VarHandle.storeStoreFence();
        oplusCustomMenuPanelView.setOnClickListener(r0);
    }

    public static final void access$reopenWhenInsetChanged(OplusCustomPopupMenuControllerImpl oplusCustomPopupMenuControllerImpl, View view, CustomRowEntry customRowEntry, boolean z, String str) {
        oplusCustomPopupMenuControllerImpl.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusCustomPopupMenuController-->reopenWhenInsetChanged:");
        }
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        OplusCustomPopupMenuControllerImpl$.ExternalSyntheticLambda6 externalSyntheticLambda6 = new OplusCustomPopupMenuControllerImpl$.ExternalSyntheticLambda6(0);
        externalSyntheticLambda6.f$0 = booleanRef;
        VarHandle.storeStoreFence();
        oplusCustomPopupMenuControllerImpl.onConfigChanged = externalSyntheticLambda6;
        NotificationStackScrollLayoutExt notificationStackScrollLayoutExt = (NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class);
        if (notificationStackScrollLayoutExt != null) {
            OplusCustomPopupMenuControllerImpl$.ExternalSyntheticLambda7 externalSyntheticLambda7 = new OplusCustomPopupMenuControllerImpl$.ExternalSyntheticLambda7();
            externalSyntheticLambda7.f$0 = booleanRef;
            externalSyntheticLambda7.f$1 = notificationStackScrollLayoutExt;
            externalSyntheticLambda7.f$2 = oplusCustomPopupMenuControllerImpl;
            externalSyntheticLambda7.f$3 = view;
            externalSyntheticLambda7.f$4 = customRowEntry;
            externalSyntheticLambda7.f$5 = z;
            externalSyntheticLambda7.f$6 = str;
            VarHandle.storeStoreFence();
            notificationStackScrollLayoutExt.setWindowInsetChangedListener(externalSyntheticLambda7);
        }
    }

    public static void boostFrameRate(View view, boolean z) {
        if (view != null) {
            ((BoostFrameRateManager) DependencyEx.sDependency.getDependency(BoostFrameRateManager.class)).boostFrameRate(view, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BlurUtils getBlurUtils() {
        return (BlurUtils) this.blurUtils$delegate.getValue();
    }

    private final WindowManager.LayoutParams getLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2020;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 8388659;
        layoutParams.flags = 134283560;
        layoutParams.privateFlags = 16;
        layoutParams.format = -3;
        layoutParams.setTitle(WINDOW_NAME);
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getMenuPanelBackgroundColor() {
        int color = this.context.getColor(2131102915);
        BlurUtils.Companion.getClass();
        return !BlurUtils.Companion.supportsBlursOnWindows() ? this.context.getColor(2131102916) : color;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusCustomPopupMenu getOplusCustomPopupMenu() {
        return (OplusCustomPopupMenu) this.oplusCustomPopupMenu$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusCustomViewCloner getViewCloner() {
        return (OplusCustomViewCloner) this.viewCloner$delegate.getValue();
    }

    private final WindowManager getWindowManager() {
        return (WindowManager) this.windowManager$delegate.getValue();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenuController
    public void dismissPopupMenu(String str, boolean z, Function0 function0) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("dismissPopupMenu: ", str, ", isNeedAnnotation: ", z), null, 8);
        OplusCustomMenuPanelView oplusCustomMenuPanelView = this.currentPanelContainer;
        View view = this.originSeedling;
        View view2 = this.showingSeedling;
        this.isPopupMenuShowing = false;
        this.oplusCustomRow = null;
        this.originSeedling = null;
        this.showingSeedling = null;
        this.currentPanelContainer = null;
        if (view != null && view2 != null) {
            OplusCustomRowCloner oplusCustomRowCloner = (OplusCustomRowCloner) getViewCloner();
            oplusCustomRowCloner.getClass();
            oplusCustomRowCloner.animateTo(view2, view);
        }
        ((OplusCustomPopupMenuImpl) getOplusCustomPopupMenu()).dismissMenu();
        if (!z) {
            if (oplusCustomMenuPanelView != null) {
                removePanelView(oplusCustomMenuPanelView);
            }
            if (function0 != null) {
                function0.invoke();
                return;
            }
            return;
        }
        boostFrameRate(view, true);
        if (oplusCustomMenuPanelView != null) {
            BlurUtils blurUtils = getBlurUtils();
            int menuPanelBackgroundColor = getMenuPanelBackgroundColor();
            OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda5 oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda5 = new OplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda5(0);
            oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda5.f$0 = this;
            oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda5.f$1 = view;
            oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda5.f$2 = function0;
            VarHandle.storeStoreFence();
            oplusCustomMenuPanelView.dismiss(blurUtils, menuPanelBackgroundColor, oplusCustomPopupMenuControllerImpl$$ExternalSyntheticLambda5, this.isOnStatusBar);
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenuController
    public void initController(OplusCustomRow oplusCustomRow) {
        this.oplusCustomRow = oplusCustomRow;
    }

    @Override // com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenuController
    public void onConfigChanged(Configuration configuration) {
        if (this.isPopupMenuShowing) {
            OplusCustomPopupMenuController.dismissPopupMenu$default(this, "onConfigChanged", false, 4);
        }
        Function0 function0 = this.onConfigChanged;
        if (function0 != null) {
            function0.invoke();
        }
        getOplusCustomPopupMenu().onConfigChanged(configuration);
    }

    public final void removePanelView(OplusCustomMenuPanelView oplusCustomMenuPanelView) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "removePanelView:", null, 8);
        NotificationStackScrollLayoutExt notificationStackScrollLayoutExt = (NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class);
        if (notificationStackScrollLayoutExt != null) {
            notificationStackScrollLayoutExt.setWindowInsetChangedListener((Function0) null);
        }
        this.sysUiStateController.removeListener(this.sysStateControllerListener);
        if (oplusCustomMenuPanelView.getParent() != null) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "removeWindowView--Remove view failed!", null, 8);
            getWindowManager().removeViewImmediate(oplusCustomMenuPanelView);
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenuController
    public void showPopupMenu(View view, boolean z, CustomRowEntry customRowEntry, boolean z2, String str) {
        BuildersKt.launch$default(this.coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new showPopupMenu.1(this, z, view, customRowEntry, z2, str, (Continuation) null), 2);
    }
}
