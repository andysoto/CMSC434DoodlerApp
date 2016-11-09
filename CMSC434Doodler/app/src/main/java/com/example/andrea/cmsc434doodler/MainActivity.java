package com.example.andrea.cmsc434doodler;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.ak.android.widget.colorpickerseekbar.ColorPickerSeekBar;


public class MainActivity extends AppCompatActivity {

    private DoodleView doodleView;
    private SeekBar sizeSeekBar;
    private SeekBar opacSeekBar;
    private ColorPickerSeekBar colorPickerSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorPickerSeekBar = (ColorPickerSeekBar)findViewById(R.id.colorpicker);
        colorPickerSeekBar.setOnColorSeekbarChangeListener(new ColorPickerSeekBar.OnColorSeekBarChangeListener() {
            @Override
            public void onColorChanged(SeekBar seekBar, int color, boolean fromUser) {
                TextView realTimeColor = (TextView) findViewById(R.id.textViewColor);
                realTimeColor.setBackgroundColor(color);
                doodleView = (DoodleView) findViewById(R.id.doodleView);
                doodleView.changeBrushColor(color);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        /*
        * SeekBar set to initial brush size of  12
        * Listener is set up to adjust seekbar upon brush size change by user
        */
        sizeSeekBar = (SeekBar) findViewById(R.id.seekBarSize);
        sizeSeekBar.setProgress(12);
        sizeSeekBar.setMax(175);
        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //DoodleView dv = (DoodleView) findViewById(R.id.doodleView);
                //dv.changeBrushSize(progress);
                doodleView = (DoodleView) findViewById(R.id.doodleView);
                doodleView.changeBrushSize(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        opacSeekBar = (SeekBar) findViewById(R.id.seekBarOpac);
        opacSeekBar.setMax(255);
        opacSeekBar.setProgress(255);
        opacSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                doodleView = (DoodleView) findViewById(R.id.doodleView);
                doodleView.changeBrushAlpha(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doodleView.clear();
            }
        });

        Button undoButton = (Button) findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doodleView.undo();
            }
        });
    }


}
