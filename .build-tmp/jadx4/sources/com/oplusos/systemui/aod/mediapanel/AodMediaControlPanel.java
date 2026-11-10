package com.oplusos.systemui.aod.mediapanel;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.launcher3.icons.FastBitmapDrawable;
import com.android.settingslib.Utils;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.MediaDataExtKt;
import com.android.systemui.media.controls.domain.pipeline.MediaActionsKt;
import com.android.systemui.media.controls.models.player.OplusMediaActionEx;
import com.android.systemui.media.controls.models.player.OplusMediaButtonEx;
import com.android.systemui.media.controls.models.player.OplusMediaDataEx;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.aod.utils.AodUtils;
import com.oplus.systemui.notification.mediapanel.AodMediaPromptController;
import com.oplus.systemui.notification.mediapanel.SpotifyMediaControl;
import com.oplus.systemui.notification.mediapanel.util.AodMediaStatisticUtil;
import com.oplus.systemui.notification.mediapanel.widget.AodMediaPanelView;
import com.oplusos.systemui.aod.mediapanel.viewstate.AodMediaViewStateHolderExpanded;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.utils.DrawableUtilsKt;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodMediaControlPanel implements IBackSreenCallBack {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AodMediaControlPanel";
    private AodMediaController aodMediaController;
    private final AodMediaPromptController aodMediaPromptController;
    private final AodMediaViewStateController aodMediaViewStateController;
    private final configChangeListener.1 configChangeListener;
    private final ConfigurationController configurationController;
    public Context context;
    private MediaController controller;
    public final int mediaAppIconSize;
    public final int mediaPanelActionImageSize;
    public final int mediaPanelAlbumSize;
    public final int mediaPanelGroupPaddingHorizontal;
    public final int mediaPanelGroupPaddingVertical;
    private AodMediaViewHolder mediaViewHolder;
    private AodMediaPanelView player;
    public final int radiusCorner;
    private SpotifyMediaControl spotifyMediaControl;
    private MediaSession.Token token;
    private final AodMediaViewStateHolderExpanded viewStateExpanded;

    public AodMediaControlPanel(Context context, ConfigurationController configurationController, AodMediaPromptController aodMediaPromptController, AodMediaViewStateController aodMediaViewStateController, AodMediaViewStateHolderExpanded aodMediaViewStateHolderExpanded) {
        this.configurationController = configurationController;
        this.aodMediaPromptController = aodMediaPromptController;
        this.aodMediaViewStateController = aodMediaViewStateController;
        this.viewStateExpanded = aodMediaViewStateHolderExpanded;
        this.context = AodUtils.getFixContext(context);
        this.spotifyMediaControl = new SpotifyMediaControl(aodMediaPromptController);
        configChangeListener.1 r1 = new configChangeListener.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configChangeListener = r1;
        configurationController.addCallback(r1);
        this.radiusCorner = this.context.getResources().getDimensionPixelSize(2131165489);
        this.mediaPanelAlbumSize = this.context.getResources().getDimensionPixelSize(2131165515);
        this.mediaPanelActionImageSize = this.context.getResources().getDimensionPixelSize(2131165492);
        this.mediaPanelGroupPaddingHorizontal = this.context.getResources().getDimensionPixelSize(2131165524);
        this.mediaAppIconSize = this.context.getResources().getDimensionPixelSize(2131165517);
        this.mediaPanelGroupPaddingVertical = this.context.getResources().getDimensionPixelSize(2131165525);
    }

    public static void setSemanticButton$default(AodMediaControlPanel aodMediaControlPanel, ImageButton imageButton, MediaAction mediaAction) {
        Drawable icon;
        if (imageButton == null) {
            OplusNotificationLogger.logAodMediaPanel(TAG, "setSemanticButton --> button is null!");
            return;
        }
        if (mediaAction == null) {
            imageButton.setImageIcon(null);
            imageButton.setContentDescription(null);
            imageButton.setEnabled(false);
            imageButton.setTag(2131363189, null);
            imageButton.setVisibility(8);
            return;
        }
        Icon icon2 = mediaAction.getMediaActionEx().getIcon();
        if (icon2 == null || (icon = icon2.loadDrawable(aodMediaControlPanel.context)) == null) {
            icon = mediaAction.getIcon();
        }
        if (icon instanceof Animatable) {
            ((Animatable) icon).start();
        }
        imageButton.setImageDrawable(icon);
        imageButton.setContentDescription(mediaAction.getContentDescription());
        Runnable action = mediaAction.getAction();
        if (action == null || !mediaAction.getMediaActionEx().getEnabled()) {
            imageButton.setEnabled(false);
        } else {
            imageButton.setEnabled(true);
            setSemanticButton.1 r5 = new setSemanticButton.1();
            r5.$action = action;
            VarHandle.storeStoreFence();
            imageButton.setOnClickListener(r5);
        }
        imageButton.setTag(2131363189, 1);
        imageButton.setVisibility(0);
    }

    public final void attachPlayer(AodMediaViewHolder aodMediaViewHolder, AodMediaController aodMediaController) {
        this.mediaViewHolder = aodMediaViewHolder;
        this.player = aodMediaViewHolder.getPlayer();
        this.aodMediaController = aodMediaController;
    }

    public final void bindArtistText(MediaData mediaData) {
        OplusMediaDataEx mediaDataEx;
        AodMediaViewHolder aodMediaViewHolder = this.mediaViewHolder;
        CharSequence artist = null;
        TextView artistText = aodMediaViewHolder != null ? aodMediaViewHolder.getArtistText() : null;
        if (artistText != null) {
            if (this.aodMediaViewStateController.getRehearsing()) {
                if (mediaData != null && (mediaDataEx = mediaData.getMediaDataEx()) != null) {
                    artist = mediaDataEx.getArtist2();
                }
            } else if (mediaData != null) {
                artist = mediaData.getArtist();
            }
            artistText.setText(artist);
        }
    }

    /* JADX WARN: Code duplicated, block: B:128:0x029a  */
    /* JADX WARN: Code duplicated, block: B:129:0x029f  */
    /* JADX WARN: Code duplicated, block: B:132:0x02a6  */
    /* JADX WARN: Code duplicated, block: B:133:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:136:0x02b2  */
    /* JADX WARN: Code duplicated, block: B:137:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:140:0x02be  */
    /* JADX WARN: Code duplicated, block: B:141:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:144:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:145:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:148:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:149:0x02dd  */
    /* JADX WARN: Code duplicated, block: B:152:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:153:0x02e9  */
    /* JADX WARN: Code duplicated, block: B:156:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:157:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:160:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:161:0x0303  */
    /* JADX WARN: Code duplicated, block: B:164:0x030a  */
    /* JADX WARN: Code duplicated, block: B:165:0x030f  */
    /* JADX WARN: Code duplicated, block: B:168:0x0316  */
    /* JADX WARN: Code duplicated, block: B:169:0x031b  */
    /* JADX WARN: Code duplicated, block: B:172:0x0322  */
    /* JADX WARN: Code duplicated, block: B:173:0x0327  */
    /* JADX WARN: Code duplicated, block: B:216:0x03c2  */
    /* JADX WARN: Code duplicated, block: B:235:0x0478  */
    /* JADX WARN: Code duplicated, block: B:238:0x047d  */
    /* JADX WARN: Code duplicated, block: B:240:0x0483  */
    /* JADX WARN: Code duplicated, block: B:242:0x0493  */
    /* JADX WARN: Code duplicated, block: B:250:0x04b9  */
    /* JADX WARN: Code duplicated, block: B:253:0x04d8  */
    /* JADX WARN: Code duplicated, block: B:258:0x04f6  */
    /* JADX WARN: Code duplicated, block: B:260:0x04f9  */
    /* JADX WARN: Code duplicated, block: B:262:0x04fe  */
    /* JADX WARN: Code duplicated, block: B:273:0x04f0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:280:0x0501 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:250:0x04b9, please report this as an issue */
    public final void bindPlayer(MediaData mediaData, String str) {
        char c;
        int iIntValue;
        LinearLayout[] linearLayoutArr;
        AodMediaViewHolder aodMediaViewHolder;
        LinearLayout action0;
        AodMediaViewHolder aodMediaViewHolder2;
        LinearLayout action1;
        AodMediaViewHolder aodMediaViewHolder3;
        LinearLayout action2;
        AodMediaViewHolder aodMediaViewHolder4;
        LinearLayout action3;
        AodMediaViewHolder aodMediaViewHolder5;
        ImageView action0Iv;
        AodMediaViewHolder aodMediaViewHolder6;
        ImageView action1Iv;
        AodMediaViewHolder aodMediaViewHolder7;
        ImageView action2Iv;
        AodMediaViewHolder aodMediaViewHolder8;
        ImageView action3Iv;
        AodMediaViewHolder aodMediaViewHolder9;
        TextView action0Tv;
        AodMediaViewHolder aodMediaViewHolder10;
        TextView action1Tv;
        AodMediaViewHolder aodMediaViewHolder11;
        TextView action2Tv;
        AodMediaViewHolder aodMediaViewHolder12;
        TextView action3Tv;
        MediaButton semanticActions;
        int i;
        LinearLayout linearLayout;
        OplusMediaButtonEx mediaButtonEx;
        int i2;
        LinearLayout linearLayout2;
        int measuredHeight;
        AodMediaViewStateHolderExpanded aodMediaViewStateHolderExpanded;
        LinearLayout linearLayout3;
        ImageView appIcon;
        ImageView albumView;
        AodMediaViewHolder aodMediaViewHolder13 = this.mediaViewHolder;
        if (aodMediaViewHolder13 == null || this.player == null) {
            OplusNotificationLogger.logAodMediaPanel(TAG, "ERROR!! bindPlayer Fail， mediaViewHolder = " + aodMediaViewHolder13 + ", player = " + this.player);
            return;
        }
        OplusNotificationLogger.logAodMediaPanel(TAG, "bindPlayer() called with: data = " + MediaDataExtKt.printInfo(mediaData, true) + ", key = " + str);
        MediaSession.Token token = this.token;
        MediaAction mediaAction = null;
        if (token == null || !token.equals(mediaData.getToken())) {
            MediaSession.Token token2 = mediaData.getToken();
            this.token = token2;
            this.controller = token2 != null ? new MediaController(this.context, token2) : null;
        }
        AodMediaPanelView aodMediaPanelView = this.player;
        int i3 = 0;
        if (aodMediaPanelView != null) {
            aodMediaPanelView.setLandscape(this.aodMediaViewStateController.aodLayoutDirection == 2);
        }
        AodMediaViewHolder aodMediaViewHolder14 = this.mediaViewHolder;
        TextView titleText = aodMediaViewHolder14 != null ? aodMediaViewHolder14.getTitleText() : null;
        if (titleText != null) {
            titleText.setText(mediaData.getSong());
        }
        bindArtistText(mediaData);
        AodMediaViewHolder aodMediaViewHolder15 = this.mediaViewHolder;
        if (aodMediaViewHolder15 == null || (albumView = aodMediaViewHolder15.getAlbumView()) == null) {
            OplusNotificationLogger.logAodMediaPanel(TAG, "albumView is null");
        } else {
            Icon artwork = mediaData.getArtwork();
            if (artwork == null) {
                OplusNotificationLogger.logAodMediaPanel(TAG, "Artwork does not exist in media data");
            } else {
                Drawable drawableLoadDrawable = artwork.loadDrawable(this.context);
                if (drawableLoadDrawable == null) {
                    OplusNotificationLogger.logAodMediaPanel(TAG, "scaled background is null");
                    drawableLoadDrawable = null;
                } else {
                    int i4 = this.mediaPanelAlbumSize;
                    Rect rect = new Rect(0, 0, i4, i4);
                    if (rect.width() > i4 || rect.height() > i4) {
                        rect.offset((int) (-((rect.width() - i4) / 2.0f)), (int) (-((rect.height() - i4) / 2.0f)));
                    }
                    drawableLoadDrawable.setBounds(rect);
                }
                albumView.setPadding(0, 0, 0, 0);
                albumView.setImageDrawable(drawableLoadDrawable);
                albumView.setOutlineProvider(new setCustomButton.1.2(this, 1));
                albumView.setClipToOutline(true);
            }
        }
        AodMediaViewHolder aodMediaViewHolder16 = this.mediaViewHolder;
        if (aodMediaViewHolder16 != null && (appIcon = aodMediaViewHolder16.getAppIcon()) != null) {
            appIcon.clearColorFilter();
            boolean zIsSpotifyActive = this.aodMediaPromptController.isSpotifyActive();
            Context context = this.context;
            if (zIsSpotifyActive) {
                Drawable drawable = context.getDrawable(2131234543);
                if (drawable != null) {
                    appIcon.setImageDrawable(drawable);
                }
            } else {
                try {
                    FastBitmapDrawable badgedIcon = Utils.getBadgedIcon(this.context, context.getPackageManager().getApplicationInfoAsUser(mediaData.getPackageName(), 128, mediaData.getUserId()));
                    int i5 = this.mediaAppIconSize;
                    Drawable roundRectDrawable = DrawableUtilsKt.getRoundRectDrawable(badgedIcon, 0, i5, i5, this.context);
                    if (roundRectDrawable != null) {
                        appIcon.setImageDrawable(roundRectDrawable);
                    } else {
                        appIcon.setImageTintList(ColorStateList.valueOf(this.context.getColor(2131099862)));
                        appIcon.setImageResource(2131234354);
                    }
                } catch (Exception unused) {
                    Log.e(TAG, "Package name not found package=" + mediaData.getPackageName() + " user=" + mediaData.getUserId());
                    appIcon.setImageTintList(ColorStateList.valueOf(this.context.getColor(2131099862)));
                    appIcon.setImageResource(2131234354);
                }
            }
        }
        MediaButton semanticActions2 = mediaData.getSemanticActions();
        List notificationActions = MediaActionsKt.getNotificationActions(mediaData.getActions(), (ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class));
        char c2 = 3;
        if (semanticActions2 == null) {
            if (!notificationActions.isEmpty()) {
                List actionsToShowInCompact = mediaData.getActionsToShowInCompact();
                ImageButton[] imageButtonArr = new ImageButton[4];
                AodMediaViewHolder aodMediaViewHolder17 = this.mediaViewHolder;
                imageButtonArr[0] = aodMediaViewHolder17 != null ? aodMediaViewHolder17.getActionPrev() : null;
                AodMediaViewHolder aodMediaViewHolder18 = this.mediaViewHolder;
                imageButtonArr[1] = aodMediaViewHolder18 != null ? aodMediaViewHolder18.getActionPlayPause() : null;
                AodMediaViewHolder aodMediaViewHolder19 = this.mediaViewHolder;
                imageButtonArr[2] = aodMediaViewHolder19 != null ? aodMediaViewHolder19.getActionNext() : null;
                AodMediaViewHolder aodMediaViewHolder20 = this.mediaViewHolder;
                imageButtonArr[3] = aodMediaViewHolder20 != null ? aodMediaViewHolder20.getLike() : null;
                int size = notificationActions.size();
                if (actionsToShowInCompact.isEmpty()) {
                    c = 3;
                    for (int i6 = 0; i6 < 4; i6++) {
                        if (i6 < size) {
                            setSemanticButton$default(this, imageButtonArr[i6], (MediaAction) notificationActions.get(i6));
                        } else {
                            setSemanticButton$default(this, imageButtonArr[i6], null);
                        }
                    }
                } else {
                    int size2 = actionsToShowInCompact.size();
                    int i7 = 0;
                    while (i7 < 4) {
                        if (i7 >= size2 || (iIntValue = ((Number) actionsToShowInCompact.get(i7)).intValue()) < 0 || iIntValue >= size) {
                            setSemanticButton$default(this, imageButtonArr[i7], null);
                        } else {
                            setSemanticButton$default(this, imageButtonArr[i7], (MediaAction) notificationActions.get(iIntValue));
                        }
                        i7++;
                        c2 = c2;
                    }
                }
            }
            linearLayoutArr = new LinearLayout[4];
            aodMediaViewHolder = this.mediaViewHolder;
            if (aodMediaViewHolder != null) {
                action0 = aodMediaViewHolder.getAction0();
            } else {
                action0 = null;
            }
            linearLayoutArr[0] = action0;
            aodMediaViewHolder2 = this.mediaViewHolder;
            if (aodMediaViewHolder2 != null) {
                action1 = aodMediaViewHolder2.getAction1();
            } else {
                action1 = null;
            }
            linearLayoutArr[1] = action1;
            aodMediaViewHolder3 = this.mediaViewHolder;
            if (aodMediaViewHolder3 != null) {
                action2 = aodMediaViewHolder3.getAction2();
            } else {
                action2 = null;
            }
            linearLayoutArr[2] = action2;
            aodMediaViewHolder4 = this.mediaViewHolder;
            if (aodMediaViewHolder4 != null) {
                action3 = aodMediaViewHolder4.getAction3();
            } else {
                action3 = null;
            }
            linearLayoutArr[c] = action3;
            ImageView[] imageViewArr = new ImageView[4];
            aodMediaViewHolder5 = this.mediaViewHolder;
            if (aodMediaViewHolder5 != null) {
                action0Iv = aodMediaViewHolder5.getAction0Iv();
            } else {
                action0Iv = null;
            }
            imageViewArr[0] = action0Iv;
            aodMediaViewHolder6 = this.mediaViewHolder;
            if (aodMediaViewHolder6 != null) {
                action1Iv = aodMediaViewHolder6.getAction1Iv();
            } else {
                action1Iv = null;
            }
            imageViewArr[1] = action1Iv;
            aodMediaViewHolder7 = this.mediaViewHolder;
            if (aodMediaViewHolder7 != null) {
                action2Iv = aodMediaViewHolder7.getAction2Iv();
            } else {
                action2Iv = null;
            }
            imageViewArr[2] = action2Iv;
            aodMediaViewHolder8 = this.mediaViewHolder;
            if (aodMediaViewHolder8 != null) {
                action3Iv = aodMediaViewHolder8.getAction3Iv();
            } else {
                action3Iv = null;
            }
            imageViewArr[c] = action3Iv;
            TextView[] textViewArr = new TextView[4];
            aodMediaViewHolder9 = this.mediaViewHolder;
            if (aodMediaViewHolder9 != null) {
                action0Tv = aodMediaViewHolder9.getAction0Tv();
            } else {
                action0Tv = null;
            }
            textViewArr[0] = action0Tv;
            aodMediaViewHolder10 = this.mediaViewHolder;
            if (aodMediaViewHolder10 != null) {
                action1Tv = aodMediaViewHolder10.getAction1Tv();
            } else {
                action1Tv = null;
            }
            textViewArr[1] = action1Tv;
            aodMediaViewHolder11 = this.mediaViewHolder;
            if (aodMediaViewHolder11 != null) {
                action2Tv = aodMediaViewHolder11.getAction2Tv();
            } else {
                action2Tv = null;
            }
            textViewArr[2] = action2Tv;
            aodMediaViewHolder12 = this.mediaViewHolder;
            if (aodMediaViewHolder12 != null) {
                action3Tv = aodMediaViewHolder12.getAction3Tv();
            } else {
                action3Tv = null;
            }
            textViewArr[c] = action3Tv;
            semanticActions = mediaData.getSemanticActions();
            if (semanticActions == null && (mediaButtonEx = semanticActions.getMediaButtonEx()) != null && mediaButtonEx.getHasCustomAction()) {
                int i8 = 0;
                while (i8 < 4) {
                    List oplusCustomActions = semanticActions.getMediaButtonEx().getOplusCustomActions();
                    MediaAction mediaAction2 = oplusCustomActions != null ? (MediaAction) oplusCustomActions.get(i8) : mediaAction;
                    if (mediaAction2 != null) {
                        LinearLayout linearLayout4 = linearLayoutArr[i8];
                        if (linearLayout4 != null) {
                            linearLayout4.setEnabled(true);
                        }
                        TextView textView = textViewArr[i8];
                        if (textView != null) {
                            textView.setText(mediaAction2.getContentDescription());
                        }
                        ImageView imageView = imageViewArr[i8];
                        if (imageView == null) {
                            i2 = i3;
                            linearLayout2 = linearLayoutArr[i8];
                            if (linearLayout2 != null) {
                                if (linearLayout2.getMeasuredHeight() == 0) {
                                    linearLayout2.measure(View.MeasureSpec.makeMeasureSpec(i2, i2), View.MeasureSpec.makeMeasureSpec(i2, i2));
                                }
                                measuredHeight = linearLayout2.getMeasuredHeight();
                            } else {
                                measuredHeight = 0;
                            }
                            aodMediaViewStateHolderExpanded = this.viewStateExpanded;
                            if (i8 <= aodMediaViewStateHolderExpanded.getRecommendationContainer().size() - 1 || measuredHeight == 0 || measuredHeight < aodMediaViewStateHolderExpanded.actionOplusHeight) {
                                OplusNotificationLogger.logAodMediaPanel("AodMediaViewStateHolderExpanded", "ERROR: updateActionHeightWithMeasuredHeight: Height：" + measuredHeight + ", index：" + i8);
                            } else {
                                aodMediaViewStateHolderExpanded.getRecommendationContainer().get(i8).setProp("PROP_HEIGHT", measuredHeight);
                            }
                            linearLayout3 = linearLayoutArr[i8];
                            if (linearLayout3 != null) {
                                setCustomButton.2 r7 = new setCustomButton.2();
                                r7.$indexToButton = i8;
                                r7.$mediaAction = mediaAction2;
                                VarHandle.storeStoreFence();
                                linearLayout3.setOnClickListener(r7);
                            }
                        } else {
                            OplusMediaActionEx mediaActionEx = mediaAction2.getMediaActionEx();
                            MediaAction icon = mediaActionEx != null ? mediaActionEx.getIcon() : mediaAction;
                            Ref.ObjectRef objectRef = new Ref.ObjectRef();
                            objectRef.element = mediaAction2.getIcon();
                            if (icon != null) {
                                if (imageView.getTag() != null) {
                                    Drawable drawable2 = (Drawable) objectRef.element;
                                    Uri uri = mediaAction2.getMediaActionEx().getUri();
                                    if ((imageView.getTag() instanceof Drawable) && (icon.getType() == 1 || icon.getType() == 2)) {
                                        if (!Intrinsics.areEqual(((Drawable) imageView.getTag()).getConstantState(), drawable2 != null ? drawable2.getConstantState() : null)) {
                                            if (imageView.getTag() instanceof String) {
                                            }
                                        }
                                    } else if ((imageView.getTag() instanceof String) || icon.getType() != 4 || !Intrinsics.areEqual(imageView.getTag(), String.valueOf(uri))) {
                                    }
                                }
                                imageView.setTag(objectRef.element);
                                if (icon.getType() == 4 && mediaAction2.getMediaActionEx().getUri() != null) {
                                    OplusNotificationLogger.logAodMediaPanel(TAG, "load uri type drawable index = " + i8);
                                    imageView.setTag(String.valueOf(mediaAction2.getMediaActionEx().getUri()));
                                    Runnable runnable = new setCustomButton.1.runnable.1();
                                    ((setCustomButton.1.runnable.1) runnable).$iconDrawable = objectRef;
                                    ((setCustomButton.1.runnable.1) runnable).$icon = icon;
                                    ((setCustomButton.1.runnable.1) runnable).this$0 = this;
                                    ((setCustomButton.1.runnable.1) runnable).$imageView = imageView;
                                    ((setCustomButton.1.runnable.1) runnable).$indexToButton = i8;
                                    ((setCustomButton.1.runnable.1) runnable).$mediaAction = mediaAction2;
                                    VarHandle.storeStoreFence();
                                    SystemUIThreadUtil.getWorkHandler().post(runnable);
                                }
                                Object obj = objectRef.element;
                                if (!(obj instanceof BitmapDrawable)) {
                                    OplusNotificationLogger.logAodMediaPanel(TAG, "setCustomButton iconDrawable = " + obj);
                                }
                                DrawableUtilsKt.recycleImageViewBitmap(imageView);
                                Drawable drawable3 = (Drawable) objectRef.element;
                                if (drawable3 != null) {
                                    imageView.setImageDrawable(drawable3);
                                }
                                i2 = 0;
                                imageView.setPadding(0, 0, 0, 0);
                                imageView.setOutlineProvider(new setCustomButton.1.2(this, 0));
                                imageView.setClipToOutline(true);
                                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                                int i9 = this.mediaPanelActionImageSize;
                                layoutParams.height = i9;
                                imageView.getLayoutParams().width = i9;
                                imageView.setVisibility(0);
                                imageView.invalidate();
                            } else {
                                i2 = i3;
                            }
                            linearLayout2 = linearLayoutArr[i8];
                            if (linearLayout2 != null) {
                                if (linearLayout2.getMeasuredHeight() == 0) {
                                    linearLayout2.measure(View.MeasureSpec.makeMeasureSpec(i2, i2), View.MeasureSpec.makeMeasureSpec(i2, i2));
                                }
                                measuredHeight = linearLayout2.getMeasuredHeight();
                            } else {
                                measuredHeight = 0;
                            }
                            aodMediaViewStateHolderExpanded = this.viewStateExpanded;
                            if (i8 <= aodMediaViewStateHolderExpanded.getRecommendationContainer().size() - 1) {
                                OplusNotificationLogger.logAodMediaPanel("AodMediaViewStateHolderExpanded", "ERROR: updateActionHeightWithMeasuredHeight: Height：" + measuredHeight + ", index：" + i8);
                            } else {
                                OplusNotificationLogger.logAodMediaPanel("AodMediaViewStateHolderExpanded", "ERROR: updateActionHeightWithMeasuredHeight: Height：" + measuredHeight + ", index：" + i8);
                            }
                            linearLayout3 = linearLayoutArr[i8];
                            if (linearLayout3 != null) {
                                setCustomButton.2 r8 = new setCustomButton.2();
                                r8.$indexToButton = i8;
                                r8.$mediaAction = mediaAction2;
                                VarHandle.storeStoreFence();
                                linearLayout3.setOnClickListener(r8);
                            }
                        }
                    } else {
                        LinearLayout linearLayout5 = linearLayoutArr[i8];
                        if (linearLayout5 != null) {
                            linearLayout5.setEnabled(false);
                        }
                    }
                    i8++;
                    mediaAction = null;
                    i3 = 0;
                }
            } else {
                for (i = 0; i < 4; i++) {
                    linearLayout = linearLayoutArr[i];
                    if (linearLayout != null) {
                        linearLayout.setEnabled(false);
                    }
                }
            }
            this.spotifyMediaControl.bindRecommendIconToSpotifyIfNeed(this.mediaViewHolder, this.controller);
            this.spotifyMediaControl.cancelBindIcGuideToSpotifyIfNeed(this.mediaViewHolder);
        }
        AodMediaViewHolder aodMediaViewHolder21 = this.mediaViewHolder;
        setSemanticButton$default(this, aodMediaViewHolder21 != null ? aodMediaViewHolder21.getActionPlayPause() : null, semanticActions2.getPlayOrPause());
        AodMediaViewHolder aodMediaViewHolder22 = this.mediaViewHolder;
        setSemanticButton$default(this, aodMediaViewHolder22 != null ? aodMediaViewHolder22.getActionNext() : null, semanticActions2.getNextOrCustom());
        AodMediaViewHolder aodMediaViewHolder23 = this.mediaViewHolder;
        setSemanticButton$default(this, aodMediaViewHolder23 != null ? aodMediaViewHolder23.getActionPrev() : null, semanticActions2.getPrevOrCustom());
        boolean zEquals = "com.spotify.music".equals(mediaData.getPackageName());
        AodMediaViewHolder aodMediaViewHolder24 = this.mediaViewHolder;
        if (zEquals) {
            setSemanticButton$default(this, aodMediaViewHolder24 != null ? aodMediaViewHolder24.getLike() : null, semanticActions2.getCustom0());
        } else {
            setSemanticButton$default(this, aodMediaViewHolder24 != null ? aodMediaViewHolder24.getLike() : null, semanticActions2.getMediaButtonEx().getHeartAction());
        }
        c = c2;
        linearLayoutArr = new LinearLayout[4];
        aodMediaViewHolder = this.mediaViewHolder;
        if (aodMediaViewHolder != null) {
            action0 = aodMediaViewHolder.getAction0();
        } else {
            action0 = null;
        }
        linearLayoutArr[0] = action0;
        aodMediaViewHolder2 = this.mediaViewHolder;
        if (aodMediaViewHolder2 != null) {
            action1 = aodMediaViewHolder2.getAction1();
        } else {
            action1 = null;
        }
        linearLayoutArr[1] = action1;
        aodMediaViewHolder3 = this.mediaViewHolder;
        if (aodMediaViewHolder3 != null) {
            action2 = aodMediaViewHolder3.getAction2();
        } else {
            action2 = null;
        }
        linearLayoutArr[2] = action2;
        aodMediaViewHolder4 = this.mediaViewHolder;
        if (aodMediaViewHolder4 != null) {
            action3 = aodMediaViewHolder4.getAction3();
        } else {
            action3 = null;
        }
        linearLayoutArr[c] = action3;
        ImageView[] imageViewArr2 = new ImageView[4];
        aodMediaViewHolder5 = this.mediaViewHolder;
        if (aodMediaViewHolder5 != null) {
            action0Iv = aodMediaViewHolder5.getAction0Iv();
        } else {
            action0Iv = null;
        }
        imageViewArr2[0] = action0Iv;
        aodMediaViewHolder6 = this.mediaViewHolder;
        if (aodMediaViewHolder6 != null) {
            action1Iv = aodMediaViewHolder6.getAction1Iv();
        } else {
            action1Iv = null;
        }
        imageViewArr2[1] = action1Iv;
        aodMediaViewHolder7 = this.mediaViewHolder;
        if (aodMediaViewHolder7 != null) {
            action2Iv = aodMediaViewHolder7.getAction2Iv();
        } else {
            action2Iv = null;
        }
        imageViewArr2[2] = action2Iv;
        aodMediaViewHolder8 = this.mediaViewHolder;
        if (aodMediaViewHolder8 != null) {
            action3Iv = aodMediaViewHolder8.getAction3Iv();
        } else {
            action3Iv = null;
        }
        imageViewArr2[c] = action3Iv;
        TextView[] textViewArr2 = new TextView[4];
        aodMediaViewHolder9 = this.mediaViewHolder;
        if (aodMediaViewHolder9 != null) {
            action0Tv = aodMediaViewHolder9.getAction0Tv();
        } else {
            action0Tv = null;
        }
        textViewArr2[0] = action0Tv;
        aodMediaViewHolder10 = this.mediaViewHolder;
        if (aodMediaViewHolder10 != null) {
            action1Tv = aodMediaViewHolder10.getAction1Tv();
        } else {
            action1Tv = null;
        }
        textViewArr2[1] = action1Tv;
        aodMediaViewHolder11 = this.mediaViewHolder;
        if (aodMediaViewHolder11 != null) {
            action2Tv = aodMediaViewHolder11.getAction2Tv();
        } else {
            action2Tv = null;
        }
        textViewArr2[2] = action2Tv;
        aodMediaViewHolder12 = this.mediaViewHolder;
        if (aodMediaViewHolder12 != null) {
            action3Tv = aodMediaViewHolder12.getAction3Tv();
        } else {
            action3Tv = null;
        }
        textViewArr2[c] = action3Tv;
        semanticActions = mediaData.getSemanticActions();
        if (semanticActions == null) {
            while (i < 4) {
                linearLayout = linearLayoutArr[i];
                if (linearLayout != null) {
                    linearLayout.setEnabled(false);
                }
            }
        } else {
            while (i < 4) {
                linearLayout = linearLayoutArr[i];
                if (linearLayout != null) {
                    linearLayout.setEnabled(false);
                }
            }
        }
        this.spotifyMediaControl.bindRecommendIconToSpotifyIfNeed(this.mediaViewHolder, this.controller);
        this.spotifyMediaControl.cancelBindIcGuideToSpotifyIfNeed(this.mediaViewHolder);
    }

    public final void destroy() {
        OplusNotificationLogger.logAodMediaPanel(TAG, "destroy");
        AodMediaViewHolder aodMediaViewHolder = this.mediaViewHolder;
        if (aodMediaViewHolder != null) {
            aodMediaViewHolder.unRegisterBSCb();
        }
        this.configurationController.removeCallback(this.configChangeListener);
        AodMediaController aodMediaController = this.aodMediaController;
        if (aodMediaController != null) {
            aodMediaController.release();
        }
        this.aodMediaController = null;
    }

    public final String getClickTargetEvent(int i) {
        MediaData boundMediaData;
        if (i == 2131362089) {
            return this.aodMediaViewStateController.getRehearsing() ? AodMediaStatisticUtil.EVENT_REHEARSING : AodMediaStatisticUtil.EVENT_MEDIA_ROOT;
        }
        if (i == 2131361908) {
            AodMediaController aodMediaController = this.aodMediaController;
            return (aodMediaController == null || (boundMediaData = aodMediaController.getBoundMediaData()) == null) ? false : Intrinsics.areEqual(boundMediaData.isPlaying(), Boolean.TRUE) ? AodMediaStatisticUtil.EVENT_PAUSE : AodMediaStatisticUtil.EVENT_PLAY;
        }
        if (i == 2131361909) {
            return AodMediaStatisticUtil.EVENT_PREV;
        }
        if (i == 2131361907) {
            return AodMediaStatisticUtil.EVENT_NEXT;
        }
        if (i == 2131363729) {
            return AodMediaStatisticUtil.EVENT_LIKE;
        }
        if (i == 2131361891) {
            return AodMediaStatisticUtil.EVENT_CUSTOM_ACTION_0;
        }
        if (i == 2131361894) {
            return AodMediaStatisticUtil.EVENT_CUSTOM_ACTION_1;
        }
        if (i == 2131361897) {
            return AodMediaStatisticUtil.EVENT_CUSTOM_ACTION_2;
        }
        return i == 2131361900 ? AodMediaStatisticUtil.EVENT_CUSTOM_ACTION_3 : "";
    }

    public final AodMediaPanelView getPlayerView() {
        return this.player;
    }

    public final void onElementClick(int i) {
        String strM;
        AodMediaController aodMediaController;
        if (LogUtil.getNormalDebug()) {
            try {
                Resources resources = this.context.getResources();
                if (resources == null || (strM = resources.getResourceName(i)) == null) {
                    strM = "unKnow_" + i;
                }
            } catch (Exception unused) {
                strM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "exception_");
            }
            OplusNotificationLogger.logAodMediaPanel(TAG, "onElementClick() called with: resName = " + strM + " animating = " + this.aodMediaViewStateController.getAnimating());
        } else {
            strM = "";
        }
        if (this.aodMediaViewStateController.getAnimating()) {
            return;
        }
        if (i == 2131362089) {
            if (this.aodMediaViewStateController.getRehearsing()) {
                AodMediaStatisticUtil.collectAodMediaActionClicked(this.context, getClickTargetEvent(i));
                this.aodMediaPromptController.onRehearsingClick();
            }
            if (this.aodMediaViewStateController.aodLayoutDirection == 2 || (aodMediaController = this.aodMediaController) == null) {
                return;
            }
            aodMediaController.toggleUpdatePanelExpandedState();
            return;
        }
        if (i != 2131361908 && i != 2131361909 && i != 2131361907 && i != 2131363729) {
            if (i == 2131361891 || i == 2131361894 || i == 2131361897 || i == 2131361900) {
                AodMediaViewHolder aodMediaViewHolder = this.mediaViewHolder;
                LinearLayout customButtonLayoutById = aodMediaViewHolder != null ? aodMediaViewHolder.getCustomButtonLayoutById(i) : null;
                if (customButtonLayoutById != null) {
                    customButtonLayoutById.performClick();
                }
                AodMediaController aodMediaController2 = this.aodMediaController;
                if (aodMediaController2 != null) {
                    aodMediaController2.resetOperateTimeout(this.aodMediaViewStateController.getCurState(), true);
                }
                AodMediaStatisticUtil.collectAodMediaActionClicked(this.context, getClickTargetEvent(i));
                return;
            }
            return;
        }
        AodMediaViewHolder aodMediaViewHolder2 = this.mediaViewHolder;
        ImageButton actionButton = aodMediaViewHolder2 != null ? aodMediaViewHolder2.getActionButton(i) : null;
        if (actionButton == null || !actionButton.isEnabled()) {
            if (LogUtil.getNormalDebug()) {
                OplusNotificationLogger.logAodMediaPanel(TAG, "onElementClick Failed : action click failed resName = ".concat(strM));
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug()) {
            boolean zIsAttachedToWindow = actionButton.isAttachedToWindow();
            ViewParent parent = actionButton.getParent();
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("onElementClick: action click resName = ", strM, ", ", zIsAttachedToWindow, " -- ");
            sbM.append(parent);
            OplusNotificationLogger.logAodMediaPanel(TAG, sbM.toString());
        }
        actionButton.performClick();
        AodMediaController aodMediaController3 = this.aodMediaController;
        if (aodMediaController3 != null) {
            aodMediaController3.resetOperateTimeout(this.aodMediaViewStateController.getCurState(), true);
        }
        AodMediaStatisticUtil.collectAodMediaActionClicked(this.context, getClickTargetEvent(i));
    }

    public final void registerBlackScreenGestureCallback() {
        OplusNotificationLogger.logAodMediaPanel(TAG, "registerBlackScreenGestureCallback: " + (this.mediaViewHolder != null));
        AodMediaViewHolder aodMediaViewHolder = this.mediaViewHolder;
        if (aodMediaViewHolder != null) {
            aodMediaViewHolder.registerBSCb(this);
        }
    }

    public final void tryAddPlayerToMediaViewGroup(ViewGroup viewGroup) {
        if (viewGroup != null) {
            AodMediaPanelView aodMediaPanelView = this.player;
            if (aodMediaPanelView == null || aodMediaPanelView.isAttachedToWindow()) {
                AodMediaPanelView aodMediaPanelView2 = this.player;
                if ((aodMediaPanelView2 != null ? aodMediaPanelView2.getParent() : null) != null) {
                    return;
                }
            }
            OplusNotificationLogger.logAodMediaPanel(TAG, "addPlayerToMediaViewGroup");
            viewGroup.setPadding(0, 0, 0, 0);
            AodMediaPanelView aodMediaPanelView3 = this.player;
            if (aodMediaPanelView3 != null) {
                int i = this.mediaPanelGroupPaddingHorizontal;
                int i2 = this.mediaPanelGroupPaddingVertical;
                aodMediaPanelView3.setPaddingRelative(i, i2, i, i2);
            }
            viewGroup.removeAllViews();
            AodMediaPanelView aodMediaPanelView4 = this.player;
            if ((aodMediaPanelView4 != null ? aodMediaPanelView4.getParent() : null) != null) {
                AodMediaPanelView aodMediaPanelView5 = this.player;
                ((ViewGroup) (aodMediaPanelView5 != null ? aodMediaPanelView5.getParent() : null)).removeView(this.player);
            }
            viewGroup.addView((View) this.player, (ViewGroup.LayoutParams) new ViewGroup.MarginLayoutParams(-2, -2));
        }
    }

    public final void unRegisterBlackScreenGestureCallback() {
        OplusNotificationLogger.logAodMediaPanel(TAG, "unRegisterBlackScreenGestureCallback: " + (this.mediaViewHolder != null));
        AodMediaViewHolder aodMediaViewHolder = this.mediaViewHolder;
        if (aodMediaViewHolder != null) {
            aodMediaViewHolder.unRegisterBSCb();
        }
    }
}
