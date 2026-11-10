package com.oplus.systemui.media.controls.pipeline;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.media.MediaMetadata;
import android.media.session.PlaybackState;
import com.android.internal.logging.InstanceId;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.models.player.OplusMediaDataEx;
import com.android.systemui.media.controls.models.player.OplusMediaLyricData;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.shared.model.SuggestionData;
import java.util.List;
import kotlin.Triple;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDataManagerExImpl$onMetaDataChanged$1$2 implements Runnable {
    public /* synthetic */ Ref.ObjectRef $heartAction;
    public /* synthetic */ String $key;
    public /* synthetic */ OplusMediaLyricData $lyric;
    public /* synthetic */ MediaMetadata $metaData;
    public /* synthetic */ Icon $newArtworkIcon;
    public /* synthetic */ Triple $songData;
    public /* synthetic */ OplusMediaDataManagerExImpl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        CharSequence artist;
        CharSequence song;
        MediaButton semanticActions;
        MediaData mediaData = this.this$0.getMediaDataManager().getMediaData(this.$key);
        if (mediaData == null) {
            return;
        }
        MediaButton semanticActions2 = mediaData.getSemanticActions();
        MediaAction mediaAction = (MediaAction) this.$heartAction.element;
        if (mediaAction != null && (semanticActions = mediaData.getSemanticActions()) != null) {
            semanticActions2 = MediaButton.copy$default(semanticActions, semanticActions.getMediaButtonEx().updateHeartAction(mediaAction));
        }
        MediaButton mediaButton = semanticActions2;
        Triple triple = this.$songData;
        if (triple == null || (artist = (String) triple.getFirst()) == null) {
            artist = mediaData.getArtist();
        }
        CharSequence charSequence = artist;
        Triple triple2 = this.$songData;
        if (triple2 == null || (song = (String) triple2.getSecond()) == null) {
            song = mediaData.getSong();
        }
        CharSequence charSequence2 = song;
        Icon artwork = this.$newArtworkIcon;
        if (artwork == null) {
            artwork = mediaData.getArtwork();
        }
        Icon icon = artwork;
        OplusMediaDataEx mediaDataEx = mediaData.getMediaDataEx();
        Triple triple3 = this.$songData;
        MediaData mediaDataCopy$default = MediaData.copy$default(mediaData, 0, charSequence, charSequence2, icon, (List) null, (List) null, mediaButton, (String) null, (PendingIntent) null, (MediaDeviceData) null, (SuggestionData) null, (Runnable) null, (String) null, (Boolean) null, 0L, 0L, (InstanceId) null, 0, OplusMediaDataEx.copy$default(mediaDataEx, this.$metaData, (PlaybackState) null, 0, triple3 != null ? ((Number) triple3.getThird()).longValue() : mediaData.getMediaDataEx().getDuration(), ((MediaActionPrioritySelectorImpl) this.this$0.getMediaActionPrioritySelector()).resolveLyricSupport(mediaData.getPackageName(), this.$metaData), 891), 1073741199);
        OplusMediaLyricData oplusMediaLyricData = this.$lyric;
        if (oplusMediaLyricData != null) {
            mediaDataCopy$default.getMediaDataEx().setLyricData(oplusMediaLyricData.updateIfNeed(mediaData.getMediaDataEx().getLyricData()));
        }
        MediaDataManager mediaDataManager = this.this$0.getMediaDataManager();
        String str = this.$key;
        mediaDataManager.onMediaDataLoaded(str, str, mediaDataCopy$default, "onMetaDataChanged");
    }
}
