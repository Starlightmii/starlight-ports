package com.oplus.systemui.util;

import com.google.gson.GsonBuilder;
import com.oplus.keyguard.config.KeyguardStyleConfig;
import com.oplus.keyguard.config.KeyguardStyleConfigTypeAdapter;
import com.oplus.systemui.keyguard.data.personalise.PersonalityData;
import com.oplus.systemui.keyguard.data.personalise.PersonalityDataTypeAdapter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SettingsStyleDataUtil$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;

    public final Object invoke(Object obj) {
        GsonBuilder gsonBuilder = (GsonBuilder) obj;
        switch (this.$r8$classId) {
            case 0:
                gsonBuilder.registerTypeAdapter(new PersonalityDataTypeAdapter(), PersonalityData.class);
                break;
            case 1:
                gsonBuilder.registerTypeAdapter(new PersonalityDataTypeAdapter(), PersonalityData.class);
                break;
            case 2:
                gsonBuilder.registerTypeAdapter(new PersonalityDataTypeAdapter(), PersonalityData.class);
                break;
            case 3:
                gsonBuilder.registerTypeAdapter(new PersonalityDataTypeAdapter(), PersonalityData.class);
                break;
            default:
                gsonBuilder.registerTypeAdapter(new KeyguardStyleConfigTypeAdapter((KeyguardStyleConfig) null), KeyguardStyleConfig.class);
                break;
        }
        return Unit.INSTANCE;
    }
}
