package com.park.tensorflow.gpt2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.park.tensorflow.gpt2.ml.GPT2Client;
import com.park.tensorflow.gpt2.ml.GptCallback;

public class Gpt2Activity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpt2);
        final TextView completionTextView = findViewById(R.id.completion_tv);
        final EditText promptInput = findViewById(R.id.prompt_input);
        final GPT2Client gpt2Client = new GPT2Client(getApplication());

        findViewById(R.id.generate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gpt2Client.launchAutocompleteWithCallback(promptInput.getText().toString(), new GptCallback() {
                    @Override
                    public void onResult(final String result) {
                        completionTextView.post(new Runnable() {
                            @Override
                            public void run() {
                                completionTextView.setText(result);
                            }
                        });
                    }
                });
            }
        });
    }
}
