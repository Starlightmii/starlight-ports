package com.oplus.systemui.volume;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.coui.appcompat.snackbar.COUISnackBar;
import com.oplus.systemui.statusbar.observer.GameModeObserver;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeSnackBarController {
    private static final String ACTION_NOTIFICATIONMANAGER_PACKAGE_NAME = "com.oplus.notificationmanager";
    private static final String ACTION_SETTINGS_PACKAGE_NAME = "com.android.settings";
    private static final String ACTION_VOLUME_AND_VIBRATE_SETTINGS = "com.android.settings.SOUND_SETTINGS";
    private static final String ACTION_ZEN_MODE_SETTINGS = "android.settings.ZEN_MODE_SETTINGS";
    private static final String SETTING_FRAGMENT_ARGS_KEY = ":settings:fragment_args_key";
    private static final String SETTING_VOLUME_HAS_SHOWN_GUIDE = "volume_has_shown_guide";
    private static final String TAG = "OplusVolumeSnackBarController";
    private static OplusVolumeSnackBarController mInstance;
    private final Context context;
    public final ObserverDispatcher dispatcher;
    public boolean isGameMode;
    public boolean isInBootGuide;
    public final boolean isTopVolumeKey;
    public boolean isVolumeChangedAndFromKey;
    private final OplusVolumeSnackBarController$mGameModeObserver$1 mGameModeObserver;
    public boolean mHasShownVolumeGuide;
    public boolean mNeedShowSnackBar;
    private final ContentObserver mSettingsObserver;
    public int mSnackerShowCounter;
    public boolean mVolumeAdaptation;
    public int userId;
    public static final Companion Companion = new Companion();
    private static final String SETTING_VOLUME_ORIENTATION_ADAPTATION = "volume_key_adaptive";
    public static final Uri VOLUME_ADAPTATION_URI = Settings.System.getUriFor(SETTING_VOLUME_ORIENTATION_ADAPTATION);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusVolumeSnackBarController getInstance(Context context) {
            OplusVolumeSnackBarController oplusVolumeSnackBarController;
            OplusVolumeSnackBarController oplusVolumeSnackBarController2 = OplusVolumeSnackBarController.mInstance;
            if (oplusVolumeSnackBarController2 != null) {
                return oplusVolumeSnackBarController2;
            }
            synchronized (this) {
                oplusVolumeSnackBarController = OplusVolumeSnackBarController.mInstance;
                if (oplusVolumeSnackBarController == null) {
                    oplusVolumeSnackBarController = new OplusVolumeSnackBarController(context.getApplicationContext());
                    OplusVolumeSnackBarController.mInstance = oplusVolumeSnackBarController;
                }
            }
            return oplusVolumeSnackBarController;
        }
    }

    /* JADX WARN: Type inference failed for: r1v14, types: [com.oplus.systemui.volume.OplusVolumeSnackBarController$mGameModeObserver$1, com.oplusos.systemui.common.observer.AbstractObserver] */
    public OplusVolumeSnackBarController(Context context) {
        this.context = context;
        ObserverDispatcher observerDispatcher = ObserverDispatcher.getInstance();
        this.dispatcher = observerDispatcher;
        this.mVolumeAdaptation = getSettingsValue(SETTING_VOLUME_ORIENTATION_ADAPTATION) != 0;
        this.mHasShownVolumeGuide = getSettingsValue(SETTING_VOLUME_HAS_SHOWN_GUIDE) != 0;
        FeatureOption.INSTANCE.getClass();
        this.isTopVolumeKey = FeatureOption.isOplusTopVolumeKey();
        this.userId = context.getUserId();
        this.mNeedShowSnackBar = true;
        this.isInBootGuide = Settings.Secure.getIntForUser(context.getContentResolver(), "user_setup_complete", 0, this.userId) != 1;
        final Handler handler = new Handler(Looper.getMainLooper());
        ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.volume.OplusVolumeSnackBarController$mSettingsObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri) {
                if (Intrinsics.areEqual(uri, OplusVolumeSnackBarController.VOLUME_ADAPTATION_URI)) {
                    OplusVolumeSnackBarController oplusVolumeSnackBarController = this.this$0;
                    oplusVolumeSnackBarController.mVolumeAdaptation = oplusVolumeSnackBarController.getSettingsValue("volume_key_adaptive") != 0;
                    this.this$0.setSettingsValue();
                    this.this$0.mHasShownVolumeGuide = true;
                }
            }
        };
        this.mSettingsObserver = contentObserver;
        ?? r1 = new GameModeObserver() { // from class: com.oplus.systemui.volume.OplusVolumeSnackBarController$mGameModeObserver$1
            @Override // com.oplus.systemui.statusbar.observer.GameModeObserver, com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                this.this$0.isGameMode = this.isInGameMode;
            }
        };
        this.mGameModeObserver = r1;
        observerDispatcher.registerObserver(VOLUME_ADAPTATION_URI, contentObserver, this.userId);
        r1.register(context, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Intent getVolumeSettingIntent() {
        Intent intent = new Intent();
        intent.setAction(ACTION_VOLUME_AND_VIBRATE_SETTINGS);
        intent.setPackage(ACTION_SETTINGS_PACKAGE_NAME);
        intent.putExtra(SETTING_FRAGMENT_ARGS_KEY, SETTING_VOLUME_ORIENTATION_ADAPTATION);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Intent getZenModeIntent() {
        Intent intent = new Intent();
        intent.setAction(ACTION_ZEN_MODE_SETTINGS);
        intent.setPackage(ACTION_NOTIFICATIONMANAGER_PACKAGE_NAME);
        return intent;
    }

    public final int getSettingsValue(String str) {
        return Settings.System.getIntForUser(this.context.getContentResolver(), str, 0, this.userId);
    }

    public final void onUserSwitchChange() {
        this.userId = ActivityManager.getCurrentUser();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.userId, "onUserSwitched, userId=", "OplusVolumeSnackBarController-->", "SystemUi--Volume");
        }
        ContentObserver contentObserver = this.mSettingsObserver;
        ObserverDispatcher observerDispatcher = this.dispatcher;
        observerDispatcher.unregisterObserver(contentObserver);
        observerDispatcher.registerObserver(VOLUME_ADAPTATION_URI, this.mSettingsObserver, this.userId);
        this.mVolumeAdaptation = getSettingsValue(SETTING_VOLUME_ORIENTATION_ADAPTATION) != 0;
        this.mHasShownVolumeGuide = getSettingsValue(SETTING_VOLUME_HAS_SHOWN_GUIDE) != 0;
    }

    public final void setSettingsValue() {
        Settings.System.putIntForUser(this.context.getContentResolver(), SETTING_VOLUME_HAS_SHOWN_GUIDE, 1, this.userId);
    }

    public final void setVolumeChangedAndFromKey(boolean z) {
        this.isVolumeChangedAndFromKey = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void showSnackBar(COUISnackBar cOUISnackBar, VolumeDialogController.State state, KeyguardStateController keyguardStateController) {
        Object[] objArr = 0;
        int i = 1;
        if (state != null && state.zenMode == 1 && state.disallowMedia && !((KeyguardStateControllerImpl) keyguardStateController).mShowing) {
            int i2 = this.mSnackerShowCounter + 1;
            this.mSnackerShowCounter = i2;
            if (i2 == 4) {
                this.mNeedShowSnackBar = false;
            }
            if (!this.mNeedShowSnackBar || cOUISnackBar == null) {
                return;
            }
            cOUISnackBar.setContentText(cOUISnackBar.getContext().getString(2131957189));
            String string = cOUISnackBar.getContext().getString(2131953416);
            OplusVolumeSnackBarController$showSnackBar$1$1 oplusVolumeSnackBarController$showSnackBar$1$1 = new OplusVolumeSnackBarController$showSnackBar$1$1(objArr == true ? 1 : 0);
            oplusVolumeSnackBarController$showSnackBar$1$1.this$0 = this;
            VarHandle.storeStoreFence();
            cOUISnackBar.setOnAction(string, oplusVolumeSnackBarController$showSnackBar$1$1);
            cOUISnackBar.show();
            return;
        }
        boolean normalDebug = LogUtil.getNormalDebug();
        boolean z = this.isTopVolumeKey;
        if (normalDebug && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            boolean z2 = this.isVolumeChangedAndFromKey;
            boolean z3 = this.mVolumeAdaptation;
            boolean z4 = this.mHasShownVolumeGuide;
            boolean z5 = this.isGameMode;
            boolean z6 = this.isInBootGuide;
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("needShowGuideSnackBar isVolumeChangedAndFromKey=", " isTopVolumeKey=", " mVolumeAdaptation=", z2, z);
            KeyguardLogger$.ExternalSyntheticOutline0.m(" mHasShownVolumeGuide=", " isGameMode=", sbM, z3, z4);
            Log.d("SystemUi--Volume", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" isInBootGuide=", "OplusVolumeSnackBarController-->", sbM, z5, z6));
        }
        if (!this.isVolumeChangedAndFromKey || !z || this.mHasShownVolumeGuide) {
            this.isVolumeChangedAndFromKey = false;
            return;
        }
        boolean z7 = Settings.Secure.getIntForUser(this.context.getContentResolver(), "user_setup_complete", 0, this.userId) != 1;
        this.isInBootGuide = z7;
        if (this.mVolumeAdaptation || this.isGameMode || z7 || cOUISnackBar == null) {
            return;
        }
        cOUISnackBar.setContentText(cOUISnackBar.getContext().getString(2131957219));
        showSnackBar.2.1 r11 = new showSnackBar.2.1();
        r11.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISnackBar.setOnStatusChangeListener(r11);
        String string2 = cOUISnackBar.getContext().getString(2131957187);
        OplusVolumeSnackBarController$showSnackBar$1$1 oplusVolumeSnackBarController$showSnackBar$1$2 = new OplusVolumeSnackBarController$showSnackBar$1$1(i);
        oplusVolumeSnackBarController$showSnackBar$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISnackBar.setOnAction(string2, oplusVolumeSnackBarController$showSnackBar$1$2);
        cOUISnackBar.show();
    }
}
