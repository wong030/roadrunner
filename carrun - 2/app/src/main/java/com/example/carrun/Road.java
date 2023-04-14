package com.example.carrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class Road {

    public float[] transformationMatrix;
    private FloatBuffer vertexBuffer;
    private FloatBuffer colorBuffer;
    private ShortBuffer topoBuffer;

    public ArrayList<Lane> lanes = new ArrayList<>();




    public Road(Lane a,Lane b,Lane c){
        transformationMatrix = new float[16];
        Matrix.setIdentityM(transformationMatrix, 0);
        lanes.add(a);
        lanes.add(b);
        lanes.add(c);

        float geometryTop[] = {
                -6.0f, 0.0f, -250.0f, 	// v0
                -6.0f, 0.0f, 50.0f, 	// v2
                6.0f, 0.0f, -250.0f, 	// v1
                6.0f, 0.0f, 50.0f };
        float colorTop[] = {
                0.23f, 0.23f, 0.23f, 0.0f, 		// v4
                0.23f, 0.23f, 0.23f, 0.0f, 		// v5
                0.23f, 0.23f, 0.23f, 0.0f,			// v0
                0.23f, 0.23f, 0.23f, 0.0f};		// v1
        short topologyTop[] = {0,1,2,
                1,3,2};						// v1, v5, v4

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

    public static int loadTexture(final Context context, final int resourceId)
    {
        final int[] textureHandle = new int[1];

        GLES20.glGenTextures(1, textureHandle, 0);

        if (textureHandle[0] != 0)
        {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;   // No pre-scaling

            // Read in the resource
            final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

            // Bind to the texture in OpenGL
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);

            // Set filtering
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

            // Load the bitmap into the bound texture.
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

            // Recycle the bitmap, since its data has been loaded into OpenGL.
            bitmap.recycle();
        }

        if (textureHandle[0] == 0)
        {
            throw new RuntimeException("Error loading texture.");
        }

        return textureHandle[0];
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
