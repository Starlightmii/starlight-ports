package com.oplus.systemui.qs.tiles;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplus.systemui.qs.external.InnerCustomTileConnection;
import com.oplus.systemui.qs.utils.Iris5Util;
import com.oplus.systemui.qs.utils.OsieUtil;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OsieTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    private static final String KEY_OSIE_IRIS5_SWITCH = "osie_iris5_switch";
    public static final String TILE_SPEC = "osie";
    private InnerCustomTileConnection customTileConnection;
    public final ComponentName mComponentName;
    public boolean mIsCheck;
    private final SettingsObserver mObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SettingsObserver extends ContentObserver {
        public boolean isRegisterObserver;
        private final Context mContext;
        public final Uri mUri;
        private final ContentResolver resolver;

        public SettingsObserver(Handler handler, Context context) {
            super(handler);
            this.mContext = context;
            this.mUri = Settings.Secure.getUriFor("osie_iris5_switch");
            this.resolver = context.getContentResolver();
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            boolean z2 = Settings.Secure.getInt(this.mContext.getContentResolver(), "osie_iris5_switch", 0) == 1;
            if (Intrinsics.areEqual(this.mUri, uri)) {
                OsieTile osieTile = OsieTile.this;
                if (z2 != osieTile.mIsCheck) {
                    osieTile.mIsCheck = z2;
                    osieTile.refreshState((Object) null);
                }
            }
        }

        public final void registerObserver() {
            if (this.isRegisterObserver) {
                return;
            }
            this.resolver.registerContentObserver(this.mUri, false, this, -1);
            this.isRegisterObserver = true;
        }

        public final void unRegisterObserver() {
            if (this.isRegisterObserver) {
                this.isRegisterObserver = false;
                this.resolver.unregisterContentObserver(this);
            }
        }
    }

    public OsieTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, Context context, InnerCustomTileConnection.Factory factory) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        ComponentName componentFromSpec = CustomTile.getComponentFromSpec("custom(com.android.settings/com.oplus.settings.feature.display.video.OsieTileService)");
        this.mComponentName = componentFromSpec;
        this.customTileConnection = factory.create(TILE_SPEC, componentFromSpec, context.getUser());
        this.mObserver = new SettingsObserver(new Handler(Looper.getMainLooper()), ((QSTileImpl) this).mContext);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131236074);
    }

    public Intent getLongClickIntent() {
        Intent intentResolveIntent = QsUtil.resolveIntent(((QSTileImpl) this).mContext, new Intent("android.service.quicksettings.action.QS_TILE_PREFERENCES").setPackage(this.mComponentName.getPackageName()));
        if (intentResolveIntent == null) {
            return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", this.mComponentName.getPackageName(), null));
        }
        intentResolveIntent.putExtra("android.intent.extra.COMPONENT_NAME", this.mComponentName);
        return intentResolveIntent;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        FlavorTwoFeatureOption.INSTANCE.getClass();
        boolean zIsFlavorTwoDevice = FlavorTwoFeatureOption.isFlavorTwoDevice();
        Context context = ((QSTileImpl) this).mContext;
        if (zIsFlavorTwoDevice) {
            String string = context.getString(2131955177);
            string.getClass();
            return string;
        }
        String string2 = context.getString(2131955176);
        string2.getClass();
        return string2;
    }

    public void handleClick(Expandable expandable) {
        if (((QSTile.State) getState()).state == 0) {
            return;
        }
        this.customTileConnection.onClick();
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        SettingsObserver settingsObserver = this.mObserver;
        if (z) {
            settingsObserver.registerObserver();
        } else {
            settingsObserver.unRegisterObserver();
        }
        this.customTileConnection.setListening(z);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean zIsOsieSwitch = OsieUtil.isOsieSwitch(((QSTileImpl) this).mContext.getApplicationContext());
        ((QSTile.AdapterState) booleanState).value = zIsOsieSwitch;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(zIsOsieSwitch ? 2131236075 : 2131236074);
        FlavorTwoFeatureOption.INSTANCE.getClass();
        boolean zIsFlavorTwoDevice = FlavorTwoFeatureOption.isFlavorTwoDevice();
        Context context = ((QSTileImpl) this).mContext;
        ((QSTile.State) booleanState).label = zIsFlavorTwoDevice ? context.getString(2131955177) : context.getString(2131955176);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
        ((QSTile.State) booleanState).contentDescription = ((QSTile.State) booleanState).label;
        ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
    }

    public final boolean isAvailable() {
        Iris5Util iris5Util = Iris5Util.INSTANCE;
        return OsBinderCacheUtils.getCurrentUserId() == 0 && this.customTileConnection.checkComponentState() && (!StringsKt__StringsKt.contains(QSFeatureOption.INSTANCE.getDisableQSTileList(), TILE_SPEC, false) || StringsKt__StringsKt.contains(TextUtils.emptyIfNull(Settings.Secure.getString(((QSTileImpl) this).mContext.getContentResolver(), "sysui_qs_tiles")), TILE_SPEC, false));
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m341newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }
}
