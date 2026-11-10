package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.appcompat.widget.ListPopupWindow$;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class GameModeObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final String IN_GAME_MODE = "debug_gamemode_value";
    private static final String TAG = "GameModeObserver";
    public boolean isInGameMode;
    private final GameModeObserver$miniGameModeObserver$1 miniGameModeObserver = new MiniGameModeObserver() { // from class: com.oplus.systemui.statusbar.observer.GameModeObserver$miniGameModeObserver$1
        @Override // com.oplus.systemui.statusbar.observer.MiniGameModeObserver, com.oplusos.systemui.common.observer.AbstractObserver
        public final void onChange(boolean z) {
            super.onChange(z);
            this.this$0.updateGameModeState();
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return "debug_gamemode_value";
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onChange(boolean z) {
        updateGameModeState();
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        register(context, false);
        updateGameModeState();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void unregister(Context context) {
        unregister(context);
        super.unregister(context);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0038  */
    /* JADX WARN: Code duplicated, block: B:18:0x003f  */
    public final void updateGameModeState() {
        int i;
        Context context = getContext();
        boolean z = true;
        if (context != null) {
            String globalStringValue = MultiUserContentHelper.Companion.getGlobalStringValue(context, "debug_gamemode_value");
            if (TextUtils.isEmpty(globalStringValue) || globalStringValue == null) {
                i = 0;
            } else {
                try {
                    i = Integer.parseInt(globalStringValue);
                } catch (NumberFormatException unused) {
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "fail to get game mode setting", null, 8);
                    i = 0;
                }
            }
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "Notification--gameMode "), null, 8);
            if ((i & 1) == 0) {
                if (!this.miniGameModeObserver.isInMiniGameMode) {
                    z = false;
                }
            }
        } else if (!this.miniGameModeObserver.isInMiniGameMode) {
            z = false;
        }
        this.isInGameMode = z;
    }
}
