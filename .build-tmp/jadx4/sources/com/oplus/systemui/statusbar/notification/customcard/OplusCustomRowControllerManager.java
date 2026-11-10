package com.oplus.systemui.statusbar.notification.customcard;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import androidx.room.util.CursorUtil$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.plugins.custom.CustomRowMenuData;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.BlurUtils;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.RoundableState;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.helper.ReplacedNotificationRemoveHelper;
import com.android.systemui.statusbar.notification.row.ExpandableOutlineView;
import com.android.systemui.statusbar.notification.row.OnUserInteractionCallback;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExt;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingViewModel;
import com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenuController;
import com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenuControllerImpl;
import com.oplus.systemui.statusbar.seeding.FluidReplaceNotificationManager;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.CommonUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRowControllerManager {
    public static final String ACTION_TYPE = "actionType";
    public static final String ACTION_TYPE_LEFT_SWIPE = "2002";
    public static final String ACTION_TYPE_LEFT_SWIPE_CLICK_DELETE = "2004";
    public static final String ACTION_TYPE_NOTIFICATION_CLICK = "2001";
    public static final String ACTION_TYPE_RIGHT_DELETE = "2003";
    public static final String ACTION_TYPE_STATUSBAR_LONG_CLICK = "1001";
    public static final Companion Companion = new Companion();
    public static final String EXTRA_KEY_CONTENT_BG_COLOR = "contentBgColor";
    private static final String IS_FROM = "isFrom";
    public static final String KEY_IMMERSIVE_CARD_TYPE = "immersiveCardType";
    public static final String KEY_LOCK_IMMERSIVE_ENABLE = "lockImmersiveEnable";
    public static final String MEDIA_CONTROLLER_SERVICE_ID = "268452006";
    public static final String MEDIA_CONTROLLER_SERVICE_ID_NEW = "default_com.android.systemui_268452006";
    private static final String METHOD_PLUGIN_REPORT_STATISTICS = "reportStatistics";
    private static final String METHOD_PLUGIN_START_ACTIVITY = "pluginStartActivity";
    private static final String SERVICE_KRY = "serviceKey";
    private static final String TAG = "OplusCustomRowControllerManager";
    private final ActivityStarter activityStarter;
    private final BlurUtils blurUtils;
    private final ConfigurationController configurationController;
    private Context context;
    private final ArrayList<OplusCustomRowController> customList = new ArrayList<>();
    private ArrayList<CustomRowEntry> customRowEntryList = new ArrayList<>();
    private final Lazy fluidReplaceNotificationManager;
    private final Lazy keyguardUpdateMonitor;
    private final Lazy lockScreenExtStateController;
    private final NotifFilter mNotifFilter;
    private NotificationStackScrollLayoutController mStackScrollerController;
    private final NotifCollection notifCollection;
    private final NotifPipeline notificationPipeline;
    private final OnUserInteractionCallback onUserInteractionCallback;
    private final OplusCustomPopupMenuController oplusCustomPopupMenuController;
    private ViewGroup parentView;
    private SeedlingPlugin plugin;
    private final Lazy replacedNotificationRemoveHelper;
    private RequestImmersiveStateListener requestImmersiveStateListener;
    private final Lazy shadeController;
    private final SysuiStatusBarStateController statusBarStateController;
    private final ViewBlurManager viewBlurManager;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ OplusCustomRowControllerManager this$0;

        public final void onConfigChanged(Configuration configuration) {
            this.this$0.oplusCustomPopupMenuController.onConfigChanged(configuration);
        }

        public final void onDensityOrFontScaleChanged() {
            ArrayList arrayList = this.this$0.customList;
            if (arrayList != null) {
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    LaunchableView oplusCustomRow = ((OplusCustomRowController) obj).getOplusCustomRow();
                    if (oplusCustomRow != null) {
                        ((ExpandableOutlineView) oplusCustomRow).mAlwaysRoundBothCorners = oplusCustomRow.getResources().getBoolean(2131034133);
                        float fCalculateMaxRadius = oplusCustomRow.calculateMaxRadius();
                        RoundableState roundableState = ((ExpandableOutlineView) oplusCustomRow).mRoundableState;
                        if (roundableState == null) {
                            ((ExpandableOutlineView) oplusCustomRow).mRoundableState = new RoundableState(oplusCustomRow, oplusCustomRow, fCalculateMaxRadius);
                        } else {
                            roundableState.setMaxRadius(fCalculateMaxRadius);
                        }
                        oplusCustomRow.setClipToOutline(((ExpandableOutlineView) oplusCustomRow).mAlwaysRoundBothCorners);
                        oplusCustomRow.applyRoundnessAndInvalidate();
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface RequestImmersiveStateListener {
        void onRequestImmersiveState(int i, int i2, String str);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager$setCustomContainerControllerList$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements Predicate {
        public /* synthetic */ List $customRowEntryListInner;
        public /* synthetic */ boolean $isOnKeyguard;
        public /* synthetic */ NotificationSwipeHelper $swipeHelper;
        public /* synthetic */ OplusCustomRowControllerManager this$0;

        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            boolean z;
            OplusCustomRowController oplusCustomRowController = (OplusCustomRowController) obj;
            List list = this.$customRowEntryListInner;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    String key = ((CustomRowEntry) it.next()).getKey();
                    CustomRowEntry entry = oplusCustomRowController.getEntry();
                    if (Intrinsics.areEqual(key, entry != null ? entry.getKey() : null)) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
                break;
            }
            boolean z2 = this.$isOnKeyguard;
            OplusCustomRowControllerManager oplusCustomRowControllerManager = this.this$0;
            NotificationSwipeHelper notificationSwipeHelper = this.$swipeHelper;
            if (z) {
                View mBackgroundNormal = oplusCustomRowController.getView().getMBackgroundNormal();
                if (mBackgroundNormal != null) {
                    if (mBackgroundNormal.isAttachedToWindow()) {
                        OplusCustomRowControllerManager$setCustomContainerControllerList$3$test$lambda$5$$inlined$doOnDetach$1 oplusCustomRowControllerManager$setCustomContainerControllerList$3$test$lambda$5$$inlined$doOnDetach$1 = new OplusCustomRowControllerManager$setCustomContainerControllerList$3$test$lambda$5$$inlined$doOnDetach$1();
                        oplusCustomRowControllerManager$setCustomContainerControllerList$3$test$lambda$5$$inlined$doOnDetach$1.$this_doOnDetach = mBackgroundNormal;
                        oplusCustomRowControllerManager$setCustomContainerControllerList$3$test$lambda$5$$inlined$doOnDetach$1.this$0 = oplusCustomRowControllerManager;
                        VarHandle.storeStoreFence();
                        mBackgroundNormal.addOnAttachStateChangeListener(oplusCustomRowControllerManager$setCustomContainerControllerList$3$test$lambda$5$$inlined$doOnDetach$1);
                    } else {
                        oplusCustomRowControllerManager.viewBlurManager.releaseBlurProxyForView(mBackgroundNormal, true, "customBackgroundNormal remove");
                    }
                }
                List<NotificationMenuRowPlugin.MenuItem> menuItems = oplusCustomRowController.getView().getMenuItems();
                if (menuItems != null) {
                    Iterator<T> it2 = menuItems.iterator();
                    while (it2.hasNext()) {
                        View menuView = ((NotificationMenuRowPlugin.MenuItem) it2.next()).getMenuView();
                        if (menuView != null) {
                            oplusCustomRowControllerManager.viewBlurManager.releaseBlurProxyForView(menuView, true, "customMenu remove");
                        }
                    }
                }
                if (!z2) {
                    OplusCustomRowControllerManager.access$onShowNotificationCard(oplusCustomRowControllerManager, oplusCustomRowController);
                }
                if (oplusCustomRowController.getView() == (notificationSwipeHelper != null ? notificationSwipeHelper.getTranslatingParentView() : null)) {
                    if (notificationSwipeHelper != null) {
                        notificationSwipeHelper.setTranslatingParentView((View) null);
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "OplusCustomRowControllerManager-->clearTranslatingParentView");
                    }
                }
            }
            return z;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager$setupDebounceClickListener$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ViewOnClickListenerC02851 implements View.OnClickListener {
        public /* synthetic */ OplusCustomRowControllerManager this$0;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusCustomRowControllerManager-->OplusCustomRow OnClick");
            }
            CustomRowEntry entry = ((OplusCustomRow) view).getEntry();
            this.this$0.callPluginStartActivity(entry != null ? entry.getKey() : null, 0);
        }
    }

    public OplusCustomRowControllerManager(Context context, BlurUtils blurUtils, Lazy lazy, Lazy lazy2, Lazy lazy3, SysuiStatusBarStateController sysuiStatusBarStateController, ActivityStarter activityStarter, ViewBlurManager viewBlurManager, NotifPipeline notifPipeline, OplusCustomPopupMenuController oplusCustomPopupMenuController, OnUserInteractionCallback onUserInteractionCallback, NotifCollection notifCollection, ConfigurationController configurationController, Lazy lazy4, Lazy lazy5) {
        this.context = context;
        this.blurUtils = blurUtils;
        this.replacedNotificationRemoveHelper = lazy;
        this.fluidReplaceNotificationManager = lazy2;
        this.shadeController = lazy3;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.activityStarter = activityStarter;
        this.viewBlurManager = viewBlurManager;
        this.notificationPipeline = notifPipeline;
        this.oplusCustomPopupMenuController = oplusCustomPopupMenuController;
        this.onUserInteractionCallback = onUserInteractionCallback;
        this.notifCollection = notifCollection;
        this.configurationController = configurationController;
        this.lockScreenExtStateController = lazy4;
        this.keyguardUpdateMonitor = lazy5;
        OplusCustomRowControllerManager$mNotifFilter$1 oplusCustomRowControllerManager$mNotifFilter$1 = new OplusCustomRowControllerManager$mNotifFilter$1("OplusCustomRowControllerManagerupdateOplusCustomRow");
        this.mNotifFilter = oplusCustomRowControllerManager$mNotifFilter$1;
        notifPipeline.addFinalizeFilter(oplusCustomRowControllerManager$mNotifFilter$1);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        configurationController.addCallback(anonymousClass1);
    }

    public static final void access$onShowNotificationCard(OplusCustomRowControllerManager oplusCustomRowControllerManager, OplusCustomRowController oplusCustomRowController) {
        CustomRowEntry entry = oplusCustomRowController.getEntry();
        String serviceId = entry != null ? entry.getServiceId() : null;
        CustomRowEntry entry2 = oplusCustomRowController.getEntry();
        String packageName = entry2 != null ? entry2.getPackageName() : null;
        CustomRowEntry entry3 = oplusCustomRowController.getEntry();
        Integer numValueOf = entry3 != null ? Integer.valueOf(entry3.getUserId()) : null;
        if (serviceId == null || packageName == null || numValueOf == null) {
            return;
        }
        ((FluidReplaceNotificationManager) oplusCustomRowControllerManager.fluidReplaceNotificationManager.get()).updateFluidShowMap(serviceId, packageName, false, numValueOf.intValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setupDebounceClickListener(OplusCustomRow oplusCustomRow) {
        ViewOnClickListenerC02851 viewOnClickListenerC02851 = new ViewOnClickListenerC02851();
        viewOnClickListenerC02851.this$0 = this;
        VarHandle.storeStoreFence();
        CommonUtils.setDebounceClickListener(oplusCustomRow, viewOnClickListenerC02851);
    }

    public final Bundle callPluginStartActivity(String str, int i) {
        if (str == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusCustomRowControllerManager-->serviceKey is null. startActivity error");
            }
            return null;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            KeyguardSettingProvider$.ExternalSyntheticOutline0.m("callPluginStartActivity, serviceKey=", str, "OplusCustomRowControllerManager-->", "SystemUi--Notification");
        }
        Bundle bundle = new Bundle();
        bundle.putString("method", METHOD_PLUGIN_START_ACTIVITY);
        bundle.putString(SERVICE_KRY, str);
        bundle.putInt(IS_FROM, i);
        SeedlingPlugin seedlingPlugin = this.plugin;
        if (seedlingPlugin != null) {
            return seedlingPlugin.call(bundle);
        }
        return null;
    }

    public final void callReportStatistics(String str, String str2, String str3) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("callReportStatistics, event=", str, "OplusCustomRowControllerManager-->", "SystemUi--Notification");
        }
        Handler workHandler = SystemUIThreadUtil.getWorkHandler();
        callReportStatistics.2 r1 = new callReportStatistics.2();
        r1.$serviceKey = str2;
        r1.$serviceId = str3;
        r1.$event = str;
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        workHandler.post(r1);
    }

    public final boolean canShowHeadsUpNotification() {
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("method", "canShowHeadsUpNotification");
        SeedlingPlugin seedlingPlugin = this.plugin;
        Bundle bundleCall = seedlingPlugin != null ? seedlingPlugin.call(bundleM) : null;
        if (bundleCall != null) {
            return bundleCall.getBoolean("canShow", true);
        }
        return true;
    }

    public final void dismissPopupMenu(String str) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, FileProvider$.ExternalSyntheticOutline0.m("dismissPopupMenu reason:", str), null, 8);
        if (str != null) {
            OplusCustomPopupMenuController.dismissPopupMenu$default(this.oplusCustomPopupMenuController, str, false, 4);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void dissmissCustomRow(OplusCustomRow oplusCustomRow, String str) {
        List popupMenuList;
        CustomRowMenuData customRowMenuData;
        PendingIntent itemClickIntent;
        NotificationEntry entry;
        CustomRowEntry entry2 = oplusCustomRow.getEntry();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, FileProvider$.ExternalSyntheticOutline0.m("dissmisscustomRow serviceid = ", entry2 != null ? entry2.getKey() : null), null, 8);
        CustomRowEntry entry3 = oplusCustomRow.getEntry();
        String serviceId = entry3 != null ? entry3.getServiceId() : null;
        String packageName = entry3 != null ? entry3.getPackageName() : null;
        Integer numValueOf = entry3 != null ? Integer.valueOf(entry3.getUserId()) : null;
        ArrayList<OplusCustomRowController> arrayList = this.customList;
        dissmissCustomRow.1 r9 = new dissmissCustomRow.1();
        r9.$viewEntry = entry3;
        VarHandle.storeStoreFence();
        arrayList.removeIf(r9);
        CustomRowEntry entry4 = oplusCustomRow.getEntry();
        if (entry4 != null && (entry = this.notifCollection.getEntry(entry4.getKey())) != null) {
            oplusCustomRow.post(this.onUserInteractionCallback.registerFutureDismissal(entry, 2));
        }
        Intent intent = (entry4 == null || (popupMenuList = entry4.getPopupMenuList()) == null || (customRowMenuData = (CustomRowMenuData) popupMenuList.get(0)) == null || (itemClickIntent = customRowMenuData.getItemClickIntent()) == null) ? null : itemClickIntent.getIntent();
        if (intent != null) {
            intent.putExtra("actionType", str);
            PendingIntent.getBroadcast(this.context, (int) System.currentTimeMillis(), intent, 67108864).send();
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "dissmissOplusCustomRow, send pendingintent", null, 8);
        }
        if (serviceId != null && packageName != null && numValueOf != null) {
            ((FluidReplaceNotificationManager) this.fluidReplaceNotificationManager.get()).updateFluidShowMap(serviceId, packageName, false, numValueOf.intValue());
        }
        this.mNotifFilter.invalidateList("dissmissOplusCustomRow");
    }

    public final void executeRunnableDismissingKeyguard(Runnable runnable) {
        StatusBarStateControllerImpl statusBarStateControllerImpl = this.statusBarStateController;
        if (statusBarStateControllerImpl instanceof StatusBarStateControllerImpl) {
            statusBarStateControllerImpl.setLeaveOpenOnKeyguardHide(true);
        }
        this.activityStarter.executeRunnableDismissingKeyguard(runnable, (Runnable) null, false, true, true);
    }

    public final void executeRunnableNsslAnimationFinish(Runnable runnable) {
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mStackScrollerController;
        NotificationStackScrollLayout view = notificationStackScrollLayoutController != null ? notificationStackScrollLayoutController.getView() : null;
        if (view == null || !view.mStateAnimator.isRunning()) {
            runnable.run();
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusCustomRowControllerManager-->executeRunnableNsslAnimationFinish");
        }
        executeRunnableNsslAnimationFinish.2 r0 = new executeRunnableNsslAnimationFinish.2();
        r0.$nssl = view;
        r0.$runnable = runnable;
        VarHandle.storeStoreFence();
        view.runAfterAnimationFinished$1(r0);
    }

    public final BlurUtils getBlursUtils() {
        return this.blurUtils;
    }

    public final List<CustomRowEntry> getFullyVisibleCustomRowKeys() {
        ExpandableViewState viewState;
        boolean zAreEqual;
        OplusExpandableViewStateEx oplusExpandableViewStateEx;
        ArrayList<OplusCustomRowController> arrayList = this.customList;
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            OplusCustomRowController oplusCustomRowController = arrayList.get(i2);
            i2++;
            LaunchableView oplusCustomRow = oplusCustomRowController.getOplusCustomRow();
            if (oplusCustomRow != null && oplusCustomRow.getVisibility() == 0 && ((viewState = oplusCustomRow.getViewState()) == null || (viewState.location & 5) != 0)) {
                ExpandableViewState viewState2 = oplusCustomRow.getViewState();
                zAreEqual = Intrinsics.areEqual((viewState2 == null || (oplusExpandableViewStateEx = viewState2.mExpandableViewStateEx) == null) ? null : Float.valueOf(oplusExpandableViewStateEx.getNotificationStackedProgress()), 0.0f);
            } else {
                zAreEqual = false;
            }
            if (zAreEqual) {
                arrayList2.add(oplusCustomRowController);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        int size2 = arrayList2.size();
        while (i < size2) {
            Object obj = arrayList2.get(i);
            i++;
            CustomRowEntry entry = ((OplusCustomRowController) obj).getEntry();
            if (entry != null) {
                arrayList3.add(entry);
            }
        }
        return arrayList3;
    }

    public final List<OplusCustomRowController> getOplusCustomRowControllerList() {
        return ((KeyguardUpdateMonitor) this.keyguardUpdateMonitor.get()).getIsSecureLockDeviceEnabled() ? CollectionsKt__CollectionsKt.emptyList() : this.customList;
    }

    public final OplusCustomPopupMenuController getPopopMenuController() {
        return this.oplusCustomPopupMenuController;
    }

    public final RequestImmersiveStateListener getRequestImmersiveStateListener() {
        return this.requestImmersiveStateListener;
    }

    public final NotificationSwipeHelper getSwipeHepler() {
        NotificationListContainer notificationListContainer;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mStackScrollerController;
        NotificationSwipeActionHelper swipeActionHelper = (notificationStackScrollLayoutController == null || (notificationListContainer = notificationStackScrollLayoutController.getNotificationListContainer()) == null) ? null : notificationListContainer.getSwipeActionHelper();
        if (swipeActionHelper instanceof NotificationSwipeHelper) {
            return (NotificationSwipeHelper) swipeActionHelper;
        }
        return null;
    }

    public final void initPlugin(SeedlingPlugin seedlingPlugin) {
        this.plugin = seedlingPlugin;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final boolean isShowMediaController() {
        OplusCustomRowController oplusCustomRowController;
        LaunchableView oplusCustomRow;
        ArrayList<OplusCustomRowController> arrayList = this.customList;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            oplusCustomRowController = null;
            if (i >= size) {
                break;
            }
            OplusCustomRowController oplusCustomRowController2 = arrayList.get(i);
            i++;
            OplusCustomRow.Companion companion = OplusCustomRow.Companion;
            CustomRowEntry entry = oplusCustomRowController2.getEntry();
            if (companion.isOplusMediaCustomRow(entry != null ? entry.getServiceId() : null)) {
                oplusCustomRowController = oplusCustomRowController2;
                break;
            }
        }
        OplusCustomRowController oplusCustomRowController3 = oplusCustomRowController;
        return (oplusCustomRowController3 == null || (oplusCustomRow = oplusCustomRowController3.getOplusCustomRow()) == null || oplusCustomRow.getVisibility() != 0) ? false : true;
    }

    public final void removePlugin() {
        this.plugin = null;
        this.customList.clear();
        this.customRowEntryList.clear();
    }

    public final void requestAnimationOnViewResizeCustom() {
        NotificationStackScrollLayout view;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mStackScrollerController;
        if (notificationStackScrollLayoutController == null || (view = notificationStackScrollLayoutController.getView()) == null || !view.mAnimationsEnabled) {
            return;
        }
        view.mNeedViewResizeAnimation = true;
        view.mNeedsAnimation = true;
    }

    public final void requestImmersiveState(int i, int i2, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(str, "OplusCustomRowControllerManager-->", "SystemUi--Notification", FloatList$.ExternalSyntheticOutline0.m("requestImmersiveState:: mode = ", i, i2, ", reason = ", ", item = "));
        }
        RequestImmersiveStateListener requestImmersiveStateListener = this.requestImmersiveStateListener;
        if (requestImmersiveStateListener != null) {
            ((SeedlingViewModel.AnonymousClass1) requestImmersiveStateListener).onRequestImmersiveState(i, i2, str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v9, types: [android.widget.FrameLayout, com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow] */
    public final void setCustomContainerControllerList(List<? extends CustomRowEntry> list, boolean z) {
        int i;
        CustomRowEntry customRowEntry;
        Iterator it;
        Object obj;
        char c;
        OplusCustomRow oplusCustomRow;
        Object next;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", MobileInputLogger$.ExternalSyntheticOutline0.m("setCustomContainerControllerList, size = ", list.size(), ", updateResources = ", "OplusCustomRowControllerManager-->", z));
        }
        if (z) {
            ArrayList<CustomRowEntry> arrayList = this.customRowEntryList;
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                CustomRowEntry customRowEntry2 = arrayList.get(i2);
                i2++;
                CustomRowEntry customRowEntry3 = customRowEntry2;
                Iterator<T> it2 = list.iterator();
                do {
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                } while (!Intrinsics.areEqual(customRowEntry3.getKey(), ((CustomRowEntry) next).getKey()));
                CustomRowEntry customRowEntry4 = (CustomRowEntry) next;
                if (customRowEntry4 != null) {
                    customRowEntry3.setPopupMenuList(customRowEntry4.getPopupMenuList());
                    customRowEntry3.setPopupMenuTitle(customRowEntry4.getPopupMenuTitle());
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "updateEntryPopupMenu, popupMenuList = " + customRowEntry3.getPopupMenuList(), null, 8);
                }
            }
            return;
        }
        boolean z2 = ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).getState() == 1;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("isOnKeyguard = ", "OplusCustomRowControllerManager-->", "SystemUi--Notification", z2);
        }
        NotificationSwipeHelper swipeHepler = getSwipeHepler();
        ArrayList<OplusCustomRowController> arrayList2 = this.customList;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.$customRowEntryListInner = list;
        anonymousClass3.$isOnKeyguard = z2;
        anonymousClass3.this$0 = this;
        anonymousClass3.$swipeHelper = swipeHepler;
        VarHandle.storeStoreFence();
        arrayList2.removeIf(anonymousClass3);
        List<? extends CustomRowEntry> list2 = list;
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            CustomRowEntry customRowEntry5 = (CustomRowEntry) it3.next();
            ArrayList<CustomRowEntry> arrayList4 = this.customRowEntryList;
            int size2 = arrayList4.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size2) {
                    customRowEntry = null;
                    break;
                }
                customRowEntry = arrayList4.get(i3);
                i3++;
                CustomRowEntry customRowEntry6 = customRowEntry;
                if (Intrinsics.areEqual(customRowEntry6.getKey(), customRowEntry5.getKey()) && Intrinsics.areEqual(customRowEntry6.getCustomView(), customRowEntry5.getCustomView()) && Intrinsics.areEqual(customRowEntry6.getMiniCardView(), customRowEntry5.getMiniCardView()) && customRowEntry6.getMiniCardViewIndex() == customRowEntry5.getMiniCardViewIndex() && Intrinsics.areEqual(customRowEntry6.getImmersiveCardView(), customRowEntry5.getImmersiveCardView()) && customRowEntry6.getMiniCardRemind() == customRowEntry5.getMiniCardRemind() && Intrinsics.areEqual(customRowEntry6.getExtras().getString(EXTRA_KEY_CONTENT_BG_COLOR), customRowEntry5.getExtras().getString(EXTRA_KEY_CONTENT_BG_COLOR)) && customRowEntry6.getExtras().getInt("immersiveCardType") == customRowEntry5.getExtras().getInt("immersiveCardType") && customRowEntry6.getExtras().getBoolean(KEY_LOCK_IMMERSIVE_ENABLE) == customRowEntry5.getExtras().getBoolean(KEY_LOCK_IMMERSIVE_ENABLE)) {
                    break;
                }
            }
            CustomRowEntry customRowEntry7 = customRowEntry;
            if (customRowEntry7 == null) {
                ArrayList<OplusCustomRowController> arrayList5 = this.customList;
                int size3 = arrayList5.size();
                boolean z3 = false;
                int i4 = 0;
                while (i4 < size3) {
                    OplusCustomRowController oplusCustomRowController = arrayList5.get(i4);
                    i4++;
                    OplusCustomRowController oplusCustomRowController2 = oplusCustomRowController;
                    CustomRowEntry entry = oplusCustomRowController2.getEntry();
                    Iterator it4 = it3;
                    if (Intrinsics.areEqual(entry != null ? entry.getKey() : null, customRowEntry5.getKey())) {
                        OplusCustomRow oplusCustomRow2 = oplusCustomRowController2.getOplusCustomRow();
                        if (oplusCustomRow2 != null) {
                            oplusCustomRow2.setNewSeedlingView(customRowEntry5.getCustomView());
                        }
                        OplusCustomRow oplusCustomRow3 = oplusCustomRowController2.getOplusCustomRow();
                        if (oplusCustomRow3 != null) {
                            oplusCustomRow3.setEntry(customRowEntry5);
                        }
                        CustomRowEntry entry2 = oplusCustomRowController2.getEntry();
                        if (entry2 != null) {
                            entry2.setMiniCardView(customRowEntry5.getMiniCardView());
                            entry2.setMiniCardViewIndex(customRowEntry5.getMiniCardViewIndex());
                            entry2.setImmersiveCardView(customRowEntry5.getImmersiveCardView());
                            entry2.setMiniCardRemind(customRowEntry5.getMiniCardRemind());
                            entry2.getExtras().putBoolean(KEY_LOCK_IMMERSIVE_ENABLE, customRowEntry5.getExtras().getBoolean(KEY_LOCK_IMMERSIVE_ENABLE));
                            entry2.getExtras().putInt("immersiveCardType", customRowEntry5.getExtras().getInt("immersiveCardType"));
                        }
                        if (((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isStackState() && (oplusCustomRow = oplusCustomRowController2.getOplusCustomRow()) != null) {
                            oplusCustomRow.setVisibility(0);
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            CustomRowEntry entry3 = oplusCustomRowController2.getEntry();
                            entry3.getClass();
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("update old view, service key = ", entry3.getKey(), "OplusCustomRowControllerManager-->", "SystemUi--Notification");
                        }
                        z3 = true;
                    } else {
                        CustomRowEntry entry4 = oplusCustomRowController2.getEntry();
                        entry4.getClass();
                        LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, Bundler$.ExternalSyntheticOutline0.m("error update old view, service key = ", entry4.getKey(), ", childview is null"));
                    }
                    it3 = it4;
                }
                it = it3;
                if (z3) {
                    obj = null;
                    c = '\b';
                } else {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("add new view, service key = ", customRowEntry5.getKey(), ", serviceid= ", customRowEntry5.getServiceId(), "OplusCustomRowControllerManager-->"));
                    }
                    OplusCustomRowController oplusCustomRowController3 = new OplusCustomRowController(this.context);
                    ViewGroup viewGroup = this.parentView;
                    if (viewGroup != null) {
                        oplusCustomRowController3.reinflateView(viewGroup, customRowEntry5);
                    }
                    ?? view = oplusCustomRowController3.getView();
                    View customView = customRowEntry5.getCustomView();
                    ViewParent parent = customView.getParent();
                    ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                    if (viewGroup2 != null) {
                        viewGroup2.removeView(customView);
                    }
                    ViewGroup.LayoutParams layoutParams = customView.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new FrameLayout.LayoutParams(-1, -2);
                    }
                    layoutParams.width = -1;
                    layoutParams.height = -2;
                    view.addView(customView, layoutParams);
                    ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                    if (layoutParams2 == null) {
                        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                    } else {
                        layoutParams2.width = -1;
                        layoutParams2.height = -2;
                    }
                    setupDebounceClickListener(view);
                    view.setAllChildViewInTranslateable();
                    this.customList.add(oplusCustomRowController3);
                    String packageName = customRowEntry5.getPackageName();
                    int userId = customRowEntry5.getUserId();
                    String serviceId = customRowEntry5.getServiceId();
                    obj = null;
                    c = '\b';
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, CursorUtil$.ExternalSyntheticOutline0.m("checkShouldRemoveNotification packageName= ", packageName, ", serviceId=", serviceId), null, 8);
                    if (packageName != null && serviceId != null) {
                        ((FluidReplaceNotificationManager) this.fluidReplaceNotificationManager.get()).updateFluidShowMap(serviceId, packageName, true, userId);
                        ((ReplacedNotificationRemoveHelper) this.replacedNotificationRemoveHelper.get()).checkShouldRemoveNotification(packageName, userId, serviceId);
                    }
                }
            } else {
                it = it3;
                obj = null;
                c = '\b';
                customRowEntry5 = customRowEntry7;
            }
            arrayList3.add(customRowEntry5);
            it3 = it;
        }
        this.customRowEntryList.clear();
        this.customRowEntryList.addAll(arrayList3);
        if ((this.statusBarStateController.getState() == 1 || this.statusBarStateController.getState() == 2) && !QSFeatureOption.INSTANCE.getDisableNotificationMediaSeedlingFeature()) {
            i = 0;
        } else {
            i = 0;
            setMediaControllerVisibility(false);
        }
        ArrayList<OplusCustomRowController> arrayList6 = this.customList;
        if (arrayList6.size() > 1) {
            OplusCustomRowControllerManager$setCustomContainerControllerList$$inlined$sortBy$1 oplusCustomRowControllerManager$setCustomContainerControllerList$$inlined$sortBy$1 = new OplusCustomRowControllerManager$setCustomContainerControllerList$$inlined$sortBy$1();
            oplusCustomRowControllerManager$setCustomContainerControllerList$$inlined$sortBy$1.this$0 = this;
            VarHandle.storeStoreFence();
            CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList6, oplusCustomRowControllerManager$setCustomContainerControllerList$$inlined$sortBy$1);
        }
        ArrayList<OplusCustomRowController> arrayList7 = this.customList;
        int size4 = arrayList7.size();
        while (i < size4) {
            OplusCustomRowController oplusCustomRowController4 = arrayList7.get(i);
            i++;
            OplusCustomRowController oplusCustomRowController5 = oplusCustomRowController4;
            View mBackgroundNormal = oplusCustomRowController5.getView().getMBackgroundNormal();
            if (mBackgroundNormal != null) {
                ViewBlurProxy viewBlurProxyRequireBlurProxyForView = this.viewBlurManager.requireBlurProxyForView(mBackgroundNormal, (6 & 2) == 0, SmoothRoundEx.INSTANCE.getDefaultWeight());
                if (viewBlurProxyRequireBlurProxyForView.getBlurConfig().getOnBlurDrawableCreated() == null) {
                    BlurConfig blurConfig = viewBlurProxyRequireBlurProxyForView.getBlurConfig();
                    OplusCustomRowControllerManager$$ExternalSyntheticLambda0 oplusCustomRowControllerManager$$ExternalSyntheticLambda0 = new OplusCustomRowControllerManager$$ExternalSyntheticLambda0();
                    oplusCustomRowControllerManager$$ExternalSyntheticLambda0.f$0 = oplusCustomRowController5;
                    oplusCustomRowControllerManager$$ExternalSyntheticLambda0.f$1 = viewBlurProxyRequireBlurProxyForView;
                    VarHandle.storeStoreFence();
                    blurConfig.setOnBlurDrawableCreated(oplusCustomRowControllerManager$$ExternalSyntheticLambda0);
                }
                this.viewBlurManager.refreshBlurType(mBackgroundNormal);
            }
        }
        this.mNotifFilter.invalidateList("updateOplusCustomRow");
    }

    public final void setCustomContainerVisibilityGone() {
        ArrayList<OplusCustomRowController> arrayList = this.customList;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            OplusCustomRowController oplusCustomRowController = arrayList.get(i);
            i++;
            oplusCustomRowController.getView().setVisibility(8);
        }
    }

    public final void setDismissUsingRowTranslationX(boolean z) {
        ArrayList<OplusCustomRowController> arrayList = this.customList;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            OplusCustomRowController oplusCustomRowController = arrayList.get(i);
            i++;
            OplusCustomRow view = oplusCustomRowController.getView();
            ((ExpandableOutlineView) view).mDismissUsingRowTranslationX = z;
            view.setDismissUsingRowTranslationXInner(z);
        }
    }

    public final void setMediaControllerVisibility(boolean z) {
        OplusCustomRowController oplusCustomRowController;
        ExpandableViewState viewState;
        OplusCustomRow.Companion companion;
        CustomRowEntry entry;
        ArrayList<OplusCustomRowController> arrayList = this.customList;
        int size = arrayList.size();
        int i = 0;
        do {
            if (i >= size) {
                oplusCustomRowController = null;
                break;
            }
            oplusCustomRowController = arrayList.get(i);
            i++;
            companion = OplusCustomRow.Companion;
            entry = oplusCustomRowController.getEntry();
        } while (!companion.isOplusMediaCustomRow(entry != null ? entry.getServiceId() : null));
        OplusCustomRowController oplusCustomRowController2 = oplusCustomRowController;
        if (oplusCustomRowController2 != null) {
            OplusCustomRow oplusCustomRow = oplusCustomRowController2.getOplusCustomRow();
            if (oplusCustomRow != null && (viewState = oplusCustomRow.getViewState()) != null) {
                ((ViewState) viewState).gone = !z;
            }
            if (!z) {
                OplusCustomRow oplusCustomRow2 = oplusCustomRowController2.getOplusCustomRow();
                if (oplusCustomRow2 != null) {
                    oplusCustomRow2.setVisibility(8);
                    return;
                }
                return;
            }
            LaunchableView oplusCustomRow3 = oplusCustomRowController2.getOplusCustomRow();
            if ((oplusCustomRow3 == null || oplusCustomRow3.getVisibility() != 0) && ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isCapsuleState()) {
                OplusCustomRow oplusCustomRow4 = oplusCustomRowController2.getOplusCustomRow();
                if (oplusCustomRow4 != null) {
                    oplusCustomRow4.setVisibility(4);
                }
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "setMediaControllerVisibility set INVISIBLE", null, 8);
                return;
            }
            OplusCustomRow oplusCustomRow5 = oplusCustomRowController2.getOplusCustomRow();
            if (oplusCustomRow5 != null) {
                oplusCustomRow5.setVisibility(0);
            }
        }
    }

    public final void setNssLController(NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        this.mStackScrollerController = notificationStackScrollLayoutController;
    }

    public final void setParentView(ViewGroup viewGroup) {
        this.parentView = viewGroup;
    }

    public final void setRequestImmersiveStateListener(RequestImmersiveStateListener requestImmersiveStateListener) {
        this.requestImmersiveStateListener = requestImmersiveStateListener;
    }

    public final void setWindowInsetChangedListener(Function0 function0) {
        NotificationStackScrollLayout view;
        NotificationStackScrollLayoutExt notificationStackScrollLayoutExt;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mStackScrollerController;
        if (notificationStackScrollLayoutController == null || (view = notificationStackScrollLayoutController.getView()) == null || (notificationStackScrollLayoutExt = view.mExt) == null) {
            return;
        }
        notificationStackScrollLayoutExt.setWindowInsetChangedListener(function0);
    }

    public final void startPopupMenu(CustomRowEntry customRowEntry) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, FileProvider$.ExternalSyntheticOutline0.m("startPopupMenu servicekey = ", customRowEntry.getKey()), null, 8);
        if (!((OplusCustomPopupMenuControllerImpl) this.oplusCustomPopupMenuController).isPopupMenuShowing) {
            this.oplusCustomPopupMenuController.showPopupMenu(customRowEntry.getCustomView(), false, customRowEntry, true, null);
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusCustomRowControllerManager-->popupMenu isShowing!");
        }
    }

    public final void startSeedingViewIntent(Intent intent, int i, String str, int i2) {
        NotificationStackScrollLayoutControllerExt ext;
        OplusCustomRowController oplusCustomRowController;
        NotificationStackScrollLayoutControllerExt ext2;
        CustomRowEntry entry;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(i, "startSeedingViewIntent, appUid = ", ", serviceId = ", str, ", entranceType=");
        sbM.append(i2);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        if (i2 != 0) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "startSeedingViewIntent on no view", null, 8);
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mStackScrollerController;
            if (notificationStackScrollLayoutController == null || (ext = notificationStackScrollLayoutController.getExt()) == null) {
                return;
            }
            ext.startSeedingViewIntent(intent, i, (LaunchableView) null);
            return;
        }
        ArrayList<OplusCustomRowController> arrayList = this.customList;
        int size = arrayList.size();
        int i3 = 0;
        do {
            if (i3 >= size) {
                oplusCustomRowController = null;
                break;
            } else {
                oplusCustomRowController = arrayList.get(i3);
                i3++;
                entry = oplusCustomRowController.getEntry();
            }
        } while (!Intrinsics.areEqual(entry != null ? entry.getKey() : null, str));
        OplusCustomRowController oplusCustomRowController2 = oplusCustomRowController;
        OplusCustomRow view = oplusCustomRowController2 != null ? oplusCustomRowController2.getView() : null;
        if (view == null) {
            ((ShadeController) this.shadeController.get()).instantCollapseShade();
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "startSeedingViewIntent on notification, view=" + view, null, 8);
        NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = this.mStackScrollerController;
        if (notificationStackScrollLayoutController2 == null || (ext2 = notificationStackScrollLayoutController2.getExt()) == null) {
            return;
        }
        ext2.startSeedingViewIntent(intent, i, view);
    }
}
