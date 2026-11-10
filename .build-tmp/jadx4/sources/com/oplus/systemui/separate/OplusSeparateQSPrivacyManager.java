package com.oplus.systemui.separate;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.UserInfo;
import android.os.Parcelable;
import android.os.UserHandle;
import android.permission.PermissionGroupUsage;
import android.permission.PermissionManager;
import android.safetycenter.SafetyCenterManager;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import com.android.settingslib.metadata.UtilsKt$;
import com.android.systemui.appops.AppOpsController;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.qs.QSPrivary;
import com.android.systemui.privacy.AbstractOngoingPrivacyChip;
import com.android.systemui.privacy.PrivacyDialogController;
import com.android.systemui.privacy.PrivacyDialogControllerV2;
import com.android.systemui.privacy.PrivacyDialogDelegate;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.privacy.PrivacyType;
import com.android.systemui.settings.UserTracker;
import com.oplus.systemui.privacy.dialog.OplusQSPrivacyDialogController;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSPrivacyManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusSeparateQSPrivacyManager";
    private final Lazy activityStarter;
    private final AppOpsController appOpsController;
    private final View.OnAttachStateChangeListener attachStateChangeListener;
    private final Executor backgroundExecutor;
    private final BroadcastDispatcher broadcastDispatcher;
    private final Context context;
    private final FeatureFlags featureFlags;
    private String lastPrivacyPackageName;
    private final Lazy oplusActivityStarter;
    private final Lazy oplusQSPrivacyDialogController;
    private final PackageManager packageManager;
    private final PermissionManager permissionManager;
    private final PrivacyItemController.Callback picCallback;
    private final AbstractOngoingPrivacyChip privacyChip;
    private final PrivacyDialogController privacyDialogController;
    private final PrivacyDialogControllerV2 privacyDialogController2;
    private final PrivacyItemController privacyItemController;
    private QSPrivary.QSPrivacyInfo qsPrivacyInfo;
    private final CopyOnWriteArrayList<QSPrivary.CallBack> qsPrivaryCallBacks = new CopyOnWriteArrayList<>();
    private QSPrivaryImpl qsPrivaryImpl;
    public boolean safetyCenterEnabled;
    private final SafetyCenterManager safetyCenterManager;
    private final OplusSeparateQSPrivacyManager$safetyCenterReceiver$1 safetyCenterReceiver;
    private final ScenarioAssistantRepository scenarioRepository;
    private final UserTracker userTracker;

    /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusSeparateQSPrivacyManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusSeparateQSPrivacyManager this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    OplusSeparateQSPrivacyManager oplusSeparateQSPrivacyManager = this.this$0;
                    oplusSeparateQSPrivacyManager.safetyCenterEnabled = oplusSeparateQSPrivacyManager.safetyCenterManager.isSafetyCenterEnabled();
                    break;
                default:
                    ArrayList<? extends Parcelable> arrayList = new ArrayList<>(this.this$0.permGroupUsage());
                    Intent intent = new Intent("android.intent.action.VIEW_SAFETY_CENTER_QS");
                    intent.putParcelableArrayListExtra("android.permission.extra.PERMISSION_USAGES", arrayList);
                    intent.setFlags(268435456);
                    OplusSeparateQSPrivacyManager.access$handleStartActivity(this.this$0, intent);
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class QSPrivaryImpl implements QSPrivary {
        private final Context context;

        public QSPrivaryImpl(Context context) {
            this.context = context;
        }

        public void addCallBack(QSPrivary.CallBack callBack) {
            OplusSeparateQSPrivacyManager oplusSeparateQSPrivacyManager = OplusSeparateQSPrivacyManager.this;
            oplusSeparateQSPrivacyManager.qsPrivaryCallBacks.add(callBack);
            QSPrivary.QSPrivacyInfo qSPrivacyInfo = oplusSeparateQSPrivacyManager.qsPrivacyInfo;
            if (qSPrivacyInfo != null) {
                callBack.onPrivacyItemsChanged(this.context, qSPrivacyInfo);
            }
        }

        public final Context getContext() {
            return this.context;
        }

        public CharSequence getFinalText(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
            String string;
            if (charSequence2 != null && charSequence3 != null) {
                string = this.context.getString(2131955012, charSequence2, charSequence3);
            } else if (charSequence2 != null) {
                string = this.context.getString(2131955011, charSequence2);
            } else {
                string = charSequence3 != null ? this.context.getString(2131955013, charSequence3) : null;
            }
            return string != null ? TextUtils.concat(charSequence, " ", string) : charSequence;
        }

        public String getManageBtnText() {
            return this.context.getString(2131955549);
        }

        public List<QSPrivary.QSPrivacyElement> getPrivacyElementList() {
            List<PrivacyDialogDelegate.PrivacyElement> list = OplusSeparateQSPrivacyManager.this.getList();
            ArrayList arrayList = new ArrayList();
            for (PrivacyDialogDelegate.PrivacyElement privacyElement : list) {
                arrayList.add(new QSPrivary.QSPrivacyElement(privacyElement.getPackageName(), privacyElement.getUserId(), privacyElement.getApplicationName(), privacyElement.getPermGroupName(), privacyElement.getNavigationIntent(), privacyElement.getAttributionTag(), privacyElement.getAttributionLabel(), privacyElement.getProxyLabel(), privacyElement.getActive()));
            }
            LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSPrivacyManager.TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(arrayList.size(), "getPrivacyElementList size: "), null, 8);
            return arrayList;
        }

        public boolean getSafetyCenterEnabled() {
            return OplusSeparateQSPrivacyManager.this.safetyCenterEnabled;
        }

        public Flow getScenarioPreviewInfo() {
            StateFlow scenarioInfo = OplusSeparateQSPrivacyManager.this.scenarioRepository.getScenarioInfo();
            OplusSeparateQSPrivacyManager$QSPrivaryImpl$getScenarioPreviewInfo$$inlined$map$1 oplusSeparateQSPrivacyManager$QSPrivaryImpl$getScenarioPreviewInfo$$inlined$map$1 = new OplusSeparateQSPrivacyManager$QSPrivaryImpl$getScenarioPreviewInfo$$inlined$map$1();
            oplusSeparateQSPrivacyManager$QSPrivaryImpl$getScenarioPreviewInfo$$inlined$map$1.$this_unsafeTransform$inlined = scenarioInfo;
            VarHandle.storeStoreFence();
            return FlowKt.distinctUntilChanged(oplusSeparateQSPrivacyManager$QSPrivaryImpl$getScenarioPreviewInfo$$inlined$map$1);
        }

        public String getUsageText(QSPrivary.QSPrivacyElement qSPrivacyElement) {
            String string;
            if (qSPrivacyElement.getAttributionLabel() == null && qSPrivacyElement.getProxyLabel() == null) {
                string = this.context.getString(qSPrivacyElement.getActive() ? 2131955539 : 2131955551, qSPrivacyElement.getApplicationName());
            } else if (qSPrivacyElement.getAttributionLabel() == null || qSPrivacyElement.getProxyLabel() == null) {
                int i = qSPrivacyElement.getActive() ? 2131955540 : 2131955552;
                Context context = this.context;
                CharSequence applicationName = qSPrivacyElement.getApplicationName();
                CharSequence attributionLabel = qSPrivacyElement.getAttributionLabel();
                if (attributionLabel == null) {
                    attributionLabel = qSPrivacyElement.getProxyLabel();
                }
                string = context.getString(i, applicationName, attributionLabel);
            } else {
                string = this.context.getString(qSPrivacyElement.getActive() ? 2131955541 : 2131955553, qSPrivacyElement.getApplicationName(), qSPrivacyElement.getAttributionLabel(), qSPrivacyElement.getProxyLabel());
            }
            string.getClass();
            return string;
        }

        public final boolean isFeatureFlagsEnable() {
            return OplusSeparateQSPrivacyManager.this.featureFlags.isEnabled(Flags.ENABLE_NEW_PRIVACY_DIALOG);
        }

        public String permGroupToPrivacyType(String str) {
            int iHashCode = str.hashCode();
            if (iHashCode == -1140935117) {
                return str.equals("android.permission-group.CAMERA") ? PrivacyType.TYPE_CAMERA.getName(this.context) : "";
            }
            if (iHashCode != 828638019) {
                return (iHashCode == 1581272376 && str.equals("android.permission-group.MICROPHONE")) ? PrivacyType.TYPE_MICROPHONE.getName(this.context) : "";
            }
            return !str.equals("android.permission-group.LOCATION") ? "" : PrivacyType.TYPE_LOCATION.getName(this.context);
        }

        public void removeCallBack(QSPrivary.CallBack callBack) {
            OplusSeparateQSPrivacyManager.this.qsPrivaryCallBacks.remove(callBack);
        }

        public void showDialog(View view) {
            boolean safetyCenterEnabled = getSafetyCenterEnabled();
            OplusSeparateQSPrivacyManager oplusSeparateQSPrivacyManager = OplusSeparateQSPrivacyManager.this;
            if (!safetyCenterEnabled) {
                ((OplusQSPrivacyDialogController) oplusSeparateQSPrivacyManager.oplusQSPrivacyDialogController.get()).showDialog(view);
            } else if (isFeatureFlagsEnable()) {
                ((OplusQSPrivacyDialogController) oplusSeparateQSPrivacyManager.oplusQSPrivacyDialogController.get()).showDialog(view);
            } else {
                showSafetyCenter();
            }
        }

        public final void showPrivacyDialog() {
            OplusSeparateQSPrivacyManager.this.privacyDialogController.showDialog(this.context);
        }

        public final void showSafetyCenter() {
            OplusSeparateQSPrivacyManager oplusSeparateQSPrivacyManager = OplusSeparateQSPrivacyManager.this;
            Executor executor = oplusSeparateQSPrivacyManager.backgroundExecutor;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
            anonymousClass1.this$0 = oplusSeparateQSPrivacyManager;
            VarHandle.storeStoreFence();
            executor.execute(anonymousClass1);
        }

        public final void showV2PrivacyDialog() {
            PrivacyDialogControllerV2 privacyDialogControllerV2 = OplusSeparateQSPrivacyManager.this.privacyDialogController2;
            Context context = this.context;
            PrivacyDialogControllerV2.Companion companion = PrivacyDialogControllerV2.Companion;
            privacyDialogControllerV2.showDialog(context, (AbstractOngoingPrivacyChip) null);
        }

        public void startActivity(String str, int i, Intent intent, View view) {
            LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSPrivacyManager.TAG, "startActivity navigationIntent = " + intent, null, 8);
            OplusSeparateQSPrivacyManager.access$handleStartActivity(OplusSeparateQSPrivacyManager.this, str, i, intent, view);
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [android.content.BroadcastReceiver, com.oplus.systemui.separate.OplusSeparateQSPrivacyManager$safetyCenterReceiver$1] */
    public OplusSeparateQSPrivacyManager(Context context, PermissionManager permissionManager, PackageManager packageManager, PrivacyItemController privacyItemController, UserTracker userTracker, Executor executor, AppOpsController appOpsController, Lazy lazy, Lazy lazy2, SafetyCenterManager safetyCenterManager, BroadcastDispatcher broadcastDispatcher, FeatureFlags featureFlags, PrivacyDialogController privacyDialogController, PrivacyDialogControllerV2 privacyDialogControllerV2, Lazy lazy3, AbstractOngoingPrivacyChip abstractOngoingPrivacyChip, ScenarioAssistantRepository scenarioAssistantRepository) {
        this.context = context;
        this.permissionManager = permissionManager;
        this.packageManager = packageManager;
        this.privacyItemController = privacyItemController;
        this.userTracker = userTracker;
        this.backgroundExecutor = executor;
        this.appOpsController = appOpsController;
        this.activityStarter = lazy;
        this.oplusActivityStarter = lazy2;
        this.safetyCenterManager = safetyCenterManager;
        this.broadcastDispatcher = broadcastDispatcher;
        this.featureFlags = featureFlags;
        this.privacyDialogController = privacyDialogController;
        this.privacyDialogController2 = privacyDialogControllerV2;
        this.oplusQSPrivacyDialogController = lazy3;
        this.privacyChip = abstractOngoingPrivacyChip;
        this.scenarioRepository = scenarioAssistantRepository;
        OplusSeparateQSPrivacyManager$picCallback$1 oplusSeparateQSPrivacyManager$picCallback$1 = new OplusSeparateQSPrivacyManager$picCallback$1();
        oplusSeparateQSPrivacyManager$picCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.picCallback = oplusSeparateQSPrivacyManager$picCallback$1;
        ?? r2 = new BroadcastReceiver() { // from class: com.oplus.systemui.separate.OplusSeparateQSPrivacyManager$safetyCenterReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                OplusSeparateQSPrivacyManager oplusSeparateQSPrivacyManager = this.this$0;
                oplusSeparateQSPrivacyManager.safetyCenterEnabled = oplusSeparateQSPrivacyManager.safetyCenterManager.isSafetyCenterEnabled();
            }
        };
        this.safetyCenterReceiver = r2;
        OplusSeparateQSPrivacyManager$attachStateChangeListener$1 oplusSeparateQSPrivacyManager$attachStateChangeListener$1 = new OplusSeparateQSPrivacyManager$attachStateChangeListener$1();
        oplusSeparateQSPrivacyManager$attachStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.attachStateChangeListener = oplusSeparateQSPrivacyManager$attachStateChangeListener$1;
        privacyItemController.addCallback(oplusSeparateQSPrivacyManager$picCallback$1);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        executor.execute(anonymousClass1);
        if (abstractOngoingPrivacyChip.isAttachedToWindow()) {
            BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, (BroadcastReceiver) r2, new IntentFilter("android.safetycenter.action.SAFETY_CENTER_ENABLED_CHANGED"), executor, (UserHandle) null, 0, 56);
        }
        abstractOngoingPrivacyChip.addOnAttachStateChangeListener(oplusSeparateQSPrivacyManager$attachStateChangeListener$1);
    }

    public static final void access$handleStartActivity(OplusSeparateQSPrivacyManager oplusSeparateQSPrivacyManager, String str, int i, Intent intent, View view) {
        if (intent == null) {
            intent = oplusSeparateQSPrivacyManager.getDefaultManageAppPermissionsIntent(str, i);
        }
        if (view != null) {
            ((OplusQSActivityStarter) oplusSeparateQSPrivacyManager.oplusActivityStarter.get()).onAnimationButtonClicked(view, intent, OplusQSActivityStarter.QSTransitionAnimator.QUICKENTRANCE);
        } else {
            ((ActivityStarter) oplusSeparateQSPrivacyManager.activityStarter.get()).startActivity(intent, true);
        }
    }

    public final QSPrivary bindQSPrivary() {
        if (this.qsPrivaryImpl == null) {
            this.qsPrivaryImpl = new QSPrivaryImpl(this.context);
        }
        return this.qsPrivaryImpl;
    }

    public final View.OnAttachStateChangeListener getAttachStateChangeListener() {
        return this.attachStateChangeListener;
    }

    public final Intent getDefaultManageAppPermissionsIntent(String str, int i) {
        Intent intent = new Intent("android.intent.action.MANAGE_APP_PERMISSIONS");
        intent.putExtra("android.intent.extra.PACKAGE_NAME", str);
        intent.putExtra("android.intent.extra.USER", UserHandle.of(i));
        return intent;
    }

    public final CharSequence getLabelForPackage(int i, String str) {
        try {
            CharSequence charSequenceLoadLabel = this.packageManager.getApplicationInfoAsUser(str, 0, UserHandle.getUserId(i)).loadLabel(this.packageManager);
            charSequenceLoadLabel.getClass();
            return charSequenceLoadLabel;
        } catch (PackageManager.NameNotFoundException unused) {
            MotionLayout$.ExternalSyntheticOutline0.m("Label not found for: ", str, TAG);
            return str;
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0073  */
    /* JADX WARN: Code duplicated, block: B:77:0x0185  */
    public final List<PrivacyDialogDelegate.PrivacyElement> getList() {
        Object next;
        List listEmptyList;
        PrivacyType privacyType;
        Object next2;
        PrivacyDialogDelegate.PrivacyElement privacyElement;
        Intent defaultManageAppPermissionsIntent;
        ActivityInfo activityInfo;
        List permGroupNameList;
        List listPermGroupUsage = permGroupUsage();
        PrivacyType privacyType2 = null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(listPermGroupUsage.size(), "getList usage.size: "), null, 8);
        List userProfiles = this.userTracker.getUserProfiles();
        ArrayList arrayList = new ArrayList();
        Iterator it = listPermGroupUsage.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PermissionGroupUsage permissionGroupUsage = (PermissionGroupUsage) it.next();
            String permissionGroupName = permissionGroupUsage.getPermissionGroupName();
            int iHashCode = permissionGroupName.hashCode();
            if (iHashCode != -1140935117) {
                if (iHashCode != 828638019) {
                    if (iHashCode == 1581272376 && permissionGroupName.equals("android.permission-group.MICROPHONE")) {
                        privacyType = PrivacyType.TYPE_MICROPHONE;
                    } else {
                        privacyType = privacyType2;
                    }
                } else if (permissionGroupName.equals("android.permission-group.LOCATION")) {
                    privacyType = PrivacyType.TYPE_LOCATION;
                } else {
                    privacyType = privacyType2;
                }
            } else if (permissionGroupName.equals("android.permission-group.CAMERA")) {
                privacyType = PrivacyType.TYPE_CAMERA;
            } else {
                privacyType = privacyType2;
            }
            PrivacyType privacyType3 = (privacyType == null || !(((privacyType == PrivacyType.TYPE_CAMERA || privacyType == PrivacyType.TYPE_MICROPHONE) && this.privacyItemController.getMicCameraAvailable()) || (privacyType == PrivacyType.TYPE_LOCATION && this.privacyItemController.getLocationAvailable()))) ? privacyType2 : privacyType;
            QSPrivary.QSPrivacyInfo qSPrivacyInfo = this.qsPrivacyInfo;
            boolean z = (qSPrivacyInfo == null || (permGroupNameList = qSPrivacyInfo.getPermGroupNameList()) == null || !permGroupNameList.contains(permissionGroupUsage.getPermissionGroupName())) ? false : true;
            Iterator it2 = userProfiles.iterator();
            do {
                if (!it2.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = it2.next();
            } while (((UserInfo) next2).id != UserHandle.getUserId(permissionGroupUsage.getUid()));
            UserInfo userInfo = (UserInfo) next2;
            if (!z || ((userInfo == null && !permissionGroupUsage.isPhoneCall()) || privacyType3 == null)) {
                privacyElement = null;
            } else {
                CharSequence labelForPackage = getLabelForPackage(permissionGroupUsage.getUid(), permissionGroupUsage.getPackageName());
                int userId = UserHandle.getUserId(permissionGroupUsage.getUid());
                String packageName = permissionGroupUsage.getPackageName();
                CharSequence attributionTag = permissionGroupUsage.getAttributionTag();
                CharSequence attributionLabel = permissionGroupUsage.getAttributionLabel();
                CharSequence proxyLabel = permissionGroupUsage.getProxyLabel();
                long lastAccessTimeMillis = permissionGroupUsage.getLastAccessTimeMillis();
                boolean zIsActive = permissionGroupUsage.isActive();
                boolean zIsManagedProfile = userInfo != null ? userInfo.isManagedProfile() : false;
                boolean zIsPhoneCall = permissionGroupUsage.isPhoneCall();
                String permissionGroupName2 = permissionGroupUsage.getPermissionGroupName();
                String packageName2 = permissionGroupUsage.getPackageName();
                String permissionGroupName3 = permissionGroupUsage.getPermissionGroupName();
                CharSequence attributionTag2 = permissionGroupUsage.getAttributionTag();
                boolean z2 = permissionGroupUsage.getAttributionLabel() != null;
                if (attributionTag2 == null || !z2) {
                    defaultManageAppPermissionsIntent = getDefaultManageAppPermissionsIntent(packageName2, userId);
                } else {
                    defaultManageAppPermissionsIntent = UtilsKt$.ExternalSyntheticOutline0.m("android.intent.action.MANAGE_PERMISSION_USAGE", packageName2);
                    defaultManageAppPermissionsIntent.putExtra("android.intent.extra.PERMISSION_GROUP_NAME", permissionGroupName3.toString());
                    defaultManageAppPermissionsIntent.putExtra("android.intent.extra.ATTRIBUTION_TAGS", new String[]{attributionTag2.toString()});
                    defaultManageAppPermissionsIntent.putExtra("android.intent.extra.SHOWING_ATTRIBUTION", true);
                    ResolveInfo resolveInfoResolveActivity = this.packageManager.resolveActivity(defaultManageAppPermissionsIntent, PackageManager.ResolveInfoFlags.of(0L));
                    if (resolveInfoResolveActivity == null || (activityInfo = resolveInfoResolveActivity.activityInfo) == null || !Intrinsics.areEqual(activityInfo.permission, "android.permission.START_VIEW_PERMISSION_USAGE")) {
                        defaultManageAppPermissionsIntent = getDefaultManageAppPermissionsIntent(packageName2, userId);
                    } else {
                        defaultManageAppPermissionsIntent.setComponent(new ComponentName(packageName2, resolveInfoResolveActivity.activityInfo.name));
                    }
                }
                privacyElement = new PrivacyDialogDelegate.PrivacyElement(privacyType3, packageName, userId, labelForPackage, attributionTag, attributionLabel, proxyLabel, lastAccessTimeMillis, zIsActive, zIsManagedProfile, zIsPhoneCall, permissionGroupName2, defaultManageAppPermissionsIntent);
            }
            if (privacyElement != null) {
                arrayList.add(privacyElement);
            }
            privacyType2 = null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            PrivacyType type = ((PrivacyDialogDelegate.PrivacyElement) obj).getType();
            Object arrayList2 = linkedHashMap.get(type);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap.put(type, arrayList2);
            }
            ((List) arrayList2).add(obj);
        }
        SortedMap sortedMap = MapsKt__MapsJVMKt.toSortedMap(linkedHashMap);
        ArrayList arrayList3 = new ArrayList();
        Iterator it3 = sortedMap.entrySet().iterator();
        while (it3.hasNext()) {
            List list = (List) ((Map.Entry) it3.next()).getValue();
            list.getClass();
            List list2 = list;
            ArrayList arrayList4 = new ArrayList();
            for (Object obj2 : list2) {
                PrivacyDialogDelegate.PrivacyElement privacyElement2 = (PrivacyDialogDelegate.PrivacyElement) obj2;
                if (privacyElement2.getActive() && !PrivacyDialogController.Companion.getMDotHidePkgs().contains(privacyElement2.getPackageName())) {
                    arrayList4.add(obj2);
                }
            }
            if (arrayList4.isEmpty()) {
                Iterator it4 = list2.iterator();
                if (it4.hasNext()) {
                    next = it4.next();
                    if (it4.hasNext()) {
                        long lastActiveTimestamp = ((PrivacyDialogDelegate.PrivacyElement) next).getLastActiveTimestamp();
                        do {
                            Object next3 = it4.next();
                            long lastActiveTimestamp2 = ((PrivacyDialogDelegate.PrivacyElement) next3).getLastActiveTimestamp();
                            if (lastActiveTimestamp < lastActiveTimestamp2) {
                                next = next3;
                                lastActiveTimestamp = lastActiveTimestamp2;
                            }
                        } while (it4.hasNext());
                    }
                } else {
                    next = null;
                }
                PrivacyDialogDelegate.PrivacyElement privacyElement3 = (PrivacyDialogDelegate.PrivacyElement) next;
                if (privacyElement3 == null || (listEmptyList = CollectionsKt__CollectionsJVMKt.listOf(privacyElement3)) == null) {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                }
            } else {
                listEmptyList = CollectionsKt___CollectionsKt.sortedWith(arrayList4, new OplusSeparateQSPrivacyManager$filterAndSelect$lambda$9$$inlined$sortedByDescending$1(0));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList3, listEmptyList);
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(arrayList3.size(), "getList size: "), null, 8);
        return arrayList3;
    }

    public final List permGroupUsage() {
        return this.permissionManager.getIndicatorAppOpUsageData(this.appOpsController.mMicMuted);
    }

    public static final void access$handleStartActivity(OplusSeparateQSPrivacyManager oplusSeparateQSPrivacyManager, Intent intent) {
        ((ActivityStarter) oplusSeparateQSPrivacyManager.activityStarter.get()).startActivity(intent, true);
    }
}
