package com.oplus.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.content.FileProvider$;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.runtime.OpaqueKey$;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.coui.appcompat.list.COUIListView;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PhoneAntiTheftTile extends QSTileImpl {
    private static final String PHONE_ANTI_THEFT_ALARM_KEY = "phone_anti_theft_alarm_key";
    private static final String PHONE_ANTI_THEFT_CHARGING_KEY = "phone_anti_theft_charging_key";
    private static final String PHONE_ANTI_THEFT_EARPHONE_UNPLUG_KEY = "phone_anti_theft_earphone_unplug_key";
    private static final String PHONE_ANTI_THEFT_SIM_KEY = "phone_anti_theft_sim_key";
    private static final String SETTINGS_PACKAGE_NAME = "com.android.settings";
    private static final String SETTINGS_PHONE_ANTI_THEFT_ACTION = "oplus.intent.action.settings.PHONE_ANTI_THEFT_SETTINGS_ACTION";
    private static final String SETTINGS_PHONE_ANTI_THEFT_NOTIFICATION_CHANGE = "oplus.intent.action.settings.PHONE_ANTI_THEFT_SETTINGS_NOTIFICATION_CHANGE";
    private static final String SETTINGS_PHONE_ANTI_THEFT_RLOCK_ACTION = "oplus.intent.action.settings.PHONE_ANTI_THEFT_SETTINGS_RLOCK_ACTION";
    private static final String SETTINGS_PHONE_ANTI_THEFT_VERIRY_ACTION = "oplus.intent.action.settings.PHONE_ANTI_THEFT_FRAGMENT";
    public static final String TILE_SPEC = "phoneantitheft";
    public int alarmValue;
    private final BroadcastSender broadcastSender;
    public int chargingValue;
    private final PhoneAntiTheftDetailAdapter detailAdapter;
    private final OplusQSDetailInteractor detailInteractor;
    public boolean isLockVerify;
    public boolean isObserverRegistered;
    private ItemAdapter mAdapter;
    private ContentObserver mAlarmObserver;
    private ContentObserver mCharingObserver;
    private KeyguardStateController mKeyguard;
    private ContentObserver mSimObserver;
    private ContentObserver mUnplugObserver;
    private View mVisible;
    private PanelInteractor panelInteractor;
    public int simValue;
    public int unplugValue;
    public static final Companion Companion = new Companion();
    private static final int[] itemRes = {2131955674, 2131955677, 2131955681};
    private static final int[] itemSummaryRes = {2131955675, 2131955676, 2131955682};

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PhoneAntiTheftDetailAdapter implements DetailAdapter {
        private final Context mainContext;

        public PhoneAntiTheftDetailAdapter(Context context) {
            this.mainContext = context;
        }

        public View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            View viewInflate = LayoutInflater.from(context).inflate(2131559351, viewGroup, z);
            COUIListView cOUIListViewFindViewById = viewInflate != null ? viewInflate.findViewById(2131364536) : null;
            PhoneAntiTheftTile phoneAntiTheftTile = PhoneAntiTheftTile.this;
            phoneAntiTheftTile.mAdapter = new ItemAdapter(phoneAntiTheftTile, context);
            ItemAdapter itemAdapter = phoneAntiTheftTile.mAdapter;
            if (itemAdapter != null) {
                createDetailView.1 r3 = new createDetailView.1();
                r3.this$0 = phoneAntiTheftTile;
                r3.$context = context;
                VarHandle.storeStoreFence();
                itemAdapter.setOnItemClickListener(r3);
            }
            if (cOUIListViewFindViewById != null) {
                cOUIListViewFindViewById.setAdapter(phoneAntiTheftTile.mAdapter);
            }
            phoneAntiTheftTile.mVisible = viewInflate != null ? viewInflate.findViewById(2131364537) : null;
            updatePanel();
            return viewInflate;
        }

        public DetailAdapter.DetailHeightType getDetailHeightType() {
            return DetailAdapter.DetailHeightType.IMMUTABLE_ONE;
        }

        public int getMetricsCategory() {
            return 111;
        }

        public Intent getSettingsIntent() {
            PhoneAntiTheftTile.this.getClass();
            return PhoneAntiTheftTile.intentToSettings();
        }

        public String getTag() {
            return PhoneAntiTheftTile.TILE_SPEC;
        }

        public CharSequence getTitle() {
            return this.mainContext.getString(2131955673);
        }

        public Boolean getToggleState() {
            PhoneAntiTheftTile phoneAntiTheftTile = PhoneAntiTheftTile.this;
            LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) phoneAntiTheftTile).TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("getToggleState =", ((QSTile.AdapterState) ((QSTileImpl) phoneAntiTheftTile).mState).value), null, 8);
            return Boolean.valueOf(((QSTile.AdapterState) ((QSTileImpl) phoneAntiTheftTile).mState).value);
        }

        public final boolean hasFooterDivider() {
            return DeviceUtil.isLandscape(((QSTileImpl) PhoneAntiTheftTile.this).mContext);
        }

        public void setToggleState(boolean z) {
            PhoneAntiTheftTile.this.onDetailToggle(Boolean.valueOf(z));
        }

        public final void updatePanel() {
            ItemAdapter itemAdapter = PhoneAntiTheftTile.this.mAdapter;
            if (itemAdapter != null) {
                itemAdapter.notifyDataSetChanged();
            }
        }
    }

    public PhoneAntiTheftTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, PanelInteractor panelInteractor, BroadcastSender broadcastSender, OplusQSDetailInteractor.Factory factory, KeyguardStateController keyguardStateController) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.panelInteractor = panelInteractor;
        this.broadcastSender = broadcastSender;
        this.mKeyguard = keyguardStateController;
        getSaveValue();
        this.mAlarmObserver = new initObserver.1(this, new Handler(), 0);
        this.mCharingObserver = new initObserver.1(this, new Handler(), 1);
        this.mUnplugObserver = new initObserver.1(this, new Handler(), 2);
        this.mSimObserver = new initObserver.1(this, new Handler(), 3);
        if (!this.isObserverRegistered) {
            this.isObserverRegistered = true;
            ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor(PHONE_ANTI_THEFT_ALARM_KEY), this.mAlarmObserver);
            ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor(PHONE_ANTI_THEFT_CHARGING_KEY), this.mCharingObserver);
            ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor(PHONE_ANTI_THEFT_EARPHONE_UNPLUG_KEY), this.mUnplugObserver);
            ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor(PHONE_ANTI_THEFT_SIM_KEY), this.mSimObserver);
        }
        PhoneAntiTheftDetailAdapter phoneAntiTheftDetailAdapter = new PhoneAntiTheftDetailAdapter(((QSTileImpl) this).mContext);
        this.detailAdapter = phoneAntiTheftDetailAdapter;
        this.detailInteractor = factory.create(phoneAntiTheftDetailAdapter);
    }

    public static final void access$itemClick(PhoneAntiTheftTile phoneAntiTheftTile, int i, int i2) {
        if (!phoneAntiTheftTile.mKeyguard.mSecure) {
            ((QSTileImpl) phoneAntiTheftTile).mActivityStarter.startActivity(intentToSettings(), true);
            return;
        }
        int i3 = itemRes[i];
        if (i3 == 2131955674) {
            if (phoneAntiTheftTile.isLockVerify) {
                AbsSettingsValueProxy.Companion.setGlobalIntValue(((QSTileImpl) phoneAntiTheftTile).mContext, PHONE_ANTI_THEFT_CHARGING_KEY, i2);
                phoneAntiTheftTile.sendNotificatonBroadcast$1();
                return;
            } else if (i2 != 1) {
                phoneAntiTheftTile.intentToSettings(i2, PHONE_ANTI_THEFT_CHARGING_KEY);
                return;
            } else {
                AbsSettingsValueProxy.Companion.setGlobalIntValue(((QSTileImpl) phoneAntiTheftTile).mContext, PHONE_ANTI_THEFT_CHARGING_KEY, i2);
                phoneAntiTheftTile.sendNotificatonBroadcast$1();
                return;
            }
        }
        if (i3 == 2131955677) {
            if (phoneAntiTheftTile.isLockVerify) {
                AbsSettingsValueProxy.Companion.setGlobalIntValue(((QSTileImpl) phoneAntiTheftTile).mContext, PHONE_ANTI_THEFT_EARPHONE_UNPLUG_KEY, i2);
                phoneAntiTheftTile.sendNotificatonBroadcast$1();
                return;
            } else if (i2 != 1) {
                phoneAntiTheftTile.intentToSettings(i2, PHONE_ANTI_THEFT_EARPHONE_UNPLUG_KEY);
                return;
            } else {
                AbsSettingsValueProxy.Companion.setGlobalIntValue(((QSTileImpl) phoneAntiTheftTile).mContext, PHONE_ANTI_THEFT_EARPHONE_UNPLUG_KEY, i2);
                phoneAntiTheftTile.sendNotificatonBroadcast$1();
                return;
            }
        }
        if (i3 == 2131955681) {
            if (phoneAntiTheftTile.isLockVerify) {
                AbsSettingsValueProxy.Companion.setGlobalIntValue(((QSTileImpl) phoneAntiTheftTile).mContext, PHONE_ANTI_THEFT_SIM_KEY, i2);
                phoneAntiTheftTile.sendNotificatonBroadcast$1();
            } else if (i2 != 1) {
                phoneAntiTheftTile.intentToSettings(i2, PHONE_ANTI_THEFT_SIM_KEY);
            } else {
                AbsSettingsValueProxy.Companion.setGlobalIntValue(((QSTileImpl) phoneAntiTheftTile).mContext, PHONE_ANTI_THEFT_SIM_KEY, i2);
                phoneAntiTheftTile.sendNotificatonBroadcast$1();
            }
        }
    }

    private final void getSaveValue() {
        AbsSettingsValueProxy.Companion companion = AbsSettingsValueProxy.Companion;
        this.alarmValue = companion.getGlobalIntValue(((QSTileImpl) this).mContext, PHONE_ANTI_THEFT_ALARM_KEY, 0);
        this.chargingValue = companion.getGlobalIntValue(((QSTileImpl) this).mContext, PHONE_ANTI_THEFT_CHARGING_KEY, 0);
        this.unplugValue = companion.getGlobalIntValue(((QSTileImpl) this).mContext, PHONE_ANTI_THEFT_EARPHONE_UNPLUG_KEY, 0);
        this.simValue = companion.getGlobalIntValue(((QSTileImpl) this).mContext, PHONE_ANTI_THEFT_SIM_KEY, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSecondaryLabelText() {
        if (this.alarmValue == 0) {
            return "";
        }
        String strM = this.chargingValue == 1 ? FileProvider$.ExternalSyntheticOutline0.m("", ((QSTileImpl) this).mContext.getString(itemRes[0])) : "";
        if (this.unplugValue == 1) {
            strM = Bundler$.ExternalSyntheticOutline0.m(strM, ((QSTileImpl) this).mContext.getString(itemRes[1]));
        }
        return this.simValue == 1 ? Bundler$.ExternalSyntheticOutline0.m(strM, ((QSTileImpl) this).mContext.getString(itemRes[2])) : strM;
    }

    private final void setClickAction(int i) {
        boolean z = this.mKeyguard.mSecure;
        Handler handler = ((QSTileImpl) this).mUiHandler;
        if (!z) {
            setClickAction.2 r4 = new setClickAction.2(2);
            r4.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(r4);
            return;
        }
        if (i == 0) {
            handleClick.1 r2 = new handleClick.1(1);
            r2.this$0 = this;
            r2.$state = i;
            VarHandle.storeStoreFence();
            handler.post(r2);
            return;
        }
        if (this.chargingValue == 1 || this.unplugValue == 1 || this.simValue == 1) {
            AbsSettingsValueProxy.Companion.setGlobalIntValue(((QSTileImpl) this).mContext, PHONE_ANTI_THEFT_ALARM_KEY, i);
            sendNotificatonBroadcast$1();
        } else {
            setClickAction.2 r5 = new setClickAction.2(0);
            r5.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(r5);
        }
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237082);
    }

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131955673);
    }

    public void handleClick(Expandable expandable) {
        int i = !((QSTile.AdapterState) ((QSTileImpl) this).mState).value ? 1 : 0;
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguard;
        if (!keyguardStateControllerImpl.mSecure || !keyguardStateControllerImpl.mShowing) {
            setClickAction(i);
            return;
        }
        if (i == 1) {
            AbsSettingsValueProxy.Companion.setGlobalIntValue(((QSTileImpl) this).mContext, PHONE_ANTI_THEFT_ALARM_KEY, i);
            sendNotificatonBroadcast$1();
            return;
        }
        handleClick.1 r0 = new handleClick.1(0);
        r0.this$0 = this;
        r0.$state = i;
        VarHandle.storeStoreFence();
        ((QSTileImpl) this).mActivityStarter.postQSRunnableDismissingKeyguard(r0);
    }

    public final void handleDestroy() {
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleDestroy", null, 8);
        if (this.isObserverRegistered) {
            ObserverDispatcher.getInstance().unregisterObserver(this.mAlarmObserver);
            ObserverDispatcher.getInstance().unregisterObserver(this.mCharingObserver);
            ObserverDispatcher.getInstance().unregisterObserver(this.mUnplugObserver);
            ObserverDispatcher.getInstance().unregisterObserver(this.mSimObserver);
            this.isObserverRegistered = false;
        }
        super.handleDestroy();
    }

    public void handleLongClick(Expandable expandable) {
        Runnable runnable = new setClickAction.2(1);
        ((setClickAction.2) runnable).this$0 = this;
        VarHandle.storeStoreFence();
        ((QSTileImpl) this).mUiHandler.post(runnable);
    }

    public void handleSecondaryClick(Expandable expandable) {
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleSecondaryClick()", null, 8);
        this.isLockVerify = false;
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguard;
        if (!keyguardStateControllerImpl.mSecure || !keyguardStateControllerImpl.mShowing) {
            this.detailInteractor.show(expandable != null ? expandable.getView() : null);
            return;
        }
        handleSecondaryClick.1 r0 = new handleSecondaryClick.1();
        r0.this$0 = this;
        r0.$expandable = expandable;
        VarHandle.storeStoreFence();
        ((QSTileImpl) this).mActivityStarter.postQSRunnableDismissingKeyguard(r0);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean z = this.alarmValue == 1;
        boolean z2 = ((QSTile.AdapterState) booleanState).value != z;
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131955673);
        ((QSTile.AdapterState) booleanState).value = z;
        ((QSTile.State) booleanState).dualTarget = true;
        ((QSTile.State) booleanState).secondaryLabel = getSecondaryLabelText();
        ((QSTile.State) booleanState).state = z ? 2 : 1;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(((QSTile.AdapterState) booleanState).value ? 2131237081 : 2131237082);
        if (z2) {
            this.detailInteractor.updateSwitchState(((QSTile.AdapterState) booleanState).value);
        }
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("handleUpdateState:", this.alarmValue, " stateEnable:", z), null, 8);
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
    public boolean handleUserOperation(Object obj) {
        boolean zAreEqual = Intrinsics.areEqual(obj, Boolean.TRUE);
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, OpaqueKey$.ExternalSyntheticOutline0.m(obj, " handleUserOperation: operation ="), null, 8);
        if (this.isLockVerify) {
            AbsSettingsValueProxy.Companion.setGlobalIntValue(((QSTileImpl) this).mContext, PHONE_ANTI_THEFT_ALARM_KEY, zAreEqual ? 1 : 0);
            sendNotificatonBroadcast$1();
            return true;
        }
        if (!this.mKeyguard.mSecure) {
            m342intentToSettings();
            return false;
        }
        if (!zAreEqual) {
            intentToSettings(zAreEqual ? 1 : 0, PHONE_ANTI_THEFT_ALARM_KEY);
            return false;
        }
        AbsSettingsValueProxy.Companion.setGlobalIntValue(((QSTileImpl) this).mContext, PHONE_ANTI_THEFT_ALARM_KEY, zAreEqual ? 1 : 0);
        sendNotificatonBroadcast$1();
        return true;
    }

    public final void intentToSettings(int i, String str) {
        FlavorOneFeatureOption.INSTANCE.getClass();
        boolean zIsFlavorOneDevice = FlavorOneFeatureOption.isFlavorOneDevice();
        String str2 = SETTINGS_PHONE_ANTI_THEFT_VERIRY_ACTION;
        if (zIsFlavorOneDevice && FlavorOneFeatureOption.sIsAntiTheftRlockSupport && SETTINGS_PHONE_ANTI_THEFT_VERIRY_ACTION.equals(SETTINGS_PHONE_ANTI_THEFT_VERIRY_ACTION)) {
            str2 = SETTINGS_PHONE_ANTI_THEFT_RLOCK_ACTION;
        }
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "intentToSettingsEx = ".concat(str2), null, 8);
        Intent intent = new Intent();
        intent.putExtra(str, i);
        intent.setAction(str2);
        intent.setFlags(268468224);
        ((QSTileImpl) this).mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
    }

    public final boolean isAvailable() {
        return false;
    }

    public final boolean isItemChecked(int i) {
        int i2 = itemRes[i];
        if (i2 == 2131955674) {
            return this.chargingValue == 1;
        }
        if (i2 == 2131955677) {
            return this.unplugValue == 1;
        }
        return i2 == 2131955681 && this.simValue == 1;
    }

    public final boolean isShowingDetail() {
        return this.detailInteractor.isShowing();
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m343newTileState() {
        return new QSTile.BooleanState();
    }

    public final void sendNotificatonBroadcast$1() {
        Intent intent = new Intent();
        intent.setAction(SETTINGS_PHONE_ANTI_THEFT_NOTIFICATION_CHANGE);
        intent.setPackage(SETTINGS_PACKAGE_NAME);
        this.broadcastSender.sendBroadcast(intent);
    }

    /* JADX INFO: renamed from: intentToSettings, reason: collision with other method in class */
    public final void m342intentToSettings() {
        FlavorOneFeatureOption.INSTANCE.getClass();
        boolean zIsFlavorOneDevice = FlavorOneFeatureOption.isFlavorOneDevice();
        String str = SETTINGS_PHONE_ANTI_THEFT_VERIRY_ACTION;
        if (zIsFlavorOneDevice && FlavorOneFeatureOption.sIsAntiTheftRlockSupport && SETTINGS_PHONE_ANTI_THEFT_VERIRY_ACTION.equals(SETTINGS_PHONE_ANTI_THEFT_VERIRY_ACTION)) {
            str = SETTINGS_PHONE_ANTI_THEFT_RLOCK_ACTION;
        }
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "intentToSettings = ".concat(str), null, 8);
        Intent intent = new Intent();
        intent.setAction(str);
        intent.setFlags(268468224);
        ((QSTileImpl) this).mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
    }

    public static Intent intentToSettings() {
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isFlavorOneDevice() && FlavorOneFeatureOption.sIsAntiTheftRlockSupport) {
            Intent intent = new Intent();
            intent.setAction(SETTINGS_PHONE_ANTI_THEFT_RLOCK_ACTION);
            intent.setFlags(268468224);
            return intent;
        }
        Intent intent2 = new Intent();
        intent2.setAction(SETTINGS_PHONE_ANTI_THEFT_ACTION);
        intent2.setFlags(268468224);
        return intent2;
    }
}
