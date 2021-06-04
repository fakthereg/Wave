package com.example.myapplication.ui.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.utils.NetworkUtils;
import com.example.myapplication.R;
import com.example.myapplication.StaticData;
import com.example.myapplication.User;

import org.json.JSONArray;

public class FragmentAnswer extends Fragment implements View.OnClickListener {

    private static FragmentAnswer instance;

    private TextView textViewCategory;
    private TextView textViewUsername;
    private TextView textViewScore;
    private ImageView imageViewAvatar;
    private ImageView imageViewSongBackground;
    private TextView textViewArtist;
    private TextView textViewTitle;
    private ImageView imageViewCup;
    private ImageView imageViewCloud;
    private TextView textViewScoreGain;
    private ImageView imageViewCoin;
    private ImageButton imageButtonBack;
    private ImageButton imageButtonNext;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answer, container, false);
        textViewCategory = view.findViewById(R.id.textViewAnswerCategory);
        textViewUsername = view.findViewById(R.id.textViewAnswerUsername);
        textViewScore = view.findViewById(R.id.textViewAnswerScore);
        textViewArtist = view.findViewById(R.id.textViewAnswerArtist);
        textViewTitle = view.findViewById(R.id.textViewAnswerTitle);
        imageViewAvatar = view.findViewById(R.id.imageViewAnswerAvatar);
        imageViewSongBackground = view.findViewById(R.id.imageViewAnswerSongBackground);
        imageViewCup = view.findViewById(R.id.imageViewAnswerCup);
        imageViewCloud = view.findViewById(R.id.imageViewAnswerCloud);
        textViewScoreGain = view.findViewById(R.id.textViewScoreGain);
        imageViewCoin = view.findViewById(R.id.imageViewAnswerCoin);
        imageButtonBack = view.findViewById(R.id.imageButtonAnswerBack);
        imageButtonNext = view.findViewById(R.id.imageButtonAnswerNext);
        return view;
    }

    public static FragmentAnswer getInstance() {
        if (instance == null) {
            instance = new FragmentAnswer();
        }
        return instance;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageButtonNext.setOnClickListener(this);
        imageButtonBack.setOnClickListener(this);
        imageViewAvatar.setImageResource(User.avatar);
        textViewUsername.setText(User.name);
        textViewCategory.setText(StaticData.getChosenCategory());
        textViewScore.setText(String.valueOf(User.score));
        textViewArtist.setText(StaticData.chosenSongArtist);
        textViewTitle.setText(StaticData.chosenSongTitle);
        if (StaticData.answered) {
            if (StaticData.currentAnswer) {
                imageViewCup.setImageResource(R.drawable.cup_win);
                imageViewCloud.setVisibility(View.INVISIBLE);
                imageViewSongBackground.setImageResource(R.drawable.song_background_win);
                imageViewCoin.setVisibility(View.VISIBLE);
                textViewScoreGain.setVisibility(View.VISIBLE);
                textViewScoreGain.setText(String.valueOf(StaticData.scoreGain));
            } else {
                imageViewCup.setImageResource(R.drawable.cup_lose);
                imageViewCloud.setVisibility(View.VISIBLE);
                imageViewSongBackground.setImageResource(R.drawable.song_background_lose);
                imageViewCoin.setVisibility(View.INVISIBLE);
                textViewScoreGain.setVisibility(View.INVISIBLE);
            }
        } else {
            imageViewCup.setImageResource(R.drawable.cup_lose);
            imageViewCloud.setVisibility(View.VISIBLE);
            imageViewSongBackground.setImageResource(R.drawable.song_background_lose);
            imageViewCoin.setVisibility(View.INVISIBLE);
            textViewScoreGain.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        MainActivity.playButtonClickSound();
        if (v.getId() == R.id.imageButtonAnswerNext) {
            //TODO получить список песен минус список уже сыгранных, родить новый фрагментПлей
            NetworkUtils.ConnectGetTask getNextSongTask = new NetworkUtils.ConnectGetTask();
            JSONArray songs = new JSONArray();
            try {
                songs = new JSONArray(getNextSongTask.execute(String.format(StaticData.URL_GET_FILES_BY_CATEGORY, StaticData.chosenCategory)).get());
                StaticData.allSongsInCategory = songs;
            } catch (Exception e) {
                e.printStackTrace();
            }
            getFragmentManager().beginTransaction().replace(R.id.container, new FragmentPlay()).commit();
        } else if (v.getId() == R.id.imageButtonAnswerBack) {
            MainActivity.playMainTheme(true);
            getFragmentManager().beginTransaction().replace(R.id.container, FragmentPanels.getInstance()).replace(R.id.panels_container, new FragmentCategory()).commit();
        }
    }
}
