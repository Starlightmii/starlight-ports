package com.oplus.systemui.media.controls.pipeline;

import android.graphics.drawable.Icon;
import android.media.MediaMetadata;
import android.media.session.PlaybackState;
import android.service.notification.StatusBarNotification;
import androidx.exifinterface.media.ExifInterface$;
import com.android.internal.logging.InstanceId;
import com.android.systemui.media.controls.domain.pipeline.MediaActionsKt;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.models.player.OplusMediaDataEx;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.shared.model.SuggestionData;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1 implements Runnable {
    public /* synthetic */ String $key;
    public /* synthetic */ Object $metaData;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusMediaDataManagerExImpl this$0;

    public /* synthetic */ OplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Boolean boolValueOf;
        List actions;
        switch (this.$r8$classId) {
            case 0:
                this.this$0.onMetaDataChanged(this.$key, (MediaMetadata) this.$metaData);
                break;
            case 1:
                Pair pairCreateActionsFromNotification = MediaActionsKt.createActionsFromNotification(this.this$0.context, (StatusBarNotification) this.$metaData);
                List list = (List) pairCreateActionsFromNotification.getFirst();
                List list2 = (List) pairCreateActionsFromNotification.getSecond();
                String str = this.$key;
                String packageName = ((StatusBarNotification) this.$metaData).getPackageName();
                int size = list.size();
                int size2 = list2.size();
                MediaData mediaData = this.this$0.getMediaDataManager().getMediaData(this.$key);
                boolValueOf = mediaData != null ? Boolean.valueOf(mediaData.getInitialized()) : null;
                StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("createActionsFromNotification media --key=", str, ", packageName=", packageName, ", actions(");
                sbM.append(size);
                sbM.append(")=");
                sbM.append(list);
                sbM.append(", actionsToShowCollapsed(");
                sbM.append(size2);
                sbM.append(")=");
                sbM.append(list2);
                sbM.append(", initialized=");
                sbM.append(boolValueOf);
                OplusNotificationLogger.logMediaPanelNormal(OplusMediaDataManagerExImpl.TAG, sbM.toString());
                ExecutorImpl executorImpl = this.this$0.foregroundExecutor;
                OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl = this.this$0;
                String str2 = this.$key;
                StatusBarNotification statusBarNotification = (StatusBarNotification) this.$metaData;
                OplusMediaDataManagerExImpl$createActionsFromNotification$1$1 oplusMediaDataManagerExImpl$createActionsFromNotification$1$1 = new OplusMediaDataManagerExImpl$createActionsFromNotification$1$1();
                oplusMediaDataManagerExImpl$createActionsFromNotification$1$1.this$0 = oplusMediaDataManagerExImpl;
                oplusMediaDataManagerExImpl$createActionsFromNotification$1$1.$key = str2;
                oplusMediaDataManagerExImpl$createActionsFromNotification$1$1.$sbn = statusBarNotification;
                oplusMediaDataManagerExImpl$createActionsFromNotification$1$1.$actionIcons = list;
                oplusMediaDataManagerExImpl$createActionsFromNotification$1$1.$actionsToShowCollapsed = list2;
                VarHandle.storeStoreFence();
                executorImpl.execute(oplusMediaDataManagerExImpl$createActionsFromNotification$1$1);
                break;
            case 2:
                MediaData mediaData2 = this.this$0.getMediaDataManager().getMediaData(this.$key);
                boolValueOf = mediaData2 != null ? MediaData.copy$default(mediaData2, 0, (CharSequence) null, (CharSequence) null, (Icon) null, CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList(), (MediaButton) null, (String) null, ((StatusBarNotification) this.$metaData).getNotification().contentIntent, (MediaDeviceData) null, (SuggestionData) null, (Runnable) null, (String) null, (Boolean) null, 0L, 0L, (InstanceId) null, 0, OplusMediaDataEx.copy$default(mediaData2.getMediaDataEx(), (MediaMetadata) null, (PlaybackState) null, 0, 0L, false, 2047), 1073737343) : null;
                MediaData mediaData3 = this.this$0.getMediaDataManager().getMediaData(this.$key);
                boolean z = false;
                if (mediaData3 != null && ((StatusBarNotification) this.$metaData).getNormalizedUserId() == mediaData3.getUserId()) {
                    z = true;
                }
                if (boolValueOf != null && boolValueOf.getInitialized() && z) {
                    boolValueOf.getMediaDataEx().setNoUpdateTime(true);
                    MediaDataManager mediaDataManager = this.this$0.getMediaDataManager();
                    String str3 = this.$key;
                    mediaDataManager.onMediaDataLoaded(str3, str3, boolValueOf, "createActionsFromNotification---2");
                    break;
                }
                break;
            default:
                MediaData mediaData4 = this.this$0.getMediaDataManager().getMediaData(this.$key);
                Integer numValueOf = (mediaData4 == null || (actions = mediaData4.getActions()) == null) ? null : Integer.valueOf(actions.size());
                if (numValueOf != null && numValueOf.intValue() > 0) {
                    String str4 = this.$key;
                    String packageName2 = ((StatusBarNotification) this.$metaData).getPackageName();
                    MediaData mediaData5 = this.this$0.getMediaDataManager().getMediaData(this.$key);
                    boolValueOf = mediaData5 != null ? Boolean.valueOf(mediaData5.getInitialized()) : null;
                    StringBuilder sbM2 = ExifInterface$.ExternalSyntheticOutline1.m("createActionsFromNotification media --key=", str4, ", packageName=", packageName2, ",  isMediaNotification from media framework, clear actions and actionsToShowInCompact,  initialized=");
                    sbM2.append(boolValueOf);
                    OplusNotificationLogger.logMediaPanelNormal(OplusMediaDataManagerExImpl.TAG, sbM2.toString());
                    ExecutorImpl executorImpl2 = this.this$0.foregroundExecutor;
                    OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl2 = this.this$0;
                    String str5 = this.$key;
                    StatusBarNotification statusBarNotification2 = (StatusBarNotification) this.$metaData;
                    OplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1 oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1 = new OplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1(2);
                    oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1.this$0 = oplusMediaDataManagerExImpl2;
                    oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1.$key = str5;
                    oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1.$metaData = statusBarNotification2;
                    VarHandle.storeStoreFence();
                    executorImpl2.execute(oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1);
                    break;
                }
                break;
        }
    }
}
