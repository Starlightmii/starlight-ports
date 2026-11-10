package com.oplus.systemui.shade;

import android.view.View;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface NotificationPanelNsslScaleAnimationManager {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final NotificationPanelNsslScaleAnimationManager create(Config config) {
            return new NotificationPanelNsslScaleAnimationManagerImpl(config);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Config {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ Config[] $VALUES;
        public static final Config BB_PLUS_ALPHA_SCALE;
        public static final Config LEGACY_SCALE_ONLY;

        static {
            Config config = new Config("LEGACY_SCALE_ONLY", 0);
            LEGACY_SCALE_ONLY = config;
            Config config2 = new Config("BB_PLUS_ALPHA_SCALE", 1);
            BB_PLUS_ALPHA_SCALE = config2;
            Config[] configArr = {config, config2};
            $VALUES = configArr;
            $ENTRIES = EnumEntriesKt.enumEntries(configArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static Config valueOf(String str) {
            return (Config) Enum.valueOf(Config.class, str);
        }

        public static Config[] values() {
            return (Config[]) $VALUES.clone();
        }
    }

    void cancelViewAnimation(View view, String str);

    void hide(View view, boolean z, Function1 function1, Function1 function2, Function1 function3);

    boolean isAnimationRunning(View view);

    void show(View view, boolean z, Function1 function1, Function1 function2, Function1 function3);
}
