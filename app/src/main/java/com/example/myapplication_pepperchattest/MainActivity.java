package com.example.myapplication_pepperchattest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import android.os.Bundle;
import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.conversation.Chat;
import com.aldebaran.qi.sdk.object.locale.Language;
import com.aldebaran.qi.sdk.object.locale.Locale;
import com.aldebaran.qi.sdk.object.locale.Region;

import java.util.ArrayList;

public class MainActivity extends RobotActivity implements RobotLifecycleCallbacks {
    String TAG = "MainActivity";
    QiContext qiContext;
    private Chat chat;

    private ArrayList<Languages> arrLanguages = new ArrayList<>();
    private ArrayList<ChatBot> arrChatBots = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QiSDK.register(this, this);
        prepareChateBots();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QiSDK.unregister(this, this);
    }

    ///////////////////////////////////
    // Robot lifecycle callbacks
    Fragment lastFragment = null;


    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        Log.i(TAG, "Robot Focus gained");
        this.qiContext = qiContext;
        createChatBots();


    }

    @Override
    public void onRobotFocusLost() {
        removeListener();

    }

    @Override
    public void onRobotFocusRefused(String reason) {

    }

    /**
     * PrepWork
     */

    public void prepareChateBots() {
        fillLanguages();

    }

    public void fillLanguages() {
       /* arrLanguages.add(Languages.French);
        arrLanguages.add(Languages.Italian);
        arrLanguages.add(Languages.Swedish);

        arrLanguages.add(Languages.Turkish);
        arrLanguages.add(Languages.Croatioan);
        arrLanguages.add(Languages.Portuguese);

        arrLanguages.add(Languages.Albanian);
        arrLanguages.add(Languages.Spanish);
        */

        arrLanguages.add(Languages.English);

       // arrLanguages.add(Languages.German);
    }

    public void createChatBots() {
        for (int i = 0; i < arrLanguages.size(); i++) {

            switch (arrLanguages.get(i)) {
                case French:

                    arrChatBots.add(
                            new ChatBot(qiContext
                                    , "hello_fr.top"
                                    , "mri_ct_mrt_fr.top"
                                    , "confidential_questions_fr.top"
                                    , "goodbye_fr.top"
                                    , new Locale(Language.FRENCH
                                            , Region.FRANCE)
                                    , arrLanguages.get(i))
                    );

                    break;
                case Italian:

                    arrChatBots.add(
                            new ChatBot(qiContext
                                    , "hello_it.top"
                                    , "mri_ct_mrt_it.top"
                                    , "confidential_questions_it.top"
                                    , "goodbye_it.top"
                                    , new Locale(Language.FRENCH
                                    , Region.FRANCE)
                                    , arrLanguages.get(i))
                    );

                    break;
                case Swedish:

                    arrChatBots.add(
                            new ChatBot(qiContext
                                    , "hello_sw.top"
                                    , "mri_ct_mrt_sw.top"
                                    , "confidential_questions_sw.top"
                                    , "goodbye_sw.top"
                                    , new Locale(Language.FRENCH
                                    , Region.FRANCE)
                                    , arrLanguages.get(i))
                    );
                    break;

                case Turkish:
                    arrChatBots.add(
                            new ChatBot(qiContext
                                    , "hello_tr.top"
                                    , "mri_ct_mrt_tr.top"
                                    , "confidential_questions_tr.top"
                                    , "goodbye_tr.top"
                                    , new Locale(Language.FRENCH
                                    , Region.FRANCE)
                                    , arrLanguages.get(i))
                    );
                    break;
                case Croatioan:
                    arrChatBots.add(
                            new ChatBot(qiContext
                                    , "hello_hr.top"
                                    , "mri_ct_mrt_hr.top"
                                    , "confidential_questions_hr.top"
                                    , "goodbye_hr.top"
                                    , new Locale(Language.FRENCH
                                    , Region.FRANCE)
                                    , arrLanguages.get(i))
                    );
                    break;
                case Portuguese:
                    arrChatBots.add(
                            new ChatBot(qiContext
                                    , "hello_pt.top"
                                    , "mri_ct_mrt_pt.top"
                                    , "confidential_questions_pt.top"
                                    , "goodbye_pt.top"
                                    , new Locale(Language.FRENCH
                                    , Region.FRANCE)
                                    , arrLanguages.get(i))
                    );
                    break;

                case Albanian:
                    arrChatBots.add(
                            new ChatBot(qiContext
                                    , "hello_sq.top"
                                    , "mri_ct_mrt_sq.top"
                                    , "confidential_questions_sq.top"
                                    , "goodbye_sq.top"
                                    , new Locale(Language.FRENCH
                                    , Region.FRANCE)
                                    , arrLanguages.get(i))
                    );
                    break;
                case Spanish:
                    arrChatBots.add(
                    new ChatBot(qiContext
                            , "hello_es.top"
                            , "mri_ct_mrt_es.top"
                            , "confidential_questions_es.top"
                            , "goodbye_es.top"
                            , new Locale(Language.FRENCH
                            , Region.FRANCE)
                            , arrLanguages.get(i))
                    );
                    break;
                case English:
                    arrChatBots.add(
                            new ChatBot(qiContext
                                    , "hello.top"
                                    , "mri_ct_mrt.top"
                                    , "confidential_questions.top"
                                    , "goodbye.top"
                                    , new Locale(Language.FRENCH
                                    , Region.FRANCE)
                                    , arrLanguages.get(i))
                    );
                    break;

                case German:
                    arrChatBots.add(
                            new ChatBot(qiContext
                                    , "hello_de.top"
                                    , "mri_ct_mrt_de.top"
                                    , "confidential_questions_de.top"
                                    , "goodbye_de.top"
                                    , new Locale(Language.FRENCH
                                    , Region.FRANCE)
                                    , arrLanguages.get(i))
                    );
                    break;
            }
        }
    }

    private void removeListener(){
        for (ChatBot cBot : arrChatBots){
            cBot.removeListeners();
        }
    }


}