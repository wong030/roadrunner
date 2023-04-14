package com.example.carrun;

import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class Building {

    public float xCoord, yCoord, zCoord;

    public float[] transformationMatrix;

    private float r,g,b;

    private float w,h;


    private FloatBuffer topVertexBuffer;
    private FloatBuffer topColorBuffer;
    private ShortBuffer topTopologyBuffer;

    private FloatBuffer stossvertexBuffer;
    private FloatBuffer stosscolorBuffer;
    private ShortBuffer stosstopoBuffer;

    private FloatBuffer leftvertexBuffer;
    private FloatBuffer leftcolorBuffer;
    private ShortBuffer lefttopoBuffer;
    private static Random rand = new Random();


    public Building(int site, float distance){
        this(site);
        Matrix.setIdentityM(transformationMatrix, 0);
        zCoord += distance;
        Matrix.translateM(transformationMatrix, 0, 0.0f, 0.0f, zCoord);
    }

    public Building(int site){

        w = (float)rand.nextInt(20)+10;
        h =(float) rand.nextInt(15)+7;

        r= 0.69f;
        g = 0.19f;
        b = 0.38f;

        float geometryStoss[];
        float colorStoss[];
        short topologyStoss[];

        float geometryLeft[];
        float colorLeft[];
        short topologyLeft[];

        float geometryTop[];
        float colorTop[];
        short topologyTop[];

        if (site == 1) {
             geometryStoss = new float[] {
                    -20.f, h, 0.0f,
                    -20.f, 0.0f, 0.0f,
                    -7.f, h, 0.0f,
                    -7.f, 0.0f, 0.0f};
             colorStoss = new float[]{
                    r-0.2f, g, b, 0.0f,
                    r-0.2f, g, b, 0.0f,
                    r-0.2f, g, b, 0.0f,
                    r-0.2f, g, b, 0.0f};
             topologyStoss = new short[] {0,1,2,
                    1,3,2};


             geometryLeft = new float[]{
                    -7.f, h, 0.0f,
                    -7.f, 0.0f, 0.0f,
                    -7.f, h, -w,
                    -7.f, 0.0f, -w};
             colorLeft = new float[]{
                    r-0.05f, g, b, 0.0f,
                    r-0.05f, g, b, 0.0f,
                    r-0.05f, g, b, 0.0f,
                    r-0.05f, g, b, 0.0f};
             topologyLeft = new short[]{1,2,0,
                    2,1,3};


             geometryTop = new float[]{
                    -20.0f, h, -w,
                    -20.0f, h, 0.0f,
                    -7.0f, h, -w,
                    -7.0f, h, 0.0f };
             colorTop = new float[]{
                    r-0.1f, g, b, 0.0f,
                    r-0.1f, g, b, 0.0f,
                    r-0.1f, g, b, 0.0f,
                    r-0.1f, g, b, 0.0f};
             topologyTop = new short[]{0,1,2,
                    2,1,3};

            zCoord = -110.0f;


        }else{
            geometryStoss = new float[] {
                    20.f, h, 0.0f,
                    20.f, 0.0f, 0.0f,
                    7.f, h, 0.0f,
                    7.f, 0.0f, 0.0f};
            colorStoss = new float[]{
                    r-0.2f, g, b, 0.0f,
                    r-0.2f, g, b, 0.0f,
                    r-0.2f, g, b, 0.0f,
                    r-0.2f, g, b, 0.0f};
            topologyStoss = new short[] {0,2,1,
                    1,2,3};


            geometryLeft = new float[]{
                    7.f, h, 0.0f,
                    7.f, 0.0f, 0.0f,
                    7.f, h, -w,
                    7.f, 0.0f, -w};
            colorLeft = new float[]{
                    r-0.05f, g, b, 0.0f,
                    r-0.05f, g, b, 0.0f,
                    r-0.05f, g, b, 0.0f,
                    r-0.05f, g, b, 0.0f};
            topologyLeft = new short[]{1,0,2,
                    2,3,1};


            geometryTop = new float[]{
                    20.0f, h, -w,
                    20.0f, h, 0.0f,
                    7.0f, h, -w,
                    7.0f, h, 0.0f };
            colorTop = new float[]{
                    r-0.1f, g, b, 0.0f,
                    r-0.1f, g, b, 0.0f,
                    r-0.1f, g, b, 0.0f,
                    r-0.1f, g, b, 0.0f};
            topologyTop = new short[]{0,2,1,
                    2,3,1};

            zCoord = -100.0f;
        }

        transformationMatrix = new float[16];








        Matrix.setIdentityM(transformationMatrix, 0);

        xCoord = 0.0f;
        yCoord = 0.0f;


        Matrix.translateM(transformationMatrix, 0, xCoord, yCoord, zCoord);






        ByteBuffer stossVertexBB = ByteBuffer.allocateDirect(geometryStoss.length * 4);
        stossVertexBB.order(ByteOrder.nativeOrder());
        stossvertexBuffer = stossVertexBB.asFloatBuffer();
        stossvertexBuffer.put(geometryStoss);
        stossvertexBuffer.position(0);

        ByteBuffer stossColorBB = ByteBuffer.allocateDirect(colorStoss.length * 4);
        stossColorBB.order(ByteOrder.nativeOrder());
        stosscolorBuffer = stossColorBB.asFloatBuffer();
        stosscolorBuffer.put(colorStoss);
        stosscolorBuffer.position(0);

        ByteBuffer stossTopologyBB = ByteBuffer.allocateDirect(topologyStoss.length * 2);
        stossTopologyBB.order(ByteOrder.nativeOrder());
        stosstopoBuffer = stossTopologyBB.asShortBuffer();
        stosstopoBuffer.put(topologyStoss);
        stosstopoBuffer.position(0);





        ByteBuffer leftVertexBB = ByteBuffer.allocateDirect(geometryLeft.length * 4);
        leftVertexBB.order(ByteOrder.nativeOrder());
        leftvertexBuffer = leftVertexBB.asFloatBuffer();
        leftvertexBuffer.put(geometryLeft);
        leftvertexBuffer.position(0);

        ByteBuffer leftColorBB = ByteBuffer.allocateDirect(colorLeft.length * 4);
        leftColorBB.order(ByteOrder.nativeOrder());
        leftcolorBuffer = leftColorBB.asFloatBuffer();
        leftcolorBuffer.put(colorLeft);
        leftcolorBuffer.position(0);

        ByteBuffer leftTopologyBB = ByteBuffer.allocateDirect(topologyLeft.length * 2);
        leftTopologyBB.order(ByteOrder.nativeOrder());
        lefttopoBuffer = leftTopologyBB.asShortBuffer();
        lefttopoBuffer.put(topologyLeft);
        lefttopoBuffer.position(0);





        ByteBuffer topVertexBB = ByteBuffer.allocateDirect(geometryTop.length * 4);
        topVertexBB.order(ByteOrder.nativeOrder());
        topVertexBuffer = topVertexBB.asFloatBuffer();
        topVertexBuffer.put(geometryTop);
        topVertexBuffer.position(0);

        ByteBuffer topColorBB = ByteBuffer.allocateDirect(colorTop.length * 4);
        topColorBB.order(ByteOrder.nativeOrder());
        topColorBuffer = topColorBB.asFloatBuffer();
        topColorBuffer.put(colorTop);
        topColorBuffer.position(0);

        ByteBuffer topTopologyBB = ByteBuffer.allocateDirect(topologyTop.length * 2);
        topTopologyBB.order(ByteOrder.nativeOrder());
        topTopologyBuffer = topTopologyBB.asShortBuffer();
        topTopologyBuffer.put(topologyTop);
        topTopologyBuffer.position(0);




    }


    public void draw(GL10 gl) {

        gl.glPushMatrix();


        gl.glMultMatrixf(transformationMatrix, 0);

        gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, stossvertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, stosscolorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, stosstopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, stosstopoBuffer);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, leftvertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, leftcolorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, lefttopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, lefttopoBuffer);


        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, topVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, topColorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, topTopologyBuffer.limit(), GL10.GL_UNSIGNED_SHORT, topTopologyBuffer);


        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glPopMatrix();
    }


    public void updatePosition(float fracSec){


        Matrix.translateM(transformationMatrix, 0, 0, 0, fracSec+ 0.4f);
        zCoord += fracSec +0.4f;




    }
}
