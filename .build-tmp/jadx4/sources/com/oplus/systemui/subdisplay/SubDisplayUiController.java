package com.oplus.systemui.subdisplay;

import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Message;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.collection.FloatList$;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusLegacyLockIconViewController;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.ViewMediatorCallback;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.metadata.UtilsKt$;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.security.verify.OplusKeyguardVerifyManagerExImpl;
import com.oplus.keyguard.utils.KeyguardRapidUnlockUtils;
import com.oplus.systemui.ISubDisplayCallBack;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.keyguard.charginganim.ChargingAnimationImpl;
import com.oplus.systemui.keyguard.charginganim.ChargingAnimationImpl$;
import com.oplus.systemui.keyguard.telephony.SimStateHelper;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.subdisplay.keyguard.SubDisplayKeyguardWindowViewController;
import com.oplus.systemui.subdisplay.security.OplusSubDisplayBouncerController;
import com.oplus.systemui.subdisplay.security.SubBiometricHelper;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SubDisplayUiController implements Handler.Callback, Dumpable {
    private static final String ACTION_SCREEN_RELAY = "com.oplus.action.LargeAndSmallScreenRelayActivity.action";
    private static final String COUNT_DOWN_STATE = "count_down_state";
    private static final String COUNT_DOWN_TIME = "count_down_time";
    public static final Companion Companion = new Companion();
    private static final String FORCE_UPDATE = "force_update";
    private static final String LOCK_ICON_STATE = "lock_icon_state";
    private static final String SECONDARY_HOME_PKG = "com.oplus.secondaryhome";
    private static final String SECURE_STATE = "secure_state";
    private static final String SIM_SECURE_STATE = "sim_secure_state";
    private static final String SIM_STATE = "sim_state";
    private static final String STRONG_AUTH_STATE = "strong_auth_state";
    private static final String SUB_DISPLAY_KEYGUARD_SERVICE = "oplus.intent.action.SUB_SYSTEMUI_CONTROLL";
    private static final String TAG = "SubDisplayUiControl";
    private final Lazy aodSubFullscreenControllerLazy;
    private final ServiceConnection cardManagerConnection;
    public boolean cardServiceBind;
    private final Context context;
    private final Lazy dialogContentUpdateToSecondaryHomeControllerLazy;
    private String dismissReason;
    private final Lazy dumpManagerLazy;
    private final DeviceStateController.StateChangedListener foldStateListener;
    private final Handler handler;
    public boolean isSubKeyguardWindowShow;
    private final KeyguardStateController.Callback keyguardStateControllerCallback;
    private final Lazy keyguardStateControllerLazy;
    private final Lazy keyguardUpdateMonitorLazy;
    private final Lazy keyguardViewController;
    private KeyguardViewMediator keyguardViewMediator;
    public int lastFoldMode;
    public int lastTime;
    private List<String> logStringList;
    private final Lazy oplusBiometricAuthController;
    private final Lazy oplusKeyguardVerifyManagerLazy;
    private final Lazy oplusLockIconViewControllerLazy;
    private final LinkedList<Message> pendingList = new LinkedList<>();
    private final ScreenLifecycle screenLifecycle;
    private final ServiceConnection secondaryKeyguardUiConnection;
    private final SecondaryKeyguardProviderClient secondaryProviderClient;
    public boolean secure;
    public boolean showing;
    public boolean simSecure;
    public int simState;
    public int state;
    public int strongAuthState;
    private OplusSubDisplayBouncerController subDisplayBouncerController;
    private final ISubDisplayCallBack subDisplayCallBack;
    private SubDisplayKeyguardWindowViewController subDisplayKeyguardWindowViewController;
    public boolean supportScreenRelay;
    public boolean trustEnable;
    private SubDisplayUiControlWrapper uiControl;
    private final KeyguardUpdateMonitorCallback updateMonitorCallback;
    private Context userContext;
    private ViewMediatorCallback viewMediatorCallback;
    private final WakefulnessLifecycle wakefulnessLifecycle;
    private final WakefulnessLifecycle.Observer wakefulnessObserver;

    public SubDisplayUiController(Context context, Lazy lazy, Lazy lazy2, WakefulnessLifecycle wakefulnessLifecycle, ScreenLifecycle screenLifecycle, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, SecondaryKeyguardProviderClient secondaryKeyguardProviderClient, Lazy lazy9) {
        this.context = context;
        this.keyguardUpdateMonitorLazy = lazy;
        this.keyguardStateControllerLazy = lazy2;
        this.wakefulnessLifecycle = wakefulnessLifecycle;
        this.screenLifecycle = screenLifecycle;
        this.oplusKeyguardVerifyManagerLazy = lazy3;
        this.dumpManagerLazy = lazy4;
        this.aodSubFullscreenControllerLazy = lazy5;
        this.oplusLockIconViewControllerLazy = lazy6;
        this.keyguardViewController = lazy7;
        this.oplusBiometricAuthController = lazy8;
        this.secondaryProviderClient = secondaryKeyguardProviderClient;
        this.dialogContentUpdateToSecondaryHomeControllerLazy = lazy9;
        updateMonitorCallback.1 r1 = new updateMonitorCallback.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        this.updateMonitorCallback = r1;
        this.subDisplayCallBack = new subDisplayCallBack.1(this);
        cardManagerConnection.1 r2 = new cardManagerConnection.1(1);
        r2.this$0 = this;
        VarHandle.storeStoreFence();
        this.secondaryKeyguardUiConnection = r2;
        cardManagerConnection.1 r3 = new cardManagerConnection.1(0);
        r3.this$0 = this;
        VarHandle.storeStoreFence();
        this.cardManagerConnection = r3;
        foldStateListener.1 r4 = new foldStateListener.1();
        r4.this$0 = this;
        VarHandle.storeStoreFence();
        this.foldStateListener = r4;
        keyguardStateControllerCallback.1 r5 = new keyguardStateControllerCallback.1();
        r5.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardStateControllerCallback = r5;
        wakefulnessObserver.1 r6 = new wakefulnessObserver.1();
        r6.this$0 = this;
        VarHandle.storeStoreFence();
        this.wakefulnessObserver = r6;
        this.lastFoldMode = -1;
        this.state = 1;
        this.dismissReason = "";
        this.logStringList = new ArrayList();
        this.handler = new Handler(OplusThreadFactoryImpl.INSTANCE.buildNewThread("SubDisplayKeyguard").getLooper(), this);
    }

    public static final void access$registerListener(SubDisplayUiController subDisplayUiController) {
        subDisplayUiController.getClass();
        DeviceStateController deviceStateController = DeviceStateController.getInstance();
        deviceStateController.addCallback(subDisplayUiController.foldStateListener);
        subDisplayUiController.lastFoldMode = deviceStateController.getState();
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) subDisplayUiController.keyguardStateControllerLazy.get();
        KeyguardStateController.Callback callback = subDisplayUiController.keyguardStateControllerCallback;
        KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
        keyguardStateControllerImpl2.getClass();
        keyguardStateControllerImpl2.addCallback(callback);
        ((KeyguardUpdateMonitor) subDisplayUiController.keyguardUpdateMonitorLazy.get()).registerCallback(subDisplayUiController.updateMonitorCallback);
        OplusPuttUtils.registerPuttObserver();
        subDisplayUiController.wakefulnessLifecycle.addObserver(subDisplayUiController.wakefulnessObserver);
        boolean z = subDisplayUiController.screenLifecycle.getScreenState() == 2;
        synchronized (OplusPuttUtils.class) {
            OplusPuttUtils.screenOn = z;
        }
        ((DumpManager) subDisplayUiController.dumpManagerLazy.get()).registerDumpable(subDisplayUiController);
    }

    public static final void access$unBindCardManagerService(SubDisplayUiController subDisplayUiController) {
        try {
            synchronized (subDisplayUiController) {
                try {
                    if (subDisplayUiController.cardServiceBind) {
                        Context context = subDisplayUiController.userContext;
                        if (context != null) {
                            context.unbindService(subDisplayUiController.cardManagerConnection);
                        }
                        subDisplayUiController.cardServiceBind = false;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (IllegalArgumentException unused) {
            KgdLogUtil.e(TAG, "unBindService IllegalArgumentException");
        } catch (Exception unused2) {
            KgdLogUtil.e(TAG, "bindService exception");
        }
    }

    public static final void access$unBindSubKeyguardService(SubDisplayUiController subDisplayUiController) {
        try {
            try {
                synchronized (subDisplayUiController) {
                    try {
                        if (subDisplayUiController.uiControl != null) {
                            Context context = subDisplayUiController.userContext;
                            if (context != null) {
                                context.unbindService(subDisplayUiController.secondaryKeyguardUiConnection);
                            }
                            subDisplayUiController.uiControl = null;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (subDisplayUiController.uiControl != null) {
                    subDisplayUiController.uiControl = null;
                }
            } catch (IllegalArgumentException unused) {
                KgdLogUtil.e(TAG, "unBindService IllegalArgumentException");
                if (subDisplayUiController.uiControl == null) {
                    return;
                }
                subDisplayUiController.uiControl = null;
            } catch (Exception unused2) {
                KgdLogUtil.e(TAG, "unBindService exception");
                if (subDisplayUiController.uiControl == null) {
                    return;
                }
                subDisplayUiController.uiControl = null;
            }
        } catch (Throwable th2) {
            if (subDisplayUiController.uiControl != null) {
                subDisplayUiController.uiControl = null;
            }
            throw th2;
        }
    }

    public static final void access$updateFaceDetectState(SubDisplayUiController subDisplayUiController, boolean z) {
        if (z) {
            KeyguardViewMediator keyguardViewMediator = subDisplayUiController.keyguardViewMediator;
            if (keyguardViewMediator != null) {
                keyguardViewMediator.requestKeyguard(FakeSubDisplayUiController.REQUEST_COMMAND_START_FACE_UNLOCK_FOR_SUB_DISPLAY);
                return;
            }
            return;
        }
        KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) subDisplayUiController.keyguardUpdateMonitorLazy.get();
        if (DeviceStateController.getInstance().getState() == 3 && keyguardUpdateMonitor.mDeviceInteractive && keyguardUpdateMonitor.isKeyguardVisible$1()) {
            KgdLogUtil.w(TAG, "updateFaceDetectState, ignore");
            return;
        }
        KeyguardViewMediator keyguardViewMediator2 = subDisplayUiController.keyguardViewMediator;
        if (keyguardViewMediator2 != null) {
            keyguardViewMediator2.requestKeyguard(FakeSubDisplayUiController.REQUEST_COMMAND_STOP_FACE_UNLOCK_FOR_SUB_DISPLAY);
        }
    }

    public static final void access$updateHomeCardState(SubDisplayUiController subDisplayUiController, boolean z) {
        ChargingAnimationImpl chargingAnimationImpl;
        Context context = subDisplayUiController.context;
        if (context == null || (chargingAnimationImpl = ChargingAnimationImpl.getInstance(context)) == null) {
            return;
        }
        OplusChargeAnimController$.ExternalSyntheticOutline0.m("ChargingAnimationImpl", new StringBuilder("updateHomeCardVisibility, mIsHomeCardVisible: "), chargingAnimationImpl.mIsHomeCardVisible);
        ChargingAnimationImpl.4 r0 = chargingAnimationImpl.mHandler;
        if (r0 != null) {
            ChargingAnimationImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ChargingAnimationImpl$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = chargingAnimationImpl;
            externalSyntheticLambda0.f$1 = z;
            VarHandle.storeStoreFence();
            r0.post(externalSyntheticLambda0);
        }
    }

    public static void showOrHideBouncer$default(SubDisplayUiController subDisplayUiController, boolean z) {
        subDisplayUiController.getClass();
        showOrHideBouncer.1 r0 = new showOrHideBouncer.1();
        r0.this$0 = subDisplayUiController;
        r0.$show = z;
        r0.$isFromFold = false;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(r0);
    }

    public final void appendLogStr(int i, String str) {
        if (this.logStringList.size() > 10) {
            this.logStringList.remove(0);
        }
        String str2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis()));
        StringBuffer stringBuffer = new StringBuffer("userId: ");
        stringBuffer.append(i);
        stringBuffer.append(" reason: ");
        stringBuffer.append(str);
        stringBuffer.append(" time: ");
        stringBuffer.append(str2);
        stringBuffer.append("\n");
        this.logStringList.add(stringBuffer.toString());
        KgdLogUtil.i(TAG, "current log str: " + this.logStringList);
    }

    public final void bindCardManagerService() {
        try {
            synchronized (this) {
                if (this.cardServiceBind) {
                    return;
                }
                ComponentName componentName = new ComponentName("com.oplus.secondaryhome", "com.oplus.secondaryhome.cardcontainer.cardmanager.CardContainerManagerService");
                Intent intent = new Intent();
                intent.setComponent(componentName);
                Context context = this.userContext;
                if (context != null) {
                    context.bindService(intent, 1, (Executor) new HandlerExecutor(this.handler), this.cardManagerConnection);
                }
            }
        } catch (IllegalStateException unused) {
            KgdLogUtil.e(TAG, "bindService IllegalStateException");
        } catch (Exception unused2) {
            KgdLogUtil.e(TAG, "bindService exception");
        }
    }

    public final void bindSubKeyguardService(int i, String str) {
        try {
            synchronized (this) {
                SubDisplayUiControlWrapper subDisplayUiControlWrapper = this.uiControl;
                if (subDisplayUiControlWrapper != null) {
                    KgdLogUtil.i(TAG, "bindSubKeyguardService binder already exist: " + subDisplayUiControlWrapper);
                    return;
                }
                Intent intent = new Intent();
                intent.setPackage("com.oplus.secondaryhome");
                intent.setAction(SUB_DISPLAY_KEYGUARD_SERVICE);
                Context contextCreateContextAsUser = this.context.createContextAsUser(new UserHandle(i), 0);
                this.userContext = contextCreateContextAsUser;
                KgdLogUtil.i(TAG, "bindSubKeyguardService mUserContext: " + contextCreateContextAsUser);
                Context context = this.userContext;
                if (context != null) {
                    context.bindService(intent, 1, (Executor) new HandlerExecutor(this.handler), this.secondaryKeyguardUiConnection);
                }
                appendLogStr(i, str);
            }
        } catch (IllegalStateException unused) {
            KgdLogUtil.e(TAG, "bindService IllegalStateException");
        } catch (Exception unused2) {
            KgdLogUtil.e(TAG, "bindService exception");
        }
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("Dump sub display ui controller bind info : ");
        printWriter.println(this.logStringList);
    }

    public final int getState() {
        return this.state;
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
    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                KgdLogUtil.i(TAG, "MSG_KEYGUARD_START");
                initValue(KeyguardUpdateMonitor.getCurrentUser());
                bindSubKeyguardService(KeyguardUpdateMonitor.getCurrentUser(), "MSG_KEYGUARD_START");
                bindCardManagerService();
                return true;
            case 2:
                Object obj = message.obj;
                if (obj instanceof Bundle) {
                    Bundle bundle = (Bundle) obj;
                    int i = bundle.getInt(LOCK_ICON_STATE);
                    boolean z = bundle.getBoolean(FORCE_UPDATE);
                    if (this.state != i || z) {
                        OplusLockIconView$.ExternalSyntheticOutline0.m(i, "MSG_LOCK_ICON_STATE_UPDATE:", TAG);
                        this.state = i;
                        SubDisplayUiControlWrapper subDisplayUiControlWrapper = this.uiControl;
                        if (subDisplayUiControlWrapper != null) {
                            subDisplayUiControlWrapper.setLockIconState(i);
                        }
                        SubBiometricHelper.getInstance(this.context).setLockIconState(i);
                        if (i == 1) {
                            this.lastTime = 0;
                            showOrHideBouncer$default(this, false);
                        }
                    }
                }
                return true;
            case 3:
                Object obj2 = message.obj;
                if (obj2 instanceof Bundle) {
                    Bundle bundle2 = (Bundle) obj2;
                    int i2 = bundle2.getInt(STRONG_AUTH_STATE);
                    boolean z2 = bundle2.getBoolean(FORCE_UPDATE);
                    if (i2 != this.strongAuthState || z2) {
                        OplusLockIconView$.ExternalSyntheticOutline0.m(i2, "MSG_STRONG_AUTH_STATE_UPDATE:", TAG);
                        this.strongAuthState = i2;
                        SubDisplayUiControlWrapper subDisplayUiControlWrapper2 = this.uiControl;
                        if (subDisplayUiControlWrapper2 != null) {
                            subDisplayUiControlWrapper2.setStrongAuthState(i2);
                        }
                        SubBiometricHelper.getInstance(this.context).setStrongAuthState(i2);
                        return true;
                    }
                }
                return true;
            case 4:
                Object obj3 = message.obj;
                if (this.state != 1 && (obj3 instanceof Bundle)) {
                    SubDisplayUiControlWrapper subDisplayUiControlWrapper3 = this.uiControl;
                    if (subDisplayUiControlWrapper3 != null) {
                        Bundle bundle3 = (Bundle) obj3;
                        subDisplayUiControlWrapper3.setCountDownInfo(bundle3.getInt(COUNT_DOWN_STATE), bundle3.getInt(COUNT_DOWN_TIME));
                    }
                    Bundle bundle4 = (Bundle) obj3;
                    SubBiometricHelper.getInstance(this.context).mHandler.obtainMessage(3, bundle4.getInt(COUNT_DOWN_STATE), bundle4.getInt(COUNT_DOWN_TIME)).sendToTarget();
                    if (this.lastTime == 0) {
                        showOrHideBouncer$default(this, true);
                    }
                    this.lastTime = bundle4.getInt(COUNT_DOWN_TIME);
                    if (bundle4.getInt(COUNT_DOWN_STATE) == 12) {
                        this.lastTime = 0;
                        return true;
                    }
                }
                return true;
            case 5:
                Object obj4 = message.obj;
                if (obj4 instanceof Bundle) {
                    SubDisplayUiControlWrapper subDisplayUiControlWrapper4 = this.uiControl;
                    if (subDisplayUiControlWrapper4 != null) {
                        subDisplayUiControlWrapper4.setSecureState(((Bundle) obj4).getBoolean(SECURE_STATE));
                    }
                    SubBiometricHelper.getInstance(this.context).mHandler.obtainMessage(4, Boolean.valueOf(((Bundle) obj4).getBoolean(SECURE_STATE))).sendToTarget();
                    return true;
                }
                return true;
            case 6:
                SubDisplayUiControlWrapper subDisplayUiControlWrapper5 = this.uiControl;
                if (subDisplayUiControlWrapper5 != null) {
                    subDisplayUiControlWrapper5.collapsePanels();
                    return true;
                }
                return true;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
            default:
                return false;
            case 8:
                boolean zIsCurrentFullScreen = AodData.getInstance(this.context).mAodOptionsMgr.isCurrentFullScreen();
                SubDisplayUiControlWrapper subDisplayUiControlWrapper6 = this.uiControl;
                if (subDisplayUiControlWrapper6 != null) {
                    subDisplayUiControlWrapper6.onDreamingStart(zIsCurrentFullScreen ? 1 : 0);
                    return true;
                }
                return true;
            case 9:
                if (KeyguardRapidUnlockUtils.Companion.getKeyguardComposed()) {
                    boolean zIsCurrentFullScreen2 = AodData.getInstance(this.context).mAodOptionsMgr.isCurrentFullScreen();
                    SubDisplayUiControlWrapper subDisplayUiControlWrapper7 = this.uiControl;
                    if (subDisplayUiControlWrapper7 != null) {
                        subDisplayUiControlWrapper7.onDreamingStop(zIsCurrentFullScreen2 ? 1 : 0);
                        return true;
                    }
                }
                return true;
            case 10:
                SubDisplayUiControlWrapper subDisplayUiControlWrapper8 = this.uiControl;
                if (subDisplayUiControlWrapper8 != null) {
                    subDisplayUiControlWrapper8.onAodAnimationEnd();
                    return true;
                }
                return true;
            case 11:
                Object obj5 = message.obj;
                if (obj5 instanceof Bundle) {
                    Bundle bundle5 = (Bundle) obj5;
                    boolean z3 = bundle5.getBoolean(SIM_SECURE_STATE);
                    int i3 = bundle5.getInt(SIM_STATE);
                    KgdLogUtil.i(TAG, "MSG_SIM_SECURE_STATE_UPDATE:" + z3 + " " + i3);
                    SubDisplayUiControlWrapper subDisplayUiControlWrapper9 = this.uiControl;
                    if (subDisplayUiControlWrapper9 != null) {
                        subDisplayUiControlWrapper9.setSimSecureState(z3, i3);
                    }
                    SubBiometricHelper.getInstance(this.context).mHandler.obtainMessage(5, i3, 0, Boolean.valueOf(z3)).sendToTarget();
                    return true;
                }
                return true;
            case 12:
                SubDisplayUiControlWrapper subDisplayUiControlWrapper10 = this.uiControl;
                if (subDisplayUiControlWrapper10 != null) {
                    subDisplayUiControlWrapper10.requestKeyguardOnHomeCard();
                    return true;
                }
                return true;
            case 13:
                this.secondaryProviderClient.onKeyguardViewDismiss(this.context);
                return true;
            case 14:
                boolean z4 = this.secure;
                Bundle bundle6 = new Bundle();
                bundle6.putBoolean(SECURE_STATE, z4);
                sendMessage(5, bundle6, false);
                if (((OplusKeyguardVerifyManagerExImpl) this.oplusKeyguardVerifyManagerLazy.get()).needVerifyPwdWhenSysRebooted() || (this.strongAuthState & 1) == 0) {
                    int i4 = this.strongAuthState;
                    Bundle bundle7 = new Bundle();
                    bundle7.putInt(STRONG_AUTH_STATE, i4);
                    bundle7.putBoolean(FORCE_UPDATE, true);
                    sendMessage(3, bundle7, true);
                }
                updateLockState(this.state, 0, true);
                boolean z5 = this.simSecure;
                int i5 = this.simState;
                Bundle bundle8 = new Bundle();
                bundle8.putBoolean(SIM_SECURE_STATE, z5);
                bundle8.putInt(SIM_STATE, i5);
                sendMessage(11, bundle8, false);
                return true;
        }
    }

    public final void initValue(int i) {
        this.state = !((KeyguardManager) this.context.getSystemService(SeparateQSStatisicUtil.VALUE_KEYGUARD)).isDeviceLocked(i) ? 1 : 0;
        this.strongAuthState = ((KeyguardUpdateMonitor) this.keyguardUpdateMonitorLazy.get()).getStrongAuthTracker().getStrongAuthForUser(i);
        this.secure = new LockPatternUtils(this.context).isSecure(i);
        boolean z = ((KeyguardUpdateMonitor) this.keyguardUpdateMonitorLazy.get()).isSimPinSecure();
        this.simSecure = z;
        refreshSimState(z);
        this.trustEnable = ((KeyguardUpdateMonitor) this.keyguardUpdateMonitorLazy.get()).getUserHasTrust(i);
        boolean z2 = UtilsKt$.ExternalSyntheticOutline0.m(ACTION_SCREEN_RELAY, "com.oplus.secondaryhome").resolveActivity(this.context.getPackageManager()) != null;
        this.supportScreenRelay = z2;
        int i2 = this.state;
        int i3 = this.strongAuthState;
        boolean z3 = this.secure;
        boolean z4 = this.simSecure;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("initValue: mState = ", i2, i3, " mStrongAuthState = ", " mSecure = ");
        KeyguardLogger$.ExternalSyntheticOutline0.m("  mSimSecure = ", " userId =", sbM, z3, z4);
        sbM.append(i);
        sbM.append(" mSupportScreenRelay =");
        sbM.append(z2);
        KgdLogUtil.i(TAG, sbM.toString());
    }

    public final boolean interruptPlayUnlockSound() {
        if (!DeviceStateController.getInstance().isFolding() || TextUtils.isEmpty(this.dismissReason)) {
            return false;
        }
        VFXController$stopAnim$1$.ExternalSyntheticOutline0.m$1("interruptPlayUnlockSound mDismissReason:", this.dismissReason, TAG);
        return FakeSubDisplayUiController.REQUEST_COMMAND_UNLOCK_IN_SLIDE.equals(this.dismissReason);
    }

    public final void onUserSwitchComplete(int i) {
        Handler handler = this.handler;
        onUserSwitchComplete.1 r1 = new onUserSwitchComplete.1();
        r1.this$0 = this;
        r1.$userId = i;
        VarHandle.storeStoreFence();
        handler.post(r1);
    }

    public final void processLockWhenTrustEnable() {
        Object obj = this.oplusLockIconViewControllerLazy.get();
        OplusLegacyLockIconViewController oplusLegacyLockIconViewController = obj instanceof OplusLegacyLockIconViewController ? (OplusLegacyLockIconViewController) obj : null;
        if (oplusLegacyLockIconViewController != null) {
            oplusLegacyLockIconViewController.mOplusLockIconViewController.processLockWhenTrustEnable(0);
        }
    }

    public final void refreshSimState(boolean z) {
        int i = 0;
        if (!z) {
            this.simState = 0;
            return;
        }
        int iOrdinal = SimStateHelper.Companion.getInstance(this.context).getSecurityMode().ordinal();
        if (iOrdinal == 5) {
            i = 2;
        } else if (iOrdinal == 6) {
            i = 3;
        }
        this.simState = i;
    }

    public final void sendMessage(int i, Bundle bundle, boolean z) {
        if (this.uiControl != null || i == 1) {
            if (this.handler.hasMessages(i)) {
                this.handler.removeMessages(i);
            }
            Handler handler = this.handler;
            if (bundle == null) {
                handler.sendEmptyMessage(i);
                return;
            } else {
                this.handler.sendMessage(Message.obtain(handler, i, bundle));
                return;
            }
        }
        if (!this.handler.hasMessages(1)) {
            this.handler.sendEmptyMessageDelayed(1, 1000L);
        }
        Message messageObtain = Message.obtain();
        if (bundle == null) {
            messageObtain.what = i;
        } else {
            messageObtain.what = i;
            messageObtain.setTarget(this.handler);
            if (z) {
                bundle.putBoolean(FORCE_UPDATE, true);
            }
            messageObtain.obj = bundle;
        }
        synchronized (this.pendingList) {
            try {
                Iterator<Message> it = this.pendingList.iterator();
                while (it.hasNext()) {
                    if (it.next().what == i) {
                        it.remove();
                    }
                }
                this.pendingList.add(messageObtain);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void setDismissReason(String str) {
        this.dismissReason = str;
    }

    public final void setKeyguardViewMediator(KeyguardViewMediator keyguardViewMediator) {
        this.keyguardViewMediator = keyguardViewMediator;
    }

    public final void setSubDisplayBouncerChontroller(OplusSubDisplayBouncerController oplusSubDisplayBouncerController) {
        this.subDisplayBouncerController = oplusSubDisplayBouncerController;
    }

    public final void setSubKeyguardWindowShow(boolean z) {
        this.isSubKeyguardWindowShow = z;
    }

    public final void setViewMediatorCallback(ViewMediatorCallback viewMediatorCallback) {
        this.viewMediatorCallback = viewMediatorCallback;
    }

    public final void updateLockState(int i, int i2, boolean z) {
        if (i2 == 1) {
            if (i == 0) {
                i = 0;
            } else if (i == 1) {
                i = 1;
            } else if (i == 2) {
                i = 3;
            } else if (i == 3) {
                i = 4;
            } else if (i == 4) {
                i = 5;
            }
        }
        if (i == 0 && ((KeyguardUpdateMonitor) this.keyguardUpdateMonitorLazy.get()).getUserHasTrust(KeyguardUpdateMonitor.getCurrentUser())) {
            KgdLogUtil.i(TAG, "in trust enable, lock open");
            i = 1;
        }
        if (this.uiControl == null) {
            this.state = i;
            if (i2 == 1) {
                return;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt(LOCK_ICON_STATE, i);
        bundle.putBoolean(FORCE_UPDATE, z);
        sendMessage(2, bundle, true);
    }
}
