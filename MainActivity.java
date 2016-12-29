package com.example.recordingtesting;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity
{
    private MediaRecorder recorder;
    private MediaPlayer player;
    private String OUTPUT_FILE;

    private Button recordButton;
    private Button stopRecordButton;
    private Button playButton;
    private Button stopPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OUTPUT_FILE = Environment.getExternalStorageDirectory() + "/audiorecorder.3gpp";

        recordButton = (Button) findViewById(R.id.recordButton);
        stopRecordButton = (Button) findViewById(R.id.stopRecordButton);
        playButton = (Button) findViewById(R.id.playButton);
        stopPlayButton = (Button) findViewById(R.id.stopPlayButton);

        recordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonClicked(view);
            }
        });
        stopRecordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonClicked(view);
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonClicked(view);
            }
        });
        stopPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonClicked(view);
            }
        });
    }

    public void buttonClicked(View view)
    {
        switch(view.getId())
        {
            case R.id.recordButton:
                try
                {
                    beginRecording();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.stopRecordButton:
                try
                {
                    stopRecording();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.playButton:
                try
                {
                    playRecording();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.stopPlayButton:
                try
                {
                    stopPlayback();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void ditchMediaRecorder()
    {
        if(recorder != null)
            recorder.release();
    }

    private void stopPlayback()
    {
        if(player != null)
        {
            player.stop();
        }
    }

    private void playRecording() throws Exception
    {
        ditchMediaPlayer();

        player = new MediaPlayer();
        player.setDataSource(OUTPUT_FILE);
        player.prepare();
        player.start();
    }

    private void ditchMediaPlayer()
    {
        if(player !=  null)
        {
            try
            {
                player.release();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void stopRecording()
    {
        if(recorder != null)
            recorder.stop();
    }

    private void beginRecording() throws Exception
    {
        ditchMediaRecorder();

        File outFile = new File(OUTPUT_FILE);

        if(outFile.exists())
        {
            //outFile.delete();
        }

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        recorder.setOutputFile(OUTPUT_FILE);
        recorder.prepare();
        recorder.start();
    }
}
