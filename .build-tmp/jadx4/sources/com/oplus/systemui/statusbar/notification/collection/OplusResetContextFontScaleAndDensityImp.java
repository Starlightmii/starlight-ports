package com.oplus.systemui.statusbar.notification.collection;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusResetContextFontScaleAndDensity;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusResetContextFontScaleAndDensityImp extends OplusResetContextFontScaleAndDensity {
    private static final Companion Companion = new Companion();
    private static final float[] FONT_SCALE_DEFAULT = {0.9f, 1.0f, 1.15f, 1.35f, 1.6f};
    private static final float[] FONT_SCALE_TARGET = {0.9f, 1.0f, 1.15f, 1.25f, 1.36f};
    private static final String SETTINGS_KEY = "display_density_index_manual";
    private static final String TAG = "OplusResetContextFontScaleAndDensity";
    private final Context appContext;
    public volatile float appDisplayDefaultDensity;
    private final ConfigurationController configurationController;
    private final Uri contentUri;
    public volatile int displayDensityIndex = -1;
    public final boolean isLowGaussianLevel;
    private final Handler mainHandler;
    private final NotificationMaterialColorEx notificationMaterialColorEx;
    public float oplusAppDisplayDensity;
    private Context oplusContext;
    private final ContentObserver settingsObserver;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.OplusResetContextFontScaleAndDensityImp$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ OplusResetContextFontScaleAndDensityImp this$0;

        public final void onConfigChanged(Configuration configuration) {
            OplusResetContextFontScaleAndDensityImp oplusResetContextFontScaleAndDensityImp = this.this$0;
            oplusResetContextFontScaleAndDensityImp.refreshDisplayDensity();
            OplusResetContextFontScaleAndDensityImp.access$refreshOplusContextDisplayDensity(oplusResetContextFontScaleAndDensityImp, configuration);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusResetContextFontScaleAndDensityImp(Context context, final Handler handler, ConfigurationController configurationController, NotificationMaterialColorEx notificationMaterialColorEx) {
        this.appContext = context;
        this.mainHandler = handler;
        this.configurationController = configurationController;
        this.notificationMaterialColorEx = notificationMaterialColorEx;
        Uri uriFor = Settings.System.getUriFor(SETTINGS_KEY);
        this.contentUri = uriFor;
        this.isLowGaussianLevel = ScrimUtil.isLowGaussianLevel(context);
        ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.statusbar.notification.collection.OplusResetContextFontScaleAndDensityImp$settingsObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                this.this$0.refreshDisplayDensity();
            }
        };
        this.settingsObserver = contentObserver;
        refreshDisplayDensity();
        context.getContentResolver().registerContentObserver(uriFor, false, contentObserver);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        configurationController.addCallback(anonymousClass1);
    }

    public static final void access$refreshOplusContextDisplayDensity(OplusResetContextFontScaleAndDensityImp oplusResetContextFontScaleAndDensityImp, Configuration configuration) {
        Context context;
        if (configuration == null || (context = oplusResetContextFontScaleAndDensityImp.oplusContext) == null) {
            return;
        }
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources != null ? resources.getDisplayMetrics() : null;
        float oplusFontScale = getOplusFontScale(configuration.fontScale);
        float oplusDisplayDensity = oplusResetContextFontScaleAndDensityImp.getOplusDisplayDensity();
        Configuration configuration2 = new Configuration(context.getResources().getConfiguration());
        configuration2.setTo(configuration);
        configuration2.fontScale = oplusFontScale;
        if (displayMetrics != null) {
            displayMetrics.density = oplusDisplayDensity;
        }
        oplusResetContextFontScaleAndDensityImp.oplusContext = context.createConfigurationContext(configuration2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusResetContextFontScaleAndDensity-->".concat("refresh oplus oplusContext: fontScale=" + oplusFontScale + ", densityDpi=" + configuration2.densityDpi));
        }
    }

    private final float getOplusDisplayDensity() {
        return this.oplusAppDisplayDensity;
    }

    public static float getOplusFontScale(float f) {
        int i;
        float[] fArr = FONT_SCALE_TARGET;
        int length = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = -1;
            if (i3 >= length) {
                i3 = -1;
                break;
            }
            if (fArr[i3] == f) {
                break;
            }
            i3++;
        }
        if (i3 >= 0) {
            return FONT_SCALE_TARGET[i3];
        }
        float[] fArr2 = FONT_SCALE_DEFAULT;
        int length2 = fArr2.length;
        for (int i4 = 0; i4 < length2; i4++) {
            if (fArr2[i4] == f) {
                i = i4;
                break;
            }
        }
        if (i >= 0) {
            return FONT_SCALE_TARGET[i];
        }
        float[] fArr3 = FONT_SCALE_DEFAULT;
        float fAbs = Math.abs(f - fArr3[0]);
        int length3 = fArr3.length;
        for (int i5 = 1; i5 < length3; i5++) {
            float fAbs2 = Math.abs(f - FONT_SCALE_DEFAULT[i5]);
            if (fAbs2 < fAbs) {
                i2 = i5;
                fAbs = fAbs2;
            }
        }
        return FONT_SCALE_TARGET[i2];
    }

    public Context createContextFontScaleAndDisplayDensity(Context context) {
        Configuration configuration;
        Resources resources = context.getResources();
        Configuration configuration2 = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float oplusFontScale = getOplusFontScale(configuration2.fontScale);
        float oplusDisplayDensity = getOplusDisplayDensity();
        float f = configuration2.fontScale;
        float f2 = displayMetrics.density;
        Context context2 = this.oplusContext;
        if (context2 == null) {
            Configuration configuration3 = new Configuration();
            configuration3.setTo(configuration2);
            configuration3.fontScale = oplusFontScale;
            resources.getDisplayMetrics().density = oplusDisplayDensity;
            this.oplusContext = context.createConfigurationContext(configuration3);
        } else {
            Resources resources2 = context2.getResources();
            if (resources2 != null && (configuration = resources2.getConfiguration()) != null) {
                configuration.setTo(configuration2);
                configuration.fontScale = oplusFontScale;
                resources.getDisplayMetrics().density = oplusDisplayDensity;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("reCreate fontScale and density: fontScaleBase=", f, " target=", oplusFontScale, ", densityBase="), f2, " target=", oplusDisplayDensity, "OplusResetContextFontScaleAndDensity-->"));
        }
        Context context3 = this.oplusContext;
        return context3 != null ? context3 : context;
    }

    public Context createDarkModeContext(Context context, ExpandableNotificationRow expandableNotificationRow) {
        NotificationEntry entry;
        int i;
        if (!this.isLowGaussianLevel) {
            ScrimControllerEx.Companion.getClass();
            if (!ScrimControllerEx.isThirdTheme && (entry = expandableNotificationRow.getEntry()) != null && entry.containsCustomViews()) {
                MaterialColorMode materialColorMode = (MaterialColorMode) this.notificationMaterialColorEx.getCurrentMaterialColor().getValue();
                Configuration configuration = context.getResources().getConfiguration();
                int i2 = configuration.uiMode & 48;
                int iOrdinal = materialColorMode.ordinal();
                if (iOrdinal != 1) {
                    i = iOrdinal == 3 ? 32 : 16;
                    return context;
                }
                if (i2 != i) {
                    Configuration configuration2 = new Configuration();
                    configuration2.setTo(configuration);
                    configuration2.uiMode = (configuration2.uiMode & (-49)) | i;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        int i3 = configuration2.uiMode;
                        String key = expandableNotificationRow.getKey();
                        StringBuilder sb = new StringBuilder("createDarkModeContext: mode=");
                        sb.append(materialColorMode);
                        sb.append(", uiMode=");
                        sb.append(i3);
                        sb.append(", key=");
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(key, "OplusResetContextFontScaleAndDensity-->", "SystemUi--Notification", sb);
                    }
                    return context.createConfigurationContext(configuration2);
                }
                return context;
            }
        }
        return context;
    }

    public Context getOplusContext(Context context) {
        Context context2 = this.oplusContext;
        return context2 == null ? context : context2;
    }

    public void reSetContextFontScaleAndDisplayDensity(Context context, ExpandableNotificationRow expandableNotificationRow) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float oplusFontScale = getOplusFontScale(configuration.fontScale);
        float oplusDisplayDensity = getOplusDisplayDensity();
        float f = configuration.fontScale;
        float f2 = displayMetrics.density;
        if (f == oplusFontScale && f2 == oplusDisplayDensity) {
            return;
        }
        configuration.fontScale = oplusFontScale;
        displayMetrics.density = oplusDisplayDensity;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String key = expandableNotificationRow.getKey();
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("reSet fontScale and density: fontScaleBase=", f, " target=", oplusFontScale, ", densityBase=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f2, " target=", oplusDisplayDensity, " ,key=");
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(key, "OplusResetContextFontScaleAndDensity-->", "SystemUi--Notification", sbM);
        }
    }

    public final void refreshDisplayDensity() {
        this.displayDensityIndex = Settings.System.getInt(this.appContext.getContentResolver(), SETTINGS_KEY, -1);
        this.appDisplayDefaultDensity = this.appContext.getResources().getDisplayMetrics().density;
        KeyguardFeatureOption.INSTANCE.getClass();
        boolean zIsScreenResolutionSwitchSupport = KeyguardFeatureOption.isScreenResolutionSwitchSupport();
        int i = this.displayDensityIndex;
        this.oplusAppDisplayDensity = (!zIsScreenResolutionSwitchSupport ? i == 2 : i == 4) ? this.appDisplayDefaultDensity : (this.appDisplayDefaultDensity / 1.125f) * 1.1f;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m(" refreshDisplayDensity: appDisplayDefaultDensity = ", this.appDisplayDefaultDensity, ", oplusAppDisplayDensity = ", this.oplusAppDisplayDensity, "OplusResetContextFontScaleAndDensity-->"));
        }
    }
}
