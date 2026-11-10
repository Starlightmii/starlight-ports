package com.oplus.systemui.media.controls.pipeline;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.media.controls.models.player.OplusMediaActionEx;
import com.android.systemui.media.controls.models.player.OplusMediaButtonEx;
import com.android.systemui.media.controls.models.player.OplusMediaDataEx;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.oplus.systemui.notification.mediapanel.util.AodMediaStatisticUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusMediaDataManagerStrategy implements BaseMediaDataManagerStrategy {
    private static final String ACTION_PLACEHOLDER = "placeholder";
    public static final Companion Companion = new Companion();
    private static final String DISABLE_PREBUTTON_PACKAGENAME = "tv.danmaku.bili";
    private static final String OPLUS_CUSTOM_ACTION_ICON_KEY = "icon";
    private static final String OPLUS_CUSTOM_ACTION_KEY = "showOnSystemUiMediaControlPanel";
    private static final String TAG = "OplusMediaDataManagerSt";
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.media.controls.pipeline.OplusMediaDataManagerStrategy$getStandardAction$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ MediaController $controller;
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    OplusNotificationLogger.logAodMediaPanel(OplusMediaDataManagerStrategy.TAG, "click media panel resume song");
                    this.$controller.getTransportControls().play();
                    break;
                case 1:
                    OplusNotificationLogger.logAodMediaPanel(OplusMediaDataManagerStrategy.TAG, "click media panel resume song when state is buffering");
                    this.$controller.getTransportControls().play();
                    break;
                case 2:
                    OplusNotificationLogger.logAodMediaPanel(OplusMediaDataManagerStrategy.TAG, "click media panel pause song");
                    this.$controller.getTransportControls().pause();
                    break;
                case 3:
                    OplusNotificationLogger.logAodMediaPanel(OplusMediaDataManagerStrategy.TAG, "click media panel pre song");
                    this.$controller.getTransportControls().skipToPrevious();
                    break;
                default:
                    OplusNotificationLogger.logAodMediaPanel(OplusMediaDataManagerStrategy.TAG, "click media panel next song");
                    this.$controller.getTransportControls().skipToNext();
                    break;
            }
        }
    }

    public OplusMediaDataManagerStrategy(Context context) {
        this.context = context;
    }

    public static final MediaAction createActionsFromState$lambda$4$nextCustomAction(Iterator it) {
        if (it.hasNext()) {
            return (MediaAction) it.next();
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:113:0x033e  */
    /* JADX WARN: Code duplicated, block: B:116:0x0348  */
    /* JADX WARN: Code duplicated, block: B:119:0x036b  */
    /* JADX WARN: Code duplicated, block: B:121:0x0374  */
    /* JADX WARN: Code duplicated, block: B:123:0x037a  */
    /* JADX WARN: Code duplicated, block: B:126:0x0382  */
    /* JADX WARN: Code duplicated, block: B:129:0x038c  */
    /* JADX WARN: Code duplicated, block: B:131:0x038f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:132:0x0391  */
    /* JADX WARN: Code duplicated, block: B:133:0x0395 A[PHI: r12
      0x0395: PHI (r12v1 com.android.systemui.media.controls.shared.model.MediaAction) = 
      (r12v0 com.android.systemui.media.controls.shared.model.MediaAction)
      (r12v3 com.android.systemui.media.controls.shared.model.MediaAction)
     binds: [B:130:0x038d, B:132:0x0391] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:134:0x0397  */
    /* JADX WARN: Code duplicated, block: B:136:0x039b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:137:0x039d  */
    /* JADX WARN: Code duplicated, block: B:138:0x03a2  */
    /* JADX WARN: Code duplicated, block: B:141:0x03ce  */
    /* JADX WARN: Code duplicated, block: B:144:0x03db  */
    /* JADX WARN: Code duplicated, block: B:146:0x03f2  */
    /* JADX WARN: Code duplicated, block: B:148:0x03fc  */
    /* JADX WARN: Code duplicated, block: B:150:0x0404  */
    /* JADX WARN: Code duplicated, block: B:152:0x0412  */
    /* JADX WARN: Code duplicated, block: B:155:0x042d A[LOOP:3: B:151:0x0410->B:155:0x042d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:159:0x043a  */
    /* JADX WARN: Code duplicated, block: B:161:0x0445  */
    /* JADX WARN: Code duplicated, block: B:163:0x044d  */
    /* JADX WARN: Code duplicated, block: B:164:0x0456  */
    /* JADX WARN: Code duplicated, block: B:166:0x045b  */
    /* JADX WARN: Code duplicated, block: B:169:0x0463  */
    /* JADX WARN: Code duplicated, block: B:171:0x0469  */
    /* JADX WARN: Code duplicated, block: B:177:0x049d  */
    /* JADX WARN: Code duplicated, block: B:182:0x04bd  */
    /* JADX WARN: Code duplicated, block: B:199:0x0432 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:200:0x0434 A[EDGE_INSN: B:200:0x0434->B:157:0x0434 BREAK  A[LOOP:3: B:151:0x0410->B:155:0x042d], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:202:0x04b0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:204:0x049b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x012d A[LOOP:0: B:25:0x012b->B:26:0x012d, LOOP_END] */
    /* JADX WARN: Instruction removed from duplicated block: B:116:0x0348, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.media.controls.pipeline.BaseMediaDataManagerStrategy
    public MediaButton createActionsFromState(String str, MediaData mediaData, MediaController mediaController) {
        MediaAction standardAction;
        MediaAction mediaAction;
        MediaController mediaController2;
        MediaAction standardAction2;
        MediaAction standardAction3;
        ArrayList arrayList;
        ArrayList arrayList2;
        int i;
        MediaAction mediaAction2;
        int i2;
        boolean z;
        Iterator it;
        Bundle extras;
        boolean z2;
        boolean z3;
        Bundle extras2;
        int i3;
        MediaAction mediaAction3;
        MediaAction mediaAction4;
        ArrayList arrayList3;
        List<OplusActionConfig> actionConfigListforPkg;
        ArrayList arrayList4;
        ArrayList arrayList5;
        int size;
        int i4;
        Object obj;
        Iterator it2;
        OplusActionConfig oplusActionConfig;
        MediaAction mediaAction5;
        int size2;
        int i5;
        Object obj2;
        MediaAction mediaAction6;
        Ref.ObjectRef objectRef;
        CharSequence charSequence;
        MediaMetadata metadata;
        MediaAction string;
        int i6;
        MediaAction heartAction;
        int i7;
        int i8;
        MediaAction mediaAction7;
        int i9;
        Parcelable parcelable;
        Icon iconCreateWithResource;
        MediaAction mediaAction8;
        boolean zAreEqual;
        String action;
        Bundle extras3;
        OplusMediaDataEx mediaDataEx;
        PlaybackState playbackState = mediaController.getPlaybackState();
        if (playbackState == null) {
            return null;
        }
        OplusNotificationLogger.logAodMediaPanel(TAG, "createActionsFromState: curState = " + playbackState.getState());
        int i10 = 1;
        if (NotificationMediaManager.CONNECTING_MEDIA_STATES.contains(Integer.valueOf(playbackState.getState()))) {
            Integer numValueOf = (mediaData == null || (mediaDataEx = mediaData.getMediaDataEx()) == null) ? null : Integer.valueOf(mediaDataEx.getPreState());
            if (numValueOf == null || NotificationMediaManager.isPlayingState(numValueOf.intValue())) {
                OplusNotificationLogger.logAodMediaPanel(TAG, "is buffering state , state = " + playbackState);
                Icon iconCreateWithResource2 = Icon.createWithResource(this.context, 2131236354);
                Drawable drawableLoadDrawable = iconCreateWithResource2.loadDrawable(this.context);
                if (drawableLoadDrawable instanceof Animatable) {
                    ((Animatable) drawableLoadDrawable).start();
                }
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(i10);
                anonymousClass1.$controller = mediaController;
                VarHandle.storeStoreFence();
                mediaAction = new MediaAction(drawableLoadDrawable, anonymousClass1, this.context.getString(2131952778), (Drawable) null, Integer.valueOf(R.drawable.rate_star_small_on_holo_light), new OplusMediaActionEx(false, iconCreateWithResource2, (Uri) null, false, (Boolean) null, false, (String) null, 125));
            } else {
                OplusNotificationLogger.logAodMediaPanel(TAG, "createActionsFromState: preState = " + numValueOf);
                standardAction = getStandardAction(mediaController, playbackState.getActions(), 4L);
            }
            mediaController2 = mediaController;
            standardAction2 = getStandardAction(mediaController2, playbackState.getActions(), 16L);
            standardAction3 = getStandardAction(mediaController2, playbackState.getActions(), 32L);
            arrayList = new ArrayList();
            arrayList2 = new ArrayList(4);
            int i11 = 0;
            for (i = 0; i < 4; i++) {
                arrayList2.add(null);
            }
            if (playbackState.getCustomActions() != null || playbackState.getCustomActions().size() == 0) {
                mediaAction2 = null;
                i2 = 0;
                z = false;
            } else {
                int size3 = playbackState.getCustomActions().size();
                mediaAction2 = null;
                int i12 = 0;
                boolean z4 = false;
                int i13 = 0;
                while (i12 < size3) {
                    PlaybackState.CustomAction customAction = playbackState.getCustomActions().get(i12);
                    Integer numValueOf2 = (customAction == null || (extras3 = customAction.getExtras()) == null) ? null : Integer.valueOf(extras3.getInt(OPLUS_CUSTOM_ACTION_KEY, i11));
                    if (numValueOf2 != null && numValueOf2.intValue() == 1) {
                        i7 = i12;
                        i8 = i13;
                        if (i8 < 4) {
                            if (customAction == null) {
                                mediaAction7 = null;
                            } else {
                                if (TextUtils.isEmpty(customAction.getAction())) {
                                    OplusNotificationLogger.logAodMediaPanel(TAG, "Empty action for " + ((Object) customAction.getName()));
                                } else {
                                    try {
                                        Bundle extras4 = customAction.getExtras();
                                        parcelable = extras4 != null ? extras4.getParcelable(OPLUS_CUSTOM_ACTION_ICON_KEY) : null;
                                        if (parcelable == null) {
                                            parcelable = null;
                                        }
                                    } catch (BadParcelableException e) {
                                        MotionLayout$.ExternalSyntheticOutline0.m$1("getCustomActionOplus e: ", Log.getStackTraceString(e), TAG);
                                    }
                                    if (parcelable == null && customAction.getIcon() == 0) {
                                        OplusNotificationLogger.logAodMediaPanel(TAG, "Empty icon for " + ((Object) customAction.getName()));
                                    } else {
                                        if (parcelable instanceof Icon) {
                                            iconCreateWithResource = (Icon) parcelable;
                                        } else if (parcelable instanceof Bitmap) {
                                            iconCreateWithResource = Icon.createWithBitmap((Bitmap) parcelable);
                                            iconCreateWithResource.getClass();
                                        } else {
                                            iconCreateWithResource = Icon.createWithResource(str, customAction.getIcon());
                                            iconCreateWithResource.getClass();
                                        }
                                        Uri uri = iconCreateWithResource.getType() == 4 ? iconCreateWithResource.getUri() : null;
                                        Drawable customActionDrawable = AodMediaStatisticUtil.getCustomActionDrawable(iconCreateWithResource, this.context);
                                        Icon icon = iconCreateWithResource;
                                        OplusMediaDataManagerStrategy$getCustomActionOplus$1$1 oplusMediaDataManagerStrategy$getCustomActionOplus$1$1 = new OplusMediaDataManagerStrategy$getCustomActionOplus$1$1(0);
                                        oplusMediaDataManagerStrategy$getCustomActionOplus$1$1.$customAction = customAction;
                                        oplusMediaDataManagerStrategy$getCustomActionOplus$1$1.$controller = mediaController2;
                                        oplusMediaDataManagerStrategy$getCustomActionOplus$1$1.$it = customAction;
                                        VarHandle.storeStoreFence();
                                        mediaAction7 = new MediaAction(customActionDrawable, oplusMediaDataManagerStrategy$getCustomActionOplus$1$1, customAction.getName(), (Drawable) null, (Integer) null, new OplusMediaActionEx(false, icon, uri, false, (Boolean) null, false, (String) null, 89), 16);
                                    }
                                }
                                mediaAction7 = null;
                            }
                            if (mediaAction7 != null) {
                                i13 = i8 + 1;
                                arrayList2.set(i8, mediaAction7);
                                i9 = 0;
                                z4 = true;
                            }
                        }
                        i9 = 0;
                        i13 = i8;
                    } else {
                        i7 = i12;
                        i8 = i13;
                        if (customAction == null) {
                            mediaAction8 = null;
                        } else {
                            if (TextUtils.isEmpty(customAction.getAction())) {
                                OplusNotificationLogger.logAodMediaPanel(TAG, "Empty action for " + ((Object) customAction.getName()));
                            } else if (!TextUtils.equals(str, DISABLE_PREBUTTON_PACKAGENAME) || ((action = customAction.getAction()) != null && !StringsKt__StringsKt.contains(action, ACTION_PLACEHOLDER, false))) {
                                Icon iconCreateWithResource3 = Icon.createWithResource(str, customAction.getIcon());
                                Drawable drawableLoadDrawable2 = iconCreateWithResource3.loadDrawable(this.context);
                                OplusMediaDataManagerStrategy$getCustomAction$1$1 oplusMediaDataManagerStrategy$getCustomAction$1$1 = new OplusMediaDataManagerStrategy$getCustomAction$1$1(0);
                                oplusMediaDataManagerStrategy$getCustomAction$1$1.$it = customAction;
                                oplusMediaDataManagerStrategy$getCustomAction$1$1.$controller = mediaController2;
                                VarHandle.storeStoreFence();
                                mediaAction8 = new MediaAction(drawableLoadDrawable2, oplusMediaDataManagerStrategy$getCustomAction$1$1, customAction.getName(), (Drawable) null, (Integer) null, new OplusMediaActionEx(false, iconCreateWithResource3, (Uri) null, false, (Boolean) null, false, (String) null, 93), 16);
                            }
                            mediaAction8 = null;
                        }
                        if (mediaAction8 != null) {
                            if (arrayList.size() != 0) {
                                CharSequence contentDescription = mediaAction8.getContentDescription();
                                i9 = 0;
                                if (contentDescription != null) {
                                    CharSequence contentDescription2 = ((MediaAction) arrayList.get(0)).getContentDescription();
                                    zAreEqual = Intrinsics.areEqual(contentDescription2 != null ? Boolean.valueOf(StringsKt__StringsKt.contains(contentDescription2, contentDescription, false)) : null, Boolean.FALSE);
                                } else {
                                    zAreEqual = false;
                                }
                                if (zAreEqual) {
                                }
                                i13 = i8;
                                z4 = true;
                            } else {
                                i9 = 0;
                            }
                            arrayList.add(mediaAction8);
                            i13 = i8;
                            z4 = true;
                        } else {
                            i9 = 0;
                            i13 = i8;
                        }
                    }
                    i12 = i7 + 1;
                    i11 = i9;
                }
                i2 = i11;
                z = z4;
            }
            if (LogUtil.getNormalDebug()) {
                OplusNotificationLogger.logAodMediaPanel(TAG, "Under special circumstances,nextButton may be null .nextButton= " + standardAction3 + " , state = " + playbackState);
            }
            it = arrayList.iterator();
            extras = mediaController2.getExtras();
            if (extras != null) {
                z2 = true;
                if (extras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS")) {
                    z3 = 1;
                }
                extras2 = mediaController2.getExtras();
                if (extras2 == null && extras2.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT") == z2) {
                    i3 = 1;
                } else {
                    i3 = i2;
                }
                if (standardAction2 != null) {
                    mediaAction3 = standardAction2;
                } else if (z3 == 0) {
                    standardAction2 = createActionsFromState$lambda$4$nextCustomAction(it);
                    mediaAction3 = standardAction2;
                } else {
                    mediaAction3 = mediaAction2;
                }
                if (standardAction3 == null) {
                    if (i3 == 0) {
                        standardAction3 = createActionsFromState$lambda$4$nextCustomAction(it);
                    } else {
                        standardAction3 = mediaAction2;
                    }
                }
                mediaAction4 = mediaAction2;
                MediaAction mediaActionCreateActionsFromState$lambda$4$nextCustomAction = createActionsFromState$lambda$4$nextCustomAction(it);
                MediaAction mediaActionCreateActionsFromState$lambda$4$nextCustomAction2 = createActionsFromState$lambda$4$nextCustomAction(it);
                arrayList3 = new ArrayList();
                actionConfigListforPkg = ((MediaActionPrioritySelectorImpl) MediaActionPrioritySelector.Companion.getInstance()).getActionConfigListforPkg(str, mediaController2.getMetadata());
                arrayList4 = new ArrayList();
                if (actionConfigListforPkg != null) {
                    it2 = actionConfigListforPkg.iterator();
                    while (it2.hasNext()) {
                        oplusActionConfig = (OplusActionConfig) it2.next();
                        mediaAction5 = mediaAction4;
                        MediaAction mediaAction9 = standardAction3;
                        if (Intrinsics.areEqual(oplusActionConfig.getKey(), OplusActionConfig.HEART_ACTION_KEY)) {
                            heartAction = getHeartAction(mediaController2, oplusActionConfig.getUriKey());
                            if (heartAction != null) {
                                arrayList3.add(heartAction);
                            }
                        } else {
                            arrayList4.add(oplusActionConfig.getKey());
                            size2 = arrayList.size();
                            i5 = 0;
                            while (true) {
                                if (i5 < size2) {
                                    obj2 = mediaAction5;
                                    break;
                                }
                                obj2 = arrayList.get(i5);
                                i5++;
                                i6 = size2;
                                if (Intrinsics.areEqual(((MediaAction) obj2).getContentDescription(), oplusActionConfig.getKey())) {
                                    break;
                                }
                                size2 = i6;
                            }
                            mediaAction6 = (MediaAction) obj2;
                            if (mediaAction6 != null) {
                                objectRef = new Ref.ObjectRef();
                                if (oplusActionConfig.getUriKey() == null) {
                                    metadata = mediaController.getMetadata();
                                    if (metadata != null) {
                                        string = metadata.getString(oplusActionConfig.getUriKey());
                                    } else {
                                        string = mediaAction5;
                                    }
                                    objectRef.element = string;
                                }
                                charSequence = (CharSequence) objectRef.element;
                                if (charSequence != null || charSequence.length() == 0) {
                                    arrayList3.add(mediaAction6);
                                } else {
                                    OplusMediaDataManagerStrategy$getCustomActionOplus$1$1 oplusMediaDataManagerStrategy$getCustomActionOplus$1$2 = new OplusMediaDataManagerStrategy$getCustomActionOplus$1$1(1);
                                    oplusMediaDataManagerStrategy$getCustomActionOplus$1$2.$customAction = this;
                                    oplusMediaDataManagerStrategy$getCustomActionOplus$1$2.$it = objectRef;
                                    oplusMediaDataManagerStrategy$getCustomActionOplus$1$2.$controller = mediaAction6;
                                    VarHandle.storeStoreFence();
                                    arrayList3.add(MediaAction.copy$default(mediaAction6, oplusMediaDataManagerStrategy$getCustomActionOplus$1$2));
                                }
                            }
                            it2 = it2;
                            mediaAction4 = mediaAction5;
                            mediaController2 = mediaController;
                            standardAction3 = mediaAction9;
                        }
                        it2 = it2;
                        it2 = it2;
                        mediaAction4 = mediaAction5;
                        mediaController2 = mediaController;
                        standardAction3 = mediaAction9;
                    }
                }
                MediaAction mediaAction10 = standardAction3;
                arrayList5 = new ArrayList();
                size = arrayList.size();
                i4 = 0;
                while (i4 < size) {
                    obj = arrayList.get(i4);
                    i4++;
                    if (!CollectionsKt___CollectionsKt.contains(arrayList4, ((MediaAction) obj).getContentDescription())) {
                        arrayList5.add(obj);
                    }
                }
                arrayList3.addAll(arrayList5);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_MEDIA)) {
                    Log.d("SystemUi--Media", "OplusMediaDataManagerSt-->".concat("createActionsFromState. oplusActionConfigList = " + actionConfigListforPkg));
                }
                return new MediaButton(mediaAction, mediaAction10, mediaAction3, mediaActionCreateActionsFromState$lambda$4$nextCustomAction, mediaActionCreateActionsFromState$lambda$4$nextCustomAction2, i3, z3, new OplusMediaButtonEx(z, arrayList2, arrayList3));
            }
            z2 = true;
            z3 = i2;
            extras2 = mediaController2.getExtras();
            if (extras2 == null) {
                i3 = i2;
            } else {
                i3 = i2;
            }
            if (standardAction2 != null) {
                mediaAction3 = standardAction2;
            } else if (z3 == 0) {
                standardAction2 = createActionsFromState$lambda$4$nextCustomAction(it);
                mediaAction3 = standardAction2;
            } else {
                mediaAction3 = mediaAction2;
            }
            if (standardAction3 == null) {
                if (i3 == 0) {
                    standardAction3 = createActionsFromState$lambda$4$nextCustomAction(it);
                } else {
                    standardAction3 = mediaAction2;
                }
            }
            mediaAction4 = mediaAction2;
            MediaAction mediaActionCreateActionsFromState$lambda$4$nextCustomAction3 = createActionsFromState$lambda$4$nextCustomAction(it);
            MediaAction mediaActionCreateActionsFromState$lambda$4$nextCustomAction4 = createActionsFromState$lambda$4$nextCustomAction(it);
            arrayList3 = new ArrayList();
            actionConfigListforPkg = ((MediaActionPrioritySelectorImpl) MediaActionPrioritySelector.Companion.getInstance()).getActionConfigListforPkg(str, mediaController2.getMetadata());
            arrayList4 = new ArrayList();
            if (actionConfigListforPkg != null) {
                it2 = actionConfigListforPkg.iterator();
                while (it2.hasNext()) {
                    oplusActionConfig = (OplusActionConfig) it2.next();
                    mediaAction5 = mediaAction4;
                    MediaAction mediaAction11 = standardAction3;
                    if (Intrinsics.areEqual(oplusActionConfig.getKey(), OplusActionConfig.HEART_ACTION_KEY)) {
                        heartAction = getHeartAction(mediaController2, oplusActionConfig.getUriKey());
                        if (heartAction != null) {
                            arrayList3.add(heartAction);
                        }
                    } else {
                        arrayList4.add(oplusActionConfig.getKey());
                        size2 = arrayList.size();
                        i5 = 0;
                        while (true) {
                            if (i5 < size2) {
                                obj2 = mediaAction5;
                                break;
                            }
                            obj2 = arrayList.get(i5);
                            i5++;
                            i6 = size2;
                            if (Intrinsics.areEqual(((MediaAction) obj2).getContentDescription(), oplusActionConfig.getKey())) {
                                break;
                                break;
                            }
                            size2 = i6;
                        }
                        mediaAction6 = (MediaAction) obj2;
                        if (mediaAction6 != null) {
                            objectRef = new Ref.ObjectRef();
                            if (oplusActionConfig.getUriKey() == null) {
                                metadata = mediaController.getMetadata();
                                if (metadata != null) {
                                    string = metadata.getString(oplusActionConfig.getUriKey());
                                } else {
                                    string = mediaAction5;
                                }
                                objectRef.element = string;
                            }
                            charSequence = (CharSequence) objectRef.element;
                            if (charSequence != null) {
                                arrayList3.add(mediaAction6);
                            } else {
                                arrayList3.add(mediaAction6);
                            }
                        }
                        it2 = it2;
                        mediaAction4 = mediaAction5;
                        mediaController2 = mediaController;
                        standardAction3 = mediaAction11;
                    }
                    it2 = it2;
                    it2 = it2;
                    mediaAction4 = mediaAction5;
                    mediaController2 = mediaController;
                    standardAction3 = mediaAction11;
                }
            }
            MediaAction mediaAction12 = standardAction3;
            arrayList5 = new ArrayList();
            size = arrayList.size();
            i4 = 0;
            while (i4 < size) {
                obj = arrayList.get(i4);
                i4++;
                if (!CollectionsKt___CollectionsKt.contains(arrayList4, ((MediaAction) obj).getContentDescription())) {
                    arrayList5.add(obj);
                }
            }
            arrayList3.addAll(arrayList5);
            if (LogUtil.getNormalDebug()) {
                Log.d("SystemUi--Media", "OplusMediaDataManagerSt-->".concat("createActionsFromState. oplusActionConfigList = " + actionConfigListforPkg));
            }
            return new MediaButton(mediaAction, mediaAction12, mediaAction3, mediaActionCreateActionsFromState$lambda$4$nextCustomAction3, mediaActionCreateActionsFromState$lambda$4$nextCustomAction4, i3, z3, new OplusMediaButtonEx(z, arrayList2, arrayList3));
        }
        standardAction = NotificationMediaManager.isPlayingState(playbackState.getState()) ? getStandardAction(mediaController, playbackState.getActions(), 2L) : getStandardAction(mediaController, playbackState.getActions(), 4L);
        mediaAction = standardAction;
        mediaController2 = mediaController;
        standardAction2 = getStandardAction(mediaController2, playbackState.getActions(), 16L);
        standardAction3 = getStandardAction(mediaController2, playbackState.getActions(), 32L);
        arrayList = new ArrayList();
        arrayList2 = new ArrayList(4);
        int i14 = 0;
        while (i < 4) {
            arrayList2.add(null);
        }
        if (playbackState.getCustomActions() != null) {
            mediaAction2 = null;
            i2 = 0;
            z = false;
        } else {
            mediaAction2 = null;
            i2 = 0;
            z = false;
        }
        if (LogUtil.getNormalDebug()) {
            OplusNotificationLogger.logAodMediaPanel(TAG, "Under special circumstances,nextButton may be null .nextButton= " + standardAction3 + " , state = " + playbackState);
        }
        it = arrayList.iterator();
        extras = mediaController2.getExtras();
        if (extras != null) {
            z2 = true;
            if (extras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS")) {
                z3 = 1;
            }
            extras2 = mediaController2.getExtras();
            if (extras2 == null) {
                i3 = i2;
            } else {
                i3 = i2;
            }
            if (standardAction2 != null) {
                mediaAction3 = standardAction2;
            } else if (z3 == 0) {
                standardAction2 = createActionsFromState$lambda$4$nextCustomAction(it);
                mediaAction3 = standardAction2;
            } else {
                mediaAction3 = mediaAction2;
            }
            if (standardAction3 == null) {
                if (i3 == 0) {
                    standardAction3 = createActionsFromState$lambda$4$nextCustomAction(it);
                } else {
                    standardAction3 = mediaAction2;
                }
            }
            mediaAction4 = mediaAction2;
            MediaAction mediaActionCreateActionsFromState$lambda$4$nextCustomAction5 = createActionsFromState$lambda$4$nextCustomAction(it);
            MediaAction mediaActionCreateActionsFromState$lambda$4$nextCustomAction6 = createActionsFromState$lambda$4$nextCustomAction(it);
            arrayList3 = new ArrayList();
            actionConfigListforPkg = ((MediaActionPrioritySelectorImpl) MediaActionPrioritySelector.Companion.getInstance()).getActionConfigListforPkg(str, mediaController2.getMetadata());
            arrayList4 = new ArrayList();
            if (actionConfigListforPkg != null) {
                it2 = actionConfigListforPkg.iterator();
                while (it2.hasNext()) {
                    oplusActionConfig = (OplusActionConfig) it2.next();
                    mediaAction5 = mediaAction4;
                    MediaAction mediaAction13 = standardAction3;
                    if (Intrinsics.areEqual(oplusActionConfig.getKey(), OplusActionConfig.HEART_ACTION_KEY)) {
                        heartAction = getHeartAction(mediaController2, oplusActionConfig.getUriKey());
                        if (heartAction != null) {
                            arrayList3.add(heartAction);
                        }
                    } else {
                        arrayList4.add(oplusActionConfig.getKey());
                        size2 = arrayList.size();
                        i5 = 0;
                        while (true) {
                            if (i5 < size2) {
                                obj2 = mediaAction5;
                                break;
                            }
                            obj2 = arrayList.get(i5);
                            i5++;
                            i6 = size2;
                            if (Intrinsics.areEqual(((MediaAction) obj2).getContentDescription(), oplusActionConfig.getKey())) {
                                break;
                                break;
                            }
                            size2 = i6;
                        }
                        mediaAction6 = (MediaAction) obj2;
                        if (mediaAction6 != null) {
                            objectRef = new Ref.ObjectRef();
                            if (oplusActionConfig.getUriKey() == null) {
                                metadata = mediaController.getMetadata();
                                if (metadata != null) {
                                    string = metadata.getString(oplusActionConfig.getUriKey());
                                } else {
                                    string = mediaAction5;
                                }
                                objectRef.element = string;
                            }
                            charSequence = (CharSequence) objectRef.element;
                            if (charSequence != null) {
                                arrayList3.add(mediaAction6);
                            } else {
                                arrayList3.add(mediaAction6);
                            }
                        }
                        it2 = it2;
                        mediaAction4 = mediaAction5;
                        mediaController2 = mediaController;
                        standardAction3 = mediaAction13;
                    }
                    it2 = it2;
                    it2 = it2;
                    mediaAction4 = mediaAction5;
                    mediaController2 = mediaController;
                    standardAction3 = mediaAction13;
                }
            }
            MediaAction mediaAction14 = standardAction3;
            arrayList5 = new ArrayList();
            size = arrayList.size();
            i4 = 0;
            while (i4 < size) {
                obj = arrayList.get(i4);
                i4++;
                if (!CollectionsKt___CollectionsKt.contains(arrayList4, ((MediaAction) obj).getContentDescription())) {
                    arrayList5.add(obj);
                }
            }
            arrayList3.addAll(arrayList5);
            if (LogUtil.getNormalDebug()) {
                Log.d("SystemUi--Media", "OplusMediaDataManagerSt-->".concat("createActionsFromState. oplusActionConfigList = " + actionConfigListforPkg));
            }
            return new MediaButton(mediaAction, mediaAction14, mediaAction3, mediaActionCreateActionsFromState$lambda$4$nextCustomAction5, mediaActionCreateActionsFromState$lambda$4$nextCustomAction6, i3, z3, new OplusMediaButtonEx(z, arrayList2, arrayList3));
        }
        z2 = true;
        z3 = i2;
        extras2 = mediaController2.getExtras();
        if (extras2 == null) {
            i3 = i2;
        } else {
            i3 = i2;
        }
        if (standardAction2 != null) {
            mediaAction3 = standardAction2;
        } else if (z3 == 0) {
            standardAction2 = createActionsFromState$lambda$4$nextCustomAction(it);
            mediaAction3 = standardAction2;
        } else {
            mediaAction3 = mediaAction2;
        }
        if (standardAction3 == null) {
            if (i3 == 0) {
                standardAction3 = createActionsFromState$lambda$4$nextCustomAction(it);
            } else {
                standardAction3 = mediaAction2;
            }
        }
        mediaAction4 = mediaAction2;
        MediaAction mediaActionCreateActionsFromState$lambda$4$nextCustomAction7 = createActionsFromState$lambda$4$nextCustomAction(it);
        MediaAction mediaActionCreateActionsFromState$lambda$4$nextCustomAction8 = createActionsFromState$lambda$4$nextCustomAction(it);
        arrayList3 = new ArrayList();
        actionConfigListforPkg = ((MediaActionPrioritySelectorImpl) MediaActionPrioritySelector.Companion.getInstance()).getActionConfigListforPkg(str, mediaController2.getMetadata());
        arrayList4 = new ArrayList();
        if (actionConfigListforPkg != null) {
            it2 = actionConfigListforPkg.iterator();
            while (it2.hasNext()) {
                oplusActionConfig = (OplusActionConfig) it2.next();
                mediaAction5 = mediaAction4;
                MediaAction mediaAction15 = standardAction3;
                if (Intrinsics.areEqual(oplusActionConfig.getKey(), OplusActionConfig.HEART_ACTION_KEY)) {
                    heartAction = getHeartAction(mediaController2, oplusActionConfig.getUriKey());
                    if (heartAction != null) {
                        arrayList3.add(heartAction);
                    }
                } else {
                    arrayList4.add(oplusActionConfig.getKey());
                    size2 = arrayList.size();
                    i5 = 0;
                    while (true) {
                        if (i5 < size2) {
                            obj2 = mediaAction5;
                            break;
                        }
                        obj2 = arrayList.get(i5);
                        i5++;
                        i6 = size2;
                        if (Intrinsics.areEqual(((MediaAction) obj2).getContentDescription(), oplusActionConfig.getKey())) {
                            break;
                            break;
                        }
                        size2 = i6;
                    }
                    mediaAction6 = (MediaAction) obj2;
                    if (mediaAction6 != null) {
                        objectRef = new Ref.ObjectRef();
                        if (oplusActionConfig.getUriKey() == null) {
                            metadata = mediaController.getMetadata();
                            if (metadata != null) {
                                string = metadata.getString(oplusActionConfig.getUriKey());
                            } else {
                                string = mediaAction5;
                            }
                            objectRef.element = string;
                        }
                        charSequence = (CharSequence) objectRef.element;
                        if (charSequence != null) {
                            arrayList3.add(mediaAction6);
                        } else {
                            arrayList3.add(mediaAction6);
                        }
                    }
                    it2 = it2;
                    mediaAction4 = mediaAction5;
                    mediaController2 = mediaController;
                    standardAction3 = mediaAction15;
                }
                it2 = it2;
                it2 = it2;
                mediaAction4 = mediaAction5;
                mediaController2 = mediaController;
                standardAction3 = mediaAction15;
            }
        }
        MediaAction mediaAction16 = standardAction3;
        arrayList5 = new ArrayList();
        size = arrayList.size();
        i4 = 0;
        while (i4 < size) {
            obj = arrayList.get(i4);
            i4++;
            if (!CollectionsKt___CollectionsKt.contains(arrayList4, ((MediaAction) obj).getContentDescription())) {
                arrayList5.add(obj);
            }
        }
        arrayList3.addAll(arrayList5);
        if (LogUtil.getNormalDebug()) {
            Log.d("SystemUi--Media", "OplusMediaDataManagerSt-->".concat("createActionsFromState. oplusActionConfigList = " + actionConfigListforPkg));
        }
        return new MediaButton(mediaAction, mediaAction16, mediaAction3, mediaActionCreateActionsFromState$lambda$4$nextCustomAction7, mediaActionCreateActionsFromState$lambda$4$nextCustomAction8, i3, z3, new OplusMediaButtonEx(z, arrayList2, arrayList3));
    }

    @Override // com.oplus.systemui.media.controls.pipeline.BaseMediaDataManagerStrategy
    public MediaAction getHeartAction(MediaController mediaController, String str) {
        if (((MediaActionPrioritySelectorImpl) MediaActionPrioritySelector.Companion.getInstance()).supportOplusActionConfig(mediaController.getPackageName())) {
            MediaMetadata metadata = mediaController.getMetadata();
            Rating rating = metadata != null ? metadata.getRating("android.media.metadata.USER_RATING") : null;
            if (rating == null) {
                MediaMetadata metadata2 = mediaController.getMetadata();
                rating = metadata2 != null ? metadata2.getRating("android.media.metadata.RATING") : null;
            }
            if (LogUtil.getNormalDebug()) {
                Object objValueOf = rating != null ? Boolean.valueOf(rating.hasHeart()) : "null";
                OplusNotificationLogger.logAodMediaPanel(TAG, "Has heart rating = " + objValueOf + " for " + mediaController.getPackageName());
            }
            if (rating != null) {
                boolean zHasHeart = rating.hasHeart();
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (str != null) {
                    MediaMetadata metadata3 = mediaController.getMetadata();
                    objectRef.element = metadata3 != null ? metadata3.getString(str) : null;
                }
                Context context = this.context;
                if (zHasHeart) {
                    Icon iconCreateWithResource = Icon.createWithResource(context, 2131234367);
                    OplusMediaDataManagerStrategy$getCustomAction$1$1 oplusMediaDataManagerStrategy$getCustomAction$1$1 = new OplusMediaDataManagerStrategy$getCustomAction$1$1(1);
                    oplusMediaDataManagerStrategy$getCustomAction$1$1.$it = objectRef;
                    oplusMediaDataManagerStrategy$getCustomAction$1$1.$controller = mediaController;
                    VarHandle.storeStoreFence();
                    return new MediaAction(iconCreateWithResource.loadDrawable(this.context), oplusMediaDataManagerStrategy$getCustomAction$1$1, this.context.getResources().getString(2131951752), (Drawable) null, (Integer) null, new OplusMediaActionEx(false, iconCreateWithResource, (Uri) null, false, Boolean.TRUE, false, str, 13), 16);
                }
                Icon iconCreateWithResource2 = Icon.createWithResource(context, 2131234366);
                OplusMediaDataManagerStrategy$getCustomAction$1$1 oplusMediaDataManagerStrategy$getCustomAction$1$2 = new OplusMediaDataManagerStrategy$getCustomAction$1$1(2);
                oplusMediaDataManagerStrategy$getCustomAction$1$2.$it = objectRef;
                oplusMediaDataManagerStrategy$getCustomAction$1$2.$controller = mediaController;
                VarHandle.storeStoreFence();
                return new MediaAction(iconCreateWithResource2.loadDrawable(this.context), oplusMediaDataManagerStrategy$getCustomAction$1$2, this.context.getResources().getString(2131951751), (Drawable) null, (Integer) null, new OplusMediaActionEx(false, iconCreateWithResource2, (Uri) null, false, Boolean.FALSE, false, str, 13), 16);
            }
        }
        return null;
    }

    public final MediaAction getStandardAction(MediaController mediaController, long j, long j2) {
        if (((j2 != 4 && j2 != 2) || (j & 512) <= 0) && (j & j2) == 0) {
            return null;
        }
        if (j2 == 4) {
            Icon iconCreateWithResource = Icon.createWithResource(this.context, 2131236355);
            Drawable drawableLoadDrawable = iconCreateWithResource.loadDrawable(this.context);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
            anonymousClass1.$controller = mediaController;
            VarHandle.storeStoreFence();
            return new MediaAction(drawableLoadDrawable, anonymousClass1, this.context.getString(2131952782), (Drawable) null, (Integer) null, new OplusMediaActionEx(false, iconCreateWithResource, (Uri) null, false, (Boolean) null, true, (String) null, 93), 16);
        }
        if (j2 == 2) {
            Icon iconCreateWithResource2 = Icon.createWithResource(this.context, 2131236354);
            Drawable drawableLoadDrawable2 = iconCreateWithResource2.loadDrawable(this.context);
            AnonymousClass1 anonymousClass2 = new AnonymousClass1(2);
            anonymousClass2.$controller = mediaController;
            VarHandle.storeStoreFence();
            return new MediaAction(drawableLoadDrawable2, anonymousClass2, this.context.getString(2131952781), (Drawable) null, (Integer) null, new OplusMediaActionEx(false, iconCreateWithResource2, (Uri) null, false, (Boolean) null, true, (String) null, 93), 16);
        }
        if (j2 == 16) {
            Icon iconCreateWithResource3 = Icon.createWithResource(this.context, 2131236356);
            Drawable drawableLoadDrawable3 = iconCreateWithResource3.loadDrawable(this.context);
            AnonymousClass1 anonymousClass3 = new AnonymousClass1(3);
            anonymousClass3.$controller = mediaController;
            VarHandle.storeStoreFence();
            return new MediaAction(drawableLoadDrawable3, anonymousClass3, this.context.getString(2131952783), (Drawable) null, (Integer) null, new OplusMediaActionEx(false, iconCreateWithResource3, (Uri) null, false, (Boolean) null, true, (String) null, 93), 16);
        }
        if (j2 != 32) {
            return null;
        }
        Icon iconCreateWithResource4 = Icon.createWithResource(this.context, 2131236353);
        Drawable drawableLoadDrawable4 = iconCreateWithResource4.loadDrawable(this.context);
        AnonymousClass1 anonymousClass4 = new AnonymousClass1(4);
        anonymousClass4.$controller = mediaController;
        VarHandle.storeStoreFence();
        return new MediaAction(drawableLoadDrawable4, anonymousClass4, this.context.getString(2131952779), (Drawable) null, (Integer) null, new OplusMediaActionEx(false, iconCreateWithResource4, (Uri) null, false, (Boolean) null, true, (String) null, 93), 16);
    }

    @Override // com.oplus.systemui.media.controls.pipeline.BaseMediaDataManagerStrategy
    public boolean onMatching(String str) {
        return true;
    }
}
