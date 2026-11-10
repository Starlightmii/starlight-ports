package com.oplus.systemui.media.controls.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.systemui.media.controls.domain.pipeline.MediaActionsKt;
import com.android.systemui.media.controls.models.player.MediaActionPriority;
import com.android.systemui.media.controls.models.player.OplusMediaActionEx;
import com.android.systemui.media.controls.models.player.OplusMediaButtonEx;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.plugins.ActivityStarter;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class MediaActionBinderKt {
    private static MediaDeviceData deviceCache;
    private static MediaAction mediaSwitchActionCache;

    public static final OplusMediaActions createMediaActionForSemanticActionsAndCustom(MediaAction mediaAction, MediaAction mediaAction2, MediaButton mediaButton) {
        MediaAction mediaAction3 = null;
        if (mediaAction2 != null && !Intrinsics.areEqual(mediaAction, mediaAction2)) {
            mediaAction3 = mediaAction;
        }
        return new OplusMediaActions(mediaAction3, mediaButton != null ? mediaButton.getPrevOrCustom() : mediaAction3, mediaButton != null ? mediaButton.getPlayOrPause() : mediaAction3, mediaButton != null ? mediaButton.getNextOrCustom() : null, mediaAction2 == null ? mediaAction : mediaAction2);
    }

    public static final OplusMediaActions createOplusMediaActions(Context context, MediaData mediaData, boolean z, ActivityStarter activityStarter, MediaOutputDialogManager mediaOutputDialogManager) {
        MediaAction mediaAction;
        OplusMediaButtonEx mediaButtonEx;
        List rule0CustomActions;
        MediaDeviceData device = mediaData.getDevice();
        MediaAction mediaAction2 = null;
        if (z && device != null && device.getEnabled()) {
            if (!device.equals(deviceCache)) {
                mediaSwitchActionCache = null;
                deviceCache = device;
            }
            Drawable iconWithoutTheme = device.getIconWithoutTheme();
            if (iconWithoutTheme == null && (iconWithoutTheme = device.getIcon()) == null) {
                iconWithoutTheme = context.getDrawable(2131237113);
            }
            Drawable drawable = iconWithoutTheme;
            CharSequence name = device.getName();
            if (name == null) {
                name = context.getString(2131954562);
            }
            CharSequence charSequence = name;
            MediaActionBinderKt$mediaSwitcherAction$targetAction$1 mediaActionBinderKt$mediaSwitcherAction$targetAction$1 = new MediaActionBinderKt$mediaSwitcherAction$targetAction$1();
            mediaActionBinderKt$mediaSwitcherAction$targetAction$1.$mediaOutputDialogManager = mediaOutputDialogManager;
            mediaActionBinderKt$mediaSwitcherAction$targetAction$1.$data = mediaData;
            VarHandle.storeStoreFence();
            mediaAction = mediaSwitchActionCache;
            if (mediaAction != null) {
                mediaAction.setIcon(drawable);
                mediaAction.setAction(mediaActionBinderKt$mediaSwitcherAction$targetAction$1);
                mediaAction.setContentDescription(charSequence);
                mediaAction.setBackground((Drawable) null);
            } else {
                MediaAction mediaAction3 = new MediaAction(drawable, mediaActionBinderKt$mediaSwitcherAction$targetAction$1, charSequence, (Drawable) null, (Integer) null, (OplusMediaActionEx) null, 48);
                mediaSwitchActionCache = mediaAction3;
                mediaAction = mediaAction3;
            }
        } else {
            mediaSwitchActionCache = null;
            mediaAction = null;
        }
        MediaActionPriority.PreferSemanticMixNotificationActions mediaActionPriority = mediaData.getMediaDataEx().getMediaActionPriority();
        OplusNotificationLogger.logMediaSeedling("MediaActionBinder", "createOplusMediaActions: mediaActionPriority=" + mediaActionPriority);
        if (mediaActionPriority instanceof MediaActionPriority.PreferNotificationActions) {
            return createPreferNotificationActions(mediaData, activityStarter, mediaAction, false);
        }
        if (mediaActionPriority instanceof MediaActionPriority.PreferSemanticActions) {
            return createMediaActionForSemanticActionsAndCustom(null, mediaAction, mediaData.getSemanticActions());
        }
        if (mediaActionPriority instanceof MediaActionPriority.PreferSemanticActionsRule0) {
            MediaButton semanticActions = mediaData.getSemanticActions();
            if (semanticActions != null && (mediaButtonEx = semanticActions.getMediaButtonEx()) != null && (rule0CustomActions = mediaButtonEx.getRule0CustomActions()) != null) {
                mediaAction2 = (MediaAction) CollectionsKt___CollectionsKt.firstOrNull(rule0CustomActions);
            }
            return createMediaActionForSemanticActionsAndCustom(mediaAction2, mediaAction, mediaData.getSemanticActions());
        }
        if (!(mediaActionPriority instanceof MediaActionPriority.PreferSemanticMixNotificationActions)) {
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
            return null;
        }
        List listNotificationActions = notificationActions(mediaData, activityStarter, mediaAction, false);
        List notificationActionIndex = mediaActionPriority.getNotificationActionIndex();
        ArrayList arrayList = new ArrayList();
        Iterator it = notificationActionIndex.iterator();
        while (it.hasNext()) {
            MediaAction mediaAction4 = (MediaAction) CollectionsKt___CollectionsKt.getOrNull(listNotificationActions, ((Number) it.next()).intValue());
            if (mediaAction4 != null) {
                arrayList.add(mediaAction4);
            }
        }
        return createMediaActionForSemanticActionsAndCustom((MediaAction) CollectionsKt___CollectionsKt.getOrNull(arrayList, 0), mediaAction, mediaData.getSemanticActions());
    }

    public static final OplusMediaActions createPreferNotificationActions(MediaData mediaData, ActivityStarter activityStarter, MediaAction mediaAction, boolean z) {
        List listNotificationActions = notificationActions(mediaData, activityStarter, mediaAction, z);
        Iterator it = listNotificationActions.iterator();
        int size = ((ArrayList) listNotificationActions).size();
        if (size == 0) {
            return new OplusMediaActions(null, null, null, null, null, 31);
        }
        if (size == 1) {
            return new OplusMediaActions((MediaAction) it.next(), null, null, null, null, 30);
        }
        if (size == 2) {
            return new OplusMediaActions((MediaAction) it.next(), (MediaAction) it.next(), null, null, null, 28);
        }
        if (size == 3) {
            return z ? new OplusMediaActions(null, (MediaAction) it.next(), (MediaAction) it.next(), (MediaAction) it.next(), null, 17) : new OplusMediaActions(null, null, (MediaAction) it.next(), (MediaAction) it.next(), (MediaAction) it.next(), 3);
        }
        if (size != 4) {
            return new OplusMediaActions((MediaAction) it.next(), (MediaAction) it.next(), (MediaAction) it.next(), (MediaAction) it.next(), (MediaAction) it.next());
        }
        if (z) {
            return new OplusMediaActions(null, (MediaAction) it.next(), (MediaAction) it.next(), (MediaAction) it.next(), (MediaAction) it.next(), 1);
        }
        return new OplusMediaActions(null, (MediaAction) it.next(), (MediaAction) it.next(), (MediaAction) it.next(), (MediaAction) it.next(), 1);
    }

    public static final OplusMediaActions createQSMediaActions(MediaData mediaData, ActivityStarter activityStarter) {
        OplusMediaButtonEx mediaButtonEx;
        MediaActionPriority.PreferSemanticMixNotificationActions mediaActionPriorityQs = mediaData.getMediaDataEx().getMediaActionPriorityQs();
        OplusNotificationLogger.logMediaSeedling("MediaActionBinder", "createQSMediaActions: mediaActionPriority=" + mediaActionPriorityQs);
        if (mediaActionPriorityQs instanceof MediaActionPriority.PreferNotificationActions) {
            return createPreferNotificationActions(mediaData, activityStarter, null, true);
        }
        if (mediaActionPriorityQs instanceof MediaActionPriority.PreferSemanticActions) {
            MediaButton semanticActions = mediaData.getSemanticActions();
            return createMediaActionForSemanticActionsAndCustom(semanticActions != null ? semanticActions.getCustom0() : null, semanticActions != null ? semanticActions.getCustom1() : null, semanticActions);
        }
        if (mediaActionPriorityQs instanceof MediaActionPriority.PreferSemanticActionsRule0) {
            MediaButton semanticActions2 = mediaData.getSemanticActions();
            List rule0CustomActions = (semanticActions2 == null || (mediaButtonEx = semanticActions2.getMediaButtonEx()) == null) ? null : mediaButtonEx.getRule0CustomActions();
            if (rule0CustomActions == null) {
                return new OplusMediaActions(null, semanticActions2 != null ? semanticActions2.getPrevOrCustom() : null, semanticActions2 != null ? semanticActions2.getPlayOrPause() : null, semanticActions2 != null ? semanticActions2.getNextOrCustom() : null, null);
            }
            Iterator it = rule0CustomActions.iterator();
            return new OplusMediaActions(it.hasNext() ? (MediaAction) it.next() : null, semanticActions2.getPrevOrCustom(), semanticActions2.getPlayOrPause(), semanticActions2.getNextOrCustom(), it.hasNext() ? (MediaAction) it.next() : null);
        }
        if (!(mediaActionPriorityQs instanceof MediaActionPriority.PreferSemanticMixNotificationActions)) {
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
            return null;
        }
        List listNotificationActions = notificationActions(mediaData, activityStarter, null, true);
        List notificationActionIndex = mediaActionPriorityQs.getNotificationActionIndex();
        ArrayList arrayList = new ArrayList();
        Iterator it2 = notificationActionIndex.iterator();
        while (it2.hasNext()) {
            MediaAction mediaAction = (MediaAction) CollectionsKt___CollectionsKt.getOrNull(listNotificationActions, ((Number) it2.next()).intValue());
            if (mediaAction != null) {
                arrayList.add(mediaAction);
            }
        }
        return createMediaActionForSemanticActionsAndCustom((MediaAction) CollectionsKt___CollectionsKt.getOrNull(arrayList, 0), (MediaAction) CollectionsKt___CollectionsKt.getOrNull(arrayList, 1), mediaData.getSemanticActions());
    }

    public static final List notificationActions(MediaData mediaData, ActivityStarter activityStarter, MediaAction mediaAction, boolean z) {
        ArrayList arrayList = new ArrayList();
        List notificationActions = MediaActionsKt.getNotificationActions(mediaData.getActions(), activityStarter);
        int size = notificationActions.size();
        int i = z ? 5 : 4;
        if (size > i) {
            size = i;
        }
        arrayList.addAll(notificationActions.subList(0, size));
        if (mediaAction != null) {
            arrayList.add(mediaAction);
        }
        return arrayList;
    }

    public static final void releaseCache() {
        OplusNotificationLogger.logMediaSeedling("MediaActionBinder", "releaseCache: mediaSwitchActionCache=" + mediaSwitchActionCache);
        mediaSwitchActionCache = null;
        deviceCache = null;
    }
}
