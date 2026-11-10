package com.oplus.systemui.media.covermedia;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.PowerManager;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.launcher3.icons.FastBitmapDrawable;
import com.android.settingslib.Utils;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplusos.systemui.common.utils.DrawableUtilsKt;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCoverMediaView extends ConstraintLayout {
    public static final Companion Companion = new Companion();
    private static final String HOUR_FORMAT_12 = "h";
    private static final String HOUR_FORMAT_24 = "H";
    private static final String MINUTE_FORMAT = "mm";
    private static final String SPOTIFY_KEY = "com.spotify.music";
    private static final String TAG = "OplusCoverMediaView";
    private MediaController controller;
    public ImageButton mActionNext;
    public ImageButton mActionPlayPause;
    public ImageButton mActionPrev;
    public ConstraintLayout mActions;
    public ImageView mAlbumView;
    public ImageView mAppIcon;
    public TextView mArtistText;
    private Calendar mCalendar;
    private TextView mClockTimeSymbol;
    private String mHour;
    private String mKey;
    public ImageButton mLike;
    private String mMinutes;
    private PowerManager mPM;
    private TextView mTimeHour;
    private TextView mTimeMinute;
    public TextView mTitleText;
    private MediaSession.Token mToken;
    public int mediaAppIconSize;
    public int mediaPanelAlbumSize;
    public int radiusCorner;

    public OplusCoverMediaView(Context context) {
        super(context);
    }

    public static void setSemanticButton$default(OplusCoverMediaView oplusCoverMediaView, ImageButton imageButton, MediaAction mediaAction) {
        if (mediaAction == null) {
            imageButton.setImageIcon(null);
            imageButton.setContentDescription(null);
            imageButton.setEnabled(false);
            imageButton.setVisibility(8);
            return;
        }
        imageButton.setImageIcon(mediaAction.getMediaActionEx().getIcon());
        imageButton.setContentDescription(mediaAction.getContentDescription());
        Runnable action = mediaAction.getAction();
        if (action == null || !mediaAction.getMediaActionEx().getEnabled()) {
            imageButton.setEnabled(false);
        } else {
            imageButton.setEnabled(true);
            setSemanticButton.1 r4 = new setSemanticButton.1();
            r4.$action = action;
            r4.this$0 = oplusCoverMediaView;
            VarHandle.storeStoreFence();
            imageButton.setOnClickListener(r4);
        }
        imageButton.setVisibility(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void bindPlayer(MediaData mediaData, String str) {
        int iIntValue;
        OplusNotificationLogger.logCoverMediaPanel(TAG, "bindPlayer() called with: data = " + mediaData + ", key = " + str);
        this.mKey = str;
        MediaSession.Token token = mediaData.getToken();
        MediaSession.Token token2 = this.mToken;
        if (token2 == null || !token2.equals(token)) {
            this.mToken = token;
        }
        MediaSession.Token token3 = this.mToken;
        this.controller = token3 != null ? new MediaController(getContext(), token3) : null;
        if (mediaData.getSong() != null) {
            CharSequence song = mediaData.getSong();
            TextView textView = this.mTitleText;
            if (textView == null) {
                textView = null;
            }
            if (!Intrinsics.areEqual(song, textView.getTag(2131362736))) {
                TextView textView2 = this.mTitleText;
                if (textView2 == null) {
                    textView2 = null;
                }
                textView2.setText(mediaData.getSong());
                TextView textView3 = this.mTitleText;
                if (textView3 == null) {
                    textView3 = null;
                }
                textView3.setTag(2131362736, mediaData.getSong());
            }
        }
        if (mediaData.getArtist() != null) {
            CharSequence artist = mediaData.getArtist();
            TextView textView4 = this.mArtistText;
            if (textView4 == null) {
                textView4 = null;
            }
            if (!Intrinsics.areEqual(artist, textView4.getTag(2131362735))) {
                TextView textView5 = this.mArtistText;
                if (textView5 == null) {
                    textView5 = null;
                }
                textView5.setText(mediaData.getArtist());
                TextView textView6 = this.mArtistText;
                if (textView6 == null) {
                    textView6 = null;
                }
                textView6.setTag(2131362735, mediaData.getArtist());
            }
        }
        int i = 0;
        if (mediaData.getArtwork() != null) {
            Icon artwork = mediaData.getArtwork();
            artwork.getClass();
            Drawable drawableLoadDrawable = artwork.loadDrawable(getContext());
            int i2 = this.mediaPanelAlbumSize;
            if (drawableLoadDrawable == null) {
                drawableLoadDrawable = null;
            } else {
                Rect rect = new Rect(0, 0, i2, i2);
                if (rect.width() > i2 || rect.height() > i2) {
                    rect.offset((int) (-((rect.width() - i2) / 2.0f)), (int) (-((rect.height() - i2) / 2.0f)));
                }
                drawableLoadDrawable.setBounds(rect);
            }
            ImageView imageView = this.mAlbumView;
            if (imageView == null) {
                imageView = null;
            }
            imageView.setPadding(0, 0, 0, 0);
            ImageView imageView2 = this.mAlbumView;
            if (imageView2 == null) {
                imageView2 = null;
            }
            imageView2.setImageDrawable(drawableLoadDrawable);
            ImageView imageView3 = this.mAlbumView;
            if (imageView3 == null) {
                imageView3 = null;
            }
            imageView3.setOutlineProvider(new bindAlbumToPlayerView.1(this));
            ImageView imageView4 = this.mAlbumView;
            if (imageView4 == null) {
                imageView4 = null;
            }
            imageView4.setClipToOutline(true);
        } else {
            OplusNotificationLogger.logCoverMediaPanel(TAG, "Media data do not has album!!!");
        }
        str.getClass();
        boolean zContains = StringsKt__StringsKt.contains(str, "com.spotify.music", false);
        ImageView imageView5 = this.mAppIcon;
        if (imageView5 == null) {
            imageView5 = null;
        }
        imageView5.clearColorFilter();
        if (zContains) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getContext().getResources(), 2131236672, options);
            int i3 = this.mediaAppIconSize;
            Bitmap scaleDrawable = DrawableUtilsKt.getScaleDrawable(bitmapDecodeResource, i3, i3);
            ImageView imageView6 = this.mAppIcon;
            if (imageView6 == null) {
                imageView6 = null;
            }
            imageView6.setImageBitmap(scaleDrawable);
        } else {
            try {
                FastBitmapDrawable badgedIcon = Utils.getBadgedIcon(getContext(), getContext().getPackageManager().getApplicationInfoAsUser(mediaData.getPackageName(), 128, mediaData.getUserId()));
                int i4 = this.mediaAppIconSize;
                Drawable roundRectDrawable = DrawableUtilsKt.getRoundRectDrawable(badgedIcon, 0, i4, i4, getContext());
                ImageView imageView7 = this.mAppIcon;
                if (roundRectDrawable != null) {
                    if (imageView7 == null) {
                        imageView7 = null;
                    }
                    imageView7.setImageDrawable(roundRectDrawable);
                } else {
                    if (imageView7 == null) {
                        imageView7 = null;
                    }
                    imageView7.setImageTintList(ColorStateList.valueOf(getContext().getColor(2131099862)));
                    ImageView imageView8 = this.mAppIcon;
                    if (imageView8 == null) {
                        imageView8 = null;
                    }
                    imageView8.setImageResource(2131234354);
                }
            } catch (PackageManager.NameNotFoundException unused) {
                OplusNotificationLogger.logCoverMediaPanel(TAG, "Package name not found package=" + mediaData.getPackageName() + " user=" + mediaData.getUserId());
                ImageView imageView9 = this.mAppIcon;
                if (imageView9 == null) {
                    imageView9 = null;
                }
                imageView9.setImageTintList(ColorStateList.valueOf(getContext().getColor(2131099862)));
                ImageView imageView10 = this.mAppIcon;
                if (imageView10 == null) {
                    imageView10 = null;
                }
                imageView10.setImageResource(2131234354);
            }
        }
        MediaButton semanticActions = mediaData.getSemanticActions();
        List actions = mediaData.getActions();
        if (semanticActions != null) {
            ViewGroup viewGroup = this.mActions;
            if (viewGroup == null) {
                viewGroup = null;
            }
            ConstraintLayout.LayoutParams layoutParams = viewGroup.getLayoutParams();
            MediaAction custom0 = semanticActions.getCustom0();
            Context context = ((ViewGroup) this).mContext;
            int iRoundToInt = custom0 == null ? MathKt.roundToInt(context.getResources().getDimension(2131167768)) : MathKt.roundToInt(context.getResources().getDimension(2131167767));
            layoutParams.setMarginStart(iRoundToInt);
            layoutParams.setMarginEnd(iRoundToInt);
            ViewGroup viewGroup2 = this.mActions;
            if (viewGroup2 == null) {
                viewGroup2 = null;
            }
            viewGroup2.setLayoutParams(layoutParams);
            ImageButton imageButton = this.mActionPlayPause;
            if (imageButton == null) {
                imageButton = null;
            }
            setSemanticButton$default(this, imageButton, semanticActions.getPlayOrPause());
            ImageButton imageButton2 = this.mActionNext;
            if (imageButton2 == null) {
                imageButton2 = null;
            }
            setSemanticButton$default(this, imageButton2, semanticActions.getNextOrCustom());
            ImageButton imageButton3 = this.mActionPrev;
            if (imageButton3 == null) {
                imageButton3 = null;
            }
            setSemanticButton$default(this, imageButton3, semanticActions.getPrevOrCustom());
            ImageButton imageButton4 = this.mLike;
            setSemanticButton$default(this, imageButton4 != null ? imageButton4 : null, semanticActions.getCustom0());
            return;
        }
        if (actions.isEmpty()) {
            return;
        }
        List actionsToShowInCompact = mediaData.getActionsToShowInCompact();
        ImageButton[] imageButtonArr = new ImageButton[4];
        ImageButton imageButton5 = this.mActionPrev;
        if (imageButton5 == null) {
            imageButton5 = null;
        }
        imageButtonArr[0] = imageButton5;
        ImageButton imageButton6 = this.mActionPlayPause;
        if (imageButton6 == null) {
            imageButton6 = null;
        }
        imageButtonArr[1] = imageButton6;
        ImageButton imageButton7 = this.mActionNext;
        if (imageButton7 == null) {
            imageButton7 = null;
        }
        imageButtonArr[2] = imageButton7;
        ImageButton imageButton8 = this.mLike;
        if (imageButton8 == null) {
            imageButton8 = null;
        }
        imageButtonArr[3] = imageButton8;
        int size = actions.size();
        if (actionsToShowInCompact.isEmpty()) {
            while (i < 4) {
                if (i >= size) {
                    setSemanticButton$default(this, imageButtonArr[i], null);
                }
                i++;
            }
            return;
        }
        int size2 = actionsToShowInCompact.size();
        while (i < 4) {
            if (i >= size2 || (iIntValue = ((Number) actionsToShowInCompact.get(i)).intValue()) < 0 || iIntValue >= size) {
                setSemanticButton$default(this, imageButtonArr[i], null);
            }
            i++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.view.ViewGroup*/.onAttachedToWindow();
        Calendar calendar = Calendar.getInstance();
        this.mCalendar = calendar;
        if (calendar != null) {
            calendar.setTimeInMillis(System.currentTimeMillis());
        }
        updateStandardTime$1();
        TextView textView = this.mClockTimeSymbol;
        if (textView != null) {
            textView.setText(KeyguardUtils.Companion.getKeyguardUtils(getContext()).getClockColonSymbol());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onFinishInflate() {
        super/*android.view.ViewGroup*/.onFinishInflate();
        this.radiusCorner = getContext().getResources().getDimensionPixelSize(2131165489);
        this.mediaPanelAlbumSize = getContext().getResources().getDimensionPixelSize(2131165515);
        getContext().getResources().getDimensionPixelSize(2131165492);
        getContext().getResources().getDimensionPixelSize(2131165524);
        this.mediaAppIconSize = getContext().getResources().getDimensionPixelSize(2131165517);
        getContext().getResources().getDimensionPixelSize(2131165525);
        requireViewById(2131362741);
        this.mAlbumView = (ImageView) requireViewById(2131362734);
        this.mAppIcon = (ImageView) requireViewById(2131362738);
        this.mTitleText = (TextView) requireViewById(2131362736);
        this.mArtistText = (TextView) requireViewById(2131362735);
        this.mActions = requireViewById(2131362733);
        this.mActionPlayPause = (ImageButton) requireViewById(2131362731);
        this.mActionNext = (ImageButton) requireViewById(2131362730);
        this.mActionPrev = (ImageButton) requireViewById(2131362732);
        this.mLike = (ImageButton) requireViewById(2131362740);
        this.mTimeHour = (TextView) requireViewById(2131362572);
        this.mTimeMinute = (TextView) requireViewById(2131362573);
        this.mClockTimeSymbol = (TextView) requireViewById(2131362574);
        TextView textView = this.mTitleText;
        if (textView == null) {
            textView = null;
        }
        textView.setSelected(true);
        TextView textView2 = this.mArtistText;
        (textView2 != null ? textView2 : null).setSelected(true);
        this.mCalendar = Calendar.getInstance();
        this.mPM = (PowerManager) getContext().getSystemService(PowerManager.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateStandardTime$1() {
        Integer numValueOf;
        Calendar calendar = this.mCalendar;
        if (calendar == null) {
            return;
        }
        if (calendar != null) {
            calendar.setTimeInMillis(System.currentTimeMillis());
        }
        this.mMinutes = (String) DateFormat.format(MINUTE_FORMAT, this.mCalendar);
        boolean zIs24HourFormat = DateFormat.is24HourFormat(getContext(), -2);
        Calendar calendar2 = this.mCalendar;
        String str = zIs24HourFormat ? (String) DateFormat.format(HOUR_FORMAT_24, calendar2) : (String) DateFormat.format(HOUR_FORMAT_12, calendar2);
        this.mHour = str;
        if (str != null) {
            try {
                numValueOf = Integer.valueOf(Integer.parseInt(str));
            } catch (NumberFormatException unused) {
                OplusNotificationLogger.logCoverMediaPanel(TAG, "fail to parse, mMinutes:" + this.mMinutes + ",mHour:" + this.mHour);
            }
        } else {
            numValueOf = null;
        }
        this.mHour = String.format("%02d", Arrays.copyOf(new Object[]{numValueOf}, 1));
        TextView textView = this.mTimeHour;
        if (textView != null) {
            textView.setText(this.mHour);
        }
        TextView textView2 = this.mTimeMinute;
        if (textView2 != null) {
            textView2.setText(this.mMinutes);
        }
        OplusNotificationLogger.logCoverMediaPanel(TAG, "updateStandardTime mTimeHour:" + this.mHour + ", mTimeMinute:" + this.mMinutes);
    }

    public OplusCoverMediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public OplusCoverMediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
