package com.oplus.systemui.media.controls.pipeline;

import android.media.MediaMetadata;
import com.android.systemui.media.controls.models.player.MediaActionPriority;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaNotificationAction;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface MediaActionPrioritySelector {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(new MediaActionPrioritySelector$Companion$$ExternalSyntheticLambda0());

        public final MediaActionPrioritySelector getInstance() {
            return (MediaActionPrioritySelector) instance$delegate.getValue();
        }
    }

    List<OplusActionConfig> getActionConfigListforPkg(String str, MediaMetadata mediaMetadata);

    int getLyricEnable(String str);

    int getLyricEntrance(String str);

    Pair<MediaActionPriority, String> getMediaActionPriority(String str, List<MediaNotificationAction> list, MediaButton mediaButton);

    Pair<MediaActionPriority, String> getMediaActionPriorityQs(String str, List<MediaNotificationAction> list, MediaButton mediaButton);

    boolean resolveLyricSupport(String str, MediaMetadata mediaMetadata);

    boolean supportOplusActionConfig(String str);

    void updatePkgActionsRule(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, Map<String, String> map5, Map<String, String> map6);
}
