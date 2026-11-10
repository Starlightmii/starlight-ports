package com.oplus.systemui.qs.impl;

import android.app.FragmentController;
import android.content.Context;
import android.content.res.Configuration;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.applications.InterestingConfigChanges;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.OplusBrightnessControllerEx;
import com.android.systemui.qs.OplusFlashLightControllerEx;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.connectivity.OplusQSNetworkControllerEx;
import com.android.systemui.qs.deprecated.QSFragmentLegacy;
import com.android.systemui.qs.deprecated.QSPanel;
import com.android.systemui.qs.deprecated.QuickQSPanel;
import com.android.systemui.qs.external.TileLifecycleManagerEx;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.qs.tile.QSTileHostHelperEx;
import com.android.systemui.qs.user.UserSwitcherControllerHelperEx;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.oplus.systemui.flashlight.FlavorOneFlashLightControllerExImpl;
import com.oplus.systemui.qs.OplusQSContainerExImpl;
import com.oplus.systemui.qs.OplusQSSmallSpaceController;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.connectivity.OplusQSNetworkControllerExImpl;
import com.oplus.systemui.qs.external.OplusTileLifecycleManagerExImpl;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.helper.QSTileFilterHelper;
import com.oplus.systemui.qs.helper.QSTileHostHelper;
import com.oplus.systemui.qs.helper.UserSwitcherControllerHelper;
import com.oplus.systemui.qs.personality.PersonalityManager;
import com.oplus.systemui.seamless.OplusFakeViewProvider;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.policy.BrightnessMirrorControllerExImpl;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.util.OpUtils;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQuickSettingsExImpl extends OplusQuickSettingsEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQuickSettingsExImp";
    private final Lazy accessPointControllerEx$delegate;
    private final ActivityStarter activityStarter;
    private final Lazy brightnessControllerEx$delegate;
    private final Lazy brightnessMirrorControllerEx$delegate;
    private final Context context;
    private final ArrayList<String> customTilesCanTipsArray;
    private dagger.Lazy flashLightControllerExImpl;
    private final KeyguardViewMediator keyguardViewMediator;
    private final InterestingConfigChanges mConfigChanges;
    private final Lazy oplusFlashLightControllerEx$delegate;
    private final Lazy oplusQSContainerEx$delegate;
    private final Lazy oplusQsBackUpEx$delegate;
    private final Lazy personalityManagerEx$delegate;
    private final Lazy qsNetworkControllerEx$delegate;
    private dagger.Lazy qsNetworkControllerExImpl;
    private final OplusQSSmallSpaceController qsSmallSpaceController;
    private dagger.Lazy qsTileHost;
    private final Lazy qsTileHostHelperEx$delegate;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final ShadeRepository shadeRepository;
    private final Lazy superPowerSaveRepository$delegate;
    private dagger.Lazy superPowerSaveRepositoryImpl;
    private final Lazy tileLifecycleManagerEx$delegate;
    private final Lazy tileServiceRequestControllerEx$delegate;
    private dagger.Lazy userSwitcherControllerHelper;
    private final Lazy userSwitcherControllerHelperEx$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static QSTileHostHelper $r8$lambda$7mpQYpIYn39J40hys75C2vcHVSI(OplusQuickSettingsExImpl oplusQuickSettingsExImpl) {
        Context context = oplusQuickSettingsExImpl.context;
        dagger.Lazy lazy = oplusQuickSettingsExImpl.qsTileHost;
        QSHost qSHost = lazy != null ? (QSHost) lazy.get() : null;
        QSTileHostHelper qSTileHostHelper = new QSTileHostHelper();
        HashMap map = new HashMap();
        qSTileHostHelper.mUpgradingMap = map;
        qSTileHostHelper.mTileMappingList = new HashMap();
        qSTileHostHelper.mContext = context;
        qSTileHostHelper.mHost = qSHost;
        map.put("custom(com.android.settings/com.coloros.partners.dolby.DolbyTileService)", "custom(com.android.settings/com.oplus.partners.dolby.DolbyTileService)");
        map.put("custom(com.coloros.oshare/.OshareTileService)", "custom(com.coloros.oshare/com.oplus.oshare.OshareTileService)");
        FlavorTwoFeatureOption.INSTANCE.getClass();
        if (FlavorTwoFeatureOption.isFlavorTwoDeviceExp()) {
            map.put("custom(com.coloros.phonemanager/.voicecallnc.VoiceCallNcTileService)", "custom(com.oplus.phonemanager/.voicecallnc.VoiceCallNcTileService)");
            map.put("custom(com.coloros.phonemanager/com.oplus.phonemanager.voicecallnc.VoiceCallNcTileService)", "custom(com.oplus.phonemanager/.voicecallnc.VoiceCallNcTileService)");
        } else {
            map.put("custom(com.coloros.phonemanager/.voicecallnc.VoiceCallNcTileService)", "custom(com.coloros.phonemanager/com.oplus.phonemanager.voicecallnc.VoiceCallNcTileService)");
            map.put("custom(com.oplus.phonemanager/.voicecallnc.VoiceCallNcTileService)", "custom(com.coloros.phonemanager/com.oplus.phonemanager.voicecallnc.VoiceCallNcTileService)");
        }
        map.put("custom(com.android.settings/com.coloros.settings.feature.display.video.InputMethodTile)", "custom(com.android.settings/com.oplus.settings.feature.display.video.InputMethodTile)");
        map.put("custom(com.coloros.safecenter/.privacy.control.AppLockSwitchTileService)", "custom(com.oplus.safecenter/.privacy.control.AppLockSwitchTileService)");
        map.put("custom(com.android.settings/com.coloros.settings.feature.display.video.OsieTileService)", "custom(com.android.settings/com.oplus.settings.feature.display.video.OsieTileService)");
        map.put("custom(com.android.settings/com.coloros.settings.feature.display.video.Iris5TileService)", "custom(com.android.settings/com.oplus.settings.feature.display.video.Iris5TileService)");
        map.put("custom(com.android.settings/com.coloros.settings.feature.display.darkmode.DarkModeTileService)", "custom(com.android.settings/com.oplus.settings.feature.display.darkmode.DarkModeTileService)");
        map.put("custom(com.android.settings/com.coloros.settings.feature.display.nightmode.AutoScreenTemperatureTileService)", QSTileFilterHelper.AUTO_SCREEN_TEMPERATURE);
        map.put("custom(com.coloros.eyeprotect/com.color.eyeprotect.services.ColorEyeProtectTileService)", "custom(com.oplus.eyeprotect/.services.EyeProtectTileService)");
        map.put("custom(com.coloros.oppoguardelf/com.coloros.powermanager.wirelesscharg.WirelessReverseControService)", "custom(com.oplus.battery/com.oplus.powermanager.wirelesscharg.WirelessReverseControService)");
        map.put("custom(com.heytap.synergy/.service.SynergyTileService)", "custom(com.oplus.linker/.synergy.service.SynergyTileService)");
        map.put("custom(com.oplus.synergy/.service.SynergyTileService)", "custom(com.oplus.linker/.synergy.service.SynergyTileService)");
        map.put("custom(com.heytap.synergy/.service.TVTileService)", "custom(com.oplus.linker/.synergy.service.TVTileService)");
        map.put("custom(com.oplus.synergy/.service.TVTileService)", "custom(com.oplus.linker/.synergy.service.TVTileService)");
        map.put("custom(com.coloros.screenrecorder/.services.QuickPanelService)", "custom(com.oplus.screenrecorder/.floatwindow.services.QuickPanelService)");
        String[] stringArray = context.getResources().getStringArray(2130903277);
        String[] stringArray2 = context.getResources().getStringArray(2130903278);
        for (String str : stringArray) {
            String[] strArrSplit = str.split(":");
            if (strArrSplit.length == 2) {
                qSTileHostHelper.mTileMappingList.put(strArrSplit[0], strArrSplit[1]);
            }
        }
        for (String str2 : stringArray2) {
            String[] strArrSplit2 = str2.split(":");
            if (strArrSplit2.length == 2) {
                qSTileHostHelper.mTileMappingList.put(strArrSplit2[0], strArrSplit2[1]);
            }
        }
        VarHandle.storeStoreFence();
        return qSTileHostHelper;
    }

    public static SuperPowerSaveRepository $r8$lambda$8dQ7wWaY9TBkFxF9ErJ15zrJITU(OplusQuickSettingsExImpl oplusQuickSettingsExImpl) {
        SuperPowerSaveRepository superPowerSaveRepository;
        dagger.Lazy lazy = oplusQuickSettingsExImpl.superPowerSaveRepositoryImpl;
        return (lazy == null || (superPowerSaveRepository = (SuperPowerSaveRepository) lazy.get()) == null) ? super.getSuperPowerSaveRepository() : superPowerSaveRepository;
    }

    /* JADX INFO: renamed from: $r8$lambda$EUQQaFsUI-JYHXwCnJuG4vPFtvk, reason: not valid java name */
    public static OplusAccessPointControllerExImpl m272$r8$lambda$EUQQaFsUIJYHXwCnJuG4vPFtvk(OplusQuickSettingsExImpl oplusQuickSettingsExImpl) {
        return new OplusAccessPointControllerExImpl(oplusQuickSettingsExImpl.context);
    }

    public static OplusTileLifecycleManagerExImpl $r8$lambda$Icv0kuStGiBNbRVzyb3PD3_E2AQ(OplusQuickSettingsExImpl oplusQuickSettingsExImpl) {
        return new OplusTileLifecycleManagerExImpl(oplusQuickSettingsExImpl.context);
    }

    public static UserSwitcherControllerHelperEx $r8$lambda$JS6OKqxhh7q6ZJZ7vFZonLXNP7U(OplusQuickSettingsExImpl oplusQuickSettingsExImpl) {
        UserSwitcherControllerHelper userSwitcherControllerHelper;
        dagger.Lazy lazy = oplusQuickSettingsExImpl.userSwitcherControllerHelper;
        return (lazy == null || (userSwitcherControllerHelper = (UserSwitcherControllerHelper) lazy.get()) == null) ? super.getUserSwitcherControllerHelperEx() : userSwitcherControllerHelper;
    }

    public static OplusBrightnessControllerExImpl $r8$lambda$Xo8I5dimbhwEpYdbHjPifu3UssQ(OplusQuickSettingsExImpl oplusQuickSettingsExImpl) {
        FlavorOneFeatureOption.INSTANCE.getClass();
        boolean zIsFlavorOneDevice = FlavorOneFeatureOption.isFlavorOneDevice();
        Context context = oplusQuickSettingsExImpl.context;
        return zIsFlavorOneDevice ? new FlavorOneBrightnessControllerExImpl(context) : new OplusBrightnessControllerExImpl(context);
    }

    /* JADX INFO: renamed from: $r8$lambda$nh-cBvusk77QOdn_e3XNi5RNhAU, reason: not valid java name */
    public static OplusFlashLightControllerEx m273$r8$lambda$nhcBvusk77QOdn_e3XNi5RNhAU(OplusQuickSettingsExImpl oplusQuickSettingsExImpl) {
        FlavorOneFlashLightControllerExImpl flavorOneFlashLightControllerExImpl;
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (!FlavorOneFeatureOption.isFlavorOneDevice()) {
            return super.getOplusFlashLightControllerEx();
        }
        dagger.Lazy lazy = oplusQuickSettingsExImpl.flashLightControllerExImpl;
        return (lazy == null || (flavorOneFlashLightControllerExImpl = (FlavorOneFlashLightControllerExImpl) lazy.get()) == null) ? super.getOplusFlashLightControllerEx() : flavorOneFlashLightControllerExImpl;
    }

    public static OplusQSNetworkControllerEx $r8$lambda$o0KXHqZ1Z3LjQo0RkV1e5rUJEWo(OplusQuickSettingsExImpl oplusQuickSettingsExImpl) {
        OplusQSNetworkControllerExImpl oplusQSNetworkControllerExImpl;
        dagger.Lazy lazy = oplusQuickSettingsExImpl.qsNetworkControllerExImpl;
        return (lazy == null || (oplusQSNetworkControllerExImpl = (OplusQSNetworkControllerExImpl) lazy.get()) == null) ? super.getQsNetworkControllerEx() : oplusQSNetworkControllerExImpl;
    }

    public static PersonalityManager $r8$lambda$raTXZn_9DrHqfdQVxYmOh3UDAN8(OplusQuickSettingsExImpl oplusQuickSettingsExImpl) {
        return new PersonalityManager(oplusQuickSettingsExImpl.context);
    }

    /* JADX INFO: renamed from: $r8$lambda$xO26Qv6nCq0FSFZS144qzx2JP-g, reason: not valid java name */
    public static OplusQsBackupExImpl m274$r8$lambda$xO26Qv6nCq0FSFZS144qzx2JPg(OplusQuickSettingsExImpl oplusQuickSettingsExImpl) {
        return new OplusQsBackupExImpl(oplusQuickSettingsExImpl.context);
    }

    public OplusQuickSettingsExImpl(Context context, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, OplusQSSmallSpaceController oplusQSSmallSpaceController, ActivityStarter activityStarter, ShadeRepository shadeRepository, KeyguardViewMediator keyguardViewMediator) {
        super(context, activityStarter);
        this.context = context;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.qsSmallSpaceController = oplusQSSmallSpaceController;
        this.activityStarter = activityStarter;
        this.shadeRepository = shadeRepository;
        this.keyguardViewMediator = keyguardViewMediator;
        this.mConfigChanges = new InterestingConfigChanges(1073745924);
        ArrayList<String> arrayList = new ArrayList<>();
        this.customTilesCanTipsArray = arrayList;
        oplusQSSmallSpaceController.init();
        CollectionsKt__MutableCollectionsKt.addAll(arrayList, context.getResources().getStringArray(2130903276));
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "init customTilesCanTipsArray: " + arrayList, null, 8);
        final int i = 0;
        OplusQuickSettingsExImpl$$ExternalSyntheticLambda0 oplusQuickSettingsExImpl$$ExternalSyntheticLambda0 = new OplusQuickSettingsExImpl$$ExternalSyntheticLambda0(i);
        oplusQuickSettingsExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.accessPointControllerEx$delegate = LazyKt__LazyJVMKt.lazy(oplusQuickSettingsExImpl$$ExternalSyntheticLambda0);
        OplusQuickSettingsExImpl$$ExternalSyntheticLambda0 oplusQuickSettingsExImpl$$ExternalSyntheticLambda1 = new OplusQuickSettingsExImpl$$ExternalSyntheticLambda0(4);
        oplusQuickSettingsExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.superPowerSaveRepository$delegate = LazyKt__LazyJVMKt.lazy(oplusQuickSettingsExImpl$$ExternalSyntheticLambda1);
        OplusQuickSettingsExImpl$$ExternalSyntheticLambda0 oplusQuickSettingsExImpl$$ExternalSyntheticLambda2 = new OplusQuickSettingsExImpl$$ExternalSyntheticLambda0(5);
        oplusQuickSettingsExImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.qsTileHostHelperEx$delegate = LazyKt__LazyJVMKt.lazy(oplusQuickSettingsExImpl$$ExternalSyntheticLambda2);
        OplusQuickSettingsExImpl$$ExternalSyntheticLambda0 oplusQuickSettingsExImpl$$ExternalSyntheticLambda3 = new OplusQuickSettingsExImpl$$ExternalSyntheticLambda0(6);
        oplusQuickSettingsExImpl$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.tileLifecycleManagerEx$delegate = LazyKt__LazyJVMKt.lazy(oplusQuickSettingsExImpl$$ExternalSyntheticLambda3);
        OplusQuickSettingsExImpl$$ExternalSyntheticLambda0 oplusQuickSettingsExImpl$$ExternalSyntheticLambda4 = new OplusQuickSettingsExImpl$$ExternalSyntheticLambda0(7);
        oplusQuickSettingsExImpl$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.brightnessControllerEx$delegate = LazyKt__LazyJVMKt.lazy(oplusQuickSettingsExImpl$$ExternalSyntheticLambda4);
        OplusQuickSettingsExImpl$$ExternalSyntheticLambda0 oplusQuickSettingsExImpl$$ExternalSyntheticLambda5 = new OplusQuickSettingsExImpl$$ExternalSyntheticLambda0(8);
        oplusQuickSettingsExImpl$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusFlashLightControllerEx$delegate = LazyKt__LazyJVMKt.lazy(oplusQuickSettingsExImpl$$ExternalSyntheticLambda5);
        OplusQuickSettingsExImpl$$ExternalSyntheticLambda0 oplusQuickSettingsExImpl$$ExternalSyntheticLambda6 = new OplusQuickSettingsExImpl$$ExternalSyntheticLambda0(9);
        oplusQuickSettingsExImpl$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.userSwitcherControllerHelperEx$delegate = LazyKt__LazyJVMKt.lazy(oplusQuickSettingsExImpl$$ExternalSyntheticLambda6);
        final int i2 = 1;
        OplusQuickSettingsExImpl$$ExternalSyntheticLambda0 oplusQuickSettingsExImpl$$ExternalSyntheticLambda7 = new OplusQuickSettingsExImpl$$ExternalSyntheticLambda0(i2);
        oplusQuickSettingsExImpl$$ExternalSyntheticLambda7.f$0 = this;
        VarHandle.storeStoreFence();
        this.personalityManagerEx$delegate = LazyKt__LazyJVMKt.lazy(oplusQuickSettingsExImpl$$ExternalSyntheticLambda7);
        this.oplusQSContainerEx$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.qs.impl.OplusQuickSettingsExImpl$$ExternalSyntheticLambda1
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return new BrightnessMirrorControllerExImpl();
                    case 1:
                        return new OplusQSContainerExImpl();
                    default:
                        return new OplusTileServiceRequestControllerExImpl();
                }
            }
        });
        final int i3 = 2;
        OplusQuickSettingsExImpl$$ExternalSyntheticLambda0 oplusQuickSettingsExImpl$$ExternalSyntheticLambda8 = new OplusQuickSettingsExImpl$$ExternalSyntheticLambda0(i3);
        oplusQuickSettingsExImpl$$ExternalSyntheticLambda8.f$0 = this;
        VarHandle.storeStoreFence();
        this.qsNetworkControllerEx$delegate = LazyKt__LazyJVMKt.lazy(oplusQuickSettingsExImpl$$ExternalSyntheticLambda8);
        this.brightnessMirrorControllerEx$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.qs.impl.OplusQuickSettingsExImpl$$ExternalSyntheticLambda1
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return new BrightnessMirrorControllerExImpl();
                    case 1:
                        return new OplusQSContainerExImpl();
                    default:
                        return new OplusTileServiceRequestControllerExImpl();
                }
            }
        });
        OplusQuickSettingsExImpl$$ExternalSyntheticLambda0 oplusQuickSettingsExImpl$$ExternalSyntheticLambda9 = new OplusQuickSettingsExImpl$$ExternalSyntheticLambda0(3);
        oplusQuickSettingsExImpl$$ExternalSyntheticLambda9.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusQsBackUpEx$delegate = LazyKt__LazyJVMKt.lazy(oplusQuickSettingsExImpl$$ExternalSyntheticLambda9);
        this.tileServiceRequestControllerEx$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.qs.impl.OplusQuickSettingsExImpl$$ExternalSyntheticLambda1
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return new BrightnessMirrorControllerExImpl();
                    case 1:
                        return new OplusQSContainerExImpl();
                    default:
                        return new OplusTileServiceRequestControllerExImpl();
                }
            }
        });
    }

    @Deprecated
    public static /* synthetic */ void getSuperPowerSaveRepository$annotations() {
    }

    public OplusAccessPointControllerExImpl getAccessPointControllerEx() {
        return (OplusAccessPointControllerExImpl) this.accessPointControllerEx$delegate.getValue();
    }

    public final ActivityStarter getActivityStarter() {
        return this.activityStarter;
    }

    public OplusBrightnessControllerEx getBrightnessControllerEx() {
        return (OplusBrightnessControllerEx) this.brightnessControllerEx$delegate.getValue();
    }

    public BrightnessMirrorControllerExImpl getBrightnessMirrorControllerEx() {
        return (BrightnessMirrorControllerExImpl) this.brightnessMirrorControllerEx$delegate.getValue();
    }

    public final Context getContext() {
        return this.context;
    }

    public final dagger.Lazy getFlashLightControllerExImpl() {
        return this.flashLightControllerExImpl;
    }

    public final KeyguardViewMediator getKeyguardViewMediator() {
        return this.keyguardViewMediator;
    }

    public OplusFlashLightControllerEx getOplusFlashLightControllerEx() {
        return (OplusFlashLightControllerEx) this.oplusFlashLightControllerEx$delegate.getValue();
    }

    public OplusQSContainerExImpl getOplusQSContainerEx() {
        return (OplusQSContainerExImpl) this.oplusQSContainerEx$delegate.getValue();
    }

    public OplusQsBackupExImpl getOplusQsBackUpEx() {
        return (OplusQsBackupExImpl) this.oplusQsBackUpEx$delegate.getValue();
    }

    public PersonalityManager getPersonalityManagerEx() {
        return (PersonalityManager) this.personalityManagerEx$delegate.getValue();
    }

    public OplusQSNetworkControllerEx getQsNetworkControllerEx() {
        return (OplusQSNetworkControllerEx) this.qsNetworkControllerEx$delegate.getValue();
    }

    public final dagger.Lazy getQsNetworkControllerExImpl() {
        return this.qsNetworkControllerExImpl;
    }

    public final OplusQSSmallSpaceController getQsSmallSpaceController() {
        return this.qsSmallSpaceController;
    }

    public QSHost getQsTileHost() {
        dagger.Lazy lazy = this.qsTileHost;
        if (lazy != null) {
            return (QSHost) lazy.get();
        }
        return null;
    }

    public QSTileHostHelperEx getQsTileHostHelperEx() {
        return (QSTileHostHelperEx) this.qsTileHostHelperEx$delegate.getValue();
    }

    public final OplusSeparateNotificationAndQSState getSeparateNotificationAndQSState() {
        return this.separateNotificationAndQSState;
    }

    public Flow getSeparateNtfAndQSFlow() {
        return this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS();
    }

    public final ShadeRepository getShadeRepository() {
        return this.shadeRepository;
    }

    public SuperPowerSaveRepository getSuperPowerSaveRepository() {
        return (SuperPowerSaveRepository) this.superPowerSaveRepository$delegate.getValue();
    }

    public final dagger.Lazy getSuperPowerSaveRepositoryImpl() {
        return this.superPowerSaveRepositoryImpl;
    }

    public TileLifecycleManagerEx getTileLifecycleManagerEx() {
        return (TileLifecycleManagerEx) this.tileLifecycleManagerEx$delegate.getValue();
    }

    public OplusTileServiceRequestControllerExImpl getTileServiceRequestControllerEx() {
        return (OplusTileServiceRequestControllerExImpl) this.tileServiceRequestControllerEx$delegate.getValue();
    }

    public final dagger.Lazy getUserSwitcherControllerHelper() {
        return this.userSwitcherControllerHelper;
    }

    public UserSwitcherControllerHelperEx getUserSwitcherControllerHelperEx() {
        return (UserSwitcherControllerHelperEx) this.userSwitcherControllerHelperEx$delegate.getValue();
    }

    public boolean isCustomTileCnTips(String str) {
        if (str != null) {
            return this.customTilesCanTipsArray.contains(str);
        }
        return false;
    }

    public final boolean isDeviceInteractive() {
        return this.keyguardViewMediator.mDeviceInteractive;
    }

    public final boolean isSeparateMode() {
        return ((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue();
    }

    public final boolean isSeparateQSFullCollapse() {
        return ((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue() && !((Boolean) this.shadeRepository.getLegacyExpandedOrAwaitingInputTransferForQS().getValue()).booleanValue();
    }

    public final void setFlashLightControllerExImpl(dagger.Lazy lazy) {
        this.flashLightControllerExImpl = lazy;
    }

    public final void setQsNetworkControllerExImpl(dagger.Lazy lazy) {
        this.qsNetworkControllerExImpl = lazy;
    }

    public final void setQsTileHost(dagger.Lazy lazy) {
        this.qsTileHost = lazy;
    }

    public final void setSuperPowerSaveRepositoryImpl(dagger.Lazy lazy) {
        this.superPowerSaveRepositoryImpl = lazy;
    }

    public final void setUserSwitcherControllerHelper(dagger.Lazy lazy) {
        this.userSwitcherControllerHelper = lazy;
    }

    public boolean shouldReloadFragments(FragmentController fragmentController, Configuration configuration, InterestingConfigChanges interestingConfigChanges, Context context) {
        boolean zApplyNewConfig = this.mConfigChanges.applyNewConfig(context.getResources());
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        qSFragmentHelper.getClass();
        boolean z = false;
        if (fragmentController == null || !(fragmentController.getFragmentManager().findFragmentByTag(OplusFakeViewProvider.QS) instanceof QSFragmentLegacy)) {
            int themeAccentColor = OpUtils.getThemeAccentColor(context, true);
            int i = qSFragmentHelper.mLastActiveColor;
            qSFragmentHelper.mLastAccentColorChanged = i != themeAccentColor;
            if (i != themeAccentColor) {
                ClipboardOverlayView$.ExternalSyntheticOutline0.m(qSFragmentHelper.mLastActiveColor, LogUtil.TAG_QS, "QSFragmentHelper", ListPopupWindow$.ExternalSyntheticOutline0.m(themeAccentColor, "configChanged activeColor=", " mLastActiveColor="));
                qSFragmentHelper.mLastActiveColor = themeAccentColor;
                int darkThemeAccentColor = OpUtils.getDarkThemeAccentColor(context);
                qSFragmentHelper.mLastActiveColorDark = darkThemeAccentColor;
                BaseQSResPool.updateDarkThemeAccentColor(darkThemeAccentColor);
                qSFragmentHelper.mLastAccentColorConfigChanged = qSFragmentHelper.mLastAccentColorChanged;
            }
            z = qSFragmentHelper.mLastAccentColorChanged;
        }
        boolean zApplyFlipFontConfig = QSFragmentHelper.getInstance().applyFlipFontConfig(configuration);
        boolean zApplyDynamicFontVariationConfig = QSFragmentHelper.getInstance().applyDynamicFontVariationConfig(configuration);
        boolean z2 = zApplyNewConfig | zApplyFlipFontConfig | zApplyDynamicFontVariationConfig;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("shouldReloadFragments ", " applyNewConfig=", " applyAccentColor=", z2, zApplyNewConfig);
        KeyguardLogger$.ExternalSyntheticOutline0.m(" applyFlipFontConfig=", " applyDynamicFontVariationConfig=", sbM, z, zApplyFlipFontConfig);
        sbM.append(zApplyDynamicFontVariationConfig);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        return z2;
    }

    public final boolean shouldReloadImmediately() {
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        if (qSFragmentHelper.mLastAccentColorChanged) {
            qSFragmentHelper.mLastAccentColorChanged = false;
            return true;
        }
        QSPanel qSPanel = qSFragmentHelper.mQSPanel;
        if (qSPanel != null && qSPanel.isVisibleToUser()) {
            return true;
        }
        QuickQSPanel quickQSPanel = qSFragmentHelper.mHeaderPanel;
        return (quickQSPanel != null && quickQSPanel.isVisibleToUser()) || ((Boolean) this.shadeRepository.getLegacyExpandedOrAwaitingInputTransfer().getValue()).booleanValue();
    }

    public final void updateConfigs() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateConfigs", null, 8);
        this.mConfigChanges.applyNewConfig(this.context.getResources());
    }

    /* JADX INFO: renamed from: getQsTileHost, reason: collision with other method in class */
    public final dagger.Lazy m280getQsTileHost() {
        return this.qsTileHost;
    }
}
