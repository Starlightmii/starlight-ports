package com.oplus.systemui.statusbar.notification.row.menupanel;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationLaunchAnimationInteractor;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpLayout;
import com.oplus.systemui.notification.row.NotificationMenuRowProviderKt;
import com.oplus.systemui.notification.row.menupanel.snoozepanel.SettingsPanelHelper;
import com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenu;
import com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenuImpl;
import com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenuOperation;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.motion.BlurUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPopupMenuControllerImpl extends NotificationPopupMenuController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "RowMenuPanelController";
    private static final String WINDOW_NAME = "NotificationMenuPanelWindow";
    private final Lazy blurUtils$delegate;
    private final ConfigurationController configurationController;
    private final Context context;
    private final CoroutineScope coroutineScope;
    private NotificationMenuPanelView currentPanelContainer;
    private final PopupWindow.OnDismissListener dismissListener;
    private final HeadsUpLayout headsUpLayout;
    private final HeadsUpManager headsUpManager;
    public boolean isKeyguardShowing;
    public boolean isPopupMenuShowing;
    private final KeyguardDismissUtil keyguardDismissUtil;
    private KeyguardUpdateMonitor keyguardUpdateMonitor;
    private final KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback;
    private final KeyguardViewMediator keyguardViewMediator;
    private final NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor;
    private final Lazy notificationPopupMenu$delegate;
    private ExpandableNotificationRow originNotificationRow;
    private final Lazy parentScrollerExt$delegate;
    private final SettingsPanelHelper settingsPanelHelper;
    private RoundClipView showingNotificationRow;
    private final ViewBlurManager viewBlurManager;
    private final Lazy viewCloner$delegate;
    private final Lazy windowManager$delegate;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuControllerImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ConfigurationController.ConfigurationListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        public final void onConfigChanged(Configuration configuration) {
            switch (this.$r8$classId) {
                case 0:
                    NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl = (NotificationPopupMenuControllerImpl) this.this$0;
                    notificationPopupMenuControllerImpl.getViewCloner().onConfigChanged(configuration);
                    notificationPopupMenuControllerImpl.getNotificationPopupMenu().onConfigChanged(configuration);
                    NotificationPopupMenuController.dismissPopupMenu$default(notificationPopupMenuControllerImpl, "onConfigChanged", false, 4);
                    break;
                default:
                    ((Ref.BooleanRef) this.this$0).element = true;
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuControllerImpl$dismissPopupMenu$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02901 extends SuspendLambda implements Function2 {
        final /* synthetic */ String $cause;
        final /* synthetic */ boolean $isNeedAnimation;
        final /* synthetic */ Function0 $onDismissEnd;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02901(String str, boolean z, Function0 function0, Continuation continuation) {
            super(2, continuation);
            this.$cause = str;
            this.$isNeedAnimation = z;
            this.$onDismissEnd = function0;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationPopupMenuControllerImpl.this.new C02901(this.$cause, this.$isNeedAnimation, this.$onDismissEnd, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            NotificationBackgroundView notificationBackgroundView;
            NotificationBackgroundViewExt ext;
            ViewBlurProxy viewBlurProxy;
            Drawable blurDrawable;
            MetaBallBlurDrawable metaBallBlurDrawableTryGetMetaBallBlurDrawable;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl = NotificationPopupMenuControllerImpl.this;
            notificationPopupMenuControllerImpl.isPopupMenuShowing = false;
            NotificationMenuPanelView notificationMenuPanelView = notificationPopupMenuControllerImpl.currentPanelContainer;
            ExpandableNotificationRow expandableNotificationRow = NotificationPopupMenuControllerImpl.this.originNotificationRow;
            RoundClipView roundClipView = NotificationPopupMenuControllerImpl.this.showingNotificationRow;
            String str = this.$cause;
            boolean z = NotificationPopupMenuControllerImpl.this.isPopupMenuShowing;
            boolean z2 = this.$isNeedAnimation;
            StringBuilder sb = new StringBuilder("dismissPopupMenu:currentPanelContainer =");
            sb.append(notificationMenuPanelView);
            sb.append(", originNotificationRo");
            sb.append(expandableNotificationRow);
            sb.append(", showingNotificationRow=");
            sb.append(roundClipView);
            sb.append(", cause=");
            sb.append(str);
            sb.append(", isPopupMenuShowing=");
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, NotificationPopupMenuControllerImpl.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(sb, z, ", isNeedAnimation=", z2), null, 8);
            NotificationMenuPanelView notificationMenuPanelView2 = NotificationPopupMenuControllerImpl.this.currentPanelContainer;
            ExpandableNotificationRow expandableNotificationRow2 = NotificationPopupMenuControllerImpl.this.originNotificationRow;
            RoundClipView roundClipView2 = NotificationPopupMenuControllerImpl.this.showingNotificationRow;
            NotificationPopupMenuControllerImpl.this.currentPanelContainer = null;
            NotificationPopupMenuControllerImpl.this.originNotificationRow = null;
            NotificationPopupMenuControllerImpl.this.showingNotificationRow = null;
            if (expandableNotificationRow2 != null && roundClipView2 != null) {
                NotificationRowCloner notificationRowCloner = (NotificationRowCloner) NotificationPopupMenuControllerImpl.this.getViewCloner();
                notificationRowCloner.getClass();
                notificationRowCloner.animateTo(roundClipView2, expandableNotificationRow2);
            }
            ((NotificationPopupMenuImpl) NotificationPopupMenuControllerImpl.this.getNotificationPopupMenu()).dismissMenu(this.$isNeedAnimation);
            if (expandableNotificationRow2 != null && (notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal) != null && (ext = notificationBackgroundView.getExt()) != null && (viewBlurProxy = ext.getViewBlurProxy()) != null && (blurDrawable = viewBlurProxy.getBlurDrawable(null)) != null && (metaBallBlurDrawableTryGetMetaBallBlurDrawable = NotificationMenuRowProviderKt.tryGetMetaBallBlurDrawable(blurDrawable)) != null) {
                NotificationMenuRowProviderKt.setBlendRange(metaBallBlurDrawableTryGetMetaBallBlurDrawable, 0, "dismissPopupMenu");
            }
            if (!this.$isNeedAnimation) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "RowMenuPanelController-->isNeedAnimation:false");
                }
                if (notificationMenuPanelView2 != null) {
                    NotificationPopupMenuControllerImpl.access$removeMenuPanel(NotificationPopupMenuControllerImpl.this, notificationMenuPanelView2);
                }
                ((NotificationStackScrollLayoutExtImpl) NotificationPopupMenuControllerImpl.this.getParentScrollerExt()).resetScrollingEnabled();
                Function0 function0 = this.$onDismissEnd;
                if (function0 != null) {
                    function0.invoke();
                }
                return Unit.INSTANCE;
            }
            if (notificationMenuPanelView2 != null) {
                BlurUtils blurUtils = NotificationPopupMenuControllerImpl.this.getBlurUtils();
                int iAccess$getMenuPanelBackgroundColor = NotificationPopupMenuControllerImpl.access$getMenuPanelBackgroundColor(NotificationPopupMenuControllerImpl.this, expandableNotificationRow2);
                NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl2 = NotificationPopupMenuControllerImpl.this;
                Function0 function1 = this.$onDismissEnd;
                NotificationPopupMenuControllerImpl$dismissPopupMenu$1$$ExternalSyntheticLambda0 notificationPopupMenuControllerImpl$dismissPopupMenu$1$$ExternalSyntheticLambda0 = new NotificationPopupMenuControllerImpl$dismissPopupMenu$1$$ExternalSyntheticLambda0();
                notificationPopupMenuControllerImpl$dismissPopupMenu$1$$ExternalSyntheticLambda0.f$0 = notificationPopupMenuControllerImpl2;
                notificationPopupMenuControllerImpl$dismissPopupMenu$1$$ExternalSyntheticLambda0.f$1 = function1;
                VarHandle.storeStoreFence();
                notificationMenuPanelView2.dismiss(blurUtils, iAccess$getMenuPanelBackgroundColor, notificationPopupMenuControllerImpl$dismissPopupMenu$1$$ExternalSyntheticLambda0);
            }
            ((NotificationStackScrollLayoutExtImpl) NotificationPopupMenuControllerImpl.this.getParentScrollerExt()).resetScrollingEnabled();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$09O0zpA1EuROP9-mFPBzgTEpx50, reason: not valid java name */
    public static Unit m453$r8$lambda$09O0zpA1EuROP9mFPBzgTEpx50(Ref.BooleanRef booleanRef, NotificationStackScrollLayoutExt notificationStackScrollLayoutExt, NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl, AnonymousClass1 anonymousClass1, ExpandableNotificationRow expandableNotificationRow) {
        if (booleanRef.element) {
            notificationStackScrollLayoutExt.setWindowInsetChangedListener((Function0) null);
            notificationPopupMenuControllerImpl.configurationController.removeCallback(anonymousClass1);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "RowMenuPanelController-->reopenSettingPanel");
            }
            notificationPopupMenuControllerImpl.showPopupMenu(notificationPopupMenuControllerImpl.isKeyguardShowing, false, expandableNotificationRow, false, new NotificationPopupMenuController$.ExternalSyntheticLambda0());
        }
        return Unit.INSTANCE;
    }

    public static WindowManager $r8$lambda$OEMqLOqEQXwOThYI9yfgdLlj19Y(NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl) {
        return (WindowManager) notificationPopupMenuControllerImpl.context.getSystemService("window");
    }

    public NotificationPopupMenuControllerImpl(Context context, ConfigurationController configurationController, NotificationPopupMenuOperation notificationPopupMenuOperation, ViewBlurManager viewBlurManager, KeyguardDismissUtil keyguardDismissUtil, KeyguardUpdateMonitor keyguardUpdateMonitor, HeadsUpLayout headsUpLayout, HeadsUpManager headsUpManager, KeyguardViewMediator keyguardViewMediator, SettingsPanelHelper settingsPanelHelper, NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor) {
        this.context = context;
        this.configurationController = configurationController;
        this.viewBlurManager = viewBlurManager;
        this.keyguardDismissUtil = keyguardDismissUtil;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.headsUpLayout = headsUpLayout;
        this.headsUpManager = headsUpManager;
        this.keyguardViewMediator = keyguardViewMediator;
        this.settingsPanelHelper = settingsPanelHelper;
        this.notificationLaunchAnimationInteractor = notificationLaunchAnimationInteractor;
        final int i = 0;
        NotificationPopupMenuControllerImpl$$ExternalSyntheticLambda0 notificationPopupMenuControllerImpl$$ExternalSyntheticLambda0 = new NotificationPopupMenuControllerImpl$$ExternalSyntheticLambda0(i);
        notificationPopupMenuControllerImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.windowManager$delegate = LazyKt__LazyJVMKt.lazy(notificationPopupMenuControllerImpl$$ExternalSyntheticLambda0);
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob((Job) null).plus(Dispatchers.getMain()));
        final int i2 = 1;
        NotificationPopupMenuControllerImpl$$ExternalSyntheticLambda0 notificationPopupMenuControllerImpl$$ExternalSyntheticLambda1 = new NotificationPopupMenuControllerImpl$$ExternalSyntheticLambda0(i2);
        notificationPopupMenuControllerImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.viewCloner$delegate = LazyKt__LazyJVMKt.lazy(notificationPopupMenuControllerImpl$$ExternalSyntheticLambda1);
        NotificationPopupMenuControllerImpl$$ExternalSyntheticLambda2 notificationPopupMenuControllerImpl$$ExternalSyntheticLambda2 = new NotificationPopupMenuControllerImpl$$ExternalSyntheticLambda2();
        notificationPopupMenuControllerImpl$$ExternalSyntheticLambda2.f$0 = this;
        notificationPopupMenuControllerImpl$$ExternalSyntheticLambda2.f$1 = notificationPopupMenuOperation;
        VarHandle.storeStoreFence();
        this.notificationPopupMenu$delegate = LazyKt__LazyJVMKt.lazy(notificationPopupMenuControllerImpl$$ExternalSyntheticLambda2);
        this.blurUtils$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuControllerImpl$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return new BlurUtils(0, 0);
                    default:
                        return (NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class);
                }
            }
        });
        this.parentScrollerExt$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuControllerImpl$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return new BlurUtils(0, 0);
                    default:
                        return (NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class);
                }
            }
        });
        NotificationPopupMenuControllerImpl$dismissListener$1 notificationPopupMenuControllerImpl$dismissListener$1 = new NotificationPopupMenuControllerImpl$dismissListener$1();
        notificationPopupMenuControllerImpl$dismissListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.dismissListener = notificationPopupMenuControllerImpl$dismissListener$1;
        NotificationPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1 notificationPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1 = new NotificationPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1();
        notificationPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardUpdateMonitorCallback = notificationPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        configurationController.addCallback(anonymousClass1);
        this.keyguardUpdateMonitor.registerCallback(notificationPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1);
    }

    public static final NotificationMenuPanelView access$createMenuPanel(NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl) {
        notificationPopupMenuControllerImpl.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "RowMenuPanelController-->createMenuPanel:");
        }
        notificationPopupMenuControllerImpl.isPopupMenuShowing = true;
        NotificationMenuPanelView notificationMenuPanelView = new NotificationMenuPanelView(notificationPopupMenuControllerImpl.context, null, 0, 14, 0);
        notificationPopupMenuControllerImpl.getWindowManager().addView(notificationMenuPanelView, notificationPopupMenuControllerImpl.getLayoutParams());
        if (!notificationMenuPanelView.isAttachedToWindow()) {
            NotificationPopupMenuControllerImpl$createMenuPanel$.inlined.doOnAttach.1 r0 = new NotificationPopupMenuControllerImpl$createMenuPanel$.inlined.doOnAttach.1();
            r0.$this_doOnAttach = notificationMenuPanelView;
            r0.$menuPanelContainer$inlined = notificationMenuPanelView;
            r0.this$0 = notificationPopupMenuControllerImpl;
            VarHandle.storeStoreFence();
            notificationMenuPanelView.addOnAttachStateChangeListener(r0);
        } else if (notificationMenuPanelView.isAttachedToWindow()) {
            notificationMenuPanelView.addOnAttachStateChangeListener(new NotificationPopupMenuControllerImpl$createMenuPanel$lambda$9$.inlined.doOnDetach.1(notificationMenuPanelView, notificationPopupMenuControllerImpl));
        } else if (notificationPopupMenuControllerImpl.isPopupMenuShowing) {
            NotificationPopupMenuController.dismissPopupMenu$default(notificationPopupMenuControllerImpl, "doOnDetach", false, 6);
        }
        createMenuPanel.3 r1 = new createMenuPanel.3();
        r1.this$0 = notificationPopupMenuControllerImpl;
        VarHandle.storeStoreFence();
        notificationMenuPanelView.setOnClickListener(r1);
        return notificationMenuPanelView;
    }

    public static final int access$getMenuPanelBackgroundColor(NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl, ExpandableNotificationRow expandableNotificationRow) {
        int color = notificationPopupMenuControllerImpl.context.getColor(2131102915);
        BlurUtils.Companion.getClass();
        if (BlurUtils.Companion.supportsBlursOnWindows()) {
            return color;
        }
        return (expandableNotificationRow == null || !expandableNotificationRow.mPinnedStatus.isPinned()) ? notificationPopupMenuControllerImpl.context.getColor(2131102916) : notificationPopupMenuControllerImpl.context.getColor(2131102917);
    }

    public static final void access$removeMenuPanel(NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl, NotificationMenuPanelView notificationMenuPanelView) {
        notificationPopupMenuControllerImpl.getClass();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "removeMenuPanel:", null, 8);
        NotificationStackScrollLayoutExt parentScrollerExt = notificationPopupMenuControllerImpl.getParentScrollerExt();
        if (parentScrollerExt != null) {
            parentScrollerExt.setWindowInsetChangedListener((Function0) null);
        }
        notificationPopupMenuControllerImpl.isPopupMenuShowing = false;
        notificationPopupMenuControllerImpl.getWindowManager().removeViewImmediate(notificationMenuPanelView);
        if (notificationMenuPanelView.getParent() != null) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "removeWindowView--Remove view failed!");
            notificationPopupMenuControllerImpl.getWindowManager().removeView(notificationMenuPanelView);
        }
    }

    public static final void access$reopenWhenInsetChanged(NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl, ExpandableNotificationRow expandableNotificationRow) {
        notificationPopupMenuControllerImpl.getClass();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "reopenWhenInsetChanged:", null, 8);
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        NotificationStackScrollLayoutExt notificationStackScrollLayoutExt = (NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.this$0 = booleanRef;
        VarHandle.storeStoreFence();
        notificationPopupMenuControllerImpl.configurationController.addCallback(anonymousClass1);
        if (notificationStackScrollLayoutExt != null) {
            NotificationPopupMenuControllerImpl$.ExternalSyntheticLambda5 externalSyntheticLambda5 = new NotificationPopupMenuControllerImpl$.ExternalSyntheticLambda5();
            externalSyntheticLambda5.f$0 = booleanRef;
            externalSyntheticLambda5.f$1 = notificationStackScrollLayoutExt;
            externalSyntheticLambda5.f$2 = notificationPopupMenuControllerImpl;
            externalSyntheticLambda5.f$3 = anonymousClass1;
            externalSyntheticLambda5.f$4 = expandableNotificationRow;
            VarHandle.storeStoreFence();
            notificationStackScrollLayoutExt.setWindowInsetChangedListener(externalSyntheticLambda5);
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
    public final NotificationPopupMenu getNotificationPopupMenu() {
        return (NotificationPopupMenu) this.notificationPopupMenu$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NotificationStackScrollLayoutExt getParentScrollerExt() {
        return (NotificationStackScrollLayoutExt) this.parentScrollerExt$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NotificationViewCloner getViewCloner() {
        return (NotificationViewCloner) this.viewCloner$delegate.getValue();
    }

    private final WindowManager getWindowManager() {
        return (WindowManager) this.windowManager$delegate.getValue();
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuController
    public void dismissPopupMenu(String str, boolean z, Function0 function0) {
        BuildersKt.launch$default(this.coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new C02901(str, z, function0, null), 2);
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuController
    public final boolean isPopupMenuShowing() {
        return this.isPopupMenuShowing;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuController
    public void showPopupMenu(boolean z, boolean z2, ExpandableNotificationRow expandableNotificationRow, boolean z3, Function0 function0) {
        OplusNotificationEntryEx ex = expandableNotificationRow.getEntry().getEx();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, FileProvider$.ExternalSyntheticOutline0.m("showPopupMenu: ", ex != null ? ex.getEncryptionKey() : null), null, 8);
        BuildersKt.launch$default(this.coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new showPopupMenu.1(this, expandableNotificationRow, z3, z, z2, function0, (Continuation) null), 2);
    }
}
