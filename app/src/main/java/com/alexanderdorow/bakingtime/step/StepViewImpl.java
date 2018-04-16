package com.alexanderdorow.bakingtime.step;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexanderdorow.bakingtime.R;
import com.alexanderdorow.bakingtime.db.model.Step;
import com.alexanderdorow.bakingtime.base.BaseFragmentViewImpl;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import butterknife.BindView;

import static com.alexanderdorow.bakingtime.main.MainViewImpl.STEP_EXTRA;

public class StepViewImpl extends BaseFragmentViewImpl<StepPresenter> implements StepView {

    @BindView(R.id.teste)
    TextView teste;
    @BindView(R.id.pv_video)
    PlayerView videoView;
    @BindView(R.id.iv_thumbnail)
    ImageView thumbnail;

    private SimpleExoPlayer player;

    @Override
    public void initializeView() {
        if (getArguments() == null) {
            return;
        }
        Step step = getArguments().getParcelable(STEP_EXTRA);
        if (step == null) {
            return;
        }

        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(), new DefaultLoadControl());


        teste.setText(step.getDescription());
        String videoURL = step.getVideoURL();
        boolean isVideoType = videoURL != null && !videoURL.isEmpty();
        String thumbnailURL = step.getThumbnailURL();
        boolean isThumbnailType = thumbnailURL != null && !thumbnailURL.isEmpty();
        if (isVideoType) {
            videoView.setVisibility(View.VISIBLE);
            Uri u = Uri.parse(videoURL);
            MediaSource source = buildMediaSource(u);
            videoView.setPlayer(player);
            player.prepare(source);
            player.setPlayWhenReady(true);
            player.seekTo(0, 0);
            player.prepare(source, true, false);
        } else if (isThumbnailType) {
            thumbnail.setVisibility(View.VISIBLE);
            Glide.with(getView()).load(thumbnailURL).into(thumbnail);
        } else {
            videoView.setVisibility(View.GONE);
            thumbnail.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        player.stop();
        super.onDestroy();
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_step;
    }

    @Override
    public StepPresenter getPresenter() {
        return new StepPresenterImpl(this);
    }
}
