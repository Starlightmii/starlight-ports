package com.oplus.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.OplusPowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.car.app.serialization.Bundler$;
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
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.coui.appcompat.grid.COUIPercentWidthListView;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class BreathingLightTile extends QSTileImpl {
    private static final String BREATHING_LIGHT_ALWAYS_ON = "customize_breath_light_always_on";
    private static final String BREATHING_LIGHT_INCOMING_CALL = "customize_breath_light_incoming_call";
    private static final String BREATHING_LIGHT_MMS = "customize_breath_light_mms";
    private static final String BREATHING_LIGHT_MUSIC_REMINDER = "customize_breath_light_music_reminder";
    private static final String BREATHING_LIGHT_MUSIC_REMINDER_LAST_STATUS = "breathing_light_multi_led_music_status";
    private static final String BREATHING_LIGHT_SWITCH = "customize_breath_light_master_switch";
    private static final String BREATHING_OFF_ACTION = "oplus.intent.action.settings.BREATHING_LIGHT_SETTINGS_OFF";
    private static final String BREATHING_ON_ACTION = "oplus.intent.action.settings.BREATHING_LIGHT_SETTINGS_ON";
    private static final String SETTINGS_BREATHING_LIGHT_ACTION = "oplus.intent.action.settings.BREATHING_LIGHT_SETTINGS_MULTI_LED";
    private static final String SETTINGS_BREATHING_LIGHT_ACTION_NEW = "oplus.intent.action.settings.BREATHING_LIGHT_SINGLE_NEW";
    private static final String SETTINGS_BREATHING_LIGHT_ON_OFF_ACTION = "oplus.intent.action.settings.BREATHING_LIGHT_SETTINGS_MASTER_ON_OFF";
    private static final String SETTINGS_PACKAGE_NAME = "com.android.settings";
    public static final String TILE_SPEC = "breathinglight";
    private final Runnable breathFlashCloseRunable;
    private final BroadcastSender broadcastSender;
    private final BreathingLightDetailAdapter detailAdapter;
    private final OplusQSDetailInteractor detailInteractor;
    private ItemAdapter mAdapter;
    private View mEmpty;
    private ImageView mEmptyIcon;
    public boolean mListening;
    private final ContentObserver mObserver;
    private View mVisible;
    private final Lazy powerManager$delegate;
    public static final Companion Companion = new Companion();
    private static int[] itemRes = {2131955596, 2131955598, 2131955597, 2131954761};

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class BreathingLightDetailAdapter implements DetailAdapter {
        private final Context mainContext;

        public BreathingLightDetailAdapter(Context context) {
            this.mainContext = context;
        }

        public View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            View viewInflate = LayoutInflater.from(context).inflate(2131559327, viewGroup, z);
            COUIPercentWidthListView cOUIPercentWidthListViewFindViewById = viewInflate != null ? viewInflate.findViewById(2131362339) : null;
            BreathingLightTile breathingLightTile = BreathingLightTile.this;
            breathingLightTile.mAdapter = new ItemAdapter(breathingLightTile, context);
            ItemAdapter itemAdapter = breathingLightTile.mAdapter;
            if (itemAdapter != null) {
                createDetailView.1 r3 = new createDetailView.1();
                r3.this$0 = breathingLightTile;
                r3.$context = context;
                VarHandle.storeStoreFence();
                itemAdapter.setOnItemClickListener(r3);
            }
            if (cOUIPercentWidthListViewFindViewById != null) {
                cOUIPercentWidthListViewFindViewById.setAdapter(breathingLightTile.mAdapter);
            }
            breathingLightTile.mEmpty = viewInflate != null ? viewInflate.findViewById(2131362335) : null;
            breathingLightTile.mVisible = viewInflate != null ? viewInflate.findViewById(2131362341) : null;
            breathingLightTile.mEmptyIcon = viewInflate != null ? (ImageView) viewInflate.findViewById(2131362336) : null;
            ImageView imageView = breathingLightTile.mEmptyIcon;
            if (imageView != null) {
                imageView.setImageResource(OplusDarkModeUtil.isNightMode(((QSTileImpl) breathingLightTile).mContext) ? 2131236004 : 2131236005);
            }
            updatePanel();
            return viewInflate;
        }

        public int getMetricsCategory() {
            return 111;
        }

        public Intent getSettingsIntent() {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (FlavorOneFeatureOption.isShutdownBreathLightZondaSupport()) {
                Intent intent = new Intent();
                intent.setAction(BreathingLightTile.SETTINGS_BREATHING_LIGHT_ACTION);
                intent.setFlags(268468224);
                return intent;
            }
            Intent intent2 = new Intent();
            intent2.setAction(BreathingLightTile.SETTINGS_BREATHING_LIGHT_ACTION_NEW);
            intent2.setFlags(268468224);
            return intent2;
        }

        public String getTag() {
            return BreathingLightTile.TILE_SPEC;
        }

        public CharSequence getTitle() {
            return this.mainContext.getString(2131956549);
        }

        public Boolean getToggleState() {
            return Boolean.valueOf(((QSTile.AdapterState) ((QSTileImpl) BreathingLightTile.this).mState).value);
        }

        public final boolean hasFooterDivider() {
            return DeviceUtil.isLandscape(((QSTileImpl) BreathingLightTile.this).mContext);
        }

        public void setToggleState(boolean z) {
            BreathingLightTile.this.onDetailToggle(Boolean.valueOf(z));
        }

        public final void updatePanel() {
            AbsSettingsValueProxy.Companion companion = AbsSettingsValueProxy.Companion;
            BreathingLightTile breathingLightTile = BreathingLightTile.this;
            if (companion.getGlobalIntValue(((QSTileImpl) breathingLightTile).mContext, BreathingLightTile.BREATHING_LIGHT_SWITCH, 1) != 1) {
                View view = breathingLightTile.mVisible;
                if (view != null) {
                    view.setVisibility(8);
                }
                View view2 = breathingLightTile.mEmpty;
                if (view2 != null) {
                    view2.setVisibility(0);
                    return;
                }
                return;
            }
            ItemAdapter itemAdapter = breathingLightTile.mAdapter;
            if (itemAdapter != null) {
                itemAdapter.notifyDataSetChanged();
            }
            View view3 = breathingLightTile.mEmpty;
            if (view3 != null) {
                view3.setVisibility(8);
            }
            View view4 = breathingLightTile.mVisible;
            if (view4 != null) {
                view4.setVisibility(0);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public BreathingLightTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, OplusQSDetailInteractor.Factory factory, BroadcastSender broadcastSender) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.broadcastSender = broadcastSender;
        final Handler handler2 = new Handler(Looper.getMainLooper());
        this.mObserver = new ContentObserver(handler2) { // from class: com.oplus.systemui.qs.tiles.BreathingLightTile$mObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                this.this$0.refreshState((Object) null);
                if (this.this$0.detailInteractor.isShowing()) {
                    this.this$0.detailAdapter.updatePanel();
                }
            }
        };
        this.powerManager$delegate = LazyKt__LazyJVMKt.lazy(new BreathingLightTile$$ExternalSyntheticLambda0());
        BreathingLightDetailAdapter breathingLightDetailAdapter = new BreathingLightDetailAdapter(((QSTileImpl) this).mContext);
        this.detailAdapter = breathingLightDetailAdapter;
        this.detailInteractor = factory.create(breathingLightDetailAdapter);
        BreathingLightTile$breathFlashCloseRunable$1 breathingLightTile$breathFlashCloseRunable$1 = new BreathingLightTile$breathFlashCloseRunable$1();
        breathingLightTile$breathFlashCloseRunable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.breathFlashCloseRunable = breathingLightTile$breathFlashCloseRunable$1;
    }

    private final OplusPowerManager getPowerManager() {
        return (OplusPowerManager) this.powerManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBreathLightFlash(boolean z) {
        OplusPowerManager powerManager;
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setBreathLightFlash  status  = ", z), null, 8);
        if (!z) {
            OplusPowerManager powerManager2 = getPowerManager();
            if (powerManager2 != null) {
                powerManager2.setFlashing(7, 0, 0, 0, 1);
                return;
            }
            return;
        }
        if (FlavorOneFeatureOption.INSTANCE.isFlavorOneOtherBreathLightSupport()) {
            OplusPowerManager powerManager3 = getPowerManager();
            if (powerManager3 != null) {
                powerManager3.setFlashing(7, 603947366, 0, 0, 1);
                return;
            }
            return;
        }
        if (!FlavorOneFeatureOption.isShutdownBreathLightDongFengSupport() || (powerManager = getPowerManager()) == null) {
            return;
        }
        powerManager.setFlashing(7, 1443105030, 0, 0, 1);
    }

    private final void setBreathLightFlashBg(boolean z) {
        SystemUIThreadUtil.getWorkHandler().removeCallbacks(this.breathFlashCloseRunable);
        if (!z) {
            setBreathLightFlash(false);
            return;
        }
        setBreathLightFlash(true);
        if (FlavorOneFeatureOption.INSTANCE.isFlavorOneOtherBreathLightSupport()) {
            SystemUIThreadUtil.getWorkHandler().postDelayed(this.breathFlashCloseRunable, 6400L);
        } else if (FlavorOneFeatureOption.isShutdownBreathLightDongFengSupport()) {
            SystemUIThreadUtil.getWorkHandler().postDelayed(this.breathFlashCloseRunable, 5000L);
        }
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237101);
    }

    public Intent getLongClickIntent() {
        return null;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956549);
    }

    public void handleClick(Expandable expandable) {
        handleUserOperationInternal$3(!((QSTile.AdapterState) ((QSTileImpl) this).mState).value);
    }

    public final void handleDestroy() {
        FlavorOneFeatureOption flavorOneFeatureOption = FlavorOneFeatureOption.INSTANCE;
        flavorOneFeatureOption.getClass();
        if (FlavorOneFeatureOption.isShutdownBreathLightDongFengSupport() || flavorOneFeatureOption.isFlavorOneOtherBreathLightSupport()) {
            setBreathLightFlashBg(false);
        }
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, ((QSTileImpl) this).TAG, "handleDestroy", null, 8);
        super.handleDestroy();
    }

    public void handleLongClick(Expandable expandable) {
        this.detailInteractor.show(expandable != null ? expandable.getView() : null);
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        if (!z) {
            ObserverDispatcher.getInstance().unregisterObserver(this.mObserver);
        } else {
            ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor(BREATHING_LIGHT_SWITCH), this.mObserver);
            refreshState((Object) null);
        }
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int globalIntValue = AbsSettingsValueProxy.Companion.getGlobalIntValue(((QSTileImpl) this).mContext, BREATHING_LIGHT_SWITCH, 1);
        boolean z = globalIntValue == 1;
        boolean z2 = ((QSTile.AdapterState) booleanState).value != z;
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131956549);
        ((QSTile.AdapterState) booleanState).value = z;
        ((QSTile.State) booleanState).state = z ? 2 : 1;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(z ? 2131237100 : 2131237101);
        if (z2) {
            this.detailInteractor.updateSwitchState(((QSTile.AdapterState) booleanState).value);
        }
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("breathingLightState:" + globalIntValue + " lightEnable:" + z);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
    }

    public boolean handleUserOperation(Object obj) {
        Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
        handleUserOperationInternal$3(bool != null ? bool.booleanValue() : false);
        return true;
    }

    public final void handleUserOperationInternal$3(boolean z) {
        AbsSettingsValueProxy.Companion.setGlobalIntValue(((QSTileImpl) this).mContext, BREATHING_LIGHT_SWITCH, z ? 1 : 0);
        FlavorOneFeatureOption flavorOneFeatureOption = FlavorOneFeatureOption.INSTANCE;
        flavorOneFeatureOption.getClass();
        if (FlavorOneFeatureOption.isShutdownBreathLightDongFengSupport() || flavorOneFeatureOption.isFlavorOneOtherBreathLightSupport()) {
            setBreathLightFlashBg(z);
        }
        Intent intent = new Intent();
        intent.setAction(SETTINGS_BREATHING_LIGHT_ON_OFF_ACTION);
        intent.setPackage(SETTINGS_PACKAGE_NAME);
        this.broadcastSender.sendBroadcast(intent);
    }

    public final boolean isAvailable() {
        return FlavorOneFeatureOption.INSTANCE.isFlavorOneShutdownBreathLightSupport();
    }

    public final boolean isItemChecked(int i) {
        int i2 = itemRes[i];
        if (i2 == 2131955596) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(((QSTileImpl) this).mContext, BREATHING_LIGHT_INCOMING_CALL, 1) == 1;
        }
        if (i2 == 2131955598) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(((QSTileImpl) this).mContext, BREATHING_LIGHT_MMS, 1) == 1;
        }
        if (i2 == 2131955597) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(((QSTileImpl) this).mContext, BREATHING_LIGHT_MUSIC_REMINDER, 0) != 0;
        }
        return i2 == 2131954761 && AbsSettingsValueProxy.Companion.getGlobalIntValue(((QSTileImpl) this).mContext, BREATHING_LIGHT_ALWAYS_ON, 0) == 1;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m313newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }
}
