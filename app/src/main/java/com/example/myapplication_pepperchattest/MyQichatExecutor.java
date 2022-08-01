package com.example.myapplication_pepperchattest;



import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.conversation.BaseQiChatExecutor;

import java.util.List;

class MyQiChatExecutor extends BaseQiChatExecutor {
    private QiContext qiContext;

    AnimationType eAnimationType;

    public MyQiChatExecutor(QiContext qiContext){//, AnimationType eAnimationType){
        super(qiContext);
    }



    @Override
    public Async async() {
        return super.async();
    }

    @Override
    public void runWith(List<String> params) {
        animate(qiContext);
    }

    @Override
    public void stop() {

    }

    private void animate(QiContext qiContext) {
        // Create an animation.
        Animation animation = AnimationBuilder.with(qiContext) // Create the builder with the context.
                .withResources(R.raw.hello__goodbye) // Set the animation resource.
                .build(); // Build the animation.

        // Create an animate action.
        Animate animate = AnimateBuilder.with(qiContext) // Create the builder with the context.
                .withAnimation(animation) // Set the animation.
                .build(); // Build the animate action.
        animate.run();
    }
}
