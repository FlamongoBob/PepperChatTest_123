package com.example.myapplication_pepperchattest;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.ChatBuilder;
import com.aldebaran.qi.sdk.builder.QiChatbotBuilder;
import com.aldebaran.qi.sdk.builder.TopicBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.conversation.AutonomousReactionImportance;
import com.aldebaran.qi.sdk.object.conversation.AutonomousReactionValidity;
import com.aldebaran.qi.sdk.object.conversation.Bookmark;
import com.aldebaran.qi.sdk.object.conversation.Chat;
import com.aldebaran.qi.sdk.object.conversation.QiChatExecutor;
import com.aldebaran.qi.sdk.object.conversation.QiChatbot;
import com.aldebaran.qi.sdk.object.conversation.Topic;
import com.aldebaran.qi.sdk.object.locale.Locale;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatBot {
    private QiContext qiContext;

    private Chat chat;

    private Topic topic1,topic2,topic3, topic4;

    private QiChatbot qiChatbot1, qiChatbot2, qiChatbot3, qiChatbot4;

    private String strTopic_Greeting, strTopic_MRI_CT_MRT, strTopic_ConfidentialQuestions,strTopic_Goodbye;

    private Locale locale;
    private Enum elanguage;
    private boolean isListening = false;
    private Animation animation;
    private Animate animate;

    public ChatBot(QiContext qiContext
            , String strTopic_Greeting
            , String strTopic_MRI_CT_MRT
            , String strTopic_ConfidentialQuestions
            , String strTopic_Goodbye
            , Locale locale
            , Enum elanguage) {
        try {

            this.qiContext = qiContext;
            this.strTopic_Greeting = strTopic_Greeting;
            this.strTopic_MRI_CT_MRT = strTopic_MRI_CT_MRT;
            this.strTopic_ConfidentialQuestions = strTopic_ConfidentialQuestions;
            this.strTopic_Goodbye = strTopic_Goodbye;
            this.locale = locale;
            this.elanguage = elanguage;

            if (this.qiContext != null
                    && this.strTopic_Greeting != null
                    && this.strTopic_MRI_CT_MRT != null
                    && this.strTopic_ConfidentialQuestions != null
                    && this.strTopic_Goodbye != null
                    && this.locale != null) {

                this.topic1 = TopicBuilder
                        .with(qiContext)
                        .withAsset(strTopic_Greeting)
                        .withResource(R.raw.hello__goodbye)
                        .build();

                this.topic2 = TopicBuilder
                        .with(qiContext)
                        .withAsset(strTopic_MRI_CT_MRT)
                        .build();

                this.topic3 = TopicBuilder
                        .with(qiContext)
                        .withAsset(strTopic_ConfidentialQuestions)
                        .build();

                this.topic4 = TopicBuilder
                        .with(qiContext)
                        .withAsset(strTopic_Goodbye)
                        .build();

                Bookmark startBookmark = topic1.getBookmarks().get("start");

                if (topic1 != null
                && topic2 != null
                && topic3 != null) {
                    this.qiChatbot1 = QiChatbotBuilder
                            .with(qiContext)
                            .withTopic(topic1)
                            .withLocale(locale)
                            .build();
                    Map<String, QiChatExecutor> executors = new HashMap<>();
                    executors.put("executorWave", new QiChatExecutor() {


                        @Override
                        public Async async() {
                            return null;
                        }

                        @Override
                        public void runWith(List<String> params) {

                        }

                        @Override
                        public void stop() {

                        }

                        public int getResourceId(AnimationType eAnimationType) {

                            switch (eAnimationType) {
                                case helloGoodbyeWave:
                                    return R.raw.hello__goodbye;
                                default:
                                    return 0;
                            }
                        }
                    })
                    qiChatbot1.setExecutors(executors);

                    this.qiChatbot2 = QiChatbotBuilder
                            .with(qiContext)
                            .withTopic(topic2)
                            .withLocale(locale)
                            .build();


                    this.qiChatbot3 = QiChatbotBuilder
                            .with(qiContext)
                            .withTopic(topic3)
                            .withLocale(locale)
                            .build();

                    this.qiChatbot4 = QiChatbotBuilder
                            .with(qiContext)
                            .withTopic(topic4)
                            .withLocale(locale)
                            .build();
                } else {
                    throw new NullPointerException();
                }

                if (qiChatbot1 != null
                        && qiChatbot2 != null
                        && qiChatbot3 != null
                        && qiChatbot4 != null) {
                    this.chat = ChatBuilder
                            .with(qiContext)
                            .withChatbot(qiChatbot1
                                    , qiChatbot2
                                    , qiChatbot3
                                    , qiChatbot4)
                            .withLocale(locale)
                            .build();

                    isListening = chat.getListening();

                } else {
                    throw new NullPointerException();
                }

                if (chat != null) {
                    this.chat.addOnStartedListener(()->{
                        Log.i(TAG,"Discussion is now in " + locale.getLanguage());
                        this.qiChatbot1.async().goToBookmark(startBookmark, AutonomousReactionImportance.HIGH, AutonomousReactionValidity.IMMEDIATE);
                    });
                } else {
                    throw new NullPointerException();
                }



            } else {
                throw new NullPointerException();
            }

        } catch (Exception ex) {
            String err = "";
            err = ex.getMessage();
            err += "";
        }
    }


    public void removeListeners() {
        if (chat != null) {
            chat.removeAllOnStartedListeners();
        }
    }


}



