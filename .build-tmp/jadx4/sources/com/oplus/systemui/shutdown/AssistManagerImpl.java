package com.oplus.systemui.shutdown;

import android.app.OplusActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.navigationbar.NavBarHelper;
import com.android.systemui.navigationbar.otherbusiness.circlesearch.OplusCircleToSearchManagerEx;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.shutdown.AssistManagerEx;
import com.oplus.systemui.shutdown.observer.AssistSettingObserver;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.ShutdownFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.ListIterator;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AssistManagerImpl extends AssistManagerEx {
    private static final String ASSISTANT = "assistant";
    public static final Companion Companion = new Companion();
    private static final String EXTRA_ASSIST_MANAGER_LAUNCH_MODE = "assistant_launch_mode";
    private static final String KEY_ASSIST_STARTED = "op_assist_started";
    private static final String POWER_LONG_PRESS_WITH_ASSISTANT_HINT = "power_long_press_with_assistant_hint";
    private static final String PROP_IS_MONKEY_RUNNING = "oplus.autotest.monkeyRunning";
    private static final String TAG = "AssistManagerImpl";
    private final Lazy assistManager$delegate = LazyKt__LazyJVMKt.lazy(new AssistManagerImpl$$ExternalSyntheticLambda0());
    private final AssistSettingObserver assistSettingObserver;
    private final OplusCircleToSearchManagerEx circleToSearchManager;
    private final Context context;
    public boolean isPowerLongPressWithAssistant;
    private final NavBarHelper navBarHelper;
    private final OpAssistNavigationDialog opAssistNavigationDialog;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AssistManagerImpl(Context context, NavBarHelper navBarHelper, OplusCircleToSearchManagerEx oplusCircleToSearchManagerEx, ActivityStarter activityStarter) {
        this.context = context;
        this.navBarHelper = navBarHelper;
        this.circleToSearchManager = oplusCircleToSearchManagerEx;
        AssistSettingObserver assistSettingObserver = new AssistSettingObserver(context);
        this.assistSettingObserver = assistSettingObserver;
        OpAssistNavigationDialog opAssistNavigationDialog = new OpAssistNavigationDialog();
        opAssistNavigationDialog.mIsShowing = false;
        opAssistNavigationDialog.mCurrentPage = 0;
        opAssistNavigationDialog.mContext = context;
        opAssistNavigationDialog.mWindowManager = (WindowManager) context.getSystemService("window");
        opAssistNavigationDialog.mActivityStarter = activityStarter;
        this.opAssistNavigationDialog = opAssistNavigationDialog;
        assistSettingObserver.observe();
    }

    private final AssistManager getAssistManager() {
        return (AssistManager) this.assistManager$delegate.getValue();
    }

    public void beforeStartAssistInternal(Bundle bundle, int i, ComponentName componentName, boolean z) {
        List listEmptyList;
        boolean zIsLockDeviceMode = OplusActivityTaskManager.getInstance().isLockDeviceMode();
        Boolean boolValueOf = Boolean.valueOf(zIsLockDeviceMode);
        if (!zIsLockDeviceMode) {
            boolValueOf = null;
        }
        if (boolValueOf != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "AssistManagerImpl-->not startAssist because device is locked");
        }
        if (zIsLockDeviceMode || this.circleToSearchManager.interceptStartAssistInternal(bundle)) {
            return;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion() && i == 5 && !this.navBarHelper.getLongPressHomeEnabled()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "AssistManagerImpl-->not startAssist because long press home wake assist is disabled");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_GLOBALACTIONS)) {
            StringBuilder sb = new StringBuilder("legacyInvocationType: ");
            sb.append(i);
            sb.append(" ,assistComponent:");
            sb.append(componentName);
            sb.append(" ,isService : ");
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("AssistManagerImpl-->", "SystemUi--GlobalActions", sb, z);
        }
        ShutdownFeatureOption.INSTANCE.getClass();
        if (ShutdownFeatureOption.isBreenoAsVoiceAssistant() || ShutdownFeatureOption.isPressPowerButtonThreeSecondsToShutDownSupport()) {
            this.isPowerLongPressWithAssistant = false;
            getAssistManager().startAssistInternal(this.context, bundle, componentName, z);
            return;
        }
        this.isPowerLongPressWithAssistant = bundle.getBoolean(POWER_LONG_PRESS_WITH_ASSISTANT_HINT, false);
        boolean z2 = Settings.Secure.getIntForUser(this.context.getContentResolver(), KEY_ASSIST_STARTED, 0, -2) != 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_GLOBALACTIONS)) {
            Log.d("SystemUi--GlobalActions", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("LongPressWithAssistant: ", ", assistStarted: ", "AssistManagerImpl-->", this.isPowerLongPressWithAssistant, z2));
        }
        if (!z2 && this.isPowerLongPressWithAssistant && i == 6) {
            if (SystemProperties.getBoolean(PROP_IS_MONKEY_RUNNING, false) || FeatureOption.isAgingVersion() || FeatureOption.isFactoryMode()) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "not start opAssistNavigationDialog because is auto test ", null, 8);
                return;
            }
            OpAssistNavigationDialog opAssistNavigationDialog = this.opAssistNavigationDialog;
            beforeStartAssistInternal.3 r11 = new beforeStartAssistInternal.3();
            r11.this$0 = this;
            VarHandle.storeStoreFence();
            ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_GLOBALACTIONS, "OpAssistNavDialog", new StringBuilder("OpAssistNavigationDialog showDialog mIsShowing:"), opAssistNavigationDialog.mIsShowing);
            ActivityStarter activityStarter = opAssistNavigationDialog.mActivityStarter;
            OpAssistNavigationDialog$$ExternalSyntheticLambda0 opAssistNavigationDialog$$ExternalSyntheticLambda0 = new OpAssistNavigationDialog$$ExternalSyntheticLambda0();
            opAssistNavigationDialog$$ExternalSyntheticLambda0.f$0 = opAssistNavigationDialog;
            opAssistNavigationDialog$$ExternalSyntheticLambda0.f$1 = r11;
            VarHandle.storeStoreFence();
            activityStarter.dismissKeyguardThenExecute(opAssistNavigationDialog$$ExternalSyntheticLambda0, (Runnable) null, true);
            return;
        }
        String stringForUser = Settings.Secure.getStringForUser(this.context.getContentResolver(), ASSISTANT, -2);
        if (TextUtils.isEmpty(stringForUser)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_GLOBALACTIONS)) {
                Log.d("SystemUi--GlobalActions", "AssistManagerImpl-->opAssistPicker is empty!");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_GLOBALACTIONS)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("opAssistPicker: ", stringForUser, "AssistManagerImpl-->", "SystemUi--GlobalActions");
        }
        stringForUser.getClass();
        List listSplit = new Regex("/").split(stringForUser, 0);
        if (!listSplit.isEmpty()) {
            ListIterator listIterator = listSplit.listIterator(listSplit.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                    break;
                } else if (((String) listIterator.previous()).length() != 0) {
                    listEmptyList = CollectionsKt___CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                    break;
                }
            }
        } else {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            break;
        }
        String[] strArr = (String[]) listEmptyList.toArray(new String[0]);
        if (strArr.length == 2) {
            ComponentName componentName2 = new ComponentName(strArr[0], StringsKt__StringsJVMKt.startsWith(strArr[1], ".", false) ? Bundler$.ExternalSyntheticOutline0.m(strArr[0], strArr[1]) : strArr[1]);
            boolean zEquals = componentName2.equals(getAssistManager().getVoiceInteractorComponentName());
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_GLOBALACTIONS)) {
                Log.d("SystemUi--GlobalActions", "AssistManagerImpl-->".concat("componentName: " + componentName2 + " opIsService: " + zEquals));
            }
            AssistManagerLaunchMode[] assistManagerLaunchModeArr = AssistManagerLaunchMode.$VALUES;
            bundle.putInt(EXTRA_ASSIST_MANAGER_LAUNCH_MODE, 1);
            getAssistManager().startAssistInternal(this.context, bundle, componentName2, zEquals);
        }
    }

    public final Context getContext() {
        return this.context;
    }
}
