package com.example.carrun;

import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Stripes {

    public float[] transformationMatrix;
    private FloatBuffer vertexBuffer;
    private FloatBuffer colorBuffer;
    private ShortBuffer topoBuffer;

    public float zCoord;





    public Stripes(int site, float distance){
        this(site);
        Matrix.setIdentityM(transformationMatrix, 0);
        zCoord += distance;
        Matrix.translateM(transformationMatrix, 0, 0.0f, 0.0f, zCoord);
    }
    public Stripes(int site){

        float geometryTop[];
        float colorTop[] = {
                1.f, 1.f, 1.f, 0.0f,
                1.f, 1.f, 1.f, 0.0f,
                1.f, 1.f, 1.f, 0.0f,
                1.f, 1.f, 1.f, 0.0f};
        short topologyTop[] = {0,1,2,
                1,3,2};

        if (site == 1) {
            geometryTop = new float[]{
                    -2.2f, 0.1f, -1.f,
                    -2.2f, 0.1f, 1.f,
                    -2.0f, 0.1f, -1.f,
                    -2.0f, 0.1f, 1.f };
        }else{
            geometryTop = new float[]{
                    2.0f, 0.1f, -1.f,
                    2.0f, 0.1f, 1.f,
                    2.2f, 0.1f, -1.f,
                    2.2f, 0.1f, 1.f
                   };
        }
        transformationMatrix = new float[16];
        Matrix.setIdentityM(transformationMatrix, 0);

        zCoord = -100.0f;

        Matrix.translateM(transformationMatrix, 0, 0.0f, 0.0f, zCoord);




        ByteBuffer topVertexBB = ByteBuffer.allocateDirect(geometryTop.length * 4);
        topVertexBB.order(ByteOrder.nativeOrder());
        vertexBuffer = topVertexBB.asFloatBuffer();
        vertexBuffer.put(geometryTop);
        vertexBuffer.position(0);

        ByteBuffer topColorBB = ByteBuffer.allocateDirect(colorTop.length * 4);
        topColorBB.order(ByteOrder.nativeOrder());
        colorBuffer = topColorBB.asFloatBuffer();
        colorBuffer.put(colorTop);
        colorBuffer.position(0);

        ByteBuffer topTopologyBB = ByteBuffer.allocateDirect(topologyTop.length * 2);
        topTopologyBB.order(ByteOrder.nativeOrder());
        topoBuffer = topTopologyBB.asShortBuffer();
        topoBuffer.put(topologyTop);
        topoBuffer.position(0);


    }


    public void updatePosition(float fracSec){


            Matrix.translateM(transformationMatrix, 0, 0, 0, fracSec+ 0.4f);
            zCoord += fracSec +0.4f;




    }

    public void draw(GL10 gl) {

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glPushMatrix();
        {
            gl.glMultMatrixf(transformationMatrix, 0);
            gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, topoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, topoBuffer);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        }
        gl.glPopMatrix();
    }
}
