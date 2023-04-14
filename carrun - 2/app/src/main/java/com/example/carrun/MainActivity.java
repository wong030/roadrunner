package com.example.carrun;

import android.content.Context;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView touchableGLSurfaceView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        touchableGLSurfaceView = new TouchableGLSurfaceView(this);
        setContentView(touchableGLSurfaceView);
        touchableGLSurfaceView.setFocusableInTouchMode(true);
        touchableGLSurfaceView.requestFocus();


    }

    @Override
    protected void onResume() {
        super.onResume();
        touchableGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        touchableGLSurfaceView.onPause();
    }
}

// touchable GLSurfaceView
class TouchableGLSurfaceView extends GLSurfaceView implements SurfaceHolder.Callback {
    private OurRenderer ourRenderer;

    private Car mcar;
    private Road road = new Road(new Lane(-1),new Lane(0), new Lane(1));




    private int currentLaneIndex;

    int score;
    static int highscore;

    boolean stopped = false;








    private ArrayList<Stripes> stripeslist = new ArrayList<Stripes>();
    private ArrayList<Building> buildinglist = new ArrayList<Building>();
    private ArrayList<ObstCar> obstacles = new ArrayList<ObstCar>();

    private int stripescount = 28;
    private int buildingcount = 8;
    private int obstCount = 5;

    public TouchableGLSurfaceView(Context context) {
        super(context);
        currentLaneIndex = 1;
        getHolder().addCallback(this);


        ourRenderer = new OurRenderer(context);
        setRenderer(ourRenderer);

        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            // Der Benutzer hat den Bildschirm berührt
            float x = event.getX();

            if (x < getWidth() / 3) {
                // Der Benutzer hat auf die linke Seite des Bildschirms getippt
                if (currentLaneIndex > 0) {
                    currentLaneIndex--;
                    mcar.changeLane(road.lanes.get(currentLaneIndex));
                }
            } else if (x > getWidth() * 2 / 3) {
                // Der Benutzer hat auf die rechte Seite des Bildschirms getippt
                if (currentLaneIndex < road.lanes.size() - 1) {
                    currentLaneIndex++;
                    mcar.changeLane(road.lanes.get(currentLaneIndex));
                }
            } else {

            }


        }

        return true;
    }


    private class OurRenderer implements GLSurfaceView.Renderer {

        private float[] modelViewScene = new float[16];

        int fps = 60;



        private long diff,start = System.currentTimeMillis();
        long lastFrameTime = 0;

        long delta;
        float fracSec;





        public OurRenderer(Context c) {
            lastFrameTime = System.currentTimeMillis();



            mcar = new Car();




        }

        public void onDrawFrame(GL10 gl) {
             delta = System.currentTimeMillis() - lastFrameTime;
            fracSec = (float) delta / 1000;
            lastFrameTime = System.currentTimeMillis();
            score += fracSec+1;


            updateCars(fracSec);


            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            GL11 gl11 = (GL11) gl;


            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl11.glLoadMatrixf(modelViewScene, 0);



            road.draw(gl);
            mcar.draw(gl);

            for(ObstCar obst : obstacles) {
                obst.draw(gl);
            }
            for(Stripes stripe : stripeslist){
                stripe.draw(gl);
            }
            for(Building building : buildinglist){
                building.draw(gl);
            }


       // sleep();

        }

        private void updateCars(float fracSec){

            mcar.updatePosition();

            for(Stripes stripe : stripeslist){
                stripe.updatePosition(fracSec);
            }
            for(Building building : buildinglist){
                building.updatePosition(fracSec);
            }

            if(!obstacles.isEmpty()) {
                for (ObstCar obst : obstacles) {
                    obst.updatePosition(fracSec);

                    for (ObstCar obst2 : obstacles) {

                        if ((obst.zCoord <= obst2.zCoord - 10.0f && obst.zCoord >= obst2.zCoord - 30.0f) && (obst.xCoord - 1.5f <= obst2.xCoord + 1.5f) && (obst.xCoord + 1.5f >= obst2.xCoord - 1.5f)) {
                            obst.speed = obst2.speed;
                        }
                    }

                    if (
                            ((mcar.xCoord - 1.5f <= obst.xCoord + 1.5f) && (mcar.xCoord + 1.5f >= obst.xCoord - 1.5f)) &&
                                    ((mcar.zCoord + 4.0f >= obst.zCoord - 4.0f) && (mcar.zCoord - 5.0f <= obst.zCoord + 5.0f))
                    ) {

                   /* for(ObstCar obstt : obstacles) {
                        obstt.stopped = true;
                    }*/
                        stopped = true;
                        mcar.crashed = true;

                    }
                }
            }

            if(!obstacles.isEmpty()){
                if (obstacles.get(0).getZ() >= 30.0f) {

                    obstacles.remove(0);

                }
            }
            if(!stripeslist.isEmpty()){
                if (stripeslist.get(0).zCoord >= 25.0f) {

                    stripeslist.remove(0);

                }
            }
            if(!buildinglist.isEmpty()){
                if (buildinglist.get(0).zCoord >= 50.0f) {

                    buildinglist.remove(0);

                }
            }
            if(!stopped) {
                if (obstCount > obstacles.size()) {
                    for (int i = 0; i < obstCount - obstacles.size(); ++i) {


                        Random rn = new Random();
                        int trail = rn.nextInt(3);

                        // Schwierigkeitsstufen (ab bestimmten Score schwieriger)
                        int level;
                        if (score >= 50) {
                            level = rn.nextInt(3) + 2;
                        } else {
                            level = rn.nextInt(2);
                        }

                        if (obstacles.isEmpty() && !stopped) {


                            ObstCar obstC = new ObstCar(trail, level);
                            obstacles.add(obstC);
                        } else {
                            if (obstacles.get(obstacles.size() - 1).getZ() >= -75.0f && !stopped) {


                                ObstCar obstC = new ObstCar(trail, level);
                                obstacles.add(obstC);
                            }
                        }

                    }

                }
            }

            if ( stripescount > stripeslist.size()) {
                for (int i = 0; i < stripescount - stripeslist.size(); ++i) {

                    if(stripeslist.isEmpty()) {

                    float distance = 130.0f;
                        for(int j = 0;j < 13; j++) {
                            distance = distance - 10.0f;
                            Stripes stripe1 = new Stripes(1,distance);
                            Stripes stripe2 = new Stripes(2,distance);
                            stripeslist.add(stripe1);
                            stripeslist.add(stripe2);
                        }

                    }else{
                        if (stripeslist.get(stripeslist.size() - 1).zCoord >= -90.0f) {


                            Stripes stripe1 = new Stripes(1);
                            Stripes stripe2 = new Stripes(2);
                            stripeslist.add(stripe1);
                            stripeslist.add(stripe2);
                        }
                    }

                }
            }
            if ( buildingcount > buildinglist.size()) {
                for (int i = 0; i < buildingcount - buildinglist.size(); ++i) {

                    if(buildinglist.isEmpty()) {

                        float distance = 130.0f;
                        for(int j = 0;j < 5; j++) {
                            distance = distance - 30.0f;
                            Building building1 = new Building(1,distance);
                            Building building2 = new Building(2,distance);
                            buildinglist.add(building1);
                            buildinglist.add(building2);
                        }

                    }else{
                        if (buildinglist.get(buildinglist.size() - 1).zCoord >= -60.0f) {


                            Building building1 = new Building(1);
                            Building building2 = new Building(2);
                            buildinglist.add(building1);
                            buildinglist.add(building2);
                        }
                    }

                }
            }


        }

        public void sleep() {

            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000/fps;
            if (diff < targetDelay) {
                try{
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {}
            }
            start = System.currentTimeMillis();

        }



        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GL11 gl11 = (GL11) gl;
            gl.glViewport(0, 0, width, height);



            float aspectRatio = (float) width / height;
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            GLU.gluPerspective(gl, 45.0f, aspectRatio, 0.001f, 300.0f);

          GLU.gluLookAt(gl, 0.0f, 30.0f, 40.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl11.glGetFloatv(GL11.GL_MODELVIEW_MATRIX, modelViewScene, 0);
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

            gl.glDisable(GL10.GL_DITHER);
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

            gl.glClearColor(0.22f, 0.69f, 0.87f, 1);
          //  gl.glClearColor(0.f, 0.8f, 0.f, 1);
            /*gl.glEnable(GL10.GL_CULL_FACE);
            //gl.glShadeModel(GL10.GL_FLAT);
            gl.glShadeModel(GL10.GL_SMOOTH);
            gl.glEnable(GL10.GL_DEPTH_TEST);*/




            gl.glEnable(GL10.GL_CULL_FACE);
            //gl.glShadeModel(GL10.GL_FLAT);
            gl.glEnable(GL10.GL_DEPTH_TEST);
            gl.glDepthFunc(GL10.GL_LEQUAL);
          //  gl.glShadeModel(GL10.GL_SMOOTH);
         //   gl.glEnable(GL10.GL_DEPTH_TEST);
        }


    }
}
