package com.oplus.systemui.notification.notificationcenter.antivoyeur;

import android.content.Context;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.systemui.notification.aon.AonEnableObserver;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AntiVoyeurConfig {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public static final Companion Companion;
    private static final String FACE_APP_ENCRYPT_SWITCH = "oplus_customize_face_unlock_app_encryption_switch";
    private static final String FACE_ENROLL_STATE = "key_face_templates_enrolled";
    private static final String IS_AON_ANTI_VOYEUR_ENABLE_STRING = "oplus.hardware.aon_enable";
    private static final String SMART_ANTI_VOYEUR_ENCRYPTED_APP_ENABLE = "smart_anti_voyeur_encrypted_app_enable";
    private static final String SUPER_SAVE_POWER_MODE_STATE = "super_powersave_mode_state";
    private final IntStateProxy allowAntiVoyeurEncryptedAppState$delegate;
    private final IntStateProxy allowAntiVoyeurEncryptedState$delegate;
    public final boolean aonAntiEnable = OplusFeatureConfigManager.getInstance().hasFeature(IS_AON_ANTI_VOYEUR_ENABLE_STRING);
    private final Context context;
    private final IntStateProxy faceEnrollState$delegate;
    private final IntStateProxy faceForAppEncryptState$delegate;
    private final IntStateProxy superSavePowerModeState$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(AntiVoyeurConfig.class, "allowAntiVoyeurEncryptedAppState", "getAllowAntiVoyeurEncryptedAppState()Z", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl, new MutablePropertyReference1Impl(AntiVoyeurConfig.class, "allowAntiVoyeurEncryptedState", "getAllowAntiVoyeurEncryptedState()Z", 0), new PropertyReference1Impl(AntiVoyeurConfig.class, "faceEnrollState", "getFaceEnrollState()Z", 0), new PropertyReference1Impl(AntiVoyeurConfig.class, "faceForAppEncryptState", "getFaceForAppEncryptState()Z", 0), new PropertyReference1Impl(AntiVoyeurConfig.class, "superSavePowerModeState", "getSuperSavePowerModeState()Z", 0)};
        Companion = new Companion();
    }

    public AntiVoyeurConfig(Context context) {
        this.context = context;
        this.allowAntiVoyeurEncryptedAppState$delegate = new IntStateProxy(context, SMART_ANTI_VOYEUR_ENCRYPTED_APP_ENABLE, false);
        this.allowAntiVoyeurEncryptedState$delegate = new IntStateProxy(context, AonEnableObserver.SMART_ANTI_VOYEUR_ENABLE, false);
        this.faceEnrollState$delegate = new IntStateProxy(context, FACE_ENROLL_STATE, false);
        this.faceForAppEncryptState$delegate = new IntStateProxy(context, FACE_APP_ENCRYPT_SWITCH, false);
        this.superSavePowerModeState$delegate = new IntStateProxy(context, "super_powersave_mode_state", false);
    }

    public final boolean getAllowAntiVoyeurEncryptedAppState() {
        return this.allowAntiVoyeurEncryptedAppState$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public final boolean getAllowAntiVoyeurEncryptedState() {
        return this.allowAntiVoyeurEncryptedState$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final boolean getAonAntiEnable() {
        return this.aonAntiEnable;
    }

    public final boolean getFaceEnrollState() {
        return this.faceEnrollState$delegate.getValue(this, $$delegatedProperties[2]);
    }

    public final boolean getFaceForAppEncryptState() {
        return this.faceForAppEncryptState$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public final boolean getSuperSavePowerModeState() {
        return this.superSavePowerModeState$delegate.getValue(this, $$delegatedProperties[4]);
    }

    public final void setAllowAntiVoyeurEncryptedAppState(boolean z) {
        this.allowAntiVoyeurEncryptedAppState$delegate.setValue(this, $$delegatedProperties[0], z);
    }

    public final void setAllowAntiVoyeurEncryptedState(boolean z) {
        this.allowAntiVoyeurEncryptedState$delegate.setValue(this, $$delegatedProperties[1], z);
    }
}
